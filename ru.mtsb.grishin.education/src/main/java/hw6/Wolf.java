package hw6;

import java.time.LocalDate;

public class Wolf extends Predator {
    public Wolf(String name, Double cost, LocalDate birthday)
    {
        super();
        this.breed = "Wolf";
        this.name  = name;
        this.cost  = cost;
        this.birthday = birthday;
    }
}
