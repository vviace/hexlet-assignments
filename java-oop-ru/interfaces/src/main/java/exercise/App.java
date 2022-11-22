package exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
// BEGIN
public class App {
    public static List<String> buildAppartmentsList(List<Home> homeList, int n) {
        List<Home> sortedHomeList = homeList.stream()
                .sorted(Comparator.comparingDouble(Home::getArea))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        int count = 0;
        for (Home home : sortedHomeList) {
            if (count != n) {
                result.add(home.toString());
                count++;
            }
        }
        return result;
    }
}
// END
