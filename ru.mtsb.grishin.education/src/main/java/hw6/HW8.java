package hw6;


import java.util.*;

public class HW8 {
    public static void main(String[] args) {
        System.out.println("Приимер использования атомарного целого числа");
        AtomicExample exmpl = new AtomicExample();
        for (int i = 0; i < 10; i++) {
            System.out.print(i+" ");
            exmpl.runExampl(10, 100);
        }
        System.out.println("Приимер поиска простых чисел");
        SimpleExample simple = new SimpleExample(100,10);
        simple.getSimpleNumbers();

        System.out.println("Приимер многопоточной сортировки целых чисел");
        SortExample sortExmpl = new SortExample(3,10,10);
        //sortExmpl.getSortRandIntList();
        System.out.println("Total finish result "+sortExmpl.getSortRandIntList());

    }
 }

