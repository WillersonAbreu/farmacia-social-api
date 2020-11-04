alter table medicine_donations add status_id bigint NOT NULL,
ADD CONSTRAINT fk_medicine_donation_status_id
FOREIGN KEY(status_id) REFERENCES status_code (id);

