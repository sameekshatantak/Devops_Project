package Devops_Project;

import java.io.*;
import java.util.*;

public class CandidateManager {

    private static final String FILE_NAME = "candidates.json";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Candidate Management ===");
            System.out.println("1. Add Candidate");
            System.out.println("2. View Candidates");
            System.out.println("3. Exit");
            System.out.print("Choose option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addCandidate();
                    break;
                case 2:
                    viewCandidates();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    // Add Candidate
    public static void addCandidate() {
        System.out.print("Enter Candidate ID: ");
        String id = scanner.nextLine();

        if (isDuplicate(id)) {
            System.out.println("❌ Candidate ID already exists!");
            return;
        }

        System.out.print("Enter Candidate Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Party Name: ");
        String party = scanner.nextLine();

        System.out.print("Enter Symbol (optional): ");
        String symbol = scanner.nextLine();

        Candidate candidate = new Candidate(id, name, party, symbol);

        try (FileWriter writer = new FileWriter(FILE_NAME, true)) {

            File file = new File(FILE_NAME);
            if (file.length() == 0) {
                writer.write("[\n");
            } else {
                writer.write(",\n");
            }

            writer.write(candidate.toJSON());
            writer.write("\n]");
            writer.flush();

            System.out.println("✅ Candidate added successfully!");

        } catch (IOException e) {
            System.out.println("Error saving candidate.");
        }
    }

    // View Candidates
    public static void viewCandidates() {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists() || file.length() == 0) {
                System.out.println("No candidates found.");
                return;
            }

            Scanner fileScanner = new Scanner(file);
            System.out.println("\n📋 Candidate List:");
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("name")) {
                    System.out.println(line);
                }
            }
            fileScanner.close();

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }
    }

    // Check Duplicate ID
    public static boolean isDuplicate(String id) {
        try {
            File file = new File(FILE_NAME);
            if (!file.exists()) return false;

            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                if (line.contains("\"id\":\"" + id + "\"")) {
                    fileScanner.close();
                    return true;
                }
            }
            fileScanner.close();
        } catch (IOException e) {
            return false;
        }
        return false;
    }
}
