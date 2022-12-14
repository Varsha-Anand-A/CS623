package grp8;

import java.sql.*;   //Package imported to use JDBC

public class group8 {

    public static void main(String[] args) throws SQLException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");             //Loading the driver

        } catch (ClassNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/v_project", "root", "root123");   //Creating connection

        conn.setAutoCommit(false);   //for atomicIty 

        conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);   //isolation
 
        Statement stmt1 = null;

        try {
	    //Create statement object

            stmt1 = conn.createStatement();


            stmt1.executeUpdate("DELETE FROM `v_project`.`depot` WHERE (`dep_id` = 'd1')"); //If we try to  delete d1 then it would throw error so deleted using ON_DELET_CASCADE  so that it deletes first foreignkey
											    // than primary key

            // stmt1.executeUpdate("DELETE FROM `v_project`.`stock` WHERE (`dep_id` = 'd1')");

        } catch (SQLException e) {

            System.out.println("An exception was thrown");

            e.printStackTrace();
            //Atomicity

            conn.rollback();

            stmt1.close();
			
            conn.close(); 

            return;

        }

        conn.commit(); 

        stmt1.close();

        conn.close();  //connection closed

        System.out.println("Object deleted!");
    }
}