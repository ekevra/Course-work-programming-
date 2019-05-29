package sample.util;

import java.util.ArrayList;
import java.util.List;
import sample.model.persons.Coach;

public class WorkingWithListsOfPersons {

    public static List<Coach> searchByName (String name, List<Coach> persons){
        List<Coach> searchinghList = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++ ){
            if(persons.get(i).getFirstName().equals(name)){
                searchinghList.add(persons.get(i));
            }
        }
        return searchinghList;
    }
    public static List<Coach> searchBySecondName (String surname, List<Coach> persons){
        List<Coach> searchinghList = new ArrayList<>();
        for (int i = 0; i < persons.size(); i++ ){
            if(persons.get(i).getSecondName().equals(surname)){
                searchinghList.add(persons.get(i));
            }
        }
        return searchinghList;
    }
}