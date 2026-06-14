package com.banksys.util;

// Classe pour colorer les textes

public enum Couleur {

    NOIR("\u001B[30m"),
    BLANC("\u001B[37m"),
    ROUGE("\u001B[31m"),
    VERT("\u001B[32m"),
    JAUNE("\u001B[33m"),
    BLEU("\u001B[34m"),
    VIOLET("\u001B[35m"),
    CYAN("\u001B[36m");
    
    // Attribut codecouleur

    private final String codeCouleur;

    // Constructeur

    private Couleur(final String codeCouleur) {

        this.codeCouleur = codeCouleur;

    }

    // Guetter

    public String getCodeCouleur() {

        return codeCouleur;

    }

    // Méthode pour colorer une portion de texte

    public static String colorText(Couleur couleur , String texte) {

        return couleur.getCodeCouleur() + texte + "\u001B[0m";

    }

}
