alter table medicine_donations 
add pharmacy_id bigint NOT NULL;

alter table medicine_donations ADD CONSTRAINT fk_medicine_donation_pharmacy_id
FOREIGN KEY(pharmacy_id) REFERENCES pharmacies (id);