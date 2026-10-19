package com.minenash.customhud.mixin.accessors;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Map;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;

@Mixin(AttributeMap.class)
public interface AttributeContainerAccessor {

    @Accessor Map<Holder<Attribute>, AttributeInstance> getAttributes();
    @Accessor AttributeSupplier getSupplier();

}
