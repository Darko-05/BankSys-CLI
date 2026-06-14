package com.banksys.model;

// import java.util

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.time.format.DateTimeFormatter;

// import de packages nécessaires

import com.banksys.service.ICompte;
import com.banksys.util.ValidationUtils;
import com.banksys.exception.ClientInexistantException;
import com.banksys.exception.MontantInvalideException;
import com.banksys.exception.SoldeInsuffisantException;

// implementation de ICompte pour forcer la redefinition de toute les méthodes chez les classes filles

public abstract class Compte implements ICompte {

    // Attributs d'un Compte bancaire

    private String numeroCompte;
    private Client titulaire;

    private double solde = 0; // initialisée a 0
    private List<Transaction> transactions = new ArrayList<>(); // On initialise avec une liste modifiable vide


    // Constructeur

    Compte(final String numeroCompte, final double solde, final Client titulaire) {

        // Appel de la méthode de validation qui lève une exception si invalide

        ValidationUtils.validerNumeroDeCompte(numeroCompte);

        // Si on arrive ici c'est que c'est valide donc on peut l'assigner

        this.numeroCompte = numeroCompte;


        // Verification automatique grace au setter

        this.setTitulaire(titulaire);

    }

    // Guetters 

    public String getNumeroCompte() {

        return numeroCompte;

    }
    
    public double getSolde() {

        return solde;

    }

    public Client getTitulaire() {

        return titulaire;

    }

    public String getTransactions() {

        // Variable contenant le texte adapté

        String displayTransaction = "";

        // Si il n'y a eu aucune transactions

        if (transactions.isEmpty()) {

            displayTransaction = "Aucune transaction";

        }

        // Parcours de la liste de transactions

        for (Transaction t : transactions) {

            // Format de l'heure voulu

            DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

            // Recuperation de l'heure dans le bon format

            String dateFormatee = t.date().format(format);

            displayTransaction += "- [" + dateFormatee + "] " + t.type() + " | " + t.montant() + "$ | " + t.descipriton() + "\n\t\t\t\t     "; /* Retour a la ligne après ajout de chaque transactions */

        }

        // Renvoie la chaine finale de transaction adjancé par ligne

        return displayTransaction;

    }

    // Setters

    public void setNumeroCompte() {

        // Appel a la méthode qui génère le numero de compte

        String numeroCompte = ValidationUtils.genererNumeroCompte();

        this.numeroCompte = numeroCompte;

    }

    public void setSolde(double solde) {

        this.solde = solde;

    }

    public void setTitulaire(Client titulaire) {

        // On s'assure que sa soit un vraie Client

        if (titulaire == null) throw new ClientInexistantException("Client inexistant");

        this.titulaire = titulaire;

    }

    public void setTransactions(List<Transaction> transactions) {

        // On verifie si la liste est vide ou est null

        if (transactions.isEmpty() || transactions == null) throw new IllegalArgumentException("Liste de transactions invalide");

        // Utilisation de iterator pour supprimer les transactions invalides (Non necessaire mais dans un but pedagogique)

        Iterator<Transaction> it = transactions.iterator();

        while (it.hasNext()) {

            Transaction temp = it.next();

            // Si cette transaction n'est pas de type valide

            if (temp == null) {

                System.out.println("Transaction invalide détectée : " + temp);

                // On le supprime

                it.remove();

                System.out.println("Cette transaction a été supprimée pour ne pas causer une erreur");

            } 

        }

        // Une fois terminer on recupère la liste

        this.transactions = transactions;

    }

    // Méthode du compte

    public void deposer(double montant) {

        // Verification du montant

        if (montant >= Double.MAX_VALUE) throw new IllegalArgumentException("Dépôt non autorisé : montant trop élevé");

        if (montant < 0) throw new MontantInvalideException(montant);

        solde += montant;

    }

    public void ajouterTransaction(Transaction transaction) {

        // On ajoute la transaction

        transactions.add(transaction);

    }

    // Redefinir toString() pour un meilleur affichage

    public String toString() {

        return "Numero de compte : " + numeroCompte + "| Nom du Titulaire : " + titulaire.getNom() + " | Prenom du Titulaire : " + titulaire.getPrenom();

    }

    // Méthode abstraite pour le retrait (forcer la redefinition dans les classes filles en fonction du type de Compte)

    public abstract void retirer(double montant) throws SoldeInsuffisantException;
    
}