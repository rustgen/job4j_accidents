create TABLE IF NOT EXISTS accident_rule (
  id serial primary key,
  accident_id INT REFERENCES accident(id),
  rule_id INT REFERENCES rule(id)
);