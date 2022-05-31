package gr.vk.cf.contacts;

import gr.vk.cf.contacts.Controller.MobileContactController;
import gr.vk.cf.contacts.model.MobileContacts;

import java.util.Scanner;

public class Main {
    private static final MobileContactController controller=new MobileContactController();
    private static final Scanner sc=new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            printMenu();
            choice=getInputChoice();
            manageChoice(choice);
        }while (choice!=5);


    }
    private static void manageChoice(int choice){
        switch (choice){
            case 1:
                insertHandler();
                break;
            case 2:
                getContactAndPrintHandler();
                break;
            case 3:
                updateHandler();
                break;
            case 4:
                deleteHandler();
                break;
            case 5:
                System.out.println("QUIT");
                break;
            default:
                System.out.println("Incalid choice");
                break;
        }
    }

    private static void printMenu(){
        System.out.println("Please insert one of the following");
        System.out.println("1.Insert Contact");
        System.out.println("2.Get and Print Contact");
        System.out.println("3.Update Contact");
        System.out.println("4.Delete Contact");
        System.out.println("5.Quit Contact");

    }
    private static int getInputChoice(){
        String strChoice;
        int choice;
        strChoice=sc.nextLine();
        if (isInt(strChoice)){
            choice=Integer.parseInt(strChoice);
        }else {
            choice=-1;
        }

        return choice;
    }

    private static boolean isInt(String s){
        try {
            Integer.parseInt(s);
            return true;
        }catch (NumberFormatException e) {
            return false;
        }
    }

    private static String getInputFirstName(){
        System.out.println("Please insert  first name ");
        return sc.nextLine();

    }
    private static String getInputLastName(){
        System.out.println("Please insert  last name ");
        return sc.nextLine();

    }
    private static String getInputPhoneNumber(){
        System.out.println("Please insert  phone number ");
        return sc.nextLine();
    }
    private static void  printContact(MobileContacts contact){
        System.out.println(contact.convertToString());
    }

    public static void insertHandler(){
        String firstname;
        String lastname;
        String phoneNumber;

        firstname=getInputFirstName().trim();
        lastname=getInputLastName().trim();
        phoneNumber=getInputPhoneNumber().trim();

        if (controller.insertController(firstname,lastname,phoneNumber)){
            System.out.println("Contact Inserted");
        }else {
            System.out.println("Contact already exists");
        }

    }
    public static void  getContactAndPrintHandler(){
        String phoneNumber;
        MobileContacts contact=new MobileContacts();

        phoneNumber=getInputPhoneNumber();
        contact=controller.getContactController(phoneNumber);

        if (contact==null){
            System.out.println("Contact not found");
            return;
        }
        printContact(contact);
    }

    public static void updateHandler(){
        String firstname;
        String lastname;
        String phoneNumber;
        String inputPhoneNumber;
        MobileContacts contact;

        inputPhoneNumber=getInputPhoneNumber();
        contact=controller.getContactController(inputPhoneNumber);

        if (contact!=null){
            firstname=getInputFirstName().trim();
            lastname=getInputLastName().trim();
            phoneNumber=getInputPhoneNumber().trim();

            if (controller.updateController(inputPhoneNumber,firstname,lastname,phoneNumber)){
                System.out.println("Contact updated");
            }else{
                System.out.println("Contact not found");
            }
        }else{
            System.out.println("Contact not found");
        }
    }

    public static void deleteHandler(){
        String phoneNumber;
        MobileContacts contact=new MobileContacts();

        phoneNumber=getInputPhoneNumber();
        contact=controller.getContactController(phoneNumber);

        if (contact!=null){
            if (controller.deleteController(phoneNumber)){
                System.out.println("Contact deleted");
            }else {
                System.out.println("Contact not found based on this phone number");
            }
        }else {
            System.out.println("Phone number not found");
        }
    }

}
