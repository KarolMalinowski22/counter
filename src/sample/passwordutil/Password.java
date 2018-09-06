package sample.passwordutil;

import java.io.Serializable;

public class Password  implements Serializable {
    private final String passwordExpected;
    private final String passwordToAccess;
    private final int cooldown;
    public Password(String passwordToAccess, String passwordExpected, int cooldown){
        this.passwordToAccess = passwordToAccess;
        this.passwordExpected = passwordExpected;
        this.cooldown = cooldown;
    }
    public boolean checkPassword(String passwordGiven){
        if(passwordExpected.equals(passwordGiven)){
            return true;
        }
        return false;
    }

    public int getCooldown() {
        return cooldown;
    }

    public String getPasswordToAccess() {
        return passwordToAccess;
    }
}
