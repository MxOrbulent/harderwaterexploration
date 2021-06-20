package net.fabricmc.example.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ChangeAirMechanicsLivingEntityMixin {
	@Inject(method = "getNextAirUnderwater", at = @At("RETURN"), cancellable = true)
private void changemaxair(CallbackInfoReturnable<Integer> value) {
  value.setReturnValue(100);
}





}
