--V1__create-person.sql
CREATE TABLE kafka_log (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    consumer VARCHAR(255),
    message VARCHAR(4000),
    exception VARCHAR(4000),
    created_at TIMESTAMP not null,
    updated_at TIMESTAMP not null
);