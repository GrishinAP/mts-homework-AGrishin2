package hw10;

import hw6.Pet;

import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String name, Double cost, LocalDate birthday){
        super();
        this.breed = "Cat";
        this.name  = name;
        this.cost  = cost;
        this.birthday = birthday;
    }
    public Cat() {
        super();
    }

    @Override
    public void setSecretInformation(String secret) {

    }
}
