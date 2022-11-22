package exercise;

import java.util.Map;
import java.util.List;
import java.time.LocalDate;
import java.util.stream.Collectors;

// BEGIN
class Sorter {
    public static List<String> takeOldestMans(List<Map<String, String>> users) {
//        DateSorter sorterDate = new DateSorter();
        return users.stream()
                .filter(user -> user.get("gender").equals("male"))
                .sorted((user1, user2) -> {
                    LocalDate s1 = LocalDate.parse(user1.get("birthday"));
                    LocalDate s2 = LocalDate.parse(user2.get("birthday"));
                    return s1.compareTo(s2);
                })
                .map(user -> user.get("name"))
                .collect(Collectors.toList());
    }
}
//class DateSorter implements Comparator<Map<String, String>> {
//    @Override
//    public int compare(Map<String, String> user1, Map<String, String> user2) {
//        LocalDate s1 = LocalDate.parse(user1.get("birthday"));
//        LocalDate s2 = LocalDate.parse(user2.get("birthday"));
//        return s1.compareTo(s2);
//    }
//}
// END
