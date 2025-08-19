import java.rmi.Naming;
import java.util.*;

public class HospitalClient {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);
            Hospital hospital = (Hospital) Naming.lookup("rmi://localhost/HospitalService");

            while (true) {
                System.out.println("\n--- Hospital System ---");
                System.out.println("1. Get Patient by ID");
                System.out.println("2. Get Patients by Disease");
                System.out.println("3. Update Diagnosis");
                System.out.println("4. Add New Patient");
                System.out.println("5. Exit");
                System.out.print("Enter choice: ");
                int choice = sc.nextInt();
                sc.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Enter Patient ID: ");
                        int id = sc.nextInt();
                        System.out.println(hospital.getPatientById(id));
                        break;
                    case 2:
                        System.out.print("Enter Disease: ");
                        String disease = sc.nextLine();
                        System.out.println(hospital.getPatientsByDisease(disease));
                        break;
                    case 3:
                        System.out.print("Enter Patient ID: ");
                        int pid = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter New Diagnosis: ");
                        String diag = sc.nextLine();
                        System.out.println(hospital.updateDiagnosis(pid, diag));
                        break;
                    case 4:
                        System.out.print("Enter ID: ");
                        int newId = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Age: ");
                        int age = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Disease: ");
                        String dis = sc.nextLine();
                        System.out.print("Enter Diagnosis: ");
                        String diagnosis = sc.nextLine();
                        System.out.println(hospital.addPatient(newId, name, age, dis, diagnosis));
                        break;
                    case 5:
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice!");
                }
            }
        } catch (Exception e) {
            System.out.println("Connection to server Failed ....Try Again");
            // e.printStackTrace();
        }
    }
}
