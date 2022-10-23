package Week2.PhoneBook.PhoneBook;

import Utils.RandomNames;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {
    final String AreaCode;

    final int number;

    PhoneNumber(String areaCode, int number) {
        RandomNames randomAreaCodes = new RandomNames("src/main/resources/phone_area_codes.json");
        if (!randomAreaCodes.isNameInJsonFile(areaCode)) throw new RuntimeException("Area code not legal");
        AreaCode = areaCode;
        this.number = number;
    }

    String getAreaCode() {
        return AreaCode;
    }

    int getNumber() {
        return number;
    }

    static PhoneNumber createRandomPhoneNumber() {

        RandomNames randomAreaCodes = new RandomNames("src/main/resources/phone_area_codes.json");
        String areaCode = randomAreaCodes.getRandomNameFromJsonFile();
        int number = ThreadLocalRandom.current().nextInt(10000000);
        return new PhoneNumber(areaCode, number);
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(getAreaCode());
        builder.append("-");
        int tempNum = 1000000;
        while (number < tempNum) {
            builder.append(0);
            tempNum /= 10;
        }
        return builder.append(number).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhoneNumber that = (PhoneNumber) o;
        return number == that.number && Objects.equals(AreaCode, that.AreaCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(AreaCode, number);
    }

    @Override
    protected Object clone() {
        return new PhoneNumber(this.getAreaCode(), this.getNumber());
    }

    @Override
    public int compareTo(PhoneNumber otherNum) {
        int result = this.getAreaCode().compareTo(otherNum.getAreaCode());
        if (result == 0) {
            return Integer.compare(this.number, otherNum.number);
        } else {
            return result;
        }
    }

    static void test() {
        System.out.println("----------------PhoneNumber Testing-------------");
        PhoneNumber num1 = new PhoneNumber("052", 6604651);
        PhoneNumber num2 = new PhoneNumber("052", 6604651);
        System.out.println(num1 + " hash code:" + num1.hashCode());
        System.out.println(num2 + " hash code:" + num2.hashCode());
        System.out.println("equals: ");
        if (num1.equals(num2)) {
            System.out.println("They Equal");
        } else {
            System.out.println("They do not Equal");
        }
        System.out.println("Clone: ");
        PhoneNumber num3 = (PhoneNumber) num1.clone();
        System.out.println(num3 + " hash code:" + num3.hashCode());

        PhoneNumber num4 = new PhoneNumber("051", 8654321);
        System.out.println("compareTo: ");
        System.out.println(num4);
        if (num4.compareTo(num1) < 0) {
            System.out.println("Smaller then original number");
        } else if(num4.compareTo(num1) > 0) {
            System.out.println("Larger then original number");
        } else {
            System.out.println("equal to original number");
        }
    }
}
