package sample.util;

import sample.model.persons.Athlete;
import sample.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CollectionAthletes {
    public ObservableList<Athlete> athletes = FXCollections.observableArrayList(Main.athletesFromFile());
    public ObservableList<Athlete> getAthletes() {
        return athletes;
    }
    public void setAthletes(ObservableList<Athlete> athletes) {
        this.athletes = athletes;
    }
}
