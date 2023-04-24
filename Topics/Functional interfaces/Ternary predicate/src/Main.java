import java.util.Objects;

class Predicate {
    public static final TernaryIntPredicate ALL_DIFFERENT = (Integer a,Integer b,Integer c)-> !Objects.equals(a, b) && !Objects.equals(b, c) && !Objects.equals(a, c) ;

    @FunctionalInterface
    public interface TernaryIntPredicate {
        public boolean test(Integer a,Integer b,Integer c);
    }
}