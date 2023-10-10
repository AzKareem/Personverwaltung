import java.util.ArrayList;
import java.util.List;

public class Household {

    private int householdId;
    private String householdName;

    List<Person> persons;

    public Household(int householdId, String householdName) {
        this.householdId = householdId;
        this.householdName = householdName;
        this.persons = new ArrayList<>();
    }

    public int getHouseholdId() {
        return householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public String toString() {
        return "Household{" +
                "householdId=" + householdId +
                ", householdName='" + householdName + '\'' +
                '}';
    }
}
