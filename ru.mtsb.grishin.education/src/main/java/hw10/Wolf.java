package hw10;

import hw6.Predator;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Wolf extends Predator {
    public Wolf(String name, Double cost, LocalDate birthday)
    {
        super();
        this.breed = "Wolf";
        this.name  = name;
        this.cost  = cost;
        this.birthday = birthday;
    }
    public Wolf() {
        super();
    }
}
