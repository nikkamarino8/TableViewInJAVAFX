/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package tableviewsearchfilterexample;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 *
 * @author nikka
 */
public class TableViewSearchFilterExample extends Application {
    private final TableView<Person> table = new TableView<>();
    private final TextField searchField = new TextField();
    private final ObservableList<Person> data = FXCollections.observableArrayList(
            new Person("John", "Smith",30,"BSIT","Male"),
            new Person("Jane", "De Gracia", 25,"BSCE","Female"),
            new Person("Bob","Cruz",18,"BSED","Male"),
            new Person("Alice", "Mandallene",20, "BSCRIM", "Female"),
            new Person("Von", "Mamaid",18,"BSIT","Male"),
            new Person("Krecel", "Garapal",19,"BSIT","Female"),
            new Person("Dayan", "Paderan",20,"BSIT","Female"),
            new Person("Jayro", "Madrona",18,"BSIT","Male"),
            new Person("Ken", "Magno",18,"BSIT","Male"),
            new Person("Luca", "Cagay",20,"BSIT","Male"),
            new Person("Jimin", "Yu",22,"BSTM","Female"),
            new Person("Sana", "Minatozaki",25,"BSBM","Female"),
            new Person("Minjeong", "Kim",22,"BSAA","Male"),
            new Person("Karina", "Yoo",18,"BSCRIM","Female"),
            new Person("Eman", "Rubio",19,"BSED","Male")
    );
public static void main(String[] args) {
    launch(args);
}

@Override
public void start(Stage stage) {
      stage.setTitle("table view search");
    VBox wowy = new VBox();
    wowy.setSpacing(5);
    wowy.setPadding(new Insets(10, 0, 0, 10));

    TableColumn<Person, String> firstNameCol = new TableColumn<>("First Name");
    firstNameCol.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());

    TableColumn<Person, String> lastNameCol = new TableColumn<>("Last Name");
    lastNameCol.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

    TableColumn<Person, Integer> ageCol = new TableColumn<>("Age");
    ageCol.setCellValueFactory(cellData -> cellData.getValue().ageProperty().asObject());

    TableColumn<Person, String> courseCol = new TableColumn<>("Course");
    courseCol.setCellValueFactory(cellData -> cellData.getValue().courseProperty());

    TableColumn<Person, String> genderCol = new TableColumn<>("Gender");
    genderCol.setCellValueFactory(cellData -> cellData.getValue().genderProperty());

    table.getColumns().addAll(firstNameCol, lastNameCol, ageCol, courseCol, genderCol);
    table.setItems(data);

    searchField.setPromptText("Search");
    searchField.textProperty().addListener((observable, oldValue, newValue) -> {
        filterTable(newValue);
    });

    wowy.getChildren().addAll(searchField, table);
    Scene scene = new Scene(wowy, 600, 400);
    stage.setScene(scene);
    stage.show();
}
 private void filterTable(String filter) {
        if (filter == null || filter.length() == 0) {
            table.setItems(data);
            return;
        }
        ObservableList<Person> filteredList = FXCollections.observableArrayList();
        for (Person person : data) {
            if (person.getFirstName().toLowerCase().contains(filter.toLowerCase()) ||
            person.getLastName().toLowerCase().contains(filter.toLowerCase()) ||
            Integer.toString(person.getAge()).contains(filter) ||
            person.getCourse().toLowerCase().contains(filter.toLowerCase()) ||
            person.getGender().toLowerCase().trim().equals(filter.toLowerCase().trim())) {
            filteredList.add(person);
            }
        }
        table.setItems(filteredList);
    }


    public class Person {
    private final String firstName;
    private final String lastName;
    private final int age;
    private final String course;
    private final String gender;

    public Person(String firstName, String lastName, int age, String course, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.course = course;
        this.gender = gender;
    }
      
    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getCourse() {
        return course;
    }

    public String getGender() {
        return gender;
    }

    public StringProperty firstNameProperty() {
        return new SimpleStringProperty(firstName);
    }

    public StringProperty lastNameProperty() {
        return new SimpleStringProperty(lastName);
    }

    public IntegerProperty ageProperty() {
        return new SimpleIntegerProperty(age);
    }

    public StringProperty courseProperty() {
        return new SimpleStringProperty(course);
    }

    public StringProperty genderProperty() {
        return new SimpleStringProperty(gender);
    }
    
    }
}
