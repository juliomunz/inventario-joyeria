-- Crear base de datos
CREATE DATABASE joyeria_db;

-- Conectarse a la base creada
\c joyeria_db

-- Crear tabla de clientes
CREATE TABLE cliente (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- Crear tabla de joyas
CREATE TABLE joya (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    material VARCHAR(50),
    peso DECIMAL(10,2),
    precio DECIMAL(10,2),
    stock INT
);

-- Crear tabla de ventas
CREATE TABLE venta (
    id SERIAL PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_joya INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    CONSTRAINT fk_cliente FOREIGN KEY (id_cliente) REFERENCES cliente(id),
    CONSTRAINT fk_joya FOREIGN KEY (id_joya) REFERENCES joya(id)
);

-- Insertar cliente de ejemplo
INSERT INTO cliente (nombre) VALUES ('Cliente de Ejemplo');

-- Insertar joya de ejemplo
INSERT INTO joya (nombre, material, peso, precio, stock)
VALUES ('Anillo de Oro', 'Oro', 5.0, 500.00, 10);
