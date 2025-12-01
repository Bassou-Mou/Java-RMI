import java.rmi.*;


public interface Deamon extends Remote {
    public void executeCommand(String cmd, Console c) throws RemoteException;
}
