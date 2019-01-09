package conPack;

public class Super extends User{    
    
    public Super(){
        newAccount("super");
        Connectivity.register(getUsername() , getPassword() , getRole());
    }
}
