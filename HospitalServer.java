import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class HospitalServer {
    public static void main(String[] args) {
        try {
            LocateRegistry.createRegistry(1099); // start RMI registry
            HospitalImpl hospital = new HospitalImpl();
            Naming.rebind("HospitalService", hospital);
            System.out.println("Hospital Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
