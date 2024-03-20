INSERT INTO customer (firstname, lastname, gender, id_number, issue_date, issue_by)
VALUES ('Nguyen Van', 'A', 'MALE', '024202007168', '2024-03-19', 'CA Ha Noi');

INSERT INTO `option` (id, label, dtype)
VALUES (1, 'Sinh vien', 'Job');

INSERT INTO `option` (id, label, dtype)
VALUES (2, 'Nap game', 'LoanPurpose');

INSERT INTO loan (
    id,
    amount,
    created_at,
    due_date,
    monthly_income,
    remaining,
    yearly_interest_rate,
    customer_id,
    job_id,
    purpose_id
) VALUES (1, 150, '2024-03-01', '2024-05-01', 100, 50, 5.0, 1, 1, 2);

INSERT INTO saving (
    id,
    amount,
    customer_id,
    deposit_date,
    due_date,
    term,
    withdraw_date,
    yearly_interest_rate
) VALUES (1, 200, 1, '2024-03-01', '2024-04-01', 'ONE_MONTH', NULL, 6.0);