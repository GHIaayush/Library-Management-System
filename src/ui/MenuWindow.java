
//Author:Aayush Ghimire
package ui;

import business.MyStage;
import controller.SystemController;
import dataaccess.Action;
import dataaccess.Auth;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuWindow extends MyStage implements LibWindow {
    public static final MenuWindow INSTANCE = new MenuWindow();



    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }

    @Override
    public void init() {

        GridPane grid = new GridPane();
        grid.setId("top-container");
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));



        Button addMember = new Button("Add member ");
        Button editMember = new Button("Edit member ");
        Button checkOutBooks = new Button("Check out books ");
        Button checkInBooks = new Button("Check in books ");
        Button renewBooks = new Button("Renew books ");
        Button addBooks = new Button("Add books ");
        Button checkBookStatus = new Button("Check book status ");
       // LIBRARIAN, ADMIN, BOTH;

        // TODO: 10/11/2017
        editMember.setVisible(false);
        addBooks.setVisible(false);
        renewBooks.setVisible(false);
        checkBookStatus.setVisible(false);

        if(SystemController.currentAuth == Auth.LIBRARIAN){
            addMember.setVisible(false);
            editMember.setVisible(false);
            addBooks.setVisible(false);
        }else if(SystemController.currentAuth == Auth.ADMIN){
            checkOutBooks.setVisible(false);
            checkInBooks.setVisible(false);
            renewBooks.setVisible(false);
            checkBookStatus.setVisible(false);
        }


        addMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!MemberWindow.INSTANCE.isInitialized()) {
                    MemberWindow.INSTANCE.init();
                }
                LoginWindow.hideAllWindows();
                MemberWindow.INSTANCE.show();
            }
        });

        editMember.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        checkOutBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {


                if (!CheckOutWindow.INSTANCE.isInitialized()) {
                    SystemController.currentAction = Action.CHECKOUT;
                    CheckOutWindow.INSTANCE.init();
                }
                CheckOutWindow.INSTANCE.clear();
                LoginWindow.hideAllWindows();
                CheckOutWindow.INSTANCE.show();

            }
        });

        checkInBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginWindow.hideAllWindows();
                if (!CheckOutWindow.INSTANCE.isInitialized()) {
                    SystemController.currentAction = Action.CHECKIN;
                    CheckOutWindow.INSTANCE.init();
                }
                CheckOutWindow.INSTANCE.clear();
                CheckOutWindow.INSTANCE.show();
            }
        });

        renewBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        addBooks.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        checkBookStatus.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

            }
        });

        Button back = new Button("Back");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().add(back);
        grid.add(hBox,0,2);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                LoginWindow.hideAllWindows();
                LoginWindow.primStage.show();
            }
        });

        VBox vBox = new VBox(20);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(addMember ,editMember,checkOutBooks, checkInBooks,
                renewBooks, addBooks, checkBookStatus);
        grid.add(vBox, 0, 1);


        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());
        setScene(scene);
    }


}
