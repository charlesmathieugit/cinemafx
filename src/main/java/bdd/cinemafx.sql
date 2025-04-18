-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost
-- Généré le : mar. 15 avr. 2025 à 01:10
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;-- phpMyAdmin SQL Dump
                  -- version 5.2.1
                  -- https://www.phpmyadmin.net/
                  --
                  -- Hôte : localhost
                  -- Généré le : mer. 16 avr. 2025 à 12:08
                  -- Version du serveur : 10.4.28-MariaDB
                  -- Version de PHP : 8.2.4

                  SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
                  START TRANSACTION;
                  SET time_zone = "+00:00";


                  /*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
                  /*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
                  /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
                  /*!40101 SET NAMES utf8mb4 */;

                  --
                  -- Base de données : `cinemafx`
                  --

                  -- --------------------------------------------------------

                  --
                  -- Structure de la table `clients`
                  --

                  CREATE TABLE `clients` (
                    `id` int(11) NOT NULL,
                    `name` varchar(50) NOT NULL,
                    `adresse` varchar(255) DEFAULT NULL,
                    `email` varchar(100) DEFAULT NULL,
                    `birthdate` varchar(50) DEFAULT NULL
                  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

                  --
                  -- Déchargement des données de la table `clients`
                  --

                  INSERT INTO `clients` (`id`, `name`, `adresse`, `email`, `birthdate`) VALUES
                  (3, 'toto', 'jvrv@ujeb', '24/03/2001', '454 avenue ideozp'),
                  (4, 'client1', 'client1@yahoo.fr', '27/04/1997', '155 avenue de Marianne'),
                  (5, 'tata', 'ezfr@reg', '12/05/2001', '25 av fcrf'),
                  (6, 'test', 'test@test', '12/12/3004', 'test');

                  -- --------------------------------------------------------

                  --
                  -- Structure de la table `salle`
                  --

                  CREATE TABLE `salle` (
                    `id` int(11) NOT NULL,
                    `nom_salle` varchar(255) NOT NULL,
                    `nb_places_total` int(11) NOT NULL
                  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

                  --
                  -- Déchargement des données de la table `salle`
                  --

                  INSERT INTO `salle` (`id`, `nom_salle`, `nb_places_total`) VALUES
                  (1, 'Jupiter', 86),
                  (3, 'Test', 20);

                  -- --------------------------------------------------------

                  --
                  -- Structure de la table `seances`
                  --

                  CREATE TABLE `seances` (
                    `id` int(11) NOT NULL,
                    `film` varchar(255) NOT NULL,
                    `client` varchar(255) NOT NULL,
                    `salle` int(11) NOT NULL,
                    `places` int(11) NOT NULL,
                    `horaires` time NOT NULL,
                    `date` date NOT NULL
                  ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

                  --
                  -- Déchargement des données de la table `seances`
                  --

                  INSERT INTO `seances` (`id`, `film`, `client`, `salle`, `places`, `horaires`, `date`) VALUES
                  (6, 'test', '', 32, 32, '13:00:00', '2024-05-12'),
                  (7, 'testa', '', 2, 1, '13:00:00', '2024-05-12'),
                  (8, 'testa', '', 3, 1, '13:00:00', '2024-05-12'),
                  (9, 'Testa', '', 1, 1, '13:00:00', '2025-04-12'),
                  (10, 'Test', '', 12, 1, '12:00:00', '1212-12-12'),
                  (11, 'Test', '', 2, 1, '13:00:00', '1314-01-13');

                  --
                  -- Index pour les tables déchargées
                  --

                  --
                  -- Index pour la table `clients`
                  --
                  ALTER TABLE `clients`
                    ADD PRIMARY KEY (`id`),
                    ADD UNIQUE KEY `mail` (`email`) USING BTREE;

                  --
                  -- Index pour la table `salle`
                  --
                  ALTER TABLE `salle`
                    ADD PRIMARY KEY (`id`);

                  --
                  -- Index pour la table `seances`
                  --
                  ALTER TABLE `seances`
                    ADD PRIMARY KEY (`id`);

                  --
                  -- AUTO_INCREMENT pour les tables déchargées
                  --

                  --
                  -- AUTO_INCREMENT pour la table `clients`
                  --
                  ALTER TABLE `clients`
                    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

                  --
                  -- AUTO_INCREMENT pour la table `salle`
                  --
                  ALTER TABLE `salle`
                    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

                  --
                  -- AUTO_INCREMENT pour la table `seances`
                  --
                  ALTER TABLE `seances`
                    MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
                  COMMIT;

                  /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
                  /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
                  /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `cinemafx`
--

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

CREATE TABLE `clients` (
  `id` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `birthdate` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`id`, `name`, `adresse`, `email`, `birthdate`) VALUES
(3, 'toto', 'jvrv@ujeb', '24/03/2001', '454 avenue ideozp'),
(4, 'client1', 'client1@yahoo.fr', '27/04/1997', '155 avenue de Marianne'),
(5, 'tata', 'ezfr@reg', '12/05/2001', '25 av fcrf'),
(6, 'test', 'test@test', '12/12/3004', 'test');

-- --------------------------------------------------------

--
-- Structure de la table `salle`
--

CREATE TABLE `salle` (
  `id` int(11) NOT NULL,
  `nom_salle` varchar(255) NOT NULL,
  `nb_places_total` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `salle`
--

INSERT INTO `salle` (`id`, `nom_salle`, `nb_places_total`) VALUES
(1, 'Jupiter', 86),
(3, 'Test', 20);

-- --------------------------------------------------------

--
-- Structure de la table `seances`
--

CREATE TABLE `seances` (
  `id` int(11) NOT NULL,
  `film` varchar(255) NOT NULL,
  `salle` int(11) NOT NULL,
  `places` int(11) NOT NULL,
  `horaires` time NOT NULL,
  `date` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `seances`
--

INSERT INTO `seances` (`id`, `film`, `salle`, `places`, `horaires`, `date`) VALUES
(6, 'test', 32, 32, '13:00:00', '2024-05-12'),
(7, 'testa', 2, 1, '13:00:00', '2024-05-12'),
(8, 'testa', 3, 1, '13:00:00', '2024-05-12'),
(9, 'Testa', 1, 1, '13:00:00', '2025-04-12'),
(10, 'Test', 12, 1, '12:00:00', '1212-12-12'),
(11, 'Test', 2, 1, '13:00:00', '1314-01-13');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `clients`
--
ALTER TABLE `clients`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `mail` (`email`) USING BTREE;

--
-- Index pour la table `salle`
--
ALTER TABLE `salle`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `seances`
--
ALTER TABLE `seances`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `clients`
--
ALTER TABLE `clients`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT pour la table `salle`
--
ALTER TABLE `salle`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT pour la table `seances`
--
ALTER TABLE `seances`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
