package hw10;

import hw6.Predator;
import org.springframework.context.annotation.Bean;

import java.beans.BeanProperty;
import java.time.LocalDate;


public class Lion extends Predator {
        public Lion(String name, Double cost, LocalDate birthday){
            super();
            this.breed = "Lion";
            this.name  = name;
            this.cost  = cost;
            this.birthday = birthday;
        }
    public Lion() {
        super();
    }
}
