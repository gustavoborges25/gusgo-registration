--V1__create-person.sql
CREATE TABLE person (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(255),
    nickname VARCHAR(255),
    is_custumer BOOLEAN,
    is_provider BOOLEAN,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);
CREATE TABLE individual_person (
    id UUID PRIMARY KEY REFERENCES person(id),
    document_cpf VARCHAR(40),
    document_rg VARCHAR(40)
);
CREATE TABLE business_person (
    id UUID PRIMARY KEY REFERENCES person(id),
    document_cnpj VARCHAR(40),
    document_ie VARCHAR(40)
);
CREATE TABLE address (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    person_id UUID REFERENCES person(id),
    street VARCHAR(255),
    number VARCHAR(40),
    zip_code VARCHAR(40),
    complement VARCHAR(255),
    state VARCHAR(255),
    city VARCHAR(255),
    created_at TIMESTAMP,
    updated_at TIMESTAMP
);