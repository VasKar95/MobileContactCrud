package gr.vk.cf.contacts.Controller;

import gr.vk.cf.contacts.dto.MobileContactDTO;
import gr.vk.cf.contacts.model.MobileContacts;
import gr.vk.cf.contacts.service.MobileContactServiceImpl;

public class MobileContactController {

    //Service instance composition
    MobileContactServiceImpl service=new MobileContactServiceImpl();



    // Controller takes fields from user
    public boolean insertController(String... fields){

        if (fields==null)return false;
        boolean response;

        //get user input and map to dto
        MobileContactDTO dto=constructDTOFromInput(fields);

        //call the service layer and get response
        response=service.insertContact(dto);

        return response;

    }

    public MobileContacts getContactController(String phoneNumber){
        if (phoneNumber==null)return null;
        MobileContacts contact;


        MobileContactDTO dto=constructDTOFromInput("", "", phoneNumber);

        contact=service.getContactOrNull(dto);

        return contact;
    }

    public boolean updateController(String phoneNumber,String... fields){
        if ((phoneNumber==null)||(fields==null))return false;
        boolean response;
    //get input from user and construct dto
        MobileContactDTO dto=constructDTOFromInput(fields);
    //call the service layer
        response=service.updateContact(phoneNumber,dto);

        return  response;
    }

    public boolean deleteController(String phoneNumber){
        if (phoneNumber==null)return false;

        boolean response;
        response=service.deleteContact(phoneNumber);

        return response;

    }

//we did construct a dto
    private MobileContactDTO constructDTOFromInput(String...inputFields){
        MobileContactDTO dto=new MobileContactDTO();
        dto.setFirstname(inputFields[0]);
        dto.setLastname(inputFields[1]);
        dto.setPhoneNumber(inputFields[2]);

        return dto;
    }






}
