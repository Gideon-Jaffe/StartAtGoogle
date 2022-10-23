package Week2.PhoneBook.PhoneBook;

import Utils.RandomNames;

import java.util.Objects;

class Contact implements Cloneable, Comparable<Contact> {
     private final Name name;

    private final PhoneNumber phoneNumber;

    Contact(Name name, PhoneNumber phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    Contact(String prefix, String firstName, String lastName, String areaCode, int number) {
        this.name = new Name(prefix, firstName, lastName);
        this.phoneNumber = new PhoneNumber(areaCode, number);
    }

    static Contact createRandomContact(RandomNames randomPrefixGenerator, RandomNames randomFirstNameGenerator, RandomNames randomLastNameGenerator) {
        Name returnName = Name.createRandomName(randomPrefixGenerator, randomFirstNameGenerator, randomLastNameGenerator);
        return new Contact(returnName, PhoneNumber.createRandomPhoneNumber());
    }

    Name getName() {
        return name;
    }

    PhoneNumber getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return name.toString() + " " + phoneNumber.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(name, contact.name) && Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phoneNumber);
    }

    @Override
    protected Object clone() {
        return new Contact((Name)this.name.clone(), (PhoneNumber) this.phoneNumber.clone());
    }

    @Override
    public int compareTo(Contact o) {
        int result = this.name.compareTo(o.name);
        if (result == 0) {
            return this.phoneNumber.compareTo(o.phoneNumber);
        } else {
            return result;
        }
    }

    static void test() {
        Name.test();
        PhoneNumber.test();

        System.out.println("----------------Contact Testing-------------");
        Contact con1 = new Contact(new Name("Mr.", "hello", "phonebook"), new PhoneNumber("052", 6604651));
        Contact con2 = new Contact(new Name("Mr.", "hello", "world"), new PhoneNumber("051", 6604651));
        System.out.println(con1 + " hash code:" + con1.hashCode());
        System.out.println(con2 + " hash code:" + con2.hashCode());
        System.out.println("equals: ");
        if (con1.equals(con2)) {
            System.out.println("They Equal");
        } else {
            System.out.println("They do not Equal");
        }
        System.out.println("Clone: ");
        Contact con3 = (Contact) con1.clone();
        System.out.println(con3 + " hash code:" + con3.hashCode());

        Contact con4 = new Contact(new Name("Mr.", "hello", "world"), new PhoneNumber("051", 6604651));
        System.out.println("compareTo: ");
        System.out.println(con4);
        if (con4.compareTo(con1) < 0) {
            System.out.println("Smaller then original number");
        } else if(con4.compareTo(con1) > 0) {
            System.out.println("Larger then original number");
        } else {
            System.out.println("equal to original number");
        }
    }
}
