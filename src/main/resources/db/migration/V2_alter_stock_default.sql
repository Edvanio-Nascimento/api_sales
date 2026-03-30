ALTER TABLE tb_products
    ALTER COLUMN stock SET DEFAULT 0;

-- Caso queira garantir que os nulos atuais virem zero:
UPDATE tb_products SET stock = 0 WHERE stock IS NULL;
