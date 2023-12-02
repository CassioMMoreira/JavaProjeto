create database loja

use loja


CREATE TABLE `carro` (
  `id_carro` int(11) NOT NULL,
  `placa` varchar(20) NOT NULL,
  `modelo` varchar(255) NOT NULL,
  `id_marca` int(11) DEFAULT NULL,
  `id_categoria` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `carro` (`id_carro`, `placa`, `modelo`, `id_marca`, `id_categoria`) VALUES
(1, 'ABC123', 'Fiesta', 1, 1),
(2, 'XYZ789', 'Civic', 4, 2),
(3, '123DEF', 'Corolla', 3, 2),
(4, '456GHI', 'Golf', 5, 3),
(5, 'JKL987', 'Cruze', 2, 1);



CREATE TABLE `categoria` (
  `id_categoria` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;



INSERT INTO `categoria` (`id_categoria`, `nome`) VALUES
(1, 'Sedan'),
(2, 'Hatchback'),
(3, 'SUV');


CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `cpf` varchar(15) NOT NULL,
  `endereco` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `cliente` (`id_cliente`, `nome`, `email`, `cpf`, `endereco`) VALUES
(1, 'Fulano Silva', 'fulano@email.com', '123.456.789-01', 'Rua A, 123'),
(2, 'Ciclano Oliveira', 'ciclano@email.com', '987.654.321-09', 'Avenida B, 456'),
(3, 'Beltrano Souza', 'beltrano@email.com', '234.567.890-12', 'Travessa C, 789'),
(4, 'Maria Pereira', 'maria@email.com', '345.678.901-23', 'Alameda D, 567'),
(5, 'José Santos', 'jose@email.com', '456.789.012-34', 'Praça E, 890');


CREATE TABLE `locacao` (
  `id_locacao` int(11) NOT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_carro` int(11) DEFAULT NULL,
  `tempo_aluguel` int(11) DEFAULT NULL,
  `valor_total` decimal(10,2) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `locacao` (`id_locacao`, `id_cliente`, `id_carro`, `tempo_aluguel`, `valor_total`) VALUES
(1, 1, 1, 10, 150.00),
(2, 2, 3, 12, 200.00),
(3, 3, 4, 3, 180.00),
(4, 4, 2, 7, 120.00),
(5, 5, 5, 2, 170.00);



CREATE TABLE `marca` (
  `id_marca` int(11) NOT NULL,
  `nome` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


INSERT INTO `marca` (`id_marca`, `nome`) VALUES
(1, 'Ford'),
(2, 'Chevrolet'),
(3, 'Toyota'),
(4, 'Honda'),
(5, 'Volkswagen');


ALTER TABLE `carro`
  ADD PRIMARY KEY (`id_carro`),
  ADD KEY `id_marca` (`id_marca`),
  ADD KEY `id_categoria` (`id_categoria`);


ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id_categoria`);


ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`);
COMMIT;
