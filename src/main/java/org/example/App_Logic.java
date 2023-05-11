package org.example;

import java.util.Scanner;

public class App_Logic{
    private Scanner in= new Scanner(System.in);
    Search_Aeroport search;
    public void start_App(){
        while (true) {

            System.out.print("Введите фильтр или команду выхода: ");
            String search_string = in.nextLine();
            System.out.print("Введите строку поиска: ");
            String name = in.nextLine();
            if (search_string.equals("!quit")) {
                exit_App();
            } else {
                if (search_string.equals("")){search=new Search_Aeroport();
                    search.read_data(name);}
                else {
                    try {
                        search=new Search_Aeroport();
                        search.read_data(name,search_string);
                    }
                    catch (IllegalArgumentException e){
                        System.out.println("Некорректный фильр поиска");
                    }

            }}
        }

    }

    protected void exit_App(){
        System.out.println("Завершение работы");
        System.exit(0);

    }
}