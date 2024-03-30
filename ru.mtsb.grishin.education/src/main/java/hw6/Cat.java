package hw6;

import java.time.LocalDate;

public class Cat extends Pet {
    public Cat(String name, Double cost, LocalDate birthday){
        super();
        this.breed = "Cat";
        this.name  = name;
        this.cost  = cost;
        this.birthday = birthday;
    }
}
