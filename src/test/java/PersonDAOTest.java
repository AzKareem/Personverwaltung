import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PersonDAOTest {


    private PersonDAO personDAO;
    private AddressDAO addressDAO;

    @BeforeEach
    void setUp() {
        personDAO = new PersonDAO();
        addressDAO = new AddressDAO();
    }

    @Test
    void createPerson() throws SQLException {
        Address address = addressDAO.createAddress("Linz", "Reindlstraße", 4, 4020);

        Address checkAddress = addressDAO.readAddress(address.getAddressId());
        assertEquals(address.getAddressId(), checkAddress.getAddressId());
        assertEquals("Linz", checkAddress.getLocation());
        assertEquals("Reindlstraße", checkAddress.getStreetName());
        assertEquals(4, checkAddress.getHouseNumber());
        assertEquals(4020, checkAddress.getPlz());

        Person person = personDAO.createPerson(15, "Khalil", "Azimi", Date.valueOf("2000-03-14"), address, Person.Gender.Male);
        Person checkPerson = personDAO.readPerson(person.getPersonId());
        assertEquals(15, person.getHouseholdId());
        assertEquals("Khalil", person.getName());
        assertEquals("Azimi", person.getLastName());
        assertEquals(Date.valueOf("2000-03-14"), person.getBirthday());
        assertEquals(address, checkPerson.getAddress());
        assertEquals(Person.Gender.Male, person.getGender());
    }


    @Test
    void readPerson() throws SQLException {
        int personId = 12;
        Person person = personDAO.readPerson(personId);
        assertEquals(personId, person.getPersonId());
    }

    @Test
    void updatePerson() throws SQLException {
        Address originalAddress = addressDAO.createAddress("Linz", "Reindlstraße", 4, 4020);
        Person originalPerson = personDAO.createPerson(15, "Khalil", "Azimi", Date.valueOf("2000-03-14"), originalAddress, Person.Gender.Male);


        int updatedHouseholdId = 33;
        String updatedName = "Melani";
        String updatedLastName = "unknown";
        Date updatedBirthday = Date.valueOf("1998-03-14");
        Address updatedAddress = originalAddress;
        Person.Gender updatedGender = Person.Gender.Female;

        Person updatedPerson = personDAO.updatePerson(originalPerson.getPersonId(), updatedName, updatedLastName, updatedBirthday, originalAddress, updatedGender, updatedHouseholdId);

        assertEquals(updatedHouseholdId, updatedPerson.getHouseholdId());
        assertEquals(updatedName, updatedPerson.getName());
        assertEquals(updatedBirthday, updatedPerson.getBirthday());
        assertEquals(updatedAddress, updatedPerson.getAddress());
        assertEquals(updatedGender, updatedPerson.getGender());


    }


    @Test
    void deletePerson() throws SQLException {
        int personId = 12;
        if (personDAO.readPerson(personId) != null) {
            personDAO.deletePerson(personId);
            assertEquals(null , personDAO.readPerson(personId));
        }
        else {
            SQLException ex = assertThrows(SQLException.class, () -> personDAO.deletePerson(personId));
            assertEquals("Deleting person failed, no rows affected.", ex.getMessage());
        }
    }

    @Test
    void getAllPersonsFromHousehold() throws SQLException {
        int householdId = 33;
        List<Person> personsFromHousehold = personDAO.getAllPersonsFromHousehold(householdId);
        int countOfPersonFromHousehold = personDAO.getAllPersonsFromHousehold(householdId).size();

        assertEquals(countOfPersonFromHousehold, personsFromHousehold.size());
    }
}