package Devops_Project;
import java.util.regex.Pattern;

public class Candidate {

    private String id;
    private String name;
    private String party;
    private String symbol;

    private static final Pattern ID_PATTERN = Pattern.compile("^[a-zA-Z0-9]+$");
    private static final Pattern NAME_PATTERN = Pattern.compile("^[a-zA-Z ]+$");

    public Candidate(String id, String name, String party, String symbol) {

        if (!isValidId(id)) {
            throw new IllegalArgumentException("Invalid ID. Only alphanumeric allowed.");
        }

        if (!isValidName(name)) {
            throw new IllegalArgumentException("Invalid Name. Only letters and spaces allowed.");
        }

        if (!isValidName(party)) {
            throw new IllegalArgumentException("Invalid Party Name.");
        }

        if (symbol != null && !symbol.isEmpty() && !isValidName(symbol)) {
            throw new IllegalArgumentException("Invalid Symbol.");
        }

        this.id = id;
        this.name = name;
        this.party = party;
        this.symbol = symbol == null ? "" : symbol;
    }

    public static boolean isValidId(String id) {
        return id != null && ID_PATTERN.matcher(id).matches();
    }

    public static boolean isValidName(String value) {
        return value != null && NAME_PATTERN.matcher(value).matches();
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getParty() { return party; }
    public String getSymbol() { return symbol; }

    public String toJSON() {
        return String.format(
            "{\"id\":\"%s\",\"name\":\"%s\",\"party\":\"%s\",\"symbol\":\"%s\"}",
            id, name, party, symbol
        );
    }
}
