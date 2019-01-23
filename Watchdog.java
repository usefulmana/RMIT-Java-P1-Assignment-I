package rmit.p1;

import rmit.p1.Ticket.Lottery;

import java.util.Observable;
import java.util.Timer;
import java.util.TimerTask;

public class Watchdog extends Observable {
    /** This class is being observed by the Observer1 class.*/
    Timer timer;
    int seconds;
    private Observer1 observer1;
    private static Watchdog watchdog; // create a default object

    public static Watchdog getInstance()
    {
        if(watchdog == null)
        {
            watchdog = new Watchdog();
        }
        return watchdog;
    }

    // Notify task to notify observers

    class NotifyTask extends TimerTask {

        @Override
        public void run() {
            Lottery lottery = Lottery.getInstance();
            System.out.print("Winning ticket numbers: ");
            lottery.printTicket();
            System.out.println();
            setChanged();
            notifyObservers();
        }
    }

    private Watchdog()
    {
        timer = new Timer();
    }

    public void schedule(){
        timer.scheduleAtFixedRate(new NotifyTask(), 604800000, 604800000); //delay in milliseconds, 1 week = 604800000 milliseconds

    }

    public void stop(){
        timer.cancel();
        watchdog.deleteObserver(observer1);
        timer = new Timer();
        // Restart the timer here
    }
}
