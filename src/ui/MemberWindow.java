
//Author:Aayush Ghimire
package ui;

import business.Address;
import business.LibraryMember;
import business.MyStage;
import dataaccess.DataAccess;
import dataaccess.DataAccessFacade;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class MemberWindow extends MyStage implements LibWindow {

    public static final MemberWindow INSTANCE = new MemberWindow();

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
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setGridLinesVisible(false);
        Text scenetitle = new Text("Please enter the info");
        scenetitle.setFont(Font.font("Harlow Solid Italic", FontWeight.NORMAL, 20)); //Tahoma
        grid.add(scenetitle, 0, 0, 2, 1);

        Text errorMessage = new Text();
        TextField mId = new TextField();
        mId.setPromptText("Member Id");
        TextField fName = new TextField();
        fName.setPromptText("First Name");
        TextField lName = new TextField();
        lName.setPromptText("Last Name");
        TextField street = new TextField();
        street.setPromptText("Street");
        TextField city = new TextField();
        city.setPromptText("City");
        TextField state = new TextField();
        state.setPromptText("State");
        TextField zip = new TextField();
        zip.setPromptText("Zip Code");
        TextField phone = new TextField();
        phone.setPromptText("Telephone");

        VBox vBox = new VBox(15);
        vBox.getChildren().addAll(mId, fName, lName, street, city, state, zip, phone);
        grid.add(vBox, 0, 1);

        Button addBtn = new Button("Add Member");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(addBtn);
        grid.add(hbBtn, 0, 2);

        HBox messageBox = new HBox(10);
        messageBox.setAlignment(Pos.BOTTOM_RIGHT);
        messageBox.getChildren().add(errorMessage);
        ;
        grid.add(messageBox, 0, 3);

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                DataAccess da = new DataAccessFacade();
                if (street.getText().trim().isEmpty() || city.getText().trim().isEmpty() || state.getText().trim().isEmpty() || zip.getText().trim().isEmpty() ||
                        mId.getText().trim().isEmpty() || fName.getText().trim().isEmpty() || lName.getText().trim().isEmpty() || phone.getText().trim().isEmpty()) {
                    errorMessage.setText("All fields are required !!!");
                    errorMessage.setFill(Start.Colors.red);
                } else if (da.readMemberMap().containsKey(mId.getText().trim())) {
                    errorMessage.setText("This member id already exist!! \nchoose next one");

                } else {

                    Address add = new Address(street.getText().trim(), city.getText().trim(), state.getText().trim(), zip.getText().trim());
                    LibraryMember lm = new LibraryMember(mId.getText().trim(), fName.getText().trim(), lName.getText().trim(), phone.getText().trim(), add);

                    da.addMember(lm);
                    errorMessage.setText("New member Added");

                    street.setText("");
                    state.setText("");
                    city.setText("");
                    zip.setText("");
                    mId.setText("");
                    fName.setText("");
                    lName.setText("");
                    phone.setText("");
                }
            }
        });

        Button back = new Button("Back");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.TOP_LEFT);
        hBox.getChildren().add(back);
        grid.add(hBox, 0, 4);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                errorMessage.setText("");
                if (!MenuWindow.INSTANCE.isInitialized()) {
                    MenuWindow.INSTANCE.init();
                }
                LoginWindow.hideAllWindows();
                MenuWindow.INSTANCE.show();
            }
        });

        Scene scene = new Scene(grid);
        scene.getStylesheets().add(getClass().getResource("library.css").toExternalForm());

        setScene(scene);
    }

}
