DROP TABLE IF EXISTS USER;
DROP TABLE IF EXISTS USER_TYPE;
DROP TABLE IF EXISTS ITEM;
DROP TABLE IF EXISTS ITEM_TYPE;

CREATE TABLE USER_TYPE (
                           id INT AUTO_INCREMENT  PRIMARY KEY,
                           type VARCHAR(250) NOT NULL
);

CREATE TABLE USER (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      name VARCHAR(250) NOT NULL,
                      type_id int DEFAULT NOT NULL,
                      joining_date date NOT NULL,
                      FOREIGN KEY(type_id) REFERENCES USER_TYPE(id)
);

CREATE TABLE ITEM_TYPE (
                           id INT AUTO_INCREMENT  PRIMARY KEY,
                           type VARCHAR(250) NOT NULL
);

CREATE TABLE ITEM (
                      id INT AUTO_INCREMENT  PRIMARY KEY,
                      name VARCHAR(250) NOT NULL,
                      price double NOT NULL,
                      type_id int NOT NULL,
                      FOREIGN KEY(type_id) REFERENCES ITEM_TYPE(id)
);

INSERT INTO USER_TYPE (type) VALUES
  ('employee'),
  ('affiliate'),
  ('customer');

INSERT INTO USER (name, type_id, joining_date) VALUES
('salah', 1, parsedatetime('10-09-2015', 'dd-MM-yyyy')),
('izzat', 2, parsedatetime('10-09-2015', 'dd-MM-yyyy')),
('ahmad', 3, parsedatetime('10-09-2015', 'dd-MM-yyyy')),
('qasem', 3, parsedatetime('10-09-2019', 'dd-MM-yyyy'));

INSERT INTO ITEM_TYPE (type) VALUES
('grocery'),
('other');

INSERT INTO ITEM (name, price, type_id) VALUES
('orange', 80, 1),
('appale', 70, 1),
('other_1', 100, 2),
('other_2', 120, 2);