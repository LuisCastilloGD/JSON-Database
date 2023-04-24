class Seven {
    public static SeptenaryStringFunction fun = (s1,s2,s3,s4,s5,s6,s7)->{
        String res = "";
        res = res.concat(s1.toUpperCase());
        res = res.concat(s2.toUpperCase());
        res = res.concat(s3.toUpperCase());
        res = res.concat(s4.toUpperCase());
        res = res.concat(s5.toUpperCase());
        res = res.concat(s6.toUpperCase());
        res = res.concat(s7.toUpperCase());
        return res;
    };
}

@FunctionalInterface
interface SeptenaryStringFunction {
    String apply(String s1, String s2, String s3, String s4, String s5, String s6, String s7);
}