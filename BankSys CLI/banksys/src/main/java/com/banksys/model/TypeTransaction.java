package com.banksys.model;

public enum TypeTransaction {
    
    DEPOT("Dépôt reçu"),
    RETRAIT("Retrait effectué"),
    VIREMENT_ENTRANT("Transfert reçu d'un tiers"),
    VIREMENT_SORTANT("Transfert de fonds vers un bénéficiaire"),
    OUVERTURE("Ouverture du compte");

    // Variable pour la description

    private final String description;

    // Constructeur

    private TypeTransaction(final String description) {

        this.description = description;

    }

    // Guetter

    public String getDescription() {

        return description;

    }

}
