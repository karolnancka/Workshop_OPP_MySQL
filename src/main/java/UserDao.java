import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class UserDao {

    public static void main(String[] args) throws SQLException {


        //insertUser();
        //removeUser();
        //listUserWithId();
        //listAll();
        changeUserData();


    }

    public static void menu() {
        System.out.println("What action would you like to perform? \n" +
                "Print: a - to add new user to database \n" +
                "Print: r - to remove user from database \n" +
                "Print: d - to display specific user");
    }

    public static void insertUser() throws SQLException {

        Scanner scan = new Scanner(System.in);

        System.out.println("Insert your credentials\n");

        System.out.println("Insert your email: ");
        String email = scan.nextLine();

        System.out.println("Insert your username: ");
        String username = scan.nextLine();

        System.out.println("Insert your password: ");
        String password = scan.nextLine();

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
        int id = scan2.nextInt();

        System.out.println("What user parameter you would like to change?\n" +
                "Print e for email\n" +
                "Print u for username\n" +
                "Print p for password\n" +
                "Print m to to go back to menu");


        String input = scan.nextLine();

        try (Connection connection = DbUtil.connect("workshop2")) {
            if (input.equals("e")) {
                System.out.println("Insert new email:\n");
                DbUtil.updateUserData(connection,"users",id,"email",scan.nextLine());
            } else if (input.equals("u")) {
                System.out.println("Insert new username:\n");
                DbUtil.updateUserData(connection,"users",id,"username",scan.nextLine());

            } else if (input.equals("p")) {
                System.out.println("Insert new password:\n");
                DbUtil.updateUserData(connection,"users",id,"password",scan.nextLine());

            }else if (input.equals("m")) {
                menu();
            }
        }


    }


}
