package com.banksys.model;

// Classe Client

import java.util.List;
import java.util.ArrayList;

// import

import com.banksys.service.ICompte;
import com.banksys.util.ValidationUtils;
import com.banksys.exception.CompteInexistantException;

public class Client {

    // Attributs du client

    private String idClient;
    private String nom;
    private String prenom;

    // On initialise avec une liste vide pour ajouter après un compte en fonction du choix de l'utilisateur

    private List<ICompte> comptes = new ArrayList<>();

    // Constructeur

    Client(final String idClient, final String nom, final String prenom) {

        // Verification a travers les setters --> Levée d'exception cas de valeurs invalides

        this.setIdClient(idClient);

        this.setNom(nom);

        this.setPrenom(prenom);

    }


    // Guetters 

    public String getNom() {

        return nom;

    }

    public String getPrenom() {

        return prenom;

    }

    public String getIdClient() {

        return idClient;

    }

    public String getComptes() {

        // Variables stockant la liste des comptes

        String listeComptes = "";

        // On affiche aucun compte si le client n'en a pas encore

        if (comptes.isEmpty()) {

            return "\tAucun Compte";

        }
 
        // On parcours la liste des comptes du client

        for (int i = 0; i < comptes.size() ; i++) {

            // On affiche chaque compte en fonction de son type

            if (comptes.get(i) instanceof CompteCourant) {

                listeComptes += "- [" + (i + 1) + "] Compte Courant ( N° " + comptes.get(i).getNumeroCompte() + " ) - Solde : " + comptes.get(i).getSolde() + " $\n\t\t\t\t    "; 

            }

            if (comptes.get(i) instanceof CompteEpargne) {

                listeComptes += "- [" + (i + 1)+ "] Compte Epargne ( N° " + comptes.get(i).getNumeroCompte() + " ) - Solde : " + comptes.get(i).getSolde() + " $\n\t\t\t\t    ";

            }

        }

        return listeComptes;

    } 

    // Setters

    public void setNom(String nom) {

        // Vérification de la validité du nom

        ValidationUtils.validerString(nom, "nom");

        this.nom = nom;

    }

    public void setPrenom(String prenom) {

        // Vérification de la validité du prenom

        ValidationUtils.validerString(prenom, "prenom");

        this.prenom = prenom;

    }
    
    public void setIdClient(String idClient) {

        // Verification du format de l'Id

        ValidationUtils.validerIdClient(idClient);

        this.idClient = idClient;
        
    }

    // Ajouter un compte après choix dans l'interface utilisateur

    public void ajouterCompte(ICompte compte) {

        // Si le compte n'est pas valide

        if (compte == null) throw new CompteInexistantException("Compte inexistant");

        // Si le client a déja un certain nombre de comptes

        if (comptes.size() == 5) throw new IllegalArgumentException("Limite de comptes par client atteint");

        // Ajout

        comptes.add(compte);

    }

    // Affichage correct des infos du Client

    @Override
    public String toString() {

        String infoClient = "ID : " + idClient + "\n\t\t\t\t\t     Nom : " + nom.toUpperCase() + " " + Character.toUpperCase(prenom.charAt(0)) + prenom.substring(1) + "\n";

        return infoClient;

    }

} 