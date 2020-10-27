CREATE TABLE medicine_donations (
  id bigint NOT NULL AUTO_INCREMENT,
  amount int NOT NULL,
  batch_code varchar(20)  NOT NULL,
  composition varchar(60)  NOT NULL,
  created_at datetime(6) DEFAULT NULL,
  description varchar(140)  NOT NULL,
  dosage varchar(15)  NOT NULL,
  packing varchar(8)  NOT NULL,
  picture_file varchar(255)  NOT NULL,
  shelf_life datetime(6) NOT NULL,
  stripe varchar(10)  NOT NULL,
  title varchar(100)  NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  user_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKofx5xpni3sby0rs5ra7itimyx (user_id),
  CONSTRAINT FKofx5xpni3sby0rs5ra7itimyx FOREIGN KEY (user_id) REFERENCES users (id)
) CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;