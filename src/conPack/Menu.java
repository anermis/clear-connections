package conPack;

import java.util.Scanner;
/**
 *
 * @author Nah
 */
public class Menu extends User{
    
    public Menu(){
        login();
        
        menuInterface();

    } 
    public  void  login(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your username: ");  
        setUsername(sc.nextLine());
        System.out.print("Enter your password: ");
        setPassword(sc.nextLine());   
        setRole(Connectivity.loginUser(getUsername(), getPassword()));
    }
    
    public void menuInterface(){
         Scanner sc = new Scanner(System.in);
        //getting role
        if (getRole().equals("creator")){           
            System.out.println("Wellcome Creator User\n");
            System.out.print("Press 1 to create ,2  to view ,3  to delete and 4 to update users "
                    + " \n*"
                    + "\nPress  22  to view ,23  to delete and 24 to update all  messages from all users "
                    + " \n*"
                    + "Press 9 to send and check your messages :");
            int run = sc.nextInt();
            sc.nextLine();
            menuCreator(run);        
            
        }else if(getRole().equals("stalker")){
             System.out.println("Wellcome Stalker User");
             System.out.print("Press 1 to send , 2 to check sended and 3 received messages" 
                     + "\nPress  22  to view  all  messages from all users");
             int run = sc.nextInt();
             sc.nextLine();             
             menuStalker(run);
             
             
        }else if (getRole().equals("editor")){
            System.out.println("Wellcome Editor User");
            System.out.print("Press 1 to send , 2 to check sended and 3 received messages "
                     + "\nPress  21  to view and 22  to update all  messages from all users");
            int run = sc.nextInt();
             sc.nextLine();
            menuEditor(run);
            
        }else if (getRole().equals("super")){
             System.out.println("Wellcome Super User");      
             System.out.print("Press 1 to send , 2 to check sended and 3 received messages "
                     + "\nPress  22  to view ,23  to delete and 24 to update all  messages from all users");
             int run = sc.nextInt();
             sc.nextLine();
             menuSuper(run);
             
        }else if (getRole().equals("normal")){
             System.out.println("Wellcome  User");        
             System.out.print("Press 1 to send , 2 to check sended and 3 received messages ");
             int run = sc.nextInt();
             sc.nextLine();
             menuNormal(run);
             
        }else if(getRole().equals("wrong")){
            //Safety
            System.out.println("Wrong username or password please try again");
            System.exit(0);
        }
    }
   
    public void menuCreator(int run){
         Scanner sc = new Scanner(System.in);
        if(run == 1){
                 //Create
                System.out.print("Press 1 to create a Stalker, 2 for Editor , 3 for Super and 4 for Normal :");
                run = sc.nextInt();
                sc.nextLine();
                User aNew =new Creator(run);
                
            }else if(run == 2){                          
                Connectivity.viewUsers();
                //Veiw        
                
            }else if(run == 3){
                //Delete
                System.out.print("Enter user to delete :");
                String user = sc.nextLine();
                Connectivity.deleteUserRow(user);
                
            }else if(run == 4){
                //Update
                System.out.print("Enter user to update :");
                String user = sc.nextLine();
                System.out.print("Enter \"username\" , \"password\" or \"role\" to update :");
                String setting = sc.nextLine();
                System.out.print("Enter new info :");
                String change = sc.nextLine();
                Connectivity.updateUserRow(user, setting, change);     
          
            }else if (run == 22){      
                //View All Mesages
                Connectivity.viewMessagesSuper();
                
            }else if(run == 23){
                //Delete  Messages Row
                
                int wrongInput0 ;               
                do{
                    System.out.print("Enter the id of the message :");
                    int id =sc.nextInt();      
                    sc.nextLine();
                    wrongInput0 =Connectivity.deleteMessagesRow(id);
                    //wrongInput = 1 if delete succeds                  
                }while(wrongInput0==0);            
                
            }else if(run == 24){
                //Update Messages Row

                int wrongInput0;
                do{
                    System.out.print("Enter the id of the message :");
                int id =sc.nextInt(); 
                System.out.print("Enter the sender of the message :");
                String sender =sc.nextLine(); 
                System.out.print("Enter the receiver of the message :");
                String receiver =sc.nextLine(); 
                System.out.print("Enter the new message  :");
                String messageUpdate =sc.nextLine();                 
              wrongInput0 =Connectivity.updateMessagesRow(id, sender, receiver, messageUpdate);
                    
                }while(wrongInput0 == 0);
                
                
            }else if(run == 9){
                //Press 9 to send and check messages
                
                int wrongInput= 0; 
                 System.out.print("Press 1 to send , 2 to check sended and 3 received messages :");
                 
                 do{
                     run = sc.nextInt();
                     sc.nextLine();
                     if (run==1){
                         //Send
                         System.out.print("Sent to @");
                         String receiver = sc.nextLine();
                         System.out.print("Your message :");
                         String message = sc.nextLine();           
                         Connectivity.newMessage(getUsername() , receiver  , message);
                         
                     }else if(run == 2){          
                         //Check Sended
                         Connectivity.checkSendedMessages(getUsername());                    
                         
                     }else if (run == 3){
                         //Check Received
                         Connectivity.checkReceivedMessages(getUsername());
                         
                     }else{
                         //Safety
                         System.out.println("Please one of the following!");          
                         wrongInput=1;
                     }
                 }while(wrongInput==1);                                      
            }
    }
    
    public void menuSuper(int run){
        Scanner sc = new Scanner(System.in);
        if(run == 1){
            //Veiw                     
            Connectivity.viewMessagesSend(getUsername());                                         
            
        }else if (run == 2){                                  
                 //Delete  Messages
                 int wrongInput0 ;               
                do{
                    System.out.print("Enter the id of the message :");
                    int id =sc.nextInt();          
                    sc.nextLine();
                    wrongInput0 =Connectivity.deleteMessagesRow(id);
                    //wrongInput = 1 if delete succeds                  
                }while(wrongInput0==0);    
                 
            }else if(run == 3){
                //Update Messages
                int wrongInput0;                
                do{
                    System.out.print("Enter the id of the message :");
                    int id =sc.nextInt(); 
                    sc.nextLine();
                    System.out.print("Enter the sender of the message :");
                    String sender =sc.nextLine(); 
                    System.out.print("Enter the receiver of the message :");
                    String receiver =sc.nextLine(); 
                    System.out.print("Enter the new message  :");
                    String messageUpdate =sc.nextLine();                 
                    wrongInput0 =Connectivity.updateMessagesRow(id, sender, receiver, messageUpdate);                    
                }while(wrongInput0 == 0);      
                
            }else if(run == 9){
                //Press 9 to send and check messages                
                int wrongInput= 0; 
                 System.out.print("Press 1 to send , 2 to check sended and 3 received messages :");                 
                 do{
                     run = sc.nextInt();
                     sc.nextLine();
                     if (run==1){
                         //Send
                         System.out.print("Sent to @:");
                         String receiver = sc.nextLine();
                         System.out.print("Your message :");
                         String message = sc.nextLine();           
                         Connectivity.newMessage(getUsername() , receiver  , message);
                         
                     }else if(run == 2){          
                         //Check Sended
                         Connectivity.checkSendedMessages(getUsername());                    
                         
                     }else if (run == 3){
                         //Check Received
                         Connectivity.checkReceivedMessages(getUsername());
                         
                     }else{
                         //Safety
                         System.out.println("Please one of the following!");          
                         wrongInput=1;
                     }
                 }while(wrongInput==1);                                      
            }
   
    }
    
    public void menuEditor(int run){
        Scanner sc = new Scanner(System.in);
        if(run == 21){
            //Veiw                     
            Connectivity.viewMessagesSend(getUsername());                                         
            
        }else if (run == 22){                                   
                //Update Messages
                int wrongInput0;                
                do{
                    System.out.print("Enter the id of the message :");
                    int id =sc.nextInt(); 
                    sc.nextLine();
                    System.out.print("Enter the sender of the message :");
                    String sender =sc.nextLine(); 
                    System.out.print("Enter the receiver of the message :");
                    String receiver =sc.nextLine(); 
                    System.out.print("Enter the new message  :");
                    String messageUpdate =sc.nextLine();                 
                    wrongInput0 =Connectivity.updateMessagesRow(id, sender, receiver, messageUpdate);                    
                }while(wrongInput0 == 0);      
               

                
            }else if(run == 1){        
                  //Send
                int wrongInput= 0; 
                do{
                     run = sc.nextInt();
                     sc.nextLine();                                           
                     System.out.print("Sent to @:");
                     String receiver = sc.nextLine();
                     System.out.print("Your message :");
                     String message = sc.nextLine();           
                     Connectivity.newMessage(getUsername() , receiver  , message);                                   
                }while(wrongInput==1);                                      
            }else if(run == 2){
                 //Check Sended
                Connectivity.checkSendedMessages(getUsername());       
            }else if(run == 3){
                 //Check Received
                Connectivity.checkReceivedMessages(getUsername());  
            }   
    }
    
    public void menuStalker(int run){
        Scanner sc = new Scanner(System.in);               
        int wrongInput= 0; 
        System.out.print("Press 1 to send , 2 to check sended and 3 received messages"
                + "\nPress 21 to view all messages from all users :");        
        do{                   
            run = sc.nextInt();
            sc.nextLine();
            if (run==1){
                //Send
                System.out.print("Sent to @");
                String receiver = sc.nextLine();
                System.out.print("Your message :");
                String message = sc.nextLine();           
                Connectivity.newMessage(getUsername() , receiver  , message);                         
            }else if(run == 2){          
                //Check Sended
                Connectivity.checkSendedMessages(getUsername());                                    
            }else if (run == 3){
                //Check Received
                Connectivity.checkReceivedMessages(getUsername());    
            }else if(run == 21){
                //Veiw                     
                Connectivity.viewMessagesSend(getUsername());         
            }else{
                //Safety
                System.out.println("Please one of the following!");          
                wrongInput=1;
            }
        }while(wrongInput==1);                                      
    }
        
    
    public void menuNormal(int run){
        Scanner sc = new Scanner(System.in);               
        int wrongInput= 0; 
        System.out.print("Press 1 to send , 2 to check sended and 3 received messages :");        
        do{                   
            run = sc.nextInt();
            sc.nextLine();
            if (run==1){
                //Send
                System.out.print("Sent to @");
                String receiver = sc.nextLine();
                System.out.print("Your message :");
                String message = sc.nextLine();           
                Connectivity.newMessage(getUsername() , receiver  , message);                         
            }else if(run == 2){          
                //Check Sended
                Connectivity.checkSendedMessages(getUsername());                                    
            }else if (run == 3){
                //Check Received
                Connectivity.checkReceivedMessages(getUsername());                
            }else{
                //Safety
                System.out.println("Please one of the following!");          
                wrongInput=1;
            }
        }while(wrongInput==1);                                      
    }    
}
