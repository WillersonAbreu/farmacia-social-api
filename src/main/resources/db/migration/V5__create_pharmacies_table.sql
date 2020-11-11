CREATE TABLE pharmacies (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    cnpj VARCHAR(255) NOT NULL,
    created_at DATETIME(6) DEFAULT NULL,
    fantasy_name VARCHAR(255) NOT NULL,
    address VARCHAR(100) NOT NULL,
    cep VARCHAR(9) NOT NULL,
    region VARCHAR(60) NOT NULL,
    password VARCHAR(255) NOT NULL,
    pharmaceutical VARCHAR(255) NOT NULL,
    updated_at DATETIME(6) DEFAULT NULL
);
