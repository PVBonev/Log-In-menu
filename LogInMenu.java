import java.util.Arrays;
import java.util.Scanner;

public class LogInMenu {

    private String email;
     private String name;
     private String password;
    LogInMenu(String _name,String _email, String _password)//constructor with parameters
    {
        name=_name;
        email=_email;
        password=_password;
    }

    public LogInMenu() {//default constructor
        name="-";
        email="-";
        password="-";
    }

    public void setName(String _name)
    {
       name=_name;
    }
    public void setEmail(String _email)
    {
        email=_email;
    }
    public void setPassword(String _password)
    {
        password=_password;
    }
    public String getEmail()
    {
        return email;
    }
    public String getPassword()
    {
        return password;
    }
    public void print()//prints information about the user
    {
        System.out.println("\nName: "+name+"\nEmail: "+email+"\nPassword: "+password);
    }

    public static boolean checkprof(LogInMenu[] accs,LogInMenu account)//checks if the given profile is genuine
    {
        int i=0;
        while( i<accs.length)
        {
            if(accs[i].getEmail().equals(account.getEmail()) && accs[i].getPassword().equals(account.getPassword()))
            {
                return true;
            }
            i+=1;
        }
        return false;
    }
    public static void login(LogInMenu []accounts)//logs you in your profile
    {

        Scanner keyboard = new Scanner(System.in);//this is a way to enter from keyboard

        LogInMenu current=new LogInMenu();

        do {


        System.out.print("\nemail: ");
        String email = keyboard.nextLine();//we enter the email

        System.out.print("password: ");
        String password = keyboard.nextLine();//we enter the password

        current.setEmail(email);
        current.setPassword(password);

        //current.print();// prints information about the current user

            if(checkprof(accounts,current)==false)
                System.out.println("Wrong email or password");


        }while(checkprof(accounts,current)==false);

            System.out.println("Welcome to your profile!");

    }

    public static int emptyPosition(LogInMenu[] accs)//returns the position of the first empty element in our array
    {
        int i=0;

        LogInMenu temp=new LogInMenu();

        while(i< accs.length)

        {

            if(accs[i].getEmail().equals(temp.getEmail()) && accs[i].getPassword().equals(temp.getPassword()))
            {
                return i;
            }

            i+=1;
        }

        return 1025;//if we return this then our maximum limit is reached
    }
    public static void newwwacc(LogInMenu[] accounts)//creates a new account
    {


        Scanner keyboard = new Scanner(System.in);//this is a way to enter from keyboard

        System.out.print("\nname: ");
        String namep = keyboard.nextLine();//we enter the name

        System.out.print("email: ");
        String email = keyboard.nextLine();//we enter the email

        System.out.print("password: ");
        String password = keyboard.nextLine();//we enter the password

        String savedpass=password;
        do {//this do while makes sure we have entered the same password twice
            System.out.print("Confirm password: ");
            password = keyboard.nextLine();//we confirm the password
            if(password.equals(savedpass)==false)
            {
                System.out.println("\nPassword doesn't match!\nConfirmation needed!\n");
            }
        }while (password.equals(savedpass)==false);

        LogInMenu newacc=new LogInMenu(namep,email,password);
        int emptypos=emptyPosition(accounts);
        //newacc.print();//works
        if(emptypos==1025)
        {
            System.out.println("\nMaximum limit of users reached!\n");
        }
        else
        {
            //we assign the first empty position in the array with the newly created account
            accounts[emptypos]=newacc;
            System.out.println("\nRegistration Successful!\nAccount information:");

            accounts[emptypos].print();//test
        }

    }



    public static void main(String []args)
    {
        //here is our collection of profiles
        LogInMenu templ=new LogInMenu("-","-","-");
        LogInMenu[] accounts=new LogInMenu[1024];
        Arrays.fill(accounts,templ);
        accounts[0]= new LogInMenu("Ivan Ivanov","iivanov@abv.bg","12345");
        accounts[1]=new LogInMenu("Dani Danev","dan@gmail.com","qwerty");
        //

        System.out.println("Choose operation:\n[1]-Log in\n[2]-Create new account");
        System.out.print("Operation: " );

        Scanner keyboard = new Scanner(System.in);//this is a way to enter from keyboard

        int operation = keyboard.nextInt();

        if(operation==1) {
            login(accounts);
        } else if (operation==2) {

            newwwacc(accounts);

            System.out.println("[1]-log in\n[2]-exit");
            operation=keyboard.nextInt();

            if(operation==1)
                login(accounts);


        } else //if(operation!=1 && operation!=2)
            System.out.println("Wrong operation!\n");


    }
}
