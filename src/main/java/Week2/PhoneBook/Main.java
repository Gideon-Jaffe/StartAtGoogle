package Week2.PhoneBook;

import Utils.DoubleRandomNames;
import Utils.RandomNames;
import Week2.PhoneBook.PhoneBook.PhoneBook;

public class Main {
    public static void main(String[] args) {
        RandomNames rd = new DoubleRandomNames("src/main/resources/first_names.json", "src/main/resources/last_names.json");
        PhoneBook book = new PhoneBook(rd.getRandomNameFromJsonFile());

        RandomNames randomPrefix = new RandomNames("src/main/resources/name_prefix.json");
        RandomNames randomFirstName = new RandomNames("src/main/resources/first_names.json");
        RandomNames randomLastName = new RandomNames("src/main/resources/last_names.json");
        book.createRandomContact(randomPrefix, randomFirstName, randomLastName);
        book.createRandomContact(randomPrefix, randomFirstName, randomLastName);
        book.createRandomContact(randomPrefix, randomFirstName, randomLastName);
        book.createRandomContact(randomPrefix, randomFirstName, randomLastName);
        book.createRandomContact(randomPrefix, randomFirstName, randomLastName);
        System.out.println(book);

        PhoneBook.test();
    }
}
