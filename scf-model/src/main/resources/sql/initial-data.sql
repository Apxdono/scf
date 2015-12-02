INSERT INTO security.security_descriptors (id, create_date, deleted, display_name, last_modified_date, priority, system_name, version)
VALUES (uuid_generate_v4(), now(), FALSE, 'Пользователь', now(), 100, 'ROLE_USER', 0);
INSERT INTO security.security_descriptors (id, create_date, deleted, display_name, last_modified_date, priority, system_name, version)
VALUES (uuid_generate_v4(), now(), FALSE, 'Администратор', now(), 100, 'ROLE_ADMIN', 0);
INSERT INTO security.users (id, create_date, deleted, username, last_modified_date, password, login, version)
VALUES (uuid_generate_v4(), now(), FALSE, 'Администратор', now(), 'admin', 'admin', 0);
INSERT INTO security.users_security_descriptors (object_id, security_descriptor_id)
  SELECT usr.id , sd.id FROM security.users usr INNER JOIN security.security_descriptors sd ON 1 = 1;