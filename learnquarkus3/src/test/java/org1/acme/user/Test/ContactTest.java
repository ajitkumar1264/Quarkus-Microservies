package org1.acme.user.Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ContactTest {

    @Test
    @DisplayName("contact Add Test")
    public void TestCreateUser() {
        Contact newContact = new Contact();
        newContact.addContact("ajitkumar", "ajit@gmail.com");
        Assertions.assertFalse(newContact.getContacts().isEmpty());
        Assertions.assertEquals(1, newContact.getContacts().size());
    }

}
