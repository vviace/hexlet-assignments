package exercise;

// BEGIN
public class ReversedSequence implements CharSequence {
    private final String reversed;
    public ReversedSequence(String original) {
        StringBuilder result = new StringBuilder(original);
        this.reversed = result.reverse().toString();
    }
    @Override
    public int length() {
        return reversed.length();
    }

    @Override
    public char charAt(int index) {
        return reversed.charAt(index);
    }
    @Override
    public String toString() {
        return reversed;
    }
    @Override
    public CharSequence subSequence(int i, int i1) {
        return reversed.subSequence(i, i1);
    }


}
// END
