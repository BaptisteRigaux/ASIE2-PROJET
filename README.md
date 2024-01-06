# Projet E-commerce

Ce projet est une application de commerce électronique développée en utilisant Spring Boot pour le backend et Angular pour le frontend.

## Prérequis

Avant de commencer, assurez-vous d'avoir les éléments suivants installés sur votre système :

- **Java Development Kit (JDK)** : Assurez-vous d'avoir Java 8 ou une version ultérieure installée.
- **Node.js avec npm** : Installez Node.js à partir du [site officiel](https://nodejs.org/).
- **Angular CLI** : Vous pouvez installer Angular CLI en utilisant la commande `npm install -g @angular/cli`.
- **Serveur MySQL** : Assurez-vous d'avoir un serveur MySQL en cours d'exécution. Vous pouvez l'installer localement ou utiliser un service d'hébergement de bases de données.

## Installation

## Installation

1. Clonez ce dépôt GitHub sur votre machine locale : git clone https://github.com/votre-utilisateur/projet-ecommerce.git
2. Accédez au répertoire backend : cd projet-ecommerce/backend


3. Créez une base de données MySQL nommée `ecommerce` (vous pouvez modifier le nom dans le fichier `application.properties` si nécessaire).

4. Ouvrez le fichier `application.properties` dans `src/main/resources` et configurez les paramètres de connexion à votre base de données :

spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe

5. Exécutez l'application backend en utilisant Maven :   mvn spring-boot:run

6. Accédez au répertoire frontend :  cd ../frontend

7. Installez les dépendances Angular :  npm install

8. Lancez l'application frontend :


9. Ouvrez votre navigateur et accédez à [http://localhost:4200](http://localhost:4200) pour voir l'application en action.

## Fonctionnalités

- [x] Inscription et connexion des utilisateurs
- [x] Gestion des articles et des vendeurs
- [x] Ajout d'articles au panier
- [x] Commande d'articles
- [x] Gestion des adresses de livraison
- [x] ...

## Contributeurs

