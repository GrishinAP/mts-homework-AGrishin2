package hw10;

import hw6.AbstractAnimal;

import java.time.LocalDate;

public class Predator extends AbstractAnimal {
    public Predator(){
        super();
        this.character = "Predator";
    }
    public String getBreed(){
        return breed;
    }
    public String getName(){
        return name;
    }
    public Double getCost(){
        return cost;
    }
    public String getCharacter(){
        return character;
    }
    public LocalDate getBirthday(){
        return birthday;
    }
}
