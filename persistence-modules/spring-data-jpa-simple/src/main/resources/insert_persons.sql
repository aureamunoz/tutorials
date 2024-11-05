INSERT INTO Country (id, name, isocode)
VALUES
    (1, 'United States', 'US'),
    (2, 'France', 'FR'),
    (3, 'Germany', 'DE'),
    (4, 'Spain', 'ES');

-- Insert sample data for Address
INSERT INTO Address (id, zipcode, country_id)
VALUES
    (1, '10001', 1),   -- US address
    (2, '75001', 2),   -- French address
    (3, '10115', 3),   -- German address
    (4, '28004', 4);   -- Spain address

-- Insert sample data for Person
INSERT INTO Person (id, name, address_id)
VALUES
    (1, 'Alice Johnson', 1),
    (2, 'Bob Smith', 2),
    (3, 'Catherine Lee', 3),
    (4, 'Yago Steiner', 4);

