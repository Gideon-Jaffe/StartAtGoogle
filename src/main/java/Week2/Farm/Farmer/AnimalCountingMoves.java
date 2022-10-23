package Week2.Farm.Farmer;

class AnimalCountingMoves extends AnimalForwarding {

    private int counter = 0;

    AnimalCountingMoves(Animal myAnimal) {
        super(myAnimal);
    }

    int getCounter() {
        return counter;
    }

    void resetCounter() {
        counter = 0;
    }

    @Override
    public void move() {
        System.out.println(counter++);
        super.move();
    }
}
