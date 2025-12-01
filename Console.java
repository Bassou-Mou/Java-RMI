import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Console extends Remote {
    public void println(String message) throws RemoteException;
}