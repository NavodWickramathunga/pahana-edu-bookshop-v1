package com.pahanaedu.billingsystem.service;

import com.pahanaedu.billingsystem.model.Bill;
import com.pahanaedu.billingsystem.model.Cart;
import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.repository.BillRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.Date;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @Autowired
    private JavaMailSender mailSender;

    public Bill createBill(Long userId) {
        User user = userService.getAllUsers().stream()
                .filter(u -> u.getId().equals(userId))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User not found"));
        Cart cart = cartService.getCartByUserId(userId);
        Double total = cart.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
        Bill bill = new Bill(userId, user.getUsername(), user.getEmail(), cart.getItems(), total, new Date());
        bill.setId(System.currentTimeMillis());
        return billRepository.save(bill);
    }

    public byte[] generateBillPdf(Bill bill) throws Exception {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
             PdfWriter writer = new PdfWriter(baos);
             PdfDocument pdf = new PdfDocument(writer);
             Document document = new Document(pdf)) {

            document.add(new Paragraph("Pahana Edu Billing System")
                    .setFontSize(20)
                    .setBold()
                    .setTextAlignment(TextAlignment.CENTER));
            document.add(new Paragraph("Bill ID: " + bill.getId()));
            document.add(new Paragraph("Customer: " + bill.getUsername()));
            document.add(new Paragraph("Email: " + bill.getEmail()));
            document.add(new Paragraph("Date: " + bill.getDate().toString()));
            document.add(new Paragraph("\n"));

            Table table = new Table(new float[]{200, 100, 100});
            table.addHeaderCell("Item Name");
            table.addHeaderCell("Price");
            table.addHeaderCell("Quantity");
            for (var item : bill.getItems()) {
                table.addCell(item.getName());
                table.addCell(String.format("$%.2f", item.getPrice()));
                table.addCell(String.valueOf(item.getQuantity()));
            }
            document.add(table);
            document.add(new Paragraph("\nTotal: $" + String.format("%.2f", bill.getTotal()))
                    .setFontSize(14)
                    .setBold());

            return baos.toByteArray();
        }
    }

    public void sendBillEmail(Bill bill, byte[] pdfBytes) throws Exception {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(bill.getEmail());
        helper.setSubject("Your Pahana Edu Bill");
        helper.setText("Dear " + bill.getUsername() + ",\n\nPlease find your bill attached.\n\nThank you,\nPahana Edu");
        helper.addAttachment("bill_" + bill.getId() + ".pdf", new ByteArrayResource(pdfBytes));
        mailSender.send(message);
    }

    public java.util.Optional<Bill> findById(Long billId) {
        return billRepository.findById(billId);
    }
}