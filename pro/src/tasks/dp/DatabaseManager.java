


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasks.dp;

import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import tasks.model.Task;
import tasks.model.User;

/**
 *
 * @author DELL
 */
public class DatabaseManager {

    Connection connection;

    public DatabaseManager() {
        String dbUrl = "jdbc:mysql://localhost:3306/pro_db";
        try {
            connection = DriverManager.getConnection(dbUrl, "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<User> loadUsers() {

        ArrayList<User> usersList = new ArrayList<User>();

        try {
            String s = "select * from users";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(s);

            while (rs.next()) {
                int id = rs.getInt("id");
                String user_name = rs.getString("user_name");
                String password = rs.getString("password");
                String gender = rs.getString("gender");

                User u = new User(id, user_name, password, gender);
                usersList.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return usersList;
    }

    public ArrayList<Task> loadTasks() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            String s = "select * from tasks inner join users on tasks.user_id = users.ID";
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(s);
            System.out.println(s);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String date = rs.getString("date");
                String description = rs.getString("description");
                int user_id = rs.getInt("user_id");

                Task t = new Task(id, name, date, description, user_id);
                taskList.add(t);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return taskList;
    }

    public boolean register(String userName, String password, String gender) {
        try {
            if (userName.isEmpty() || password.isEmpty() || gender.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Missing information");
                return false;
            }
            Statement sqlStatement = connection.createStatement();
            String insertStm = "insert into users (user_name,password,gender) values('"
                    + userName + "','" + password + "','" + gender + "')";

            System.out.println(insertStm);
            sqlStatement.execute(insertStm);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    public boolean deleteTask(int id) {
        try {
            String deleteStm = "delete from tasks where ID=?";
            PreparedStatement preparedStm
                    = connection.prepareStatement(deleteStm);
            preparedStm.setString(1, String.valueOf(id));

            System.out.print(preparedStm.toString());

            preparedStm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean inserTask(String name, String date, String description, int id) {

        try {
            Statement sqlStatement = connection.createStatement();
            String insertStm = "insert into tasks (name,date,description ,user_id) values('"
                    + name + "','" + date + "','" + description + "','" + id + "')";
            System.out.println(insertStm);
            sqlStatement.execute(insertStm);
            return true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateTaskName(String name, int id) {

        try {
            String updateStm = "update tasks set name= ? where id=?";
            PreparedStatement preparedstm
                    = connection.prepareStatement(updateStm);
            preparedstm.setString(1, name);
            preparedstm.setInt(2, id);
            preparedstm.executeUpdate();
            System.out.println(updateStm);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDate(String date, int id) {

        try {
            String updateStm = "update tasks set date= ? where id=?";
            PreparedStatement preparedstm
                    = connection.prepareStatement(updateStm);
            preparedstm.setString(1, date);
            preparedstm.setInt(2, id);
            preparedstm.executeUpdate();
            System.out.println(updateStm);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateDescription(String description, int id) {

        try {
            String updateStm = "update tasks set description= ? where id=?";
            PreparedStatement preparedstm
                    = connection.prepareStatement(updateStm);
            preparedstm.setString(1, description);
            preparedstm.setInt(2, id);
            preparedstm.executeUpdate();
            System.out.println(updateStm);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public User getUserById(int ID) {
        User user = null ;
        try {
            String s = "select * from users where ID = " + ID ;
            Statement stm = connection.createStatement();
            ResultSet rs = stm.executeQuery(s);

               System.out.println(s);
            
            while (rs.next()) {
                int id = rs.getInt("ID");
                String user_name = rs.getString("user_name");
                String password = rs.getString("password");
                String gender = rs.getString("gender");

                user = new User(id, user_name, password, gender);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
