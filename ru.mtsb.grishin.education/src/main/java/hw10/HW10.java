package hw10;

import hw6.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HW10 {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AnimalApplicationContextConfiguration.class);
        String[] arr = ctx.getBeanDefinitionNames();
        for(String s : arr) System.out.println("++++++"+ s);
        Lion lion= ctx.<Lion>getBean("getLion", Lion.class);
        Wolf wolf= ctx.<Wolf>getBean("getWolf", Wolf.class);
        Cat cat= ctx.<Cat>getBean("getCat", Cat.class);
        Dog dog= ctx.<Dog>getBean("getDog", Dog.class);
        System.out.println("---" + lion.getCharacter()+" "+lion.getBreed()+" "+lion.getName()+" "+lion.getBirthday()+" "+lion.getCost()+" "+lion.getSecretInformation());
        System.out.println("---" + wolf.getCharacter()+" "+wolf.getBreed()+" "+wolf.getName()+" "+wolf.getBirthday()+" "+wolf.getCost()+" "+wolf.getSecretInformation());
        System.out.println("---" + cat.getCharacter()+" "+cat.getBreed()+" "+cat.getName()+" "+cat.getBirthday()+" "+cat.getCost()+" "+cat.getSecretInformation());
        System.out.println("---" + dog.getCharacter()+" "+dog.getBreed()+" "+dog.getName()+" "+dog.getBirthday()+" "+dog.getCost()+" "+dog.getSecretInformation());
        Map<String, List<Animal>> animalMap =(Map<String, List<Animal>>) ctx.getBean("createAnimals");
        for(String breed : animalMap.keySet()) {
            for(Animal animal : animalMap.get(breed)) {
                System.out.println("Breed="+breed+"  "+animal.getCharacter() + "---" + animal.getName() + " " + animal.getBirthday() + " " + animal.getCost() + " " + animal.getSecretInformation());
            }
        }
    }
}
