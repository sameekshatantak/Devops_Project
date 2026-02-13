package Devops_Project;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CandidateTest {

    @Test
    void validCandidateCreation() {
        Candidate c = new Candidate("101", "Rahul", "ABC Party", "Lotus");

        assertEquals("101", c.getId());
        assertEquals("Rahul", c.getName());
        assertEquals("ABC Party", c.getParty());
        assertEquals("Lotus", c.getSymbol());
    }

    @Test
    void invalidIdWithSpecialCharacter() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("10@1", "Rahul", "ABC Party", "Lotus"));
    }

    @Test
    void invalidIdWithSpace() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("10 1", "Rahul", "ABC Party", "Lotus"));
    }

    @Test
    void emptyIdShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("", "Rahul", "ABC Party", "Lotus"));
    }

    @Test
    void nullIdShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate(null, "Rahul", "ABC Party", "Lotus"));
    }

    @Test
    void invalidNameWithNumbers() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("101", "Rahul123", "ABC Party", "Lotus"));
    }

    @Test
    void invalidNameWithSpecialCharacters() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("101", "Rahul@", "ABC Party", "Lotus"));
    }

    @Test
    void emptyNameShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("101", "", "ABC Party", "Lotus"));
    }

    @Test
    void invalidPartyNameShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("101", "Rahul", "ABC@123", "Lotus"));
    }

    @Test
    void optionalEmptySymbolAllowed() {
        Candidate c = new Candidate("102", "Sneha", "XYZ Party", "");
        assertEquals("", c.getSymbol());
    }

    @Test
    void invalidSymbolShouldFail() {
        assertThrows(IllegalArgumentException.class, () ->
                new Candidate("101", "Rahul", "ABC Party", "Lotus@"));
    }

    @Test
    void jsonFormatShouldContainAllFields() {
        Candidate c = new Candidate("200", "Amit", "PQR Party", "Star");

        String json = c.toJSON();

        assertTrue(json.contains("\"id\":\"200\""));
        assertTrue(json.contains("\"name\":\"Amit\""));
        assertTrue(json.contains("\"party\":\"PQR Party\""));
        assertTrue(json.contains("\"symbol\":\"Star\""));
    }
}
