package gr.vk.cf.contacts.model;
/**
 * Defines a mobile contact Java Bean
 *
 * @author vkara
 * @version 0.1
 * */

public class MobileContacts {
    private String firstname;
    private String lastname;
    private String phoneNumber;


    public MobileContacts(){}

    public MobileContacts(String firstname, String lastname, String phoneNumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phoneNumber = phoneNumber;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String convertToString(){
        return "{"+firstname+"/t"+lastname+"/t"+phoneNumber+"}";
    }
}
