package com.banksys.main;

// import util

import java.util.Scanner;
import java.util.InputMismatchException;

// import necessaire

import com.banksys.model.BankService;
import com.banksys.util.AffichageUtils;
import com.banksys.exception.ClientInexistantException;
import com.banksys.exception.CompteInexistantException;
import com.banksys.exception.MontantInvalideException;

public class Lauch {

    // Test

    static void main(String[] args) {

        boolean continuer = true;
        
        BankService test = new BankService();

        Scanner sc = new Scanner(System.in);

        App app = new App(test, sc);

        do {
   
            try {       

                app.LaunchBankSys();

            }

            catch (InputMismatchException e) {

                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();

                System.out.println( "      Erreur : Entrée invalide");

                AffichageUtils.reduceSpacing();

                // Consommer l'erreur

                sc.nextLine();
                
            }

            catch (IllegalArgumentException e) {

                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();

                System.out.println("Erreur : " + e.getMessage());

                AffichageUtils.reduceSpacing();

            }

            catch (MontantInvalideException e) {

                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();

                System.out.println("Erreur : " + e.getMessage());

                AffichageUtils.reduceSpacing();
               
            }

            catch (ClientInexistantException e) {

                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();

                System.out.println("Erreur : " + e.getMessage());

                AffichageUtils.reduceSpacing();

            }

            catch (CompteInexistantException e) {

                AffichageUtils.sautDeLigne();
                AffichageUtils.putSpace();

                System.out.println("Erreur : " + e.getMessage());

                AffichageUtils.reduceSpacing();

            }

            // Si il choisis 8 au debut, on arrète grace a la variable static STOP

            if (app.STOP = true) {

                continuer = false;

            }

            // En cas d'entrée invalide ou fin d'une instruction on relance le menu

            else if (app.STOP == false){

                continuer = app.menuDeFin();

            }

        }

        while (continuer);

    }

}
