CREATE SCHEMA IF NOT EXISTS dietiestate25_estate;

set search_path to dietiestate25_estate;

CREATE TABLE IF NOT EXISTS dietiestate25_estate.location(
    "id" SERIAL PRIMARY KEY,
    "county_code" VARCHAR(2) NOT NULL,
    "county" VARCHAR(255) NOT NULL,
    "city" VARCHAR(255) NOT NULL,
    "postal_code" VARCHAR(5) NOT NULL,
    "street" VARCHAR(255) NOT NULL,
    "lat" DECIMAL(9,6) NOT NULL,
    "lng" DECIMAL(9,6) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.poi(
    "id" SERIAL PRIMARY KEY,
    "lat" DECIMAL(9,6) NOT NULL,
    "lng" DECIMAL(9,6) NOT NULL,
    "category"  VARCHAR(100) NOT NULL,
    "location_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES dietiestate25_estate.location(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate(
    "id" SERIAL PRIMARY KEY,
    "title" VARCHAR(255) NOT NULL,
    "category_id" VARCHAR(255) NOT NULL,
    "description" VARCHAR(1000) NOT null,
    "rental" BOOLEAN NOT NULL DEFAULT FALSE,
    "price" REAL NOT NULL,
    "mtq" INTEGER NOT NULL,
    "energy_class" VARCHAR(2) NOT NULL,
    "rooms" INTEGER NOT NULL DEFAULT 1,
    "services" INTEGER NOT NULL DEFAULT 1,
    "location_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    
    CONSTRAINT positive_price CHECK (price > 0),
    CONSTRAINT positive_mtq CHECK (mtq > 0),
    CONSTRAINT positive_rooms CHECK (rooms > 0),
    CONSTRAINT positive_service CHECK (services > 0),

    CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES dietiestate25_estate.category(name) ON DELETE CASCADE,
    CONSTRAINT fk_location_id FOREIGN KEY (location_id) REFERENCES dietiestate25_estate.location(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.category(
    "name" VARCHAR(255) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.favorite_estate(
    "estate_id" INTEGER NOT NULL,
    "user_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (estate_id, user_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.addon(
    "name" VARCHAR(255) NOT NULL PRIMARY KEY
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.estate_addon(
    "estate_id" INTEGER NOT NULL,
    "addon_id" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY (estate_id, addon_id),
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE,
    CONSTRAINT fk_addon_id FOREIGN KEY (addon_id) REFERENCES dietiestate25_estate.addon(name) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS dietiestate25_estate.file(
    "id" SERIAL PRIMARY KEY,
    "url" VARCHAR(500) NOT NULL,
    "bucket" VARCHAR(500) NOT NULL,
    "name" VARCHAR(255) NOT NULL,
    "size" REAL NOT NULL,
    "content_type" VARCHAR(10) NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_estate_id FOREIGN KEY (estate_id) REFERENCES dietiestate25_estate.estate(id) ON DELETE CASCADE
);

CREATE OR REPLACE FUNCTION update_updated_at()
RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER update_location_updated_at
BEFORE UPDATE ON dietiestate25_estate.location
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_poi_updated_at
BEFORE UPDATE ON dietiestate25_estate.poi
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_estate_updated_at
BEFORE UPDATE ON dietiestate25_estate.estate
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_favorite_estate_updated_at
BEFORE UPDATE ON dietiestate25_estate.favorite_estate
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_addon_updated_at
BEFORE UPDATE ON dietiestate25_estate.addon
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_estate_addon_updated_at
BEFORE UPDATE ON dietiestate25_estate.estate_addon
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();

CREATE OR REPLACE TRIGGER update_file_updated_at
BEFORE UPDATE ON dietiestate25_estate.file
FOR EACH ROW
EXECUTE FUNCTION update_updated_at();