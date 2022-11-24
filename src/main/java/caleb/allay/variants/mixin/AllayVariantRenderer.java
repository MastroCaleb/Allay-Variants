package caleb.allay.variants.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import caleb.allay.variants.data.AllayDataTracker;
import caleb.allay.variants.interfaces.AllayVariant;
import net.minecraft.client.render.entity.AllayEntityRenderer;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.util.Identifier;
/*
 * We change the entity renderer to obtain the entity variant and change the texture accordingly
 */
@Mixin(AllayEntityRenderer.class)
public class AllayVariantRenderer{

    @Overwrite
    public Identifier getTexture(AllayEntity allayEntity){
        AllayVariant variant = allayEntity.getDataTracker().get(AllayDataTracker.ALLAY_VARIANT);
        return this.setVariant(variant);
    }

    private Identifier setVariant(AllayVariant variant){
        return new Identifier("allay_variants", "textures/entity/allay/" + variant.getName() +".png");
    }
}