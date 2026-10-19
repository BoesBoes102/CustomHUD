package com.minenash.customhud.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.minenash.customhud.ProfileManager;
import com.minenash.customhud.data.Crosshairs;
import com.minenash.customhud.data.Profile;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static com.minenash.customhud.CustomHud.CLIENT;

@Mixin(GameRenderer.class)
public class GameRendererMixin {

    // 26.2 split GUI extraction from rendering, so the old render-time scaling hook no longer exists.
    // Keep this optional until HUD scaling is moved to the new render-state pipeline.
    @WrapOperation(method = "render", require = 0, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;render(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/client/DeltaTracker;)V"))
    public void changeHudGuiScale(Gui instance, GuiGraphicsExtractor context, DeltaTracker tickCounter, Operation<Void> original) {
        Profile p = ProfileManager.getActive();
        if (p == null || p.baseTheme.hudScale == null) {
            original.call(instance, context, tickCounter);
            return;
        }

        int originalScale = CLIENT.getWindow().getGuiScale();
        int target = p.baseTheme.getTargetGuiScale();
        float scale = (float) (target/originalScale);
        CLIENT.getWindow().setGuiScale(target);

        context.pose().pushMatrix();
        context.pose().scale(scale, scale);
        original.call(instance, context, tickCounter);
        context.pose().popMatrix();

        CLIENT.getWindow().setGuiScale(originalScale);

    }

    @ModifyExpressionValue(method = "renderLevel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntryList;isCurrentlyEnabled(Lnet/minecraft/resources/Identifier;)Z"), require = 0)
    private boolean getDebugCrosshairEnable(boolean original) {
        return original || (ProfileManager.getActive() != null && ProfileManager.getActive().crosshair == Crosshairs.DEBUG);
    }


}
