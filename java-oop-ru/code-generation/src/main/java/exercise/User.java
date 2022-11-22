package exercise;

import lombok.*;

// BEGIN
@Getter
@Setter
@AllArgsConstructor
// END
class User {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
}
