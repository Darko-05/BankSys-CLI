package com.banksys.exception;

public class SoldeInsuffisantException extends Exception {

    public SoldeInsuffisantException(double solde, double decouvert) {

        // Message pour solde invalide

        super("Solde Invalide - " + solde + "$ | Decouvert Autoriser : " + decouvert + "$");

    }
    
}
