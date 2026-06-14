package com.banksys.main;

// util necessaire

import java.util.Scanner;

// package necessaire

import com.banksys.service.IBankService;
import com.banksys.util.AffichageUtils;
import com.banksys.util.Couleur;
import com.banksys.util.Texte;
import com.banksys.util.ValidationUtils;
import com.banksys.exception.SoldeInsuffisantException;

// Classe de gestion du programme

public class App {

    // Constante pour stopper le programme

    public boolean STOP = false;

    // Attributs 

    private IBankService bankService;
    private Scanner scanner;

    // Constructeur

    protected App(final IBankService bankService, final Scanner scanner) {

        if (bankService == null) throw new IllegalArgumentException("Service not initialized");

        if (scanner == null) throw new IllegalArgumentException("Scanner not initialized");

        this.bankService = bankService;
        this.scanner = scanner;

    }

    // Launch method

    public void LaunchBankSys() {

        // Variable de choix dans le menu

        int choix;

        // Variables intervenant dans la création d'un client
        String nom;
        String prenom;
        String idClient;

        // Variables pour la recherche d'un client


        boolean continuer = true;

        do {

            // Affichage du menu principale

            AffichageUtils.afficherMenuPrincipale();

            // Je récupère le choix (1 - 8)

            choix = scanner.nextInt();

            // Vider le buffer

            scanner.nextLine();

            // Vérification de l'entrée

            if (choix < 1 || choix > 8) throw new IllegalArgumentException("Choix Invalide !");

            // Gestion avec switch

            switch (choix) {

                // 1 - Création d'un nouveau client

                case 1 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();

                    System.out.println(Texte.modifierTexte(Texte.GRAS, "Entrez le nom du client :"));

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    // Récuperer le nom

                    nom = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();

                    System.out.println(Texte.modifierTexte(Texte.GRAS, "Entrez le prénom du client :"));

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    // Recuperer le prenom

                    prenom = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    System.out.println(Couleur.colorText(Couleur.VERT, "... Génération de l'ID client ..."));

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();

                    // Generer l'id Client

                    idClient = ValidationUtils.genererId(nom, prenom);

                    // Enregistrer un nouveau client dans le systeme de la banque

                    bankService.creerClient(idClient, nom, prenom);

                    // Affichage d'un message de confirmation montrant les informations du client

                    System.out.println("SUCCES : Client " + nom.toUpperCase() + " " + Character.toUpperCase(prenom.charAt(0)) + prenom.substring(1).toLowerCase() + " (ID : " + idClient + ") crée.");
                    
                    // Fin

                    continuer = menuDeFin();

                }

                case 2 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez l'ID du client :");
                    AffichageUtils.sautDeLigne();

                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String clientToFind = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    System.out.println("... Recherche du client ...");

                    AffichageUtils.reduceSpacing();

                    // Appel de la méthode de recherche du client

                    bankService.trouverClient(clientToFind);

                    // Fin

                    continuer = menuDeFin();

                }

                case 3 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez l'ID du client pour lequel ouvrir un compte : ");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String idForNewAccount = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();
                    
                    bankService.searchBeforeFinding(idForNewAccount);

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Quel type de compte souhaitez-vous ouvrir ?");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();
                    System.out.println("1. Compte Courant\n\t\t\t\t\t     2. Compte Epargne");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Votre choix : ");
                    AffichageUtils.sautDeLigne();

                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    int compteChoice = scanner.nextInt();

                    // Vider le buffer

                    scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();

                    bankService.creerCompte(idForNewAccount, compteChoice);

                    // Fin

                    continuer = menuDeFin();

                }

                case 4 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le numéro du compte :");
                    AffichageUtils.sautDeLigne();

                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String searchAccount = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    bankService.trouverCompte(searchAccount);

                    // Fin

                    continuer = menuDeFin();

                }

                case 5 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le numéro du compte à créditer :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String numberAccountTomakeADeposit = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le montant à déposer :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    double amountToDeposit = scanner.nextDouble();

                    // Vider le buffer

                    scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    bankService.effectuerDepot(numberAccountTomakeADeposit, amountToDeposit);

                    // Fin 

                    continuer = menuDeFin();

                }

                case 6 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le numéro du compte à débiter :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String numberAccountToDebit = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le montant à retirer :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    double amountToDebit = scanner.nextDouble();

                    // Vider le buffer

                    scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    bankService.effectuerRetrait(numberAccountToDebit, amountToDebit);

                    // Fin 

                    continuer = menuDeFin();

                }

                case 7 -> {

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le numéro du compte SOURCE (débit) :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String accountToDebit = scanner.nextLine();

                    
                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le numéro du DESTINATAIRE (crédit) :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    String accountToCredit = scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.println("Entrez le montant à virer :");

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.reduceSpacing();
                    System.out.print("> ");

                    double amount = scanner.nextDouble();

                    // Vider le buffer

                    scanner.nextLine();

                    AffichageUtils.sautDeLigne();
                    AffichageUtils.putSpace();

                    try {

                        bankService.effectuerVirement(accountToDebit, accountToCredit, amount);

                    }

                    catch (SoldeInsuffisantException e) {

                        System.out.println(e.getMessage());

                    }

                    // Fin

                    continuer = menuDeFin();

                }

                case 8 -> { 
                    
                    AffichageUtils.afficherMessageDeFin();

                    continuer = false;

                    STOP = true;
                
                }

                default -> continuer = false;

            }

        }

        while (continuer);

    }

    public boolean menuDeFin() {
 
        // Revenir au Menu || Quitter

        AffichageUtils.sautDeLigne();
        AffichageUtils.quitOrContinue();

        int anotherChoice = scanner.nextInt();

        scanner.nextLine();

        // Verifier l'entrée

        if (anotherChoice < 1 || anotherChoice > 2) throw new IllegalArgumentException("Choix Invalide !");

        // 1 - Redemarrer le systeme || 2 - Quitter (continuer = false)

        if (anotherChoice == 1) {

            return true;

        }

        else {

            AffichageUtils.afficherMessageDeFin();

            return false;

        }

    }

}