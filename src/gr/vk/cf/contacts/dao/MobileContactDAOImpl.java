package gr.vk.cf.contacts.dao;

import gr.vk.cf.contacts.model.MobileContacts;

public class MobileContactDAOImpl {

    /**
     * Here we create a Singleton pattern .Singleton pattern needs a private constructor.
     * We need Singleton pattern because we face the problem if two users in real time,
     * add at the same time two thread instances.So we face the problem in array/database to insert
     * two instances in the same cell
     * */

    //it means that array objects could change only with Java API
    private static final MobileContactDAOImpl DAO= new MobileContactDAOImpl();

    //This class should not  be directly instantiated
    private MobileContactDAOImpl(){}

    public static MobileContactDAOImpl getInstance(){
        return DAO;
    }

    private static final int ARRAY_SIZE=500;
    //we create a contacts array which is connected with the model MobileContacts
    private final MobileContacts[] contacts=new MobileContacts[ARRAY_SIZE];
    private int pivot=-1;


    // CRUD API

    private int getPosition(String phoneNumber){
        if (phoneNumber==null){
            return -1;
        }

        for (int i=0;i<=pivot;i++){
            //contacts[i] is Mobile Contact . So I can take getters
            if(contacts[i].getPhoneNumber().equals(phoneNumber)){
                return i;
            }
        }
        return -1;
    }

    public MobileContacts getOneContactPossibleNull(String phoneNumber){
        //We see if this phone number is in the contact list
        int position=getPosition(phoneNumber);
        return (position!=-1)?contacts[position]:null;
    }

    public boolean insert(MobileContacts contact){
        if (contact==null) return false;
        int position=getPosition(contact.getPhoneNumber());

        //if contact already exist
        if (position!=-1)return false;

        //if contact not exists then do insert
        contacts[++pivot]=contact;
        return true;
    }

    public boolean update(String phoneNumber,MobileContacts newContact){
        if ((phoneNumber==null)||(newContact==null))return false;
        int positionToUpdate=getPosition(phoneNumber);

        //Contact based on phone number not found
        if (positionToUpdate==-1)return false;

        contacts[positionToUpdate]=newContact;
        return true;
    }

    public boolean delete(String phoneNumber){
        int positionToDelete;
        if (phoneNumber==null)return false;

        positionToDelete=getPosition(phoneNumber);
        //if contact not found
        if (positionToDelete == -1)return false;

        System.arraycopy(contacts,positionToDelete + 1, contacts ,positionToDelete,pivot-positionToDelete);
        pivot--;
        return true;
    }





}
