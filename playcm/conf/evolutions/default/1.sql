# Tasks schema

# --- !Ups

CREATE SEQUENCE blogpost_id_seq;
CREATE TABLE blogpost (
    id integer NOT NULL DEFAULT nextval('blogpost_id_seq'),
    title varchar(255),
    content text
);

# --- !Downs

DROP TABLE task;
DROP SEQUENCE task_id_seq;