CREATE TABLE roles_code (
  id bigint PRIMARY KEY AUTO_INCREMENT, 
  role varchar(20)  NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL
);

INSERT INTO roles_code (role) VALUES('usuario');
INSERT INTO roles_code (role) VALUES('farmacia');


