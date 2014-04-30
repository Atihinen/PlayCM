# Tasks schema

# --- !Ups

CREATE SEQUENCE blogpost_id_seq;
CREATE TABLE blogpost (
    id integer NOT NULL DEFAULT nextval('blogpost_id_seq'),
    title varchar(255),
    content text
);

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    username varchar(60),
    password varchar(255),
    email varchar(60),
    biography text
);

INSERT INTO user (username, password, email, biography) VALUES (
'admin',
'W6ph5Mm5Pz8GgiULbPgzG37mj9g=',
'admin@admin.fi',
'ir admin'
);


# --- !Downs

DROP TABLE task;
DROP SEQUENCE blogpost_id_seq;

DROP TABLE user;
DROP SEQUENCE user_id_seq;