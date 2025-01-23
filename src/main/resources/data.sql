CREATE TABLE employees (
	id SERIAL PRIMARY KEY,
	first_name VARCHAR(255) NOT NULL,
	last_name VARCHAR(255) NOT NULL,
	department VARCHAR(3),
	birthday VARCHAR(12) NOT NULL
);

CREATE TABLE salaries (
	id SERIAL PRIMARY KEY,
	employee_id INT NOT NULL,
	amount FLOAT NOT NULL,
	currency VARCHAR(3) DEFAULT 'PLN',
	UNIQUE(employee_id),
	FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE departments (
	id SERIAL PRIMARY KEY,
	abbreviation VARCHAR(3) NOT NULL,
	name VARCHAR(255) NOT NULL,
	UNIQUE(abbreviation)
);

CREATE TABLE date_stamps (
	id SERIAL PRIMARY KEY,
	employee_id INT NOT NULL,
	employment_day VARCHAR(12) NOT NULL,
	payment_date VARCHAR(12) NOT NULL,
	UNIQUE(employee_id),
	FOREIGN KEY (employee_id) REFERENCES employees (id) ON DELETE CASCADE
);

CREATE TABLE users (
	username VARCHAR(50) NOT NULL,
	password CHAR(68) NOT NULL,
	enabled SMALLINT NOT NULL,
	PRIMARY KEY (username)
);

CREATE TABLE authorities (
	username VARCHAR(50) NOT NULL,
	authority VARCHAR(50) NOT NULL,
	UNIQUE(username, authority),
	FOREIGN KEY (username) REFERENCES users (username) ON DELETE CASCADE
);

