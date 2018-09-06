package sample.passwordutil;

import javafx.geometry.Pos;
import sample.Controller;
import sample.util.Counting;

import java.util.concurrent.CountDownLatch;

public class CheckAndSetPassword{
    private  Controller controller;
    private  Password password = new Password("", "", 0);
    private  String passwordExpected;
    private  String passwordSecured;
    private static boolean isPasswordExpectedSet = false;
    private static boolean isPasswordHiddenSet = false;
    private static boolean isCooldownSet = false;
    private  int cooldown;


    public void checkOrSetPassword() {
        if (!isCooldownSet) {
            if (!isPasswordHiddenSet) {
                if (!isPasswordExpectedSet) {
                    setPasswordExpected();
                } else {
                    setPasswordHidden();
                }
            } else {
                setCooldown();
                new SaveState().save(password);
            }
        } else if (password.checkPassword(Input.getPasswordGiven())) {
            CountDownLatch latch = new CountDownLatch(1);
            Counting counting = new Counting(controller.getDisplay());
            Runnable runnable = () -> counting.startCountingBack(password.getCooldown(), latch);
            Runnable runnable2 = () -> givePassword(latch);
            Thread thread = new Thread(runnable);
            Thread thread2 = new Thread(runnable2);
            controller.getDisplay().setAlignment(Pos.CENTER);
            thread.setDaemon(true);
            thread2.setDaemon(true);
            thread.start();
            thread2.start();
        }
    }

    private void setPasswordExpected() {
        passwordExpected = Input.getPasswordGiven();
        isPasswordExpectedSet = true;
        Input.reset();
        controller.setTextOnDisplay(Input.getPasswordGiven());
        controller.setTextOnLabel("Set password secured:");
    }

    private void setPasswordHidden() {
        passwordSecured = Input.getPasswordGiven();
        isPasswordHiddenSet = true;
        Input.reset();
        controller.setTextOnDisplay(Input.getPasswordGiven());
        controller.setTextOnLabel("Set cooldown (min):");
    }

    public int getCooldown() {
        return cooldown;
    }

    private void setCooldown() {
        cooldown = Integer.parseInt(Input.getPasswordGiven()) * 60000;
        password = new Password(passwordSecured, passwordExpected, cooldown);
        passwordSecured = null;
        passwordExpected = null;
        isCooldownSet = true;
        Input.reset();
        controller.setTextOnDisplay(Input.getPasswordGiven());
        controller.setTextOnCheckButton("Check");
        controller.setTextOnLabel("Password:");
    }

    private void givePassword(CountDownLatch latch){
        controller.disableButtons(true);
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        controller.disableReset(false);
        Input.setPasswordToDisplay(password.getPasswordToAccess());
        controller.setTextOnDisplay(Input.getPasswordGiven());
        controller.getDisplay().setAlignment(Pos.CENTER_RIGHT);
    }


    public void setController(Controller controller) {
        this.controller = controller;
    }

     static void setIsPasswordExpectedSet(boolean isPasswordExpectedSet) {
        CheckAndSetPassword.isPasswordExpectedSet = isPasswordExpectedSet;
    }

     static void setIsPasswordHiddenSet(boolean isPasswordHiddenSet) {
        CheckAndSetPassword.isPasswordHiddenSet = isPasswordHiddenSet;
    }

     static void setIsCooldownSet(boolean isCooldownSet) {
        CheckAndSetPassword.isCooldownSet = isCooldownSet;
    }
    public void setPassword(Password password){
        this.password = password;
    }
}

