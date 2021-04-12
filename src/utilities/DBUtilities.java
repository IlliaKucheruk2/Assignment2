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

    public static int insertUserIntoDB(User newUser) throws SQLException {
        int userNum = -1;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the DB
            conn = DriverManager.getConnection(connString,user,password);

            //2. create our sql statement
            statement = conn.prepareStatement("INSERT INTO usersA (nameUser, birthday, creditCard) VALUES " +
                    "(?,?,?)", new String[]{"userNum"});

            //3. bind the values to the datatypes
            statement.setString(1, newUser.getName());
            statement.setDate(2, Date.valueOf(newUser.getBirthday()));
            statement.setInt(3, newUser.getCreditCard());


            //4. execute the insert
            statement.executeUpdate();

            //5. get the student number returned
            resultSet = statement.getGeneratedKeys();

            //6. update the student number variable
            while (resultSet.next())
                userNum = resultSet.getInt(1);

        }catch (Exception e)
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
            return userNum;
        }
    }

}
