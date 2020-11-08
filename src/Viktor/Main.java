package Viktor;

/*
 * 1 Создать 2 текстовых файла, примерно по 50-100 символов в каждом (особого значения не имеет);
 * 2 Написать программу, «склеивающую» эти файлы, то есть вначале идет текст из первого файла, потом текст из второго.
 * 3 Написать программу, которая проверяет присутствует ли указанное пользователем слово в файле.
 * 4* Написать метод, проверяющий, есть ли указанное слово в папке, внутри есть файлы, в которых может содержатся это слово
 * */


import java.io.*;

public class Main {

    public static void main(String[] args) {
        skleivanieTextov("src//Viktor//papka_textov//Kung Lao.txt",
                "src//Viktor//papka_textov//Megaman.txt", "src//Viktor//papka_textov//общий text.txt");
        poiskSlovaVTexte("src//Viktor//papka_textov//Kung Lao.txt", "pit");
        PoiskVKataloge("src//Viktor//papka_textov", "Галереи");
    }

    static void skleivanieTextov(String file1, String file2, String file3) {
        try {
            FileInputStream fin = new FileInputStream(file1);
            FileInputStream fin2 = new FileInputStream(file2);
            FileOutputStream fos = new FileOutputStream(file3);
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
            fos.write(buffer, 0, buffer.length);
            fos.write("\n".getBytes());
            byte[] buffer2 = new byte[fin2.available()];
            fin2.read(buffer2, 0, buffer2.length);
            fos.write(buffer2, 0, buffer2.length);
            System.out.println("Тексты " + file1 + " и " + file2 + " успешно объединены в файл " + file3);
            System.out.println();

            fos.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static void poiskSlovaVTexte(String file, String search) {

        try {
            LineNumberReader fr = new LineNumberReader(new BufferedReader(new FileReader(file)));
            String s;
            while (true) {
                s = fr.readLine();
                if (s == null)
                    break;
                if (s.toLowerCase().indexOf(search.toLowerCase()) != -1) {
                    System.out.println("Слово: " + search + " - найдено в строке №" + fr.getLineNumber());
                } else System.out.println("Слово: " + search + " - в строке №" + fr.getLineNumber() + " не найдено");
            }
            System.out.println();
            fr.close();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    static void PoiskVKataloge(String path, String search) {

        try {
            File dir = new File(path);

            if (dir.isDirectory()) {

                for (File item : dir.listFiles()) {

                    if (item.isFile()) {
                        LineNumberReader fr = new LineNumberReader(new BufferedReader(new FileReader(item)));
                        System.out.println(item.getName() + " файл");
                        String s1;
                        while (true) {
                            s1 = fr.readLine();
                            if (s1 == null)
                                break;

                            if (s1.toLowerCase().indexOf(search.toLowerCase()) != -1) {
                                System.out.println("Слово: " + search + " - найдено в строке №" + fr.getLineNumber());
                            } else
                                System.out.println("Слово: " + search + " - в строке №" + fr.getLineNumber() + " не найдено");
                        }
                        System.out.println();
                        fr.close();
                    }
                }
            }


        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }


}
