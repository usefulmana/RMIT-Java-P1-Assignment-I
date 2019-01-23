package rmit.p1;

import java.util.Observable;
import java.util.Observer;

public class Observer1 implements Observer {

    /** Observer class for the subscription*/
    private static Observer1 observer1; // create a default object

    private Observer1() // private constructor to prevent additional object creation
    {
    }

    public static Observer1 getInstance()
    {
        if (observer1 == null)
        {
            observer1 = new Observer1();
        }
        return observer1;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("New Lottery Result Available!"); // notify user of new result
    }

    public static boolean checkSubscription()
    { /** Check if a user is a subscriber or not*/
        if (observer1 == null)
        {
            return false;
        }
        return true;
    }
}
