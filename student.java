package sample;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.*;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javafx.scene.control.Button;


public class Main extends Application{
    public static  String dname,dusn,dgender,demailid,dmobile,dsemester,dcourse,usn;
    String genderinput="";
    String dateoutput="";
    String seminput="";
    boolean flag1=false,flag2=false,flag3=false,flag4=false,flag5=false,flag6=false,flag7=false,flag8=false;


    @Override
    public void start(Stage primaryStage) throws Exception {





        GridPane root=new GridPane();
        root.setHgap(5);
        root.setVgap(20);
        Text textscene=new Text("GRADE TRACKER");
        textscene.setStyle("-fx-font: 24 verdana;");
        textscene.setTextAlignment(TextAlignment.CENTER);


        Label name=new Label("Student Name:");
        name.setStyle("-fx-font: 15 verdana;");
        final TextField nameinput=new TextField();
        nameinput.setStyle("-fx-font: 15 verdana;");
        nameinput.setPromptText("Enter your full Name");
        nameinput.setFocusTraversable(false);
        root.add(name,0,1);
        root.add(nameinput,1,1);

        Label dob=new Label("DOB:");
        dob.setStyle("-fx-font: 15 verdana;");
        String pattern="yyyy-MM-dd";
        DatePicker date=new DatePicker();
        date.setStyle("-fx-font: 15 verdana;");
        date.setPromptText(pattern.toLowerCase());

        date.setConverter(new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }

            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        });
        root.add(dob,0,8);

        root.add(date,1,8);

        Label gender=new Label("Gender:");
        gender.setStyle("-fx-font: 15 verdana;");
        root.add(gender, 0, 3);
        ToggleGroup pickgender=new ToggleGroup();
        RadioButton pickmale=new RadioButton("Male");
        pickmale.setStyle("-fx-font: 15 verdana;");
        pickmale.setToggleGroup(pickgender);
        root.add(pickmale, 1, 3, 1, 1);

        RadioButton pickfemale=new RadioButton("Female");
        pickfemale.setStyle("-fx-font: 15 verdana;");
        pickfemale.setToggleGroup(pickgender);
        root.add(pickfemale, 2, 3);



        Label mobile=new Label("Mobile number:");
        mobile.setStyle("-fx-font: 15 verdana;");
        TextField mobileinput=new TextField();
        mobileinput.setPromptText("Enter your MObile Number");
        mobileinput.setStyle("-fx-font: 15 verdana;");
        mobileinput.setFocusTraversable(false);
        root.add(mobile, 0, 5);
        root.add(mobileinput, 1, 5);

        Label sem=new Label("Semester:");
        sem.setStyle("-fx-font: 15 verdana;");
        root.add(sem, 0, 6);
        ToggleGroup picksem=new ToggleGroup();
        RadioButton pick2=new RadioButton("2nd sem");
        pick2.setToggleGroup(picksem);
        pick2.setStyle("-fx-font: 15 verdana;");
        root.add(pick2, 1, 6, 1, 1);

        RadioButton pick4=new RadioButton("4th sem");
        pick4.setToggleGroup(picksem);
        pick4.setStyle("-fx-font: 15 verdana;");
        root.add(pick4, 2, 6);
        RadioButton pick6=new RadioButton("6th sem");
        pick6.setToggleGroup(picksem);
        pick6.setStyle("-fx-font: 15 verdana;");
        root.add(pick6, 3, 6);




        Label interest=new Label("USN:");
        interest.setStyle("-fx-font: 15 verdana;");
        TextField interestinput=new TextField();
        interestinput.setPromptText("Enter your interest");
        interestinput.setFocusTraversable(false);
        interestinput.setStyle("-fx-font: 15 verdana;");
        root.add(interest, 0, 2);
        root.add(interestinput, 1, 2);

        Label emailid=new Label("College Email Id:");
        emailid.setStyle("-fx-font: 15 verdana;");
        TextField emailinput=new TextField();
        emailinput.setStyle("-fx-font: 15 verdana;");
        emailinput.setPromptText("Enter your College Email Id");
        emailinput.setFocusTraversable(false);
        root.add(emailid, 0, 4);
        root.add(emailinput, 1, 4);

        root.add(textscene,0,0,3,1);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setTitle("Student Marks");
        textscene.setFont(Font.font("Tahoma",FontWeight.NORMAL,20));
        root.setAlignment(Pos.CENTER);

        ComboBox<String> cbCourse;
        Label course=new Label("Course:");
        course.setStyle("-fx-font: 15 verdana;");
        ObservableList<String> courses =
                FXCollections.observableArrayList( "CSE", "ISE", "ECE" );
        cbCourse = new ComboBox<String>(courses);
        cbCourse.setStyle("-fx-font: 15 verdana;");
        cbCourse.setValue("CSE");
        dcourse= cbCourse.getValue();
        cbCourse.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent ae) {
                //course.setText("Selected Course is " + cbCourse.getValue());
            }
        });
        root.add(course, 0, 7);
        root.add(cbCourse,1,7);





        Button save=new Button("Save");
        save.setStyle("-fx-font: 15 verdana;");
        root.add(save, 1, 10);



        dateoutput=date.toString();
        //dateoutput=date.getValue().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date d = new Date();
        //String alok=d.toString();
        String alok=dateFormat.format(d).toString();

        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        // set background
        root.setBackground(background);
        Button button = new Button();
        button.setText("Get Results");

        button.setOnAction(new EventHandler<ActionEvent>() {


                @Override
            public void handle(ActionEvent event) {

                    StackPane secondaryLayout=new StackPane();
                    GridPane root = new GridPane();
                    root.setHgap(5);
                    root.setVgap(20);




                Text textscene = new Text(" Student Marks");
                textscene.setStyle("-fx-font: 24 verdana;");
                root.add(textscene, 1,2);
                root.setAlignment(Pos.CENTER);
                Scene scene = new Scene(root, 500, 500);


                Label ui = new Label("Student usn:");
                ui.setStyle("-fx-font: 15 verdana;");
                TextField uniqueid = new TextField();
                uniqueid.setStyle("-fx-font: 15 verdana;");
                uniqueid.setPromptText("Enter usn");
                uniqueid.setFocusTraversable(false);
                root.add(ui, 0, 3);
                root.add(uniqueid, 1, 3);



                Label m_marks= new Label("Math:");
                m_marks.setStyle("-fx-font: 15 verdana;");
                TextField math_marks = new TextField();
                math_marks.setStyle("-fx-font: 15 verdana;");
                math_marks.setPromptText(" marks scored..");
                math_marks.setFocusTraversable(false);
                root.add(m_marks, 0, 5);
                root.add(math_marks, 1, 5);



                Label j_marks= new Label("Java:");
                j_marks.setStyle("-fx-font: 15 verdana;");
                TextField java_marks = new TextField();
                java_marks.setStyle("-fx-font: 15 verdana;");
                java_marks.setPromptText(" marks scored..");
                java_marks.setFocusTraversable(false);
                root.add(j_marks, 0, 7);
                root.add(java_marks, 1, 7);


                Label d_marks= new Label("DBMS:");
                d_marks.setStyle("-fx-font: 15 verdana;");
                TextField dbms_marks = new TextField();
                dbms_marks.setStyle("-fx-font: 15 verdana;");
                java_marks.setPromptText(" marks scored..");
                java_marks.setFocusTraversable(false);
                root.add(d_marks, 0, 9);
                root.add(dbms_marks, 1, 9);


                Label da_marks= new Label("DAA:");
                da_marks.setStyle("-fx-font: 15 verdana;");
                TextField daa_marks = new TextField();
                daa_marks.setPromptText(" marks scored..");
                daa_marks.setStyle("-fx-font: 15 verdana;");
                daa_marks.setFocusTraversable(false);
                root.add(da_marks, 0, 11);
                root.add(daa_marks, 1, 11);

                Label t_marks= new Label("Total Marks:");
                t_marks.setStyle("-fx-font: 15 verdana;");
                TextField to_marks = new TextField();
                daa_marks.setPromptText(" marks scored..");
                to_marks.setStyle("-fx-font: 15 verdana;");
                daa_marks.setFocusTraversable(false);
                root.add(t_marks, 0, 14);
                root.add(to_marks, 1, 14);

                    Label t_percent= new Label("Percentage:");
                    t_percent.setStyle("-fx-font: 15 verdana;");
                    TextField to_percent = new TextField();
                    to_percent.setStyle("-fx-font: 15 verdana;");
                    root.add(t_percent, 0, 15);
                    root.add(to_percent, 1, 15);



                    secondaryLayout.getChildren().addAll(root);

                    Scene secondScene = new Scene(secondaryLayout, 230, 100);


                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Student Marks");
                newWindow.setScene(secondScene);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                BackgroundFill background_fill = new BackgroundFill(Color.LIGHTBLUE,
                        CornerRadii.EMPTY, Insets.EMPTY);

                // create Background
                Background background = new Background(background_fill);

                // set background
                root.setBackground(background);
                Button save=new Button("Click to Get Results");
                save.setStyle("-fx-font: 15 verdana;");
                root.add(save, 1, 16);
                save.setOnAction(new EventHandler<ActionEvent>()
                {
                    public void handle(ActionEvent event) {
                        Alert alert = new Alert(AlertType.WARNING);

                        writeresults();

                    }

                    public void writeresults() {

                        try {

                            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "2234");
                            Statement statement = connection.createStatement();
                            String QUERY = "SELECT java,math,daa,dbms from marks where usn="+dusn;

                            ResultSet rs = statement.executeQuery(QUERY);

                            while(rs.next()) {

                                System.out.print("Java Marks: " + rs.getInt("java"));
                                System.out.print(", Math Marks: " + rs.getInt("math"));
                                System.out.print(", Daa Marks: " + rs.getString("daa"));
                                System.out.print(", DBMS marks: " + rs.getString("dbms"));
                                String insertdaa=rs.getString("daa");
                                String insertddms=rs.getString("dbms");
                                String insertjava=rs.getString("java");
                                String insertmath=rs.getString("math");
                                System.out.println("marks: " + insertdaa+" "+insertmath);
                                daa_marks.setText(insertdaa);
                                dbms_marks.setText(insertddms);
                                math_marks.setText(insertmath);
                                java_marks.setText(insertjava);
                                int i=Integer.parseInt(insertddms);
                                int j=Integer.parseInt(insertdaa);
                                int k=Integer.parseInt(insertjava);
                                int l=Integer.parseInt(insertmath);
                                int total=i+j+k+l;

                                String s=String.valueOf(total);
                                to_marks.setText(s);
                                double percent=Double.parseDouble(to_marks.getText())/1.20;
                                String p=String.valueOf(percent+"%");

                                to_percent.setText(p);
                            }
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


        root.add(button, 1, 12);

        save.setOnAction(new EventHandler<ActionEvent>()
        {
            @Override

            public void handle(ActionEvent event) {

                ProgressBar progressBar = new ProgressBar(0);

                VBox vBox = new VBox(progressBar);
                //Scene scene = new Scene(vBox, 960, 600);

                //primaryStage.setScene(scene);
                //primaryStage.show();

                Thread taskThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        double progress = 0;
                        for (int i = 0; i < 10; i++) {

                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            progress += 0.4;
                            final double reportedProgress = progress;

                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar
                                            .setProgress(reportedProgress);
                                }
                            });
                        }
                    }
                });

                taskThread.start();
                root.add(progressBar,1,11);





                if(pickmale.isSelected())
                {
                    genderinput="male";
                    flag3=true;
                }
                if(pickfemale.isSelected())
                {
                    flag3=true;
                    genderinput="female";
                }
                if(pick2.isSelected())
                {
                    seminput= "2nd Semester";
                    flag6=true;
                }
                if(pick4.isSelected())
                {
                    flag6=true;
                    seminput="4th Semester";
                }
                if(pick6.isSelected())
                {
                    flag6=true;
                    seminput="6th Semester";
                }

                if(nameinput.getText().length()>=2 && nameinput.getText().matches("[A-Za-z ]+"))
                {
                    flag1=true;
                }
                if(interestinput.getText().length()>=2 && interestinput.getText().matches("A-Za-z ]+")) {
                    flag4 = true;
                }
                if(mobileinput.getText().length()==10)
                {
                    flag5=true;
                }

                flag2=true;

                System.out.println("Your Details:-");

                System.out.println("Name: "+nameinput.getText());
                dname=nameinput.getText();
                System.out.println("DOB: "+dateoutput);
                System.out.println("Gender: "+genderinput);
                dgender=genderinput;
                System.out.println("USN: "+interestinput.getText());
                dusn=interestinput.getText();
                System.out.println("Mobile Number: "+mobileinput.getText());
                dmobile=mobileinput.getText();
                System.out.println("Email: "+emailinput.getText());
                demailid=emailinput.getText();
                System.out.println("Sem: "+seminput);
                dsemester=seminput;
                System.out.println("Course: "+dcourse);

                Alert alert = new Alert(AlertType.WARNING);
                if(flag3)
                {
                    writenewuser();

                }
                else if(!flag1)
                {
                    alert.setContentText("Please enter some valid name");
                    alert.showAndWait();
                }
                else if(!flag2)
                {

                    // alert.setContentText("Please choose some valid date");
                    //alert.showAndWait();
                }

                else if(!flag4)
                {
                    //alert.setContentText("Please fill some valid USN");
                    //alert.showAndWait();
                }
                else if(!flag1 && !flag3 || !flag1 && !flag4 || !flag3 && !flag4)
                {
                    alert.setContentText("Please fill appropriate in the field");
                    alert.showAndWait();
                }

            }

            public void writenewuser() {
                try {
                    Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject", "root", "2234");
                    Statement statement = connection.createStatement();

                    statement.executeUpdate("insert into student values('"+dname+"','"+dusn+"','"+dgender+"','"+demailid+"','"+dmobile+"','"+dsemester+"','"+dcourse+"')");
                }catch(Exception e){
                    e.printStackTrace();
                    System.err.println(e.getMessage());
                    System.err.println("Got an exception!! ");
                }



            }


        });


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String args[])
    {

        launch(args);


    }

}

