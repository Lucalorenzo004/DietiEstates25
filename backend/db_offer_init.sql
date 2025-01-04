CREATE SCHEMA IF NOT EXISTS dietiestate25_offer;

set search_path to dietiestate25_offer;

DO $$ BEGIN
    CREATE TYPE dietiestate25_offer.status AS ENUM ('Attesa', 'Accettato', 'Rifiutato');
EXCEPTION
    WHEN duplicate_object THEN null;
END $$;

CREATE TABLE IF NOT EXISTS dietiestate25_offer.offer(
    "id" SERIAL PRIMARY KEY,
    "price" REAL NOT NULL DEFAULT 0,
    "status" VARCHAR(255) NOT NULL,
    "user_id" INTEGER NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT positive_price CHECK (price > 0)
);