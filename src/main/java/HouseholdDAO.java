import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HouseholdDAO {



    public  Household createHousehold(String householdName) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO household (household_name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, householdName);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating household failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int householdId = generatedKeys.getInt(1); // This ID is auto-generated
                    Household household = new Household(householdId, householdName);
                    return household;
                } else {
                    throw new SQLException("Creating household failed, no ID obtained.");
                }
            }
        }
    }

    public Household readHousehold(int householdID) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT * From  household WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, householdID);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Household household = new Household(resultSet.getInt("household_id"), resultSet.getString("household_name"));
                    System.out.println(household.getHouseholdId() + " " + household.getHouseholdName());
                    return household;
                } else {
                    throw new SQLException("Household not found with ID: " + householdID);
                }
            }
        }
    }

    public void updateHousehold(int householdId, String newHouseholdName) throws SQLException {
        Connection connection = Database.getConnection();
        String updateSQL = "UPDATE household SET household_name = ? WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setInt(2, householdId);
            statement.setString(1, newHouseholdName);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating household failed, no rows affected.");
            }
        }
    }

    public void deleteHousehold(int householdId) throws SQLException {
        Connection connection = Database.getConnection();
        String deleteSQL = "DELETE FROM household WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, householdId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting household failed, no rows affected.");
            }
        }
    }

    public List<Household> getAllHouseholds() throws SQLException {
        Connection connection = Database.getConnection();
        String readSQL = "SELECT * FROM household";
        try (PreparedStatement statement = connection.prepareStatement(readSQL)) {
            ResultSet resultSet = statement.executeQuery();

            List<Household> households = new ArrayList<>();

            while (resultSet.next()) {
                int householdId = resultSet.getInt(1);
                String householdName = resultSet.getString(2);
                Household household = new Household(householdId, householdName);
                households.add(household);
            }
            return households;
        }
    }


}
