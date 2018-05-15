package DatabaseLogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Reservation_Session_DAO extends BaseEntityDAO {

    //CRUD Statements

    //CREATE

    public int insertIntoReservation_Session(Reservation_Session reservation_session) throws SQLException {

        BaseEntity newBaseEntity = new BaseEntity(reservation_session.getEntityId(), reservation_session.getEntityVersion(), reservation_session.getActive(), reservation_session.getTimestamp());

        //execute baseEntity Insert
        int callbackInsertedInt = newBaseEntity.getEntityId();

        if (reservation_session.getEntityId()!=0 && callbackInsertedInt != reservation_session.getEntityId()) {
            throw new SQLException("ERROR 05: Given id(" + reservation_session.getEntityId() + ") does not correspond to retreived id(" + callbackInsertedInt + ")!");
        }

        //System.out.println("TEST: Given id(" + reservation_session.getEntityId() + ") retreived id(" + callbackInsertedInt + ")!");
        PreparedStatement preparedStatement = null;
        String sqlQuery = "";

        //sqlQuery = "INSERT INTO wordpress2.Reservation_Session (`reservationId`, `reservationUUID`, `sessionUUID`, `userUUID`) VALUES(\"" + callbackInsertedInt + "\",\"" + reservation_session.getReservationUUID() + "\",\"" + reservation_session.getSessionUUID() + "\",\"" + reservation_session.getUserUUID() + "\");";
        sqlQuery = "INSERT INTO wordpress2.Reservation_Session (`idReservationSession`, `reservationUUID`, `sessionUUID`, `userUUID`) VALUES(" + callbackInsertedInt + ",\"" + reservation_session.getReservationUUID() + "\",\"" + reservation_session.getSessionUUID() + "\",\"" + reservation_session.getUserUUID() + "\");";
        //INSERT INTO `wordpress2`.`Reservation_Session` (`idReservation`, `reservationUUID`, `sessionUUID`, `userUUID`) VALUES (NULL, NULL, NULL, NULL);

        //System.out.println("sqlQuery: "+sqlQuery);

        int insertSucces = BaseEntityDAO.runInsertQuery(sqlQuery);
        //System.out.println("insertSucces: "+insertSucces+", callbackInsertedInt: "+callbackInsertedInt);
        return callbackInsertedInt;

    }

    //READ

/*

    public ArrayList<Reservation_Session> getAllReservation_Sessions() {
        ResultSet rs = null;
        ArrayList<Reservation_Session> sessionReservationsList = null;

        String sql = "SELECT * FROM Reservation_Session;";

        try (Statement s = getConnection().createStatement()) {

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                sessionReservationsList.add(new Reservation_Session(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            return sessionReservationsList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    public ArrayList<Reservation_Session> getAllReservation_SessionsFull() {
        ResultSet rs = null;
        ArrayList<Reservation_Session> sessionReservationsList = null;

        String sql = "SELECT * FROM Reservation_Session JOIN BaseEntity ON Reservation_Session.reservationId = BaseEntity.entityId;";

        try (Statement s = getConnection().createStatement()) {

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                sessionReservationsList.add(new Reservation_Session(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            return sessionReservationsList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
*/

    //UPDATE


    //DELETE


    //OTHER


}
