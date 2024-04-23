package hw10;


import hw6.AtomicExample;
import hw6.SimpleExample;
import hw6.SortExample;

public class HW8 {
    public static void main(String[] args) {
        System.out.println("Приимер использования атомарного целого числа");
        hw6.AtomicExample exmpl = new AtomicExample();
        for (int i = 0; i < 10; i++) {
            System.out.print(i+" ");
            exmpl.runExampl(10, 100);
        }
        System.out.println("Приимер поиска простых чисел");
        hw6.SimpleExample simple = new SimpleExample(100,10);
        simple.getSimpleNumbers();

        System.out.println("Приимер многопоточной сортировки целых чисел");
        hw6.SortExample sortExmpl = new SortExample(3,10,10);
        //sortExmpl.getSortRandIntList();
        System.out.println("Total finish result "+sortExmpl.getSortRandIntList());

    }
 }

