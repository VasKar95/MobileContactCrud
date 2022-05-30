package gr.vk.cf.contacts.service;

import gr.vk.cf.contacts.dao.MobileContactDAOImpl;
import gr.vk.cf.contacts.dto.MobileContactDTO;
import gr.vk.cf.contacts.model.MobileContacts;

public class MobileContactServiceImpl {
    //DAO composition, I take an instance from DAO
    MobileContactDAOImpl dao=MobileContactDAOImpl.getInstance();


    //public API

    public MobileContacts getContactOrNull(MobileContactDTO dto){
        /**
         * It takes a DTO from user via controller and we see if there is any contact with this DTO
         * */

        if ((dto==null)||(dto.getPhoneNumber()==null))return null;
        //Extract phone number from dto
        String phoneNumber=dto.getPhoneNumber();

        //forwarding to dao and return
        return dao.getOneContactPossibleNull(phoneNumber);

    }

    //DAO needs contacts not dto.So extractFields takes dto fields and add them to mobile contact from model
    private void extractFields(MobileContactDTO dto,MobileContacts contact){
        contact.setFirstname(dto.getFirstname());
        contact.setLastname(dto.getLastname());
        contact.setPhoneNumber(dto.getPhoneNumber());
    }


    //dao do insert entities from model so we have to do extract from dto and give it to model.
    public boolean insertContact(MobileContactDTO dto){
        if (dto==null)return false;
        MobileContacts contact=new MobileContacts() ;
        extractFields(dto,contact);

        //dao insert needs mobile contact as parameter.Dao methods works via Model but sevice takes dto.So i do insert the model
        return dao.insert(contact);
    }

    public boolean updateContact(String phoneNumber,MobileContactDTO dto){
        if ((dto==null)||(phoneNumber==null))return false;
        MobileContacts contact=new MobileContacts();
        extractFields(dto,contact);
        return dao.update(phoneNumber,contact);
    }

    public boolean deleteContact(String phoneNumber){
        if (phoneNumber==null)return false;
        return dao.delete(phoneNumber);
    }
}
