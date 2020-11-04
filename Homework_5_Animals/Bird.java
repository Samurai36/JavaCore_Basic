package Viktor.Homework_5_Animals;

/*3 У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м., Лошадь 1500 м., Птица 5 м.; прыжок: кот 2 м.,
    собака 0.5 м., Лошадь 3 м., Птица 0.2 м. ; плавание: кот и птица не умеет плавать, собака 10 м., лошадь 100 м.).*/

public class Bird extends Animals {

    public Bird(String name, int runMax, int swimMax, double jumpMax, boolean canSwim) {
        super(name, runMax, swimMax, jumpMax, canSwim);
    }
    public Bird(String name) {
        super(name);
        setRunMax(5);
        setJumpMax(0.2);
        setCanSwim(false);
    }
}
