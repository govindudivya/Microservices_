-- Insert into category table
INSERT INTO public.category (id, description, name) VALUES 
  (nextval('category_seq'), 'Electronics', 'Electronics'),
  (nextval('category_seq'), 'Clothing', 'Clothing'),
  (nextval('category_seq'), 'Home Appliances', 'Home Appliances'),
  (nextval('category_seq'), 'Books', 'Books');

-- Insert into product table
INSERT INTO public.product (id, description, name, available_quantity, price, category_id) VALUES 
  (nextval('product_seq'), 'Smartphone with HD display', 'Smartphone X', 100.0, 499.99, 1),
  (nextval('product_seq'), 'Wireless Bluetooth Earbuds', 'Earbuds Pro', 150.0, 79.99, 1),
  (nextval('product_seq'), 'Men''s Casual Shirt, Blue', 'Casual Shirt', 80.0, 29.99, 51),
  (nextval('product_seq'), 'Stainless Steel Microwave Oven', 'Microwave Oven', 50.0, 199.99, 101),
  (nextval('product_seq'), 'Java Programming Guide', 'Java Programming Book', 120.0, 39.99, 151);
