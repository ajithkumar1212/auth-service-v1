INSERT INTO permission (name) VALUES ('READ');
INSERT INTO permission (name) VALUES ('WRITE');

INSERT INTO role (name) VALUES ('USER');
INSERT INTO role (name) VALUES ('ADMIN');

INSERT INTO role_permission (role_id, permission_id) SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'USER' AND p.name = 'READ';
INSERT INTO role_permission (role_id, permission_id) SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'ADMIN' AND p.name = 'READ';
INSERT INTO role_permission (role_id, permission_id) SELECT r.id, p.id FROM role r, permission p WHERE r.name = 'ADMIN' AND p.name = 'WRITE';