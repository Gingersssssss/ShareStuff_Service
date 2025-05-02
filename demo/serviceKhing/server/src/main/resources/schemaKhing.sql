CREATE TABLE borrowed_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    borrower_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item(id)
);

CREATE TABLE item_all (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    quantity INT NOT NULL
);

INSERT INTO item_all (name, category, quantity)
VALUES 
  ('Keyboard', 'Electronics', 5),
  ('Mouse', 'Electronics', 3),
  ('Chair', 'Furniture', 2);

  
INSERT INTO borrowed_record (borrower_id, item_id, borrow_date, due_date)
VALUES 
    (101, 1, '2025-04-01', '2025-04-15'),
    (102, 2, '2025-04-10', '2025-04-25'),
    (103, 3, '2025-04-20', '2025-05-01'),
    (104, 1, '2025-04-05', '2025-04-12'), -- overdue
    (105, 2, '2025-03-25', '2025-04-05'); -- overdue