package com.minenash.customhud.mixin.music;

import com.minenash.customhud.complex.MusicAndRecordTracker;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.item.JukeboxSong;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LevelEventHandler.class)
public class WorldRendererMixin {

    @Shadow @Final private Map<BlockPos, SoundInstance> playingJukeboxSongs;

    @Inject(method = "playJukeboxSong", at = @At("TAIL"))
    private void getRecord(Holder<JukeboxSong> song, BlockPos songPosition, CallbackInfo ci) {
        MusicAndRecordTracker.setRecord(song, playingJukeboxSongs.get(songPosition), songPosition);
    }

}
