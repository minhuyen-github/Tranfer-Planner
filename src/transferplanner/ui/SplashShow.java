package transferplanner.ui;

import java.io.FileNotFoundException;

/**
 * A complete class which displays the splash screen
 *
 * @author Uyen Hoang
 * @author Phuong Tran
 * @version 1.0
 */
public class SplashShow {
    
    public static void splashMethod () throws InterruptedException, FileNotFoundException
    {
        // fields
        SplashScreen Splash = new SplashScreen();
        AssociateCSPlan appliance = new AssociateCSPlan();
        try
        {
            for(int i = 0; i <= 100; i++)
            {
                // turn on the splash screen
                Thread.sleep(40);
                Splash.setVisible(true);
                // set the message
                Splash.loading.setText("Loading... " + Integer.toString(i)
                        + "%");
                // run the loading process
                Splash.loadingBar.setValue(i);
                if (i == 100)
                {
                    // stop running and display the main application if reach 100%
                    Splash.setVisible(false);
                    appliance.setVisible(true);
                }
            }
        }
        catch (InterruptedException e){
            
        }
  
    }
}
