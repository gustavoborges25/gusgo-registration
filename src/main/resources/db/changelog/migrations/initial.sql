--liquibase formatted sql

--changeset gustavocamargo:1 dbms:postgresql
--comment: create table users
CREATE TABLE tdc.users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name TEXT,
    email TEXT
);