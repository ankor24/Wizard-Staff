package mod.vemerion.wizardstaff.Magic.spellbookupdate;

import mod.vemerion.wizardstaff.Magic.Magic;
import mod.vemerion.wizardstaff.entity.WizardHatEntity;
import mod.vemerion.wizardstaff.renderer.WizardStaffLayer;
import mod.vemerion.wizardstaff.renderer.WizardStaffLayer.RenderThirdPersonMagic;
import mod.vemerion.wizardstaff.renderer.WizardStaffTileEntityRenderer;
import mod.vemerion.wizardstaff.renderer.WizardStaffTileEntityRenderer.RenderFirstPersonMagic;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.UseAction;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class WizardHatThrowMagic extends Magic {

	public WizardHatThrowMagic(String name) {
		super(name);
	}

	@Override
	public RenderFirstPersonMagic firstPersonRenderer() {
		return WizardStaffTileEntityRenderer::forward;
	}

	@Override
	public RenderThirdPersonMagic thirdPersonRenderer() {
		return WizardStaffLayer::forwardShake;
	}

	@Override
	public UseAction getUseAction(ItemStack stack) {
		return UseAction.BLOCK;
	}

	@Override
	public ItemStack magicFinish(World world, PlayerEntity player, ItemStack staff) {
		if (!world.isRemote) {
			cost(player);
			Vector3d direction = Vector3d.fromPitchYaw(player.getPitchYaw());
			Vector3d position = player.getPositionVec().add(direction.getX() * 1, 1.2, direction.getZ() * 1);
			WizardHatEntity hat = new WizardHatEntity(position.getX(), position.getY(), position.getZ(), world);
			hat.setShooter(player);
			hat.func_234612_a_(player, player.rotationPitch, player.rotationYaw, 0, 1f, 0); // shoot()
			world.addEntity(hat);
		}
		return super.magicFinish(world, player, staff);
	}
}
