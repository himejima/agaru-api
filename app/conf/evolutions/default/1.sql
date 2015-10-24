# --- !Ups

create table music (
    id int NOT NULL AUTO_INCREMENT,
    url varchar(255),
    PRIMARY KEY (id)
);

# --- !Downs

drop table music;
