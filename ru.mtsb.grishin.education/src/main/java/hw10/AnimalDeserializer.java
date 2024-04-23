package hw10;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import hw6.Animal;
import hw6.Cat;
import hw6.Dog;
import hw6.Lion;
import hw6.Wolf;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Base64;

public class AnimalDeserializer extends StdDeserializer<hw6.Animal> {
//    public AnimalDeserializer() {
//        this(null);
//    }

    public AnimalDeserializer(Class<?> vc) {
        super(vc);
    }


    public hw6.Animal deserialize(JsonParser parser, DeserializationContext deserializer) throws IOException {

        Animal animal=null;
        String breed="";
        while(!parser.isClosed()){
            JsonToken jsonToken = parser.nextToken();

            if(JsonToken.FIELD_NAME.equals(jsonToken)){


                String fieldName = parser.getCurrentName();
                System.out.println(fieldName);

                jsonToken = parser.nextToken();

                if("breed".equals(fieldName)){
                    breed=parser.getValueAsString();
                    if(breed.equals("Dog")){animal=new Dog();}
                    if(breed.equals("Cat")){animal=new Cat();}
                    if(breed.equals("Wolf")){animal=new Wolf();}
                    if(breed.equals("Lion")){animal=new Lion();}
                    animal.setBreed(parser.getValueAsString());
                } else if ("name".equals(fieldName)){
                    animal.setName(parser.getValueAsString());
                } else if("cost".equals(fieldName)){
                    animal.setCost(parser.getValueAsDouble());
                } else if("birthday".equals(fieldName)){
                    //animal.setBirthday(LocalDate.parse(parser.getValueAsString(), DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                    animal.setBirthday(LocalDate.parse(parser.getValueAsString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                } else if("secretInformation".equals(fieldName)){
                    animal.setCharacter(Arrays.toString(Base64.getDecoder().decode(parser.getValueAsString().getBytes())));
                }
            }
        }
        return animal;
    }



}
