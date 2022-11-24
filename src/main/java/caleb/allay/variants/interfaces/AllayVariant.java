package caleb.allay.variants.interfaces;

import java.util.Arrays;
import java.util.Comparator;

import net.minecraft.util.Util;
import net.minecraft.util.math.random.Random;

/*
 * This is the Enum where every variant is stored.
 * I plan on adding other variants in the future, they will not have a new Note Block (they'll use the vanilla one)
 * and will be just purely cosmetic.
 * Also holds some methods i used as debug, like the getRandom() method which returns a random variant
 */
public enum AllayVariant {

    WHITE(0, "white"),
    ORANGE(1, "orange"),
    MAGENTA(2, "magenta"),
    LIGHT_BLUE(3, "light_blue"),
    YELLOW(4, "yellow"),
    LIME(5, "lime"),
    PINK(6, "pink"),
    GRAY(7, "gray"),
    LIGHT_GRAY(8, "light_gray"),
    CYAN(9, "cyan"),
    PURPLE(10, "purple"),
    BLUE(11, "blue"),
    BROWN(12, "brown"),
    GREEN(13, "green"),
    RED(14, "red"),
    BLACK(15, "black");

    public static final AllayVariant[] VARIANTS;
    private final int id;
    private final String name;

    private AllayVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static AllayVariant fromId(int id){
        for(int i=0;i<VARIANTS.length;i++){
            if(VARIANTS[i].getId() == id){
                return VARIANTS[i];
            }
        }
        return null;
    }

    public String getName() {
        return this.name;
    }

    public static AllayVariant getRandom(Random random) {
        AllayVariant[] variants = (AllayVariant[])Arrays.stream(VARIANTS).toArray(AllayVariant[]::new);
        return Util.getRandom(variants, random);
    }

    static {
        VARIANTS = (AllayVariant[])Arrays.stream(AllayVariant.values()).sorted(Comparator.comparingInt(AllayVariant::getId)).toArray(AllayVariant[]::new);
    }
}
