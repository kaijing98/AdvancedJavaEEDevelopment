# AdvancedJavaEEDevelopment
Example on Java Transaction API and Timer Service API

In this example, we use the fund transfer concept.

To simulate an error in the credit step, we will attempt to perform the credit on a non-existent savings account. 
Running the program will lead to an application exception AccountNotFoundException being thrown.

We then initiate a transaction rollback when this exception is caught in the calling session bean, i.e., CheckProcessingBean.
Depending on the transaction attribute configured, i.e., either Required or RequiresNew, we will see that if a rollback is not performed correctly, 
the integrity of the information stored in the database would be compromised.

1) When Required is used consistently:
- There is one and only one transaction T1 that is involved.
- Thus, when an application exception occurs during the credit
step, CheckProcessingBean can rollback the entire transaction T1.

2) When RequiresNew is involved:
- Each of the debit checking account, credit savings account and update history logging step runs in its own new
transaction, i.e., T2, T3 and T4.
- Thus, when CheckProcessingBean attempts to rollback after the credit step, 
transaction T2 (CheckingAccountSessionBean debitCheckingAccount()) would have already been committed.
- In other words, the checking account involved would have been debited without the corresponding savings account 
being credited.
- The two accounts involved would not be balanced, i.e. compromising data integrity

The program also demonstrates the use of the timer service of the EJB container to allow the application to handle time-based events.
Bean validation is also carried out.
