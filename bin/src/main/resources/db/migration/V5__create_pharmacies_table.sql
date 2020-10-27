CREATE TABLE pharmacies
(
  id bigint NOT NULL
  AUTO_INCREMENT,
  cnpj varchar
  (255)  NOT NULL,
  created_at datetime
  (6) DEFAULT NULL,
  fantasy_name varchar
  (255)  NOT NULL,
  password varchar
  (255)  NOT NULL,
  pharmaceutical varchar
  (255)  NOT NULL,
  updated_at datetime
  (6) DEFAULT NULL,
  PRIMARY KEY
  (id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
