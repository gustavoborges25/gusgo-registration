--V1__create-person.sql
CREATE TABLE seller (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255) not null,
    erp_id VARCHAR(255) not null unique,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);
CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    seller_id UUID REFERENCES seller(id),
    name VARCHAR(255) not null,
    nickname VARCHAR(255),
    erp_id VARCHAR(255) not null unique,
    is_customer BOOLEAN,
    is_provider BOOLEAN,
    status VARCHAR(40) not null,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);
CREATE TABLE natural_person (
    id UUID PRIMARY KEY REFERENCES person(id),
    document_cpf VARCHAR(255) not null unique,
    document_rg VARCHAR(255)
);
CREATE TABLE juridical_person (
    id UUID PRIMARY KEY REFERENCES person(id),
    document_cnpj VARCHAR(255) not null,
    is_branch BOOLEAN,
    document_ie VARCHAR(255)
);
CREATE TABLE address (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id UUID REFERENCES person(id),
    street VARCHAR(255) not null,
    number VARCHAR(255),
    neighborhood VARCHAR(255),
    zip_code VARCHAR(40),
    complement VARCHAR(255),
    state VARCHAR(255) not null,
    city VARCHAR(255) not null,
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);
CREATE TABLE phone (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id UUID REFERENCES person(id),
    phone VARCHAR(255) not null,
    contact VARCHAR(255),
    note VARCHAR(255),
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);
CREATE TABLE email (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id UUID REFERENCES person(id),
    email VARCHAR(255) not null,
    contact VARCHAR(255),
    note VARCHAR(255),
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);