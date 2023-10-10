import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PetDAO {


    public Pet createPet(int personId, String name, String species) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "INSERT INTO pet (person_Id, name, species) VALUES (?,?,?)";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, personId);
            statement.setString(2, name);
            statement.setString(3, species);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating Pet failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    Pet createdPet = new Pet();
                    createdPet.setPetId(generatedKeys.getInt(1));
                    createdPet.setPersonId(personId);
                    createdPet.setName(name);
                    createdPet.setSpecies(species);
                    return createdPet;
                } else {
                    throw new SQLException("Creating Pet failed, no ID obtained.");
                }
            }
        }
    }

    public Pet readPet(int petId) throws SQLException {
        Connection connection = Database.getConnection();
        String sql = "SELECT * From  pet WHERE pet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, petId);


            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Pet pet = new Pet();
                    pet.setPetId(resultSet.getInt("pet_id"));
                    pet.setPersonId(resultSet.getInt("person_id"));
                    pet.setName(resultSet.getString("name"));
                    pet.setSpecies(resultSet.getString("species"));
                    return pet;
                } else {
                    throw new SQLException("Pet not found with ID: " + petId);
                }
            }
        }
    }

    public void updatePet(int petId, int personId, String name, String species) throws SQLException {
        Connection connection = Database.getConnection();
        String updateSQL = "UPDATE pet SET person_id=?, name = ?, species = ? WHERE pet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
            statement.setInt(4, petId);
            statement.setInt(1, personId);
            statement.setString(2, name);
            statement.setString(3, species);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating household failed, no rows affected.");
            }
        }
    }

    public void deletePet(int petId) throws SQLException {
        Connection connection = Database.getConnection();
        String deleteSQL = "DELETE FROM pet WHERE pet_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
            statement.setInt(1, petId);
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting Pet failed, no rows affected.");
            }
        }
    }

    public List<Pet> getPetsForPerson(int personId) throws SQLException {
        Connection connection = Database.getConnection();

        String readSQL = "SELECT * FROM pet WHERE person_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(readSQL)) {
            statement.setInt(1, personId);
            ResultSet resultSet = statement.executeQuery();

            List<Pet> pets = new ArrayList<>();

            while (resultSet.next()) {
                int petId = resultSet.getInt(1);

                String name = resultSet.getString(3);
                String species = resultSet.getString(4);
                Pet pet = new Pet( personId, name, species);
                pet.setPetId(petId);
                pets.add(pet);
            }
            return pets;
        }

    }
}






