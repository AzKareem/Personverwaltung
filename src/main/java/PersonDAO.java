import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDAO {


    public Person createPerson(int householdId, String name, String lastName, Date birthday, Address address, Person.Gender gender) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO person ( name, lastname, birthdate, address_id, gender,household_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setDate(3, birthday);
                statement.setInt(4, address.getAddressId());
                statement.setString(5, gender.toString());
                statement.setInt(6, householdId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating person failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Person createdPerson = new Person(householdId, name, lastName, birthday, address, gender);
                        createdPerson.setPersonId(generatedKeys.getInt(1));
                        return createdPerson;
                    } else {
                        throw new SQLException("Creating person failed, no ID obtained.");
                    }
                }
            }
        }
    }

    public Person createPerson( int householdId, String name, String lastName, Person.Gender gender, Date birthday) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO person ( name, lastname, gender, birthdate, household_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, gender.toString());
            statement.setDate(4, birthday);
            statement.setInt(5, householdId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating person failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Person createdPerson = new Person(householdId, name, lastName, birthday, null, gender); // Assuming address is null
                    createdPerson.setPersonId(generatedKeys.getInt(1));
                    return createdPerson;
                } else {
                    throw new SQLException("Creating person failed, no ID obtained.");
                }
            }
        }
    }


    public Person createPerson( int householdId, String name, String lastName) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO person (name, lastname, household_id) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setInt(3, householdId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating person failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        Person createdPerson = new Person(householdId, name, lastName); // Assuming address is null
                        createdPerson.setPersonId(generatedKeys.getInt(1));
                        return createdPerson;
                    } else {
                        throw new SQLException("Creating person failed, no ID obtained.");
                    }
                }
            }
        }
    }

    public Person readPerson(int personId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM person WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {


                        int personIdDb = resultSet.getInt("person_id");
                        String name = resultSet.getString("name");
                        String lastName = resultSet.getString("lastname");
                        Date birthday = resultSet.getDate("birthdate");
                        int addressId = resultSet.getInt("address_id");
                        Person.Gender gender = Person.Gender.valueOf(resultSet.getString("gender"));
                        int householdId = resultSet.getInt("household_id");


                        Address address =  AddressDAO.readAddress(addressId);

                        Person person = new Person(householdId, name, lastName, birthday, address, gender);


                        return person;
                    }
                }
            }
        }
        return null; // Return null if no person with the given ID is found
    }

    public void updatePerson(int personId, String name, String lastName, Date birthday, Address address, Person.Gender gender, int householdId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE person SET name = ?, lastname = ?, birthdate = ?, address_id = ?, gender = ?, household_id =? WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);
                statement.setString(2, name);
                statement.setString(3, lastName);
                statement.setDate(4, birthday);
                statement.setInt(5, address.getAddressId());
                statement.setString(6, gender.toString());
                statement.setInt(7, householdId);


                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating person failed, no rows affected.");
                }
            }
        }
    }

    public void updatePerson(int personId, String name, String lastName, int householdId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE person SET name = ?, lastname = ? , household_id =? WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);
                statement.setString(2, name);
                statement.setString(3, lastName);
                statement.setInt(4, householdId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating person failed, no rows affected.");
                }
            }
        }
    }

    public void updatePerson(int personId, int householdId, String name, String lastName, Person.Gender gender, Date birthday) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE person SET name = ?, lastname = ?, gender = ?, birthdate = ?, household_id =?  WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);
                statement.setString(2, name);
                statement.setString(3, lastName);
                statement.setString(4, gender.toString());
                statement.setDate(5, birthday);
                statement.setInt(6, householdId);


                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating person failed, no rows affected.");
                }
            }
        }
    }

    public void deletePerson(int personId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "DELETE FROM person WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);

                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting person failed, no rows affected.");
                }
            }
        }
    }

    public List<Person> getAllPersonsFromHousehold(int householdID) throws SQLException {
        Connection connection = Database.getConnection();
        String readSQL = "SELECT * FROM person WHERE household_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(readSQL)) {
            statement.setInt(1, householdID);
            ResultSet resultSet = statement.executeQuery();

            List<Person> persons = new ArrayList<>();

            while (resultSet.next()) {
                int personId = resultSet.getInt("person_id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                Date birthday = resultSet.getDate("birthdate");
                int addressId = resultSet.getInt("address_id");
                Person.Gender gender = Person.Gender.valueOf(resultSet.getString("gender"));

                Address address =  AddressDAO.readAddress(addressId);
                Person person = new Person(personId, name, lastName, birthday, address, gender);
                persons.add(person);
            }
            return persons;
        }
    }
}

