package sample.util;

import javafx.scene.control.TextField;
import sample.passwordutil.CheckAndSetPassword;

import java.io.Serializable;
import java.util.concurrent.CountDownLatch;

public class Counting {
    private TextField display;

    public Counting(TextField display) {
        this.display = display;
    }

    public void startCountingBack(int cooldown, CountDownLatch latch) {
        int cooldownSec = cooldown / 1000;
        int currentMins;
        int currentSecs;
        for (int i = cooldownSec; i > 0; i--) {
            currentMins = i / 60;
            currentSecs = i % 60;
            if (currentSecs > 9) {
                display.setText("AUTHORIZATION " + currentMins + ":" + currentSecs);
            }else {
                display.setText("AUTHORIZATION " + currentMins + ":0" + currentSecs);
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        latch.countDown();
    }
}
