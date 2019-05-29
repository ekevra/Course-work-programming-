package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sample.Main;
import sample.model.AthleteListWrapper;
import sample.model.persons.Athlete;
import sample.model.persons.Title;
import sample.util.BaseController;
import sample.util.WorkingWithDate;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class watchingAthletePageGuestController extends BaseController {

    @FXML
    private Label otchField;
    @FXML
    private Label titleField;
    @FXML
    private Label coachField;
    @FXML
    private Label birthdayField;
    @FXML
    private Label lastNameField;
    @FXML
    private Label firstNameField;
    @FXML
    private Label typeOfSportsField;

    @FXML
    void initialize(){
        List<Athlete> athleteList = new ArrayList<>();
        try {
            File file = new File("athleteForWatching.xml");

            JAXBContext context = JAXBContext.newInstance(AthleteListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            AthleteListWrapper wrapper = (AthleteListWrapper) um.unmarshal(file);

            athleteList = wrapper.getAthletes();
        } catch (Exception e) {
            e.printStackTrace();
        }

        firstNameField.setText(athleteList.get(0).getFirstName());
        lastNameField.setText(athleteList.get(0).getSecondName());
        otchField.setText(athleteList.get(0).getOtchestvo());
        birthdayField.setText(WorkingWithDate.format(athleteList.get(0).getBirthday()));
        titleField.setText(fromTitleToString(athleteList.get(0).getTitle()));
        typeOfSportsField.setText(athleteList.get(0).getTypeOfSports().getName());
        coachField.setText(athleteList.get(0).getCoach().getSecondName() + " " +
                athleteList.get(0).getCoach().getFirstName() + " " +
                athleteList.get(0).getCoach().getOtchestvo());
    }

    @FXML
    void btnOkAction(ActionEvent event) {
        Main.getNavigation().GoBack();
    }

    String fromTitleToString(Title title){
        if(title.toString() == Title.THETHIRDJUNIOR.toString()){ return "3 юношеский"; }
        else if(title.toString() == Title.THESECONDJUNIOR.toString()){ return "2 юношеский"; }
        else if(title.toString() == Title.THEFIRSTJUNIOR.toString()){ return "1 юношеский"; }
        else if(title.toString() == Title.THESECONDSPORTS.toString()){ return "2 спортивный"; }
        else if(title.toString() == Title.THEFIRSTSPORTS.toString()){ return "1 спортивный"; }
        else if(title.toString() == Title.CANDIDATETOMASTEROFSPORTS.toString()){ return "КМС(кандидат в МС)"; }
        else if(title.toString() == Title.MASTEROFSPORTS.toString()){ return "МС(мастер спорта)"; }
        else if(title.toString() == Title.MASTEROFSPORTSOFINTERNATIONALCLASS.toString()){ return "МСМК"; }
        else { return "ЗМС"; }
    }

}
