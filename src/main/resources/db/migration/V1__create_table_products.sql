CREATE TABLE tb_products (
    id BINARY(16) NOT NULL,
    sku VARCHAR(30) NOT NULL ,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL ,
    price DECIMAL(19, 2) NOT NULL,
    stock INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,

    PRIMARY KEY (id),
    UNIQUE INDEX uk_sku (sku)
) ENGINE=InnoDB;