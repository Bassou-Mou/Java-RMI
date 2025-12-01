import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

// 1. On étend UnicastRemoteObject et on implémente Console
public class RE extends UnicastRemoteObject implements Console {
    public RE() throws RemoteException {
        super();
    }

    public void println(String message) throws RemoteException {
        System.out.println("[SERVEUR DIT] : " + message);
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java RE <host> <command>");
            System.exit(0);
        }

        try {
            String host = args[0];
            String cmd = args[1];

            RE monClientConsole = new RE();

            Deamon daemon = (Deamon) Naming.lookup("rmi://" + host + "/DaemonService");

            System.out.println("Envoi de la commande...");
            daemon.executeCommand(cmd, monClientConsole);


            System.out.println("Commande envoyée. Attente de réponse...");

            Thread.sleep(2000);
            System.exit(0);

        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }
}