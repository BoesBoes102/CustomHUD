package com.minenash.customhud.mixin.accessors;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.ViewArea;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.state.level.LevelRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Environment(EnvType.CLIENT)
@Mixin(LevelRenderer.class)
public interface WorldRendererAccess {

    @Accessor ViewArea getViewArea();
    @Accessor LevelRenderState getLevelRenderState();

}
