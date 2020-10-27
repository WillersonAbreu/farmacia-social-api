CREATE TABLE post_images (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  image_name varchar(60)  NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  donation_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKo8e2ra0d50hh9kp3d5kynt2y0 (donation_id),
  CONSTRAINT FKo8e2ra0d50hh9kp3d5kynt2y0 FOREIGN KEY (donation_id) REFERENCES medicine_donations (id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

