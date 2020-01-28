public class Calculator {

    public int add(int a, int b) {
        return a + b;
    }

    public static int getNumberOfMaxParam(int a, int b, int c) {
        int max1 = Math.max(a,b);
        int max = Math.max(max1,c);
        if(max==a) {
            return 1;
        } else if (max==b) {
            return 2;
        } else {
            return 3;
        }
    }

}
