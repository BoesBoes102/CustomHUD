package com.minenash.customhud.mixin;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import com.minenash.customhud.ProfileManager;
import com.minenash.customhud.data.Crosshairs;
import net.minecraft.client.renderer.extract.LevelExtractor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LevelExtractor.class)
public class LevelExtractorMixin {

    @ModifyExpressionValue(method = "extract", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/debug/DebugScreenEntryList;isCurrentlyEnabled(Lnet/minecraft/resources/Identifier;)Z"))
    private boolean getDebugCrosshairEnable(boolean original) {
        return original || (ProfileManager.getActive() != null && ProfileManager.getActive().crosshair == Crosshairs.DEBUG);
    }

}
