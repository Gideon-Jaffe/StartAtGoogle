package Week2.EnumExercise;

public enum Birds {
    ROCK_DOVE("Columba livia"){
        @Override
        public void sing() {
            System.out.println("rolling \"coo\"");
        }
    },
    PURPLE_MARTIN("Progne subis"){
        @Override
        public void sing() {
            System.out.println("tchew-wew, pew pew, choo, cher, zweet zwack");
        }
    },
    STARLING("Sturnus Vulgaris"){
        @Override
        public void sing() {
            System.out.println("wolf whistle");
        }
    },
    SNIPE("Gallinago gallinago"){
        @Override
        public void sing() {
            System.out.println("wicka-wicka-wicka");
        }
    };

    Birds(String scientificName) {
        this.scientificName = scientificName;
    }

    public final String scientificName;

    public abstract void sing();
}
