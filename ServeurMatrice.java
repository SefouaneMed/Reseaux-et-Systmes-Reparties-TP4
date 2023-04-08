import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServeurMatrice extends Remote {
    // Méthode pour ajouter un utilisateur à la liste des utilisateurs du serveur
    boolean ajouterUtilisateur(Utilisateur utilisateur) throws RemoteException;

    // Méthode pour authentifier un utilisateur auprès du serveur
    boolean authentifierUtilisateur(Utilisateur utilisateur) throws RemoteException;

    // Méthode pour générer une matrice aléatoire de dimensions rows x cols
    double[][] genererMatriceAleatoire(int rows, int cols) throws RemoteException;

    // Méthode pour calculer la somme de deux matrices
    double[][] additionnerMatrices(double[][] matrice1, double[][] matrice2) throws RemoteException;

    // Méthode pour calculer le produit de deux matrices
    double[][] multiplierMatrices(double[][] matrice1, double[][] matrice2) throws RemoteException;

    // Méthode pour calculer la transposée d'une matrice
    double[][] transposerMatrice(double[][] matrice) throws RemoteException;
}
