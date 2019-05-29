package sample.model.persons;

import sample.model.TypeOfSports;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;

@XmlRootElement
public class Athlete extends Person {

    private Title title;//спортивный разряд
    private Coach coach;
    private TypeOfSports typeOfSports;

    public Title getTitle() {
        return title;
    }
    @XmlElement
    public void setTitle(Title title) {
        this.title = title;
    }
    public Coach getCoach() {
        return coach;
    }
    @XmlElement
    public void setCoach(Coach coach) {
        this.coach = coach;
    }
    public TypeOfSports getTypeOfSports() {
        return typeOfSports;
    }
    @XmlElement
    public void setTypeOfSports(TypeOfSports typeOfSports) {
        this.typeOfSports = typeOfSports;
    }


    public Athlete(String name, String surname, String otchestvo1, LocalDate birthday,
                   Title title1, Coach coach, TypeOfSports typeOfSports)
    {
        this.firstName = name;
        this.secondName = surname;
        this.otchestvo = otchestvo1;
        this.birthday = birthday;
        this.title = title1;
        this.coach = coach;
        this.typeOfSports = typeOfSports;
    }
    public Athlete(String name, String surname, String otchestvo1, LocalDate birthday,
                   Coach coach, TypeOfSports typeOfSports)
    {
        this.firstName = name;
        this.secondName = surname;
        this.otchestvo = otchestvo1;
        this.birthday = birthday;
        this.title = null;
        this.coach = coach;
        this.typeOfSports = typeOfSports;
    }
    public Athlete(){

    }
}
