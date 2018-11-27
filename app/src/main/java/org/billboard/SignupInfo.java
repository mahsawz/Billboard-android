package org.billboard;

public class SignupInfo {

        private String password;
        private String username;
        private String email;

        public SignupInfo(String username, String email,String password) {
            this.password = password;
            this.username = username;
            this.email=email;
        }

        public String getPassword() {
            return password;
        }

        public String getUsername() {
            return username;
        }
        public String getEmail() {
        return email;
    }

}
