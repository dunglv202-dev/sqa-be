INSERT INTO authority (id, code)
VALUES (1, 'ROLE_MANAGER');

INSERT INTO authority (id, code)
VALUES (2, 'ROLE_EMPLOYEE');

# RAW PASSWORD: pass
INSERT INTO user (id, display_name, password, username)
VALUES (1, 'Manager', '$2a$10$lZOpHEwtoY3e6LxqvOq0lu/eI0tOEKQudZxJVJqktnqT95pHHmNTO', 'manager'),
       (2, 'Employee', '$2a$10$lZOpHEwtoY3e6LxqvOq0lu/eI0tOEKQudZxJVJqktnqT95pHHmNTO', 'employee');

INSERT INTO user_authorities (user_id, authorities_id)
VALUES (1, 1),
       (2, 2);

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
    purpose_id,
    type
) VALUES (1, 150, '2024-03-01', '2024-05-01', 100, 50, 5.0, 1, 1, 2, 'UNSECURED');

INSERT INTO saving (
    id,
    amount,
    customer_id,
    deposit_date,
    due_date,
    term_in_month,
    withdraw_date,
    yearly_interest_rate
) VALUES (1, 200, 1, '2024-03-01', '2024-04-01', 1, NULL, 6.0);