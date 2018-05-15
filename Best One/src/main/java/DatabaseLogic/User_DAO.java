
package DatabaseLogic;

import AppLogic.Helper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class User_DAO extends BaseEntityDAO {

    //CRUD Statements

    //CREATE

    public int insertIntoUser(User user) throws SQLException {

        BaseEntity newBaseEntity = new BaseEntity(user.getEntityId(), user.getEntityVersion(), user.getActive(), user.getTimestamp());

        //execute baseEntity Insert
        int callbackInsertedInt = newBaseEntity.getEntityId();

        if (user.getEntityId()!=0 && callbackInsertedInt != user.getEntityId()) {
            throw new SQLException("ERROR 05: Given id(" + user.getEntityId() + ") does not correspond to retreived id(" + callbackInsertedInt + ")!");
        }

        //System.out.println("TEST: Given id(" + user.getEntityId() + ") retreived id(" + callbackInsertedInt + ")!");
        PreparedStatement preparedStatement = null;
        String sqlQuery = "";

        sqlQuery = "INSERT INTO wordpress2.User (`idUser`, `userUUID`, `lastName`, `firstName`, `phonenumber`, `email`, `street`,`houseNr`,`city`,`postalCode`, `country`, `company`, `type`) VALUES (" + callbackInsertedInt + ",\"" + user.getUuid() + "\",\"" + user.getLastname() + "\",\"" + user.getFirstname() + "\",\"" + user.getPhoneNumber() + "\",\"" + user.getEmail() + "\",\"" + user.getStreet() + "\",\"" + user.getHouseNr() + "\",\"" + user.getCity() + "\",\"" + user.getPostalCode() + "\",\"" + user.getCountry() + "\",\""+user.getCompany() + "\",\""+user.getUserType() + "\");";
        //sqlQuery = "INSERT INTO wordpress2.user (`idUser`, `userUUID`, `lastName`, `firstName`, `phonenumber`, `email`, `street`,`houseNr`,`city`,`postalCode`, `country`, `company`, `type`) VALUES (" + callbackInsertedInt + ",\"" + user.getUserUUID() + "\",\"" + user.getLastname() + "\",\"" + user.getFirstname() + "\",\"" + user.getPhonenumber() + "\",\"" + user.getEmail() + "\",\"" + user.getStreet() + "\",\"" + user.getHouseNr() + "\",\"" + user.getCity() + "\",\"" + user.getPostalCode() + "\",\"" + user.getCountry() + "\",\""+user.getCompany() + "\",\""+user.getType() + "\");";

        //INSERT INTO `wordpress2`.`Session` (`idSession`, `sessionUUID`, `eventUUID`, `sessionName`, `maxAttendees`, `dateTimeStart`, `dateTimeEND`, `speaker`, `local`, `type`) VALUES (NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
        //System.out.println("sqlQuery: "+sqlQuery);

        int insertSucces = BaseEntityDAO.runInsertQuery(sqlQuery);
        //System.out.println("insertSucces: "+insertSucces+", callbackInsertedInt: "+callbackInsertedInt);
        return callbackInsertedInt;

    }

    //READ

    public BaseEntity getUserByUserId(int thisUserId)
    {
        ResultSet rs = null;
        User thisUser = null;

        String sql = "SELECT * FROM User t1 JOIN BaseEntity t2 ON t1.idUser = t2.entityId WHERE idUser = \""+thisUserId+"\";";

        try(Statement s = getConnection().createStatement()){

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            rs = s.executeQuery(sql);
            if(rs.next())
            {
                // user constructor: public User(int idUser, int Entity_version, int active, String Timestamp, String userUUID, String lastname, String firstname, String phoneNumber, String email, String street, int houseNr, String city, int postalCode, String country, String company, Helper.EntityType type) {

                // for our enum: tweaked from https://stackoverflow.com/questions/3155967/are-enums-supported-by-jdbc
                // Helper.EntityType.valueOf(rs.getString("type")

                thisUser = new User(rs.getInt(1),rs.getInt(15),rs.getInt(16), rs.getString(17),rs.getString(2),rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6), rs.getString(7),rs.getString(8),rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString("userType"));

                return thisUser;
            }else{
                throw new IllegalStateException("ERROR 03: No entity found with id: '"+thisUserId+"'...");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
/*

    public ArrayList<User> getAllUsers() {
        ResultSet rs = null;
        ArrayList<User> userList = null;

        String sql = "SELECT * FROM wordpress2.Reservation_Session;";

        try (Statement s = getConnection().createStatement()) {

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                userList.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            return sessionReservationsList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
*/
/*

    public ArrayList<Reservation_Session> getAllReservation_SessionsFull() {
        ResultSet rs = null;
        ArrayList<Reservation_Session> sessionReservationsList = null;

        String sql = "SELECT * FROM wordpress2.Reservation_Session JOIN wordpress2.BaseEntity ON Reservation_Session.reservationId = BaseEntity.entityId;";

        try (Statement s = getConnection().createStatement()) {

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                sessionReservationsList.add(new Reservation_Session(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            return sessionReservationsList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
*/

    //UPDATE

    public int UpdateUser(User newUserFromMessage, int oldEntityId) throws SQLException {

        //Maak een nieuwe BaseEntity met incremented entityVersion
        BaseEntity newBaseEntity = new BaseEntity(newUserFromMessage.getEntityId(), newUserFromMessage.getEntityVersion(), newUserFromMessage.getActive(), newUserFromMessage.getTimestamp());
        //execute baseEntity Insert
        int callbackInsertedInt = newBaseEntity.getEntityId();

        String sqlQuery = "INSERT INTO wordpress2.User (idUser, userUUID, lastName, 'firstname', phonenumber, email, street, houseNr,`city`,`postalCode`,`country`,`company`, type) VALUES (" + callbackInsertedInt + ",\"" + newUserFromMessage.getUuid() + "\",\"" + newUserFromMessage.getLastName() + "\",\"" + newUserFromMessage.getFirstName() + "\",\"" + newUserFromMessage.getPhoneNumber() + "\",\"" + newUserFromMessage.getEmail() + "\",\"" + newUserFromMessage.getStreet() + "\",\"" + newUserFromMessage.getHouseNr() + "\",\"" + newUserFromMessage.getCity() + "\",\"" + newUserFromMessage.getPostalCode() + "\",\"" + newUserFromMessage.getCountry() + "\",\"" + newUserFromMessage.getCompany() + "\",\"" + newUserFromMessage.getUserType() + "\");";

        //softdelete oude base entity
        softDeleteBaseEntity("User", oldEntityId);
        int insertSucces = BaseEntityDAO.runInsertQuery(sqlQuery);

        return callbackInsertedInt;
    }

    //DELETE


    //OTHER


}
