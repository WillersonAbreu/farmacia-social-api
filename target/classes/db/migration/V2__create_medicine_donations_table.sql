CREATE TABLE medicine_donations (
  id bigint NOT NULL AUTO_INCREMENT,
  amount int NOT NULL,
  batch_code varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  composition varchar(60) COLLATE utf8mb4_general_ci NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  description varchar(140) COLLATE utf8mb4_general_ci NOT NULL,
  dosage varchar(15) COLLATE utf8mb4_general_ci NOT NULL,
  packing varchar(8) COLLATE utf8mb4_general_ci NOT NULL,
  picture_file varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
  shelf_life datetime(6) NOT NULL,
  stripe varchar(10) COLLATE utf8mb4_general_ci NOT NULL,
  title varchar(100) COLLATE utf8mb4_general_ci NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKofx5xpni3sby0rs5ra7itimyx (user_id),
  CONSTRAINT FKofx5xpni3sby0rs5ra7itimyx FOREIGN KEY (user_id) REFERENCES users (id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;