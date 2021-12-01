# Spring2020

## 1 - Sujet

<p style='text-align: justify;'>
On souhaite développer une application de type "prise de RDV" entre un professionnel et un utilisateur. L’idée est de pouvoir ensuite connecter l’application à un agenda externe (zimbra, google) pour identifier les plages disponibles pour un utilisateur donné afin de pouvoir prendre un RDV. Chaque professionnel de l’application pourra se créer un compte, se loguer, mettre à disposition l’url de récupération des créneaux disponibles, définir la durée nominal d’un RDV et les intitulés possibles d’un RDV. 
De plus, un utilisateur souhaitant consulter un professionnel pourra lui aussi se créer un compte, se loguer et consulter la liste des créneaux disponibles et réserver un créneau.
</p>

## 2 - Différents TPs

### *TP 1 Mapping Relationnel*

Lien vers le dépôt GIT : https://github.com/gadreysophie/tpjpa2021

### *TP 2 Utilisation de Rest*

Lien vers le dépôt GIT : https://github.com/gadreysophie/JaxRSOpenAPI

### *TP 3 Utilisation de Spring*

#### *PARTIE 1*

<p style='text-align: justify;'>
Toute la première partie se situe dans le repository suivant :
<a href="https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-simple-standalone">https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-simple-standalone</a>
Nous avons ajouté quelques méthodes qui permettent de tester le fonctionnement avec des System.out.println().
</p>

#### *PARTIE 2*

<p style='text-align: justify;'>
La partie 2 reprend le code de la partie 1 en utilisant les annotations @Aspect afin de travailler en programmation par aspect.
</p>

#### *PARTIE 3*

<p style='text-align: justify;'>
Ici nous avons intégré notre code précédemment développé dans le TP2 au dépôt suivant : 
<a href="https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-data-jpa-standalone">https://github.com/gadreysophie/Spring2020/tree/master/spring-boot-sample-data-jpa-standalone</a>.
Ainsi, nous avons dû faire plusieurs modifications et adapter notre code afin d'utiliser Spring. 
</p>

## 3 - Diagramme UML et explications

<p style='text-align: justify;'>
Par rapport au sujet de l'énoncé, nous avons identifié 6 classes métiers principales à développer : 
</p>

<ul style='text-align: justify;'><li>Une classe abstraite Personne</li></ul>

    - Contenant les attributs généraux qualifiant à la fois un utilisateur et un professionnel

<ul style='text-align: justify;'><li>Une classe Utilisateur (extends Personne)</li></ul>

    - Réserver / Annuler un rdv
    - Consulter la liste des rdvs disponibles
    - Consulter la liste des professionnels
    - Consulter la liste de ses réservations
    - Création d'un compte

<ul style='text-align: justify;'><li>Une classe Professionnel (extends Personne)</li></ul>

    - Réserver / Annuler un RDV
    - Consulter la liste des rdvs disponibles
    - Consulter la liste de ses rdvs
    - Ajouter / Modifier un type de rdv
    - Ajouter / Modifier son département
    - Création d'un compte

<ul style='text-align: justify;'><li>Une classe RDV</li></ul>

    - Contenant toutes les informations liées à un rdv : date de début, date de fin, professionnel, utilisateur...
    - Création d'une liste de créneaux disponibles via une DTO

<ul style='text-align: justify;'><li>Une classe TypeRdv</li></ul>

    - Permet de définir différents types de rdv avec des durées et des noms différrents

<ul style='text-align: justify;'><li>Une Classe Departement</li></ul>

    - Permet d'indiquer le département dans lequel le professionnel travaille

<p style='text-align: justify;'>
Afin de travailler sur l'héritage des classes comme demandé dans un des TPs, nous avons créé la classe abstraite Personne. Par ailleurs, nous avons fait le choix d'utiliser l'annotation SINGLE_TABLE lors de la création de l'entité Personne afin de ne gérer qu'une seule table en base de données et ainsi éviter des pertes de performance en cas de jointures des tables.
</p>

<p style='text-align: justify;'>
Ensuite, nous avons décidé de créer une classe TypeRdv dans le but de simplifier la gestion des durées de rdv. Une optimisation pourra être effectuée avant de proposer des créneaux disponibles aux utilisateurs. L'objectif étant de ne pas avoir de créneaux libres trop courts qui ne permettent pas de fixer un rdv. Ce système permet donc de proposer des créneaux disponibles, à un utilisateur, pour un professionnel (à une date donnée) en prenant en compte la durée minimum de ses types de rdv. Ainsi, il pourra toujours au minimum placer un rdv (avec la durée minimum de ses types de rdv) lors de ses créneaux disponibles et donc optimiser le nombre de rdvs par jour.
</p>

<p style='text-align: justify;'>
Ci-dessous, le diagramme UML que vous pouvez retrouver dans le répertoire resources (<a href="spring-boot-sample-data-jpa-standalone/src/main/resources">spring-boot-sample-data-jpa-standalone/src/main/resources</a>) sous le nom de 'Diagramme UML SG ELC.png'</p>

![](https://github.com/gadreysophie/Spring2020/blob/master/spring-boot-sample-data-jpa-standalone/src/main/resources/Diagramme%20UML%20SG%20ELC.png?raw=true)

## 4 - Structure

<ul style='text-align: justify;'><li>Un dossier 'domain' contenant les classes métiers et qui sont annotées @Entity pour permettre la création d'une base de données relationnelle.</li></ul>

    - Departement
    - Personne (classe abstraite)
    - Professionnel
    - Rdv
    - TypeRdv
    - Utilisateur

<ul style='text-align: justify;'><li>Un dossier 'dao' contenant des interfaces qui permettent de requêter la base de données.</li></ul>

    - DepartementDao
    - PersonneDao
    - ProfessionnelDao
    - RdvDao
    - TypeRdvDao
    - UtilisateurDao

<ul style='text-align: justify;'><li>Un dossier 'rest' contenant les classes ressources permettant d'envoyer des requêtes http qui font le lien entre une page web et la base de données.</li></ul>

    - DepartementResource
    - ProfessionnelResource
    - RdvResource
    - TypeRdvResource
    - UtilisateurResource

<ul style='text-align: justify;'><li>Un dossier 'service' contenant les classes services dans lesquelles des fonctions pour ajouter des fausses données dans la base de données ont été codées. Mais aussi certaines plus complexes comme notamment les méthodes pour générer une liste de créneaux disponibles.</li></ul>

    - DepartementService
    - ProfessionnelService
    - RdvService
    - TypeRdvService
    - UtilisateurService

<ul style='text-align: justify;'><li>Un dossier 'dto' contenant deux classes qui permettent de faire le lien entre nos classes métiers et ressources.</li></ul>

    - CreneauxDispoParProfEtDateEtTypeRdvDto
    - RdvsParProfessionnelEtDateDto

<ul style='text-align: justify;'><li>Un dossier 'web' contenant deux classes qui permettent de générer des pages web.</li></ul>

    - AddUser
    - ListUser

## 5 - Lancement de l'application

<p style='text-align: justify;'>
Dans un premier temps, il faut cloner ce projet en utilisant par exemple la commande 'New Project from Version Control' d'IntelliJ et le lien ci-dessous.
</p>
    
    https://github.com/gadreysophie/Spring2020.git

<p style='text-align: justify;'>
Puis, afin de lancer l'application, il est tout d'abord nécessaire de s'assurer que le port 8084 n'est pas utilisé. 
Ensuite, vous devez reload le maven pour être à jour au niveau des dépendances maven.
Enfin, il faut être connecté au réseau wifi de l'ISTIC ou bien utiliser un vpn comme forticlient si on souhaite s'y connecter depuis un site extérieur.
</p>

<p style='text-align: justify;'>
Si vous souhaitez utiliser un vpn, la passerelle distante à utiliser est la suivante : <a href="istic-vpn.univ-rennes1.fr">istic-vpn.univ-rennes1.fr</a>.
Pour se connecter, il suffit d'utiliser sa paire d'identifiant/mot de passe de l'université de Rennes 1.
</p>

<p style='text-align: justify;'>
Une fois connecté au réseau, il suffit de lancer l'application SpringBoot 'SampleDataJpaApplication' (<a href="spring-boot-sample-data-jpa-standalone/src/main/java/sample/data/jpa/SampleDataJpaApplication.java">spring-boot-sample-data-jpa-standalone/src/main/java/sample/data/jpa/SampleDataJpaApplication.java</a>).
</p>

<p style='text-align: justify;'>
Pour information, on peut accèder au phpMyAdmin de la base de données mysql à l'adresse suivante en utilisant les identifiants ci-dessous : <a href="http://phpmyadmin.istic.univ-rennes1.fr/phpmyadmin/index.php?db=base_sgadrey">http://phpmyadmin.istic.univ-rennes1.fr/phpmyadmin/index.php?db=base_sgadrey</a>.
</p>

    - base = base_sgadrey
    - login = user_sgadrey
    - mdp = puccini

## 6 - Description de l'API Rest

<p style='text-align: justify;'>
Concernant la description de l'API Rest, nous avons choisi d'utiliser swagger. Ainsi, la description de notre API Rest est visible via cette adresse : <a href="http://localhost:8084/swagger-ui.html">http://localhost:8084/swagger-ui.html</a>
</p>

## 7 - Tests via Postman

<p style='text-align: justify;'>
Pour tester notre API Rest, nous avons créé une collection de tests en utilisant Postman.
Cette collection de tests (fichier 'JaxRS TP SG ELC.postman_collection') se trouve dans le répertoire resources (<a href="spring-boot-sample-data-jpa-standalone/src/main/resources">spring-boot-sample-data-jpa-standalone/src/main/resources</a>) et peut être importée directement sur Postman. Si l'application est lancée, la collection peut directement être 'run' entièrement.
</p>

<p style='text-align: justify;'>
Par exemple, voici une requête parmi celles effectuées :
</p>

    http://localhost:8084/user/1

<p style='text-align: justify;'>
et la réponse en JSON :
</p>

    {
        "id": 1,
        "nom": "Monsieur",
        "identifiant": "Mtoto",
        "mdp": "toto2",
        "mail": "toto@univrennes.fr",
        "prenom": "toto"
    }