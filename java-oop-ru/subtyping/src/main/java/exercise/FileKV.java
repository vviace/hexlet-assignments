package exercise;

import java.util.HashMap;
import java.util.Map;

// BEGIN
public class FileKV implements KeyValueStorage{

    private final String path;
    public FileKV(String path, Map<String, String> initial) {
        Utils.writeFile(path, Utils.serialize(initial));
        this.path = path;
    }
    @Override
    public String set(String key, String value) {
        String fileStorage  = Utils.readFile(path);
        Map<String, String> storageMap = Utils.unserialize(fileStorage);
        storageMap.put(key, value);
        return Utils.serialize(storageMap);
    }

    @Override
    public void unset(String key) {
        String fileStorage  = Utils.readFile(path);
        Map<String, String> storageMap = Utils.unserialize(fileStorage);
        storageMap.remove(key);
        Utils.writeFile(path, Utils.serialize(storageMap));
    }

    @Override
    public String get(String key, String defaultValue) {
        String fileStorage  = Utils.readFile(path);
        Map<String, String> storageMap = Utils.unserialize(fileStorage);
        return storageMap.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String fileStorage  = Utils.readFile(path);
        return Utils.unserialize(fileStorage);
    }
}
// END
