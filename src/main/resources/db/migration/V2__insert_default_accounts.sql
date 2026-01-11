INSERT INTO users (username, password, enabled)
VALUES ('admin', '{bcrypt}$2a$12$dKoeX.ntXEMDJI87nCxIOueKMTy7kO5/211FdvWHbuugpZyA/cQVu', true);

INSERT INTO users (username, password, enabled)
VALUES ('manager', '{bcrypt}$2a$12$dKoeX.ntXEMDJI87nCxIOueKMTy7kO5/211FdvWHbuugpZyA/cQVu', true);

INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_ADMIN');

INSERT INTO authorities (username, authority)
VALUES ('admin', 'ROLE_MANAGER');

INSERT INTO authorities (username, authority)
VALUES ('manager', 'ROLE_MANAGER');