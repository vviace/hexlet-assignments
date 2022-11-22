package exercise;

import java.util.Map;

// BEGIN
public class SingleTag extends Tag {
    public SingleTag(String name, Map<String, String> attributes) {
        super(name, attributes);
    }
    public String toString() {
        return String.format("<%s%s>", getName(), stringifyAttributes());
    }
}
// END
