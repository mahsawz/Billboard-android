package org.faradars.billboard;

public class SignupInfo {

        private String password;
        private String name;
        private String email;

        public SignupInfo(String username, String email,String password) {
            this.password = password;
            this.name = username;
            this.email=email;
        }

        public String getPassword() {
            return password;
        }

        public String getName() {
            return name;
        }
        public String getEmail() {
        return email;
    }

}
