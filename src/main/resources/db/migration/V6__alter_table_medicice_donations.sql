alter table medicine_donations add manufactury_date datetime(6) NOT NULL;
alter table medicine_donations add picture_file_back varchar(255) COLLATE utf8mb4_general_ci NOT NULL;
ALTER TABLE medicine_donations DROP composition;
