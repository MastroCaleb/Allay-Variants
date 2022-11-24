package caleb.allay.variants.mixin;

import javax.annotation.Nullable;

import org.spongepowered.asm.mixin.Mixin;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import caleb.allay.variants.data.AllayDataTracker;
import caleb.allay.variants.interfaces.AllayVariant;
import caleb.allay.variants.interfaces.AllayVariantInterface;
/*
 * Leftover from debugging the mod,
 * Should add the nbt "Variant" to any old Allay in the world.
 */
@Mixin(MobEntity.class)
public class MobInitMixin {

    @Inject(at = @At("HEAD"), method = "initialize")
    public void initializeMixin(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData, @Nullable NbtCompound entityNbt, CallbackInfoReturnable<EntityData> inf) {
        MobEntity mob = (MobEntity)(Object)this;
        if(mob instanceof AllayEntity allay){
            if(entityNbt != null && !entityNbt.contains("Variant")){
                entityNbt.putString("Variant", allay.getDataTracker().get(AllayDataTracker.ALLAY_VARIANT).name());
                ((AllayVariantInterface)allay).setVariant(AllayVariant.LIGHT_BLUE);
            }
        }
    }
}