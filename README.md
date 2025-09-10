Project Overview: Pahana Edu Billing System

This project delivers a robust and user-friendly billing system for "Pahana Edu Bookshop," encompassing a full-stack application designed to streamline operations from inventory management to customer billing. The system is built with a powerful Spring Boot backend and a dynamic, responsive frontend.

Key Features:

Secure Authentication and Authorization:

Admin Login: Dedicated login page (AdminLogin.html) for administrators with credentials managed via application.properties and secured using Spring Security's BCryptPasswordEncoder.
User Login & Registration: Separate interfaces (UserLogin.html) for customer login and self-registration, allowing users to create accounts with mobile numbers and passwords.
Role-Based Access Control: Spring Security is configured to differentiate between user roles (e.g., ADMIN for item management) ensuring appropriate access to API endpoints.
Comprehensive Item Management (CRUD Operations):

Create (Add Item): Administrators can seamlessly add new items directly from the AdminDashboard.html interface, providing details like name, price, stock quantity, and an optional image.
Read (View Items): All inventory items are fetched and displayed in a clear table on the admin dashboard, and also on the main index.html page for customers to browse.
Update (Modify Item): (Implicitly supported through re-adding or future enhancement, though not explicitly shown in provided HTML for direct editing of existing items).
Delete (Remove Item): Administrators have the ability to delete items from the inventory directly from the admin dashboard.
Image Upload Functionality: The system supports uploading item images, which are stored locally in an uploads/ directory and served dynamically.
Dynamic Shopping Cart and Order Processing:

User-Specific Carts: Each user maintains a unique shopping cart, allowing them to add and remove items.
Real-time Cart Updates: Cart contents are dynamically loaded and displayed, reflecting additions and removals.
Bill Generation: Upon completing a purchase, the system generates a detailed bill, calculating the total based on items and quantities in the cart.
PDF Bill Generation: Bills are generated as professional PDF documents using the iText library.
Email Delivery: Customers can opt to receive their bills via email, with the PDF attached, facilitated by Spring Mail.
Bill Download: Users also have the option to directly download their generated bills as PDF files.
User and Customer Management (CRUD Operations):

Create (Register User): New users can register themselves through the UserLogin.html page.
Read (View Users/Customers): (Implicitly supported for admin viewing, though not explicitly shown in provided HTML for a user list). User details are fetched for profile display and bill generation.
Update (Edit Profile): Users can update their personal information (username, email) through a dedicated profile editing interface (profile.html and UserDashboard.html).
Delete (Remove User): (Functionality exists in UserController.java but not explicitly exposed in the provided HTML for direct user deletion by admin).
Modern and Responsive User Interface:

Tailwind CSS: The frontend is styled using Tailwind CSS, providing a clean, modern, and highly responsive design that adapts well to various screen sizes.
Intuitive Navigation: Clear navigation across different sections like Home, Admin Dashboard, Cart, Checkout, and Help.
Theme Switching: A simple theme switcher allows users to toggle between different color schemes (blue and green) for a personalized experience.
Image Carousel: The homepage features an interactive image carousel to showcase promotions or featured products.
Robust Backend Architecture:

Spring Boot: Provides a powerful and efficient framework for building the RESTful API endpoints.
Spring Data MongoDB: Utilized for flexible and scalable NoSQL database operations, managing collections for items, users, carts, bills, orders, and customers.
Global Exception Handling: A centralized GlobalExceptionHandler ensures graceful error handling for ResourceNotFoundException and other general exceptions, providing informative responses to the client.
CORS Configuration: Properly configured Cross-Origin Resource Sharing (CORS) to allow frontend applications (e.g., running on localhost:8000) to interact with the backend API.
This system provides a complete solution for managing a bookshop's billing and inventory, offering a seamless experience for both administrators and customers through well-defined CRUD operations across key entities.
