-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : jeu. 25 avr. 2024 à 18:39
-- Version du serveur : 5.7.40
-- Version de PHP : 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `aminedb`
--

-- --------------------------------------------------------

--
-- Structure de la table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE IF NOT EXISTS `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment` varchar(255) DEFAULT NULL,
  `date` datetime(6) DEFAULT NULL,
  `note` enum('ONE','TWO','THREE','FOUR','FIVE') DEFAULT NULL,
  `title_formation` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `file_entity`
--

DROP TABLE IF EXISTS `file_entity`;
CREATE TABLE IF NOT EXISTS `file_entity` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `data` mediumblob,
  `file_name` varchar(255) DEFAULT NULL,
  `file_type` varchar(255) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbh56rr8lky8jnxug5yneo74qc` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `question`
--

DROP TABLE IF EXISTS `question`;
CREATE TABLE IF NOT EXISTS `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `response` enum('NO','NOT_ENOUGH','OKAY','GOOD','EXCELLENT') DEFAULT NULL,
  `text` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `question`
--

INSERT INTO `question` (`id`, `name`, `response`, `text`) VALUES
(1, 'Question 1', 'NO', 'Pouvez vou assurer une transaction monétaire?'),
(2, 'Question 2', 'NO', 'Pouvez vou assurer une transaction monétaire?');

-- --------------------------------------------------------

--
-- Structure de la table `theme`
--

DROP TABLE IF EXISTS `theme`;
CREATE TABLE IF NOT EXISTS `theme` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `theme`
--

INSERT INTO `theme` (`id`, `title`) VALUES
(1, 'Gestion etats financier');

-- --------------------------------------------------------

--
-- Structure de la table `theme_questions`
--

DROP TABLE IF EXISTS `theme_questions`;
CREATE TABLE IF NOT EXISTS `theme_questions` (
  `theme_id` bigint(20) NOT NULL,
  `questions_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_heeoxep8i20ltvatvdbk3yk8u` (`questions_id`),
  KEY `FKp9udb4uxlklxn8y1u2c1bxehl` (`theme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `theme_questions`
--

INSERT INTO `theme_questions` (`theme_id`, `questions_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `theme_user`
--

DROP TABLE IF EXISTS `theme_user`;
CREATE TABLE IF NOT EXISTS `theme_user` (
  `theme_id` bigint(20) NOT NULL,
  `user_id` bigint(20) NOT NULL,
  KEY `FKgooih4phftrad8urw4suwcytk` (`user_id`),
  KEY `FKmfrnhbfshqimy15utob1pcobj` (`theme_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `theme_user`
--

INSERT INTO `theme_user` (`theme_id`, `user_id`) VALUES
(1, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `matricule` bigint(20) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','USER') DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `department`, `email`, `first_name`, `last_name`, `matricule`, `password`, `phone`, `region`, `role`, `status`) VALUES
(1, 'Informatique', 'test@gmail.com', 'test', 'test', 12345, '$2a$10$iuP76FsRugA14fZNakZpceciELNqVi2H1DCyjOe5b4/QsqUN3Svj.', '26338622', 'Tunis', 'USER', b'1'),
(5, 'Informatique', 'test1@gmail.com', 'test', 'test', 12345, '$2a$10$.hKb/sCdk2RGFGn4/VB/pOZ9asYeq2eQz99xl74FquR4IlZwUF3Fm', '26338622', 'Tunis', 'USER', b'1');

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `file_entity`
--
ALTER TABLE `file_entity`
  ADD CONSTRAINT `FKbh56rr8lky8jnxug5yneo74qc` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);

--
-- Contraintes pour la table `theme_questions`
--
ALTER TABLE `theme_questions`
  ADD CONSTRAINT `FK105pmfroh9hvg6m58a5628kjx` FOREIGN KEY (`questions_id`) REFERENCES `question` (`id`),
  ADD CONSTRAINT `FKp9udb4uxlklxn8y1u2c1bxehl` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`);

--
-- Contraintes pour la table `theme_user`
--
ALTER TABLE `theme_user`
  ADD CONSTRAINT `FKgooih4phftrad8urw4suwcytk` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  ADD CONSTRAINT `FKmfrnhbfshqimy15utob1pcobj` FOREIGN KEY (`theme_id`) REFERENCES `theme` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
