CREATE DATABASE IF NOT EXISTS OnlineStore;
USE OnlineStore;

CREATE TABLE clients (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(55) NOT NULL,
    last_name VARCHAR(55) NOT NULL,
    birth_date TIMESTAMP NOT NULL,
    email VARCHAR(55) NOT NULL UNIQUE,
    address VARCHAR(55),
    telephone VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(55) NOT NULL,
    description VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE invoices (
    id INT AUTO_INCREMENT PRIMARY KEY,
    clients_id INT NOT NULL,
    total DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (clients_id) REFERENCES clients(id)
);

CREATE TABLE invoices_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    invoices_id INT NOT NULL,
    products_id INT NOT NULL,
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (invoices_id) REFERENCES invoices(id),
    FOREIGN KEY (products_id) REFERENCES products(id)
);
