package exercise;

import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends Tag {
    private String body;
    private List<Tag> child;
    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> child) {
        super(name, attributes);
        this.body = body;
        this.child = child;
    }
    public String toString() {
        String name = getName();
        String attributes = stringifyAttributes();
        String value = "";

        child.stream()
                .map(Object::toString)
                .collect(Collectors.joining(""));

        return String.format("<%s%s>%s%s</%s>", name, attributes, body, value, name);
    }
}
// END
