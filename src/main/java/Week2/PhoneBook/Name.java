package Week2.PhoneBook;

public class Name {
    String preFix;

    String firstName;

    String lastName;

    public Name(String preFix, String firstName, String lastName) {
        this.preFix = preFix;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getPreFix() {
        return preFix;
    }

    public void setPreFix(String preFix) {
        this.preFix = preFix;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

