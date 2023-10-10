import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AddressDAOTest {

    private AddressDAO addressDAO;

    @BeforeEach
    void setup(){
        addressDAO = new AddressDAO();
    }

    @Test
    void createAddress()throws SQLException {
        Address address = addressDAO.createAddress("idontknow1","unknown",4,4020);


        Address checkAddress = addressDAO.readAddress(address.getAddressId());
        assertEquals(address.getAddressId(), checkAddress.getAddressId());
        assertEquals("idontknow1", checkAddress.getLocation());
        assertEquals("unknown", checkAddress.getStreetName());
        assertEquals(4, checkAddress.getHouseNumber());
        assertEquals(4020, checkAddress.getPlz());
    }

    @Test
    void readAddress()throws SQLException {
        int addressId = 10;
        Address address = addressDAO.readAddress(addressId);

        assertEquals(addressId, address.getAddressId());
    }

    @Test
    void updateAddress()throws SQLException {
        int addressId = 10;
        String newAddressLocation = "newLocation";
        String newAddressStreetName = "newStreetName";
        int newHouseNumber = 66;
        int newPlz = 4050;
        addressDAO.updateAddress(addressId,newAddressLocation,newAddressStreetName, newHouseNumber,newPlz);
        Address updatedAddress = addressDAO.readAddress(addressId);
        assertEquals(newAddressLocation, updatedAddress.getLocation());
        assertEquals(newAddressStreetName, updatedAddress.getStreetName());
        assertEquals(newHouseNumber, updatedAddress.getHouseNumber());
        assertEquals(newPlz, updatedAddress.getPlz());

    }

    @Test
    void deleteAddress()throws SQLException {
        int addressId = 17;
        addressDAO.deleteAddress(addressId);
        SQLException ex = assertThrows(SQLException.class, () -> addressDAO.readAddress(addressId));
        assertEquals("Address not found with ID: " + addressId, ex.getMessage());
    }
}