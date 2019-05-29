package sample.util;

import sample.model.TypeOfSports;
import sample.model.persons.Category;
import sample.model.persons.Coach;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkingWithTextFiles {

    public static List<String> outputInformation1(){
        List<String> strList = new ArrayList<>();
        String s;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("allCoachesAtTheMoment.txt"));
            while ((s = reader.readLine()) != null) {
                strList.add(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return strList;
    }

    public static List<Coach> getAllCoaches(){
        List<String> strings = outputInformation1();
        List<Coach> allCoaches = new ArrayList<>(strings.size());
        for(int i = 0 ; i < strings.size(); i++){
            String[] stringArray = strings.get(i).split(" ");
            Coach coach = new Coach(stringArray[0],stringArray[1],stringArray[2],WorkingWithDate.parse(stringArray[3]),
                    findCategory(stringArray[4]),findTypeOfSport(stringArray[5]));
            allCoaches.add(coach);
        }
        return allCoaches;
    }

    public static TypeOfSports findTypeOfSport(String type){
//        try(FileWriter writer = new FileWriter("Types of sports.txt", false))
//        {
//            String text1 = "ПрыжкиНаБатуте" + "\n";
//            writer.write(text1);
//            String text2 = "ХудожественнаяГимнастика" + "\n";
//            writer.write(text2);
//            String text3 = "СпортивнаяГимнастика" + "\n";
//            writer.write(text3);
//            writer.flush();
//        }
//        catch(IOException ex){
//            System.out.println(ex.getMessage());
//        }

        TypeOfSports ts = new TypeOfSports("IT'S null");
        List<String> strList = new ArrayList<>();
        String s;

        try{
            BufferedReader reader = new BufferedReader(new FileReader("Types of sports.txt"));
            while ((s = reader.readLine()) != null) {
                strList.add(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }

        List<TypeOfSports> types = new ArrayList<>();

        for(int i = 0; i < 3; i++ ){
            types.add(new TypeOfSports(strList.get(i)));
        }

        for(int i = 0; i < types.size(); i++ ){
            if(types.get(i).getName().equals(type)){
                 ts = types.get(i);
            }
        }
        return ts;
    }

    public static List<TypeOfSports> getAllTypesOfSports(){
        List<String> strList = new ArrayList<>();
        String s;
        try{
            BufferedReader reader = new BufferedReader(new FileReader("Types of sports.txt"));
            while ((s = reader.readLine()) != null) {
                strList.add(s);
            }
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        List<TypeOfSports> types = new ArrayList<>();
        for(int i = 0; i < 3; i++ ){
            types.add(new TypeOfSports(strList.get(i)));
        }
        return types;
    }

    public static String nameOfCategory(String category){
        if (category.equals("WITHOUTCATEGORY")){ return "Молодой специалист"; }
        else if(category.equals("THESECONDCATEGORY")) {return "Вторая категория"; }
        else if(category.equals("THEFIRSTCATEGORY")) {return "Первая категория"; }
        else if(category.equals("HIGHCATEGORY")) {return "Высшая категория"; }
        else{return null;}
    }

    public static Category findCategory(String name){
        if (name.equals(Category.WITHOUTCATEGORY.toString())){ return Category.WITHOUTCATEGORY; }
        else if (name.equals(Category.THESECONDCATEGORY.toString())){ return Category.THESECONDCATEGORY; }
        else if (name.equals(Category.THEFIRSTCATEGORY.toString())){ return Category.THEFIRSTCATEGORY; }
        else if (name.equals(Category.HIGHCATEGORY.toString())){ return Category.HIGHCATEGORY; }
        else{return null;}
    }

    public static Category categoryFromRussian(String name){
        if (name.equals("Молодой специалист")){ return Category.WITHOUTCATEGORY; }
        else if (name.equals("Вторая категория")){ return Category.THESECONDCATEGORY; }
        else if (name.equals("Первая категория")){ return Category.THEFIRSTCATEGORY; }
        else if (name.equals("Высшая категория")){ return Category.HIGHCATEGORY; }
        else{return null;}
    }

    public static void inputInformation(Coach coachInfo){
        try(FileWriter writer = new FileWriter("coachToEditPage.txt", false))
        {
            String text = coachInfo.getFirstName()+ " "+coachInfo.getSecondName()+" "
                    +coachInfo.getOtchestvo()+" "+ WorkingWithDate.format(coachInfo.getBirthday())
                    +" "+coachInfo.getCategory().toString()+" "+coachInfo.getTypeOfSports().getName();
            writer.write(text);
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }

    public static String outputInformation(){
        String s = "";
        try{
            Scanner in = new Scanner(new File("coachToEditPage.txt"));
            while(in.hasNext())
                s += in.nextLine() + "\r\n";
            in.close();
        }
        catch(IOException ex) {
            System.out.println(ex.getMessage());
        }
        return s;
    }

    public static Coach utilString(){

        String str = outputInformation();
        String[] strings = str.split(" ");
        Coach coach = new Coach(strings[0],strings[1],strings[2],WorkingWithDate.parse(strings[3]),
                findCategory(strings[4]),findTypeOfSport(strings[5]));
        return coach;
    }

    public static Coach utilString2(){

        String str = outputInformation();
        String[] strings = str.split(" ");
        Coach coach = new Coach(strings[0],strings[1],strings[2],WorkingWithDate.parse(strings[3]),
                findCategory(strings[4]),new TypeOfSports(strings[5]));
        return coach;
    }

    public static void inputInformation(List<Coach> coachInfo){
        try(FileWriter writer = new FileWriter("allCoachesAtTheMoment.txt", false))
        {
            for(int i = 0; i < coachInfo.size(); i++ ){
                String text = coachInfo.get(i).getFirstName()+ " "+coachInfo.get(i).getSecondName()+" "
                        +coachInfo.get(i).getOtchestvo()+" "+ WorkingWithDate.format(coachInfo.get(i).getBirthday())
                        +" "+coachInfo.get(i).getCategory().toString()+" "+coachInfo.get(i).getTypeOfSports().getName() + "\n";
                writer.write(text);
            }
            writer.flush();
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
    }
}
