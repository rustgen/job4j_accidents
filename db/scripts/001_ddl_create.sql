create TABLE IF NOT EXISTS rule (
  id serial primary key,
  name varchar
);

create TABLE IF NOT EXISTS type (
  id serial primary key,
  name varchar
);

create TABLE IF NOT EXISTS accident (
  id serial primary key,
  name varchar,
  text text,
  address text,
  type_id INT REFERENCES type(id)
);

create TABLE IF NOT EXISTS accident_rule (
  id serial primary key,
  accident_id INT REFERENCES accident(id),
  rule_id INT REFERENCES rule(id)
);