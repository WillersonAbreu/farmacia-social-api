CREATE TABLE pharmacies
(
  id bigint NOT NULL
  AUTO_INCREMENT,
  cnpj varchar
  (255) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime
  (6) DEFAULT NULL,
  fantasy_name varchar
  (255) COLLATE utf8mb4_general_ci NOT NULL,
    address varchar
  (100) COLLATE utf8mb4_general_ci NOT NULL,
  cep varchar
  (9) COLLATE utf8mb4_general_ci NOT NULL,
	region varchar 
	(6) COLLATE utf8mb4_general_ci NOT NULL,
	
  password varchar
  (255) COLLATE utf8mb4_general_ci NOT NULL,
  pharmaceutical varchar
  (255) COLLATE utf8mb4_general_ci NOT NULL,
  updated_at datetime
  (6) DEFAULT NULL,
  PRIMARY KEY
  (id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
