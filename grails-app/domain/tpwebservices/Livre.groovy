package tpwebservices

class Livre {

    String nom
    Date parution
    String isbn
    String auteur

    static belongsTo = [biblio:Bibliothèque]

    static constraints = {
        nom blank:false
        parution nullable:false
        isbn null:false
        auteur blank:false
    }

}
