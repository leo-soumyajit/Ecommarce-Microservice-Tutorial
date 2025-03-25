INSERT INTO orders (total_price, order_status) VALUES
(100.50, 'PENDING'),
(150.75, 'CONFIRMED'),
(200.25, 'DELIVERED'),
(50.00, 'CANCELLED'),
(120.00, 'PENDING'),
(180.80, 'CONFIRMED'),
(220.00, 'DELIVERED'),
(75.50, 'CANCELLED'),
(130.00, 'PENDING'),
(170.00, 'CONFIRMED');

INSERT INTO orders_item (order_id, product_id, quantity) VALUES
(1, 1, 2),
(1, 3, 1),
(2, 2, 4),
(2, 5, 1),
(3, 4, 3),
(3, 2, 2),
(4, 7, 1),
(5, 9, 2),
(5, 10, 1),
(6, 8, 3);
