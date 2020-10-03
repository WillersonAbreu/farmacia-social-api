CREATE TABLE reserved_donations (
  id bigint NOT NULL AUTO_INCREMENT,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  benefited_user_id bigint DEFAULT NULL,
  medicine_donation_id bigint DEFAULT NULL,
  PRIMARY KEY (id),
  KEY FKab90rpfcatyu9e9ru7vvnpmik (benefited_user_id),
  KEY FKn0f65dc2sh634a5atyo461tdt (medicine_donation_id),
  CONSTRAINT FKab90rpfcatyu9e9ru7vvnpmik FOREIGN KEY (benefited_user_id) REFERENCES users (id),
  CONSTRAINT FKn0f65dc2sh634a5atyo461tdt FOREIGN KEY (medicine_donation_id) REFERENCES medicine_donations (id)
) DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
