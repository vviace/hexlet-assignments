package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    private Map<String, String> storageDB = new HashMap<>();
    public InMemoryKV(Map<String, String> initial) {
        storageDB.putAll(initial);
    }
    @Override
    public String set(String key, String value) {
        return storageDB.put(key, value);
    }

    @Override
    public void unset(String key) {
         storageDB.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return storageDB.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storageDB);
    }
}
// END
