package space.mxorbulent.fabricmods.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;

import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.PolarBearEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.particle.ParticleTypes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(LivingEntity.class)
public abstract class ChangeAirMechanicsLivingEntityMixin {
  /* abstract class LivingEntityMixin {
    @Redirect(method = "getNextAirUnderwater",
              at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;"))
    private Integer changereturn(int configureable) {
        return i > 0 && this.random.nextInt(i + 1) > 0 ? air : air - configureable;
    }
} */


  @Shadow protected abstract ItemStack getArmorInSlot(EquipmentSlot slot);

  @Redirect(method = "baseTick()V",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMaxAir()I"))
  private int returnMaxAir1(LivingEntity lentity) {
    return switch (this.getArmorInSlot(EquipmentSlot.CHEST).getName().asString()) {
      case "600maxair" -> 600;
      case "450maxair" -> 450;
      case "300maxair" -> 300;
      case "150maxair" -> 150;
      case "100maxair" -> 100;
      default -> 300;
    };
  }

  @Redirect(method = "getNextAirOnLand",
          at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getMaxAir()I"))
  private int returnMaxAir2(LivingEntity lentity) {
    int maxair = switch (this.getArmorInSlot(EquipmentSlot.CHEST).getName().asString()) {
      case "600maxair" -> 600;
      case "450maxair" -> 450;
      case "300maxair" -> 300;
      case "150maxair" -> 150;
      case "100maxair" -> 100;
      default -> 300;
    };
    if (lentity.getAir() > maxair) {
      lentity.setAir(maxair);
    }
    return maxair;


  }

  @Inject(method = "getNextAirUnderwater", at = @At("RETURN"), cancellable = true)
private void changereturnvaluegetNextAirUnderwater(CallbackInfoReturnable<Integer> value) {




    int additionaldrainticks = switch (this.getArmorInSlot(EquipmentSlot.HEAD).getName().asString()) {
      case "defairdrain" -> 0;
      case "+1airdrain" -> 1;
      case "+2airdrain" -> 2;
      case "+3airdrain" -> 3;
      case "+4airdrain" -> 4;
      default -> 0;
    };
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

@Inject(method = "getNextAirOnLand", at = @At("RETURN"), cancellable = true)
private void changereturnvaluegetNextAirOnLand(CallbackInfoReturnable<Integer> value) {
  int returnedvaluebeforemodficiation = value.getReturnValue();
  int numberofairegainticks = switch (this.getArmorInSlot(EquipmentSlot.HEAD).getName().asString()) {
    case "defairregain" -> 3;
    case "2airregain" -> 2;
    case "1airregain" -> 1;
    default -> 3;
  };


  System.out.println("AIR: " +returnedvaluebeforemodficiation);


    value.setReturnValue(returnedvaluebeforemodficiation - numberofairegainticks);

  
}
/*
@Inject(method = "getNextAirUnderwater", at = @At("TAIL"), cancellable = true)
private void changemethod(CallbackInfoReturnable info) {
  int newair = i > 0 && this.random.nextInt(i + 1) > 0 ? air : air - 2;
} */





}
