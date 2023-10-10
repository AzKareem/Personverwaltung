import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.SQLException;

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
    }

    @Test
    void testUpdatePerson() throws SQLException {
    }

    @Test
    void testUpdatePerson1() throws SQLException {
    }

    @Test
    void deletePerson() throws SQLException {
    }

    @Test
    void getAllPersonsFromHousehold() throws SQLException {
    }
}