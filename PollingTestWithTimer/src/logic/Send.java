package logic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.*;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static logic.MysqlCon.hi;





public class Send{

    static class SayHello extends TimerTask {
        public void run() {
            try {
                sendDbToReceiver();
            } catch (Exception b) {
                System.out.println(b);
            }
        }
    }

    public static void main(String[] argv) throws Exception {

        Timer timer = new Timer();
        timer.schedule(new SayHello(), 1000, 10000);

    }

    private final static String QUEUE_NAME = "hello";

    public static void sendDbToReceiver () throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //String message = "Hello World!";


        //String sql = "INSERT INTO Reservations (name)" + "VALUES ('" + message + "')";
        //PreparedStatement preparedStatement = conn.prepareStatement(sql);
        //preparedStatement.



        try {

            Class.forName("com.mysql.jdbc.Driver");
            java.sql.Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false", "root", "");

            Statement stmt = con.createStatement();
            String selectPoll = "SELECT idUser, userUUID, lastName, firstname, phonenumber, email, houseNr, postalCode, country, company, type FROM wordpress2.User WHERE polled_time is null LIMIT 1";
            //stmt.executeQuery(selectPoll);
            ResultSet rs = stmt.executeQuery(selectPoll);

            /*
            String lastName = "SELECT lastName FROM wordpress2.User";
            ResultSet ln = stmt.executeQuery(lastName);
            String firstName = "SELECT firstname FROM wordpress2.User";
            ResultSet fn = stmt.executeQuery(firstName);
            */



            while (rs.next()) {
                String message = rs.getString(1);
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes("UTF-8"));
                System.out.println(" [x] Sent '" + message + "'");
            }

            String updatePoll = "UPDATE wordpress2.User SET polled_time = now() WHERE polled_time IS NULL LIMIT 1";
            stmt.executeUpdate(updatePoll);

            con.close();
            } catch (Exception e) {
                 System.out.println(e);
        }

        channel.close();
        connection.close();


    }

}













