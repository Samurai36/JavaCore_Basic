package Viktor;

public class Main {

    // Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат, где a, b, c, d – входные параметры этого метода.

    public static void main(String[] args) {

        System.out.println(Term(2.2,-5,7,8));
    }
    static double Term (double a, double b, double c , double d) {
        double result = a * (b + (c / d));
        return result;
    }
}
