import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Person {

    private int personId;
    int householdId;
    String name;
    String lastName;
    Date birthday;

    Address address_id;
    Gender gender;
    List<Pet> pets;

    public enum Gender {
        Male,
        Female
    }

    public Person(int householdId, String name, String lastName, Date birthday, Address address_id, Gender gender) {

        this.householdId = householdId;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address_id = address_id;
        this.gender = gender;
        this.pets = new ArrayList<>();
    }

    public Person(int householdId, String name, String lastName, Gender gender, Date birthday) {

        this.householdId = householdId;
        this.name = name;
        this.lastName = lastName;
        this.birthday = birthday;
        this.gender = gender;
        this.pets = new ArrayList<>();
    }

    public Person(int householdId, String name, String lastname) {

        this.householdId = householdId;
        this.name = name;
        this.lastName = lastname;
        this.pets = new ArrayList<>();
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void addPet(Pet pet) {
        pets.add(pet);

    }
    public int getPersonId() {
        return personId;
    }

    public int getHouseholdId() {
        return householdId;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public Address getAddress() {
        return address_id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setHouseholdId(int householdId) {
        this.householdId = householdId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setAddress(Address address_id) {
        this.address_id = address_id;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }



}
