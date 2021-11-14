import handlers.EntityHandler;
import handlers.JSONHandler;
import interfaces.IEntityHandler;
import interfaces.IPersistenceHandler;
import model.users.User;

public class App {
    public static void main(String[] args) throws Exception {
        IEntityHandler eh = new EntityHandler();
        IPersistenceHandler ph = new JSONHandler();
        ph.deserialize(eh, "db.json");
        
        User u = eh.findUserByName("pedru");
        u.playSong(2, 3);
        
        ph.serialize(eh, "db.json");
    }
}
