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

            //loop over the resultset and create user
            while (resultSet.next()){
                User newUser = new User(resultSet.getString("nameUser"),
                        resultSet.getDate("birthday").toLocalDate(),
                        resultSet.getInt("creditCard"),
                        resultSet.getInt("userNum"));
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

            while (resultSet.next()){
                VideoGame newVideoGame = new VideoGame(
                        resultSet.getString("nameGame"),
                        resultSet.getInt("ageRating"),
                        resultSet.getInt("videoGamesNum")
                );

                String gameId = resultSet.getString("videoGamesNum");

                Statement usersStatement = conn.createStatement();
                ResultSet gameUsersSet = usersStatement.executeQuery("SELECT * FROM Illia200453638.usersA WHERE userNum in (SELECT UserId FROM Illia200453638.GameUsers WHERE GameId = " + gameId + ")");
                while (gameUsersSet.next()) {
                    User gameUser = new User(
                            gameUsersSet.getString("nameUser"),
                            gameUsersSet.getDate("birthday").toLocalDate(),
                            gameUsersSet.getInt("creditCard"),
                            gameUsersSet.getInt("userNum")
                    );
                    newVideoGame.addUser(gameUser);
                }

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

            //3. bind the values to the data
            statement.setString(1, newUser.getName());
            statement.setDate(2, Date.valueOf(newUser.getBirthday()));
            statement.setInt(3, newUser.getCreditCard());


            //4. execute the insert
            statement.executeUpdate();

            //5. get the user returned
            resultSet = statement.getGeneratedKeys();

            //6. update the user
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

    public static int insertGameIntoDB(VideoGame newVideoGame) throws SQLException {
        int gameNum = -1;

        Connection conn = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try{
            //1. connect to the DB
            conn = DriverManager.getConnection(connString,user,password);

            //2. create our sql statement
            statement = conn.prepareStatement("INSERT INTO videoGames (nameGame, ageRating) VALUES " +
                    "(?,?)", new String[]{"gameNum"});

            //3. bind the values to the data
            statement.setString(1, newVideoGame.getName());
            statement.setInt(2, newVideoGame.getAgeRating());

            //4. execute the insert
            statement.executeUpdate();

            //5. get the game returned
            resultSet = statement.getGeneratedKeys();

            //6. update the game
            while (resultSet.next())
                gameNum = resultSet.getInt(1);

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
            return gameNum;
        }
    }

    public static void UpdateGame(VideoGame game) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(connString,user,password);

            String query = "update videoGames set nameGame = ?, ageRating = ? where videoGamesNum = ?";
            PreparedStatement statement = conn.prepareStatement(query);

            statement.setString(1, game.getName());
            statement.setInt(2, game.getAgeRating());
            statement.setInt(3, game.getId());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //add user to game using id of game and id of user
    public static void AddUserToGame(int gameId, int userId){
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(connString,user,password);

            statement = conn.prepareStatement ("INSERT INTO GameUsers (GameId, UserID) VALUES (?, ?)");
            statement.setInt(1, gameId);
            statement.setInt(2, userId);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //delete user game using the id of game
    public static void ClearGameUser(int gameId){
        Connection conn = null;
        PreparedStatement statement = null;

        try {
            conn = DriverManager.getConnection(connString,user,password);

            statement = conn.prepareStatement ("DELETE FROM  GameUsers WHERE gameid = ?");
            statement.setInt(1, gameId);
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
