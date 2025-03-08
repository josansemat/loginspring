-- Equipos
INSERT INTO equipos (nombre, ciudad, anio_fundacion, estadio, categoria, fecha_creacion, fecha_modificacion, escudo) 
VALUES ('FC Barcelona', 'Barcelona', 1899, 'Camp Nou', 'PRIMERA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/en/thumb/4/47/FC_Barcelona_%28crest%29.svg/1200px-FC_Barcelona_%28crest%29.svg.png');

INSERT INTO equipos (nombre, ciudad, anio_fundacion, estadio, categoria, fecha_creacion, fecha_modificacion, escudo) 
VALUES ('Real Madrid CF', 'Madrid', 1902, 'Santiago Bernabéu', 'PRIMERA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/en/thumb/5/56/Real_Madrid_CF.svg/1200px-Real_Madrid_CF.svg.png');

INSERT INTO equipos (nombre, ciudad, anio_fundacion, estadio, categoria, fecha_creacion, fecha_modificacion, escudo) 
VALUES ('Atlético de Madrid', 'Madrid', 1903, 'Metropolitano', 'PRIMERA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/en/thumb/f/f4/Atletico_Madrid_2017_logo.svg/1200px-Atletico_Madrid_2017_logo.svg.png');

INSERT INTO equipos (nombre, ciudad, anio_fundacion, estadio, categoria, fecha_creacion, fecha_modificacion, escudo) 
VALUES ('Valencia CF', 'Valencia', 1919, 'Mestalla', 'PRIMERA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/en/thumb/c/ce/Valenciacf.svg/1200px-Valenciacf.svg.png');

INSERT INTO equipos (nombre, ciudad, anio_fundacion, estadio, categoria, fecha_creacion, fecha_modificacion, escudo) 
VALUES ('Málaga CF', 'Málaga', 1904, 'La Rosaleda', 'SEGUNDA', CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/en/thumb/f/f1/Malaga_CF.svg/1200px-Malaga_CF.svg.png');

-- Jugadores
INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Lionel', 'Messi', '1987-06-24', 'Argentina', 'DELANTERO', 10, 170, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/b/b4/Lionel-Messi-Argentina-2022-FIFA-World-Cup_%28cropped%29.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Sergio', 'Busquets', '1988-07-16', 'España', 'CENTROCAMPISTA', 5, 189, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/8/8c/Sergio_Busquets_2018.jpg/800px-Sergio_Busquets_2018.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Marc-André', 'ter Stegen', '1992-04-30', 'Alemania', 'PORTERO', 1, 187, 1, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/e1/Marc-Andr%C3%A9_ter_Stegen.jpg/800px-Marc-Andr%C3%A9_ter_Stegen.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Karim', 'Benzema', '1987-12-19', 'Francia', 'DELANTERO', 9, 185, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/e/ec/Karim_Benzema_2018.jpg/800px-Karim_Benzema_2018.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Thibaut', 'Courtois', '1992-05-11', 'Bélgica', 'PORTERO', 1, 199, 2, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/c/c4/Courtois_2018_%28cropped%29.jpg/800px-Courtois_2018_%28cropped%29.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Antoine', 'Griezmann', '1991-03-21', 'Francia', 'DELANTERO', 7, 176, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/f/fa/Antoine_Griezmann_2018.jpg/800px-Antoine_Griezmann_2018.jpg');

INSERT INTO jugadores (nombre, apellidos, fecha_nacimiento, nacionalidad, posicion, dorsal, altura, equipo_id, fecha_creacion, fecha_modificacion, foto) 
VALUES ('Jan', 'Oblak', '1993-01-07', 'Eslovenia', 'PORTERO', 13, 188, 3, CURRENT_TIMESTAMP(), CURRENT_TIMESTAMP(), 'https://upload.wikimedia.org/wikipedia/commons/thumb/7/7f/Jan_Oblak_2019.jpg/800px-Jan_Oblak_2019.jpg');

-- Usuario administrador por defecto
INSERT INTO usuarios (username, password, email, role, activo) 
VALUES ('admin', '$2a$10$rJyLJv.1aHgaorpC8qwdAO.rSdYfzuflqBVJKGQUwKzNYvwygEE9e', 'admin@example.com', 'ROLE_ADMIN', true); 