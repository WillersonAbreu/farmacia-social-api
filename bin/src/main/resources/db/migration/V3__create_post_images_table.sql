CREATE TABLE post_images (
  id bigint PRIMARY KEY AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  image_name varchar(60)  NOT NULL,
  updated_at datetime(6) DEFAULT NULL,
  donation_id bigint DEFAULT NULL,
  FOREIGN KEY (donation_id) REFERENCES medicine_donations (id)
);

