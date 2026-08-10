import java.util.HashMap;
import java.util.Map;

public class RecurringDecimal {

    static long denominator;
    static long numerator;

    static class RemNext {
        int rem;
        int next;
        public RemNext(int rem, int next) {
            this.rem = rem;
            this.next = next;
        }

        @Override
        public int hashCode() {
            return (int) (rem * denominator + next);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RemNext remNext = (RemNext) o;
            return rem == remNext.rem && next == remNext.next;
        }
    }
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 0) {
            return "0";
        }
        this.denominator = denominator;
        this.numerator = numerator;
        boolean negative = ((numerator & 1 << 31) ^ (denominator & 1 << 31)) != 0;
        if (negative) {
            this.denominator = -denominator;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(this.numerator / this.denominator);
        if (negative) {
            sb.insert(0, '-');
        }
        if (this.numerator % this.denominator == 0) {
            return sb.toString();
        }
        sb.append('.');
        Map<RemNext, Integer> seenPairs = new HashMap<>();
        this.numerator = Math.toIntExact(this.numerator % this.denominator);
        int currPos = sb.length();
        while (this.numerator != 0) {
            int temp  = Math.toIntExact(this.numerator);
            this.numerator *= 10;
            int digit = Math.toIntExact(this.numerator / this.denominator);
            this.numerator = this.numerator % this.denominator;
            RemNext pair = new RemNext(temp, (int) this.numerator);
            if (seenPairs.containsKey(pair)) {
                sb.insert(( int) seenPairs.get(pair), '(');
                sb.append(')');
                return sb.toString();
            }
            sb.append(digit);
            seenPairs.put(pair, currPos++);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        RecurringDecimal r = new RecurringDecimal();
        System.out.println(r.fractionToDecimal(22, 7));
    }
}
