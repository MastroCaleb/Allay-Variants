package caleb.allay.variants.data;

import caleb.allay.variants.interfaces.AllayVariant;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandler;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.network.PacketByteBuf;


/*
 * The AllayDataTracker class helps us register a DataTracker for the AllayVariant enum value given to the entity.
 * This is strictly necessary, as otherwise we would not see the right variant, just the LIGHT_BLUE vanilla texture.
 * Also makes it so that i can insert the name of the enum value to summon a colored allay using nbts.
 */
public class AllayDataTracker implements TrackedDataHandler<AllayVariant> {
    public static final AllayDataTracker ALLAY_VARIANT_TRACKER_ENUM = new AllayDataTracker();
    public static TrackedData<AllayVariant> ALLAY_VARIANT = DataTracker.registerData(AllayEntity.class, ALLAY_VARIANT_TRACKER_ENUM);

    @Override
    public void write(PacketByteBuf buf, AllayVariant value) {
        buf.writeEnumConstant(value);
    }

    @Override
    public AllayVariant read(PacketByteBuf buf) {
        return buf.readEnumConstant(AllayVariant.class);
    }

    @Override
    public AllayVariant copy(AllayVariant value) {
        return AllayVariant.valueOf(value.name());
    }
}
