package Viktor;

public class Main {

/*
Написать метод, принимающий на вход два числа и проверяющий, что их сумма лежит в пределах от 10 до 20 (включительно),
если да – вернуть true, в противном случае – false.
*/

    public static void main(String[] args) {

        System.out.println(summaChisel(5, 15));
        System.out.println(summaChisel(5, 5));
        System.out.println(summaChisel(10, 11));

    }
    static boolean summaChisel (int a, int b){
        int summa = a + b;
        return summa >9 && summa <=20 ? true : false;
    }
}
