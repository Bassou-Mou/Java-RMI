import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class DeamonImpl extends UnicastRemoteObject implements Deamon {

    public DeamonImpl() throws RemoteException {
        super();
    }

    public void executeCommand(String cmd, Console console) throws RemoteException {
        System.out.println("J'ai recu l'ordre d'exécuter : " + cmd);
        localExec(cmd, console);
    }

    public void localExec(String cmd, Console console) {
        try {
            console.println(">>> Le serveur commence le travail sur : " + cmd);

            if (cmd.equals("ls")) {
                console.println("fichier1.txt");
                console.println("fichier2.java");
                console.println("dossier_projet");
            } else {
                console.println("Commande inconnue ou simulation non prévue.");
            }

            console.println(">>> Fin de l'exécution.");

        } catch (RemoteException e) {
            System.out.println("Erreur : Impossible de joindre le client.");
        }
    }

    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099);
            DeamonImpl server = new DeamonImpl();
            Naming.rebind("DaemonService", server);
            System.out.println("Serveur Daemon prêt.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}