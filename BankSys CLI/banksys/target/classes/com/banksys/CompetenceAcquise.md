
---

**Projet Java : BankSys CLI – Système Bancaire en Ligne de Commande**

Je viens de finaliser **BankSys CLI**, un projet ambitieux de **gestion bancaire** développé en **Java**, entièrement en **ligne de commande**.
L’objectif : créer une application **robuste**, capable de **gérer clients, comptes et transactions**, tout en appliquant les **principes avancés de la programmation orientée objet** et du **design logiciel propre**.

---

**Fonctionnalités principales de l’application :**

BankSys CLI simule le fonctionnement d’une vraie banque à travers un menu interactif en console.
Chaque action est pensée pour reproduire une opération bancaire complète :

* **Gestion des clients**

  * Création d’un nouveau client (génération automatique d’un identifiant unique).
  * Consultation des informations d’un client et de ses comptes associés.

* **Gestion des comptes**

  * Ouverture d’un nouveau compte (courant ou épargne) pour un client existant.
  * Affichage de l’historique complet des transactions d’un compte.

* **Opérations bancaires**

  * Effectuer un **dépôt** sur un compte.
  * Effectuer un **retrait** avec validation du solde ou du découvert autorisé.
  * Réaliser un **virement** entre deux comptes (source et destinataire).

* **Système**

  * Gestion des erreurs d’entrée utilisateur.
  * Option pour **quitter proprement l’application** avec message de fin.

L’ensemble est orchestré à l’aide d’une **boucle principale (while)**, de **switch**, de **try/catch centralisés**, et d’un **contrôle précis des entrées via Scanner** pour rendre l’expérience fluide et sans crash.

---

💡 **Concepts Java mis en pratique :**

* **Programmation Orientée Objet (POO)** : utilisation concrète de l’héritage, de l’abstraction, du polymorphisme et de l’encapsulation.
* **Modélisation des relations entre objets** :

  * **Association** → entre le service bancaire et les clients/comptes.
  * **Agrégation** → un client possède une liste de comptes (`List<ICompte>`).
  * **Composition** → un compte contient ses propres transactions.
* **Principe SOLID et Inversion de Dépendance (DPI)** : séparation claire entre la logique métier (`BankService`) et l’interface utilisateur (`Application`).
* **Interfaces, Enums et Records** : définition de contrats clairs (`ICompte`), gestion des types fixes (`TypeTransaction`) et structures immuables (`Transaction`).
* **Structures de données Java** :

  * `List` → pour stocker transactions et comptes.
  * `Map` → pour indexer clients et comptes par identifiant.
  * `Set` → pour assurer l’unicité des numéros de compte.
* **Gestion d’exceptions robustes** : intégration d’exceptions standards et personnalisées pour sécuriser les opérations et éviter les comportements imprévus.

---

🧠 **Ce que j’ai appris en plus au cours du développement :**

* L’opérateur `instanceof` est essentiel pour **vérifier le type réel d’un objet** avant un cast.
* Le mot-clé `throws` **n’a de sens que s’il y a un `throw` effectif** dans la méthode.
* **Centraliser les `try/catch` dans la classe principale** permet un débogage clair et évite de masquer les erreurs dans la logique métier.
* Le **casting entre type abstrait et type fille** est utile pour accéder à des comportements spécifiques après vérification :
  *(ex : `if (compte instanceof CompteCourant) { CompteCourant c = (CompteCourant) compte; }`)*
* En parcourant des `Map.Entry`, j’ai découvert que leur `toString()` diffère de celui des objets d’origine — mieux vaut donc utiliser `client.getValue()` pour un affichage cohérent.
* J’ai perfectionné ma **maîtrise du Run & Debug de VS Code**, ce qui facilite l’analyse et la capture des exceptions à l’exécution.
* Utilisation de Maven pour la gestion du projet et des packages
* Enfin, la combinaison **`do-while` + nettoyage du buffer du Scanner** s’est révélée très efficace pour gérer les **InputMismatchException** sans interrompre le programme.

---

Ce projet m’a permis de renforcer mes **bases solides en Java**, de mettre en pratique la **POO, SOLID et la modélisation objet**, et surtout d’apprendre à concevoir une application **propre, fluide et fiable**, du code à l’exécution.

---
