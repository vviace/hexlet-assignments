package exercise;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class Validator {
    public static List<String> validate(Object object) {
       List<Field> fields = List.of(object.getClass().getDeclaredFields());
       List<String> filteredFields = fields.stream()
               .filter(field -> field.isAnnotationPresent(NotNull.class))
               .filter(field -> {
                   Object value;
                   try {
                       field.setAccessible(true);
                       value = field.get(object);
                       field.setAccessible(false);
                   } catch (IllegalAccessException e) {
                       throw new RuntimeException(e);
                   }
                   return value == null;
               })
               .map(field -> field.getName())
               .collect(Collectors.toList());
        return filteredFields;
    }
}
// END
