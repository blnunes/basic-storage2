CREATE SCHEMA IF NOT EXISTS STORAGE;

CREATE TABLE IF NOT EXISTS STORAGE.PRODUCT (
                                               id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               name VARCHAR(255),
                                               quantity INT
);

INSERT INTO STORAGE.PRODUCT (id, name, quantity) VALUES (1, 'Item 1', 10);
INSERT INTO STORAGE.PRODUCT (id, name, quantity) VALUES (2, 'Item 2', 20);
INSERT INTO STORAGE.PRODUCT (id, name, quantity) VALUES (3, 'Item 3', 30);