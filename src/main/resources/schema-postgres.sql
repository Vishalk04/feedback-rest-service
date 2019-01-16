DROP TABLE IF EXISTS feedback_schema.feedback;
DROP SCHEMA IF EXISTS feedback_schema;
CREATE SCHEMA feedback_schema AUTHORIZATION root;
CREATE TABLE feedback_schema.feedback(id serial, source varchar(255), rating integer, comment varchar(255), username varchar(255), created_time date,  PRIMARY KEY (id));