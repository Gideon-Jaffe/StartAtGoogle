package Week2.PhoneBook.PhoneBook;

import Utils.RandomNames;

import java.util.ArrayList;
import java.util.List;

public class PhoneBook {
    private final List<Contact> contacts;

    private final String name;

    public PhoneBook(List<Contact> contacts, String name) {
        this.contacts = contacts;
        this.name = name;
    }

    public PhoneBook(String name) {
        this.contacts = new ArrayList<>();
        this.name = name;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public void addContact(Contact newContact) {
        contacts.add(newContact);
    }

    public void createRandomContact(RandomNames randomPrefix, RandomNames randomFirstName, RandomNames randomLastName) {
        contacts.add(Contact.createRandomContact(randomPrefix, randomFirstName, randomLastName));
    }

    public void createContact(String prefix, String firstName, String lastName, String areaCode, int number) {
        contacts.add(new Contact(prefix, firstName, lastName, areaCode, number));
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder(name + "'s PhoneBook:\n");
        for (Contact contact :
                contacts) {
            str.append(contact.toString()).append("\n");
        }
        return str.toString();
    }

    public static void test() {
        Contact.test();
    }
}

