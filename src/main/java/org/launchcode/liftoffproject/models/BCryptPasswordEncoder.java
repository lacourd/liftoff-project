package org.launchcode.liftoffproject.models;

public class BCryptPasswordEncoder {
    public String encode(String password) {
        return password;
    }

    public boolean matches(String password, String pwHash) {
        return false;
    }
}
