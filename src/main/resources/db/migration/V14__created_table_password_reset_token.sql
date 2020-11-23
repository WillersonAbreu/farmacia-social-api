CREATE TABLE password_reset_token (
 	id INT(11) PRIMARY KEY AUTO_INCREMENT,
 	token VARCHAR(191) NOT NULL,
 	data_expiracao TIMESTAMP NULL,
 	user_id bigint,
 	deleted_at TIMESTAMP NULL,
 	created_at TIMESTAMP NULL DEFAULT NOW(),
 	FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);