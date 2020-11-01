package Viktor;

import java.sql.SQLOutput;

/**
 * 1. Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
 * 2. Конструктор класса должен заполнять эти поля при создании объекта;
 * 3. Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;
 * 4. Вывести при помощи методов из пункта 3 ФИО и должность.
 * 5. Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
 * 6* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000;
 * 7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.
 * */

public class Main {

    public static void main(String[] args) {

        Sotrudnik sotrudnik1 = new Sotrudnik("Иванов Иван Иванович", "образец для заявлений", "89012345678", 99999, 30 );
        Sotrudnik sotrudnik2 = new Sotrudnik("Климденбын Иракез Ярославович", "генеральный директор", "89009009090", 100000000, 60 );
        Sotrudnik sotrudnik3 = new Sotrudnik("Харитонова Ульяна Яковлевна", "главбух", "88005353535", 300000, 39 );
        Sotrudnik sotrudnik4 = new Sotrudnik("Богатырь Леопольд Ясносолнышкин", "директор службы безопаности", "89005731975", 354874, 33 );
        Sotrudnik sotrudnik5 = new Sotrudnik("Диалогов Андрей Йордомиров", "сисадмин", "89002561024", 10240, 50 );

        // 5. Создать массив из 5 сотрудников. С помощью цикла вывести информацию только о сотрудниках старше 40 лет;

        Sotrudnik[] arraySotrudnikov = new Sotrudnik[5];

        arraySotrudnikov[0] = (sotrudnik1);
        arraySotrudnikov[1] = (sotrudnik2);
        arraySotrudnikov[2] = (sotrudnik3);
        arraySotrudnikov[3] = (sotrudnik4);
        arraySotrudnikov[4] = (sotrudnik5);

        System.out.println("Список сотрудников: ");

        for (int i = 0; i < 5; i++) {
            arraySotrudnikov[i].printInfo();
        }

        System.out.println();

        System.out.println("Сотрудники старше 40 лет:");

        for (int i = 0; i < 5; i++) {
            if (arraySotrudnikov[i].getVozrast() >= 40)
                arraySotrudnikov[i].printInfo();
        }
        for (int i = 0; i < 5; i++) {
            arraySotrudnikov[i].upZarplataVozrast45 ();
        }

        System.out.println();

        System.out.println("Сотрудника старше 45 лет повысили зарплату на 5000:");

        for (int i = 0; i < 5; i++) {
            if (arraySotrudnikov[i].getVozrast() >= 45)
                arraySotrudnikov[i].printInfo();
        }

    }

}
