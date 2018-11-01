package is2103lecture09client;

import ejb.session.stateless.BeanValidationSessionBeanRemote;
import ejb.session.stateless.CheckProcessingSessionBeanRemote;
import ejb.session.stateless.EjbTimerSessionBeanRemote;
import java.math.BigDecimal;
import java.util.Scanner;
import util.exception.AccountNotFoundException;
import util.exception.EncashCheckException;
import util.exception.InsufficientBalanceException;



public class MainApp
{
    private CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote;
    private EjbTimerSessionBeanRemote ejbTimerSessionBeanRemote;
    private BeanValidationSessionBeanRemote beanValidationSessionBeanRemote;
    
    
    
    public MainApp()
    {
    }

    
    
    public MainApp(CheckProcessingSessionBeanRemote checkProcessingSessionBeanRemote, EjbTimerSessionBeanRemote ejbTimerSessionBeanRemote, BeanValidationSessionBeanRemote beanValidationSessionBeanRemote) 
    {
        this();
        
        this.checkProcessingSessionBeanRemote = checkProcessingSessionBeanRemote;
        this.ejbTimerSessionBeanRemote = ejbTimerSessionBeanRemote;
        this.beanValidationSessionBeanRemote = beanValidationSessionBeanRemote;
    }
    
    
    
    public void runApp()
    {
        Scanner scanner = new Scanner(System.in);
        Integer response;
        
        while(true)
        {
            System.out.println("*** Welcome to IS2103 Lecture 09 ***\n");
            System.out.println("1: Demo Transactions - Required");
            System.out.println("2: Demo Transactions - Requires New");
            System.out.println("3: Demo Create Timers");
            System.out.println("4: Demo Cancel Timers");
            System.out.println("5: Demo Bean Validation - Programmatic Validation");
            System.out.println("6: Exit\n");
            response = 0;
            
            while(response < 1 || response > 6)
            {
                System.out.print("> ");

                response = scanner.nextInt();

                if(response == 1)
                {
                    demo1();
                }
                else if (response == 2)
                {
                    demo2();
                }
                else if (response == 3)
                {
                    demo3();
                }
                else if (response == 4)
                {
                    demo4();
                }
                else if (response == 5)
                {
                    demo5();
                }                
                else if (response == 6)
                {
                    break;
                }
                else
                {
                    System.out.print("Invalid option, please try again!\n");                
                }
            }
            
            if(response == 6)
            {
                break;
            }
        }
    }



    private void demo1()
    {
        try
        {
            System.out.println("*** IS2103 Lecture 09 :: 1 - Demo Transactions - Required ***\n");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Checking Account Id> ");
            Long checkingAccountId = scanner.nextLong();
            System.out.print("Enter Savings Account Id> ");
            Long savingsAccountId = scanner.nextLong();
            System.out.print("Enter Check amount> ");
            BigDecimal amount = scanner.nextBigDecimal();

            checkProcessingSessionBeanRemote.encashCheck(checkingAccountId, savingsAccountId, amount);
            
            System.out.println("Check encashed successfully!");
        }
        catch(EncashCheckException ex)
        {
            System.out.println("An error has occurred while encashing your check: " + ex.getMessage() + "!");
        }
    }
    
    
    
    private void demo2()
    {
        try
        {
            System.out.println("*** IS2103 Lecture 09 :: 2 - Demo Transactions - Requires New ***\n");

            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter Checking Account Id> ");
            Long checkingAccountId = scanner.nextLong();
            System.out.print("Enter Savings Account Id> ");
            Long savingsAccountId = scanner.nextLong();
            System.out.print("Enter Check amount> ");
            BigDecimal amount = scanner.nextBigDecimal();

            checkProcessingSessionBeanRemote.encashCheckRequiresNewTransaction(checkingAccountId, savingsAccountId, amount);
            
            System.out.println("Check encashed successfully!");
        }
        catch(EncashCheckException ex)
        {
            System.out.println("An error has occurred while encashing your check: " + ex.getMessage() + "!");
        }
    }
    
    
    
    private void demo3()
    {
        System.out.println("*** IS2103 Lecture 09 :: 1 - Demo Create Timers ***\n");
        ejbTimerSessionBeanRemote.createTimers();
        System.out.println("Done!\n");
    }
    
    
    
    private void demo4()
    {
        System.out.println("*** IS2103 Lecture 09 :: 1 - Demo Cancel Timers ***\n");
        ejbTimerSessionBeanRemote.cancelTimers();
        System.out.println("Done!\n");
    }
    
    
    
    private void demo5()
    {
        System.out.println("*** IS2103 Lecture 09 :: 5 - Demo Bean Validation - Programmatic Validation ***\n");
        
        beanValidationSessionBeanRemote.validateProgrammatically();
        
        System.out.println("Done!\n");
    }
}
