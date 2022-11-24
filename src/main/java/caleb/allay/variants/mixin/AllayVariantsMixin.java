package caleb.allay.variants.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.AllayEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.DyeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import caleb.allay.variants.data.AllayDataTracker;
import caleb.allay.variants.interfaces.AllayVariant;
import caleb.allay.variants.interfaces.AllayVariantInterface;

/*
 * We implement every new feature in a mixin to the Entity class that implements the AllayVariantInterface.
 */
@Mixin(AllayEntity.class)
public abstract class AllayVariantsMixin extends PathAwareEntity implements AllayVariantInterface{

	public AllayVariantsMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
		super(entityType, world);
	}

	private static TrackedData<Boolean> GO_TO_PLAYER = DataTracker.registerData(AllayEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

	private static final String VARIANT_KEY = "Variant";
	private static final String GO_TO_PLAYER_KEY = "GoToPlayer";

	@Inject(at = @At("TAIL"), method = "initDataTracker")
	private void initDataTrackerMixin(CallbackInfo info) {
		dataTracker.startTracking(AllayDataTracker.ALLAY_VARIANT, AllayVariant.LIGHT_BLUE);
		dataTracker.startTracking(GO_TO_PLAYER, true);
	}
	
	@Override
	public AllayVariant getVariant() {
        return this.getDataTracker().get(AllayDataTracker.ALLAY_VARIANT);
    }

	@Override
    public void setVariant(AllayVariant variant) {
        this.getDataTracker().set(AllayDataTracker.ALLAY_VARIANT, variant);
    }

	@Override
	public void setGoToPlayer(boolean bool){
		this.getDataTracker().set(GO_TO_PLAYER, bool);
	}

	@Override
	public boolean getGoToPlayer(){
		return this.getDataTracker().get(GO_TO_PLAYER).booleanValue();
	}

	@Inject(at = @At("TAIL"), method = "writeCustomDataToNbt")
	private void writeCustomDataToNbtMixin(NbtCompound nbt, CallbackInfo info) {
		nbt.putString(VARIANT_KEY, this.getDataTracker().get(AllayDataTracker.ALLAY_VARIANT).name());
		nbt.putBoolean(GO_TO_PLAYER_KEY, this.getDataTracker().get(GO_TO_PLAYER).booleanValue());
	}

	@Inject(at = @At("TAIL"), method = "readCustomDataFromNbt")
	private void readCustomDataFromNbtMixin(NbtCompound nbt, CallbackInfo info) {
		if(!nbt.contains(VARIANT_KEY)){
			this.getDataTracker().set(AllayDataTracker.ALLAY_VARIANT, AllayVariant.LIGHT_BLUE);
		}
		this.getDataTracker().set(AllayDataTracker.ALLAY_VARIANT, AllayVariant.valueOf(nbt.getString(VARIANT_KEY)));
		this.getDataTracker().set(GO_TO_PLAYER, nbt.getBoolean(GO_TO_PLAYER_KEY));
	}

	@Inject(at = @At("HEAD"), method = "interactMob", cancellable = true)
	private void interactMobMixin(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> inf){
		ItemStack itemStack = player.getStackInHand(hand);
		if (itemStack.isEmpty() && player.isSneaking()) {
			if(!this.getGoToPlayer()){
				player.sendMessage(Text.of("Now drops items to you."), true);
            	this.setGoToPlayer(true);
			}
			else{
				player.sendMessage(Text.of("Now doesn't drop items to you."), true);
            	this.setGoToPlayer(false);
			}
            inf.setReturnValue(ActionResult.SUCCESS);
        }
		if (itemStack.getItem() instanceof DyeItem item && player.isSneaking()) {
			this.setVariant(this.fromDye(item.getColor()));
            inf.setReturnValue(ActionResult.SUCCESS);
        }
	}

	private AllayVariant fromDye(DyeColor color){
		return AllayVariant.fromId(color.getId());
	}
}
