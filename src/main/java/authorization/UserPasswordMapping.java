package authorization;
import java.util.HashMap;
import java.util.Map;


public class UserPasswordMapping {
    private UserPasswordMapping(){}
    private static Map<String, Integer> instance = null;

    public static Map<String, Integer> getInstance(){
        if(instance == null){
            instance = new HashMap<>();
        }
        return instance;
    }
}
