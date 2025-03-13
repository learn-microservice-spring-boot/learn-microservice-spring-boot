CREATE TABLE currency (
    id SERIAL PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    base VARCHAR(10) NOT NULL,
    rates TEXT NOT NULL,
    fetch_date_at TIMESTAMP NOT NULL
);
