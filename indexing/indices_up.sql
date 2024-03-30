USE bigcompany;

CREATE INDEX idx_last_name ON employee(LastName);
CREATE INDEX idx_phone_number ON employee(PhoneNumber);
CREATE INDEX idx_phone_number ON phonecall(PhoneNumber);