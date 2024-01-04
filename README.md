# Projet E-commerce

Ce projet est une application de commerce électronique développée en utilisant Spring Boot pour le backend et Angular pour le frontend.

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés :

- Java Development Kit (JDK)
- Node.js avec npm
- Angular CLI
- MySQL Server

## Installation

1. Clonez ce dépôt GitHub sur votre machine locale : git clone https://github.com/votre-utilisateur/projet-ecommerce.git
2. Accédez au répertoire backend : cd projet-ecommerce/backend


3. Créez une base de données MySQL nommée `ecommerce` (vous pouvez modifier le nom dans le fichier `application.properties` si nécessaire).

4. Ouvrez le fichier `application.properties` dans `src/main/resources` et configurez les paramètres de connexion à votre base de données :

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
