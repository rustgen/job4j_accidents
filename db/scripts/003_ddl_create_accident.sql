create TABLE IF NOT EXISTS accident (
  id serial primary key,
  name varchar,
  text text,
  address text,
  type_id INT REFERENCES type(id)
);