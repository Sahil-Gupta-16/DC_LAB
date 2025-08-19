import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Hospital extends Remote {
    String getPatientById(int id) throws RemoteException;
    List<String> getPatientsByDisease(String disease) throws RemoteException;
    String updateDiagnosis(int id, String newDiagnosis) throws RemoteException;
    String addPatient(int id, String name, int age, String disease, String diagnosis) throws RemoteException;
}
