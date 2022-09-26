import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import java.util.*;
import java.text.*;

class User
{
    private String email;
    private String name;
    private String password;
    User(String _name,String _email, String _password)//constructor with parameters
    {
        name=_name;
        email=_email;
        password=_password;
    }

    public User()//default constructor
    {
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
        System.out.println("Name: "+name+"\nEmail: "+email+"\nPassword: "+password);
    }
}

class Engine {

    //---------------------------------------login--------------------------------------------------------------------

    public static boolean checkprof(User[] accs,User account)//checks if the given profile is genuine
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

    public static void login(User[] accounts)//logs you in your profile
    {

        Scanner keyboard = new Scanner(System.in);//this is a way to enter from keyboard

        User current = new User();

        do {


            System.out.print("\nemail: ");
            String email = keyboard.nextLine();//we enter the email

            System.out.print("password: ");
            String password = keyboard.nextLine();//we enter the password

            current.setEmail(email);
            current.setPassword(password);

            //current.print();// prints information about the current user

            if (checkprof(accounts, current) == false)
                System.out.println("Wrong email or password");


        } while (checkprof(accounts, current) == false);

        System.out.println("\nWelcome to your profile!");

        //a fancy welcoming message to your profile
        Date dNow = new Date( );
        SimpleDateFormat day = new SimpleDateFormat ("E");
        String dayofweek= String.valueOf('-');
        if(day.format(dNow).toString().equals("Mon"))
            dayofweek="monday!";
        else if (day.toString().equals("Tue")) {
            dayofweek="tuesday!";
        }
        else if (day.toString().equals("Wed")) {
            dayofweek="wednesday!";
        }
        else if (day.toString().equals("Thu")) {
            dayofweek="thursday!";
        }
        else if (day.toString().equals("Fri")) {
            dayofweek="friday!";
        }
        else if (day.toString().equals("Sat")) {
            dayofweek="saturday!";
        }
        else if (day.toString().equals("Sun")) {
            dayofweek="sunday!";
        }

        SimpleDateFormat date = new SimpleDateFormat ("'\nDate: 'yyyy.MM.dd '\nHour:' hh:mm");

        //System.out.println("It's a beautiful " + day.format(dNow)+"\nDate: "+date.format(dNow));//this works
        System.out.println("It's a beautiful " + dayofweek+date.format(dNow));


    }
    //--------------------------------------------------------------------------

    public static int emptyPosition(User[] accs)//returns the position of the first empty element in our array
    {
        int i=0;

        User temp=new User();//creates an User with '-' for each field(name,email,pass)

        while(i < accs.length)

        {
            if(accs[i].getEmail().equals(temp.getEmail()) && accs[i].getPassword().equals(temp.getPassword()))
            {
                return i;
            }
            i+=1;
        }

        return 1025;//if we return this then our maximum limit is reached
    }

    public static boolean checkEmail(String email)//method that checks if the email is correct
    {
        if(email.endsWith("@abv.bg") || email.endsWith("@gmail.com"))
            return true;
        return false;
    }

    public static void newacc(User[] accounts)//creates a new account
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

        User newacc=new User(namep,email,password);
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
            System.out.println("\nRegistration Successful!\n\nAccount information:");

            accounts[emptypos].print();
            Date date=new Date();
            SimpleDateFormat simp=new SimpleDateFormat ("yyyy.MM.dd 'at' hh:mm:ss zzz");
            System.out.println("Account created on "+simp.format(date)+'\n');
        }

    }

}
public class OOPLogIn {
    public static void main(String[] args)
    {
        Engine engine=new Engine();//check



        //here is our collection of profiles
        User templ=new User();// this user will have '-' on each of its fields
        User[] accounts=new User[1024];
        Arrays.fill(accounts,templ);
        accounts[0]= new User("Ivan Ivanov","iivanov@abv.bg","12345");
        accounts[1]=new User("Dani Danev","dan@gmail.com","qwerty");
        //

        System.out.println("Choose operation:\n[1]-Log in\n[2]-Create new account");
        System.out.print("Operation: " );

        Scanner keyboard = new Scanner(System.in);//this is a way to enter from keyboard

        int operation = keyboard.nextInt();

        if(operation==1) {
            engine.login(accounts);
        }
        else if (operation==2) {
            engine.newacc(accounts);

            System.out.println("[1]-log in\n[2]-exit\nOperation: ");
            operation=keyboard.nextInt();

            if(operation==1)
                engine.login(accounts);


        } else if(operation!=1 && operation!=2)
            System.out.println("Wrong operation!");
    }
}
