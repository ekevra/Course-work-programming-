package sample.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
public class TypeOfSports {


    private String name;

    public String getName() {
        return name;
    }
    @XmlElement
    public void setName(String name) {
        this.name = name;
    }

    public TypeOfSports(String name) {
        this.name = name;
    }
    public TypeOfSports() {

    }

    @Override
    public String toString() {
        return  name ;
    }
}
