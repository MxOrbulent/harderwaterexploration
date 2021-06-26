package space.mxorbulent.fabricmods.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class ChangeSwimSpeedMechanicsLivingEntityMixin {
  @Shadow protected abstract ItemStack getArmorInSlot(EquipmentSlot slot);

  //this.updateVelocity(g, movementInput);
  @ModifyArg(method = "travel(Lnet/minecraft/util/math/Vec3d;)V", at = @At(args = {"log=true"}, value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;updateVelocity(FLnet/minecraft/util/math/Vec3d;)V"), index = 0)


  private float injected(float g) {
    float f = switch (this.getArmorInSlot(EquipmentSlot.FEET).getName().asString()) {
      case "+10swimspeed" -> 1.10F;
      case "defswimspeed" -> 1F;
      case "-10swimspeed" -> 0.9F;
      case "-15swimspeed" -> 0.85F;
      case "-25swimspeed" -> 0.75F;
      default -> 1F;
    };
    return g * f;
  }





}
