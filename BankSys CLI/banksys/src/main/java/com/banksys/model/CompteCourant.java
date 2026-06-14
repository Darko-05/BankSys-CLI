package com.banksys.model;

// import des exceptions

import com.banksys.exception.SoldeInsuffisantException;
import com.banksys.exception.MontantInvalideException;

public class CompteCourant extends Compte {

    // Attribut spécifique au Compte courant

    private double decouvertAutoriser;

    // Constructeur

    CompteCourant(final String numeroCompte, final double solde, final Client titulaire, final double decouvertAutoriser) {

        // Liste transactions null car initialisée avec une liste vide

        super(numeroCompte, solde, titulaire);

        // Verification automatique dans le Setter

        this.setDecouvertAutoriser(decouvertAutoriser);

    }

    // Guetter et Setter

    public double getDecouvertAutoriser() {

        return decouvertAutoriser;

    }

    public void setDecouvertAutoriser(double decouvertAutoriser) {

        // On verifie que le decouvert est posotif

        if (decouvertAutoriser < 0) throw new IllegalArgumentException("Le découvert doit etre positif");

        this.decouvertAutoriser = decouvertAutoriser;

    }

    // Méthode de retrait a redefinir

    @Override
    public void retirer(double montant) throws SoldeInsuffisantException {

        // Si le client avait atteint le limite du decouvert autorisée

        if (getSolde() == -decouvertAutoriser) throw new IllegalArgumentException("Veillez rembourser le defaut du compte");

        // Si la somme du montant avec le decouvert est inferieur au montant on bloque le retrait 

        if (montant > ( getSolde() + decouvertAutoriser)) throw new SoldeInsuffisantException(montant, decouvertAutoriser);

        // Si le montant est invalide

        if (montant <= 0) throw new MontantInvalideException(montant);

        // Enlever le montant et le decouvert du solde

        setSolde(getSolde() - montant - decouvertAutoriser);

    }
    
}
