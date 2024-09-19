package org1.acme.user.Test;

import java.util.ArrayList;
import java.util.List;

public class Contact {

    public String name;
    public String emailId;

    List<Contact> contact=new ArrayList<>();

    public void addContact(String name,String emailId)
    {
        Contact newContact = new Contact();
        newContact.name = name;
        newContact.emailId = emailId;
        contact.add(newContact);
    }


    public List<Contact> getContacts()
    {
        return contact;
    }


}
