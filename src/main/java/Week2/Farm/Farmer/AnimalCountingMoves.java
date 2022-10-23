package Week2.Farm.Farmer;

public class AnimalCountingMoves extends AnimalForwarding {

    private int counter = 0;

    public AnimalCountingMoves(Animal myAnimal) {
        super(myAnimal);
    }

    public int getCounter() {
        return counter;
    }

    @Override
    public void move() {
        System.out.println(counter++);
        super.move();
    }
}
