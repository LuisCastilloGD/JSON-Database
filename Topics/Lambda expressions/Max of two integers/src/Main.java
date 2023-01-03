import java.util.Map;
import java.util.function.IntBinaryOperator;

class Operator {

    public static IntBinaryOperator binaryOperator = (x,y) -> {
return Math.max(x,y);
    };
}