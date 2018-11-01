package is2103lecture09client;

import ejb.session.stateless.BeanValidationSessionBeanRemote;
import ejb.session.stateless.CheckProcessingSessionBeanRemote;
import ejb.session.stateless.EjbTimerSessionBeanRemote;
import javax.ejb.EJB;



public class Main
{    
    @EJB
    private static CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote;
    @EJB
    private static EjbTimerSessionBeanRemote ejbTimerSessionBeanRemote;
    @EJB
    private static BeanValidationSessionBeanRemote beanValidationSessionBeanRemote;
    
    
        
    public static void main(String[] args)
    {
        MainApp mainApp = new MainApp(checkProcessingSessionBeanRemote, ejbTimerSessionBeanRemote, beanValidationSessionBeanRemote);
        mainApp.runApp();   
    }
}