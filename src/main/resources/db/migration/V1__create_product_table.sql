
CREATE TABLE tb_products (

    id BINARY(16) NOT NULL,
    sku VARCHAR(30) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description TEXT NOT NULL,
    price DECIMAL(19, 2) NOT NULL,

    CONSTRAINT pk_products PRIMARY KEY (id),
    CONSTRAINT pk_sku UNIQUE (sku),
    CONSTRAINT chk_products_price CHECK ( price > 0 )

);