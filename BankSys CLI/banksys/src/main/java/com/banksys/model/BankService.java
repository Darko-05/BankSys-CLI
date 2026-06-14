package com.banksys.model;

// package util neessaire

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.LinkedHashSet;
import java.time.LocalDateTime;

// import necessaire

import com.banksys.service.IBankService;
import com.banksys.util.AffichageUtils;
import com.banksys.util.Couleur;
import com.banksys.util.Texte;
import com.banksys.util.ValidationUtils;
import com.banksys.exception.SoldeInsuffisantException;
import com.banksys.exception.ClientInexistantException;
import com.banksys.exception.CompteInexistantException;

public class BankService implements IBankService {

    // Solde de base

    private final int SOLDE = 0;

    // Decouvert de base a 120$

    private final double DECOUVERT_AUTORISER = 120;

    // Taux d'interet de base a 1% 

    private final double TAUX_INTERET = 0.01;

    // Attributs de BankService

    /* Initialisée avec des listes vides */

    private Map<String, Client> clients = new LinkedHashMap<>();
    private Map<String, Compte> comptes = new LinkedHashMap<>();
    private Set<String> numerosComptesUtilises = new LinkedHashSet<>();

    // Constructeur vide 

    public BankService() {}

    
    // Guetters

    public void getClients() {

        for (Map.Entry<String, Client> client : clients.entrySet()) {

            System.out.println(client.getKey() + client.getValue().toString());

        }

    }

    public void getComptes() {

        for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

            System.out.println(compte.getKey() + compte.getValue().toString());

        }

    }

    public void getNumeroComptesUtilises() {

        for (String n : numerosComptesUtilises) {

            System.out.println(n);

        }

    }

    // Méthode pour créer un nouveau Client

    public void creerClient(String idClient, String nom, String prenom) {
        
        // Creer le client

        Client nouveauClient = new Client(idClient, nom, prenom);

        // Relation de composition -> Ajout du client dans la liste des clients

        clients.put(idClient, nouveauClient);

    }

    // Méthode pour trouver un Client 

    public void trouverClient(String idClient) {

        System.out.println();

        // On lance une exception si l'id Client n'est pas contenue dans le systeme

        if (!clients.containsKey(idClient)) throw new ClientInexistantException("Aucun client trouvé avec l'ID '" + idClient + "'.");

        // Sinon on parcours la collection pour ressortir les infos clients

        for (Map.Entry<String, Client> client : clients.entrySet()) {

            // Une fois trouvée on l'affiche

            if (client.getValue().getIdClient().equals(idClient)) {

                AffichageUtils.putSpace();
                System.out.println("--- Fiche Client ---");
                AffichageUtils.sautDeLigne();

                AffichageUtils.putSpace();

                // Eviter toString directement car la Map a son format de toString prédéfinis

                System.out.println(client.getValue());

                AffichageUtils.putSpace();
                System.out.println("Comptes : ");
 
                // Affichage des informations du compte

                AffichageUtils.sautDeLigne();
                AffichageUtils.reduceSpacing();

                /* Méthode qui affiche la liste des comptes */ 
                System.out.println(client.getValue().getComptes()); 

            }

            // Puis on arrete la boucle

            return;

        }
        
    }   

    // Méthode pour créer un compte

    public void searchBeforeFinding(String idClient) {

        System.out.println("... Recherche du client ...");

        // 1 - Recherche du Client
        // On lance une exception si le client n'est pas trouvée

        if (!clients.containsKey(idClient)) throw new ClientInexistantException("Aucun client trouvé avec l'ID '" + idClient + "'.");

        // Si le le client existe on parcours la liste

        for (Map.Entry<String, Client> client : clients.entrySet()) {

            // Et une fois trouvée on affiche son nom et son prenom sous le format (Nom Prenom)

            if (client.getValue().getIdClient().equals(idClient)) {


                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();
                AffichageUtils.moreSpace();
                System.out.println("Client " + Character.toUpperCase(client.getValue().getNom().charAt(0)) + client.getValue().getNom().substring(1) + " " + Character.toUpperCase(client.getValue().getPrenom().charAt(0)) + client.getValue().getPrenom().substring(1) + " trouvé");

            }

            // Quitter la fonction

            return;
        
        }
    
    }

    public void creerCompte(String idClient, int type) {

        // Variable qui va recuperer le client associée a l'id Client

        Client titulaire = null;

        // Variable associée au compte

        Compte accountOpening = null;

        // Reverification

        if (!clients.containsKey(idClient)) throw new ClientInexistantException("Aucun client trouvé avec l'ID '" + idClient + "'.");

        // Si le le client existe on parcours la liste

        for (Map.Entry<String, Client> client : clients.entrySet()) {

            // On recupère le client associée

            if (client.getValue().getIdClient().equals(idClient)) {

                titulaire = client.getValue();

            }

            // Stopper la boucle

            break;
        
        }

        System.out.println(Couleur.colorText(Couleur.VERT, "     ... Génération du numéro de compte ..."));
        AffichageUtils.sautDeLigne();

        // Variable du numero de compte

        String numeroCompte;

        // 1- Compte courant / 2- Compte epargne

        if (type == 1) {

            // On genere un nouveau numero tant qu'il existe déja

            do {

                numeroCompte = ValidationUtils.genererNumeroCompte();

            }
            
            while (numerosComptesUtilises.contains(numeroCompte)) ;

            CompteCourant nouveauCompteCourant = new CompteCourant(numeroCompte, SOLDE, titulaire, DECOUVERT_AUTORISER);

            // Ajouter le compte au comptes du client

            titulaire.ajouterCompte(nouveauCompteCourant);

            // Ajouter le nouveau compte a la liste de comptes

            comptes.put(numeroCompte, nouveauCompteCourant);

            // Ajouter le nouveau numero de compte a la liste de numero de compte

            numerosComptesUtilises.add(numeroCompte);

            // Créer une nouvelle transaction associée a l'ouverture du compte avant affichage du message
    
            // Parcourir les comptes

            for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

                // On recupère le client associée

                if (compte.getValue().getTitulaire().equals(titulaire)) {

                    accountOpening = compte.getValue();

                }

                // Stopper la boucle

                break;
        
            }

            // Ajouter une transaction d'ouverture de compte

            accountOpening.ajouterTransaction(new Transaction(LocalDateTime.now(), TypeTransaction.OUVERTURE, SOLDE, TypeTransaction.OUVERTURE.getDescription()));

            // Affichage du message de succes

            System.out.println("\t\t\t\tSUCCES : Compte courant ( N° " + numeroCompte + " ) ouvert pour " + Character.toUpperCase(titulaire.getNom().charAt(0)) + titulaire.getNom().substring(1) + " " + Character.toUpperCase(titulaire.getPrenom().charAt(0)) + titulaire.getPrenom().substring(1) + ".");

        }

        if (type == 2) {

            // On genere un nouveau numero tant qu'il existe déja

            do {

                numeroCompte = ValidationUtils.genererNumeroCompte();

            }
            
            while (numerosComptesUtilises.contains(numeroCompte)) ;

            CompteEpargne nouveauCompteEpargne = new CompteEpargne(numeroCompte, SOLDE, titulaire, TAUX_INTERET);

            // Ajouter aux comptes du client

            titulaire.ajouterCompte(nouveauCompteEpargne);

            // Ajouter le nouveau compte a la liste de comptes

            comptes.put(numeroCompte, nouveauCompteEpargne);

            // Ajouter le numero de compte a la liste de numero

            numerosComptesUtilises.add(numeroCompte);

            // Créer une nouvelle transaction associée a l'ouverture du compte avant affichage du message
    
            // Parcourir les comptes

            for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

                // On recupère le client associée

                if (compte.getValue().getTitulaire().equals(titulaire)) {

                    accountOpening = compte.getValue();

                }

                // Stopper la boucle

                break;
        
            }

            // Ajouter une transaction d'ouverture de compte

            accountOpening.ajouterTransaction(new Transaction(LocalDateTime.now(), TypeTransaction.OUVERTURE, SOLDE, TypeTransaction.OUVERTURE.getDescription()));

            // Affichage du message de succes

            System.out.println("\t\t\t\tSUCCES : Compte Epargne ( N° " + numeroCompte + " ) ouvert pour " + Character.toUpperCase(titulaire.getNom().charAt(0)) + titulaire.getNom().substring(1) + " " + Character.toUpperCase(titulaire.getPrenom().charAt(0)) + titulaire.getPrenom().substring(1) + ".");

        }

    }

    // Méthode pour trouver un compte
    
    public void trouverCompte(String numeroCompte) {

        // Variable representant le compte associée au numero de compte

        Compte wantToFindIt = null;

        System.out.println(Couleur.colorText(Couleur.NOIR, Texte.modifierTexte(Texte.SOULIGNE, "... Recherche du compte ...")));

        // 1 - Verifier que le numero de compte existe et associée 

        AffichageUtils.sautDeLigne();
        AffichageUtils.putSpace();

        if (!comptes.containsKey(numeroCompte)) throw new CompteInexistantException("Aucun compte trouvée avec numero : [" + numeroCompte + "]." );

        for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

            // On cherche le compte parmis la liste de comptes

            if (compte.getKey().equals(numeroCompte)) {

                // Puis on l'affiche

                System.out.println("--- Historique du Compte (" + compte.getKey() + ") ---");

                // On récupère le compte

                wantToFindIt = compte.getValue();

                // On sort de la boucle

                break;

            }

        }

        // Afficher les transactions du compte et le solde actuel

        AffichageUtils.sautDeLigne();
        AffichageUtils.reduceSpacing();
        System.out.println(wantToFindIt.getTransactions());

        AffichageUtils.sautDeLigne();
        AffichageUtils.putSpace();
        System.out.println("--- Solde actuel : " + wantToFindIt.getSolde() + "$ ---");

    }

    // Méthode pour effectuer depot

    public void effectuerDepot(String numeroCompte, double montant) {

        // Variable representant le compte

        Compte accountToMakeADeposit = null;

        // Verifier numero de compte

        if (!comptes.containsKey(numeroCompte)) throw new CompteInexistantException("Aucun compte trouvée avec numero : [" + numeroCompte + "]." );

        System.out.println("... Opération en cours ...");

        for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

            // On cherche le compte parmis la liste de comptes

            if (compte.getKey().equals(numeroCompte)) {

                // On récupère le compte

                accountToMakeADeposit = compte.getValue();

                // On sort de la boucle

                break;

            }

        }

        // Faire le dépot

        accountToMakeADeposit.deposer(montant);

        // Ajouter une transaction

        accountToMakeADeposit.ajouterTransaction(new Transaction(LocalDateTime.now(), TypeTransaction.DEPOT, montant, TypeTransaction.DEPOT.getDescription()));

        AffichageUtils.sautDeLigne();
        AffichageUtils.reduceSpacing();
        
        System.out.println("SUCCÈS : Dépôt de " + montant + " $ effectué. Nouveau solde : " + accountToMakeADeposit.getSolde() + " $.");

    }

    // Méthode pour effectuer depot

    public void effectuerRetrait(String numeroCompte, double montant) {

        System.out.println("... Opération en cours ...");

        // Variable representant le compte

        Compte accountToDebit = null;

        // Verifier numero de compte

        if (!comptes.containsKey(numeroCompte)) throw new CompteInexistantException("Aucun compte trouvée avec numero : [" + numeroCompte + "]." );

        for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

            // On cherche le compte parmis la liste de comptes

            if (compte.getKey().equals(numeroCompte)) {

                // On récupère le compte

                accountToDebit = compte.getValue();

                // On sort de la boucle

                break;

            }

        }

        // Faire le dépot

        try {

            accountToDebit.retirer(montant);
    
        }

        catch (SoldeInsuffisantException e) {

            AffichageUtils.sautDeLigne();
            System.out.println(e.getMessage());

        }

        // Ajouter une transaction

        accountToDebit.ajouterTransaction(new Transaction(LocalDateTime.now(), TypeTransaction.RETRAIT, montant, TypeTransaction.RETRAIT.getDescription()));

        AffichageUtils.sautDeLigne();
        AffichageUtils.reduceSpacing();
        
        System.out.println("SUCCÈS : Retrait de " + montant + " $ effectué. Nouveau solde : " + accountToDebit.getSolde() + " $.");

    }

    // Méthode pour effectuer un virement

    public void effectuerVirement(String numeroCompteEmetteur, String numeroCompteBeneficiaire, double montant) throws SoldeInsuffisantException {

        System.out.println(Couleur.colorText(Couleur.BLEU, "... Opération en cours ..."));

        // Vérification des numeros de comptes

        if (!comptes.containsKey(numeroCompteEmetteur) ) throw new CompteInexistantException("Aucun compte trouvée avec numero : [" + numeroCompteEmetteur + "]." );

        if (!comptes.containsKey(numeroCompteBeneficiaire) ) throw new CompteInexistantException("Aucun compte trouvée avec numero : [" + numeroCompteBeneficiaire + "]." );

        // On initialise a null pour faciliter la reconnaissance d'un compte non trouvée

        Compte compteEmetteur = null;
        Compte compteBeneficiaire = null;

        for (Map.Entry<String, Compte> compte : comptes.entrySet()) {

            // On recupere le compte emetteur et le compte beneficiaire

            if (numeroCompteEmetteur.equals(compte.getValue().getNumeroCompte())) {

                compteEmetteur = compte.getValue();

            }

            if (numeroCompteBeneficiaire.equals(compte.getValue().getNumeroCompte())) {

                compteBeneficiaire = compte.getValue();

            }

            // Une fois les deux comptes retrouvés, on quitte la boucle

            if (compteEmetteur != null && compteBeneficiaire != null) {

                break;

            }  
        
        }
        
        AffichageUtils.sautDeLigne();
        AffichageUtils.reduceSpacing();

        // 1 - Retirer le montant du compte emetteur

        compteEmetteur.retirer(montant);

        // 2 - Ajouter une nouvelle transaction au compte emetteur

        compteEmetteur.ajouterTransaction(new Transaction(/* La date et l'heure de la transaction */ LocalDateTime.now(), TypeTransaction.VIREMENT_SORTANT, montant, TypeTransaction.VIREMENT_SORTANT.getDescription()));

        // 3 - Ajouter le montant au compte du beneficiare

        compteBeneficiaire.deposer(montant);

        // 4 - Ajouter une nouvelle transaction au compte beneficiaire

        compteBeneficiaire.ajouterTransaction(new Transaction(LocalDateTime.now(), TypeTransaction.VIREMENT_ENTRANT, montant, TypeTransaction.VIREMENT_ENTRANT.getDescription()));

        // 5 - Afficher un message qui confirme la transaction

        AffichageUtils.sautDeLigne();
        AffichageUtils.reduceSpacing();
        System.out.println(Couleur.colorText(Couleur.VERT, Texte.modifierTexte(Texte.ITALIQUE, "SUCCÈS : Virement de " + montant + " $ de " + compteEmetteur.getNumeroCompte() + " vers " + compteBeneficiaire.getNumeroCompte() + " effectuée.")));

    }
    
} 