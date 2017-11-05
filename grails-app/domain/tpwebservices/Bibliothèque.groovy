package tpwebservices

class Bibliothèque {

    String nom
    String adresse
    int année
    static hasMany = [livres:Livre]

    static constraints = {
        nom blank:false
        adresse blank:false
        année nullable:false
    }

}
