create sequence if not exists hibernate_sequence start 1 increment 1 ;
create table if not exists cat
(
    id   int4 not null,
    name varchar(255),
    date_of_birth date,
    primary key (id)
);
