# 📚 Pahana Edu Billing System  

A **full-stack billing system** designed for **Pahana Edu Bookshop**, combining a **Spring Boot backend** with a **responsive Tailwind CSS frontend**. This system streamlines **inventory management, customer billing, and PDF invoice generation**, making it efficient for both administrators and customers.  

---

## ✨ Key Features  

### 🔐 Secure Authentication & Authorization  
- **Admin Login**: Dedicated admin access via `AdminLogin.html`, secured with **Spring Security + BCryptPasswordEncoder**.  
- **User Login & Registration**: Customers can self-register using mobile numbers & passwords via `UserLogin.html`.  
- **Role-Based Access Control**: Differentiated access for **ADMIN** and **USER** roles.  

### 📦 Item & Inventory Management (CRUD)  
- **Create**: Admins can add new items with details (name, price, stock, image).  
- **Read**: Items displayed in **AdminDashboard.html** (for admins) and `index.html` (for customers).  
- **Update**: Supports modifying existing items.  
- **Delete**: Admins can remove items directly from the dashboard.  
- **Image Uploads**: Item images stored in `/uploads` and served dynamically.  

### 🛒 Shopping Cart & Order Processing  
- **User-Specific Carts**: Each customer maintains their own shopping cart.  
- **Real-time Updates**: Add/remove items with dynamic cart updates.  
- **Bill Generation**: Calculates totals automatically.  
- **PDF Invoice**: Bills generated using **iText**.  
- **Email Delivery**: Bills sent via **Jakarta Mail** with PDF attached.  
- **Download Option**: Users can download PDF bills directly.  

### 👥 User & Customer Management (CRUD)  
- **Register Users**: Customers sign up via `UserLogin.html`.  
- **Profile Management**: Users can update details via `profile.html` & `UserDashboard.html`.  
- **Admin Management**: Support for user/customer CRUD in backend controllers.  

### 🎨 Modern UI & Experience  
- **Tailwind CSS**: Responsive, modern design for all devices.  
- **Theme Switcher**: Users can toggle between **blue** & **green** themes.  
- **Image Carousel**: Interactive homepage carousel for promotions.  
- **Intuitive Navigation**: Seamless movement across Home, Dashboard, Cart, Checkout, and Help.  

### ⚙️ Robust Backend Architecture  
- **Spring Boot**: RESTful API backend.  
- **Spring Data MongoDB**: NoSQL database for items, users, carts, bills & orders.  
- **Global Exception Handling**: Managed via `GlobalExceptionHandler`.  
- **CORS Configured**: Smooth frontend-backend integration.  

---

## 🛠️ Tech Stack  

### 💻 Backend  
- **Spring Boot** (REST APIs)  
- **Spring Security** (Authentication & Authorization)  
- **Spring Data MongoDB** (Database Operations)  
- **iText** (PDF Generation)  
- **Jakarta Mail** (Email Services)  
- **Lombok** (Reduce Boilerplate Code)  

### 🎨 Frontend  
- **HTML5, CSS3**  
- **Tailwind CSS** (Modern UI Framework)  
- **JavaScript**  

### 🗄️ Database  
- **MongoDB** (NoSQL, scalable data storage)  

---

## 📸 Screenshots  

👉 <img width="1339" height="583" alt="{34C5B82C-0B5F-4FE5-85A5-7F4740551630}" src="https://github.com/user-attachments/assets/2ed45cac-a34d-4d92-b39d-874e94bbb1fe" />

---

## 🚀 How to Run the Project  

1. **Clone the repository**  
   ```bash
   git clone https://github.com/your-username/pahana-edu-billing-system.git
   cd pahana-edu-billing-system
