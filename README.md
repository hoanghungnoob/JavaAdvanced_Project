đây là dự án lập trình java swing với đề bài là quản lí cửa hàng quần áo.

Folder SQL đang chứa file qlycuahang.sql đây là file mẫu để insert dữ liệu. Tuy nhiên trong toàn bộ dự án chúng tôi đã kết nối với cơ sở dữ liệu là MySql Workbench 8.0 nên là các kiểu dữ liệu cũng như cách connect với database đề liên quan đến cơ sở dữ liệu này.

đây mới là file data của mysql workbench


create database qlycuahang;
use qlycuahang;
create table customer (
customer_id tinyint primary key auto_increment,
customer_name varchar(255),
address varchar(255),
phone_number varchar(15),
username varchar(50),
password varchar(255),
status char(1)
);


CREATE TABLE purchase (
    purchase_id TINYINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50), 
    product_id TINYINT,
    selling_price DECIMAL(10,2),
    quantity TINYINT,
    sale_date DATE,
    total_amount DECIMAL(10,2)
);



create table product (
product_id tinyint primary key auto_increment,
product_name varchar(255),
quantity int,
cost_price decimal(10,2),
selling_price decimal(10,2)
);



INSERT INTO customer (customer_name, address, phone_number, username, password, status)
VALUES ('John Doe', '123 Main Street', '1234567890', 'johndoe', 'password123', '1'),
       ('Jane Smith', '456 Elm Street', '9876543210', 'janesmith', 'password456', '1'),
       ('Hung', '789 Oak Street', '5555555555', 'hoanghung', 'hoanghung', '0');

INSERT INTO product (product_name, quantity, cost_price, selling_price)
VALUES
    ('Áo sơ mi trắng', 20, 15.00, 29.99),
    ('Quần jeans đen', 15, 25.00, 49.99),
    ('Váy hoa dài', 10, 29.99, 59.99),
    ('Áo khoác dù', 12, 39.99, 79.99),
    ('Đầm chấm bi', 8, 34.99, 69.99),
    ('Áo len nữ', 18, 19.99, 39.99),
    ('Quần tây', 20, 22.99, 45.99),
    ('Áo phông nữ', 25, 12.99, 24.99),
    ('Chân váy xếp ly', 15, 27.99, 54.99),
    ('Áo khoác da nữ', 10, 49.99, 99.99);
 