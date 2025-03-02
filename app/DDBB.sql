create Database DDBB;
use DDBB;

Drop Database DDBB;

CREATE TABLE persona (
    id INT AUTO_INCREMENT PRIMARY KEY,   -- ID único para cada persona
    name_ VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL
);

CREATE TABLE vuelo (
    id INT AUTO_INCREMENT PRIMARY KEY,   -- ID único para cada vuelo
    flight_number VARCHAR(20) NOT NULL UNIQUE, -- Número de vuelo (único)
    name_ VARCHAR(100) NOT NULL, -- Nombre de la aerolínea
    origin VARCHAR(100) NOT NULL,
    destination VARCHAR(100) NOT NULL,
    seat_type VARCHAR(50) NOT NULL,
    flight_price DECIMAL(10,2) NOT NULL,
    date_ DATE NOT NULL
);

CREATE TABLE reserva_vuelo (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL,
    vuelo_id INT NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (vuelo_id) REFERENCES vuelo(id) ON DELETE CASCADE,
    UNIQUE (persona_id, vuelo_id)
);

CREATE TABLE hotel (
    hotel_code INT AUTO_INCREMENT PRIMARY KEY,  -- ID único para cada hotel
    name_ VARCHAR(100) NOT NULL,      -- Nombre del hotel
    place VARCHAR(100) NOT NULL,     -- Ubicación del hotel
    room_type VARCHAR(50) NOT NULL,  -- Tipo de habitación
    room_price DECIMAL(10,2) NOT NULL, -- Precio por habitación
    disponibility_date_from DATE NOT NULL, -- Fecha de inicio de disponibilidad
    disponibility_date_to DATE NOT NULL,   -- Fecha de fin de disponibilidad
    is_booked BOOLEAN NOT NULL DEFAULT FALSE -- Indica si está reservado o no
);
-- Crear tabla Reserva_Hotel (relación "uno a muchos" entre Persona y Hotel)
CREATE TABLE reserva_hotel (
    id INT AUTO_INCREMENT PRIMARY KEY,
    persona_id INT NOT NULL,
    hotel_id INT NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE NOT NULL,
    FOREIGN KEY (persona_id) REFERENCES persona(id) ON DELETE CASCADE,
    FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_code) ON DELETE CASCADE
);

INSERT INTO vuelo (flight_number, name_, origin, destination, seat_type, flight_price, date_) 
VALUES 
('BAMI-1235', 'Iberia', 'Barcelona', 'Miami', 'Economy', 650, '2024-02-10'),
('MIMA1420', 'American Airlines', 'Miami', 'Madrid', 'Business', 4320, '2024-02-10'),
('MIMA-1420', 'American Airlines', 'Miami', 'Madrid', 'Economy', 2573, '2024-02-10'),
('BABU-5536', 'LATAM', 'Barcelona', 'Buenos Aires', 'Economy', 732, '2024-02-10'),
('BUBA-3369', 'Air Europa', 'Buenos Aires', 'Barcelona', 'Business', 1253, '2024-02-12'),
('IGBA-3369', 'Aerolineas Argentinas', 'Iguazú', 'Barcelona', 'Economy', 540, '2024-01-02'),
('BOCA-4213', 'Avianca', 'Bogotá', 'Cartagena', 'Economy', 800, '2024-01-23'),
('CAME-0321', 'Viva Air', 'Cartagena', 'Medellín', 'Economy', 780, '2024-01-23'),
('BOIG-6567', 'Copa Airlines', 'Bogotá', 'Iguazú', 'Business', 570, '2024-02-15'),
('BOBA-6567', 'LATAM', 'Bogotá', 'Buenos Aires', 'Economy', 398, '2024-03-01'),
('BOMA-4442', 'Iberia', 'Bogotá', 'Madrid', 'Economy', 1100, '2024-02-10'),
('MEMI-9986', 'American Airlines', 'Medellín', 'Miami', 'Business', 1164, '2024-04-17');

INSERT INTO hotel (name_, place, room_type, room_price, disponibility_date_from, disponibility_date_to, is_booked) 
VALUES 
('Atlantis Resort', 'Miami', 'Doble', 630, '2024-02-10', '2024-03-20', FALSE),
('Atlantis Resort 2', 'Miami', 'Triple', 820, '2024-02-10', '2024-03-23', FALSE),
('Ritz-Carlton', 'Buenos Aires', 'Single', 543, '2024-02-10', '2024-03-19', FALSE),
('Ritz-Carlton 2', 'Medellín', 'Doble', 720, '2024-02-12', '2024-04-17', FALSE),
('Grand Hyatt', 'Madrid', 'Doble', 579, '2024-04-17', '2024-05-23', FALSE),
('Grand Hyatt 2', 'Buenos Aires', 'Single', 415, '2024-01-02', '2024-02-19', FALSE),
('Hilton', 'Barcelona', 'Single', 390, '2024-01-23', '2024-11-23', FALSE),
('Hilton 2', 'Barcelona', 'Doble', 584, '2024-01-23', '2024-10-15', FALSE),
('Marriott', 'Barcelona', 'Doble', 702, '2024-02-15', '2024-03-27', FALSE),
('Sheraton', 'Madrid', 'Múltiple', 860, '2024-03-01', '2024-04-17', FALSE),
('Sheraton 2', 'Iguazú', 'Doble', 640, '2024-02-10', '2024-03-20', FALSE),
('InterContinental', 'Cartagena', 'Múltiple', 937, '2024-04-17', '2024-06-12', FALSE);

INSERT INTO persona (name_, last_name) 
VALUES 
('Carlos', 'Gómez'),
('María', 'Fernández'),
('Juan', 'Pérez'),
('Ana', 'Martínez'),
('Luis', 'Rodríguez'),
('Sofía', 'López'),
('Pedro', 'García'),
('Elena', 'Sánchez'),
('Diego', 'Torres'),
('Valeria', 'Ramírez'),
('Javier', 'Morales'),
('Lucía', 'Vargas');

SET FOREIGN_KEY_CHECKS=1;

ALTER TABLE reserva_hotel DROP FOREIGN KEY reserva_hotel_ibfk_2;
ALTER TABLE reserva_hotel DROP FOREIGN KEY reserva_hotel_ibfk_1;
ALTER TABLE reserva_vuelo DROP FOREIGN KEY reserva_vuelo_ibfk_1;
ALTER TABLE reserva_vuelo DROP FOREIGN KEY reserva_vuelo_ibfk_2;

ALTER TABLE hotel MODIFY COLUMN hotel_code BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE persona MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE vuelo MODIFY COLUMN id BIGINT NOT NULL AUTO_INCREMENT;
ALTER TABLE reserva_hotel MODIFY COLUMN hotel_id BIGINT NOT NULL;
ALTER TABLE reserva_vuelo MODIFY COLUMN persona_id BIGINT NOT NULL;

ALTER TABLE reserva_hotel ADD CONSTRAINT reserva_hotel_ibfk_2 FOREIGN KEY (hotel_id) REFERENCES hotel(hotel_code);
ALTER TABLE reserva_hotel ADD CONSTRAINT reserva_hotel_ibfk_1 FOREIGN KEY (persona_id) REFERENCES persona(id);
ALTER TABLE reserva_vuelo ADD CONSTRAINT reserva_vuelo_ibfk_1 FOREIGN KEY (persona_id) REFERENCES persona(id);
ALTER TABLE reserva_vuelo ADD CONSTRAINT reserva_vuelo_ibfk_2 FOREIGN KEY (vuelo_id) REFERENCES vuelo(id);

