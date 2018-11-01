package ejb.session.stateless;



public interface EjbTimerSessionBeanRemote
{
    public void createTimers();
    
    public void cancelTimers();
}