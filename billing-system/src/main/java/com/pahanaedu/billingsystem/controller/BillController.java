package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Bill;
import com.pahanaedu.billingsystem.model.Item;
import com.pahanaedu.billingsystem.service.BillService;
import com.pahanaedu.billingsystem.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private BillService billService;


    @GetMapping("/items")
    public ResponseEntity<List<Item>> getItemsInStock() {
        List<Item> items = itemService.getAllItems();
        items.removeIf(item -> item.getStock() == null || item.getStock() <= 0);
        return ResponseEntity.ok(items);
    }

    @PostMapping("/generate/{userId}")
    public ResponseEntity<Bill> generateBill(@PathVariable Long userId) {
        Bill bill = billService.createBill(userId);
        return ResponseEntity.ok(bill);
    }

    @GetMapping("/download/{billId}")
    public ResponseEntity<ByteArrayResource> downloadBill(@PathVariable Long billId) throws Exception {
        Bill bill = billService.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
        byte[] pdfBytes = billService.generateBillPdf(bill);
        ByteArrayResource resource = new ByteArrayResource(pdfBytes);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=bill_" + billId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(resource);
    }

    @PostMapping("/email/{billId}")
    public ResponseEntity<Void> sendBillEmail(@PathVariable Long billId) throws Exception {
        Bill bill = billService.findById(billId).orElseThrow(() -> new RuntimeException("Bill not found"));
        byte[] pdfBytes = billService.generateBillPdf(bill);
        billService.sendBillEmail(bill, pdfBytes);
        return ResponseEntity.ok().build();
    }
}