package Game;

public class MutatorFactory {

    public static Mutator createRandomMutator() {
        int index = Main.rng.nextInt(MutatorT.values().length);
        MutatorT type = MutatorT.values()[index];
        return createByType(type);
    }

    public static Mutator createByType(MutatorT type) {
        return new Mutator(type);
    }
}
