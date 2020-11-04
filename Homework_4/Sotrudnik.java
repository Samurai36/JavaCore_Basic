package Viktor;
// 1. Создать класс "Сотрудник" с полями: ФИО, должность, телефон, зарплата, возраст;
public class Sotrudnik {
    private String FIO;
    private String dolzhnost;
    private String telNomer;
    private int zarplata;
    private int vozrast;
    private int id;
    private static int count = 1;

    // 2. Конструктор класса должен заполнять эти поля при создании объекта;

    Sotrudnik (String FIO, String dolzhnost, String telNomer, int zarplata, int vozrast) {

        this.FIO = FIO;
        this.dolzhnost = dolzhnost;
        this.telNomer = telNomer;
        this.zarplata = zarplata;
        this.vozrast = vozrast;

    // 7** При создании экземпляра класса Сотрудник присваивать ему уникальный порядковый номер.
        id=count++;
    }

    int getiD() {
        return id;
    }

    // 3. Внутри класса «Сотрудник» написать методы, которые возвращают значение каждого поля;

    String getFIO() {
        return FIO;
    }

    String getDolzhnost() {
        return this.dolzhnost;
    }

    String getTelNomer() {
        return telNomer;
    }

    void setZarplata(int zarplata) {
        this.zarplata = zarplata + 5000;
    }

    int getZarplata() {
        return this.zarplata;
    }

    int getVozrast() {
        return this.vozrast;
    }

    // 4. Вывести при помощи методов из пункта 3 ФИО и должность.

    String getSotrudnikFIODolzghnost() {
        return "Сотрудник " + getFIO() + " в должности: " + getDolzhnost();
    }

    //    6* Создать метод, повышающий зарплату всем сотрудникам старше 45 лет на 5000;

    int upZarplataVozrast45 () {
        if (vozrast >= 45)
            return this.zarplata+=5000;
        return this.zarplata;
    }

    public void printInfo (){
        System.out.print("Cотрудник: " + getiD() + ". " + this.FIO);
        System.out.print(" в должности - " + this.dolzhnost);
        System.out.print(" . Тел.: " + this.telNomer);
        System.out.print(". Зарплата - " + this.zarplata + " руб.");
        System.out.print(" Возраст " + this.vozrast + " лет");
        System.out.println();
    }

}
