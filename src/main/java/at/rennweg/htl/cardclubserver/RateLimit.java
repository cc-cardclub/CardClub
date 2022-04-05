package at.rennweg.htl.cardclubserver;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class RateLimit {
    public static List<InetAddress> addresses = new ArrayList<>();

    public static final Timer timer = new Timer();
    public static final TimerTask resetRate = new TimerTask() {
        @Override
        public void run() {
            addresses.clear();
        }
    };
}
