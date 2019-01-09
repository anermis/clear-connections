package conPack;

public class Editor extends User {
    
    public Editor(){        
        newAccount("editor");
        Connectivity.register(getUsername() , getPassword() , getRole());
    }
    
}
