package sample.model.persons;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDate;
import sample.util.LocalDateAdapter;

public abstract class Person {

    protected String firstName;
    protected String secondName;
    protected String otchestvo;
    protected LocalDate birthday;

    public String getFirstName() {
        return firstName;
    }
    @XmlElement
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }
    @XmlElement
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getOtchestvo() {
        return otchestvo;
    }
    @XmlElement
    public void setOtchestvo(String otchestvo) {
        this.otchestvo = otchestvo;
    }

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday;
    }
    @XmlElement
    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

}
