drop table if exists cat cascade;
drop sequence if exists hibernate_sequence;
create sequence hibernate_sequence start 1 increment 1;
create table cat
(
    id   int4 not null,
    name varchar(255),
    primary key (id)
);
