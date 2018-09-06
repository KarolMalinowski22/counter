package sample.passwordutil;

import java.io.File;

public class Input {
    private static String defaultPassword = "86248624";
    private static String passwordGiven = "";


    public static String getPasswordGiven() {
        return passwordGiven;
    }

    public static void setPasswordToDisplay(String passwordGiven) {
        Input.passwordGiven = passwordGiven;
    }

    public static void writePasswordToDisplay(String passwordGiven) {
        Input.passwordGiven += passwordGiven;
    }

    public static boolean reset() {
        if(defaultPassword.equals(passwordGiven)){
            Input.passwordGiven = "";
            hardReset();
            return true;
        }
        Input.passwordGiven = "";
        return false;
    }

    public static void back() {
        if (!"".equals(Input.passwordGiven)) {
            Input.passwordGiven = getPasswordGiven().substring(0, getPasswordGiven().length() - 1);
        }
    }

    private static void hardReset(){
        new File("casp.ser").delete();
        CheckAndSetPassword.setIsPasswordHiddenSet(false);
        CheckAndSetPassword.setIsPasswordExpectedSet(false);
        CheckAndSetPassword.setIsCooldownSet(false);

    }
}
