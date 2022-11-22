package exercise;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

import static exercise.Car.serialize;

// BEGIN
class App {
    public static void save(Path path, Car car) throws IOException {
        Files.writeString(path, Car.serialize(car), StandardOpenOption.WRITE);
    }
    public static Car extract(Path path) throws IOException {
       String read = Files.readString(path);
       return Car.unserialize(read);
    }

}
// END
