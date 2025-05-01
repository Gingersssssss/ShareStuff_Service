DROP TABLE IF EXISTS item;

CREATE TABLE item (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    category VARCHAR(255),
    quantity INT
);

INSERT INTO item (name, category, quantity) VALUES 
('Tent', 'Camping', 3),
('Lantern', 'Camping', 5),
('Book A', 'Book', 4),
('Mop', 'Cleaning', 2),
('Laptop', 'Electronics', 1);
