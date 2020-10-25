package Viktor;

/*
Написать метод, который определяет является ли год високосным,
и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
*/

public class Main {

    public static void main(String[] args) {
	    godVisokosniy(2020);
    	godVisokosniy(2021);
        godVisokosniy(4);
        godVisokosniy(100);
        godVisokosniy(-4900);
    }
    static void godVisokosniy (int a) {
        if (a>0) {
            if ((a % 4 == 0) && (a % 100 != 0) || (a % 400 == 0))
                System.out.println(a + ": високосный год");
            else
                System.out.println(a + ": не високосный год");
        }
        else
            System.out.println("введите положительное число");
    }
}
