# 🚗 Vehicle Spare Parts Shop

![Project Banner](images/banner.png)

## 📋 Table of Contents
- [📖 Project Description](#project-description)
- [✨ Features](#features)
- [🛠️ Technologies Used](#technologies-used)
- [📦 Setup Instructions](#setup-instructions)
- [📸 Screenshots](#screenshots)
- [🤝 Contributing](#contributing)
- [📜 License](#license)
- [📧 Contact Information](#contact-information)

## 📖 Project Description
Vehicle Spare Parts Shop is a web application developed using Java, JSP, Servlets, and MySQL. It allows users to purchase vehicle spare parts, view their order history, and manage pending orders. The admin has a comprehensive dashboard for managing products and orders, and suppliers have a separate dashboard for handling order requests and managing inventory.

## ✨ Features
### 🛒 User Features
- Browse and purchase vehicle spare parts.
- View order history.
- Update pending orders.
- Upload payment receipts for orders.

### 🛠️ Admin Features
- View and manage pending orders.
- Accept or reject orders.
- Dashboard for adding, updating, deleting, and retrieving products.
- Send product requests to suppliers.

### 📦 Supplier Features
- Dashboard to view received orders and accept or reject them.
- Manage product inventory (add, update, delete, retrieve).
- View accepted and rejected requests.

## 🛠️ Technologies Used
- **Java** (JDK 19)
- **JSP** and **Servlets**
- **MySQL** (MySQL Workbench)
- **Tomcat Server** (Version 10.1)

## 📦 Setup Instructions

### 1. Clone the Repository
```sh
git clone https://github.com/your-username/vehicle-spare-parts-shop.git
cd vehicle-spare-parts-shop
## ℹ️ Instructions for Customization

### 🖼️ Images
Ensure you have the images in a folder named `images` and replace the filenames with your actual image filenames if they differ.

### 🛠️ Database Configuration
Adjust the database configuration file path (`src/main/resources/database.properties`) if your project structure is different.

### 📞 Contact Information
Replace "John Doe", `johndoe@example.com`, and the LinkedIn and GitHub URLs with your actual details.

### 🔗 Repository URL
Update `https://github.com/your-username/vehicle-spare-parts-shop.git` with the actual URL of your GitHub repository.

## 🛠️ Setup the Database

### 1. Import the SQL Script
To set up the database, follow these steps:

1. **Open MySQL Workbench**:
   - Launch MySQL Workbench on your computer.

2. **Open the SQL Script**:
   - Go to `File` -> `Open SQL Script`.
   - Select the provided SQL script file (`database_setup.sql`).

3. **Execute the SQL Script**:
   - Click the `Execute` button to run the script.
   - This will create the necessary tables and populate initial data.

### 2. Update Database Configuration
After setting up the database, update the database configuration in the project:

1. **Open the Project in Your Preferred IDE**:
   - Use your preferred Integrated Development Environment (IDE) to open the project.

2. **Navigate to the Database Configuration File**:
   - Locate the database configuration file (e.g., `src/main/resources/database.properties`).

3. **Update the Database URL, Username, and Password**:
   - Edit the configuration file to match your MySQL setup. For example:
     ```properties
     db.url=jdbc:mysql://localhost:3306/vehicle_spare_parts
     db.username=root
     db.password=yourpassword
     ```

## 🖼️ Screenshots

### 🖥️ User Dashboard
![User Dashboard](images/user_dashboard.png)

### 🖥️ Admin Dashboard
![Admin Dashboard](images/admin_dashboard.png)

### 🖥️ Supplier Dashboard
![Supplier Dashboard](images/supplier_dashboard.png)
