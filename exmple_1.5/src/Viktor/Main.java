package Viktor;

/*
Создать метод, который принимает число. Если данное число больше 100 и меньше 999 включительно -
вывести в консоль цифры данного числа в обратном порядке. Например, введено число 725 -> в консоле будет: 527.
*/

public class Main {

    public static void main(String[] args) {
        chislo(517);
        chislo(34);
        chislo(8297);
        chislo(499);
    }
    static void chislo (int a) {
        if (a > 99 && a <= 999) {
            int reverse = 0;
            while (a != 0) {
                reverse = reverse * 10 + (a % 10);
                a = a / 10;
            }
            System.out.println(reverse);
        }
    }
}
