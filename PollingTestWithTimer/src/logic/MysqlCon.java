package logic;

import java.sql.*;

class MysqlCon{

    public static void main(String args[]) throws Exception {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://dt5.ehb.be/1718OOAJAVA082","1718OOAJAVA082","53794618");
//here sonoo is database name, root is username and password


            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from Reservations");
            while(rs.next())
                System.out.println(rs.getString(1));
                //hi();
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

    public static String hi ()
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://dt5.ehb.be/1718OOAJAVA082","1718OOAJAVA082","53794618");
//here sonoo is database name, root is username and password


            Statement stmt=con.createStatement();

            ResultSet rs=stmt.executeQuery("select name from Reservations order by id desc limit 1");

            while(rs.next())
            {

                //System.out.println(rs.getString(1));
                return rs.getString(1);

            }
           // con.close();
        }catch(Exception e){ System.out.println(e);}
        return null;
    }
    }




