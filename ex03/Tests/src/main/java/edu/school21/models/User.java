package edu.school21.models;

public class User {

    private int identifier;
    private String login;
    private String password;
    private boolean authentication;

    public User(int identifier, String login, String password, boolean authentication) {
        this.identifier = identifier;
        this.login = login;
        this.password = password;
        this.authentication = authentication;
    }

    public int getIdentifier() {
        return identifier;
    }

    public void setIdentifier(int identifier) {
        this.identifier = identifier;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthentication() {
        return authentication;
    }

    public void setAuthentication(boolean authentication) {
        this.authentication = authentication;
    }

    @Override
    public String toString() {
        return "User{" +
                "identifier=" + identifier +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", authentication=" + authentication +
                '}';
    }
}
