alter table medicine_donations add manufactury_date datetime(6) NOT NULL;
alter table medicine_donations add picture_file_back varchar(255)  NOT NULL;
ALTER TABLE medicine_donations DROP composition;
