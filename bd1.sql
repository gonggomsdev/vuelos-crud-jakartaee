DROP DATABASE IF EXISTS sistema_vuelos;
CREATE DATABASE sistema_vuelos;
USE sistema_vuelos;

CREATE TABLE companias (
                           id_compania INT PRIMARY KEY AUTO_INCREMENT,
                           nombre VARCHAR(100) NOT NULL
);

CREATE TABLE aviones (
                         id_avion INT PRIMARY KEY AUTO_INCREMENT,
                         modelo VARCHAR(50),
                         id_compania INT,
                         FOREIGN KEY (id_compania) REFERENCES companias(id_compania)
);

CREATE TABLE aeropuertos (
                             id_aeropuerto INT PRIMARY KEY AUTO_INCREMENT,
                             nombre VARCHAR(100),
                             ciudad VARCHAR(100)
);

CREATE TABLE terminales (
                            id_terminal INT PRIMARY KEY AUTO_INCREMENT,
                            nombre VARCHAR(50),
                            id_aeropuerto INT,
                            FOREIGN KEY (id_aeropuerto) REFERENCES aeropuertos(id_aeropuerto)
);

CREATE TABLE puertas (
                         id_puerta INT PRIMARY KEY AUTO_INCREMENT,
                         codigo VARCHAR(10),
                         id_terminal INT,
                         FOREIGN KEY (id_terminal) REFERENCES terminales(id_terminal)
);

CREATE TABLE vuelos (
                        id_vuelo INT PRIMARY KEY AUTO_INCREMENT,
                        numero_vuelo VARCHAR(20),
                        id_avion INT,
                        id_puerta_origen INT,
                        id_puerta_destino INT,
                        fecha_salida DATETIME,
                        FOREIGN KEY (id_avion) REFERENCES aviones(id_avion),
                        FOREIGN KEY (id_puerta_origen) REFERENCES puertas(id_puerta),
                        FOREIGN KEY (id_puerta_destino) REFERENCES puertas(id_puerta)
);
INSERT INTO companias (nombre)
VALUES
    ('Iberia'),
    ('Ryanair'),
    ('Vueling');

INSERT INTO aviones (modelo, id_compania)
VALUES
    ('Airbus A320', 1),
    ('Boeing 737', 2),
    ('Airbus A350', 1),
    ('Boeing 737 MAX', 2),
    ('Airbus A321neo', 3);

INSERT INTO aeropuertos (nombre, ciudad)
VALUES
    ('Jimmy Neutron', 'Madrid'),
    ('El Prat', 'Barcelona'),
    ('Narita International', 'Japon'),
    ('Charles de Gaulle', 'Paris'),
    ('Fiumicino', 'Roma');

INSERT INTO terminales (nombre, id_aeropuerto)
VALUES
    ('T4', 1),
    ('T1', 2),
    ('Terminal 1', 3),
    ('Terminal 2E', 4),
    ('T3', 5);

INSERT INTO puertas (codigo, id_terminal)
VALUES
    ('J52', 1),
    ('A12', 2),
    ('G10', 3),
    ('F22', 4),
    ('H01', 5);

INSERT INTO vuelos (
    numero_vuelo,
    id_avion,
    id_puerta_origen,
    id_puerta_destino,
    fecha_salida
)
VALUES
    ('IB3210', 1, 1, 2, '2024-10-25 12:00:00'),
    ('IB4455', 3, 1, 3, '2024-10-26 10:00:00'),
    ('RY8899', 2, 2, 4, '2024-10-26 15:30:00'),
    ('VY1010', 5, 2, 1, '2024-10-27 08:00:00'),
    ('VY2020', 5, 1, 5, '2024-10-27 22:15:00'),
    ('IB9900', 3, 3, 1, '2024-10-28 11:45:00'),
    ('RY1111', 4, 4, 2, '2024-10-29 07:00:00'),
    ('VY3333', 5, 5, 2, '2024-10-29 14:00:00'),
    ('IB7777', 1, 1, 4, '2024-10-30 19:20:00'),
    ('RY5555', 2, 2, 5, '2024-10-30 21:00:00');