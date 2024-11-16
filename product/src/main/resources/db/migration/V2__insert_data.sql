-- Insert sample data into the category table

ALTER SEQUENCE category_seq RESTART WITH 1 INCREMENT BY 50;

INSERT INTO category (id, description, name)
VALUES
    (nextval('category_seq'), 'Electronics and gadgets', 'Electronics'),
    (nextval('category_seq'), 'Books and stationery', 'Books'),
    (nextval('category_seq'), 'Clothing and accessories', 'Fashion'),
    (nextval('category_seq'), 'Health and beauty products', 'Health & Beauty');

ALTER SEQUENCE product_seq RESTART WITH 1 INCREMENT BY 50;
-- Insert sample data into the product table
INSERT INTO product (id, description, name, available_quantity, price, category_id)
VALUES
    (nextval('product_seq'), 'A high-quality smartphone with 128GB storage', 'Smartphone', 50, 699.99, 1),
    (nextval('product_seq'), 'A lightweight and durable laptop with 16GB RAM', 'Laptop', 30, 1299.99, 1),
    (nextval('product_seq'), 'A thrilling mystery novel by a bestselling author', 'Mystery Novel', 100, 19.99, 51),
    (nextval('product_seq'), 'A pack of 5 gel pens with smooth ink flow', 'Gel Pens', 200, 4.99, 51),
    (nextval('product_seq'), 'A stylish leather jacket for men', 'Leather Jacket', 25, 149.99, 101),
    (nextval('product_seq'), 'A casual cotton T-shirt for everyday wear', 'Cotton T-Shirt', 50, 15.99, 101),
    (nextval('product_seq'), 'A skincare cream for smooth and glowing skin', 'Skincare Cream', 40, 25.99, 151),
    (nextval('product_seq'), 'A pack of 3 toothbrushes with soft bristles', 'Toothbrush Pack', 80, 7.49, 151);
