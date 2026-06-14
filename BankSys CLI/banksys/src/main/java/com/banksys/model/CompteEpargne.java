package com.banksys.model;

// import d'exception

import com.banksys.exception.MontantInvalideException;
import com.banksys.exception.SoldeInsuffisantException;

public class CompteEpargne extends Compte {

    // Attribut taux d'interet

    private double tauxInteret;

    // Constructeur

    CompteEpargne(final String numeroCompte, final double solde, final Client titulaire, final double tauxInteret) {

        super(numeroCompte, solde, titulaire);

        // Verification automatique du taux dans le Setter

        this.setTauxInteret(tauxInteret);

    } 

    // Guetter et Setter

    public double getTauxInteret() {

        return tauxInteret;

    }

    public void setTauxInteret(double tauxInteret) {


        // Le taux d'intèret est souvent entre 1 % et 10 %

        boolean valide = (tauxInteret >= 0.01 && tauxInteret <= 0.1);

        if (!valide) throw new IllegalArgumentException("Taux d'intèret invalide");

        this.tauxInteret = tauxInteret;

    }

    // Méthode de retrait

    @Override
    public void retirer(double montant) throws SoldeInsuffisantException {

        // Erreur si le solde est inferieur au montant

        if (getSolde() < montant) throw new SoldeInsuffisantException(montant, 0);

        // Si le montant est invalide

        if (montant <= 0) throw new MontantInvalideException(montant);

        // Ajouter le solde au montant si tout est correcte

        setSolde(getSolde() + montant);

    }
    
}
