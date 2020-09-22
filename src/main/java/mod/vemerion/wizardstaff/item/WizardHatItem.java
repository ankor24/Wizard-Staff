package mod.vemerion.wizardstaff.item;

import mod.vemerion.wizardstaff.Main;
import mod.vemerion.wizardstaff.model.WizardHatModel;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.DyeableArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WizardHatItem extends DyeableArmorItem {
	@OnlyIn(Dist.CLIENT)
	WizardHatModel<?> model;

	public WizardHatItem() {
		super(ArmorMaterial.LEATHER, EquipmentSlotType.HEAD,
				new Item.Properties().maxStackSize(1).group(ItemGroup.COMBAT));
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, EquipmentSlotType slot, String type) {
		if ("overlay".equals(type))
			return Main.MODID + ":textures/armor/wizard_hat_overlay.png";
		else
			return Main.MODID + ":textures/armor/wizard_hat.png";
	}

	@SuppressWarnings("unchecked")
	@OnlyIn(Dist.CLIENT)
	@Override
	public <A extends BipedModel<?>> A getArmorModel(LivingEntity entityLiving, ItemStack itemStack,
			EquipmentSlotType armorSlot, A _default) {
		if (model == null) {
			model = new WizardHatModel<>();
			model.bipedHead.showModel = true;
		}

		model.isSitting = _default.isSitting;
		model.isSneak = _default.isSneak;
		model.isChild = _default.isChild;
		model.rightArmPose = _default.rightArmPose;
		model.leftArmPose = _default.leftArmPose;

		return (A) model;
	}

}