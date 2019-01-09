package conPack;

public class Stalker extends User {
    
    public Stalker(){
        newAccount("stalker");
        Connectivity.register(getUsername() , getPassword() , getRole());
    }
    
     
}
