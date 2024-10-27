select *from tipohabilidad;

INSERT INTO tipohabilidad (nombre) VALUES
('Defensa'), ('Ataque'), ('Curación');

INSERT INTO habilidad (nombre, tipo_habilidad_id) VALUES
('Lengüetazo', 2), ('Rayo confusión', 2), ('Tinieblas', 2), ('Hipnosis', 3),
('Come Sueños', 3), ('Metronomo', 2), ('Canto', 3), ('Doble bofetón', 2),
('Reducción', 1), ('Anulación', 1), ('Rizo defensa', 1), ('Destructor', 2),
('Descanso', 3), ('Doble filo', 2), ('Meditación', 3), ('Sustituto', 1),
('Pantalla de luz', 1), ('Confusión', 2), ('Barrera', 1), ('Gruñido', 1),
('Teletransporte', 1), ('Reflejo', 1), ('Psicorrayo', 2), ('Recuperación', 3),
('Psíquico', 2), ('Golpe cabeza', 2), ('Pistola de rayo', 2), ('Amnesia', 1),
('Refugio', 1), ('Gas venenoso', 2), ('Somnífero', 3), ('Presa', 1),
('Drenadoras', 3), ('Paralizador', 2), ('Polvo venenoso', 2), ('Pisotón', 2),
('Pistola de agua', 2), ('Fortaleza', 1), ('Placaje', 2), ('Beso amoroso', 3),
('Puño hielo', 2), ('Golpe cuerpo', 2), ('Golpe', 2), ('Ventisca', 2),
('Rapidez', 2), ('Neblina', 1), ('Mega puño', 2), ('Transformación', 1),
('Impactrueno', 2), ('Onda trueno', 2), ('Ataque rápido', 2), ('Agilidad', 1),
('Trueno', 2), ('Bomba sónica', 2), ('Supersónico', 2), ('Chirrido', 2),
('Autodestrucción', 2), ('Explosión', 2), ('Malicioso', 1), ('Puño trueno', 2),
('Ataque arena', 1), ('Látigo', 1), ('Doble patada', 2), ('Pin misil', 2),
('Pico taladro', 2), ('Rayo aurora', 2), ('Derribo', 2), ('Tenaza', 2),
('Clavo cañón', 2), ('Rayo confuso', 2), ('Hidrobomba', 2), ('Picotazo', 2),
('Ventisca', 2);

SELECT *FROM habilidad;
