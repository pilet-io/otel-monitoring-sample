DROP SCHEMA IF EXISTS sample CASCADE;
CREATE SCHEMA IF NOT EXISTS sample;

CREATE TABLE IF NOT EXISTS sample.people (
    id TEXT PRIMARY KEY,
    name TEXT,
    age INT,
    zip TEXT
);