package Week2.PhoneBook.PhoneBook;

import Utils.RandomNames;

import java.util.Objects;

class Name implements Cloneable, Comparable<Name> {
    String preFix;

    final String firstName;

    final String lastName;

    Name(String preFix, String firstName, String lastName) {
        RandomNames rd = new RandomNames("src/main/resources/name_prefix.json");
        if (rd.isNameInJsonFile(preFix)) {
            this.preFix = preFix;
        } else {
            throw new RuntimeException("Prefix not Allowed");
        }
        this.firstName = firstName;
        this.lastName = lastName;
    }

    static Name createRandomName(RandomNames randomPrefixNameGenerator, RandomNames randomFirstNameGenerator, RandomNames randomLastNameGenerator) {
        String prefix = randomPrefixNameGenerator.getRandomNameFromJsonFile();
        String firstName = randomFirstNameGenerator.getRandomNameFromJsonFile();
        String lastName = randomLastNameGenerator.getRandomNameFromJsonFile();
        return new Name(prefix, firstName, lastName);
    }

    String getPreFix() {
        return preFix;
    }

    void setPreFix(String preFix) {
        RandomNames rd = new RandomNames("name_prefix.json");
        if (rd.isNameInJsonFile(preFix)) {
            this.preFix = preFix;
        } else {
            throw new RuntimeException("Prefix not Allowed");
        }
    }

    String getFirstName() {
        return firstName;
    }

    String getLastName() {
        return lastName;
    }

    @Override
    public String toString() {
        return preFix + " " + firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Name name = (Name) o;
        return Objects.equals(preFix, name.preFix) && Objects.equals(firstName, name.firstName) && Objects.equals(lastName, name.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preFix, firstName, lastName);
    }

    @Override
    protected Object clone() {
        return new Name(this.preFix, this.firstName, this.lastName);
    }

    @Override
    public int compareTo(Name o) {
        int result = this.preFix.compareTo(o.preFix);
        if (result == 0) {
            result = this.firstName.compareTo(o.firstName);
            if (result == 0) {
                return this.lastName.compareTo(o.lastName);
            } else {
                return result;
            }
        } else {
            return result;
        }
    }

    static void test() {
        System.out.println("----------------Name Testing-------------");
        Name name1 = new Name("Mr.", "hello", "phonebook");
        Name name2 = new Name("Mr.", "hello", "phonebook");
        System.out.println(name1 + " hash code:" + name1.hashCode());
        System.out.println(name2 + " hash code:" + name2.hashCode());
        System.out.println("equals: ");
        if (name1.equals(name2)) {
            System.out.println("They Equal");
        } else {
            System.out.println("They do not Equal");
        }
        System.out.println("Clone: ");
        Name name3 = (Name) name1.clone();
        System.out.println(name3 + " hash code:" + name3.hashCode());

        Name name4 = new Name("Mr.", "hello", "world");
        System.out.println("compareTo: ");
        System.out.println(name4);
        if (name4.compareTo(name1) < 0) {
            System.out.println("Smaller then original name");
        } else if(name4.compareTo(name1) > 0) {
            System.out.println("Larger then original name");
        } else {
            System.out.println("equal to original name");
        }
    }
}

