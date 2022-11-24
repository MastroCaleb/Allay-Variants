package caleb.allay.variants.mixin;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import caleb.allay.variants.ColoredAllaysMod;
import caleb.allay.variants.interfaces.AllayVariant;
import caleb.allay.variants.interfaces.AllayVariantInterface;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.brain.Brain;
import net.minecraft.entity.ai.brain.MemoryModuleType;
import net.minecraft.entity.passive.AllayBrain;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.GlobalPos;
import net.minecraft.world.World;

/*
 * Here we change how the brain of the entity works.
 */
@Mixin(AllayBrain.class)
public class AllayBrainMixin {

    @Overwrite
    private static boolean shouldGoTowardsNoteBlock(LivingEntity allay, Brain<?> brain, GlobalPos pos) {
        Optional<Integer> optional = brain.getOptionalMemory(MemoryModuleType.LIKED_NOTEBLOCK_COOLDOWN_TICKS);
        World world = allay.getWorld();
        AllayVariant variant = ((AllayVariantInterface)allay).getVariant();
        Block block;
        if(variant.equals(AllayVariant.WHITE)){
            block = ColoredAllaysMod.WHITE_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.ORANGE)){
            block = ColoredAllaysMod.ORANGE_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.MAGENTA)){
            block = ColoredAllaysMod.MAGENTA_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.LIGHT_BLUE)){
            block = ColoredAllaysMod.LIGHT_BLUE_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.YELLOW)){
            block = ColoredAllaysMod.YELLOW_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.LIME)){
            block = ColoredAllaysMod.LIME_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.PINK)){
            block = ColoredAllaysMod.PINK_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.LIGHT_GRAY)){
            block = ColoredAllaysMod.LIGHT_GRAY_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.GRAY)){
            block = ColoredAllaysMod.GRAY_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.CYAN)){
            block = ColoredAllaysMod.CYAN_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.PURPLE)){
            block = ColoredAllaysMod.PURPLE_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.BLUE)){
            block = ColoredAllaysMod.BLUE_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.BROWN)){
            block = ColoredAllaysMod.BROWN_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.GREEN)){
            block = ColoredAllaysMod.GREEN_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.RED)){
            block = ColoredAllaysMod.RED_NOTE_BLOCK;
        }
        else if(variant.equals(AllayVariant.BLACK)){
            block = ColoredAllaysMod.BLACK_NOTE_BLOCK;
        }
        else{
            block = Blocks.NOTE_BLOCK;
        }
        return world.getRegistryKey() == pos.getDimension() && world.getBlockState(pos.getPos()).isOf(block) && optional.isPresent();
    }

    @Overwrite
    public static Optional<ServerPlayerEntity> getLikedPlayer(LivingEntity allay) {
        World world = allay.getWorld();
        if (!world.isClient() && world instanceof ServerWorld) {
            ServerWorld serverWorld = (ServerWorld)world;
            Optional<UUID> optional = allay.getBrain().getOptionalMemory(MemoryModuleType.LIKED_PLAYER);
            if (optional.isPresent()) {
                Entity entity = serverWorld.getEntity(optional.get());
                if (entity instanceof ServerPlayerEntity) {
                    ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)entity;
                    if ((serverPlayerEntity.interactionManager.isSurvivalLike() || serverPlayerEntity.interactionManager.isCreative()) && serverPlayerEntity.isInRange(allay, 64.0) && ((AllayVariantInterface)allay).getGoToPlayer()) {
                        return Optional.of(serverPlayerEntity);
                    }
                }
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
    
}
