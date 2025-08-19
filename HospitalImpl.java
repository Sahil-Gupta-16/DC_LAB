import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.*;
import java.io.*;
import java.nio.file.*;

public class HospitalImpl extends UnicastRemoteObject implements Hospital {
    private Map<Integer, String[]> patients; // id -> patient row

    public HospitalImpl() throws RemoteException {
        super();
        patients = new HashMap<>();
        loadCSV("patient_dataset.csv");
    }

   private void loadCSV(String filename) {
    try {
        List<String> lines = Files.readAllLines(Paths.get(filename));
        for (int i = 1; i < lines.size(); i++) { // skip header if exists
            String[] parts = lines.get(i).trim().split("\\s+");
            if (parts.length < 4) continue; // skip bad lines

            int id = Integer.parseInt(parts[0].trim());
            patients.put(id, parts);
        }
        System.out.println("Loaded " + patients.size() + " patients from CSV.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}


    @Override
    public String getPatientById(int id) throws RemoteException {
        if (patients.containsKey(id)) {
            return Arrays.toString(patients.get(id));
        }
        return "Patient not found.";
    }

    @Override
    public List<String> getPatientsByDisease(String disease) throws RemoteException {
        List<String> result = new ArrayList<>();
        for (String[] data : patients.values()) {
            if (data[3].equalsIgnoreCase(disease)) {
                result.add(Arrays.toString(data));
            }
        }
        return result;
    }

    @Override
    public String updateDiagnosis(int id, String newDiagnosis) throws RemoteException {
        if (patients.containsKey(id)) {
            String[] data = patients.get(id);
            data[4] = newDiagnosis;
            patients.put(id, data);
            return "Diagnosis updated for Patient ID: " + id;
        }
        return "Patient not found.";
    }

    @Override
    public String addPatient(int id, String name, int age, String disease, String diagnosis) throws RemoteException {
        if (patients.containsKey(id)) {
            return "Patient ID already exists!";
        }
        String[] newData = {String.valueOf(id), name, String.valueOf(age), disease, diagnosis};
        patients.put(id, newData);
        return "Patient added successfully.";
    }
}
