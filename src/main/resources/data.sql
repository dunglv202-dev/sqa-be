INSERT INTO authority (id, code)
VALUES (1, 'ROLE_MANAGER');

INSERT INTO authority (id, code)
VALUES (2, 'ROLE_EMPLOYEE');

INSERT INTO authority (id, code)
VALUES (3, 'ROLE_DIRECTOR');

# RAW PASSWORD: pass
INSERT INTO user (id, display_name, password, username)
VALUES (1, 'Manager', '$2a$10$lZOpHEwtoY3e6LxqvOq0lu/eI0tOEKQudZxJVJqktnqT95pHHmNTO', 'manager'),
       (2, 'Employee', '$2a$10$lZOpHEwtoY3e6LxqvOq0lu/eI0tOEKQudZxJVJqktnqT95pHHmNTO', 'employee'),
       (3, 'Director', '$2a$10$lZOpHEwtoY3e6LxqvOq0lu/eI0tOEKQudZxJVJqktnqT95pHHmNTO', 'director');

INSERT INTO user_authorities (user_id, authorities_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

INSERT INTO customer (firstname, lastname, gender, id_number, issue_date, issue_by)
VALUES ('Nguyen Van', 'A', 'MALE', '024202007168', '2024-03-19', 'CA Ha Noi');

INSERT INTO sqa.`option` (dtype, id, label, loan_type) VALUES ('Job', 1, 'Sinh vien', null);
INSERT INTO sqa.`option` (dtype, id, label, loan_type) VALUES ('LoanPurpose', 2, 'Chả để làm cái gì cả đâu', 'SECURED');
INSERT INTO sqa.`option` (dtype, id, label, loan_type) VALUES ('LoanPurpose', 3, 'Vay cho vui', 'SECURED');
INSERT INTO sqa.`option` (dtype, id, label, loan_type) VALUES ('LoanPurpose', 4, 'Test', 'UNSECURED');

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

INSERT INTO config_history (id, config_type, created_at, note, start_date, status, summary, updated_at, created_by_id)
VALUES  (1, 'SECURED_LOAN', '2024-03-26 03:44:42.894492', 'OK day', '2024-03-28', 'APPROVED', 'FROM ... WITH LOVE', '2024-03-26 03:49:37.808652', 1),
        (3, 'UNSECURED_LOAN', '2024-03-26 04:20:23.720004', 'OK day', '2024-03-28', 'APPROVED', 'FROM ... WITH LOVE', '2024-03-26 04:20:54.423336', 1),
        (4, 'SAVING', '2024-03-26 04:26:01.718125', 'OK day', '2024-04-02', 'APPROVED', '  ', '2024-03-26 04:26:26.937931', 1);

INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (1, 24, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (2, 1, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (3, 36, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (4, 18, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (5, 2, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (6, 6, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (7, 12, 0.06, 4);
INSERT INTO saving_config (id, term_in_month, yearly_interest_rate, config_history_id) VALUES (8, 3, 0.06, 4);

INSERT INTO loan_config (id, `limit`, yearly_interest_rate, config_history_id, purpose_id) VALUES (1, 1000000.00, 0, 1, 2);
INSERT INTO loan_config (id, `limit`, yearly_interest_rate, config_history_id, purpose_id) VALUES (2, 1000000.00, 0.06, 1, 3)
INSERT INTO loan_config (id, `limit`, yearly_interest_rate, config_history_id, purpose_id) VALUES (5, 1000000.00, 0, 3, 4);