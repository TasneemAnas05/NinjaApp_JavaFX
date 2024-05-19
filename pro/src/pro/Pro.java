/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pro;

import java.awt.Color;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import tasks.dp.DatabaseManager;
import tasks.model.Task;
import tasks.model.User;

/**
 *
 * @author DELL
 */
public class Pro extends Application {

    DatabaseManager dbManager = new DatabaseManager();
    int userID = -1;
    String style = "";

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Ninja Prodactivity App");
        primaryStage.setScene(LandingPage(primaryStage));
        primaryStage.show();
    }

    private Scene buildLogin(Stage stage) {

        Label userNameLabel = new Label("User name");
        userNameLabel.setStyle("-fx-text-fill: #00b300;");
        userNameLabel.setFont(Font.font("Jost", 15));

        Label passwordLabel = new Label("Password");
        passwordLabel.setStyle("-fx-text-fill: #00b300;");
        passwordLabel.setFont(Font.font("Jost", 15));

        Button loginBtn = new Button("Login");
        Button registerBtn = new Button("register");

        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(10);
        root.setHgap(10);

        root.setStyle(style);
        System.out.println(style);
        root.add(userNameLabel, 0, 0);
        root.add(userNameField, 1, 0);
        root.add(passwordLabel, 0, 1);
        root.add(passwordField, 1, 1);
        HBox hbox = new HBox(15, loginBtn, registerBtn);

        root.add(hbox, 1, 3);

        loginBtn.setFont(Font.font("Jost", 15));
        loginBtn.setStyle("-fx-background-color: #00b300;");
        loginBtn.setDefaultButton(true);
        loginBtn.setOnMouseEntered(e -> {
            loginBtn.setStyle("-fx-background-color: #086c08;");

        });
        loginBtn.setOnMouseExited(e -> {
            loginBtn.setStyle("-fx-background-color: #00b300;");

        });
        registerBtn.setFont(Font.font("Jost", 15));
        registerBtn.setStyle("-fx-background-color: #00b300;");
        registerBtn.setOnMouseEntered(e -> {
            registerBtn.setStyle("-fx-background-color: #086c08;");

        });
        registerBtn.setOnMouseExited(e -> {
            registerBtn.setStyle("-fx-background-color: #00b300;");

        });
        loginBtn.setOnAction((event) -> {
            String userName = userNameField.getText();
            String password = passwordField.getText();
            int loginResult = login(userName, password);
            if (loginResult != -1) {
                userID = loginResult;
                Alert success = new Alert(Alert.AlertType.CONFIRMATION);
                success.setTitle("Login Success");
                success.show();

//                JOptionPane.showMessageDialog(null, "Login Success ");
                stage.setScene(buildHomeScene(stage));

            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password");
            }
        });
        Scene scene = new Scene(root, 600, 600);
        registerBtn.setOnAction((event) -> {
            stage.setScene(buildRegistrationScene(stage));
            stage.setTitle("Register");
        });
        return scene;
    }

    private Scene buildRegistrationScene(Stage stage) {

        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildLogin(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });
        Label userNameLabel = new Label("User name");
        Label passwordLabel = new Label("Password");

        userNameLabel.setStyle("-fx-text-fill: #00b300;");
        userNameLabel.setFont(Font.font("Jost", 15));
        passwordLabel.setStyle("-fx-text-fill: #00b300;");
        passwordLabel.setFont(Font.font("Jost", 15));
        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();

        ToggleGroup group = new ToggleGroup();
        RadioButton btnFemale = new RadioButton("Female");
        btnFemale.setToggleGroup(group);
        btnFemale.setSelected(true);
        RadioButton btnMale = new RadioButton("Male");
        btnMale.setToggleGroup(group);
        Button registerBtn = new Button("register");

        btnFemale.setStyle("-fx-text-fill: #00b300;");
        btnFemale.setFont(Font.font("Jost", 15));

        btnMale.setStyle("-fx-text-fill: #00b300;");
        btnMale.setFont(Font.font("Jost", 15));

        registerBtn.setFont(Font.font("Jost", 15));
        registerBtn.setStyle("-fx-background-color: #00b300;");
        registerBtn.setOnMouseEntered(e -> {
            registerBtn.setStyle("-fx-background-color: #086c08;");

        });
        registerBtn.setOnMouseExited(e -> {
            registerBtn.setStyle("-fx-background-color: #00b300;");

        });
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setVgap(16);
        root.setHgap(16);
        
        root.setStyle(style);
        root.add(userNameLabel, 0, 0);
        root.add(userNameField, 1, 0);
        root.add(passwordLabel, 0, 1);
        root.add(passwordField, 1, 1);
        root.add(btnFemale, 0, 2);
        root.add(btnMale, 1, 2);
        root.add(registerBtn, 1, 3);
        root.add(BackBtn, 0, 11);
        Scene scene = new Scene(root, 600, 600);

        registerBtn.setOnAction((ActionEvent event) -> {
            String userName = userNameField.getText();
            String password = passwordField.getText();

            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            String toogleGroupValue = selectedRadioButton.getText();
            boolean registrationResult = dbManager.register(userName, password, toogleGroupValue.charAt(0) + "");
            if (registrationResult) {
                userID = login(userName, password);
                JOptionPane.showMessageDialog(null, "Registration Success");
                 stage.setScene(buildHomeScene(stage));//!!!!!!!!!!!!!!!!!!!!!!
                
            } else {
                JOptionPane.showMessageDialog(null, "Registration failed");
            }
        });

        return scene;
    }

    private int login(String userName, String password) {

        try {
            ArrayList<User> usersList = dbManager.loadUsers();

            for (int i = 0; i < usersList.size(); i++) {
                if (usersList.get(i).getUser_name().equals(userName) && usersList.get(i).getPassword().equals(password)) {
                    userID = usersList.get(i).getId();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return userID;
    }

    private Scene buildHomeScene(Stage stage) {
        BorderPane root = new BorderPane();

        Button loadBtn = new Button("Load");
        loadBtn.setFont(Font.font("Jost", 15));

        loadBtn.setStyle("-fx-background-color: #00b300;");
        loadBtn.setOnMouseEntered(e -> {
            loadBtn.setStyle("-fx-background-color: #086c08;");

        });
        loadBtn.setOnMouseExited(e -> {
            loadBtn.setStyle("-fx-background-color: #00b300;");

        });
        Button update = new Button("Update");
        update.setFont(Font.font("Jost", 15));
        update.setStyle("-fx-background-color: #00b300;");
        update.setOnMouseEntered(e -> {
            update.setStyle("-fx-background-color: #086c08;");

        });
        update.setOnMouseExited(e -> {
            update.setStyle("-fx-background-color: #00b300;");

        });

        Button delete = new Button("Delete");
        delete.setFont(Font.font("Jost", 15));
        delete.setStyle("-fx-background-color: #00b300;");
        delete.setOnMouseEntered(e -> {
            delete.setStyle("-fx-background-color: #086c08;");

        });
        delete.setOnMouseExited(e -> {
            delete.setStyle("-fx-background-color: #00b300;");

        });
        Button insertBtn = new Button("Insert");
        insertBtn.setFont(Font.font("Jost", 15));
        insertBtn.setStyle("-fx-background-color: #00b300;");
        insertBtn.setOnMouseEntered(e -> {
            insertBtn.setStyle("-fx-background-color: #086c08;");

        });
        insertBtn.setOnMouseExited(e -> {
            insertBtn.setStyle("-fx-background-color: #00b300;");

        });
        Button profileBtn = new Button("Profile");
        profileBtn.setFont(Font.font("Jost", 15));
        profileBtn.setStyle("-fx-background-color: #00b300;");
        profileBtn.setOnMouseEntered(e -> {
            profileBtn.setStyle("-fx-background-color: #086c08;");

        });
        profileBtn.setOnMouseExited(e -> {
            profileBtn.setStyle("-fx-background-color: #00b300;");

        });
        Button CustomeBtn = new Button("Custimize");
        CustomeBtn.setFont(Font.font("Jost", 15));
        CustomeBtn.setStyle("-fx-background-color: #00b300;");
        CustomeBtn.setOnMouseEntered(e -> {
            CustomeBtn.setStyle("-fx-background-color: #086c08;");

        });
        CustomeBtn.setOnMouseExited(e -> {
            CustomeBtn.setStyle("-fx-background-color: #00b300;");

        });
        CustomeBtn.setOnAction((event) -> {
            stage.setScene(custimize(stage));
            root.setStyle(style);
        });
        Button aboutBtn = new Button("About");
        aboutBtn.setFont(Font.font("Jost", 15));

        aboutBtn.setStyle("-fx-background-color: #00b300;");
        aboutBtn.setOnMouseEntered(e -> {
            aboutBtn.setStyle("-fx-background-color: #086c08;");

        });
        aboutBtn.setOnMouseExited(e -> {
            aboutBtn.setStyle("-fx-background-color: #00b300;");

        });

        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildLogin(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });
        profileBtn.setOnAction((event) -> {
            stage.setScene(ProfilePage(stage));

        });
        aboutBtn.setOnAction((event) -> {
            stage.setScene(AboutPage(stage));
        });

        CustomeBtn.fire();
        ListView<Task> list = new ListView<>();
        loadBtn.setOnAction((event) -> {
            ArrayList<Task> tasksList = dbManager.loadTasks();
            for (int i = 0; i < tasksList.size(); i++) {
                if (tasksList.get(i).getUser_id() == userID) {
                    list.getItems().add(tasksList.get(i));
                }
            }
            root.setCenter(list);

        });

        delete.setOnAction((event) -> {
            Task item = list.getSelectionModel().getSelectedItem();
            if (item == null) {
                JOptionPane.showConfirmDialog(null, "select a task to be deleted ");
            } else {
                stage.setScene(buildDeleteScene(stage, item));
            }

        });
        insertBtn.setOnAction((event) -> {

            stage.setScene(buildInsertTasksScene(stage));

        });
        update.setOnAction((event) -> {

            Task item = list.getSelectionModel().getSelectedItem();
            if (item == null) {
                JOptionPane.showConfirmDialog(null, "select a task to be updated ");
            } else {
                stage.setScene(buildUpdateScene(stage, item));
            }
        });
        VBox leftContainer = new VBox(16, loadBtn, update, delete, insertBtn, profileBtn, aboutBtn, CustomeBtn, BackBtn);
        root.setLeft(leftContainer);

        root.setCenter(list);

        Scene scene = new Scene(root, 600, 600);
        return scene;
    }

    private Scene buildInsertTasksScene(Stage stage) {

        Label nameLabel = new Label("Task name");
        nameLabel.setFont(Font.font("Jost", 15));
        nameLabel.setStyle("-fx-text-fill: #00b300;");

        Label dateLabel = new Label("Date");
        dateLabel.setFont(Font.font("Jost", 15));
        dateLabel.setStyle("-fx-text-fill: #00b300;");

        Label descriptionLabel = new Label("description");
        descriptionLabel.setFont(Font.font("Jost", 15));
        descriptionLabel.setStyle("-fx-text-fill: #00b300;");

        TextField nameField = new TextField();
        TextField dateField = new TextField();
        TextField descriptionField = new TextField();

        Button insert = new Button("Insert");
        Button cancel = new Button("Cancel");

        cancel.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });

        cancel.setFont(Font.font("Jost", 15));

        cancel.setStyle("-fx-background-color: #00b300;");
        cancel.setOnMouseEntered(e -> {
            cancel.setStyle("-fx-background-color: #086c08;");

        });
        cancel.setOnMouseExited(e -> {
            cancel.setStyle("-fx-background-color: #00b300;");

        });

        insert.setFont(Font.font("Jost", 15));

        insert.setStyle("-fx-background-color: #00b300;");
        insert.setOnMouseEntered(e -> {
            insert.setStyle("-fx-background-color: #086c08;");

        });
        insert.setOnMouseExited(e -> {
            insert.setStyle("-fx-background-color: #00b300;");

        });
        insert.setOnAction((event) -> {
            String name = nameField.getText();
            String date = dateField.getText();
            String description = descriptionField.getText();
            boolean response = dbManager.inserTask(name, date, description, userID);
            if (response == true) {
                stage.setScene(buildHomeScene(stage));
                System.out.println("kjh");
            } else {
                System.out.println("not inserted");
            }
        });
        HBox btns = new HBox(insert, cancel);
     
        btns.setAlignment(Pos.CENTER);
        
        VBox root = new VBox(
                nameLabel,
                nameField, dateLabel, dateField, descriptionLabel, descriptionField, btns
        );
        
        Scene scene = new Scene(root, 500, 500);
        return scene;
    }

    private Scene buildDeleteScene(Stage stage, Task item) {

        Label Row = new Label("Are You Sure You Want To Delete This Task ? \n" + item);
        Row.setFont(Font.font("Jost", 15));

        Button yesBtn = new Button("Yes");
        Button NoBtn = new Button("No");
        NoBtn.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });
        yesBtn.setOnAction((event) -> {

            dbManager.deleteTask(item.getId());
            stage.setScene(buildHomeScene(stage));

        });

        GridPane root = new GridPane();
        yesBtn.setFont(Font.font("Jost", 15));
        yesBtn.setStyle("-fx-background-color: #00b300;");
        yesBtn.setOnMouseEntered(e -> {
            yesBtn.setStyle("-fx-background-color: #086c08;");

        });
        yesBtn.setOnMouseExited(e -> {
            yesBtn.setStyle("-fx-background-color: #00b300;");

        });

        NoBtn.setFont(Font.font("Jost", 15));
        NoBtn.setStyle("-fx-background-color: #00b300;");
        NoBtn.setOnMouseEntered(e -> {
            NoBtn.setStyle("-fx-background-color: #086c08;");

        });
        NoBtn.setOnMouseExited(e -> {
            NoBtn.setStyle("-fx-background-color: #00b300;");

        });

        root.add(Row, 0, 0);
        HBox hbox = new HBox(12);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(yesBtn, NoBtn);
//        root.add(yesBtn, 0, 1);
//        root.add(NoBtn, 0, 1);
        root.add(hbox, 0, 2);
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 500);
        return scene;
    }

    private Scene buildUpdateScene(Stage stage, Task item) {

        Label Row = new Label("Are You Sure You Want To Update This Task ? \n" + item);
        Row.setFont(Font.font("Jost", 15));

        Label updatedLabel = new Label("Update Value");
        updatedLabel.setFont(Font.font("Jost", 15));

        TextField updatedValue = new TextField();
        updatedValue.setMaxSize(200, 200);
        Button nameBtn = new Button("Name");
        nameBtn.setFont(Font.font("Jost", 15));
        nameBtn.setStyle("-fx-background-color: #00b300;");
        nameBtn.setOnMouseEntered(e -> {
            nameBtn.setStyle("-fx-background-color: #086c08;");

        });
        nameBtn.setOnMouseExited(e -> {
            nameBtn.setStyle("-fx-background-color: #00b300;");

        });
        Button dateBtn = new Button("date");
        dateBtn.setFont(Font.font("Jost", 15));
        dateBtn.setStyle("-fx-background-color: #00b300;");
        dateBtn.setOnMouseEntered(e -> {
            dateBtn.setStyle("-fx-background-color: #086c08;");

        });
        dateBtn.setOnMouseExited(e -> {
            dateBtn.setStyle("-fx-background-color: #00b300;");

        });
        Button descriptionBtn = new Button("description");
        descriptionBtn.setFont(Font.font("Jost", 15));
        descriptionBtn.setStyle("-fx-background-color: #00b300;");
        descriptionBtn.setOnMouseEntered(e -> {
            descriptionBtn.setStyle("-fx-background-color: #086c08;");

        });
        descriptionBtn.setOnMouseExited(e -> {
            descriptionBtn.setStyle("-fx-background-color: #00b300;");

        });

        nameBtn.setOnAction((event) -> {
            dbManager.updateTaskName(updatedValue.getText(), item.getId());
            stage.setScene(buildHomeScene(stage));

        });
        dateBtn.setOnAction((event) -> {
            dbManager.updateDate(updatedValue.getText(), item.getId());
            stage.setScene(buildHomeScene(stage));

        });
        descriptionBtn.setOnAction((ActionEvent event) -> {
            dbManager.updateDescription(updatedValue.getText(), item.getId());
            stage.setScene(buildHomeScene(stage));

        });
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });
        HBox hbox = new HBox(12);
        hbox.setAlignment(Pos.CENTER);
        hbox.getChildren().addAll(nameBtn, dateBtn, descriptionBtn);
        hbox.setSpacing(userID);
        VBox root = new VBox(8,Row, updatedLabel, updatedValue, hbox, BackBtn);
        
        
        root.setAlignment(Pos.CENTER);
        Scene scene = new Scene(root, 500, 500);
        return scene;
    }

    private Scene LandingPage(Stage stage) {
        //---------Landing Page-------
        GridPane root = new GridPane();
        // btn with image view
        root.setVgap(12);
        Button btn1;
        try {

            Image image = new Image("https://i.pinimg.com/564x/01/7f/be/017fbe1b7eac2700feff7ed1c76a06ec.jpg");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            btn1 = new Button("", imageView);
            btn1.setStyle("-fx-background-color: #00b300;");

            root.add(btn1, 0, 0);
            root.setAlignment(Pos.CENTER);

        } catch (Exception e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "File not Found");
        }
        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label label1 = new Label("Ninja Productivity App");
        label1.setFont(Font.font("Jost", 25));

        Label label2 = new Label("Welcome :)");
        label2.setFont(Font.font("Jost", 25));

        Button btn2 = new Button("Let's Start");
        vbox.getChildren().addAll(label1, label2, btn2);
        root.add(vbox, 0, 1);
        btn2.setFont(Font.font("Jost", 25));
        btn2.setStyle("-fx-background-color: #00b300;");
        btn2.setDefaultButton(true);
        btn2.setOnMouseEntered(e -> {
            btn2.setStyle("-fx-background-color: #086c08;");

        });
        btn2.setOnMouseExited(e -> {
            btn2.setStyle("-fx-background-color: #00b300;");

        });
        btn2.setOnAction((event) -> {
            stage.setScene(buildLogin(stage));
        });
        Scene scene = new Scene(root, 600, 600);
        return scene;

    }

    private Scene AboutPage(Stage stage) {
        GridPane root = new GridPane();
        Button btn3;
        btn3 = new Button();
        try {
            Image image = new Image("https://i.pinimg.com/564x/a9/b2/eb/a9b2eb38b1ff3db16d97f7fe562f7d26.jpg");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            btn3 = new Button("", imageView);
            btn3.setStyle("-fx-background-color: #00b300;");

            root.setAlignment(Pos.CENTER);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not Found");
        }
        Label label3 = new Label("About Ninja App");
        label3.setFont(Font.font("Jost", 20));
        Label label4 = new Label("It is a productivity Application that helps you to keep\n "
                + "track of your tasks by writing it's name ,describtion "
                + "\n and date to be done developed by Lujain and Tasnim "
                + "\n two sisters who studies Computer Sceience "
                + "\n at Applied Sceience University ");
        label4.setWrapText(true);
        label4.setFont(Font.font("Jost", 15));
        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });

        VBox vbox = new VBox();

        vbox.getChildren().addAll(btn3, label3, label4, BackBtn);
        vbox.setAlignment(Pos.CENTER);
        root.setVgap(12);
        root.add(vbox, 0, 1);
        Scene scene = new Scene(root, 600, 600);

        return scene;
    }

    private Scene ProfilePage(Stage stage) {

        User user = dbManager.getUserById(userID);
        GridPane root = new GridPane();
        Button btn5 = new Button();
        try {
            Image image = new Image("https://i.pinimg.com/564x/d7/66/fb/d766fba345c91fb31a3d12fce3a32728.jpg");
            ImageView imageView = new ImageView();
            imageView.setImage(image);
            btn5 = new Button("", imageView);
            btn5.setStyle("-fx-background-color: #00b300;");

            root.add(btn5, 0, 0);
            root.setAlignment(Pos.TOP_CENTER);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "File not Found");
        }
        Label label5 = new Label("Ninja Profile");
        label5.setFont(Font.font("Jost", 25));
        VBox vbox = new VBox(btn5, label5);
        vbox.setAlignment(Pos.CENTER);
        root.getChildren().add(vbox);
        Label label6 = new Label("ID");
        label6.setFont(Font.font("Jost", 20));
        label6.setStyle("-fx-text-fill: #00b300;");

        Label label7 = new Label(String.valueOf(user.getId()));//from DB bring ID
        label7.setFont(Font.font("Jost", 15));

        Label label8 = new Label("Ninja Name");
        label8.setFont(Font.font("Jost", 20));
        label8.setStyle("-fx-text-fill: #00b300;");

        Label label9 = new Label(user.getUser_name());//from DB bring Name
        label9.setFont(Font.font("Jost", 15));

        Label label10 = new Label("Gender");
        label10.setFont(Font.font("Jost", 20));
        label10.setStyle("-fx-text-fill: #00b300;");

        Label label11 = new Label(user.getGender());//from DB bring Gender
        label11.setFont(Font.font("Jost", 15));

        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });

        vbox.getChildren().addAll(label6, label7, label8, label9, label10, label11, BackBtn);
        Scene scene = new Scene(root, 600, 600);

        return scene;
    }

    private Scene custimize(Stage stage) {

        //ContextMenu menu = new ContextMenu("Choose Navbar Color");
        Label txt = new Label("Choose Navbar Color");
        txt.setFont(Font.font("Jost", 20));

        MenuItem m1 = new MenuItem("blue");
        MenuItem m2 = new MenuItem("pink");
        MenuItem m3 = new MenuItem("black");
        MenuItem m4 = new MenuItem("gray");
        ContextMenu menu = new ContextMenu(m1, m2, m3, m4);

        //menu.setFont(Font.font("Jost", 20));
        //menu.getItems().addAll(m1,m2,m3,m4);
        //MenuBar mb = new MenuBar();
        //menu.getItems().addAll(m1,m2,m3,m4);
        txt.setContextMenu(menu);
        m1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                style = ("-fx-background-color: #A7E4F6;");

            }
        });
        m2.setOnAction(e -> {
            style = ("-fx-background-color: #F988E2;");
        });
        m3.setOnAction(e -> {
            style = ("-fx-background-color:#1F1E23;");
        });
        m4.setOnAction(e -> {
            style = ("-fx-background-color: #B1AFB8;");

        });

        Button BackBtn = new Button("Back");
        BackBtn.setOnAction((event) -> {
            stage.setScene(buildHomeScene(stage));
        });

        BackBtn.setFont(Font.font("Jost", 15));
        BackBtn.setStyle("-fx-background-color: #00b300;");
        BackBtn.setOnMouseEntered(e -> {
            BackBtn.setStyle("-fx-background-color: #086c08;");

        });
        BackBtn.setOnMouseExited(e -> {
            BackBtn.setStyle("-fx-background-color: #00b300;");

        });
        System.out.println(style);
        VBox vbox = new VBox(txt, BackBtn);

        vbox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vbox, 600, 600);
        return scene;

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
