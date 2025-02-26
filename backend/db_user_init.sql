CREATE SCHEMA IF NOT EXISTS dietiestate25_user;

set search_path to dietiestate25_user;

CREATE TABLE IF NOT EXISTS dietiestate25_user.users(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "surname" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL UNIQUE,
    "password" VARCHAR(255),
    "provider" VARCHAR(255) NOT NULL,
    "role"  VARCHAR(255) NOT NULL,
    "agency" VARCHAR(255), 
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now()
);
