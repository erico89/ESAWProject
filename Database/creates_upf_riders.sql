DROP SCHEMA IF EXISTS upf_riders;
CREATE DATABASE IF NOT EXISTS upf_riders;
USE upf_riders;

DROP TABLE IF EXISTS headquarters;
CREATE TABLE IF NOT EXISTS headquarters (
code integer PRIMARY KEY,
latitude float(5), -- "For float(M,D) or double(M,D) or decimal(M,D) M must be >= D (column 'latitude')"
longitude float(5),
postal_address varchar(100)
);

DROP TABLE IF EXISTS rider;
CREATE TABLE IF NOT EXISTS rider (
rider_id integer PRIMARY KEY,
name varchar (32),
headquarters_code integer,
FOREIGN KEY (headquarters_code) REFERENCES headquarters (code)
);

DROP TABLE IF EXISTS vehicle_type;
CREATE TABLE IF NOT EXISTS vehicle_type (
type_id integer PRIMARY KEY,
description varchar(20),
number_of_wheels integer
);

DROP TABLE IF EXISTS vehicle;
CREATE TABLE IF NOT EXISTS vehicle (
 ref_number integer PRIMARY KEY,
 brand varchar(18),
 model varchar(20),
 liter_capacity integer,
 vehicle_type_id integer,
 FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type (type_id)
);

DROP TABLE IF EXISTS licensed;
CREATE TABLE IF NOT EXISTS licensed(
 rider_id integer, 
 vehicle_type_id integer,
 years_experience integer,
 PRIMARY KEY (rider_id, vehicle_type_id),
 FOREIGN KEY (rider_id) REFERENCES rider (rider_id), 
 FOREIGN KEY (vehicle_type_id) REFERENCES vehicle_type (type_id)
);

DROP TABLE IF EXISTS uses;
CREATE TABLE IF NOT EXISTS uses(
 vehicle_ref_number integer,
 headquarters_code integer,
 PRIMARY KEY (vehicle_ref_number, headquarters_code),
 FOREIGN KEY (vehicle_ref_number) REFERENCES vehicle (ref_number), 
 FOREIGN KEY (headquarters_code) REFERENCES headquarters (code)
);

DROP TABLE IF EXISTS category;
CREATE TABLE IF NOT EXISTS category (
category_id integer PRIMARY KEY,
description varchar(30),
parent_category_id integer,
FOREIGN KEY (parent_category_id) REFERENCES category (category_id) ON DELETE SET NULL
-- Usamos on delete set null para no obtener errores a la hora de eliminar las tuplas en inserts_upf_riders
); 

DROP TABLE IF EXISTS business;
CREATE TABLE IF NOT EXISTS business (
id integer PRIMARY KEY,
name varchar(30),
latitude float(5),
longitude float(5),
address varchar(70),
category_id integer,
FOREIGN KEY (category_id) REFERENCES category (category_id)
);

DROP TABLE IF EXISTS serve;
CREATE TABLE IF NOT EXISTS serve (
business_id integer,
headquarters_code integer,
distance float(6),
PRIMARY KEY (business_id, headquarters_code),
FOREIGN KEY (business_id) REFERENCES business (id),
FOREIGN KEY (headquarters_code) REFERENCES headquarters (code)
);