import java.util.HashMap;
import java.util.Map;
import java.util.Random;

class UserManager {
    private final Map<Integer, User> users = new HashMap<>();

    public int createAccount(String name, int age) {
        Random random = new Random();
        int randomId = 1000 + random.nextInt(9000);  // Generates a random 4-digit number between 1000 and 9999
        User user = new User(name, age, randomId);
        users.put(user.getId(), user);
        return user.getId();
    }

    public void modifyAccount(String id, String name, int age){
        User user = users.get(id);
        if (user != null) {
            user.setName(name);
            user.setAge(age);
            System.out.println("Account modified successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void deleteAccount(int id) {
        if (users.remove(id) != null) {
            System.out.println("Account deleted successfully.");
        } else {
            System.out.println("User not found.");
        }
    }

    public void displayAllAccounts() {
        for (User user : users.values()) {
            System.out.println(user);
        }
    }

    public boolean userExists(String id) {
        return users.containsKey(id);
    }

}
