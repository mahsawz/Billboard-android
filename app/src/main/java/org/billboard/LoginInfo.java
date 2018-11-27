package org.billboard;

public class LoginInfo {
    private String password;
    private String email;

    public LoginInfo(String password, String email) {
        this.password = password;
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
