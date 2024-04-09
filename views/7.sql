USE bigcompany;

DROP VIEW IF EXISTS aggregate_view;

CREATE VIEW aggregate_view AS
SELECT COUNT(p.CallId) as count,
  AVG(p.price) as avg,
  MIN(p.price) as min,
  MAX(p.price) as max
FROM Phonecall p;

SELECT *
FROM aggregate_view;