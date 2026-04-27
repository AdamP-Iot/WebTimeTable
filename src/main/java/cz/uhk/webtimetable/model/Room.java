package cz.uhk.webtimetable.model;

public class Room {
    private String zkrBudovy;
    private String cisloMistnosti;

    public String getZkrBudovy() {
        return zkrBudovy;
    }

    public void setZkrBudovy(String zkrBudovy) {
        this.zkrBudovy = zkrBudovy;
    }

    public String getCisloMistnosti() {
        return cisloMistnosti;
    }

    public void setCisloMistnosti(String cisloMistnosti) {
        this.cisloMistnosti = cisloMistnosti;
    }

    public Room(String zkrBudovy, String cisloMistnosti) {
        this.zkrBudovy = zkrBudovy;
        this.cisloMistnosti = cisloMistnosti;
    }
    public Room() {}
}
