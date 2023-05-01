package org.example;

import java.io.File;


public class Tasks2 {
    public static void main(String[] args) {
        //2.Напишите метод, который определит тип (расширение) файлов
        // из текущей папки и выведет в консоль результат вида:
        //    1 Расширение файла: txt
        //    2 Расширение файла: pdf
        //    3 Расширение файла:
        //    4 Расширение файла: jpg

        ex("C:\\Users\\31m34\\IdeaProjects\\homeWorkJava2\\.idea\\SomeFiles");
    }

    private static void ex(String pathDir) {
        File file= new File(pathDir);
        if (!(file.exists() || file.isDirectory())){
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (String extension : file.list()) {
            String[] ff = extension.split("\\.");
            for (int i = 0; i < ff.length; i++) {
                if (i % 2 == 1) {
                    sb.append("Расширение файла: ").append(ff[i]).append(System.lineSeparator());
                }

            }
        }
        System.out.println(sb);
    }
}