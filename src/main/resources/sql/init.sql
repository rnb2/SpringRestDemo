DROP TABLE IF EXISTS employee;
DROP TABLE IF EXISTS company;

CREATE TABLE IF NOT EXISTS company
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS employee
(
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    email VARCHAR(255),
    company_id INT REFERENCES company (id)
)
