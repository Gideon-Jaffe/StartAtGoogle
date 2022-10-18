package JavaBasic;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class User {
    String Name;
    String Id;
    boolean IsActivated;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public boolean isActivated() {
        return IsActivated;
    }

    public void setActivated(boolean activated) {
        IsActivated = activated;
    }

    public User(String name, String id, boolean isActivated) {
        Name = name;
        Id = id;
        IsActivated = isActivated;
    }

    @Override
    public String toString() {
        return this.Name + ", Id: " + this.Id + ", Is Activated: " + this.IsActivated;
    }

    public static HashMap<String, User> createRandomUserMap(int amount)
    {
        HashMap<String, User> UserMap = new HashMap<>();
        for (int i = 0; i < amount; i++) {
            User user = User.createRandomUser();
            UserMap.put(user.Id, user);
        }
        return UserMap;
    }

    public static User GetUserFromList(List<User> users, String id) {
        for (User current : users) {
            if (current.Id.equals(id)) {
                return current;
            }
        }
        return null;
    }

    public static int amountUsersActivated(List<User> users) {
        int amount = 0;
        for (User current : users) {
            if (current.IsActivated) {
                amount++;
            }
        }
        return amount;
    }

    public static User createRandomUser() {
        String name = ThreadLocalRandom.current().ints(97, 123)
                .limit(7)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        String id = UUID.randomUUID().toString();
        boolean activated = ThreadLocalRandom.current().nextBoolean();
        return new User(name, id, activated);
    }

    public static void test() {
        Map<String, User> usersMap = User.createRandomUserMap(10);
        User tempUser = User.createRandomUser();
        usersMap.put(tempUser.Id, tempUser);
        ArrayList<User> usersAsList = new ArrayList<>(usersMap.values());
        User gotUser = User.GetUserFromList(usersAsList, tempUser.Id);
        System.out.println(gotUser);

        int amountActivated = User.amountUsersActivated(usersAsList);
        System.out.println(amountActivated + " Users Activated");
    }
}
