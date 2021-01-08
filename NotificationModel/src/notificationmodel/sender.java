/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notificationmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author HP
 */
public class sender implements dequeueing {

    public void send(int NotificationID) {
        Connection conn = null;
        Statement stmt = null;
        Random rn = new Random();
        int answer = rn.nextInt(2) + 0;
        Template temp = new Template();
        try {
            //STEP 2: Register JDBC driver
            boolean flag = false;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
            stmt = conn.createStatement();
            String query = "SELECT `statues` FROM `sendbyemail` WHERE NotificationID=" + NotificationID + "; ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            Scanner input = new Scanner(System.in);
            if (rs.next()) {

                
                    int statues = rs.getInt("statues");
                    
                    if (statues == 0) 
                    {
                        if(answer ==0){System.out.println("send faild");}
                        else{
                        String sql = "UPDATE `sendbyemail` SET `statues`='1' WHERE NotificationID= " + NotificationID + ";";
                        stmt.executeUpdate(sql);

                        System.out.println("notification is sent");
                        }
                    } else if (statues == 1 ) {
                        System.out.println("notification actually sent");
                    }
                    
                    flag = true;
                }


            if (flag == false) {
                String query2 = "SELECT `statues` FROM `sendbysms` WHERE NotificationID=" + NotificationID + "; ";
                Statement st2 = conn.createStatement();
                ResultSet rs2 = st.executeQuery(query2);
                Scanner input2 = new Scanner(System.in);
                if (rs2.next()) {

                    
                        int statues2 = rs2.getInt("statues");
                        if (statues2 == 0) {
                            if(answer ==0){System.out.println("send faild");}
                        else{
                            String sql2 = "UPDATE `sendbysms` SET `statues`='1' WHERE NotificationID= " + NotificationID + ";";
                            stmt.executeUpdate(sql2);

                            System.out.println("notification is sent");
                            }
                        } else if (statues2 == 1) {
                            System.out.println("notification actually sent");
                        }
                        flag = true;
                    
                }
                st.close();
            }
            if (flag == false) {
                System.out.println("Notification not found");
            }

        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null) {
                    conn.close();
                }
            } catch (SQLException se) {
            }// do nothing
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
    }
}
