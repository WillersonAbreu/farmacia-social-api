CREATE TABLE users
(
  id bigint NOT NULL
  AUTO_INCREMENT,
  address varchar
  (100)  NOT NULL,
  cep varchar
  (9)  NOT NULL,
  cpf varchar
  (14)  NOT NULL,
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
  (6) DEFAULT NULL,
  PRIMARY KEY
  (id),
  UNIQUE KEY UK_7kqluf7wl0oxs7n90fpya03ss
  (cpf)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
