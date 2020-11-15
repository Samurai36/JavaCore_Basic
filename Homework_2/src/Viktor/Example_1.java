package Viktor;
/*
Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
Написать метод, заменяющий в принятом массиве 0 на 1, 1 на 0;
*/

public class Example_1 {
    public static void main(String[] args) {
        int[] arr = new int[(int) (Math.random() * 21)]; //с помощью метода Math.random генерируем случайное число в диапазоне
                                                          // от 0,0 дл 20,9 и т.к. количество ячеек должно быть целочисленное явно приводим его к integer
        for (int i = 0; i < arr.length; i++) { //с помощью цикла for перебираем все ячейки массива
            arr[i] = 0 + (int) (Math.random() * 2); // заполняем ячейки массива случайным числом от 0.0 до 1.99 с помощью Math.random
                                                    // и также присваиваем тип int
            System.out.print(arr[i] + " "); // выводим каждую ячейку массива через пробел
        }
        System.out.println(); // переход на новую строку
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (arr[i] == 1) ? 0 : 1; // проверяем каждую ячейку и сравниваем ее значение, если оно равняется 1, то меняем на 0, если нет, то меняем на 1
            System.out.print(arr[i] + " "); // выводим каждую ячейку массива через пробел
        }
    }
}