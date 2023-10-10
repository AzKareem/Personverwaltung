import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Practice {
    public static void main(String[] args) throws SQLException {


         Person person = new Person(1, "alex", "alex2", Date.valueOf("1997-03-14"),new Address(10,"Linz","gsdgds",5,4020), Person.Gender.Male );
         Person person2 = new Person(1, "alex", "alex2", Date.valueOf("1997-03-14"),new Address(10,"Linz","gsdgds",5,4020), Person.Gender.Male );


         Address address = new Address(10,"Linz","gsdgds",5,4020);
         Address address2 = new Address(10,"Linz","gsdgds",5,4020);



         if (person.equals(person2)){
             System.out.println("Same person");
         }else {
             System.out.println("different person");
         }


//        HouseholdDAO householdDAO = new HouseholdDAO();
//        Household household1 = householdDAO.createHousehold("test1234");
//       Household household2 = householdDAO.createHousehold("Check");
//
//        int householdId= 15;
//        householdDAO.readHousehold(householdId);
//       householdDAO.deleteHousehold(30);
//        householdDAO.updateHousehold(15,"successfully changed");
//        List<Household> households = householdDAO.getAllHouseholds();
//        for (int i = 0; i < households.size(); i++) {
//            Household household = households.get(i);
//            System.out.println(household.getHouseholdId() + " " + household.getHouseholdName());
//        }
//
//        Address address = AddressDAO.createAddress("gg1", "idontknow2", 3, 4020);
//        Address address1 = AddressDAO.createAddress("gg2", "idontknow2", 4, 4020);
//        Address address2 = AddressDAO.createAddress("gg3", "idontknow2", 5, 4020);
//        Address address3 = AddressDAO.createAddress("gg4", "idontknow2", 6, 4020);
//        Address address4 = AddressDAO.createAddress("gg5", "idontknow2", 7, 4020);
//        Address address5 = AddressDAO.createAddress("gg6", "idontknow2", 8, 4020);
//
//        PersonDAO person = new PersonDAO();
//        PersonDAO person1 = new PersonDAO();
//        PersonDAO person2 = new PersonDAO();
//        PersonDAO person3 = new PersonDAO();
//        PersonDAO person4 = new PersonDAO();
//        PersonDAO person5 = new PersonDAO();
////        person.createPerson(15, "karim", "azimi", Date.valueOf("1997-06-06"), address, Person.Gender.valueOf("Male"));
//        person.createPerson(33, "Ferzan", "unknown", Date.valueOf("1997-06-06"), address1, Person.Gender.valueOf("Male"));
//        person.createPerson(33, "Luna", "unknown", Date.valueOf("1997-06-06"), address2, Person.Gender.valueOf("Male"));
//        person.createPerson(33, "AbdulRahman", "unknown", Date.valueOf("1997-06-06"), address3, Person.Gender.valueOf("Male"));
//        person.createPerson(15, "David", "unknown", Date.valueOf("1997-06-06"), address4, Person.Gender.valueOf("Male"));
//        person.createPerson(35, "Oliver", "unknown", Date.valueOf("1997-06-06"), address5, Person.Gender.valueOf("Male"));
////


//        Pet pet = PetDAO.createPet(10, "saitama", "unknown");
//        Pet pet1 = PetDAO.createPet(10, "wolverine", "wolf");
//        Pet pet2 = PetDAO.createPet(12, "Tiger", "cat");
//        Pet pet3 = PetDAO.createPet(12, "Nero", "cat");
//        Pet pet4 = PetDAO.createPet(12, "Garfield", "cat");
//        Pet pet5 = PetDAO.createPet(13, "Balo", "bear");
//        Pet pet6 = PetDAO.createPet(13, "King", "lion");
//        Pet pet7 = PetDAO.createPet(15, "Rocky", "cat");
//        Pet pet8 = PetDAO.createPet(15, "Sammy", "cat");
//        Pet pet9 = PetDAO.createPet(15, "Bruno", "cat");
//        Pet pet10 = PetDAO.createPet(15, "Caesar", "cat");
//        Pet pet11 = PetDAO.createPet(16, "Charly", "cat");
//        int personID = 10;
//        PetDAO petDAO = new PetDAO();
//        List<Pet> pets = petDAO.getPetsForPerson(personID);
//        for (int i = 0; i < pets.size(); i++) {
//            Pet pet123 = pets.get(i);
//            System.out.println(pet123.getPetId() + " " + pet123.getName() + " " + pet123.getSpecies());
//        }

    }
}





