package at.rennweg.htl.cardclubserver;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Class for rate-limiting server requests
 *
 * @author Raven Burkard
 */
public class RateLimit {
    /**
     * Addresses which should be limited
     */
    public static List<InetAddress> addresses = new ArrayList<>();

    /**
     * Timer for the reset task
     */
    public static final Timer TIMER = new Timer();

    /**
     * Periodically resets the limited addresses
     */
    public static final TimerTask TIMER_TASK = new TimerTask() {
        @Override
        public void run() {
            addresses.clear();
        }
    };
}
