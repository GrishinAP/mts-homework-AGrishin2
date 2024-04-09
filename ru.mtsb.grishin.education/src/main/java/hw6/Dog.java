package hw6;

import java.time.LocalDate;

public class Dog extends Pet {
    public Dog(String name, Double cost, LocalDate birthday) {
        super();
        this.breed = "Dog";
        this.name  = name;
        this.cost  = cost;
        this.birthday = birthday;
    }
    public Dog() {
        super();
    }
}
