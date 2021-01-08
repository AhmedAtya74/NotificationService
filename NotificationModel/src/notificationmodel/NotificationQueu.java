package notificationmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;



public class NotificationQueu
{
    public NotificationQueu() {
        
    }
    
	
	public void perpareToSend(int id)
	{
            Connection conn = null;
            Statement stmt = null;
            
            Template temp = new Template();
   try{
      //STEP 2: Register JDBC driver
       
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
      stmt = conn.createStatement();
      
     String query = "SELECT `notificationTemplateID`, `subject`, `content`, `type`, `language` FROM `notificationtemplate` WHERE notificationTemplateID="+id+"; ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
Scanner input = new Scanner(System.in);
            while (rs.next()) {
                int NID = rs.getInt("notificationTemplateID");
                String sub = rs.getString("subject");
                String cont = rs.getString("content");
                
                Channel typ =Channel.valueOf( rs.getString("type"));
                Language lang =Language.valueOf( rs.getString("language"));

                char[] inp = cont.toCharArray();

                
                String newString="";
		for(int i = 0 ; i < inp.length ; i++)
		{
			if(inp[i] != '*')
			{
				newString+=inp[i];
			}
			else
			{
				String inserted = input.next();
				newString+=inserted;
			}
		}
                
                 if(typ.equals(Channel.EMAIL))
                 {
                     String sql = "INSERT INTO `sendbyemail`(`NotificationID`, `subject`, `content`, `channel`, `language` , `statues`) VALUES ('"+NID +"'  , '"+sub+"'  , '"+newString+"', '"+typ +"','"+lang +"'  , '0');";
                        stmt.executeUpdate(sql);
                        
                        System.out.println("notification is send by email");
                 }
                 else if (typ.equals(Channel.SMS))
                 {
                     String sql = "INSERT INTO `sendbysms`(`notificationID`, `subject`, `content`, `channel`, `language`, `statues`) VALUES ('"+NID +"'  , '"+sub+"'  , '"+newString+"', '"+typ +"','"+lang +"' , '0');";
                        stmt.executeUpdate(sql);
                        System.out.println("notification is send by sms"); 
                 }
                 else
                     System.out.println("notification is not send"); 
                 
                 

            }
            st.close();
            
            
      
   }catch(SQLException se){
      //Handle errors for JDBC
      se.printStackTrace();
   }catch(Exception e){
      //Handle errors for Class.forName
      e.printStackTrace();
   }finally{
      //finally block used to close resources
      try{
         if(stmt!=null)
            conn.close();
      }catch(SQLException se){
      }// do nothing
      try{
         if(conn!=null)
            conn.close();
      }catch(SQLException se){
         se.printStackTrace();
      }//end finally try
   }//end try
	}

}
