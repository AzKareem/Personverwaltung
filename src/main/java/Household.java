import java.util.HashMap;

public class Household {

    private int householdId;
    private String householdName;

    private HashMap<Integer, Person> persons;


    public Household() {
        persons = new HashMap<>();

    }

    public Household(String householdName) {
        this.householdName = householdName;
        this.persons = new HashMap<>();
    }


    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }

    public void setHouseholdName(String household_name) {
        this.householdName = household_name;
    }

    public int getHouseholdId() {
        return householdId;
    }

    public String getHouseholdName() {
        return householdName;
    }

    public HashMap<Integer, Person> getPersons() {
        return persons;
    }

    public void addPerson(Person person) {
        persons.put(person.getPersonId(), person);
    }

    public String toString() {
        return "Household{" +
                "householdId=" + householdId +
                ", householdName='" + householdName + '\'' +
                '}';
    }
}
