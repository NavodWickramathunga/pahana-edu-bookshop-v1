public boolean authenticateAdmin(String mobileNumber, String rawPassword) {
    Optional<User> userOpt = userRepository.findByMobileNumber(mobileNumber);
    return userOpt.isPresent() &&
           passwordEncoder.matches(rawPassword, userOpt.get().getPassword()) &&
           "ADMIN".equalsIgnoreCase(userOpt.get().getRole());
}
