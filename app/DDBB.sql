
DROP DATABASE DDBB;
CREATE DATABASE DDBB;
USE DDBB;

CREATE TABLE persona (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL
);

CREATE TABLE vuelo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    flight_number VARCHAR(20) NOT NULL,
    aerolinea_nombre VARCHAR(100) NOT NULL, 
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    seat_type VARCHAR(50) NOT NULL,
    flight_price DECIMAL(10,2) NOT NULL,
    fecha DATE NOT NULL
);

CREATE TABLE reserva_vuelo (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    persona_id BIGINT NOT NULL,
    vuelo_id BIGINT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (vuelo_id) REFERENCES vuelo(id) ON DELETE CASCADE,
    UNIQUE (persona_id, vuelo_id)
);

CREATE TABLE hotel (
    hotel_code BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    place VARCHAR(100) NOT NULL,
    room_type VARCHAR(50) NOT NULL,
    room_price DECIMAL(10,2) NOT NULL,
    disponibility_date_from DATE NOT NULL,
    disponibility_date_to DATE NOT NULL,
    is_booked BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE reserva_hotel (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    persona_id BIGINT NOT NULL,
    hotel_id BIGINT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_code) ON DELETE CASCADE
);

SET FOREIGN_KEY_CHECKS=0;

-- Eliminar claves foráneas correctamente
ALTER TABLE reserva_hotel DROP FOREIGN KEY reserva_hotel_ibfk_2;
ALTER TABLE reserva_hotel DROP FOREIGN KEY reserva_hotel_ibfk_1;
ALTER TABLE reserva_vuelo DROP FOREIGN KEY reserva_vuelo_ibfk_1;
ALTER TABLE reserva_vuelo DROP FOREIGN KEY reserva_vuelo_ibfk_2;

-- Modificar tipos de datos antes de cambiar claves primarias
ALTER TABLE hotel MODIFY COLUMN hotel_code BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE persona MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE vuelo MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE reserva_hotel MODIFY COLUMN hotel_id BIGINT NOT NULL;
ALTER TABLE reserva_vuelo MODIFY COLUMN persona_id BIGINT NOT NULL;

-- Restaurar claves foráneas correctamente
ALTER TABLE reserva_hotel ADD CONSTRAINT reserva_hotel_ibfk_2 FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_code);
ALTER TABLE reserva_hotel ADD CONSTRAINT reserva_hotel_ibfk_1 FOREIGN KEY (persona_id) REFERENCES persona(id);
ALTER TABLE reserva_vuelo ADD CONSTRAINT reserva_vuelo_ibfk_1 FOREIGN KEY (persona_id) REFERENCES persona(id);
ALTER TABLE reserva_vuelo ADD CONSTRAINT reserva_vuelo_ibfk_2 FOREIGN KEY (vuelo_id) REFERENCES vuelo(id);

SET FOREIGN_KEY_CHECKS=1;

-- Insertar datos corregidos
INSERT INTO vuelo (flight_number, aerolinea_nombre, origin, destination, seat_type, flight_price, fecha) 
VALUES 
('BAMI-1235', 'Iberia', 'Barcelona', 'Miami', 'Economy', 650, '2024-02-10'),
('MIMA1420', 'American Airlines', 'Miami', 'Madrid', 'Business', 4320, '2024-02-10'),
('MIMA-1420', 'American Airlines', 'Miami', 'Madrid', 'Economy', 2573, '2024-02-10'),
('BABU-5536', 'LATAM', 'Barcelona', 'Buenos Aires', 'Economy', 732, '2024-02-10'),
('BUBA-3369', 'Air Europa', 'Buenos Aires', 'Barcelona', 'Business', 1253, '2024-02-12');

INSERT INTO hotel (nombre, place, room_type, room_price, disponibility_date_from, disponibility_date_to, is_booked) 
VALUES 
('Atlantis Resort', 'Miami', 'Doble', 630, '2024-02-10', '2024-03-20', FALSE),
('Ritz-Carlton', 'Buenos Aires', 'Single', 543, '2024-02-10', '2024-03-19', FALSE),
('Grand Hyatt', 'Madrid', 'Doble', 579, '2024-04-17', '2024-05-23', FALSE),
('Hilton', 'Barcelona', 'Single', 390, '2024-01-23', '2024-11-23', FALSE),
('Sheraton', 'Madrid', 'Múltiple', 860, '2024-03-01', '2024-04-17', FALSE);

INSERT INTO persona (nombre, last_name) 
VALUES 
('Carlos', 'Gómez'),
('María', 'Fernández'),
('Juan', 'Pérez'),
('Ana', 'Martínez'),
('Luis', 'Rodríguez');

