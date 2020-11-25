CREATE TABLE reserved_donations (
  id bigint PRIMARY KEY AUTO_INCREMENT ,
  created_at datetime(6) DEFAULT NULL,
  updated_at datetime(6) DEFAULT NULL,
  benefited_user_id bigint DEFAULT NULL,
  medicine_donation_id bigint DEFAULT NULL,

  FOREIGN KEY (benefited_user_id) REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
  FOREIGN KEY (medicine_donation_id) REFERENCES medicine_donations (id) ON UPDATE CASCADE ON DELETE CASCADE
);