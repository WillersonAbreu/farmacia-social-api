CREATE TABLE users
(
  id bigint primary key
  AUTO_INCREMENT,
  address varchar
  (100)  NOT NULL,
  cep varchar
  (9) NOT NULL,
  cpf varchar
  (14)  NOT NULL unique,
  created_at datetime
  (6) DEFAULT NULL,
  email varchar
  (80)  NOT NULL,
  name varchar
  (150)  NOT NULL,
  password varchar
  (255)  NOT NULL,
  phone varchar
  (15)  NOT NULL,
  updated_at datetime
  (6) DEFAULT NULL
);

INSERT INTO users (address, cep, cpf, email, name, password, phone) VALUES
('rua x', '04904000','00000000000', 'admin@teste.com', 'Admin Teste', '$2y$12$sSLCsH9Q.irGZhYl2zaQweO5wIhlExwiqZyerdmMSVzSRr.h0.zzS', '11900000000');
