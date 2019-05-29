package sample.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.ArrayList;
import java.util.List;

public class Navigation {

    private static final Logger LOGGER = LogManager.getLogger(Navigation.class.getName());
    private final Stage stage;
    private final Scene scene;

    final private int width = 700;
    final private int height = 450;

    private List<Controller> controllers = new ArrayList<>();

    public Navigation(Stage stage)
    {
        LOGGER.info("starting");
        this.stage = stage;
        scene = new Scene(new Pane(), width, height);
        stage.setScene(scene);
    }

    public Controller load(String sUrl)
    {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(sUrl));
            Node root = fxmlLoader.load();

            Controller controller = fxmlLoader.getController();
            controller.setView(root);

            return controller;

        } catch(Exception e) {
            LOGGER.error(e);
        }
        return null;
    }

    public void Show(Controller controller)
    {
        try {
            scene.setRoot((Parent) controller.getView());
            controllers.add(controller);
        }
        catch(Exception e) {
            LOGGER.error(e.getMessage());
        }
    }

    public void GoBack()
    {
        if (controllers.size() > 1)
        {
            controllers.remove(controllers.get(controllers.size() - 1));
            scene.setRoot((Parent) controllers.get(controllers.size() - 1).getView());
        }
    }
}