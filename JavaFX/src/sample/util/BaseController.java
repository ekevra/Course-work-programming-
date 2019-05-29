package sample.util;

import javafx.scene.Node;
import sample.Main;

public class BaseController implements Controller {

    private Node view;

    @Override
    public Node getView() {
        return view;
    }

    @Override
    public void setView (Node view){
        this.view = view;
    }

    @Override
    public void Show() {
        Main.getNavigation().Show(this);
    }
}