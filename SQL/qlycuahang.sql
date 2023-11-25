create database qlycuahang;
use qlycuahang;
CREATE TABLE customer (
    customer_id TINYINT PRIMARY KEY IDENTITY(1,1),
    customer_name NVARCHAR(255),
    address NVARCHAR(255),
    phone_number NVARCHAR(15),
    username NVARCHAR(50),
    password NVARCHAR(255),
    status CHAR(1)
);




CREATE TABLE purchase (
    purchase_id TINYINT PRIMARY KEY IDENTITY(1,1),
    username NVARCHAR(50), 
    product_id TINYINT,
    selling_price DECIMAL(10,2),
    quantity TINYINT,
    sale_date DATE,
    total_amount DECIMAL(10,2)
);




CREATE TABLE product (
    product_id TINYINT PRIMARY KEY IDENTITY(1,1),
    product_name NVARCHAR(255),
    quantity INT,
    cost_price DECIMAL(10,2),
    selling_price DECIMAL(10,2)
);


INSERT INTO customer (customer_name, address, phone_number, username, password, status)
VALUES (N'John Doe', N'123 Main Street', N'1234567890', N'johndoe', N'password123', '1'),
       (N'Jane Smith', N'456 Elm Street', N'9876543210', N'janesmith', N'password456', '1'),
       (N'Hung', N'789 Oak Street', N'5555555555', N'hoanghung', N'hoanghung', '0');




INSERT INTO product (product_name, quantity, cost_price, selling_price)
VALUES
    (N'Áo sơ mi trắng', 20, 15.00, 29.99),
    (N'Quần jeans đen', 15, 25.00, 49.99),
    (N'Váy hoa dài', 10, 29.99, 59.99),
    (N'Áo khoác dù', 12, 39.99, 79.99),
    (N'Đầm chấm bi', 8, 34.99, 69.99),
    (N'Áo len nữ', 18, 19.99, 39.99),
    (N'Quần tây', 20, 22.99, 45.99),
    (N'Áo phông nữ', 25, 12.99, 24.99),
    (N'Chân váy xếp ly', 15, 27.99, 54.99),
    (N'Áo khoác da nữ', 10, 49.99, 99.99);
