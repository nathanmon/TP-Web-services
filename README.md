# TP-Web-services

Projet d'étude sur les services web REST utilisant le framework Grails

Manipulation des tables
- Bibliothèque (String nom, String adresse, int année, static hasMany = [livres:Livre]) 
- Livre (String nom, Date parution, String isbn, String auteur, static belongsTo = [biblio:Bibliothèque])

Une collection de tests Postman se trouve à la racine

Les oppérations dipsonibles sur sont :

## localhost:8080/api/book :
  ### - GET : afficher un livre
    - params :
      - id
    - retour :
      - code 200 si livre trouvé + représentation json/xml (ex: localhost:8080/api/book/1.xml)
    - erreurs :
      - code 400 + "livre introuvable" si mauvais id
  ### - POST : créer un livre
    - params :
      - nom
      - isbn
      - parution (Date ex: 2001-11-11T01:01:01.00Z)
      - auteur
      - biblio.id
    - retour :
      - code 201 si livre créé + représentation
    - erreurs :
      - code 400 + "bibliotheque introuvable" si mauvais biblio.id
      - code 400 si un argument manque ou est incorrect
  ### - PUT : modifier un livre
    - params :
    ' - id
    - params facultatifs :
      - nom
      - isbn
      - parution (Date ex: 2001-11-11T01:01:01.00Z)
      - auteur
      - biblio.id
    - retour :
      - code 200 si livre modifié + représentation
    - erreurs :
      - code 400 + "livre introuvable" si mauvais id
      - code 400 + "bibliotheque introuvable" si mauvais biblio.id
      - code 400 + "format date incorrect" si mauvaise parution
  ### - DELETE : supprimer un livre
    - params :
      - id
    - retour :
      - code 204 si livre supprimé
    - erreurs :
      - code 400 + "livre introuvable" si mauvais id
## localhost:8080/api/library :
  ### - GET : afficher une bibliothèque
    - params :
      - id
    - retour :
      - code 200 si bibliotheque trouvée + représentation json/xml (ex: localhost:8080/api/librabry/1.xml)
    - erreurs :
      - code 400 + "bibliotheque introuvable" si mauvais id
  ### - POST : créer une bibliothèque
    - params :
      - nom
      - année (entier)
      - adresse
    - retour :
      - code 201 si bibliotheque créée + représentation
    - erreurs :
      - code 400 si un argument manque
  ### - PUT : modifier une bibliothèque
    - params :
    ' - id
    - params facultatifs :
      - nom
      - annee (entier)
      - adresse
    - retour :
      - code 200 si bibliotheque modifiée + représentation
    - erreurs :
      - code 400 + "bibliotheque introuvable" si mauvais id
      - code 400 + "année incorrecte" si mauvaise année
  ### - DELETE : supprimer une bibliothèque
    - params :
      - id
    - retour :
      - code 204 si bibliotheque supprimée
    - erreurs :
      - code 400 + "bibliotheque introuvable" si mauvais id
## localhost:8080/api/books :
  ### - GET : afficher tous les livres
    - retour :
      - code 200 + resprésentation json/xml (ex: localhost:8080/api/books.xml)
## localhost:8080/api/libraries :
  ### - GET : afficher toutes les bibliothèques
    - retour :
      - code 200 + resprésentation json/xml (ex: localhost:8080/api/libraries.xml)
## localhost:8080/api/library/$id/books :
  ### - GET : afficher tous les livres d'une bibliothèque
    - params :
      - id de la bibliothèque
    - retour :
      - code 200 + resprésentation json/xml de (ex: localhost:8080/api/books.xml)
    - erreurs :
      - code 400 + "bibliotheque introuvable" si mauvais id
  
