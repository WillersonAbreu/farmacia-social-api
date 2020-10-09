CREATE TABLE orders (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    medicine_donation_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    FOREIGN KEY (medicine_donation_id)
        REFERENCES medicine_donations (id),
    FOREIGN KEY (user_id)
        REFERENCES users (id)
);

