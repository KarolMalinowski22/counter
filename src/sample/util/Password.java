package sample.util;

public class Password {
    private final String passwordExpected;
    private final String passwordToAccess;
    public Password(String passwordToAccess, String passwordExpected){
        this.passwordToAccess = passwordToAccess;
        this.passwordExpected = passwordExpected;
    }
    public boolean checkPassword(String passwordGiven){
        if(passwordExpected.equals(passwordGiven)){
            return true;
        }
        return false;
    }



    public String getPasswordToAccess() {
        return passwordToAccess;
    }
}
