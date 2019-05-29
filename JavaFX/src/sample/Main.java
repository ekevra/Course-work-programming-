package sample;

import java.util.*;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import sample.model.AthleteListWrapper;
import sample.model.persons.Athlete;
import sample.util.Navigation;
import sample.util.CollectionAthletes;
import java.util.List;
import javafx.collections.FXCollections;
import javax.xml.bind.JAXBException;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import sample.model.PersonListWrapper;
import sample.util.WorkingWithTextFiles;

public class Main extends Application {

    private Stage primaryStage;
    private static Navigation navigation;
    public static Navigation getNavigation(){return navigation;}

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{

        navigation = new Navigation(primaryStage);

        primaryStage.setTitle("СДЮШОР №3");
        primaryStage.show();

        this.primaryStage = primaryStage;

        Main.getNavigation().load("/sample/views/loginPage.fxml").Show();
    }

    public static void main(String[] args) { launch(args); }

    public void coachesToXml(File file) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(PersonListWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            PersonListWrapper wrapper = new PersonListWrapper();
            wrapper.setCoaches(WorkingWithTextFiles.getAllCoaches());
            jaxbMarshaller.marshal(wrapper, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static void coachesFromFile(File file){
        try {
            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            WorkingWithTextFiles.inputInformation(wrapper.getCoaches());

            Main.getNavigation().load("/sample/views/coachesPage.fxml").Show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void athletesToXml(List<Athlete> allAthletes) {
        try {
            File file = new File("athlete.xml");

            JAXBContext jaxbContext = JAXBContext.newInstance(AthleteListWrapper.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            AthleteListWrapper wrapper = new AthleteListWrapper();
            wrapper.setAthletes(allAthletes);
            jaxbMarshaller.marshal(wrapper, file);

        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    public static List<Athlete> athletesFromFile(){
        List<Athlete> athleteList = new ArrayList<>();
        try {
            File file = new File("athlete.xml");

            JAXBContext context = JAXBContext.newInstance(AthleteListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            AthleteListWrapper wrapper = (AthleteListWrapper) um.unmarshal(file);

            athleteList = wrapper.getAthletes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return athleteList;
    }

}

