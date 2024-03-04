create database db_hoy_no_circula
use db_hoy_no_circula
CREATE TABLE carro (
    id INT AUTO_INCREMENT PRIMARY KEY,
    placa VARCHAR(10) NOT NULL UNIQUE,
    color VARCHAR(50) NOT NULL,
    modelo VARCHAR(50) NOT NULL,
    chasis VARCHAR(17) NOT NULL UNIQUE,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
INSERT INTO carro (placa, color, modelo, chasis)
VALUES ('ABC123', 'Rojo', 'Toyota Corolla', '12345678901234567');
select * FROM carro;
