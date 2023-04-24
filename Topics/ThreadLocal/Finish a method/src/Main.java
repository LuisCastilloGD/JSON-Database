class UseThreadLocal {


    public static ThreadLocal<Integer> makeThreadLocal(int counter) {
        ThreadLocal<Integer> threadLocalValue = new ThreadLocal<>();
        threadLocalValue.set(counter+1);
        return threadLocalValue;
    }
}