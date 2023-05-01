package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Task1 {
    //1. Дана строка sql-запроса "select * from students where ". Сформируйте часть WHERE этого запроса,
    // используя StringBuilder. Данные для фильтрации приведены ниже в виде json-строки.
    //Если значение null, то параметр не должен попадать в запрос.
    //Пример 1:
    //Параметры для фильтрации: {"name:Ivanov", "country:Russia", "city:Moscow", "age:null"}
    //Результат: SELECT * FROM USER WHERE name = 'Ivanov' and country = 'Russia' and city = 'Moscow';
    //Пример 2:
    //Параметры для фильтрации: {"name:null", "country:null", "city:null", "age:null"}
    //Результат: SELECT * FROM USER;

    public static void main(String[] args) {
        String json = "{\"name\":\"Ivanov\",\"country\":\"Russia\",\"city\":\"Moscow\",\"age\":null}"; // пример JSON-строки
        //String json = "{\"name:null\", \"country:null\", \"city:null\", \"age:null\"}";

        // извлекаем значения полей с помощью регулярных выражений
        Pattern pattern = Pattern.compile("\"name\":\"(.*?)\".*?\"country\":\"(.*?)\".*?\"city\":\"(.*?)\".*?\"age\":(.*?)(,|})");
        Matcher matcher = pattern.matcher(json);

        String name = null;
        String country = null;
        String city = null;
        Integer age = null;

        if (matcher.find()) {
            name = matcher.group(1);
            country = matcher.group(2);
            city = matcher.group(3);
            String ageString = matcher.group(4);
            if (!"null".equals(ageString)) {
                age = Integer.parseInt(ageString);
            }
        }

        StringBuilder request = new StringBuilder("SELECT * FROM students WHERE ");
        // формируем запрос с условием WHERE, добавляя в него только поля, значение которых не равно null
        boolean needAnd = false;
        if (name != null) {
            request.append("name = '").append(name).append("'");
            needAnd = true;
        }
        if (country != null) {
            if (needAnd) {
                request.append(" and ");
            }
            request.append("country = '").append(country).append("'");
            needAnd = true;
        }
        if (city != null) {
            if (needAnd) {

                request.append(" and ");
            }
            request.append("city = '").append(city).append("'");
            needAnd = true;
        }
        if (age != null) {
            if (needAnd) {
                request.append(" and ");
            }
            request.append("age = ").append(age);
        }

        System.out.println(request);
    }
}
