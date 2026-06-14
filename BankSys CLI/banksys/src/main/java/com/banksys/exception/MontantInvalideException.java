package com.banksys.exception;

public class MontantInvalideException extends RuntimeException {

    public MontantInvalideException(final double montant) {

        super("Montant invalide - " + montant + "$");

    }
    
}
