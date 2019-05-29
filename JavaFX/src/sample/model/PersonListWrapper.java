package sample.model;

import sample.model.persons.Coach;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "coaches")
public class PersonListWrapper {

    private List<Coach> coaches;

    @XmlElement(name = "coach")
    public List<Coach> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Coach> coaches){this.coaches = coaches;}


}