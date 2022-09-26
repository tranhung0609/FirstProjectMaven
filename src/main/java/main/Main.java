package main;

import dao.UserDAO;
import model.User;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        /* Test Insert */

		User user1 = new User(10, "Nguyen Van A", "123456");
		User user2 = new User(11, "Nguyen Van B", "123456");

		UserDAO.getInstance().insert(user2);

        /* Test update */
        User user = new User(1, "admin", "123456");
        UserDAO.getInstance().update(user);

        /* Test delete */
            User user3 = new User(4,"Hoa","12345");
            UserDAO.getInstance().delete(user3);
            UserDAO.getInstance().update(user);
    }
}
