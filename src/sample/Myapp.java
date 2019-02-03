package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;

/**
 * Created by NET on 2/3/2019.
 */
public class Myapp extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        FlowPane pane=new FlowPane(); //flow pane
        Scene scene=new Scene(pane , 400,400);
        pane.requestFocus();

        MenuBar menuBar=new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");

        MenuItem openfileItem = new MenuItem("Open File");
        openfileItem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                FileChooser fileChooser=new FileChooser();
                fileChooser.showOpenDialog(primaryStage);
            }
        });
        openfileItem.setAccelerator(new KeyCodeCombination(KeyCode.O,KeyCombination.ALT_DOWN));

        MenuItem exitItem = new MenuItem("Exit");

        fileMenu.getItems().addAll(openfileItem , exitItem);

        menuBar.getMenus().addAll(fileMenu , editMenu);

        TextField textField=new TextField();
        textField.prefWidthProperty().bind(pane.widthProperty().divide(2));
        Label label=new Label();
        label.textProperty().bind(textField.textProperty());

        ObservableList<String> list=FXCollections.observableArrayList("USA","CANADA","IRAN");


        //ChoiceBox<String> choiceBox=new ChoiceBox<>(list);
        ListView<String> listView=new ListView<>(list);
        listView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        //TODO check click
        listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
            }
        });

        Button button=new Button("remove");
        Button button2=new Button("add");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.remove(0);
            }
        });
        button2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                list.add(textField.getText());
            }
        });
        listView.itemsProperty().addListener(new ChangeListener<ObservableList<String>>() {
            @Override
            public void changed(ObservableValue<? extends ObservableList<String>> observable, ObservableList<String> oldValue, ObservableList<String> newValue) {
                System.out.println(newValue);
            }
        });
        DatePicker datePicker=new DatePicker();
        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell(){
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {

                        super.updateItem(LocalDate.now(), empty);
                    }
                };
            }
        });
        pane.getChildren().addAll(menuBar,textField,label,listView,button,button2 , datePicker);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
