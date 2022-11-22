package exercise;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class SorterTest {
    @Test
    void sorterTest() {
        List<Map<String, String>> users = List.of(
                Map.of("name", "Vladimir Nikolaev",
                        "birthday", "1990-12-27",
                        "gender", "male"),
                Map.of("name", "Andrey Petrov",
                        "birthday", "1989-11-23",
                        "gender", "male"),
                Map.of("name", "Anna Sidorova",
                        "birthday", "1996-09-09",
                        "gender", "female"),
                Map.of("name", "John Smith",
                        "birthday", "1989-03-11",
                        "gender", "male"),
                Map.of("name", "Vanessa Vulf",
                        "birthday", "1985-11-16",
                        "gender", "female"),
                Map.of("name", "Alice Lucas",
                        "birthday", "1986-01-01",
                        "gender", "female"),
                Map.of("name", "Elsa Oscar",
                        "birthday", "1970-03-10",
                        "gender", "female")
        );
        List<Map<String, String>> actualUsers = List.of(
                Map.of("name", "John Smith",
                        "birthday", "1989-03-11",
                        "gender", "male"),
                Map.of("name", "Andrey Petrov",
                        "birthday", "1989-11-23",
                        "gender", "male"),
                Map.of("name", "Vladimir Nikolaev",
                        "birthday", "1990-12-27",
                        "gender", "male")
        );
        List<String> mans = Sorter.takeOldestMans(users);
        List<String> actual = Sorter.takeOldestMans(actualUsers);
        assertThat(actual).isEqualTo(mans);
    }
}
// END


