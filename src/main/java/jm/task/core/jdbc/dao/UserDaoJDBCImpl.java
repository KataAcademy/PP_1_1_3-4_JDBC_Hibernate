package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

   Connection connection = Util.getConnection();

    public void createUsersTable() {


       try (Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS " +
                    " (" +

                    "  id INT NOT NULL AUTO_INCREMENT," +

                    "  name VARCHAR(50)," +

                    "  lastName VARCHAR(50)," +

                    "  age INT," +

                    "  PRIMARY KEY (id));");


       }catch (SQLException e){
           e.printStackTrace();
       }

        System.out.println("Table create successful by JDBC");
    }

    public void dropUsersTable() {
       try (Statement statement = connection.createStatement()){

        statement.executeUpdate("DROP TABLE IF EXISTS " );

    } catch (SQLException e) {
       throw new RuntimeException ("Error dropping users",e);
       }

    }

    public void saveUser(String name, String lastName, byte age) {
        String save = " INSERT INTO users (name, lastName, age) values (?,?,?)";
      try(  PreparedStatement preparedStatement = connection.prepareStatement(save)){
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3,age);
            preparedStatement.executeUpdate();

      }catch (SQLException e){
          throw new RuntimeException("Error save user", e);
      }




    }

    public void removeUserById(long id) {
        String removeID = " DELETE  FROM  where id=? ";

        try ( PreparedStatement preparedStatement = connection.prepareStatement(removeID )){
            preparedStatement.setLong(1,  id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error removing users", e);
        }

    }

    public List<User> getAllUsers() {
        User user = new User();
        List <User> userList = new ArrayList<>();
        String getAll = "SELECT * FROM ";


       try ( Statement statement= connection.createStatement();
        ResultSet resultSet = statement.executeQuery(getAll)){
           while (resultSet.next()) {
               user.setName(resultSet.getString("name"));
               user.setLastName(resultSet.getString("lastName"));
               user.setAge(resultSet.getByte("age"));
               userList.add(user);
           }



       }catch ( SQLException e){
           throw  new RuntimeException("Error getting users",e);
       }




        return  userList;
    }

    public void cleanUsersTable() {
      //  String cleanTable = "DELETE FROM users";
       try ( Statement statement = connection.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE ");
        } catch (SQLException e){
           throw  new RuntimeException("Error cleaning table", e);
       }

    }
}
