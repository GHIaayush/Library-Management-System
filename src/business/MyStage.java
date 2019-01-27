
//Author:Aayush Ghimire
package business;

import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MyStage extends Stage {

  public   MyStage() {
        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        setX(primaryScreenBounds.getMinX());
        setY(primaryScreenBounds.getMinY());
        setWidth(primaryScreenBounds.getWidth());
        setHeight(primaryScreenBounds.getHeight());
    }

}
