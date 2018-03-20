CREATE TABLE userlog (
    utcdate text,
    address text,
    ts timestamp,
    name text,
    PRIMARY KEY (utcdate,ts)
)