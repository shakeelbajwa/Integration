package DatabaseLogic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.sql.Statement;


public class Reservation_Event_DAO extends BaseEntityDAO{

    //CRUD Statements

    //CREATE

    public int insertIntoReservation_Event(Reservation_Event reservation_event) throws SQLException {

        BaseEntity newBaseEntity = new BaseEntity(reservation_event.getEntityId(),reservation_event.getEntityVersion(),reservation_event.getActive(),reservation_event.getTimestamp());

        int callbackInsertedInt = newBaseEntity.getEntityId();

        if(callbackInsertedInt != reservation_event.getEntityId())
        {
            throw new SQLException("ERROR 05: Given id("+reservation_event.getEntityId()+") does not correspond to retreived id("+callbackInsertedInt+")!");
        }

        PreparedStatement preparedStatement = null;

        String sqlQuery ="";

        if(reservation_event.getEntityId()==0)
        {
            sqlQuery = "INSERT INTO wordpress2.Reservation_Event (`reservationUUID`, `eventUUID`, `userUUID`) VALUES(\""+reservation_event.getReservationUUID()+"\",\""+reservation_event.getEventUUID()+"\",\""+reservation_event.getUserUUID()+"\");";


        }else{

            sqlQuery = "INSERT INTO wordpress2.Reservation_Event (`idReservationEvent`, `reservationUUID`, `eventUUID`, `userUUID`) VALUES(\""+callbackInsertedInt+"\",\""+reservation_event.getReservationUUID()+"\",\""+reservation_event.getEventUUID()+"\",\""+reservation_event.getUserUUID()+"\");";

        }

        //System.out.println("sqlQuery= "+sqlQuery);

        int insertSucces = BaseEntityDAO.runInsertQuery(sqlQuery);
        //System.out.println("insertSucces: "+insertSucces+", callbackInsertedInt: "+callbackInsertedInt);


        return callbackInsertedInt;

    }

    //READ
/*

    public ArrayList <Reservation_Event> getAllReservation_Events()
    {
        ResultSet rs = null;
        ArrayList<Reservation_Event> eventReservationsList = null;

        String sql = "SELECT * FROM Reservation_Event;";

        try(Statement s = getConnection().createStatement()){

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                eventReservationsList.add(new Reservation_Event(rs.getInt(1),rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5)));
            }

            return eventReservationsList;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
    public ArrayList <Reservation_Event> getAllReservation_EventsFull()
    {
        ResultSet rs = null;
        ArrayList<Reservation_Event> eventReservationsList = null;

        String sql = "SELECT * FROM Reservation_Event JOIN BaseEntity ON Reservation_Event.reservationId = BaseEntity.entityId;";

        try(Statement s = getConnection().createStatement()){

            if (getConnection().isClosed()) {
                throw new IllegalStateException("ERROR 01: Connection seems to be closed...");
            }

            while (rs.next()) {
                eventReservationsList.add(new Reservation_Event(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8)));
            }

            return eventReservationsList;

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
