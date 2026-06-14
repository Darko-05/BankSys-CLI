package com.banksys.util;

// Classe utilitaire pour gerer l'affichage de menu

public final class AffichageUtils {

    private AffichageUtils () {}

    // Methodes d'affichage 

    public static void afficherBarre() {

        String character = "=";

        System.out.printf("\t\t\t\t\t");

        for (int i = 0; i <= 20; i++) {

            System.out.printf(character + " ");

        }

        System.out.println();

    }

    public static void putSpace() {

        System.out.printf("\t\t\t\t\t     ");

    }

    public static void reduceSpacing() {

        System.out.printf("\t\t\t\t     ");

    }

    public static void sautDeLigne() {

        System.out.println();

    }

    public static void moreSpace() {

        System.out.printf(" ");

    }

    public static void center() {

        System.out.printf("\t\t\t\t  ");

    }

    public static void quitOrContinue() {

        sautDeLigne();
        putSpace();

        System.out.println(Couleur.colorText(Couleur.VIOLET, "1 - Menu Principal") + " || " + Couleur.colorText(Couleur.ROUGE, " 2 - Quitter"));

        sautDeLigne();
        putSpace();

        System.out.print("> ");

    }

    public static void afficherMessageDeFin() {

        sautDeLigne();
        center();

        System.out.println("   " + Texte.modifierTexte(Texte.ITALIQUE, "Merci d'avoir utilisé BankSys CLI. Au revoir !"));

        reduceSpacing();
        sautDeLigne();
        reduceSpacing();

    }

    // -- MENU PRINCIPAL ---

    public static void afficherMenuPrincipale() {

        afficherBarre();
        sautDeLigne();
        putSpace();

        // Méthode pour colorer le texte

        System.out.println(Couleur.colorText(Couleur.CYAN,"== Bienvenue sur BankSys CLI =="));

        sautDeLigne();
        afficherBarre();
        sautDeLigne();
        putSpace();

        System.out.println(Texte.modifierTexte(Texte.SOULIGNE,"Veuillez choisir une option :"));

        sautDeLigne();
        sautDeLigne();
        putSpace();
        moreSpace();

        System.out.println(Couleur.colorText(Couleur.VERT, "--- Gestion des Clients ---"));

        // Afficher les choix

        sautDeLigne();
        reduceSpacing();

        System.out.println("1. Créer un nouveau client");

        reduceSpacing();

        System.out.println("2. Afficher les détails d'un client (et ses comptes)");

        sautDeLigne();
        putSpace();
        moreSpace();

        System.out.println(Couleur.colorText(Couleur.ROUGE, "--- Gestion des Comptes ---"));

        sautDeLigne();
        reduceSpacing();

        System.out.println("3. Ouvrir un nouveau compte (pour un client existant)");

        reduceSpacing();

        System.out.println("4. Afficher l'historique des transactions d'un compte");

        sautDeLigne();
        putSpace();
        moreSpace();

        System.out.println(Couleur.colorText(Couleur.JAUNE, "--- Opérations Bancaires ---"));

        sautDeLigne();
        reduceSpacing();

        System.out.println("5. Effectuer un dépôt");

        reduceSpacing();

        System.out.println("6. Effectuer un retrait");

        reduceSpacing();

        System.out.println("7. Effectuer un virement (de compte à compte)");

        sautDeLigne();
        putSpace();

        System.out.println(Couleur.colorText(Couleur.CYAN, "      --- Système ---"));

        sautDeLigne();
        reduceSpacing();

        System.out.println("8. Quitter l'application");

        System.out.println();

        reduceSpacing();

        System.out.print("Votre choix : ");

    }

}
