
//Author:Aayush Ghimire
package ui;

import business.*;
import controller.ControllerInterface;
import controller.SystemController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class CheckOutRecordWindow extends MyStage implements LibWindow {
    public static final CheckOutRecordWindow INSTANCE = new CheckOutRecordWindow();

    ControllerInterface ci = new SystemController();

    private boolean isInitialized = false;
    private String id ;


    @Override
    public boolean isInitialized() {
        return isInitialized;
    }

    @Override
    public void isInitialized(boolean val) {
        isInitialized = val;
    }

    public void setId(String id){
        this.id = id;
    }
    @Override
    public void init() {

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));


        TableView table = new TableView();
        table.setEditable(false);

        TableColumn mId = new TableColumn("Member Id");
        TableColumn book = new TableColumn("Book Id");
        TableColumn cDate = new TableColumn("Date");
        TableColumn dDate = new TableColumn("Due Date");
        TableColumn rDate = new TableColumn("Return Date");
        mId.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecordEntry, String>("memberId"));
        book.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecordEntry, String>("bookCopy"));
        cDate.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecordEntry, String>("checkOutDate"));
        dDate.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecordEntry, String>("dueDate"));
        rDate.setCellValueFactory(
                new PropertyValueFactory<CheckOutRecordEntry, String>("returnDate"));
        ObservableList<CheckOutRecordEntry> data = FXCollections.observableArrayList(ci.getAllCheckoutRecordEntry(id));


        table.setItems(data);
        table.getColumns().addAll(mId, book, cDate, dDate, rDate);



        Button back = new Button("Back");
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_LEFT);
        hBox.getChildren().add(back);
        grid.add(hBox,1,1);
        back.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (!CheckOutWindow.INSTANCE.isInitialized()) {
                    CheckOutWindow.INSTANCE.init();
                }
                LoginWindow.hideAllWindows();
                CheckOutWindow.INSTANCE.show();
            }
        });

        grid.getChildren().add(table);
        Scene scene = new Scene(grid);
        setScene(scene);
    }
}
