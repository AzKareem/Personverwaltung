import java.sql.*;

public class HouseholdDAO {

    private Connection connection;

    public HouseholdDAO(Connection connection) {
        this.connection = connection;
    }

    public int createHousehold(String household_name) throws SQLException {
        String sql = "INSERT INTO household (household_name) VALUES (?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, household_name);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating household failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1); // This ID is auto-generated
                } else {
                    throw new SQLException("Creating household failed, no ID obtained.");
                }
            }
        }
    }
    public Household readHousehold(int householdID) throws SQLException {
        String sql = "SELECT * From  household WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, householdID);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Household household = new Household();
                    household.setHouseholdId(resultSet.getInt("household_id"));
                    household.setHouseholdName(resultSet.getString("householdName"));
                    return household;
                } else {
                    throw new SQLException("Household not found with ID: " + householdID);
                }
            }
        }
    }
    public void updateHousehold(int householdId, String newHouseholdName) throws SQLException {
        String updateSQL = "UPDATE household SET householdName = ? WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setString(1, newHouseholdName);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating household failed, no rows affected.");
            }
        }
    }
    public void deleteHousehold(int householdId) throws SQLException {
        String deleteSQL = "DELETE FROM household WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, householdId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting household failed, no rows affected.");
            }
        }
    }


}
