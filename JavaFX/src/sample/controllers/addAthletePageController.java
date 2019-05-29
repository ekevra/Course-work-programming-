package sample.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import sample.Main;
import sample.model.PersonListWrapper;
import sample.model.TypeOfSports;
import sample.model.persons.Athlete;
import sample.model.persons.Coach;
import sample.model.persons.Title;
import sample.util.BaseController;
import sample.util.CollectionAthletes;
import sample.util.WorkingWithDate;
import sample.util.WorkingWithTextFiles;
import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.util.ArrayList;
import java.util.List;

public class addAthletePageController extends BaseController {

    @FXML
    private TextField otchField;
    @FXML
    private TextField birthdayField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField firstNameField;
    @FXML
    private ComboBox<String> titleBox;
    @FXML
    private ComboBox<String> coachBox;
    @FXML
    private ComboBox<String> typesBox;


    @FXML
    private void initialize() {
        //ComboBox отделения
        List<TypeOfSports> types = WorkingWithTextFiles.getAllTypesOfSports();
        List<String> typeOfSportChoose = new ArrayList<>();
        String s;
        for(int i = 0; i < types.size(); i++ ){
            s = types.get(i).getName();
            typeOfSportChoose.add(s);
        }
        typesBox.getItems().addAll(typeOfSportChoose);

        //ComboBox разряды
        List<String> categoriesRussian = new ArrayList<>();
        categoriesRussian.add("3 юношеский");
        categoriesRussian.add("2 юношеский");
        categoriesRussian.add("1 юношеский");
        categoriesRussian.add("2 взрослый");
        categoriesRussian.add("1 взрослый");
        categoriesRussian.add("КМС");
        categoriesRussian.add("МС");
        categoriesRussian.add("МСМК");
        categoriesRussian.add("ЗМС");
        titleBox.getItems().addAll(categoriesRussian);

        //ComboBox тренера
        List<String> surnames = new ArrayList<>();
        for(Coach ch : getCoachesToComboBox()){
            surnames.add(ch.getSecondName());
        }
        coachBox.getItems().addAll(surnames);
    }
    @FXML
    void btnOkAction(ActionEvent event) {
        if(isInputValid()){
            Athlete newAthlete = new Athlete(
                    firstNameField.getText(), lastNameField.getText(), otchField.getText(),
                    WorkingWithDate.parse(birthdayField.getText()),findTitle(titleBox.getSelectionModel().getSelectedItem()),
                    findNeedCoach(coachBox.getSelectionModel().getSelectedItem()),whatISTypeOfSports());
            CollectionAthletes ca = new CollectionAthletes();
            ca.athletes.add(newAthlete);
            Main.athletesToXml(ca.athletes);
            Main.getNavigation().load("/sample/views/athletesPage.fxml").Show();
        }
    }
    @FXML
    void btnCancelAction(ActionEvent event) {
        Main.getNavigation().GoBack();
    }

    private List<Coach> getCoachesToComboBox(){
        List<Coach> coachList = new ArrayList<>();
        try {
            File file = new File("allCoaches.xml");

            JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            PersonListWrapper wrapper = (PersonListWrapper) um.unmarshal(file);

            coachList = wrapper.getCoaches();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return coachList;
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (firstNameField.getText() == null || firstNameField.getText().length() == 0 || isValisString(firstNameField.getText()) == false) {
            errorMessage += "Некорректно написано имя.\n";
        }
        if (lastNameField.getText() == null || lastNameField.getText().length() == 0 || isValisString(lastNameField.getText()) == false) {
            errorMessage += "Некорректно написана фамилия.\n";
        }
        if (otchField.getText() == null || otchField.getText().length() == 0 || isValisString(otchField.getText()) == false) {
            errorMessage += "Некорректно написано отчество.\n";
        }
        if (birthdayField.getText() == null || birthdayField.getText().length() == 0 || WorkingWithDate.validDate(birthdayField.getText()) == false) {
            errorMessage += "Неправильно написана дата рождения.\n";
        }
        String type = typesBox.getSelectionModel().getSelectedItem();
        if(type == null){
            errorMessage += "Не выбран вид спорта.\n";
        }
        String coachSurname = coachBox.getSelectionModel().getSelectedItem();
        if(coachSurname == null){
            errorMessage += "Не выбран тренер.\n";
        }
        String title = titleBox.getSelectionModel().getSelectedItem();
        if(title == null){
            errorMessage += "Не выбран спортивный разряд.\n";
        }
        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Неправильное заполнение полей");
            alert.setHeaderText("Проверьте правильность введённых данных.");
            alert.setContentText(errorMessage);

            alert.showAndWait();

            return false;
        }
    }
    private boolean isValisString(String thisString){
        int k =0;
        char[] charArray = thisString.toCharArray();
        char[] chars = {'.',',',' ','-','!','?','"',';',':','(',')','/',
                '1','2','3','4','5','6','7','8','9','0'};
        for(int i = 0; i < charArray.length; i++){
            for(int j = 0; j < chars.length; j++){
                if(charArray[i]==chars[j]){
                    k++;
                }
            }
        }
        if(k==0){return true;}
        else{return false;}
    }
    private TypeOfSports whatISTypeOfSports(){
        TypeOfSports ts = new TypeOfSports("");
        String type = typesBox.getSelectionModel().getSelectedItem();
        if(type != null){
            List<TypeOfSports> types = WorkingWithTextFiles.getAllTypesOfSports();
            for(int i = 0; i < types.size(); i++ ){
                if(types.get(i).getName().equals(type)){
                    ts = types.get(i);
                }
            }
        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Неправильное заполнение полей");
            alert.setHeaderText("Проверьте правильность введённых данных.");
            alert.setContentText("Не выбран вид спорта");
            alert.showAndWait();
        }
        return ts;
    }
    private Coach findNeedCoach (String surname){
        Coach needingCoach = new Coach();
        for(Coach coach : getCoachesToComboBox()){
            if(coach.getSecondName().equals(surname)){
                needingCoach = coach;
            }
        }
        return needingCoach;
    }
    public Title findTitle(String titleStr){
        if(titleStr == "3 юношеский") { return Title.THETHIRDJUNIOR; }
        else if(titleStr == "2 юношеский") { return Title.THESECONDJUNIOR; }
        else if(titleStr == "1 юношеский") { return Title.THEFIRSTJUNIOR; }
        else if(titleStr == "2 спортивный") { return Title.THESECONDSPORTS; }
        else if(titleStr == "1 спортивный") { return Title.THEFIRSTSPORTS; }
        else if(titleStr == "КМС(кандидат в МС)") { return Title.CANDIDATETOMASTEROFSPORTS; }
        else if(titleStr == "МС(мастер спорта)") { return Title.MASTEROFSPORTS; }
        else if(titleStr == "МСМК") { return Title.MASTEROFSPORTSOFINTERNATIONALCLASS; }
        else { return Title.HONOREDMASTEROFSPORTS; }
    }
}
