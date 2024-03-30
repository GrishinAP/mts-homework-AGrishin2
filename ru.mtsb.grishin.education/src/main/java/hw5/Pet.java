package hw5;

import java.time.LocalDate;

public class Pet extends AbstractAnimal {
    public Pet(){
        super();
        this.character = "Pet";
    }
    public String getBreed(){
        return breed;
    }
    public String getName(){
        return this.name;
    }
    public Double getCost(){
        return this.cost;
    }
    public String getCharacter(){
        return this.character;
    }
    public LocalDate getBirthday(){ return this.birthday; }
}
