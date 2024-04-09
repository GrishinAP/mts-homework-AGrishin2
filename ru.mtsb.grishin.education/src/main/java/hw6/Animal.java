package hw6;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.*;

public interface Animal {
    static final String DIR = "src/main/resources/secretStore/";
     static final String PATH = DIR+"secretInformation.txt";

     String getBreed();
     String getName();
     Double getCost();
     String getCharacter();
     LocalDate getBirthday();

     default String getSecretInformation(){
         if(Files.notExists(Path.of(PATH))) {
             throw new RuntimeException("Секрет безвозвратно утерян. :=(");
         }
          String res="";
          List<String> list=new ArrayList<>();
          RandValues<String> rv=new RandValues<>();
         try(RandomAccessFile rAF=new RandomAccessFile(PATH, "rw");
             FileChannel channel = rAF.getChannel();
             ) {
             File secret = new File(PATH);
             Scanner scanner = new Scanner(secret);
             while(scanner.hasNextLine()){
                 list.add(scanner.nextLine());
             }
             res=rv.listItem(list);

          } catch (FileNotFoundException e) {
               throw new RuntimeException(e);
          } catch (IOException e) {
               throw new RuntimeException(e);
          }

          return res;
     };
    void setBreed(String breed);
    void setName(String name);
    void setCost(Double cost);
    void setCharacter(String character);
    void setBirthday(LocalDate localDate);
    void setSecretInformation(String secret);

}
