package sample.model.persons;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.model.TypeOfSports;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class Coach extends Person {


    private Category category;

    private TypeOfSports typeOfSports;

    public Category getCategory() {
        return category;
    }
    @XmlElement
    public void setCategory(Category category) {
        this.category = category;
    }
    public TypeOfSports getTypeOfSports() {
        return typeOfSports;
    }
    @XmlElement
    public void setTypeOfSports(TypeOfSports typeOfSports) {
        this.typeOfSports = typeOfSports;
    }

    public Coach(String name, String surname, String otchestvo1, LocalDate birthday,
                 Category category1, TypeOfSports typeOfSports)
    {
        this.firstName = name;
        this.secondName = surname;
        this.otchestvo = otchestvo1;
        this.birthday = birthday;
        this.category = category1;
        this.typeOfSports = typeOfSports;
    }
    public Coach(){

    }
}
