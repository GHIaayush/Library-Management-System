
//Author:Aayush Ghimire
package ui;

import controller.ControllerInterface;
import excception.LoginException;
import controller.SystemController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class LoginWindow extends Application {
    public static final LoginWindow INSTANCE = new LoginWindow();


    /*	private boolean isInitialized = false;

        public boolean isInitialized() {
            return isInitialized;
        }
        public void isInitialized(boolean val) {
            isInitialized = val;
        }*/
    private Text messageBar = new Text();

    public void clear() {
        messageBar.setText("");

    }
   /* private LoginWindow () {}
    public void init() {
        

        
    }*/

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage primStage = null;

    private static Stage[] allWindows = {
            MenuWindow.INSTANCE,
            AllMembersWindow.INSTANCE,
            AllBooksWindow.INSTANCE,
            CheckOutWindow.INSTANCE,
            CheckOutRecordWindow.INSTANCE,
            MenuWindow.INSTANCE,
            MemberWindow.INSTANCE
    };

    public static void hideAllWindows() {
        primStage.hide();
        for (Stage st : allWindows) {
            st.hide();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primStage = primaryStage;
        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("Library Management System");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("User Name:");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setPromptText("User Name.");
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        grid.setGridLinesVisible(false);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button loginBtn = new Button("Log in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(loginBtn);
        grid.add(hbBtn, 1, 4);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);
        ;
        grid.add(messageBox, 1, 6);

        loginBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    String uname = userTextField.getText().trim();
                    String pass = pwBox.getText().trim();

                    if(uname.isEmpty() || pass.isEmpty()) throw  new LoginException("All fields are required!!!");
                    ControllerInterface c = new SystemController();
                    c.login(uname, pass);

                    if (!MenuWindow.INSTANCE.isInitialized()) {
                        MenuWindow.INSTANCE.init();

                    }
                    messageBar.setText("");
                    primStage.hide();
                    MenuWindow.INSTANCE.show();

                } catch (LoginException ex) {
                    messageBar.setFill(Start.Colors.red);
                    messageBar.setText("Error! " + ex.getMessage());
                }

            }
        });

       /* Button backBtn = new Button("<= Back to Main");
        backBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                Start.hideAllWindows();
                Start.primStage().show();
            }
        });
        HBox hBack = new HBox(10);
        hBack.setAlignment(Pos.BOTTOM_LEFT);
        hBack.getChildren().add(backBtn);
        grid.add(hBack, 0, 7);*/


        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();

        //set Stage boundaries to visible bounds of the main screen
        primaryStage.setX(primaryScreenBounds.getMinX());
        primaryStage.setY(primaryScreenBounds.getMinY());
        primaryStage.setWidth(primaryScreenBounds.getWidth());
        primaryStage.setHeight(primaryScreenBounds.getHeight());


        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();

    }


}
