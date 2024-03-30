package hw5;


import java.time.LocalDate;

public abstract class AbstractAnimal implements Animal {
    protected String breed; //порода
    protected String name; //имя
    protected Double cost; //цена в магазине
    protected String character; //характер
    protected LocalDate birthday; // день рождения


    public abstract String getBreed();
    public abstract String getName();
    public abstract Double getCost();
    public abstract String getCharacter();
    public abstract LocalDate getBirthday();
}
