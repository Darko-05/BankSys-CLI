package com.banksys.model;

// import de la biliothèque de gestion des dates

import java.time.LocalDateTime;

// import de l'exception

import com.banksys.exception.MontantInvalideException;

public record Transaction(LocalDateTime date, TypeTransaction type, double montant, String descipriton) {
    
    public Transaction {

        // Utilisation de instanceof pour verifier le type de l'objet

        if (date == null) throw new IllegalArgumentException("Date Invalide");

        // Levée d'exception si montant invalide

        if (montant < 0) throw new MontantInvalideException(montant);

    }

}