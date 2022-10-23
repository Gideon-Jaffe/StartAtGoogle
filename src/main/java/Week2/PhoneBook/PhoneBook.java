package Week2.PhoneBook;

import java.util.List;

public class PhoneBook {
    List<Contact> contacts;

    String name;

    public PhoneBook(List<Contact> contacts, String name) {
        this.contacts = contacts;
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

