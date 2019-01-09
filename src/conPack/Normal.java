package conPack;
/**
 *
 * @author Nah
 */
public class Normal extends User{
    
    public Normal(){
        newAccount("normal");
        Connectivity.register(getUsername() , getPassword() , getRole());
    }
}
