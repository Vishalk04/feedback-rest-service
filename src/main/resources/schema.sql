DROP TABLE IF EXISTS feedback;
CREATE TABLE feedback(id serial, source varchar(255), rating integer, comment varchar(255), username varchar(255), created_time date, PRIMARY KEY (id));
