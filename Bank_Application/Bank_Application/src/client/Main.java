package client;
import transaction.Transaction;
import java.util.*;
import java.util.ArrayList;
import account.Account;
import admin.Admin;
public class Main {
   public static void main(String[] args) {
	   Scanner sc=new Scanner(System.in);
	   HashMap<String,Account>details=new HashMap<>();
	   HashMap<String,Admin>Admin_details=new HashMap<>();
	   HashMap<String,ArrayList<Transaction>>TransactionDetails=new HashMap<>();
	   details.put("Boopathi", new Account(101,"Boopathi","Savings",12000.35,"123456",70108687,"Kallakurichi","Rishivandhiyam"));
	   Admin_details.put("Vijayan", new Admin(102,"Vijayan","12345"));
	   Admin Ad_account=null;
	   Account account=null;
       boolean flag=true;	   
	   while(flag) {
		   System.out.println("1.Login\n"+
	                            "2.Check Balance\n"+
				                 "3.Deposit\n"+
	                             "4.Withdraw\n"+
				                 "5.Transfer\n"+
	                             "6.log out");
		   int eventType=sc.nextInt();
           if(account==null && eventType>1) {
        	   System.out.println("User not logged in");
        	   continue;
           }
           else {
        	   switch(eventType) {
        	   case 1:
        		   System.out.println("Login (Admin/User) :");
        		   String ch=sc.next();
        		   if(ch.equals("User")) {
        			   if(account==null) {
                		   System.out.println("Enter Username :");
                		   String userName=sc.next();
                		   System.out.println("Enter Password :");
                		   String password=sc.next();
                		   account=checkAccountExist(userName,password,details);
                		   if(account.accountNumber!=0) {
                 		   System.out.println("User has been logged in with Account no "+account.accountNumber);
                 		   TransactionDetails.put(account.userName, new ArrayList<Transaction>());
                		   }
                		   else {
                			   account=null;
                		   }
                		   }
                		   else {
                			   System.out.println("User is already logged in");
                		   }
        		   }
        		   else {
        			   if(Ad_account==null) {
                		   System.out.println("Enter Admin Name :");
                		   String AdminName=sc.next();
                		   System.out.println("Enter Password :");
                		   String password=sc.next();
                		   Ad_account=checkAccountExist_admin(AdminName,password,Admin_details);
                		   if(Ad_account.id!=0) {
                 		   System.out.println("Admin has been logged in with Account no "+Ad_account.id);
//                 		   TransactionDetails.put(account.userName, new ArrayList<Transaction>());
                 		   
                 		   boolean flog1=true;
                 		   while(flog1) {
                 			   System.out.println("1.Create Account\n"
                 			   		+ "2.other options\n"
                 			   		+ "3.back");
                 			    int option=sc.nextInt();
                 			    switch(option) {
                 			    case 1:
                 			    	long accountNo;
                 			    	System.out.println("Enter Account no: ");
                 			    	accountNo=sc.nextLong();
                 			   	    String username;
                 			   	    System.out.println("Enter User Name: ");
                 			   	    username=sc.next();
                 			        String accounttype;
                 			        System.out.println("Enter Account Type: ");
                			   	    accounttype=sc.next();
                 			        double balance;
                 			        System.out.println("Enter initail balance: ");
               			   	        balance=sc.nextDouble();
                 			        String pw;
                 			        System.out.println("Enter Password: ");
               			   	        pw=sc.next();
                 			        long phoneNo;
                 			       System.out.println("Enter Phone Number: ");
              			   	        phoneNo=sc.nextLong();
                 			        String addrs;
                 			       System.out.println("Enter Address: ");
              			   	        addrs=sc.next();

                 			        String branchname;
                 			       System.out.println("Enter branch Name: ");
              			   	        branchname=sc.next();
              			   	        
              			   	        Account acc2=new Account(accountNo,username,accounttype,balance,pw,phoneNo,addrs,branchname);
              			   	      if(details.getOrDefault(username, null)==null) {
              			   	    	   details.put(username, acc2);
              			   	      }
              			   	      break;

                 			    }
;                 		   }
                		   }
                		   else {
                			   account=null;
                		   }
                		   }
                		   else {
                			   System.out.println("Admin is already logged in");
                		   }
        		   }
        		   
        		  break;
        	   case 2:
        		   System.out.println("Your Account Balance is : "+account.balance);
        		   break;
        	   case 3:
        		     System.out.println("Enter deposit amount : ");
        		     double deposit_amount=sc.nextDouble();
        		     if(deposit_amount>0) {
        		    	 int fromAcountId=123;
        		    	 int TransactionId=4567;
;        		    	 double bal=account.balance+deposit_amount;
        		    	 account.balance=bal;
        		    	 System.out.println("Deposit is completed ..your initail amount is : "+account.balance);
        		    	 ArrayList<Transaction> prevTrans=TransactionDetails.get(account.getUserName());
        		    	 prevTrans.add(new Transaction(fromAcountId,101,TransactionId++,account.accountNumber,"12-nov-2023","deposit"));
        		    	 TransactionDetails.put(account.userName,prevTrans);
        		    	 details.put(account.getUserName(),account);
        		     }
        		     break;
        	   case 4:
        		   System.out.println("Enter the Withdraw amount : ");
        		   double withdrawAmt=sc.nextDouble();
        		   if(account.getBalance()>=withdrawAmt) {
        			   System.out.println("Enter the to Account id : ");
      		    	   int toAccountId=sc.nextInt();
      		    	   int fromAcountId=123;
      		    	   int TransactionId=4567;
        			   Transaction transactions=new Transaction();
        			   double bal=account.balance-withdrawAmt;
      		    	   account.balance=bal;
      		    	   ArrayList<Transaction> prevTrans=TransactionDetails.get(account.getUserName());
      		    	   prevTrans.add(new Transaction(fromAcountId,toAccountId,TransactionId++,account.accountNumber,"12-nov-2023","deposit"));
 
      		    	   TransactionDetails.put(account.userName,prevTrans);
      		    	   details.put(account.getUserName(),account);
        		   }
        		   else {
      		    	 System.out.println("Your balance is less than your Withdraw amount !!");
      		     }
      		     break;
        	   case 6:
        		   System.out.println("User has been logged out");
        		   account=null;
        		   flag=false;
        		   break;
        	   }
        	   
           }
	   }
	   
	   
   }
   public static Account checkAccountExist(String username,String password,HashMap<String,Account>accounts) {
	   if(accounts.getOrDefault(username, null)!=null) {
		   Account acc=accounts.get(username);
		   if(acc.password.equals(password) && acc.userName.equals(username)) {
			   return acc;
		   }
		   else {
			    return new Account();
		   }
		   
	   }
	   else {
		   return new Account();
	   }
   }
   public static Admin checkAccountExist_admin(String username,String password,HashMap<String,Admin>Admin_details) {
	   if(Admin_details.getOrDefault(username, null)!=null) {
		   Admin acc1=Admin_details.get(username);
		   if(acc1.password.equals(password) && acc1.userName.equals(username)) {
			   return acc1;
		   }
		   else {
			    return new Admin();
		   }
		   
	   }
	   else {
		   return new Admin();
	   }
   }
 
}
