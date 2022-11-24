package caleb.allay.variants.note_blocks;

import caleb.allay.variants.interfaces.AllayVariant;
import net.minecraft.block.NoteBlock;

/*
 * Simple ColoredNoteBlock class that extends the already existing NoteBlock
 */
public class ColoredNoteBlock extends NoteBlock{

    private final AllayVariant variant;

    public ColoredNoteBlock(Settings settings, AllayVariant variant) {
        super(settings);
        this.variant = variant;
    }

    public AllayVariant getVariant(){
        return this.variant;
    }
    
}