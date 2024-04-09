package hw6;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

class AnimalSerializer extends StdSerializer<Animal> {
    //DateTimeFormatter df = new DateTimeFormatter("dd-MM-yyyy hh:mm");
    protected AnimalSerializer(Class<Animal> t) {
        super(t);
    }

    public void serialize(Animal animal, JsonGenerator jsonGenerator,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("character", animal.getCharacter());
        jsonGenerator.writeStringField("breed", animal.getBreed());
        jsonGenerator.writeStringField("name", animal.getName());
        jsonGenerator.writeNumberField("cost", animal.getCost());
        jsonGenerator.writeStringField("birthday", animal.getBirthday().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        jsonGenerator.writeBinaryField("secretInformation", Base64.getEncoder().encodeToString(animal.getSecretInformation().getBytes()).getBytes());
        jsonGenerator.writeEndObject();
    }
}
