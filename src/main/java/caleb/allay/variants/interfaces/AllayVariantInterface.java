package caleb.allay.variants.interfaces;

/*
 * This interface is implemented in the AllayVariantsMixin, where we add the code for variants in the entity class.
 * It's used to call the following methods without having to cast to the mixin class.
 */
public interface AllayVariantInterface {
    void setVariant(AllayVariant variant);
    AllayVariant getVariant();
    void setGoToPlayer(boolean bool);
    boolean getGoToPlayer();
}
