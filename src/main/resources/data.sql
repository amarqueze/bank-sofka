INSERT INTO Clients (dni, name, last_name, address, phone) VALUES ('1047488553', 'Alan', 'Marquez', 'Calle 123', '1234567890');
INSERT INTO Clients (dni, name, last_name, address, phone) VALUES ('1047488555', 'Jhon', 'Doe', 'Avenida 456', '0987654321');

INSERT INTO Users (nickname, password, client_id) VALUES ('alan.marquez', 'password1', 1);
INSERT INTO Users (nickname, password, client_id) VALUES ('joe.doe', 'password2', 2);

INSERT INTO Accounts (account_number, balance, status, client_id) VALUES ('123456789', 1000.00, 'ACTIVE', 1);
INSERT INTO Accounts (account_number, balance, status, client_id) VALUES ('849382918', 0, 'INACTIVE', 1);
INSERT INTO Accounts (account_number, balance, status, client_id) VALUES ('478392817', 200.00, 'ACTIVE', 1);
INSERT INTO Accounts (account_number, balance, status, client_id) VALUES ('987654321', 500.00, 'ACTIVE', 2);
