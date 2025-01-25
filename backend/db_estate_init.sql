CREATE SCHEMA IF NOT EXISTS dietiestate25_estate;

set search_path to dietiestate25_estate;

CREATE TABLE IF NOT EXISTS dietiestate25_estate.location(
    "id" VARCHAR(500) PRIMARY KEY,
    "address_line1" VARCHAR(255) NOT NULL,
    "address_line2" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now()
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate(
    "id" SERIAL PRIMARY KEY,
    "title" VARCHAR(255) NOT NULL,
    "category" VARCHAR(255) NOT NULL,
    "description" VARCHAR(1000) NOT null,
    "rental" BOOLEAN NOT NULL DEFAULT FALSE,
    "price" REAL NOT NULL,
    "mtq" INTEGER NOT NULL,
    "energy_class" VARCHAR(2) NOT NULL,
    "rooms" INTEGER NOT NULL DEFAULT 1,
    "services" INTEGER NOT NULL DEFAULT 1,
    "location_id" VARCHAR(500) NOT NULL,
    "user_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    
    CONSTRAINT positive_price CHECK (price > 0),
    CONSTRAINT positive_mtq CHECK (mtq > 0),
    CONSTRAINT positive_rooms CHECK (rooms > 0),
    CONSTRAINT positive_service CHECK (services > 0),

    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES dietiestate25_estate.location(id) ON DELETE CASCADE
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

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate_addons(
    "estate_id" INTEGER NOT NULL,
    "addons_id" INTEGER NOT NULL,
    
    PRIMARY KEY (estate_id, addons_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE,
    CONSTRAINT fk_addons_id FOREIGN KEY (addons_id) REFERENCES dietiestate25_estate.addons(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.file(
    "id" SERIAL PRIMARY KEY,
    "bucket" VARCHAR(500) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "size" REAL NOT NULL,
    "content_type" VARCHAR(10) NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);