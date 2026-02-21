CREATE DATABASE IF NOT EXISTS bd1
DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE bd1;

DROP TABLE IF EXISTS contactos;

CREATE TABLE contactos (
    ide_con INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    nom_con VARCHAR(20),
    tlf_con INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO contactos (nom_con, tlf_con) VALUES
('Javier', 666666666),
('Ana', 777777777),
('Luís', 888888888),
('Javier', 999999999);
