package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.util.Callback;

import java.time.LocalDate;

public class Controller {
    public static void main(String[] args) {
        SimpleIntegerProperty v1=new SimpleIntegerProperty(10);
        SimpleIntegerProperty v2=new SimpleIntegerProperty(20);




        v1.bindBidirectional(v2);
        System.out.println(v1);
        System.out.println(v2);
        v1.setValue(15);
        System.out.println(v1);
        System.out.println(v2);
        v2.setValue(30);
        System.out.println(v1);
        System.out.println(v2);
    }
}
