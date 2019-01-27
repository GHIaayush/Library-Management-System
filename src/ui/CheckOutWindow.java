
//Author:Aayush Ghimire
package ui;

import excception.CheckException;
import controller.ControllerInterface;
import business.MyStage;
import controller.SystemController;
import dataaccess.Action;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class CheckOutWindow extends MyStage implements LibWindow {
    public static final CheckOutWindow INSTANCE = new CheckOutWindow();

    @Override
    public boolean isInitialized() {
        return false;
    }

    @Override
    public void isInitialized(boolean val) {

    }


    private Text messageBar = new Text();

    public void clear() {
        messageBar.setText("");
    }

    @Override
    public void init() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setGridLinesVisible(false);
        Text scenetitle = new Text();
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);


        Label memberId = new Label("Member Id:");

        TextField memberIdTextField = new TextField();
        //userTextField.setPrefColumnCount(10);
        //userTextField.setPrefWidth(30);

        Label isbn = new Label("ISBN:");


        TextField isbnTextField = new TextField();


        Label copyNo = new Label("copy No:");

        TextField copyNoTextField = new TextField();


        Button check = new Button();
        VBox hbBtn = new VBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_LEFT);
        hbBtn.getChildren().addAll(memberId, memberIdTextField, isbn, isbnTextField, copyNo, copyNoTextField, check);
        grid.add(hbBtn, 0, 1);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(messageBar);
        ;
        grid.add(messageBox, 0, 2);

        // for check out condition;
        if (SystemController.currentAction == Action.CHECKOUT) {
            copyNo.setVisible(false);
            copyNoTextField.setVisible(false);
            check.setText("Check out");
            scenetitle.setText("Check Out");

            check.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String memberId = memberIdTextField.getText().trim();
                    String isbn = isbnTextField.getText().trim();


                        try {
                            if (memberId.isEmpty() || isbn.isEmpty()) throw  new CheckException("All fields are required!!!");
                                ControllerInterface c = new SystemController();
                            c.checkout(memberId, isbn);
                            if (!CheckOutRecordWindow.INSTANCE.isInitialized()) {
                                CheckOutRecordWindow.INSTANCE.setId(memberId);
                                CheckOutRecordWindow.INSTANCE.init();
                            }
                            LoginWindow.hideAllWindows();
                            CheckOutRecordWindow.INSTANCE.show();
                            messageBar.setText("");

                        } catch (CheckException ex) {
                            System.out.println("Error caught+ " + ex.getMessage());
                            messageBar.setFill(Start.Colors.red);
                            messageBar.setText("Error! " + ex.getMessage());
                        }
                    }


            });

        } else {
            check.setText("Check in");
            scenetitle.setText("Check In");
            check.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String memberId = memberIdTextField.getText().trim();
                    String isbn = isbnTextField.getText().trim();
                    int copyNo = -1;

                        try {

                            if (memberId.isEmpty() || isbn.isEmpty() || copyNoTextField.getText().trim().isEmpty())throw new CheckException("all fields are required !!!");
                            try {
                                copyNo = Integer.valueOf(copyNoTextField.getText().trim());
                            }catch (Exception ex){
                                throw new CheckException("Please enter the valid no.");
                            }
                            ControllerInterface c = new SystemController();
                            c.checkIn(memberId, isbn, copyNo);

                            if (!CheckOutRecordWindow.INSTANCE.isInitialized()) {
                                CheckOutRecordWindow.INSTANCE.setId(memberId);
                                CheckOutRecordWindow.INSTANCE.init();
                            }
                            LoginWindow.hideAllWindows();
                            CheckOutRecordWindow.INSTANCE.show();
                            messageBar.setText("");

                        } catch (CheckException ex) {
                            System.out.println("Error caught+ " + ex.getMessage());
                            messageBar.setFill(Start.Colors.red);
                            messageBar.setText("Error! " + ex.getMessage());
                        }
                    }


            });


        }

        Button back = new Button("Back");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().add(back);
        grid.add(hBox, 0, 3);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!MenuWindow.INSTANCE.isInitialized()) {
                    MenuWindow.INSTANCE.init();
                }
                messageBar.setText("");
                LoginWindow.hideAllWindows();
                MenuWindow.INSTANCE.show();
            }
        });
        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());

        setScene(scene);
    }


}
