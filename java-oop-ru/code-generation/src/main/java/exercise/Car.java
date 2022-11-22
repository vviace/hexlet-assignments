package exercise;

import lombok.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

// BEGIN
@Getter
@Setter
@AllArgsConstructor
// END
class Car {
    private int id;
    private String brand;
    private String model;
    private String color;
    private User owner;

    // BEGIN
    public static String serialize(Car car) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(car);
    }
    public static Car unserialize(String jsonCar) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonCar, Car.class);
    }
    // END
}
