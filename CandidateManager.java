package Devops_Project;
import java.io.*;
import java.util.*;

public class CandidateManager {

    private static String FILE_NAME = "candidates.json";
    private static Scanner scanner = new Scanner(System.in);

    public static void setFileName(String fileName) {
        FILE_NAME = fileName;
    }

    // =========================
    // MAIN METHOD (User Interface)
    // =========================
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n===== Candidate Management =====");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    handleAddCandidate();
                    break;
                case "2":
                    handleViewCandidates();
                    break;
                case "3":
                    System.out.println("Exiting Candidate Module...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    // =========================
    // UI Handler for Adding Candidate
    // =========================
    private static void handleAddCandidate() {
        try {
            System.out.print("Enter Candidate ID: ");
            String id = scanner.nextLine().trim();

            System.out.print("Enter Candidate Name: ");
            String name = scanner.nextLine().trim();

            System.out.print("Enter Party Name: ");
            String party = scanner.nextLine().trim();

            System.out.print("Enter Symbol (optional): ");
            String symbol = scanner.nextLine().trim();

            addCandidate(id, name, party, symbol);
            System.out.println("✅ Candidate added successfully.");

        } catch (IllegalArgumentException e) {
            System.out.println("❌ Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // =========================
    // UI Handler for Viewing
    // =========================
    private static void handleViewCandidates() {
        try {
            List<String> candidates = readAllCandidatesRaw();

            if (candidates.isEmpty()) {
                System.out.println("No candidates found.");
                return;
            }

            System.out.println("\n--- Candidate List ---");

            int count = 1;
            for (String c : candidates) {
                if (!c.startsWith("{")) c = "{" + c;
                if (!c.endsWith("}")) c = c + "}";

                System.out.println(count + ". " + c);
                count++;
            }

        } catch (IOException e) {
            System.out.println("Error reading candidates.");
        }
    }

    // =========================
    // CORE LOGIC METHODS
    // =========================

    public static void addCandidate(String id, String name, String party, String symbol)
            throws Exception {

        if (isDuplicate(id)) {
            throw new Exception("Candidate ID already exists.");
        }

        Candidate candidate = new Candidate(id, name, party, symbol);
        List<String> candidates = readAllCandidatesRaw();

        candidates.add(candidate.toJSON());
        writeAllCandidates(candidates);
    }

    public static boolean isDuplicate(String id) throws IOException {
        List<String> candidates = readAllCandidatesRaw();

        for (String c : candidates) {
            if (c.contains("\"id\":\"" + id + "\"")) {
                return true;
            }
        }
        return false;
    }

    public static List<String> readAllCandidatesRaw() throws IOException {
        File file = new File(FILE_NAME);

        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        StringBuilder content = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            content.append(line.trim());
        }
        reader.close();

        String json = content.toString();
        json = json.replace("[", "").replace("]", "");

        if (json.trim().isEmpty()) {
            return new ArrayList<>();
        }

        return new ArrayList<>(Arrays.asList(json.split("},\\{")));
    }

    private static void writeAllCandidates(List<String> candidates) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        writer.write("[\n");

        for (int i = 0; i < candidates.size(); i++) {
            String candidate = candidates.get(i);

            if (!candidate.startsWith("{")) candidate = "{" + candidate;
            if (!candidate.endsWith("}")) candidate = candidate + "}";

            writer.write(candidate);
            if (i != candidates.size() - 1) writer.write(",\n");
        }

        writer.write("\n]");
        writer.close();
    }
}
