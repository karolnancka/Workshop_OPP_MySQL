import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDao {

    public static void main(String[] args) throws SQLException {
        menu();



    }

    private static void anyOtherAction() throws SQLException {
        System.out.println("Would you like to perform any other actions \n" +
                "Print: y - if yes,\n" +
                "Print: n - if not, \n");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        switch (input) {
            case "y":
                menu();
                break;
            case "n":
                System.out.println("bye bye");
                break;
        }
    }

    public static void menu() throws SQLException {
        System.out.println("What action would you like to perform? \n" +
                "Print: a - to add new user to database \n" +
                "Print: r - to remove user from database \n" +
                "Print: d - to display specific user \n" +
                "Print: l - to list all users \n" +
                "Print: u - to update user email, username or password");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        switch (input) {
            case "a":
                insertUser();
                break;
            case "r":
                removeUser();
                break;
            case "d":
                listUserWithId();
            case "l":
                listAll();
                break;
            case "u":
                changeUserData();
                break;
        }
        anyOtherAction();


    }
    public static String hashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }


    public static void insertUser() throws SQLException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Insert your credentials\n");

        System.out.println("Insert your email: ");
        String email = scan.nextLine();

        System.out.println("Insert your username: ");
        String username = scan.nextLine();

        System.out.println("Insert your password: ");
        String password = hashPassword(scan.nextLine());
        System.out.println(password);


        try (Connection connection = DbUtil.connect("workshop2")) {
            DbUtil.insertUser(connection, "users", email, username, password);
            System.out.println("User added");
        }

    }


    public static void removeUser() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInsert user ID to be removed: ");
        int id = scan.nextInt();

        try (Connection connection = DbUtil.connect("workshop2")) {
            DbUtil.remove(connection, "users", id);
            System.out.println("User with id: " + id + "removed");
        }

    }


    public static void listUserWithId() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.println("\nInsert user ID to be listed: ");
        int id = scan.nextInt();

        try (Connection connection = DbUtil.connect("workshop2")) {
            DbUtil.listUserWithId(connection, "users", id);
            System.out.println("User with id: " + id);
        }
    }

    public static void listAll() throws SQLException {
        try (Connection connection = DbUtil.connect("workshop2")) {
            DbUtil.listAll(connection, "users");
        }
    }

    public static void changeUserData() throws SQLException {
        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);

        System.out.println("Select user by printing it's ID: \n");
        int id = scan.nextInt();

        System.out.println("What user parameter you would like to change?\n" +
                "Print e for email\n" +
                "Print u for username\n" +
                "Print p for password\n" +
                "Print m to to go back to menu");


        String input = scan2.nextLine();

        try (Connection connection = DbUtil.connect("workshop2")) {
            if (input.equals("e")) {
                System.out.println("Insert new email:\n");
                DbUtil.updateUserData(connection, "email", id, scan2.nextLine());
            } else if (input.equals("u")) {
                System.out.println("Insert new username:\n");
                DbUtil.updateUserData(connection, "username", id, scan2.nextLine());

            } else if (input.equals("p")) {
                System.out.println("Insert new password:\n");
                DbUtil.updateUserData(connection, "password", id, hashPassword(scan2.nextLine()));

            } else if (input.equals("m")) {
                menu();
            }
        }


    }


}
