package sample.util;

import java.util.*;
import sample.model.persons.Coach;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionCoaches {

    public ObservableList<Coach> coaches = FXCollections.observableArrayList();
    public ObservableList<Coach> getCoaches() {
        return coaches;
    }
    public void setCoaches(ObservableList<Coach> coaches) {
        this.coaches = coaches;
    }

    public void fillTestData(){
        List<Coach> all = WorkingWithTextFiles.getAllCoaches();
        for(int i = 0; i < all.size(); i++){
            this.coaches.add(all.get(i));
        }
    }
}
