package authorization;

import java.util.Map;

public class AuthorizationManager {
    public static Map<String, Integer> users_mapping = UserPasswordMapping.getInstance();

    public static boolean userExists(String username){
        return users_mapping.containsKey(username);
    }

    public static void createUser(String username, String password) {
        users_mapping.put(username, password.hashCode());
    }

    public static boolean verifyPassword(String username, String password) {
        Integer desired = password.hashCode();
        Integer expected = users_mapping.get(username);
        return expected.equals(desired);
    }

}
