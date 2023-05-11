package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search_Aeroport {

    public void read_data(String name){
        String holder;
        BufferedReader reader;
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher;
        int counter=0;
        long start=System.currentTimeMillis();
        try {
            reader=new BufferedReader(new FileReader("src/main/resources/airports.csv"));
        while ((holder=reader.readLine())!=null){
            matcher= pattern.matcher(holder);
            matcher.find();
            if (matcher.group(1).toLowerCase().startsWith(name.toLowerCase())) {
                System.out.println(matcher.group() + "[" + holder + "]");
            counter++;
            }
            else continue;
        }
        long end=System.currentTimeMillis();
            System.out.printf("Количество найденных строк: %d  Время, затраченное на поиск: %d мс \n", counter,end-start);
        reader.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }
    }
    public void read_data(String search_query,String filter){
        ArrayDeque<String> stack=StringToRPN.toRPN(filter);
        BufferedReader reader;
        Pattern pattern = Pattern.compile("\"(.*?)\"");
        Matcher matcher;
        int counter=0;
        String holder;
        Calculate calculate=new Calculate();
        Pattern pat = Pattern.compile("(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(.*?),(\".*?\")");
        Matcher m;

        long start=System.currentTimeMillis();
        try {
            reader=new BufferedReader(new FileReader("src/main/resources/airports.csv"));
            while ((holder=reader.readLine())!=null){
                matcher= pattern.matcher(holder);
                matcher.find();
                if (search_query.equals("")||matcher.group(1).toLowerCase().startsWith(search_query.toLowerCase())) {
                    m = pat.matcher(holder);
                    m.find();

                    if (calculate.beginCalculation(stack.clone(), m)) {
                        System.out.println(matcher.group() + "[" + holder + "]");
                        counter++;
                    }else continue;
                }
                else continue;
            }
            long end=System.currentTimeMillis();
            System.out.printf("Количество найденных строк: %d  Время, затраченное на поиск: %d мс \n", counter,end-start);
            reader.close();
        } catch (IOException e) {
            System.out.println("Файл не найден");
        }

    }
}
