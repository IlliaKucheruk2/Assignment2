package utilities;

import models.User;
import models.VideoGame;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DBUtilities {
    private static String user = "Illia200453638";
    private static String password = "9JGPrwrjLp";
    private static String connString = "jdbc:mysql://172.31.22.43:3306/Illia200453638";

    public static ArrayList<User> getUserFromDB() throws SQLException {
        ArrayList<User> users = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{

            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM usersA");

            //loop over the resultset and create Student objects
            while (resultSet.next()){
                User newUser = new User(resultSet.getString("nameUser"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getInt("creditCard"));
                users.add(newUser);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return users;
    }

    public static ArrayList<VideoGame> getVideoGameFromDB() throws SQLException{
        ArrayList<VideoGame> games = new ArrayList<>();

        //connect to the DB
        Connection conn = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
            //connect to the Database
            conn = DriverManager.getConnection(connString, user, password);

            statement = conn.createStatement();

            //run the query on the DB
            resultSet = statement.executeQuery("SELECT * FROM videoGames");

            //loop over the resultset and create Professor objects
            while (resultSet.next()){
                VideoGame newVideoGame = new VideoGame(resultSet.getString("nameGame"),
                        resultSet.getInt("ageRating"));
                games.add(newVideoGame);
            }
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            if (conn != null)
                conn.close();
            if (statement != null)
                statement.close();
            if (resultSet != null)
                resultSet.close();
        }
        return games;
    }

}
