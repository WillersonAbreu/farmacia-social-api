CREATE TABLE users
(
  id bigint NOT NULL
  AUTO_INCREMENT,
  address varchar
  (100) COLLATE utf8mb4_general_ci NOT NULL,
  cep varchar
  (9) COLLATE utf8mb4_general_ci NOT NULL,
  cpf varchar
  (14) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime
  (6) DEFAULT NULL,
  email varchar
  (80) COLLATE utf8mb4_general_ci NOT NULL,
  name varchar
  (150) COLLATE utf8mb4_general_ci NOT NULL,
  password varchar
  (255) COLLATE utf8mb4_general_ci NOT NULL,
  phone varchar
  (15) COLLATE utf8mb4_general_ci NOT NULL,
  updated_at datetime
  (6) DEFAULT NULL,
  PRIMARY KEY
  (id),
  UNIQUE KEY UK_7kqluf7wl0oxs7n90fpya03ss
  (cpf)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
