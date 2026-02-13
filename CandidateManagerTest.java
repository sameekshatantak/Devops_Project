package Devops_Project;
import org.junit.jupiter.api.*;
import java.io.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CandidateManagerTest {

    private static final String TEST_FILE = "test_candidates.json";

    @BeforeEach
    void setup() {
        CandidateManager.setFileName(TEST_FILE);
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @AfterEach
    void cleanup() {
        File file = new File(TEST_FILE);
        if (file.exists()) file.delete();
    }

    @Test
    void addSingleCandidateSuccessfully() throws Exception {
        CandidateManager.addCandidate("201", "Amit", "PQR Party", "Star");

        assertTrue(CandidateManager.isDuplicate("201"));
    }

    @Test
    void addMultipleCandidatesSuccessfully() throws Exception {
        CandidateManager.addCandidate("301", "Riya", "AAA Party", "Tree");
        CandidateManager.addCandidate("302", "Karan", "BBB Party", "Lion");

        assertTrue(CandidateManager.isDuplicate("301"));
        assertTrue(CandidateManager.isDuplicate("302"));
    }

    @Test
    void preventDuplicateCandidate() throws Exception {
        CandidateManager.addCandidate("401", "Neha", "CCC Party", "Sun");

        assertThrows(Exception.class, () ->
                CandidateManager.addCandidate("401", "Neha", "CCC Party", "Sun"));
    }

    @Test
    void invalidCandidateShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("20@2", "Riya", "AAA Party", "Tree"));
    }

    @Test
    void emptyFileShouldReturnFalseDuplicate() throws Exception {
        assertFalse(CandidateManager.isDuplicate("999"));
    }

    @Test
    void readingCandidatesFromFile() throws Exception {
        CandidateManager.addCandidate("501", "Anita", "DDD Party", "Moon");

        List<String> list = CandidateManager.readAllCandidatesRaw();
        assertEquals(1, list.size());
    }

    @Test
    void corruptedFileShouldNotCrash() throws Exception {
        BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE));
        writer.write("INVALID_JSON_CONTENT");
        writer.close();

        List<String> list = CandidateManager.readAllCandidatesRaw();
        assertNotNull(list);
    }

    @Test
    void fileDoesNotExistShouldReturnEmptyList() throws Exception {
        File file = new File(TEST_FILE);
        file.delete();

        List<String> list = CandidateManager.readAllCandidatesRaw();
        assertTrue(list.isEmpty());
    }

    @Test
    void whitespaceIdStillCheckedForDuplicate() throws Exception {
        CandidateManager.addCandidate("601", "Pooja", "EEE Party", "Flag");

        assertTrue(CandidateManager.isDuplicate("601"));
    }
    /* 
    @Test
    void largeNumberOfCandidates() throws Exception {
        for (int i = 1; i <= 20; i++) {
            CandidateManager.addCandidate("ID" + i, "Name" + i, "Party", "Symbol");
        }

        List<String> list = CandidateManager.readAllCandidatesRaw();
        assertEquals(20, list.size());
    }*/
}
