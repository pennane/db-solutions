USE bigcompany;

ALTER TABLE employee DROP INDEX idx_last_name;
ALTER TABLE employee DROP INDEX idx_phone_number;
ALTER TABLE phonecall DROP INDEX idx_phone_number;