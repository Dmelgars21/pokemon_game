CREATE DATABASE pokemon_game;

USE pokemon_game;

CREATE TABLE Usuario
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255)       NOT NULL

);

CREATE TABLE Entrenador
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        NVARCHAR(100) NOT NULL,
    pueblo_origen NVARCHAR(100),
    usuario_id    INT,
    FOREIGN KEY (usuario_id) REFERENCES Usuario (id)
);

-- Crear la tabla Tipo (usando autorreferencia si un tipo puede tener subtipos)
CREATE TABLE Tipo
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    nombre        VARCHAR(50) NOT NULL,
    tipo_padre_id INT DEFAULT NULL, -- Esto permite crear jerarquías de tipos
    FOREIGN KEY (tipo_padre_id) REFERENCES Tipo (id)
);

-- Crear la tabla Especie
CREATE TABLE Especie
(
    id      INT AUTO_INCREMENT PRIMARY KEY,
    nombre  VARCHAR(50) NOT NULL,
    tipo_id INT,
    FOREIGN KEY (tipo_id) REFERENCES Tipo (id)
);

-- Crear la tabla Pokemon
CREATE TABLE Pokemon
(
    id            INT AUTO_INCREMENT PRIMARY KEY,
    apodo         VARCHAR(50) DEFAULT NULL,                         -- Apodo opcional
    nivel         INT NOT NULL CHECK (nivel >= 1 AND nivel <= 100), -- Definir rango válido para el nivel
    salud         INT NOT NULL CHECK (salud > 0),                   -- Asegurar que la salud siempre sea positiva
    entrenador_id INT,
    especie_id    INT,
    FOREIGN KEY (especie_id) REFERENCES Especie (id)
);

-- Crear la tabla TipoHabilidad (tipos de habilidades como 'ataque', 'defensa', 'curación')
CREATE TABLE TipoHabilidad
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL
);

-- Crear la tabla Habilidad
CREATE TABLE Habilidad
(
    id                INT AUTO_INCREMENT PRIMARY KEY,
    nombre            VARCHAR(50) NOT NULL,
    tipo_habilidad_id INT,
    FOREIGN KEY (tipo_habilidad_id) REFERENCES TipoHabilidad (id)
);

-- Crear la tabla PokemonHabilidad (relación muchos a muchos entre Pokemon y Habilidad)
CREATE TABLE PokemonHabilidad
(
    pokemon_id   INT,
    habilidad_id INT,
    PRIMARY KEY (pokemon_id, habilidad_id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon (id),
    FOREIGN KEY (habilidad_id) REFERENCES Habilidad (id)
);

-- Crear la tabla Evolucion (relaciona las especies en una evolución)
CREATE TABLE Evolucion
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    especie_origen_id  INT,
    especie_destino_id INT,
    FOREIGN KEY (especie_origen_id) REFERENCES Especie (id),
    FOREIGN KEY (especie_destino_id) REFERENCES Especie (id)
);

-- Crear la tabla RegistroEvolucion
CREATE TABLE RegistroEvolucion
(
    id                 INT AUTO_INCREMENT PRIMARY KEY,
    pokemon_id         INT,
    especie_origen_id  INT,
    especie_destino_id INT,
    fecha_evolucion    DATE,
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon (id),
    FOREIGN KEY (especie_origen_id) REFERENCES Especie (id),
    FOREIGN KEY (especie_destino_id) REFERENCES Especie (id)
);


INSERT INTO Tipo (nombre)
VALUES ('Planta');
INSERT INTO Tipo (nombre)
VALUES ('Veneno');
INSERT INTO Tipo (nombre)
VALUES ('Fuego');
INSERT INTO Tipo (nombre)
VALUES ('Agua');
INSERT INTO Tipo (nombre)
VALUES ('Eléctrico');
INSERT INTO Tipo (nombre)
VALUES ('Normal');
INSERT INTO Tipo (nombre)
VALUES ('Volador');
INSERT INTO Tipo (nombre)
VALUES ('Bicho');
INSERT INTO Tipo (nombre)
VALUES ('Hielo');
INSERT INTO Tipo (nombre)
VALUES ('Lucha');
INSERT INTO Tipo (nombre)
VALUES ('Psíquico');
INSERT INTO Tipo (nombre)
VALUES ('Tierra');
INSERT INTO Tipo (nombre)
VALUES ('Roca');
INSERT INTO Tipo (nombre)
VALUES ('Fantasma');
INSERT INTO Tipo (nombre)
VALUES ('Dragón');
INSERT INTO Tipo (nombre)
VALUES ('Acero');
INSERT INTO Tipo (nombre)
VALUES ('Siniestro');

UPDATE Tipo
set nombre = 'Hada' where id = 17; 

Select *from especie;

INSERT INTO Especie (nombre, tipo_id) VALUES ('Bulbasaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ivysaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Venusaur', 1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charmander', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charmeleon', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Charizard', 3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Squirtle', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Wartortle', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Blastoise', 4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Caterpie', 8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Metapod', 8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Butterfree',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Weedle',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kakuna', 8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Beedrill',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pidgey',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pidgeotto',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pidgeot',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Rattata',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Raticate',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Spearow',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Fearow',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ekans',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Arbok',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pikachu',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Raichu',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Sandshrew',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Sandslash',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidoranf',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidorina',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidoqueen',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidoranm',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidorino',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Nidoking',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Clefairy',17);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Clefable',17);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Vulpix',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ninetales',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Jigglypuff',17);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Wigglytuff',17);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Zubat',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Golbat',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Oddish',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Gloom',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Viteplume',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Paras',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Parasect',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Venonat',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Venomoth',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Diglett',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dugtrio',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Meowth',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Persian',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Psyduck',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Golduck',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Mankey',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Primeape',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Growlithe',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Arcanine',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Poliwag',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Poliwhirl',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Poliwrath',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Abra',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kadabra',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Alakazam',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Machop',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Machoke',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Machamp',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Bellsprout',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Weepinbell',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Victreebel',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Tentacool',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Tentacruel',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Geodude',13);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Graveler',13);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Golem',13);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ponyta',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Rapidash',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Slowpoke',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Slowbro',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Magnemite',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Magneton',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Farfetchd',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Doduo',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dodrio',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Seel',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dewgong',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Grimer',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Muk',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Shellder',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Cloyster',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Gastly',14);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Haunter',14);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Gengar',14);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Onix',13);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Drowzee',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Hypno',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Krabby',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kinger',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Voltorb',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Electrode',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Exeggcute',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Exeggutor',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Cubone',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Marawak',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Hitmonlee',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Hitmonchan',10);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Lickitung',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Koffing',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Weezing',2);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Rhyhorn',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Rhydon',12);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Chansey',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Tangela',1);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kangaskhan',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Horsea',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Seadra',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Goldeen',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Seaking',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Staryu',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Starmie',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Mr. Mine',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Scyther',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Jynx',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Electrabuzz',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Magmar',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Pinsir',8);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Tauros',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Magikarp',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Gyarados',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Lapras',9);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Ditto',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Eevee',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Vapereon',4);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Jolteon',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Flareon',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Porygon',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Omanyte',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Omastar',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kabuto',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Kabutops',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Aerodactyl',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Snorlax',6);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Articuno',9);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Zapdos',5);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Moltres',3);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dratini',15);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dragonair',15);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Dragonite',15);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Mewtwo',11);
INSERT INTO Especie (nombre, tipo_id) VALUES ('Mew',11);

SELECT *
FROM pokemon;
SELECT * FROM Especie;
-- Insertar Pokémon en la tabla 'Pokemon'
-- Reemplaza 'entrenador_id' con los IDs de los entrenadores correspondientes.

INSERT INTO Pokemon (apodo, nivel, salud, entrenador_id, especie_id)
VALUES ('Bulbi', 5, 100, 1, 1),
       ('Charry', 8, 120, 1, 4),
       ('Squirty', 7, 110, 2, 7),
       ('Pika', 10, 130, 3, 25),
       ('Geo', 6, 90, 1, 74),
       ('Eevee', 15, 140, 2, 133),
       ('Drago', 12, 150, 3, 147);

ALTER TABLE Pokemon
    DROP COLUMN entrenador_id;
ALTER TABLE Pokemon
    DROP FOREIGN KEY pokemon_ibfk_1;


CREATE TABLE PiedraEvolutiva
(
    id           SERIAL PRIMARY KEY,
    nombre       VARCHAR(50) NOT NULL,
    tipo_pokemon VARCHAR(50) NOT NULL
);

ALTER TABLE PiedraEvolutiva
    ADD CONSTRAINT unique_nombre UNIQUE (nombre);

ALTER TABLE PiedraEvolutiva
    DROP COLUMN tipo_pokemon;

ALTER TABLE PiedraEvolutiva
    ADD COLUMN tipo_id INT,
    ADD CONSTRAINT fk_tipo_pokemon
        FOREIGN KEY (tipo_id) REFERENCES tipo (id);


INSERT INTO PiedraEvolutiva (nombre, tipo_id)
VALUES ('Piedra Hoja', 1),     -- Planta
       ('Piedra Veneno', 2),   -- Veneno
       ('Piedra Fuego', 3),    -- Fuego
       ('Piedra Agua', 4),     -- Agua
       ('Piedra Trueno', 5),   -- Eléctrico
       ('Piedra Normal', 6),   -- Normal
       ('Piedra Voladora', 7),-- Volador
       ('Piedra Bicho', 8),    -- Bicho
       ('Piedra Hielo', 9),    -- Hielo
       ('Piedra Lucha', 10),   -- Lucha
       ('Piedra Psíquica', 11),-- Psíquico
       ('Piedra Tierra', 12),  -- Tierra
       ('Piedra Roca', 13),    -- Roca
       ('Piedra Fantasma', 14),-- Fantasma
       ('Piedra Dragón', 15),  -- Dragón
       ('Piedra Acero', 16),   -- Acero
       ('Piedra Fantasia', 17); -- Hada

INSERT INTO Evolucion (especie_origen_id, especie_destino_id)
VALUES (1, 2),
       (2, 3),
       (4, 5),
       (5, 6),
       (7, 8),
       (8, 9),
       (10, 11);

CREATE TABLE Mochila (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    entrenador_id INT,
    FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id)
);
SELECT*FROM Mochila;
CREATE TABLE Item (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    tipo VARCHAR(50), -- Ejemplo: "pocion", "pokeball", "piedra evolutiva"
    especificaciones JSON, -- Para guardar propiedades adicionales (como poder, tipo de evolución)
    mochila_id INT,
    FOREIGN KEY (mochila_id) REFERENCES Mochila(id)
);

ALTER TABLE Item
   DROP column especificaciones;

CREATE TABLE Pokedex (
    id INT PRIMARY KEY AUTO_INCREMENT,
    entrenador_id INT,
    pokemon_id INT,
    registrado BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id),
    FOREIGN KEY (pokemon_id) REFERENCES Pokemon(id)
);

CREATE TABLE UsoItem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    item_id INT,
    entrenador_id INT,
    fecha_uso DATETIME,
    resultado JSON,
    FOREIGN KEY (item_id) REFERENCES Item(id),
    FOREIGN KEY (entrenador_id) REFERENCES Entrenador(id)
);
CREATE TABLE CategoriaItem (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL); -- Nombre de la categoría, por ejemplo, 'Pokeballs', 'Evolución'

ALTER TABLE Item
   DROP COLUMN tipo,
   ADD COLUMN categoria_id INT,
   ADD FOREIGN KEY (categoria_id) REFERENCES CategoriaItem(id);
   show tables;

INSERT INTO Usuario(username,password) VALUES('dai1','123');

# INSERT INTO categoriaitem(nombre) VALUES
# ('Piedra Hoja'),     -- Planta
# ('Piedra Veneno'),   -- Veneno
# ('Piedra Fuego'),    -- Fuego
# ('Piedra Agua'),     -- Agua
# ('Piedra Trueno'),   -- Eléctrico
# ('Piedra Normal'),   -- Normal
# ('Piedra Voladora'),-- Volador
# ('Piedra Bicho'),    -- Bicho
# ('Piedra Hielo'),    -- Hielo
# ('Piedra Lucha'),   -- Lucha
# ('Piedra Psíquica'),-- Psíquico
# ('Piedra Tierra'),  -- Tierra
# ('Piedra Roca'),    -- Roca
# ('Piedra Fantasma'),-- Fantasma
# ('Piedra Dragón'),  -- Dragón
# ('Piedra Acero'),   -- Acero
# ('Piedra Fantasia'); -- Hada

ALTER TABLE categoriaitem
 RENAME COLUMN id TO id_tipo_categoria;

ALTER TABLE item DROP FOREIGN KEY item_ibfk_2;

ALTER TABLE categoriaitem MODIFY id_tipo_categoria INT;

# ALTER TABLE item
# ADD CONSTRAINT item_ibfk_2 FOREIGN KEY (id_tipo_categoria) REFERENCES categoriaitem(id_tipo_categoria);

ALTER TABLE item
RENAME COLUMN categoria_id TO id_tipo_categoria;

# ALTER TABLE Item DROP FOREIGN KEY item_ibfk_2;
# TRUNCATE TABLE CategoriaItem;

ALTER TABLE item 
ADD CONSTRAINT item_ibfk_2 FOREIGN KEY (id_tipo_categoria) REFERENCES categoriaitem(id_tipo_categoria);

INSERT INTO CategoriaItem (id_tipo_categoria, nombre) VALUES
(1, 'Pokeball'),
(2, 'Piedra Evolutiva');

# ALTER TABLE Item
# DROP COLUMN mochila_id;

# ALTER TABLE Item
# DROP FOREIGN KEY item_ibfk_1;

# ALTER TABLE Item
# DROP COLUMN mochila_id;

INSERT INTO Item (nombre, id_tipo_categoria) VALUES
('Pokeball', 1),
('Pokeball Ultra', 1);

INSERT INTO Item (nombre, id_tipo_categoria) VALUES
('Piedra Hoja',2),     -- Planta
('Piedra Veneno',2),   -- Veneno
('Piedra Fuego',2),    -- Fuego
('Piedra Agua',2),     -- Agua
('Piedra Trueno',2),   -- Eléctrico
('Piedra Normal',2),   -- Normal
('Piedra Voladora',2),-- Volador
('Piedra Bicho',2),    -- Bicho
('Piedra Hielo',2),    -- Hielo
('Piedra Lucha',2),   -- Lucha
('Piedra Psíquica',2),-- Psíquico
('Piedra Tierra',2),  -- Tierra
('Piedra Roca',2),    -- Roca
('Piedra Fantasma',2),-- Fantasma
('Piedra Dragón',2),  -- Dragón
('Piedra Acero',2),   -- Acero
('Piedra Fantasia',2); -- Hada

ALTER TABLE Mochila
ADD COLUMN items INT;

INSERT INTO Entrenador (nombre, pueblo_origen,usuario_id) VALUES ('Ash Ketchum', 'Pueblo Paleta', 1);

ALTER TABLE Mochila
MODIFY COLUMN items INT DEFAULT 0;
 -- Asignar una mochila al primer entrenador
INSERT INTO Mochila (entrenador_id, nombre, items) VALUES (1, 'Mochila Principal', 5);

ALTER TABLE Usoitem
RENAME COLUMN resultado TO cantidad_usada;

ALTER TABLE Usoitem
MODIFY COLUMN cantidad_usada INT;

INSERT INTO UsoItem (item_id, entrenador_id, cantidad_usada) VALUES (1, 1, 1);