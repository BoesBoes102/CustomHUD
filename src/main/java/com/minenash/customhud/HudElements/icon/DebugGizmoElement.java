package com.minenash.customhud.HudElements.icon;

import com.minenash.customhud.data.Flags;
import com.minenash.customhud.mixin.accessors.DebugHudAccessor;
import com.minenash.customhud.render.RenderPiece;
import org.joml.Matrix4f;
import org.joml.Matrix4fStack;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.OptionalDouble;
import java.util.OptionalInt;
import net.minecraft.client.Camera;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.util.Mth;

import static com.minenash.customhud.CustomHud.CLIENT;

// No longer works
public class DebugGizmoElement extends IconElement {

    private final float size;

    public DebugGizmoElement(Flags flags) {
        super(flags, 10);
        this.size = (int)(10*scale) / 2F;
    }

    @Override
    public void render(GuiGraphicsExtractor context, RenderPiece piece) {
        // The 3D debug crosshair now renders in the level render pass, not the GUI extraction pass.
    }



}
