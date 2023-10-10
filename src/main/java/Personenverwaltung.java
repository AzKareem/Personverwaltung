

import java.sql.Date;
import java.util.ArrayList;


public class Personenverwaltung {

    public static ArrayList<Person> personArrayList;

    public Personenverwaltung() {
        personArrayList = new ArrayList<>();
    }

    public void addPerson(Person person) {
        personArrayList.add(person);
    }

    public void displayAllPersons() {
        for (Person person : personArrayList) {
            System.out.println(person.name + " " + person.lastName + " " + person.birthday + " " + person.address_id + " " + person.gender);
        }
    }

    public void createPerson(int householdId, String name, String lastName) {
        if (Util.containsNumber(name) || Util.containsNumber(lastName)) {
            throw new IllegalArgumentException("Name oder Nachname dürfen keine Zahlen enthalten.");
        }
        Person person = new Person(householdId, name, lastName);
        addPerson(person);
    }


    public void createPerson(int householdId, String name, String lastName, Date birthday, Address address_id, Person.Gender gender) {
        if (Util.containsNumber(name) || Util.containsNumber(lastName)) {
            throw new IllegalArgumentException("Name oder Nachname dürfen keine Zahlen enthalten.");
        }

        Person person = new Person(householdId,name, lastName, birthday, address_id, gender);
        addPerson(person);
    }


    public void createPerson(int householdId, String name, String lastName, Person.Gender gender, Date birthday) {
        if (Util.containsNumber(name) || Util.containsNumber(lastName)) {
            throw new IllegalArgumentException("Name oder Nachname dürfen keine Zahlen enthalten.");
        }
        Person person = new Person(householdId, name, lastName, gender, birthday);
        addPerson(person);
    }

    public String findPerson(String name) {
        for (Person person : personArrayList) {
            if (person.name.equals(name)) {
                System.out.println("Details der gefundenen Person:");
                System.out.println("Name: " + person.name);
                System.out.println("Nachname: " + person.lastName);
                System.out.println("Geburtstag: " + person.birthday);
                System.out.println("Adresse: " + person.address_id);
                System.out.println("Geschlecht: " + person.gender);
                return name; // Return the found person
            }
        }
        throw new NullPointerException("Diese Person " + name + " exestiert nicht!");
    }

    public void removePerson(String name) {
        Person personToRemove = null;
        for (int i = 0; i < personArrayList.size(); i++) {
            Person p = personArrayList.get(i);
            if (p.name.equals(name)) {
                personToRemove = p;
                break;
            }
        }
        if (personToRemove != null) {
            personArrayList.remove(personToRemove);
        } else {
            throw new NullPointerException("Diese Person " + name + " exestiert nicht!");
        }
    }
}
