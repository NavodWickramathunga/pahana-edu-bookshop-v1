@PostMapping("/add")
public ResponseEntity<?> addCustomer(@RequestBody Customer customer,
                                     @RequestParam String mobile,
                                     @RequestParam String password) {
    if (!userService.authenticateAdmin(mobile, password)) {
        return ResponseEntity.status(403).body("Access Denied: Admins only");
    }
    return ResponseEntity.ok(customerService.addCustomer(customer));
}
