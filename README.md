# Spring2020
#Sujet (un doctolib pour voir les profs)

On souhaite développer l'application suivante de type prise de RDV pour tout professionnel. L’idée est de pouvoir connecter l’application à un agenda externe (zimbra, google) pour identifier les plages disponibles pour un utilisateur donné afin de pouvoir prendre un RDV. Chaque professionnel de l’application pourra se créer un compte, se loguer, mettre à disposition l’url de récupération des créneaux disponibles, définir la durée nominal d’un RDV et les intitulés possibles d’un RDV. 
Un utilisateur souhaitant consulter un professionnel pourra aussi se créer un compte, se loguer et consulter la liste des créneaux disponibles et réserver un créneau.


*TP 1 Mapping Relationnel*

Lien vers le dépôt GIT : https://github.com/gadreysophie/tpjpa2021

Q1:
Diagramme UML - genyModel et Drawio

Par rapport au sujet de l'énoncé, nous avions identifier 3 classes métiers principales à développer : 

- Classe Utilisateur
-> Consulter la liste des RDV (collection de RDV)
-> Réserver un RDV
-> Consulter la liste des professionnels

- Classe Professionnel
-> Définir la durée des RDV
-> Définir l'intutilé des RDV
-> Mettre à disposition l'url de récupération des créneaux disponibles 

- Classe RDV
-> Intitulé du RDV
-> Durée du RDV (date de début et date de fin) 

- Classe Département

Q2 : 

Pour démarrer, nous avons créer un premier Package Domain contenant les classes suivantes :
- Classe Departement
- Classe Profesionnel
- Classe Rdv
- Classe Utilisateur

Ces 4 classes sont annotés @Entity afin de permettre la création de notre base de données relationnelles.

Q3 :

Nous avons créer le package dao afin de pouvoir créer et consulter des données dans la base de données relationnelles.
Nous avons créer 4 classes : 
- Classe DepartementDao
- Classe ProfessionnelDao
- Classe UtilisateurDao
- Classe RdvDao

Ces classes contiennent les requêtes qui permettent d'interagir avec la base de données.

Q4 : 
Test de liaison à une base de données sql. Pour cela il faut modifier le fichier persistance.xml.
Nous l'avons modifier avec nos données afin de se connecter à notre base sql.
Pour accéder à la base de données mysql (il faut être sur le réseau de ISTIC):
base = base_sgadrey
login = user_sgadrey
mdp = puccini

Q5 : 
Afin de travailler sur l'héritage des classes, nous avons créé la classe Personne. Nous avons créer la classe abstraite Personne dans le package domain puis nous avons créé la classe PersonneDao dans le package dao afin d'utiliser la table associée dans notre base de données relationnelles.

Nous avons également travailler sur les requêtes faites via JPA, nous avons créer une @namedQuery dans la classe Professionnel et nous l'avons utilisé dans la classe ProfessionnelDao pour lister tous les profesionnels par nom.

-> Problème de l'injection de dépendances - Malgré l'utilisation des DAO, on est dépendant de l'utilisation des EntityManager.


Q6:

Par ailleurs, nous avons fait le choix d'utiliser l'annotation SINGLE_TABLE lors de la création de l'entité Personne afin de ne gérer qu'une seule table en base de données et ainsi éviter des pertes de performance en cas de jointures des tables. En utilisant le SINGLE_TABLE, il y aura moins de jointures à réaliser.

*TP 2 Utilisation de REST*

Lien vers le dépôt GIT : https://github.com/gadreysophie/JaxRSOpenAPI

PARTIE 1

Q1 :
Mise en place du serveur Jetty en configurant le pom.xml

Q2 :

Nous avons mis en forme le formulaire HTML formAddUser.html dans le répertoire webapp -> swagger

Q3 :

Création d'un package Rest avec les classes Resource :
- DepartementResource
- ProfessionnelResource
- RdvResource 
- UserResource

Ce package et ces classes nous permettent d'envoyer des requêtes de type GET, POST et DELETE afin d'avoir une interaction entre les pages webs (formAddUser.html) et notre base de données.

Q4: 

Création de la classe AddUser afin de récupérer les données saisies dans le formulaire en ligne et les sauvegarder en base de données. 
Création de la classe ListUser afin d'afficher les données saisies et sauvegardées en base de données.



Q5: 

Nous avons développé les méthodes afin de lister les créneaux disponibles des professionnels pour l'application de rendez vous dans le package Service -> Classe RdvService. 
Nous les avons séparées des Dao car cela concerne l'applicatif métier.

PARTIE 2

Q6:

Fork du projet de GitHub pour récupérer les classes (cf Questions précédentes pour la mise en oeuvre sur notre projet JAXRS).

Q7:

Tests sur quelques requêtes avec POSTMAN.

-> Toutes nos requêtes sont sur le dépôt GITHUB du projet SPRING 2020.

Voici une des quelques requêtes effectuées :

- http://localhost:8084/prof/1

et la réponse en JSON :

{
    "nom": "Vorc'h",
    "identifiant": "rvorch",
    "mdp": "rvorch",
    "mail": "rvorch@univrennes.fr",
    "prenom": "Raoul",
    "heureDebut": "10:30:00",
    "heureFin": "12:30:00",
    "heureDebutPause": "13:30:00",
    "heureFinPause": "18:00:00",
    "joursDePresence": "1110110",
    "id": 1
}

Q8 :

Utilisation de OpenAPI et SWAGGER.

http://localhost:8084/openapi.json -> format inexploitable en l'état les requêtes ne sont pas lisibles

http://localhost:8084/api/ -> format exploitable et beaucoup plus conviviale et testable. On arrive sur Swagger PetStore suivant ce qui est indiqué dans index.html (dans webapp/swagger). 


*TP 3 Utilisation de SPRING*

PARTIE 1

Toute la première partie se situe dans le repository suivant :
https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-simple-standalone

Nous avons ajouté quelques méthodes qui permettent de tester le fonctionnement avec des System.out.println().

PARTIE 2

La partie 2 reprend le code de la partie 1 en utilisant les annotations @Aspect afin de travailler en programmation par aspect.

PARTIE 3 

Ici nous avons intégré notre code précédemment développé dans le TP2 au dépôt suivant :
https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-data-jpa-standalone

Nous avons dû faire plusieurs modifications afin d'adapter notre code à l'utilisation de SPRING. 
- Toutes nos dao sont devenues des interfaces que nous utilisons dans nos classes Resource du package REST.
- Nous avons utilisé le package SERVICE afin d'y mettre toutes nos méthodes côté applicatif métier.
- Nous avons également crée un package DTO afin de faire le lien entre nos classes domain et resource pour l'applicatif métier.

Tous nos tests Postman se trouvent à la racine du projet sous le nom Spring TP.postman_collection.
