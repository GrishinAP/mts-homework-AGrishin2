package hw10;


import hw6.Animal;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;

public abstract class AbstractAnimal implements Animal {
    protected String breed; //порода
    protected String name; //имя
    protected Double cost; //цена в магазине
    protected String character; //характер
    protected LocalDate birthday; // день рождения
    protected String secretInformation; //секрет

    public AbstractAnimal(){
        secretInformation=getSecretInformation();
    }

    public abstract String getBreed();
    public abstract String getName();
    public abstract Double getCost();
    public abstract String getCharacter();
    public abstract LocalDate getBirthday();
    public void setSecretInformation(String  secret){
        this.secretInformation = Arrays.toString(Base64.getDecoder().decode(secret.getBytes()));
    };
    public void setBreed(String breed){
        this.breed=breed;
    };
    public void setName(String name){
        this.name=name;
    };
    public void setCost(Double cost){
        this.cost=cost;
    };
    public void setCharacter(String character){
        this.character=character;
    };
    public void setBirthday(LocalDate birthday){
        this.birthday=birthday;
    };
}
