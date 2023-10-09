import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PersonDAO {
    private Connection connection;

    public PersonDAO(Connection connection) {
        this.connection = connection;
    }

    public Person createPerson(int householdId, String name, String lastName, String birthday, Address address, Person.Gender gender) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "INSERT INTO person ( name, lastname, birthday, address_id, gender,household_id) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, name);
                statement.setString(2, lastName);
                statement.setString(3, birthday);
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

    public Person createPerson(int householdId, String name, String lastName, Person.Gender gender, String birthday) throws SQLException {
        String sql = "INSERT INTO person (name, lastname, gender, birthday, household_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setString(3, gender.toString());
            statement.setString(4, birthday);
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
                    throw  new SQLException("Creating person failed, no ID obtained.");
                }
            }
        }
    }


    public Person createPerson(int householdId, String name, String lastName) throws SQLException {
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

    public Map<String, Object> readPerson(int personId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "SELECT * FROM person WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        // Create a map to hold the person's details
                        Map<String, Object> personDetails = new HashMap<>();
                        personDetails.put("personId", resultSet.getInt("person_id"));
                        personDetails.put("name", resultSet.getString("name"));
                        personDetails.put("lastName", resultSet.getString("lastname"));
                        personDetails.put("birthday", resultSet.getString("birthday"));
                        personDetails.put("addressId", resultSet.getInt("address_id"));
                        personDetails.put("gender", Person.Gender.valueOf(resultSet.getString("gender")));
                        personDetails.put("householdId", resultSet.getInt("household_id"));

                        return personDetails;
                    }
                }
            }
        }
        return null; // Return null if no person with the given ID is found
    }

    public void updatePerson(int personId, String name, String lastName, String birthday, Address address, Person.Gender gender, int householdId) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE person SET name = ?, lastname = ?, birthday = ?, address_id = ?, gender = ?, household_id =? WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);
                statement.setString(2, name);
                statement.setString(3, lastName);
                statement.setString(4, birthday);
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

    public void updatePerson(int personId, int householdId, String name, String lastName, Person.Gender gender, String birthday) throws SQLException {
        try (Connection connection = Database.getConnection()) {
            String sql = "UPDATE person SET name = ?, lastname = ?, gender = ?, birthday = ?, household_id =?  WHERE person_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, personId);
                statement.setString(2, name);
                statement.setString(3, lastName);
                statement.setString(4, gender.toString());
                statement.setString(5, birthday);
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
}

