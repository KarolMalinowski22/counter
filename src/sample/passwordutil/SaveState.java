package sample.passwordutil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SaveState {
    public void save(Object o) {
        try {
            FileOutputStream fos = new FileOutputStream("casp.ser");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(o);
            oos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static boolean checkForSave(){
        if(new File("casp.ser").exists()){
            CheckAndSetPassword.setIsCooldownSet(true);
            CheckAndSetPassword.setIsPasswordExpectedSet(true);
            CheckAndSetPassword.setIsPasswordHiddenSet(true);
            return true;
        }
        return false;
    }

}
