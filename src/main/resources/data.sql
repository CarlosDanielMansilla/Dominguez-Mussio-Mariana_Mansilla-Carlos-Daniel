INSERT INTO roles(id, NAME) VALUES(1, 'ROLE_ADMIN');
INSERT INTO roles(id, NAME) VALUES(2, 'ROLE_USER');

INSERT INTO users (id, email, PASSWORD, username) VALUES(1, 'admin@gmail.com', '$3a$20$gSB5MyJyJUfIiYRkKei1oOyBAH8SHvVr5DHmrcGRy4U7uSgTH97hRT4', 'admin');
INSERT INTO users (id, email, PASSWORD, username) VALUES(2, 'user@gmail.com', '$3a$20$gSB5MyJyJUfIiYRkKei1oOyBAH8SHvVr5DHmrcGRy4U7uSgTH97hRT4', 'user');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO user_roles (user_id, role_id) VALUES (2, 2);