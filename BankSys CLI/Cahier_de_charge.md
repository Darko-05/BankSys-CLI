Projet "BankSys CLI" (Niveau Boss)

1.  Introduction et Contexte
   Le projet "BankSys CLI" vise à créer un système de gestion bancaire en ligne de commande. Ce n'est pas un simple "hello world", mais une application robuste, "Niveau Boss", qui simule les interactions fondamentales d'une banque : gestion des clients, gestion de différents types de comptes, et transactions.

L'objectif principal est de servir de projet intégrateur pour valider la maîtrise d'un large éventail de concepts Java, de la POO de base aux principes de conception avancés comme SOLID.

2. Objectifs Pédagogiques
   Ce projet doit explicitement démontrer la compréhension et l'application des concepts suivants :

Principes de base POO : Classe, Héritage, Abstraction, Polymorphisme.

Relations entre Objets : Association, Agrégation, Composition.

Principes de Conception : SOLID (en mettant l'accent sur la Responsabilité Unique, l'Ouvert/Fermé et l'Inversion de Dépendance - DPI).

Fonctionnalités Java Modernes : Interface, Enum, Record.

Structures de Données : List, Map, Set, et Arrays (pour des usages spécifiques).

Gestion des Flux : Entrées/Sorties (via Scanner et System.out), Structures conditionnelles (if/else), Boucles (while, for), et switch.

Manipulation de Données : String, StringBuilder, Iterator.

Robustesse : Gestion des EXCEPTIONS (standards et personnalisées).

3. Architecture et Modèle de Données

A. Entités Principales (Classes, Records, Enums)
Client (Classe)

Attributs : idClient (String), nom (String), prenom (String), comptes (List<ICompte>)

Relation : Un Client possède des comptes. C'est une Agrégation de ICompte.

Transaction (Record)

Utilisez un record pour cette structure de données immuable.

Attributs : (LocalDateTime date, TypeTransaction type, double montant, String description)

TypeTransaction (Enum)

Valeurs : DEPOT, RETRAIT, VIREMENT_ENTRANT, VIREMENT_SORTANT, OUVERTURE.

B. Hiérarchie des Comptes (Héritage, Abstraction, Polymorphisme)
ICompte (Interface)

Définit le contrat public : deposer(double montant), retirer(double montant), getSolde(), getNumeroCompte(), getTitulaire(), getTransactions().

Permet le Polymorphisme.

Compte (Classe Abstraite)

Implémente ICompte.

Fournit l'état et le comportement communs.

Attributs : numeroCompte (String), solde (double), titulaire (Client), transactions (List<Transaction>).

Relation : Un Compte est composé de Transaction. C'est une Composition.

Méthode abstraite : public abstract void retirer(double montant) throws SoldeInsuffisantException;

Démontre : Héritage, Abstraction.

CompteCourant (Classe)

Hérite de Compte.

Attribut spécifique : decouvertAutorise (double).

Implémente retirer() en tenant compte du découvert.

CompteEpargne (Classe)

Hérite de Compte.

Attribut spécifique : tauxInteret (double).

Implémente retirer() (sans découvert, solde >= montant).

C. Services et Logique Métier (SOLID, DPI, Structures de Données)
IBankService (Interface)

Définit le contrat pour les opérations métier.

Méthodes : creerClient(...), trouverClient(...), creerCompte(...), trouverCompte(...), effectuerVirement(...).

Objectif : Inversion de Dépendance (DPI).

BankService (Classe)

Implémente IBankService.

Single Responsibility Principle (SRP) : Gère la logique métier, pas la console.

Attributs : clients (Map<String, Client>), comptes (Map<String, Compte>), numerosComptesUtilises (Set<String>).

Map : Permet une recherche O(1) rapide d'un client ou d'un compte par son ID.

Set : Garantit l'unicité des numéros de compte.

Relation : A une Association avec Client et Compte.

D. Point d'Entrée et Exceptions
Application (Classe)

Contient la méthode main().

Attribut : private final IBankService bankService; (C'est le DPI).

Attribut : private final Scanner scanner; (Entrée/Sortie).

Gère le menu principal (boucle while, switch).

Gère toutes les Entrées/Sorties et les try-catch.

Exceptions Personnalisées

SoldeInsuffisantException (checked, extends Exception)

CompteInexistantException (unchecked, extends RuntimeException)

ClientInexistantException (unchecked, extends RuntimeException)

MontantInvalideException (unchecked, extends RuntimeException)

4. Exigences Techniques Spécifiques

POO (Classe, Héritage, Abstraction, Polymorphisme) : Couvert par la hiérarchie ICompte.

SOLID & DPI : Couvert par la séparation Application (I/O) et BankService (Métier) via IBankService.

Interface, Enum, Record : ICompte, TypeTransaction, Transaction.

Association, Composition, Agrégation :

Composition : Compte et sa List<Transaction>.

Agrégation : Client et sa List<ICompte>.

Association : BankService et les Map de Client/Compte.

Structures de Données : List (transactions, comptes client), Map (gestion des clients/comptes), Set (unicité numéros).

I/O, Conditionnels, Boucles, Switch : Gérés dans Application.

String, StringBuilder, Iterator :

StringBuilder : À utiliser pour générer l'historique des transactions (Option 4).

Iterator : À utiliser explicitement pour parcourir l'historique (Option 4).

Exceptions : Gestion centrale dans Application des exceptions métier (SoldeInsuffisant...) et d'entrée (InputMismatchException).

5. Interface Utilisateur (Menus Interactifs CLI)

Ceci est le cœur de la classe Application. Les > indiquent une saisie de l'utilisateur.

A. Menu Principal
=====================================
==     Bienvenue sur BankSys CLI     ==
=====================================
Veuillez choisir une option :

--- Gestion des Clients ---
1. Créer un nouveau client
2. Afficher les détails d'un client (et ses comptes)

--- Gestion des Comptes ---
3. Ouvrir un nouveau compte (pour un client existant)
4. Afficher l'historique des transactions d'un compte

--- Opérations Bancaires ---
5. Effectuer un dépôt
6. Effectuer un retrait
7. Effectuer un virement (de compte à compte)

--- Système ---
8. Quitter l'application

Votre choix :
B. Flux des Sous-Menus
Voici le comportement attendu pour chaque option.

[Option 1: Créer un nouveau client]

Entrez le nom du client :
> Dupont
Entrez le prénom du client :
> Martin
... Génération de l'ID client ...
✅ SUCCÈS : Client 'Martin Dupont' (ID: CLT-78A2B) créé.
(Retour au menu principal)
[Option 2: Afficher les détails d'un client]

Cas Nominal :

Entrez l'ID du client :
> CLT-78A2B
... Recherche du client ...
--- Fiche Client ---
ID: CLT-78A2B
Nom: Martin Dupont
Comptes :
- [1] Compte Courant (N° 00C-111) - Solde: 450.00 €
- [2] Compte Épargne (N° 00E-222) - Solde: 1200.00 €
  (Retour au menu principal)
  Cas d'Erreur (Exception) :

Entrez l'ID du client :
> CLT-999
... Recherche du client ...
❌ ERREUR : Aucun client trouvé avec l'ID 'CLT-999'.
(Gestion d'une 'ClientInexistantException' interceptée par l'Application)
(Retour au menu principal)
[Option 3: Ouvrir un nouveau compte]

Entrez l'ID du client pour lequel ouvrir un compte :
> CLT-78A2B
... Recherche du client ...
Client 'Martin Dupont' trouvé.

Quel type de compte souhaitez-vous ouvrir ? (Utilisation d'Enum)
1. Compte Courant
2. Compte Épargne

Votre choix :
> 1
... Génération du numéro de compte ...
✅ SUCCÈS : Compte Courant (N° 00C-111) ouvert pour Martin Dupont.
(Retour au menu principal)
[Option 4: Afficher l'historique des transactions]

Entrez le numéro du compte :
> 00C-111
... Recherche du compte ...
--- Historique du Compte (00C-111) ---
(Logique utilisant StringBuilder et Iterator)
- [2025-11-02 21:30:00] OUVERTURE      | +0.00 €    | "Ouverture de compte"
- [2025-11-02 21:32:00] DEPOT          | +500.00 €  | "Dépôt initial"
- [2025-11-02 21:35:00] RETRAIT        | -50.00 €   | "Retrait DAB"
  --- Solde actuel : 450.00 € ---
  (Retour au menu principal)
  [Option 5: Effectuer un dépôt]

Entrez le numéro du compte à créditer :
> 00C-111
Entrez le montant à déposer :
> 150.75
... Opération en cours ...
✅ SUCCÈS : Dépôt de 150.75 € effectué. Nouveau solde : 600.75 €.
(Retour au menu principal)
Cas d'Erreur (Validation) :

Entrez le montant à déposer :
> -100
❌ ERREUR : Le montant doit être positif.
(Gestion d'une 'MontantInvalideException')
(Retour au menu principal)
[Option 6: Effectuer un retrait]

Cas Nominal :

Entrez le numéro du compte à débiter :
> 00C-111
Entrez le montant à retirer :
> 100
... Opération en cours ...
✅ SUCCÈS : Retrait de 100.00 € effectué. Nouveau solde : 500.75 €.
(Retour au menu principal)
Cas d'Erreur (Métier) :

Entrez le numéro du compte à débiter :
> 00E-222 (Compte Épargne avec 1200€)
Entrez le montant à retirer :
> 1500
... Opération en cours ...
❌ ERREUR : Opération échouée. Solde insuffisant.
(Gestion d'une 'SoldeInsuffisantException' levée par CompteEpargne)
(Retour au menu principal)
[Option 7: Effectuer un virement]

Entrez le numéro du compte SOURCE (débit) :
> 00C-111
Entrez le numéro du compte DESTINATAIRE (crédit) :
> 00E-222
Entrez le montant à virer :
> 200
... Opération en cours ...
✅ SUCCÈS : Virement de 200.00 € de 00C-111 vers 00E-222 effectué.
(Retour au menu principal)
[Option 8: Quitter]

Merci d'avoir utilisé BankSys CLI. Au revoir !
[Programme terminé]
[Cas d'Erreur : Entrée Invalide (Menu)]

Votre choix :
> z
❌ ERREUR : Veuillez entrer un nombre valide.
(Gestion d'une 'InputMismatchException' par le Scanner)

Votre choix :
> 9
❌ ERREUR : Option invalide. Veuillez choisir entre 1 et 8.
(Gestion par le 'default' du 'switch')
6. 🏁 Livrables
   L'ensemble du code source Java, structuré en packages (ex: com.banksys.model, com.banksys.service, com.banksys.main, com.banksys.exception).