package com.banksys.util;

// Classe pour modifier la forme du texte

public enum Texte {

    GRAS("\u001B[1m"),
    ITALIQUE("\u001B[3m"),
    SOULIGNE("\u001B[4m");

    // Attribut code texte

    private final String codeTexte;

    // Constructeur

    private Texte(final String codeTexte) {

        this.codeTexte = codeTexte;

    }

    // Guetter

    public String getCodeTexte() {

        return codeTexte;

    }

    // Méthode pour modifier la forme du texte

    public static String modifierTexte(Texte format, String texte) {

        return format.getCodeTexte() + texte + "\u001B[0m";

    }

    public static void main(String[] args) {
        
        System.out.println(modifierTexte(Texte.ITALIQUE, "Salut"));

    }
    
}
