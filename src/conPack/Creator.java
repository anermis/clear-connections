package conPack;
/**
 *
 * @author Nah
 */
public class Creator extends User{
    
    public Creator(int run){
        
        if (run == 1){
            newUserStalker();
        }else if(run == 2){
            newUserEditor();
        }else if (run == 3){
            newUserSuper();
        }else if(run == 4){
            newUserNormal();
        }else{
            System.out.println("Not valid run command");            
        
        }
    }
    
    public void newUserStalker(){
        User Nah = new Stalker();
    }
    public void newUserEditor(){
        User Nah = new Editor();
    }
    public void newUserSuper(){
        User Nah = new Super();
    }
    public void newUserNormal(){
        User Nah = new Normal();
    }
    
}
