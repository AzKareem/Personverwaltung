import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AddressDAO {


    private Connection connection;

    public AddressDAO(Connection connection) {
        this.connection = connection;
    }

    public Address createAddress(String location, String streetName, int houseNumber, int plz) throws SQLException {
        String sql = "INSERT INTO address (location, streetName, houseNumber,plz ) VALUES (?,?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, location);
            statement.setString(2, streetName);
            statement.setInt(3, houseNumber);
            statement.setInt(4, plz);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Address failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Address createdAddress = new Address(location, streetName, houseNumber, plz);
                    createdAddress.setAddressId(generatedKeys.getInt(1));
                    return createdAddress;
                } else {
                    throw new SQLException("Creating Address failed, no ID obtained.");
                }
            }
        }
    }

    public Map<String, Object> readAddress(int addressId) throws SQLException {
        String sql = "SELECT * From  address WHERE address_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, addressId);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Map<String, Object> addressDetails = new HashMap<>();
                    addressDetails.put("addressId", resultSet.getInt("address_id"));
                    addressDetails.put("location", resultSet.getString("location"));
                    addressDetails.put("streetName", resultSet.getString("streetName"));
                    addressDetails.put("houseNumber", resultSet.getInt("houseNumber"));
                    addressDetails.put("plz", resultSet.getInt("plz"));

                    return addressDetails;
                } else {
                    throw new SQLException("Address not found with ID: " + addressId);
                }
            }
        }
    }

    public void updateAddress(int addressId, String location, String streetName, int houseNumber, int plz) throws SQLException {
        String updateSQL = "UPDATE address SET location =?, streetName = ?, houseNumber = ?, plz = ? WHERE address_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setInt(1, addressId);
            statement.setString(2, location);
            statement.setString(3, streetName);
            statement.setInt(4, houseNumber);
            statement.setInt(5, plz);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating Address failed, no rows affected.");
            }
        }
    }

    public void deleteAddress(int addressId) throws SQLException {
        String deleteSQL = "DELETE FROM address WHERE address_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, addressId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting Address failed, no rows affected.");
            }
        }
    }



}
