# Tasks schema

# --- !Ups

CREATE SEQUENCE blogpost_id_seq;
CREATE TABLE blogpost (
    id integer NOT NULL DEFAULT nextval('blogpost_id_seq'),
    title varchar(255),
    content text,
    created_at timestamp,
    updated_at timestamp,
    username varchar(60)
);

CREATE SEQUENCE user_id_seq;
CREATE TABLE user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    username varchar(60),
    password varchar(255),
    email varchar(60),
    biography text
);

ALTER TABLE blogpost ADD CONSTRAINT fk_user_id FOREIGN KEY (username) REFERENCES user (id) ON DELETE RESTRICT ON UPDATE RESTRICT;

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