import javax.xml.stream.Location;
import java.util.Objects;

public class Address {
    private int addressId;
    private int plz;
    private String location;

    private String streetName;
    private int houseNumber;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return addressId == address.addressId && plz == address.plz && houseNumber == address.houseNumber && location.equals(address.location) && streetName.equals(address.streetName);
    }


    public Address(int addressId, String location, String streetName, int houseNumber, int plz ) {
        this.addressId = addressId;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.plz = plz;
        this.location = location;
    }

    public String toString() {
        StringBuilder sp = new StringBuilder();
        sp.append(location + " ");
        sp.append(streetName + " ");
        sp.append(houseNumber + " ");
        sp.append(plz + " ");
        return sp.toString();
    }

    public int getAddressId() {
        return addressId;
    }

    public int getPlz() {
        return plz;
    }

    public String getLocation() {
        return location;
    }

    public String getStreetName() {
        return streetName;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }




}
