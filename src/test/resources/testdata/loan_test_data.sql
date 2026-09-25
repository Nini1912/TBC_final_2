CREATE TABLE loan_test_data (
                                id INT IDENTITY(1,1) PRIMARY KEY,
                                description VARCHAR(255) NOT NULL,
                                loan_amount FLOAT NOT NULL,
                                loan_term_months INT NOT NULL
);

INSERT INTO loan_test_data (description, loan_amount, loan_term_months) VALUES
                                                                            ('Valid loan amount and term', 10000, 24),
                                                                            ('Invalid loan amount and term - below minimum', 150, 2);