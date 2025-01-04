CREATE SCHEMA IF NOT EXISTS dietiestate25_estate;

set search_path to dietiestate25_estate;

DO $$ BEGIN
	CREATE TYPE dietiestate25_estate.energy_class AS ENUM ('A3', 'A2', 'A1', 'A','B','C','D','E', 'F');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate(
    "id" SERIAL PRIMARY KEY,
    "title" VARCHAR(255) NOT NULL,
    "description" VARCHAR(1000) NOT null,
    "rental" BOOLEAN NOT NULL DEFAULT FALSE,
    "price" REAL NOT NULL,
    "mtq" INTEGER NOT NULL,
    "energy_class" energy_class NOT NULL,
    "rooms" INTEGER NOT NULL DEFAULT 1,
    "services" INTEGER NOT NULL DEFAULT 1,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    CONSTRAINT positive_price CHECK (price > 0),
    CONSTRAINT positive_mtq CHECK (mtq > 0),
    CONSTRAINT positive_rooms CHECK (rooms > 0),
    CONSTRAINT positive_service CHECK (services > 0)
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.addons(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate_addons(
    "estate_id" INTEGER NOT NULL,
    "addons_id" INTEGER NOT NULL,
    
    PRIMARY KEY (estate_id, addons_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE,
    CONSTRAINT fk_addons_id FOREIGN KEY (addons_id) REFERENCES dietiestate25_estate.addons(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.file(
    "id" SERIAL PRIMARY KEY,
    "url" VARCHAR(500) NOT NULL UNIQUE,
    "name" VARCHAR(255) NOT NULL,
    "extension" VARCHAR(10) NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);CREATE SCHEMA IF NOT EXISTS dietiestate25_estate;

set search_path to dietiestate25_estate;

DO $$ BEGIN
	CREATE TYPE dietiestate25_estate.energy_class AS ENUM ('A3', 'A2', 'A1', 'A','B','C','D','E', 'F');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate(
    "id" SERIAL PRIMARY KEY,
    "title" VARCHAR(255) NOT NULL,
    "description" VARCHAR(1000) NOT null,
    "rental" BOOLEAN NOT NULL DEFAULT FALSE,
    "price" REAL NOT NULL,
    "mtq" INTEGER NOT NULL,
    "energy_class" energy_class NOT NULL,
    "rooms" INTEGER NOT NULL DEFAULT 1,
    "services" INTEGER NOT NULL DEFAULT 1,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    CONSTRAINT positive_price CHECK (price > 0),
    CONSTRAINT positive_mtq CHECK (mtq > 0),
    CONSTRAINT positive_rooms CHECK (rooms > 0),
    CONSTRAINT positive_service CHECK (services > 0)
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.favorite_estate(
    "estate_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,

    PRIMARY KEY (estate_id, user_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.addons(
    "id" SERIAL PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate_addon(
    "estate_id" INTEGER NOT NULL,
    "addons_id" INTEGER NOT NULL,
    
    PRIMARY KEY (estate_id, addons_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE,
    CONSTRAINT fk_addons_id FOREIGN KEY (addons_id) REFERENCES dietiestate25_estate.addons(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.file(
    "id" SERIAL PRIMARY KEY,
    "url" VARCHAR(500) NOT NULL UNIQUE,
    "name" VARCHAR(255) NOT NULL,
    "extension" VARCHAR(10) NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.location(
    "id" SERIAL PRIMARY KEY,
    "latitude" VARCHAR(255) NOT NULL,
    "longitude" VARCHAR(255) NOT NULL,
    "region" VARCHAR(255) NOT NULL,
    "city" VARCHAR(255) NOT NULL,
    "cap" VARCHAR(255) NOT NULL,
    "address" VARCHAR(255) NOT NULL,
    "nearSchool" BOOLEAN NOT NULL DEFAULT FALSE,
    "nearPark" BOOLEAN NOT NULL DEFAULT FALSE,
    "nearTransport" BOOLEAN NOT NULL DEFAULT FALSE,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT unique_address UNIQUE(region, city, cap, address),
    CONSTRAINT unique_location UNIQUE(latitude, longitude)
);