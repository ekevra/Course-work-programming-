package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.model.AthleteListWrapper;
import sample.model.persons.Athlete;
import sample.model.persons.Coach;
import sample.Main;
import sample.util.BaseController;
import sample.util.CollectionAthletes;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

public class athletesPageGuestController extends BaseController {

    CollectionAthletes collectionAthletes = new CollectionAthletes();

    @FXML
    private TableView<Athlete> personTable;
    @FXML
    private TableColumn<Coach, String> lastNameColumn;
    @FXML
    private TableColumn<Coach, String> firstNameColumn;

    @FXML
    void initialize(){
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("firstName"));
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<Coach, String>("secondName"));

        personTable.setItems(collectionAthletes.getAthletes());
    }

    @FXML
    void btnBackAction(ActionEvent event) {
        Main.getNavigation().load("/sample/views/firstPageGuest.fxml").Show();
    }

    @FXML
    void btnWatchAction(ActionEvent event) {
        Athlete athlete = personTable.getSelectionModel().getSelectedItem();
        List<Athlete> athleteList = new ArrayList<>();
        athleteList.add(athlete);

        if(athlete != null){
            try {
                File file = new File("athleteForWatching.xml");

                JAXBContext jaxbContext = JAXBContext.newInstance(AthleteListWrapper.class);
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                AthleteListWrapper wrapper = new AthleteListWrapper();
                wrapper.setAthletes(athleteList);
                jaxbMarshaller.marshal(wrapper, file);

            } catch (JAXBException e) {
                e.printStackTrace();
            }
            Main.getNavigation().load("/sample/views/watchingAthletePageGuest.fxml").Show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Нет выделенного объекта");
            alert.setContentText("Чтобы увидеть данные о спортсмене, надо его выделить.");
            alert.showAndWait();
        }

    }

}
