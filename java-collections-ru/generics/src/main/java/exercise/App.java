package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(
                                            List<Map<String, String>> books,
                                            Map<String, String> where) {

        List<Map<String, String>> searchBooks = new ArrayList<>();
        for (Map<String, String> book: books) {
            boolean hasBook = true;
            for (Map.Entry<String, String> scan: where.entrySet()) {
               String bookValue = book.getOrDefault(scan.getKey(), "");
                if (!bookValue.equals(scan.getValue())) {
                    hasBook = false;
                }

            }
            if (hasBook) {
                searchBooks.add(book);
            }
        }

        return searchBooks;
    }
}
//END
