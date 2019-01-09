package conPack;

import java.util.Scanner;
 
public class User {
    private String username = "username";
    private String password = "password";
    private String role;
    
    public User(){
        
    }
    public void sendMessages(String sender , String receiver , String message){
        Connectivity.newMessage(sender, receiver, message);
    }
    
    public void checkMessages(){
        // have to ask query about new msg(may need to play with time here)
    }
    
    public void newAccount(String role){
        setRole(role);
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter " + role + "'s username: ");  
        setUsername(sc.nextLine());
        int i = 0;
        while(i==0){
            System.out.print("Creating new password: ");
            setPassword(sc.nextLine());      
            System.out.print("Securing password: ");
            String checkPass = sc.nextLine();
            if(checkPass.equals(getPassword())){
                i++;
                System.out.println("Password matched! ");
            }else{
                System.out.println("Password not matched! ");
            }
        } 
        sc.close();  
    }
    
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsername() {
        return username;
    }    
    public void setUsername(String username) {
        this.username = username;
    }
     public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }
    
}
