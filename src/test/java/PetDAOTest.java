import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PetDAOTest {

    private PetDAO petDAO;

    @BeforeEach
    void setUp() {
        petDAO = new PetDAO();
    }

    @Test
    void createPet() throws SQLException {
        Pet pet = petDAO.createPet(10, "Warwick", "wolf");
        Pet checkPet = petDAO.readPet(pet.getPetId());

        assertEquals(pet.getPetId(), checkPet.getPetId());
        assertEquals(10, checkPet.getPersonId());
        assertEquals("Warwick", checkPet.getName());
        assertEquals("wolf", checkPet.getSpecies());

    }

    @Test
    void readPet() throws SQLException {
        int petId = 27;

        Pet pet = petDAO.readPet(petId);
        assertEquals(petId, pet.getPetId());
    }

    @Test
    void updatePet() throws SQLException {
        int petID = 27;
        int personID = 16;
        String name = "Tiger";
        String species = "tiger";
        petDAO.updatePet(petID, personID, name, species);
        Pet updatedPet = petDAO.readPet(petID);
        assertEquals(personID, updatedPet.getPersonId());
        assertEquals(name, updatedPet.getName());
        assertEquals(species, updatedPet.getSpecies());

    }

    @Test
    void deletePet() throws SQLException {
        int petId = 28;
        try {
            if (petDAO.readPet(petId) != null) {
                petDAO.deletePet(petId);
                assertNull(petDAO.readPet(petId));
            } else {
            }
        } catch (SQLException e) {
            SQLException ex = assertThrows(SQLException.class, () -> petDAO.deletePet(petId));
            assertEquals("Deleting Pet failed, no rows affected.", ex.getMessage());
        }
    }

    @Test
    void getPetsForPerson() throws SQLException {
        int personId = 15;
        List<Pet> pets = petDAO.getPetsForPerson(personId);
        assertEquals(3, pets.size());

    }
}