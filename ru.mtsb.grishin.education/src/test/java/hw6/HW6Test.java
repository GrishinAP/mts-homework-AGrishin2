package hw6;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;


class HW5Test {
    static LocalDate ld = LocalDate.parse("2000-01-01");

    @DisplayName("Тестируем львов")
    @Test
    void getCharacter(){
        Lion lion;
        lion = new Lion("Friend", 22.5, ld);
        System.out.println("----------  " + lion.getCharacter());
        Assertions.assertEquals("Predator", lion.getCharacter());
    }
    @Test
    void getName(){
        Lion lion=new Lion("Friend", 22.5, ld);
        System.out.println("----------  " + lion.getName());
        Assertions.assertEquals("Friend", lion.getName());
    }
    @Test
    void getBreed(){
        Lion lion;
        lion = new Lion("Friend", 22.5, ld);
        System.out.println("----------  " + lion.getBreed());
        Assertions.assertEquals("Lion", lion.getBreed());
    }
    @Test
    void getBirthdate(){
        Lion lion;
        lion = new Lion("Friend", 22.5, ld);
        System.out.println("----------  " + lion.getBirthday());
        Assertions.assertEquals("2000-01-01", lion.getBirthday().toString());
    }

    @Test
    void getNameNoName(){
        Lion lion;
        lion = new Lion(null, 22.5, ld);
        System.out.println("----------  " + lion.getName());
        Assertions.assertNotEquals("Friend", lion.getName());
    }

    @DisplayName("Тестируем исключение invalidAnimalBirthDateException в CreateAnimalsImpl")
    @Test
    void birthdayException() {
        AbstractAnimal aA = new Lion("Friend", 22.5, null);
        SearchServiceImpl ss = new SearchServiceImpl();
        Assertions.assertThrows(invalidAnimalBirthDateException.class, () ->  ss.searchService(aA));
    }
//    @DisplayName("Тестируем исключение InvalidAnimalException в CreateAnimalsImpl")
//    @Test
//    void animalException() {
//        SearchServiceImpl ss = new SearchServiceImpl();
//        Assertions.assertThrows(InvalidAnimalException.class, () ->  ss.searchService(null));
//    }
    static Animal[] animalArray= {
        new Lion("King", 200.20, LocalDate.parse("2000-01-01")),
        new Lion("Барсик", 201.20,  LocalDate.parse("2001-01-01")),
        new Lion("Дружок", 20.20,  LocalDate.parse("2004-01-01")),
        new Wolf("King", 2.20,  LocalDate.parse("2003-01-01")),
        new Wolf("Барсик", 40.20,  LocalDate.parse("2004-01-01")),
        new Dog("King", 50.20, LocalDate.parse("2008-01-01")),
        new Dog("Барсик", 70.20,  LocalDate.parse("2015-01-01")),
        new Dog("Шарик", 75.20,  LocalDate.parse("2021-01-01")),
        new Cat("King", 1200.20, LocalDate.parse("2024-01-01")),
        new Cat("Барсик", 1500.20,  LocalDate.parse("2017-01-01")),
        new Cat("Пушок", 2000.20,LocalDate.parse("2024-01-01"))
    };
    @DisplayName("Тестируем AnimalRepositoryImpl.findLeapYearNames()")
    @Test
    void testFindLeapYearNames() throws invalidAnimalBirthDateException {
        AnimalRepository ar=new AnimalsRepositoryImpl();
        HashMap<String, LocalDate> res = ar.findLeapYearNames(animalArray);
        System.out.println(res);
        Assertions.assertEquals(6, res.size());
        Assertions.assertEquals(LocalDate.parse("2000-01-01"), res.get(animalArray[0].getBreed() + animalArray[0].getName()));
    }
    @DisplayName("Тестируем AnimalRepositoryImpl.findOlderAnimal()")
    @Test
    void testFindOlderAnimal() {
        AnimalRepository ar=new AnimalsRepositoryImpl() ;
        HashMap<Animal, Integer> res = null;
        HashMap<Animal, Integer> res2 = null;
        try {
            res = ar.findOlderAnimal(animalArray,5);
            res2 = ar.findOlderAnimal(animalArray,100);
            Assertions.assertThrows(NullPointerException.class, () -> ar.findOlderAnimal(null,7));
        } catch (invalidAnimalBirthDateException e) {
            System.out.println("Bad birthday");
        }
        System.out.println(res.get(animalArray[9]));
        Assertions.assertEquals(7, res.get(animalArray[9]));
        System.out.println(res.get(animalArray[0]));
        Assertions.assertEquals(24, res2.get(animalArray[0]));
    }

    @DisplayName("Тестируем AnimalRepositoryImpl.findDuplicate()")
    @Test
    void testFindDuplicate() throws invalidAnimalBirthDateException {
        AnimalRepository ar=new AnimalsRepositoryImpl();
        HashMap<String, List<Animal>> res = ar.findDuplicate(animalArray);
        System.out.println(res.get("Wolf"));
        System.out.println(res.get("Puma"));
        Assertions.assertEquals(2, res.get("Wolf"));
        Assertions.assertNull(res.get("Puma"));
        //Assertions.assertEquals("King", animalArray[3].getName());
    }
}