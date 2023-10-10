import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AddressDAO {

    public Address createAddress( String location, String streetName, int houseNumber, int plz) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO address ( location, streetName, houseNumber,plz ) VALUES (?,?,?,?)";
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
                    int generatedAddressID= generatedKeys.getInt(1);
                    Address createdAddress = new Address(generatedAddressID, location, streetName, houseNumber, plz);

                    return createdAddress;
                } else {
                    throw new SQLException("Creating Address failed, no ID obtained.");
                }
            }
        }
    }

    public static Address readAddress(int addressId) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT * From  address WHERE address_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, addressId);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                  int addressID = resultSet.getInt("address_id");
                  String location = resultSet.getString("location");
                  String streetName = resultSet.getString("streetName");
                  int houseNumber = resultSet.getInt("houseNumber");
                  int plz = resultSet.getInt("plz");
                 Address address = new Address(addressID, location, streetName, houseNumber, plz);

                    return address;
                } else {
                    throw new SQLException("Address not found with ID: " + addressId);
                }
            }
        }
    }

    public void updateAddress(int addressId, String location, String streetName, int houseNumber, int plz) throws SQLException {
        Connection connection = Database.getConnection();
        String updateSQL = "UPDATE address SET location =?, streetName = ?, houseNumber = ?, plz = ? WHERE address_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setInt(5, addressId);
            statement.setString(1, location);
            statement.setString(2, streetName);
            statement.setInt(3, houseNumber);
            statement.setInt(4, plz);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating Address failed, no rows affected.");
            }
        }
    }

    public void deleteAddress(int addressId) throws SQLException {
        Connection connection = Database.getConnection();
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
