CREATE SCHEMA IF NOT EXISTS dietiestate25_offer;

set search_path to dietiestate25_offer;


CREATE TABLE IF NOT EXISTS dietiestate25_offer.offer(
    "id" SERIAL PRIMARY KEY,
    "price" REAL NOT NULL DEFAULT 0,
    "status" VARCHAR(255) NOT NULL,
    "user_email" VARCHAR(255) NOT NULL,
    "estate_id" INTEGER NOT NULL,
    "created_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),
    "updated_at" TIMESTAMP WITH TIME ZONE DEFAULT now(),

    CONSTRAINT positive_price CHECK (price > 0)
);

CREATE OR REPLACE FUNCTION update_offer_status()
RETURNS TRIGGER AS $$
BEGIN
    IF NEW.status = 'ACCEPTED' THEN
        UPDATE dietiestate25_offer.offer
        SET status = 'DECLINED'
        WHERE estate_id = NEW.estate_id
        AND id <> NEW.id;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE TRIGGER trigger_offer_status
AFTER UPDATE ON dietiestate25_offer.offer
FOR EACH ROW
WHEN (OLD.status IS DISTINCT FROM NEW.status)
EXECUTE FUNCTION update_offer_status();