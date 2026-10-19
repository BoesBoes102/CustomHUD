package com.minenash.customhud.HudElements.icon;

import com.minenash.customhud.data.Flags;
import com.minenash.customhud.render.RenderPiece;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.PlayerFaceExtractor;
import net.minecraft.client.renderer.entity.player.AvatarRenderer;

import static com.minenash.customhud.CustomHud.CLIENT;

public class PlayerHeadIconElement extends IconElement {

    public PlayerHeadIconElement(Flags flags) {
        super(flags, 10);
    }

    @Override
    public void render(GuiGraphicsExtractor context, RenderPiece piece) {
        context.pose().pushMatrix();
        float y = piece.y;
        if (!referenceCorner)
            y -= (10*scale-10)/2;

        boolean hat = CLIENT.player != null && CLIENT.getConnection().getPlayerInfo(CLIENT.player.getUUID()).showHat();
        context.pose().translate(piece.x + scale + shiftX, y + shiftY);
        int size = (int)(8*scale);
        rotate(context.pose(), size, size);
        PlayerFaceExtractor.extractRenderState(context, CLIENT.player.getSkin(), 0, 0, size, hat ? -1 : 0);
        context.pose().popMatrix();
    }

}
