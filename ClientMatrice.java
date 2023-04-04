import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClientMatrice {

    public static void main(String[] args) {
        try {
            // Se connecter au registre RMI
            Registry registry = LocateRegistry.getRegistry("localhost", 1099);

            // Obtenir une référence vers l'objet distant ServeurMatrice
            ServeurMatrice serveurMatrice = (ServeurMatrice) registry.lookup("ServeurMatrice");

            // Demander à l'utilisateur de s'identifier auprès du serveur
            Scanner scanner = new Scanner(System.in);
            System.out.print("Nom d'utilisateur : ");
            String nomUtilisateur = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String motDePasse = scanner.nextLine();
            Utilisateur utilisateur = new Utilisateur(nomUtilisateur, motDePasse);

            // Appeler la méthode distante d'authentification
            boolean authentifie = serveurMatrice.authentifier(utilisateur);
            if (!authentifie) {
                System.out.println("Nom d'utilisateur ou mot de passe incorrect");
                return;
            }

            // Afficher les opérations disponibles
            System.out.println("Opérations disponibles :");
            System.out.println("1 - Somme de deux matrices");
            System.out.println("2 - Multiplication de deux matrices");
            System.out.println("3 - Transposée d'une matrice");
            System.out.print("Entrez le numéro de l'opération souhaitée : ");

            // Lire le choix de l'utilisateur
            int choix = scanner.nextInt();

            // Traiter le choix de l'utilisateur
            switch (choix) {
                case 1:
                    // Demander à l'utilisateur de spécifier les dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    int rows1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    int cols1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    int rows2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    int cols2 = scanner.nextInt();

                    // Générer les matrices aléatoires
                    double[][] matrice1 = serveurMatrice.genererMatriceAleatoire(rows1, cols1);
                    double[][] matrice2 = serveurMatrice.genererMatriceAleatoire(rows2, cols2);

                    // Calculer la somme des matrices
                    double[][] resultat = serveurMatrice.additionnerMatrices(matrice1, matrice2);

                    // Afficher le résultat
                    System.out.println("Résultat de la somme :");
                    afficherMatrice(resultat);
                    break;
                case 2:
                    // Demander à l'utilisateur de spécifier les dimensions des matrices
                    System.out.print("Nombre de lignes de la première matrice : ");
                    rows1 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la première matrice : ");
                    cols1 = scanner.nextInt();
                    System.out.print("Nombre de lignes de la deuxième matrice : ");
                    rows2 = scanner.nextInt();
                    System.out.print("Nombre de colonnes de la deuxième matrice : ");
                    cols2 = scanner.nextInt();

                    // Vérifier que les matrices sont compatibles pour la multiplication
                    if (cols1 != rows2) {
                        System.out.println("Les dimensions des matrices ne permettent");
                                        // Générer les matrices aléatoires
                matrice1 = serveurMatrice.genererMatriceAleatoire(rows1, cols1);
                matrice2 = serveurMatrice.genererMatriceAleatoire(rows2, cols2);

                // Calculer le produit des matrices
                resultat = serveurMatrice.multiplierMatrices(matrice1, matrice2);

                // Afficher le résultat
                System.out.println("Résultat de la multiplication :");
                afficherMatrice(resultat);
                break;
            case 3:
                // Demander à l'utilisateur de spécifier les dimensions de la matrice
                System.out.print("Nombre de lignes de la matrice : ");
                int rows = scanner.nextInt();
                System.out.print("Nombre de colonnes de la matrice : ");
                int cols = scanner.nextInt();

                // Générer la matrice aléatoire
                double[][] matrice = serveurMatrice.genererMatriceAleatoire(rows, cols);

                // Calculer la transposée de la matrice
                resultat = serveurMatrice.transposerMatrice(matrice);

                // Afficher le résultat
                System.out.println("Résultat de la transposée :");
                afficherMatrice(resultat);
                break;
            default:
                System.out.println("Opération invalide");
                break;
        }
    } catch (Exception e) {
        System.err.println("Erreur : " + e.getMessage());
        e.printStackTrace();
    }

    private static void afficherMatrice(double[][] matrice) {
        for (int i = 0; i < matrice.length; i++) {
            for (int j = 0; j < matrice[i].length; j++) {
                System.out.print(matrice[i][j] + " ");
            }
            System.out.println();
        }
    }
}
