package notificationmodel;

import java.sql.*;

public class dbRepository implements Repository {

    

    public dbRepository() {

       
    }

    @Override
    public boolean createNotificationTemplate(Template template) {
        template.setTemplateId();
        template.setSubject();
        template.setContent();
        template.setLanguage();
        template.setChannel();

        int ID = template.getTemplateId();
        String content = template.getContent();
        String subject = template.getSubject();
        Language language = template.getLanguage();
        Channel channel = template.getChannel();
      
       
        
    Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
      stmt = conn.createStatement();
      
      String sql = "INSERT INTO `notificationtemplate`(`notificationTemplateID`, `subject`, `content`, `type`, `language`) VALUES ('"+ID +"'  , '"+subject +"'  , '"+content +"', '"+channel +"','"+language +"' );";
      stmt.executeUpdate(sql);
      return true;
      
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
        
         
         
return false;
    }

    @Override
    public Template readNotificationTemplate(int templateId) {
       
             try {
            
            
            Class.forName("com.mysql.jdbc.Driver");
            

        } catch (ClassNotFoundException ex) {
            System.out.println("mysql not found");
        }
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
            String query = "SELECT `notificationTemplateID`, `subject`, `content`, `type`, `language` FROM `notificationtemplate` WHERE notificationTemplateID="+templateId+"; ";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);

            while (rs.next()) {
                int NID = rs.getInt("notificationTemplateID");
                String sub = rs.getString("subject");
                String cont = rs.getString("content");
                String typ = rs.getString("type");
                 String lang = rs.getString("language");

                System.out.format("%s , %s , %s ,%s ,%s \n ", NID, sub, cont, typ , lang);
            }

            st.close();
        } catch (SQLException ex) {
            System.out.println("not connected");
        } finally {
            try {
                if (conn != null) {
                   conn.close();
               }
           } catch (SQLException e) {
              e.printStackTrace();
            }
        }
   return null;
    }

    @Override
    public boolean updateNotificationTemplate(int templateId) {
        
        Template template =new Template();
        
         template.setTemplateId();
        template.setSubject();
        template.setContent();
        template.setLanguage();
        template.setChannel();

        int ID = template.getTemplateId();
        String content = template.getContent();
        String subject = template.getSubject();
        Language language = template.getLanguage();
        Channel channel = template.getChannel();
    Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
      stmt = conn.createStatement();
      
      String sql = "UPDATE `notificationtemplate` SET `notificationTemplateID`='"+ID+"', `subject`='"+subject+"' ,`content`='"+content+"',`type`='"+channel+"' ,`language`='"+language+"' WHERE notificationTemplateID= "+templateId+";";
      stmt.executeUpdate(sql);
      
      System.out.println("notification updated");
      return true;
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
        return false;
    }

    @Override
    public boolean deleteNotificationTemplate(int templateId) {
        Connection conn = null;
   Statement stmt = null;
   try{
      //STEP 2: Register JDBC driver
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/notificationservice", "root", "");
      stmt = conn.createStatement();
      
      String sql = "DELETE FROM `notificationtemplate` WHERE notificationTemplateID= "+templateId+";";
      stmt.executeUpdate(sql);
      
      System.out.println("notification deleted");
      return true;
      
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
        return false;
    }

    @Override
    public int getNotificationTemplateId(Template template) {
        // TODO Auto-generated method stub
        return 0;
    }

}
