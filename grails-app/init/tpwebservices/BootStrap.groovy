package tpwebservices

class BootStrap {

    def init = { servletContext ->

        new Bibliothèque(nom:"biblio1", adresse:"42 rue de Dracofeu", année:2010)
                .addToLivres(new Livre(nom:"Tintin et Michoue", auteur:"inconnu", parution:new Date(2016-1900), isbn:"001"))
                .addToLivres(new Livre(nom:"Sacha et Pikachu prennent le thé", auteur:"Baudelaire", parution:new Date(1860-1900), isbn:"002"))
                .addToLivres(new Livre(nom:"Les avantages de Grails", auteur:"Rimbaud", parution:new Date(1880-1900), isbn:"003"))
                .save(flush:true, failOnError:true)






    }
    def destroy = {
    }
}
