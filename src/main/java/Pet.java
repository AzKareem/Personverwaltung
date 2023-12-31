import java.util.Objects;

public class Pet {
    private int petId;
    private int personId;
    private String name;
    private String species;


    public Pet() {
    }

    public Pet(int personId, String name, String species) {
        this.personId = personId;
        this.name = name;
        this.species = species;
    }

    public int getPetId() {
        return petId;
    }

    public int getPersonId() {
        return personId;
    }

    public String getName() {
        return name;
    }

    public String getSpecies() {
        return species;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return petId == pet.petId && personId == pet.personId && name.equals(pet.name) && species.equals(pet.species);
    }

}
