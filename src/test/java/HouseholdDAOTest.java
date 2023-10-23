import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HouseholdDAOTest {

    private HouseholdDAO householdDAO;

    @BeforeEach
    void setUp() {
        householdDAO = new HouseholdDAO();
    }

    @Test
    void createHousehold() throws SQLException {

        Household household = householdDAO.createHousehold("Test100");
        assertEquals("Test100", household.getHouseholdName());
    }

    @Test
    void readHousehold() throws SQLException {
        int householdID = 36;
        Household household = householdDAO.readHousehold(householdID);
        assertEquals(householdID, household.getHouseholdId());

    }

    @Test
    void updateHousehold() throws SQLException {
        int householdID = 36;
        String newHouseholdName = "Successfully changed";
        householdDAO.updateHousehold(householdID, newHouseholdName);
        Household updatedHousehold = householdDAO.readHousehold(householdID);
        assertEquals(newHouseholdName, updatedHousehold.getHouseholdName());
    }

    @Test
    void deleteHousehold() throws SQLException {
        int householdId = 32;

        try {
            if (householdDAO.readHousehold(householdId )  != null){
                householdDAO.deleteHousehold(householdId);
                assertEquals(null, householdDAO.readHousehold(householdId));
        }

        }catch (SQLException e){
            SQLException ex = assertThrows(SQLException.class, () -> householdDAO.readHousehold(householdId));
            assertEquals("Household not found with ID: " + householdId, ex.getMessage());
        }


    }

    @Test
    void getAllHouseholds() throws SQLException {
        int countHouseholds = householdDAO.getAllHouseholds().size();
        List<Household> households = householdDAO.getAllHouseholds();
        assertEquals(countHouseholds, households.size());
    }
}