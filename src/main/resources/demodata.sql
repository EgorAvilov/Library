INSERT INTO task.roles (id, role) VALUES (1, 'admin');
INSERT INTO task.roles (id, role) VALUES (2, 'user');
INSERT INTO task.users (id, first_name, email, date_of_registration, phone_number, last_name, gender, deleted_status, username, password, role_id) VALUES (1, 'Admin', 'egoravilov9@mail.ru', '2012-12-12', '+375 (044) 111-1111', 'Admin', 0, 0, 'admin123', 'admin123', 1);