package caleb.allay.variants;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import caleb.allay.variants.data.AllayDataTracker;
import caleb.allay.variants.interfaces.AllayVariant;
import caleb.allay.variants.note_blocks.ColoredNoteBlock;

public class ColoredAllaysMod implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("Allay Variants");

	public static final ColoredNoteBlock BLACK_NOTE_BLOCK = registerNoteBlock(AllayVariant.BLACK);
	public static final ColoredNoteBlock RED_NOTE_BLOCK = registerNoteBlock(AllayVariant.RED);
	public static final ColoredNoteBlock GREEN_NOTE_BLOCK = registerNoteBlock(AllayVariant.GREEN);
	public static final ColoredNoteBlock BLUE_NOTE_BLOCK = registerNoteBlock(AllayVariant.BLUE);
	public static final ColoredNoteBlock BROWN_NOTE_BLOCK = registerNoteBlock(AllayVariant.BROWN);
	public static final ColoredNoteBlock PURPLE_NOTE_BLOCK = registerNoteBlock(AllayVariant.PURPLE);
	public static final ColoredNoteBlock CYAN_NOTE_BLOCK = registerNoteBlock(AllayVariant.CYAN);
	public static final ColoredNoteBlock LIGHT_GRAY_NOTE_BLOCK = registerNoteBlock(AllayVariant.LIGHT_GRAY);
	public static final ColoredNoteBlock GRAY_NOTE_BLOCK = registerNoteBlock(AllayVariant.GRAY);
	public static final ColoredNoteBlock PINK_NOTE_BLOCK = registerNoteBlock(AllayVariant.PINK);
	public static final ColoredNoteBlock LIME_NOTE_BLOCK = registerNoteBlock(AllayVariant.LIME);
	public static final ColoredNoteBlock YELLOW_NOTE_BLOCK = registerNoteBlock(AllayVariant.YELLOW);
	public static final ColoredNoteBlock LIGHT_BLUE_NOTE_BLOCK = registerNoteBlock(AllayVariant.LIGHT_BLUE);
	public static final ColoredNoteBlock MAGENTA_NOTE_BLOCK = registerNoteBlock(AllayVariant.MAGENTA);
	public static final ColoredNoteBlock ORANGE_NOTE_BLOCK = registerNoteBlock(AllayVariant.ORANGE);
	public static final ColoredNoteBlock WHITE_NOTE_BLOCK = registerNoteBlock(AllayVariant.WHITE);

	//Method to easily register NoteBlocks
	private static ColoredNoteBlock registerNoteBlock(AllayVariant allayVariant){
		ColoredNoteBlock block = new ColoredNoteBlock(FabricBlockSettings.copyOf(Blocks.NOTE_BLOCK), allayVariant);
		Registry.register(Registry.BLOCK, new Identifier("allay_variants", allayVariant.getName() + "_note_block"), block);
		Registry.register(Registry.ITEM, new Identifier("allay_variants", allayVariant.getName() + "_note_block"), new BlockItem(block, new Item.Settings().group(ItemGroup.REDSTONE)));
		return block;
	}
	
	@Override
	public void onInitialize() {
		TrackedDataHandlerRegistry.register(AllayDataTracker.ALLAY_VARIANT_TRACKER_ENUM);
		LOGGER.info("TASTE THE RAINBOW!");
	}
}
