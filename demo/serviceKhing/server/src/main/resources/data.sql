INSERT INTO borrowed_record (borrower_id, item_id, borrow_date, due_date)
VALUES 
    ('Atitaya', 1, '2025-04-01', '2025-04-15'),
    ('Atitaya', 2, '2025-04-10', '2025-04-25'),
    ('Atitaya', 3, '2025-04-20', '2025-05-01'),
    ('Atitaya', 1, '2025-04-05', '2025-04-12'), -- overdue
    ('Atitaya', 2, '2025-03-25', '2025-04-05'); -- overdue