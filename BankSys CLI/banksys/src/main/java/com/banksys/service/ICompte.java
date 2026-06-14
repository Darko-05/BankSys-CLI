package com.banksys.service;

import com.banksys.exception.SoldeInsuffisantException;

// import de client

import com.banksys.model.Client;

// Interface ICompte

public interface ICompte {

    // Méthodes a implementer

    public void deposer(double montant);

    public void retirer(double montant) throws SoldeInsuffisantException;

    public double getSolde();

    public String getNumeroCompte();

    public Client getTitulaire();

    public String getTransactions();

    @Override
    public String toString();

}