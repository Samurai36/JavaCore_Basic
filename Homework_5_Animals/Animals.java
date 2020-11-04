package Viktor.Homework_5_Animals;

public abstract class Animals {

//    2 Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая
//    или длину препятствия (для бега и плавания), или высоту (для прыжков).

    private String name;
    private int runMax;
    private double swimMax;
    private double jumpMax;
    private boolean canSwim;

    //5* Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
    // решил с помощью конструкторов, где мы можем указать каждому животному при вызове его ограничения

    public Animals(String name, int runMax, double swimMax, double jumpMax, boolean canSwim) {
        this.name = name;
        this.runMax = runMax;
        this.swimMax = swimMax;
        this.jumpMax = jumpMax;
        this.canSwim = canSwim;
    }

    public Animals(String name) {
        this.name = name;
    }
    // по аналогии можно прописать все варианты конструкторов, где будут известны максимальное расстояние бега, либо бега и плаванья и т.п.)
    // и в классе наследники будут по аналогии прописаны вызовы сеттеров

    protected void setRunMax(int runMax) {
        this.runMax = runMax;
    }

    protected void setSwimMax(double swimMax) {
        this.swimMax = swimMax;
    }

    protected void setJumpMax(double jumpMax) {
        this.jumpMax = jumpMax;
    }

    protected void setCanSwim(boolean canSwim) {
        this.canSwim = canSwim;
    }

    //  * 4 При попытке животного выполнить одно из этих действий, оно должно сообщить результат. (Например, dog1.run(150); -> результат: 'Пёсик пробежал!')

    protected void run (int run){
        if (run > 0) {
            if (run <= runMax)
                System.out.println(name + " успешно пробежал(а) " + run + " метров. ");
            else
                System.out.println(name + " не смог(ла) пробежать " + run + " метров, осилив только " + runMax + " метров");
        }
        else System.out.println(name + " не сможет пробежать " + run + " метров, т.к. число отрицательное");
    }

    protected void jump (double jump){
        if (jump > 0) {
            if (jump <= jumpMax)
                System.out.println(name + " успешно прыгнул(а) на " + jump + " метров. ");
            else
                System.out.println(name + " не смог(ла) прыгнуть на " + jump + " метров, осилив только " + jumpMax + " метров");
        }
        else System.out.println(name + " не сможет прыгнуть на " + jump + " метров, т.к. число отрицательное");
    }

    protected void swim (int swim){
        if (swim > 0) {
            if (canSwim) {
                if (swim <= swimMax)
                    System.out.println(name + " успешно проплыл(а) " + swim + " метров. ");
                else {
                    System.out.print(name + " не смог(ла) проплыть " + swim + " метров, осилив только " + swimMax + " метров");
                    System.out.println(". Интересно, куда подевался(ась) " + name + "?");
                }
            }
            else System.out.println(name + " не умеет плавать");
        }
        else System.out.println(name + " не сможет проплыть " + swim + " метров, т.к. число отрицательное");
    }

}
