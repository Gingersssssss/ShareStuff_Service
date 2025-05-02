CREATE TABLE borrowed_record (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    borrower_id BIGINT NOT NULL,
    item_id BIGINT NOT NULL,
    borrow_date DATE NOT NULL,
    due_date DATE NOT NULL,
    FOREIGN KEY (item_id) REFERENCES item(id)
);

INSERT INTO borrowed_record (borrower_id, item_id, borrow_date, due_date)
VALUES 
    (101, 1, '2025-04-01', '2025-04-15'),
    (102, 2, '2025-04-10', '2025-04-25'),
    (103, 3, '2025-04-20', '2025-05-01'),
    (104, 1, '2025-04-05', '2025-04-12'), -- overdue
    (105, 2, '2025-03-25', '2025-04-05'); -- overdue