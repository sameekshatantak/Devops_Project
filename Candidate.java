package Devops_Project;

public class Candidate {
    private String id;
    private String name;
    private String party;
    private String symbol;

    public Candidate(String id, String name, String party, String symbol) {
        this.id = id;
        this.name = name;
        this.party = party;
        this.symbol = symbol;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public String getSymbol() {
        return symbol;
    }

    public String toJSON() {
        return String.format(
            "{\"id\":\"%s\",\"name\":\"%s\",\"party\":\"%s\",\"symbol\":\"%s\"}",
            id, name, party, symbol
        );
    }
}
