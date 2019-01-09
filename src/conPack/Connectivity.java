package conPack;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Connectivity  {  
    
    private static final String DB_URL = "localhost:3306";
    private static final String FULL_DB_URL = "jdbc:mysql://" + DB_URL + 
        "/mails?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String DB_USER = "root";
    private static final String DB_PASSWD = "2979aggelos";    
    private static Connection connection = null;

public static void updateUserRow(String user , String setting , String change ){
    Connectivity.connect();    
    PreparedStatement pst = null;        
    try {
        pst = connection.prepareStatement("UPDATE users SET " + setting + "  = ? WHERE username = ?");   
        pst.setString(1,change);        
        pst.setString(2,user);
        pst.executeUpdate();        
        System.out.println("updating...");
    } catch (SQLException ex) {
        Logger.getLogger(Connectivity.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        disconnect();
    }
}

public static int updateMessagesRow(int id , String sender , String receiver ,String messageUpdate ){
    Connectivity.connect();    
    PreparedStatement pst = null;    
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();    
    try {
        pst = connection.prepareStatement("UPDATE messages SET message  = ? WHERE message_id = ?");   
        pst.setString(1,dateFormat.format(date) + " " + sender + "@" + receiver +  " :" +messageUpdate);        
        pst.setInt(2,id);
        pst.executeUpdate();        
        System.out.println("updating...");
        return 1;
    } catch (SQLException ex) {
        System.out.println();
        return 0;
    }finally{
        disconnect();
    }
}

public static void deleteUserRow(String user){
    Connectivity.connect();    
    PreparedStatement pst = null;     
    try {
        pst = connection.prepareStatement("DELETE FROM users WHERE username = ?");   
        pst.setString(1,user);
        pst.executeUpdate();        
        System.out.println("deleting...");
    } catch (SQLException ex) {
        Logger.getLogger(Connectivity.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        disconnect();
    }
}

public static int deleteMessagesRow(int id){
    Connectivity.connect();    
    PreparedStatement pst = null;     
    try {
        pst = connection.prepareStatement("DELETE FROM messages  WHERE messages_id = ?");   
        pst.setInt(1,id);
        pst.executeUpdate();        
        System.out.println("deleting...");
        return 0;
    } catch (SQLException ex) {
       System.out.println("id is not vailid");
       return 1;
    }finally{
        disconnect();
    }
}

public static void viewUsers(){
    Connectivity.connect();    
    Statement statement = null;
    ResultSet rst = null;
    String sql = "select * from users ";
     try {       
         statement =connection.createStatement();
         rst = statement.executeQuery(sql);
         System.out.print("Username");    
         System.out.print(" " + "Password"); 
         System.out.println(" " + "Role");  
         while (rst.next()) {
             System.out.print(rst.getString(1));                                    
             System.out.print(" " + rst.getString(2));                                     
             System.out.println(" " + rst.getString(3));   
         }                          
     } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Clu)");
     }finally{
         Connectivity.disconnect();
    }               
}

public static void viewMessagesSuper(){
    Connectivity.connect();    
    Statement statement = null;
    ResultSet rst = null;
    String sql = "select message from messages ";
     try {       
         statement =connection.createStatement();
         rst = statement.executeQuery(sql);        
         while (rst.next()) {
             System.out.println(rst.getString(1));                                
         }                          
     } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (CvmS)");
     }finally{
         Connectivity.disconnect();
    }
     
}

public static void viewMessagesSend(String Sender){
    Connectivity.connect();    
    Statement statement = null;
    PreparedStatement pst = null;  
    ResultSet rst = null;
    String sql = "select message from messages  where sender = ?";
     try {       
         statement =connection.createStatement();
         pst = connection.prepareCall(sql);
        pst.setString(1,Sender);
         rst = statement.executeQuery(sql);        
         while (rst.next()) {
             System.out.println(rst.getString(1));                                
         }                          
     } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Cvm)");
     }finally{
         Connectivity.disconnect();
    }
     
}

public static void checkReceivedMessages(String username){
    Connectivity.connect();       
    String sql = "SELECT messages_id ,  message FROM messages WHERE receiver = ? ;" ; 
    PreparedStatement pst = null;  
    ResultSet rst = null;
    try {
        pst = connection.prepareCall(sql);
        pst.setString(1,username);
        rst = pst.executeQuery();
        while (rst.next()) {
            System.out.print("ID :" + rst.getString(1) + "  ");
            System.out.println(rst.getString(2));
        }
    } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Ccm)");
    }finally{
        Connectivity.disconnect();           
    } 
}


public static void updateReceiveMessage(int id ,  String sender , String receiver ,String messageUpdate){
    Connectivity.connect();    
    PreparedStatement pst = null;     
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
    try {
        pst = connection.prepareStatement("UPDATE messages SET message  = ? WHERE messages_id = ?;");   
        pst.setString(1,dateFormat.format(date) + " " + sender + "@" + receiver +  " :" +messageUpdate);        
        pst.setInt(2,id);
        pst.executeUpdate();        
        System.out.println("updating...");
    } catch (SQLException ex) {
        Logger.getLogger(Connectivity.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        disconnect();
    }
    
}

public static void deleteReceiveMessage(int id ){
    Connectivity.connect();    
    PreparedStatement pst = null;     
    try {
        pst = connection.prepareStatement("DELETE FROM messages WHERE messages_id = ?");   
        pst.setInt(1,id);
        pst.executeUpdate();        
        System.out.println("deleting...");
    } catch (SQLException ex) {
        Logger.getLogger(Connectivity.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        disconnect();
    }
}


public static void checkSendedMessages(String username){
    Connectivity.connect();       
    String sql = "SELECT  message FROM messages WHERE sender = ? ;" ; 
    PreparedStatement pst = null;  
    ResultSet rst = null;
    try {
        pst = connection.prepareCall(sql);
        pst.setString(1,username);
        rst = pst.executeQuery();
        if(!rst.next()){
            System.out.println("Not messages yet! ");
        }
        while (rst.next()) {
            System.out.println(rst.getString(1));
        }
    } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Ccm)");
    }finally{
        Connectivity.disconnect();           
    } 
}

public static void newMessage(String sender , String receiver ,String  message){   
    PreparedStatement pst = null;
    Statement statement = null;
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    Date date = new Date();
   
    try {
        Connectivity.connect();                
        statement = connection.createStatement();
        String sql = "insert into messages (sender , receiver , message) values (?,?,?)";
        pst = connection.prepareStatement(sql);   
        pst.setString(1,sender);
        pst.setString(2, receiver);
        pst.setString(3,dateFormat.format(date) + " " + sender + "@" + receiver +  " :" +  message);             
        pst.executeUpdate();      
        System.out.println("messege send");
    } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Cnm)");
    }finally{
        Connectivity.disconnect();           
    } 
}

public static void newMessageFile (String sender , String receiver ,String  message){
    Statement statement = null;
    try {
        Connectivity.connect();                
        statement = connection.createStatement();
        String sql = "insert into files(sender , receiver , file) values (?,?,?)";
        PreparedStatement pst = connection.prepareStatement(sql);                  
        ByteArrayInputStream stream = new ByteArrayInputStream(message.getBytes("UTF-8"));
        pst.setString(1,sender);
        pst.setString(2, receiver);
        pst.setBinaryStream(3,stream);             
        pst.executeUpdate();                  
    } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Cup)");
    } catch (UnsupportedEncodingException ex) {
        Logger.getLogger(Connectivity.class.getName()).log(Level.SEVERE, null, ex);
    }finally{
        Connectivity.disconnect();           
    }
}

public static String  loginUser(String username , String password){
     Connectivity.connect();
     String output = "wrong";
     Statement statement = null;
     ResultSet rst = null;
     String sql = "select role from users where username  = \"" + username + "\" and password =\"" + password + "\"";
     try {       
         statement =connection.createStatement();
         rst = statement.executeQuery(sql);
         while (rst.next()) {
             output = rst.getString(1);    
         
         }
     } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Clu)");
     }finally{
         Connectivity.disconnect();
    }
    return output;    
}

public static void register(String username , String password ,String role){
        Statement statement = null;
        try {
            Connectivity.connect();            
            statement = connection.createStatement();
            String sql = "insert into users(username , password , role) values (?,?,?)";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,username);
            pst.setString(2, password);
            pst.setString(3,role);
            pst.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println("Sorry, problems with the database connection! (Creg)");
//             System.out.println("This name already in use please try again!");
             System.out.println("");
//            System.out.println(ex.toString());
            
        }finally{
            Connectivity.disconnect();
           
    
}
    }

public static void connect() {
    try{
        System.out.println("Starting new connection!");
        connection = DriverManager.getConnection(FULL_DB_URL, DB_USER, DB_PASSWD);
        System.out.println("Connection succeeded!");
    
}catch(SQLException ex){
    System.out.println("Sorry, problems with the database connection!(Cc)");
    System.out.println(ex.toString());
    System.exit(0);
        }
}

public static void disconnect() {
    try {
        connection.close();
        System.out.println("Connection closed!");
    } catch (SQLException ex) {
        System.out.println("Sorry, problems with the database connection! (Cdis)");
       
    }
                
}            
    
}
