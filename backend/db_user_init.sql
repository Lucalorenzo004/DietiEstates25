CREATE SCHEMA IF NOT EXISTS dietiestate25_user;

set search_path to dietiestate25_user;


CREATE TABLE IF NOT EXISTS dietiestate25_user.role(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL UNIQUE,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS dietiestate25_user.permission(
    "id" SERIAL PRIMARY KEY,
    "action" VARCHAR(255) NOT NULL UNIQUE,
    "description" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS dietiestate25_user.rolo_permission(
    "role_id" INTEGER NOT NULL,
    "permission_id" INTEGER NOT NULL,
    
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES dietiestate25_user.role(id) ON DELETE CASCADE,
    CONSTRAINT fk_permission_id FOREIGN KEY (permission_id) REFERENCES dietiestate25_user.permission(id) ON DELETE CASCADE
);

DO $$ BEGIN
    CREATE TYPE dietiestate25_user.provider AS ENUM ('Google', 'Local');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS dietiestate25_user.user(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "surname" VARCHAR(255) NOT NULL,
    "email" VARCHAR(255) NOT NULL UNIQUE,
    "password" VARCHAR(255) NOT NULL,
    "provider" provider NOT NULL,
    "role_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT fk_role_id FOREIGN KEY (role_id) REFERENCES dietiestate25_user.role(id) ON DELETE CASCADE
);
