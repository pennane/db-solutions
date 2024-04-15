USE bigcompany;

ALTER TABLE employee DROP INDEX idx_last_name;
CREATE INDEX idx_last_name ON employee(LastName (8));