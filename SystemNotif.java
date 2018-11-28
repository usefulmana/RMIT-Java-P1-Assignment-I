package rmit.p1;
import java.awt.*;
import java.awt.TrayIcon.MessageType;
import java.net.MalformedURLException;

public class SystemNotif {

    public void displayTray(String caption, String text) throws AWTException, MalformedURLException {   //Desktop notification
        // This method is created to display notification
        //Obtain only one instance of the SystemTray object
        SystemTray newTray = SystemTray.getSystemTray();

        //If the icon is a file
        Image image = Toolkit.getDefaultToolkit().createImage("icon.png");

        TrayIcon trayIcon = new TrayIcon(image, "Tray Demo");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        trayIcon.setToolTip("System tray icon demo");
        newTray.add(trayIcon);

        trayIcon.displayMessage(caption, text, MessageType.INFO);
    }
}
