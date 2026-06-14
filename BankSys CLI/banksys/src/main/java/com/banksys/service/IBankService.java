package com.banksys.service;

// import exception

import com.banksys.exception.SoldeInsuffisantException;

// Interface

public interface IBankService {

    // Pour créer un client -> idClient (ValidationUtils), nom et prenom

    public void creerClient(String idClient, String nom, String prenom);

    // Trouver un client -> idClient, nom et prenom pour confirmer

    public void trouverClient(String idClient);

    // Méthode supplémentaire avant la création de compte

    public void searchBeforeFinding(String idClient); 

    // Créer un compte -> numeroCompte (ValidationUtils), solde (0 a l'initialisation) et un Client

    public void creerCompte(String idClient, int type);

    // Trouver un compte -> Numero du compte

    public void trouverCompte(String numeroCompte);

    // Effctuer depot avec numero du compte et le montant

    public void effectuerDepot(String numeroCompte, double montant);

    // Effctuer depot avec numero du compte et le montant

    public void effectuerRetrait(String numeroCompte, double montant);

    // Effectuer virement -> emmeteur, recepteur, titulaire et montant

    public void effectuerVirement(String numeroCompteEmetteur, String numeroCompteBeneficiaire, double montant) throws SoldeInsuffisantException;
    
}
