package space.mxorbulent.fabricmods.mixin;

import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.entity.Entity;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Iterator;

@Mixin(Entity.class)
public abstract class ChangeAirStatsEntityMixin {


    @Shadow public abstract Iterable<ItemStack> getArmorItems();
    @Inject(method = "getMaxAir", at = @At("RETURN"), cancellable = true)
private void changemaxair(CallbackInfoReturnable<Integer> value) {

        int maxair = 2000;
        //Iterable<ItemStack> shit = this.getArmorItems();


        /*if (shit != null) {
            for (ItemStack itemStack : shit) {
                Item item = itemStack.getItem();
                if (item instanceof ArmorItem armoritem) {

                    if (armoritem.getSlotType().equals(EquipmentSlot.CHEST)) {
                        if (armoritem.getName().asString().equals("600maxair")) {
                            maxair = 600;
                        } else if (armoritem.getName().asString().equals("450maxair")) {
                            maxair = 450;
                        } else if (armoritem.getName().asString().equals("300maxair")) {
                            maxair = 300;
                        } else if (armoritem.getName().asString().equals("150maxair")) {
                            maxair = 150;
                        } else if (armoritem.getName().asString().equals("100maxair")) {
                            maxair = 100;

                        }
                        break;
                    }
                }

            }
        }*/



	    value.setReturnValue(maxair);
}





}
