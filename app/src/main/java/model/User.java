package model;

public class User {
    private String password;
    private String email;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getUserName() {
        return email;
    }

    public void setUserName(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
