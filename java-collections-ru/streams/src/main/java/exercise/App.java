package exercise;

import java.util.List;
import java.util.Arrays;

// BEGIN
class App {
    private static final List<String> FREE_DOMAINS = Arrays.asList(
            "yandex.ru",
            "gmail.com",
            "hotmail.com"
    );
    public static long getCountOfFreeEmails(List<String> emails) {

        long result = emails.stream()
                .map(email -> email.split("@")[1])
                .filter(FREE_DOMAINS::contains)
                .count();
        //System.out.println(Arrays.toString(result.toArray()));
        return result;
    }
}
// END
