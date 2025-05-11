# Architecture du projet CinemaFX

Ce document décrit l'architecture technique et la structure du projet CinemaFX, une application JavaFX pour la gestion d'un cinéma.

## Vue d'ensemble

CinemaFX est une application de bureau développée en Java avec le framework JavaFX qui permet de gérer les différentes entités d'un cinéma : clients, salles et séances. L'application suit une architecture proche du modèle MVC (Modèle-Vue-Contrôleur) et utilise une base de données MySQL pour la persistance des données.

## Structure du projet

Le projet est organisé selon la structure suivante :

```
cinemafx/
├── src/
│   └── main/
│       ├── java/
│       │   ├── bdd/                    # Couche d'accès aux données
│       │   │   ├── BDDManager.java     # Gestion de la connexion à la BDD
│       │   │   ├── ClientManager.java  # Opérations CRUD pour les clients
│       │   │   ├── SalleManager.java   # Opérations CRUD pour les salles
│       │   │   ├── SeanceManager.java  # Opérations CRUD pour les séances
│       │   │   └── cinemafx.sql        # Script SQL de la base de données
│       │   ├── com/example/cinemafx/   # Package principal de l'application
│       │   │   ├── Cinema.java         # Point d'entrée de l'application JavaFX
│       │   │   └── CinemaController.java # Contrôleur principal de l'interface
│       │   ├── models/                 # Modèles de données
│       │   │   ├── Client.java         # Entité Client
│       │   │   ├── Salle.java          # Entité Salle de cinéma
│       │   │   └── Seance.java         # Entité Séance de cinéma
│       │   └── module-info.java        # Descripteur du module Java
│       └── resources/                  # Ressources de l'application
├── pom.xml                             # Configuration Maven du projet
└── ARCHITECTURE.md                     # Ce document
```

## Architecture logicielle

L'application suit une architecture en couches :

### Couche de présentation (Vue)
- Implémentée avec JavaFX
- Fichiers FXML (non visibles dans l'analyse, mais référencés dans le code)
- Située principalement dans le package `com.example.cinemafx`
- `Cinema.java` : classe principale qui démarre l'application JavaFX

### Couche de contrôleur
- `CinemaController.java` : contrôleur principal qui gère les événements de l'interface utilisateur et coordonne les appels entre la vue et les modèles
- Responsable de la logique métier et de la coordination des différentes actions

### Couche modèle
- Entités de données dans le package `models`
  - `Client.java` : représente un client du cinéma
  - `Salle.java` : représente une salle de cinéma avec son nom et sa capacité
  - `Seance.java` : représente une séance de projection avec film, salle, client et places

### Couche d'accès aux données
- Package `bdd` : contient les classes qui gèrent l'accès à la base de données
  - `BDDManager.java` : établit la connexion avec la base de données MySQL
  - `ClientManager.java` : gère les opérations CRUD pour les clients
  - `SalleManager.java` : gère les opérations CRUD pour les salles
  - `SeanceManager.java` : gère les opérations CRUD pour les séances

## Schéma de la base de données

D'après l'analyse du code, la base de données comprend au moins les tables suivantes :

- `clients` : stocke les informations des clients (id, name, birthdate, adresse, email)
- `salle` : stocke les informations des salles de cinéma (id, nom_salle, nb_places_total)
- Une table pour les séances (non visible directement dans les extraits de code analysés)

## Technologies utilisées

- **Langage** : Java 21
- **Interface utilisateur** : JavaFX 21
- **Base de données** : MySQL 8
- **Connecteur** : mysql-connector-java 8.0.33
- **Gestion de dépendances** : Maven
- **Tests** : JUnit 5.9.2

## Flux de données

1. L'utilisateur interagit avec l'interface JavaFX
2. Les événements sont captés par le `CinemaController`
3. Le contrôleur utilise les classes Manager pour effectuer des opérations CRUD
4. Les classes Manager communiquent avec la base de données via `BDDManager`
5. Les données récupérées sont transformées en objets modèles
6. Les objets modèles sont affichés dans l'interface utilisateur

## Points d'extension

Le code actuel présente une architecture modulaire qui pourrait être étendue pour :
- Ajouter de nouvelles entités (films, employés, etc.)
- Implémenter de nouvelles fonctionnalités (réservation en ligne, statistiques, etc.)
- Améliorer la gestion des erreurs et la validation des données
- Ajouter des tests unitaires et d'intégration
