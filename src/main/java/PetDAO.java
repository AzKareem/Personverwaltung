import java.sql.*;

public class PetDAO {


        private Connection connection;

        public PetDAO(Connection connection) {
            this.connection = connection;
        }

        public Pet createPet(int personId, String name, String species) throws SQLException {
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
                        throw new SQLException("Household not found with ID: " + petId);
                    }
                }
            }
        }
        public void updatePet(int petId, int personId, String name, String species) throws SQLException {
            String updateSQL = "UPDATE pet SET person_id=?, name = ?, species = ? WHERE pet_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(updateSQL)) {
                statement.setInt(1, petId);
                statement.setInt(2, personId);
                statement.setString(3, name);
                statement.setString(4, species);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Updating household failed, no rows affected.");
                }
            }
        }
        public void deletePet(int petId) throws SQLException {
            String deleteSQL = "DELETE FROM pet WHERE pet_id = ?";
            try (PreparedStatement statement = connection.prepareStatement(deleteSQL)) {
                statement.setInt(1, petId);
                int affectedRows = statement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Deleting Pet failed, no rows affected.");
                }
            }
        }




}
