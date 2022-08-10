package sample;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.paint.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;


public class Admin extends Application {
    public static  String mathmark,daamark,javamark,dbmsmak,usn;

    String genderinput = "";
    boolean flag1 = false, flag2 = false, flag3 = false, flag4 = false, flag5 = false, flag6 = false, flag7 = false, flag8 = false;

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        GridPane root = new GridPane();
        root.setHgap(5);
        root.setVgap(20);


        Text textscene = new Text("College Admin Panel");
        textscene.setFill(Color.BLACK);
        textscene.setStyle("-fx-font: 24 verdana;");
        Label name = new Label("Name:");
        name.setStyle("-fx-font: 15 verdana;");
        final TextField nameinput = new TextField();
        nameinput.setPromptText("Enter your full Name");
        nameinput.setFocusTraversable(false);
        nameinput.setStyle("-fx-font: 15 verdana;");
        root.add(name, 0, 1);
        root.add(nameinput, 1, 1);


        Label gender = new Label("Gender:");
        root.add(gender, 0, 3);
        gender.setStyle("-fx-font: 15 verdana;");
        ToggleGroup pickgender = new ToggleGroup();
        RadioButton pickmale = new RadioButton("Male");
        pickmale.setStyle("-fx-font: 15 verdana;");
        pickmale.setToggleGroup(pickgender);
        root.add(pickmale, 1, 3, 1, 1);

        RadioButton pickfemale = new RadioButton("Female");
        pickfemale.setToggleGroup(pickgender);
        pickfemale.setStyle("-fx-font: 15 verdana;");
        root.add(pickfemale, 2, 3);


        Label ui = new Label("Unique Id:");
        ui.setStyle("-fx-font: 15 verdana;");
        TextField uniqueid = new TextField();
        uniqueid.setPromptText("Enter your unique id");
        uniqueid.setFocusTraversable(false);
        uniqueid.setStyle("-fx-font: 15 verdana;");
        root.add(ui, 0, 2);
        root.add(uniqueid, 1, 2);


        Label emailid = new Label("Email Id:");
        emailid.setStyle("-fx-font: 15 verdana;");
        TextField emailinput = new TextField();
        emailinput.setPromptText("Enter your Email Id");
        emailinput.setFocusTraversable(false);
        emailinput.setStyle("-fx-font: 15 verdana;");
        root.add(emailid, 0, 4);
        root.add(emailinput, 1, 4);

        root.add(textscene, 0, 0, 3, 1);
        Scene scene = new Scene(root, 500, 500);

        primaryStage.setTitle("Admin panel");
        textscene.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        root.setAlignment(Pos.CENTER);

        ComboBox<String> cbCourse;
        Label course = new Label("Department:");
        course.setStyle("-fx-font: 15 verdana;");
        ObservableList<String> courses =
                FXCollections.observableArrayList("CSE", "ISE", "ECE");
        cbCourse = new ComboBox<String>(courses);
        cbCourse.setStyle("-fx-font: 15 verdana;");
        cbCourse.setValue("CSE");
        // course.setText("Selected Course is " + cbCourse.getValue());
        cbCourse.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                //course.setText("Selected Course is " + cbCourse.getValue());
            }
        });
        root.add(course, 0, 7);
        root.add(cbCourse, 1, 7);


        Button save = new Button("Done");
        save.setStyle("-fx-font: 15 verdana;");
        root.add(save, 1, 10);



        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                if (pickmale.isSelected()) {
                    genderinput = "male";
                    flag3 = true;
                }
                if (pickfemale.isSelected()) {
                    flag3 = true;
                    genderinput = "female";
                }


                if (nameinput.getText().length() >= 2 && nameinput.getText().matches("[A-Za-z ]+")) {
                    flag1 = true;
                }

                System.out.println("Your Details:-");

                System.out.println("Name: " + nameinput.getText());
                System.out.println("Gender: " + genderinput);
                System.out.println("Unique Id: " + uniqueid.getText());

                Alert alert = new Alert(AlertType.WARNING);
                if (flag1 && flag3) {
                    writenewuser();
                } else if (!flag1) {
                    alert.setContentText("Please enter some valid name");
                    alert.showAndWait();
                } else if (!flag3) {
                    alert.setContentText("Please choose gender");
                    alert.showAndWait();
                } else if (!flag1 && !flag3 || !flag1 && !flag4 || !flag3 && !flag4) {
                    alert.setContentText("Please fill appropriate in the field");
                    alert.showAndWait();
                }

            }

            public void writenewuser() {
                try {
                    FileWriter fstream = new FileWriter("registration_form.txt");
                    BufferedWriter out = new BufferedWriter(fstream);
                    out.write(nameinput.getText());
                    out.newLine();
                    out.write(genderinput);
                    out.newLine();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    ;
                }

            }

        });
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGRAY,
                CornerRadii.EMPTY, Insets.EMPTY);

        // create Background
        Background background = new Background(background_fill);

        // set background
        root.setBackground(background);


        primaryStage.setScene(scene);
        primaryStage.show();

        Button button = new Button();
        button.setText("Upload student marks");

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {



                StackPane secondaryLayout = new StackPane();


                GridPane root = new GridPane();
                root.setHgap(5);
                root.setVgap(20);
                root.setAlignment(Pos.CENTER);

                Text textscene = new Text("Upload Student Marks:");
                root.add(textscene, 0, 0, 3, 1);
                textscene.setFill(Color.BLACK);
                textscene.setStyle("-fx-font: 24 arial;");
                Scene scene = new Scene(root, 500, 500);


                Label ui = new Label("Student usn:");
                ui.setStyle( "-fx-font: 15 verdana;");
                TextField uniqueid = new TextField();
                uniqueid.setStyle("-fx-font: 15 verdana;");
                uniqueid.setPromptText("Enter usn");
                uniqueid.setFocusTraversable(false);
                root.add(ui, 0, 1);
                root.add(uniqueid, 1, 1);



                Label m_marks= new Label("Math:");
                m_marks.setStyle("-fx-font: 15 verdana;");
                TextField math_marks = new TextField();
                math_marks.setStyle("-fx-font: 15 verdana;");
                math_marks.setPromptText("Enter marks scored..");
                math_marks.setFocusTraversable(false);
                root.add(m_marks, 0, 3);
                root.add(math_marks, 1, 3);



                Label j_marks= new Label("Java:");
                j_marks.setStyle("-fx-font: 15 verdana;");
                TextField java_marks = new TextField();
                java_marks.setStyle("-fx-font: 15 verdana;");
                java_marks.setPromptText("Enter marks scored..");
                java_marks.setFocusTraversable(false);
                root.add(j_marks, 0, 5);
                root.add(java_marks, 1, 5);


                Label d_marks= new Label("DBMS:");
                d_marks.setStyle("-fx-font: 15 verdana;");
                TextField dbms_marks = new TextField();
                dbms_marks.setStyle("-fx-font: 15 verdana;");
                java_marks.setPromptText("Enter marks scored..");
                java_marks.setFocusTraversable(false);
                root.add(d_marks, 0, 8);
                root.add(dbms_marks, 1, 8);


                Label da_marks= new Label("DAA:");
                da_marks.setStyle("-fx-font: 15 verdana;");
                TextField daa_marks = new TextField();
                daa_marks.setPromptText("Enter marks scored..");
                daa_marks.setStyle("-fx-font: 15 verdana;");
                daa_marks.setFocusTraversable(false);
                root.add(da_marks, 0, 11);
                root.add(daa_marks, 1, 11);

                secondaryLayout.getChildren().addAll(root);


                Scene secondScene = new Scene(secondaryLayout, 230, 100);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Student Marks");
                newWindow.setScene(secondScene);


                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGRAY,
                        CornerRadii.EMPTY, Insets.EMPTY);

                // create Background
                Background background = new Background(background_fill);

                // set background
                root.setBackground(background);
                Button save=new Button("Save");
                save.setStyle("-fx-font: 15 verdana;");
                root.add(save, 1, 16);
                save.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event) {
                        System.out.println("USN: "+uniqueid.getText());
                        usn=uniqueid.getText();

                        System.out.println("DBMS Marks: "+dbms_marks.getText());
                        dbmsmak=dbms_marks.getText();
                        System.out.println("Java Marks: "+java_marks.getText());
                        javamark=java_marks.getText();
                        System.out.println("DAA Marks: "+daa_marks.getText());
                        daamark=daa_marks.getText();
                        System.out.println("Maths Marks: "+math_marks.getText());
                        mathmark=math_marks.getText();

                        Alert alert = new Alert(AlertType.WARNING);

                        if(math_marks.getText()=="" && java_marks.getText()=="" && daa_marks.getText()=="" && dbms_marks.getText()=="")
                        {
                            alert.setContentText("Please enter all the field values");
                            alert.showAndWait();
                        }
                        else{
                            writeresults();

                        }


                    }
                    public void writeresults() {
                        try {
                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "2234");
                            Statement statement = connection.createStatement();

                            statement.executeUpdate("insert into marks values('"+usn+"','"+javamark+"','"+mathmark+"','"+daamark+"','"+dbmsmak+"')");
                        }catch(Exception e){
                            e.printStackTrace();
                            System.err.println(e.getMessage());
                            System.err.println("Got an exception! ");
                        }



                    }



                });



                newWindow.show();

            }
        });

        //StackPane root = new StackPane();/
        //root.getChildren().add(button);/
        root.add(button, 1, 10);

        //Scene scene = new Scene(root, 450, 250);/

        primaryStage.setTitle("Admin Panel");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
