package net.fabricmc.example.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public class ChangeAirMechanicsLivingEntityMixin {
  /* abstract class LivingEntityMixin {
    @Redirect(method = "getNextAirUnderwater",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;"))
    private Integer changereturn(int configureable) {
        return i > 0 && this.random.nextInt(i + 1) > 0 ? air : air - configureable;
    }
} */







  	@Inject(method = "getNextAirUnderwater()I", at = @At("RETURN"), cancellable = true)
private void changereturnvaluegetNextAirUnderwater(CallbackInfoReturnable<Integer> value) {
  int additionaldrainticks = 1;
  int airbeforeadditionaldrain = value.getReturnValue();
  int airafteradditionaldrain = airbeforeadditionaldrain - additionaldrainticks;
  //System.out.println("[AIR BEFORE DRAIN]: " + airbeforeadditionaldrain);
  //System.out.println("[AIR AFTER DRAIN]: " + airafteradditionaldrain);
  if (airafteradditionaldrain < -20) {
    airafteradditionaldrain = -20;
  }
  if (airbeforeadditionaldrain <= 0) {
    value.setReturnValue(airbeforeadditionaldrain);
  } else {
    value.setReturnValue(airafteradditionaldrain);
  }
  
}

@Inject(method = "getNextAirUnderwater()I", at = @At("RETURN"), cancellable = true)
private void changereturnintovariable(CallbackInfoReturnable<Integer> value) {
  int additionaldrainticks = 1;
  int airbeforeadditionaldrain = value.getReturnValue();
  int airafteradditionaldrain = airbeforeadditionaldrain - additionaldrainticks;
  //System.out.println("[AIR BEFORE DRAIN]: " + airbeforeadditionaldrain);
  //System.out.println("[AIR AFTER DRAIN]: " + airafteradditionaldrain);
  if (airafteradditionaldrain < -20) {
    airafteradditionaldrain = -20;
  }
  if (airbeforeadditionaldrain <= 0) {
    value.setReturnValue(airbeforeadditionaldrain);
  } else {
    value.setReturnValue(airafteradditionaldrain);
  }
  
}
/*
@Inject(method = "getNextAirUnderwater", at = @At("TAIL"), cancellable = true)
private void changemethod(CallbackInfoReturnable info) {
  int newair = i > 0 && this.random.nextInt(i + 1) > 0 ? air : air - 2;
} */





}
