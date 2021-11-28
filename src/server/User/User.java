package server.User;

public class User implements IUser {
    private String lastName;
    private String firstName;
    private String login;
    private String password;

    User(String lastName, String firstName, String login, String password) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.login = login;
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
