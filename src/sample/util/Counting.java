package sample.util;

public class Counting {
    private static String passwordGiven = "";


    public static String getPasswordGiven() {
        return passwordGiven;
    }

    public static void setPasswordGiven(String passwordGiven) {
        Counting.passwordGiven += passwordGiven;
    }
    public static void reset(){
        Counting.passwordGiven = "";
    }
    public static void back(){
        Counting.passwordGiven = getPasswordGiven().substring(0, getPasswordGiven().length() - 1);
    }
}
