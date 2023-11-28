CREATE DATABASE IF NOT EXISTS OnlineStore;
USE OnlineStore;

CREATE TABLE clients
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(55) NOT NULL,
    last_name  VARCHAR(55) NOT NULL,
    birth_date TIMESTAMP   NOT NULL,
    email      VARCHAR(55) NOT NULL UNIQUE,
    address    VARCHAR(55),
    telephone  VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_clients_email ON clients (email);

CREATE TABLE products
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(55)    NOT NULL,
    description VARCHAR(100)   NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    stock       INT            NOT NULL,
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_products_name ON products (name);

CREATE TABLE invoices
(
    id         INT AUTO_INCREMENT PRIMARY KEY,
    clients_id INT            NOT NULL,
    total      DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (clients_id) REFERENCES clients (id)
);

CREATE TABLE invoices_details
(
    id          INT AUTO_INCREMENT PRIMARY KEY,
    invoices_id INT            NOT NULL,
    products_id INT            NOT NULL,
    quantity    INT            NOT NULL,
    price       DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (invoices_id) REFERENCES invoices (id),
    FOREIGN KEY (products_id) REFERENCES products (id)
);

-- index foreign keys
CREATE INDEX idx_invoices_clients_id ON invoices (clients_id);
CREATE INDEX idx_invoice_details_invoices_id ON invoices_details (invoices_id);
CREATE INDEX idx_invoice_details_products_id ON invoices_details (products_id);
