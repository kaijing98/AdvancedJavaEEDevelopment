package ejb.session.stateless;

import datamodel.Record;
import java.util.Collection;
import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.NoSuchObjectLocalException;
import javax.ejb.Remote;
import javax.ejb.Schedule;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;



@Stateless
@Local(EjbTimerSessionBeanLocal.class)
@Remote(EjbTimerSessionBeanRemote.class)

public class EjbTimerSessionBean implements EjbTimerSessionBeanLocal, EjbTimerSessionBeanRemote
{
    @Resource 
    private SessionContext sessionContext;

    
    
    public EjbTimerSessionBean()
    {
    }
    
    
    
    @Override
    public void createTimers() 
    {
        TimerService timerService = sessionContext.getTimerService();
        
        timerService.createTimer(5000, 5000, "createTimer5000msInterval");
        
        timerService.createTimer(5000, "createTimer5000msSingleAction");
        
        timerService.createTimer(5000, new Record(1l, "Record One"));
        
        timerService.createSingleActionTimer(5000, new TimerConfig("createSingleActionTimer5000ms", true));
    }
    
    
    
    @Override
    public void cancelTimers() 
    {
        TimerService timerService = sessionContext.getTimerService();
        Collection<Timer> timers = timerService.getTimers();
        
        for(Timer timer:timers) 
        {
            if(timer.getInfo() != null)
            {
                if(timer.getInfo().toString().equals("createTimer5000msInterval") ||
                        timer.getInfo().toString().equals("createTimer5000msSingleAction") ||
                        timer.getInfo().toString().equals("createSingleActionTimer5000ms") ||
                        timer.getInfo().toString().equals("scheduleEvery5Second"))
                {
                    try
                    {
                        timer.cancel();
                        System.out.println("********** EjbTimerSession.cancelTimers(): " + timer.getInfo().toString());
                    }
                    catch(NoSuchObjectLocalException ex)
                    {
                        System.err.println("********** EjbTimerSession.cancelTimers(): ERROR CANCELING, timer already cancelled or does not exist");
                    }
                }
            }
        }
    }
    
    
    
    @Schedule(hour = "*", minute = "*", second = "*/5", info = "scheduleEvery5Second")
    public void automaticTimer()
    {
        System.out.println("********** EjbTimerSession.automaticTimer(): scheduleEvery5Second");
    }
    
    
    
    @Timeout
    public void handleTimeout(Timer timer) 
    {
        System.out.println("********** EjbTimerSession.handleTimeout(): " + timer.getInfo().toString());
    }
}