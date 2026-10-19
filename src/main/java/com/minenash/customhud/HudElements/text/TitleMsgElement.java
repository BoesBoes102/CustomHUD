package com.minenash.customhud.HudElements.text;

import com.minenash.customhud.data.Flags;
import java.util.function.Supplier;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Mth;

import static com.minenash.customhud.CustomHud.CLIENT;

public class TitleMsgElement extends TextSupplierElement {
    public TitleMsgElement(Supplier<Component> supplier, Flags flags) { super(supplier, flags); }

    @Override
    public int getColor(int current) {
        int l = 0;
        if (CLIENT.gui.hud.titleTime > CLIENT.gui.hud.titleFadeOutTime + CLIENT.gui.hud.titleStayTime) {
            float o = (float)(CLIENT.gui.hud.titleFadeInTime + CLIENT.gui.hud.titleStayTime + CLIENT.gui.hud.titleFadeOutTime) - CLIENT.gui.hud.titleTime;
            l = (int)(o * 255.0F / CLIENT.gui.hud.titleFadeInTime);
        }

        if (CLIENT.gui.hud.titleTime <= CLIENT.gui.hud.titleFadeOutTime)
            l = (int)(CLIENT.gui.hud.titleTime * 255.0F / CLIENT.gui.hud.titleFadeOutTime);

        return (current & 0xFFFFFF) | Mth.clamp(l, 0, 255) << 24 & 0xFF000000;
    }
}
