package tpwebservices

import grails.converters.JSON
import grails.converters.XML

class ApiController {

    def book (){
        switch(request.getMethod()){

            case "GET" :
                println 'requete get :'+params
                if(!Livre.get(params.get("id"))) {
                    render(status: 400, text: "livre introuvable")
                    return
                }
                def bookInstance = Livre.get(params.id)
                if(bookInstance){
                    withFormat{
                        json {render bookInstance as JSON}
                        xml {render bookInstance as XML}
                    }
                }
                break

            case "POST" :
                println 'requete post :'+params
                if(!Bibliothèque.get(params.biblio.id)){
                    render(status: 400, text: "bibliotheque introuvable")
                    return
                }
                def bookInstance = new Livre(params)
                if (bookInstance.save(flush:true)){
                    response.status = 201
                    withFormat{
                        json {render bookInstance as JSON}
                        xml {render bookInstance as XML}
                    }
                }else{
                    response.status = 400
                }
                break

            case "DELETE":
                if(!Livre.get(params.id)){
                    render(status: 400, text: "livre introuvable")
                    return
                }
                Livre.get(params.id).delete(flush:true)
                if(!Livre.get(params.id)){
                    response.status = 204
                }
                break

            case "PUT":
                if(!Livre.get(params.id)){
                    render(status: 400, text: "livre introuvable")
                    return
                }
                def livre = Livre.get(params.id)
                if(params.nom)
                    livre.setNom(params.nom)
                if(params.isbn)
                    livre.setIsbn(params.isbn)
                if(params.auteur)
                    livre.setAuteur(params.auteur)
                if(params.parution) {
                    try {
                        livre.setParution(params.parution)
                    }
                    catch (Exception e) {
                        println "!date : "+e.toString()
                        render(status: 400, text: "format date incorrect")
                        return
                    }
                }
                if(params.biblio&&params.biblio.id) {
                    if(!Bibliothèque.get(params.biblio.id)){
                        render(status: 400, text: "bibliotheque introuvable")
                        return
                    }
                    else
                        livre.setBiblio(Bibliothèque.get(params.biblio.id))
                }
                if (livre.save(flush:true)){
                    response.status = 200
                    withFormat{
                        json {render livre as JSON}
                        xml {render livre as XML}
                    }
                }else{
                    response.status = 400
                }
                break

            default:
                response.status = 405
        }
    }

    def library (){
        switch(request.getMethod()){

            case "GET" :
                println 'requete get :'+params
                if(!Bibliothèque.get(params.get("id"))) {
                    render(status: 400, text: "bibliotheque introuvable")
                    return
                }
                def libInstance = Bibliothèque.get(params.id)
                if(libInstance){
                    withFormat{
                        json {render libInstance as JSON}
                        xml {render libInstance as XML}
                    }
                }
                break

            case "POST" :
                println 'requete post :'+params
                def libInstance = new Bibliothèque(params)
                if (libInstance.save(flush:true)){
                    response.status = 201
                    withFormat{
                        json {render libInstance as JSON}
                        xml {render libInstance as XML}
                    }
                }else{
                    response.status = 400
                }
                break

            case "DELETE":
                if(!Bibliothèque.get(params.id)){
                    render(status: 400, text: "bibliotheque introuvable")
                    return
                }
                Bibliothèque.get(params.id).delete(flush:true)
                if(!Bibliothèque.get(params.id)){
                    response.status = 204
                }
                break

            case "PUT":
                println params
                if(!Bibliothèque.get(params.id)){
                    render(status: 400, text: "bibliotheque introuvable")
                    return
                }
                def libInstance = Bibliothèque.get(params.id)
                if(params.nom)
                    libInstance.setNom(params.nom)
                if(params.adr)
                    libInstance.setAdresse(params.adr)
                if(params.année)
                    try {
                        libInstance.setAnnée(params.année.toInteger())
                    }
                    catch(Exception e) {
                        println "!année : "+e.toString()
                        render(status: 400, text: "année incorrecte")
                        return
                    }
                if (libInstance.save(flush:true)){
                    response.status = 200
                    withFormat{
                        json {render libInstance as JSON}
                        xml {render libInstance as XML}
                    }
                }else{
                    response.status = 400
                }
                break

            default:
                response.status = 405
        }
    }
    def libraries () {
        switch (request.getMethod()) {

            case "GET":
                println 'requete get :' + params
                def libInstance = Bibliothèque.getAll()
                if (libInstance) {
                    withFormat {
                        json { render libInstance as JSON }
                        xml { render libInstance as XML }
                    }
                }

            default:
                response.status = 405
        }
    }

    def books () {
        switch (request.getMethod()) {

            case "GET":
                println 'requete get :' + params
                if(!params.id){
                    withFormat {
                        json { render Livre.getAll() as JSON }
                        xml { render Livre.getAll() as XML }
                    }
                }
                if(!Bibliothèque.get(params.id)){
                    render(status: 400, text: "bibliotheque introuvable")
                    return
                }
                withFormat {
                    json { render Bibliothèque.get(params.id).getLivres() as JSON }
                    xml { render Bibliothèque.get(params.id).getLivres() as XML }
                }


            default:
                response.status = 405
        }
    }
}
