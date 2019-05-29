package sample.model;

import sample.model.persons.Athlete;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "athletes")
public class AthleteListWrapper {
    private List<Athlete> athletes;

    @XmlElement(name = "athlete")
    public List<Athlete> getAthletes() {
        return athletes;
    }

    public void setAthletes(List<Athlete> athletes){this.athletes = athletes;}
}
