package com.banksys.util;

// import de LocalTime

import java.time.LocalTime;

public final class ValidationUtils { // Empèche l'heritage

    private ValidationUtils() {} // Empecher la création d'objets

    // --> Classe constante qui peut etre utiliser directement grace a l'import

    // Methode pour la validation des noms / prenoms

    public static void validerString(String chaine, String type) {

        // Ne dois pas etre vide || contenir un chiffre || contenir certains caractères spéciaux || avoir une taille inferieur a 3

        if (chaine == null || chaine.isBlank() || chaine.matches(".*[0-9].*") || chaine.matches(".*[,?;.:/!§<>%$£*µ^¨=+°@_()|`\\[\\]{}~&²ù].*") || chaine.length() < 3) throw new IllegalArgumentException("Veuillez entrer un " + type + " valide");

    }

    // Methode pour valider l'id Client

    public static void validerIdClient(String idClient) {

        // L'id ne doit pas etre null ni contenir de l'espace ni un ou des cractères speciales ni etre de taille inferieur a 12

        if (idClient == null || idClient.contains(" ") || idClient.matches(".*[,?;.:/!§<>%$£*µ^¨=+°@_()|`\\\\[\\\\]{}~&²ù].*") || idClient.length() < 12) throw new IllegalArgumentException("Format ID invalide");

    }

    // Methode pour valider le numero de compte

    public static void validerNumeroDeCompte(String numeroDeCompte) {

        // Ne dois pas avoir une longueur inferieur a 12 || Ne peut pas etre null ou vide || Ne dois pas Contenir des carctères spéciaux || Ne dois pas Contenir des lettres || Ne dois pas contenir de l'espace

        if (numeroDeCompte == null || numeroDeCompte.isBlank() || numeroDeCompte.length() < 12 || numeroDeCompte.matches(".*[,?;.:/!§<>%$£*µ^¨=+°@_()|`\\[\\]{}~&²].*") || numeroDeCompte.matches(".*[a-z].*") || numeroDeCompte.matches(".*[A-Z].*") || numeroDeCompte.contains(" ")) throw new IllegalArgumentException("Numero de compte Invalide");

    }

    // Generer un Id aleatoire sous contrainte

    public static String genererId(String nom, String prenom) {

        validerString(nom, "nom");
        validerString(prenom, "prenom");

        // Variable pour le debut du prenom / nom

        String debutPrenom = null;
        String debutNom = null;

        // Lorsque le nom de l'utilisateur a 3 caractères on recupère seulement les deux premiers (Les chaines de caractères auront toujours au moins 3 caractères grace a la méthode validerString)

        if (nom.length() == 3) {

            debutNom = nom.substring(0, 2);
    
        }

        // Sinon on récupère toujours les 3 premiers

        else {

            debutNom = nom.substring(0,3);

        }

        // Meme logique pour le prenom

        if (prenom.length() == 3) {

            debutPrenom = prenom.substring(0, 2);
    
        }

        // Sinon on récupère toujours les 3 premiers

        else {

            debutPrenom = prenom.substring(0,3);

        }

        // On genere un nombre aléatoire entre 100 et +-999

        int nombreAleatoire0 = (int) ((Math.random() * 909) + 100);

        // On genere un nombre aléatoire entre 10 et 99

        int nombreAleatoire1 = (int) ((Math.random() * 90) + 10);

        // Je recupère l'heure actuel sous forme de chaine de caractères

        String temp = String.valueOf(LocalTime.now());

        // Je supprime les ":" et "." dans l'heure et je récupère 3 chiffres aléatoires

        String nombreAleatoire2 = temp.replaceAll("[:.]", "").substring(1,4);

        // On crée l'id a partir des infos obtenus et on supprime les espaces dans les noms au cas ou

        String idClient = debutNom.toUpperCase() + nombreAleatoire0 + debutPrenom.toUpperCase() + nombreAleatoire1 + nombreAleatoire2;

        // On retourne l'id

        return idClient;

    }

    // Méthode pour generer un numero de Compte

    public static String genererNumeroCompte() {

        int nombreAleatoire0;
        int nombreAleatoire1;

        // On génère un nombre aléatoire entre 1000 et 1998 (4 premiers chiffres)

        nombreAleatoire0 = (int) ((Math.random() * 1009) + 1000);

        // Nombre entre 10.000.000 et 19.899.999 (8 chiffres suivant)

        nombreAleatoire1 = (int) ((Math.random() * 9999999) + 10000000);

        // Je recupere les nombres aléatoire sous forme de String

        String temp0 = String.valueOf(nombreAleatoire0);

        String temp1 = String.valueOf(nombreAleatoire1);

        // On génère un nombre entre O et 9

        int nbr = (int) (Math.random() * 10);

        // Je remplace le 1 du nombreAleatoire1 par un chiffre entre 0 et 9 ('1'.000.000.0 - 19.899.999)

        temp1 =  temp1.substring(0,1) // premier caractère du nombrealéatoire1
            
            .replace("1", // Dont je remplace le 1 par
            
            String.valueOf(nbr)) + // Une valeur entre 0 et 10 (nbr)
            
            temp1.substring(1); // auquel j'ajoute les chiffres restantes

        String numeroCompte = temp0 + temp1;

        return numeroCompte;

    }

}