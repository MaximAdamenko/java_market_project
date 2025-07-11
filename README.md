# 🛍️ Max & Roni Store

**A Java-Based Sales Management System**  
This project simulates a basic e-commerce environment using object-oriented Java, enabling interactions between buyers, sellers, products, and orders. It is designed to be extendable and easy to maintain.

---

## 📌 Overview

The Max & Roni Store project allows users to:
- Create and manage users (Buyers and Sellers)
- Add and purchase products
- Process and manage customer orders
- Bundle products into special packages
- Track operations via a centralized business manager

---

## 🗂️ Project Structure

```bash
Max's & Roni store/
├── Address.java            # Represents a user's physical address
├── BusinessManager.java    # Core class for managing users, orders, and logic
├── Buyer.java              # Subclass of User representing a customer
├── Seller.java             # Subclass of User representing a vendor
├── Product.java            # Represents a product sold in the store
├── SpecialPackage.java     # Represents a bundle of products
├── Order.java              # Represents a customer's order
├── User.java               # Abstract base class for all users
├── Main.java               # Main application entry point
├── MarathonDiagramRoniAndMax.pdf   # UML class diagram
└── .idea/, .iml, out/...   # IDE and build folders (optional)
