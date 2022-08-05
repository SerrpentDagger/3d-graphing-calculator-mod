package graphingcalculator3d.common.util.events.register;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.gameplay.tile.TileGC6Sphere;
import graphingcalculator3d.common.gameplay.tile.TileGC6SphereV;
import graphingcalculator3d.common.gameplay.tile.TileGC6SphereW;
import graphingcalculator3d.common.gameplay.tile.TileGCBipolarCylindrical;
import graphingcalculator3d.common.gameplay.tile.TileGCBipolarCylindricalSigma;
import graphingcalculator3d.common.gameplay.tile.TileGCBipolarCylindricalTau;
import graphingcalculator3d.common.gameplay.tile.TileGCCartesian;
import graphingcalculator3d.common.gameplay.tile.TileGCConical;
import graphingcalculator3d.common.gameplay.tile.TileGCConicalMu;
import graphingcalculator3d.common.gameplay.tile.TileGCConicalNu;
import graphingcalculator3d.common.gameplay.tile.TileGCCylindrical;
import graphingcalculator3d.common.gameplay.tile.TileGCCylindricalH;
import graphingcalculator3d.common.gameplay.tile.TileGCCylindricalTheta;
import graphingcalculator3d.common.gameplay.tile.TileGCEllipticCylindrical;
import graphingcalculator3d.common.gameplay.tile.TileGCEllipticCylindricalMu;
import graphingcalculator3d.common.gameplay.tile.TileGCEllipticCylindricalNu;
import graphingcalculator3d.common.gameplay.tile.TileGCOblateSpheroidal;
import graphingcalculator3d.common.gameplay.tile.TileGCOblateSpheroidalNu;
import graphingcalculator3d.common.gameplay.tile.TileGCOblateSpheroidalPhi;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolic;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolicCylindrical;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolicCylindricalSigma;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolicCylindricalTau;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolicTau;
import graphingcalculator3d.common.gameplay.tile.TileGCParabolicPhi;
import graphingcalculator3d.common.gameplay.tile.TileGCProlateSpheroidal;
import graphingcalculator3d.common.gameplay.tile.TileGCProlateSpheroidalNu;
import graphingcalculator3d.common.gameplay.tile.TileGCProlateSpheroidalPhi;
import graphingcalculator3d.common.gameplay.tile.TileGCSpherical;
import graphingcalculator3d.common.gameplay.tile.TileGCSphericalPhi;
import graphingcalculator3d.common.gameplay.tile.TileGCSphericalTheta;
import graphingcalculator3d.common.gameplay.tile.TileGCToroidal;
import graphingcalculator3d.common.gameplay.tile.TileGCToroidalPhi;
import graphingcalculator3d.common.gameplay.tile.TileGCToroidalTau;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class RegisterTileEntities
{
	public static void registerTiles()
	{
		GameRegistry.registerTileEntity(TileGCCartesian.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_cartesian"));
		GameRegistry.registerTileEntity(TileGCSpherical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_spherical"));
		GameRegistry.registerTileEntity(TileGCSphericalTheta.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_spherical_theta"));
		GameRegistry.registerTileEntity(TileGCSphericalPhi.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_spherical_phi"));
		GameRegistry.registerTileEntity(TileGCCylindrical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_cylindrical"));
		GameRegistry.registerTileEntity(TileGCCylindricalH.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_cylindrical_h"));
		GameRegistry.registerTileEntity(TileGCCylindricalTheta.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_cylindrical_theta"));
		GameRegistry.registerTileEntity(TileGCParabolicCylindrical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic_cylindrical"));
		GameRegistry.registerTileEntity(TileGCParabolicCylindricalSigma.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic_cylindrical_sigma"));
		GameRegistry.registerTileEntity(TileGCParabolicCylindricalTau.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic_cylindrical_tau"));
		GameRegistry.registerTileEntity(TileGCParabolic.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic"));
		GameRegistry.registerTileEntity(TileGCParabolicTau.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic_tau"));
		GameRegistry.registerTileEntity(TileGCParabolicPhi.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_parabolic_phi"));
		GameRegistry.registerTileEntity(TileGCBipolarCylindrical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_bipolar_cylindrical"));
		GameRegistry.registerTileEntity(TileGCBipolarCylindricalSigma.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_bipolar_cylindrical_sigma"));
		GameRegistry.registerTileEntity(TileGCBipolarCylindricalTau.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_bipolar_cylindrical_tau"));
		GameRegistry.registerTileEntity(TileGCOblateSpheroidal.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_oblate_spheroidal"));
		GameRegistry.registerTileEntity(TileGCOblateSpheroidalNu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_oblate_spheroidal_nu"));
		GameRegistry.registerTileEntity(TileGCOblateSpheroidalPhi.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_oblate_spheroidal_phi"));
		GameRegistry.registerTileEntity(TileGCProlateSpheroidal.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_prolate_spheroidal"));
		GameRegistry.registerTileEntity(TileGCProlateSpheroidalNu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_prolate_spheroidal_nu"));
		GameRegistry.registerTileEntity(TileGCProlateSpheroidalPhi.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_prolate_spheroidal_phi"));
		GameRegistry.registerTileEntity(TileGCToroidal.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_toroidal"));
		GameRegistry.registerTileEntity(TileGCToroidalPhi.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_toroidal_phi"));
		GameRegistry.registerTileEntity(TileGCToroidalTau.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_toroidal_tau"));
		GameRegistry.registerTileEntity(TileGCConical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_conical"));
		GameRegistry.registerTileEntity(TileGCConicalMu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_conical_mu"));
		GameRegistry.registerTileEntity(TileGCConicalNu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_conical_nu"));
		GameRegistry.registerTileEntity(TileGC6Sphere.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_6_sphere"));
		GameRegistry.registerTileEntity(TileGC6SphereV.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_6_sphere_v"));
		GameRegistry.registerTileEntity(TileGC6SphereW.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_6_sphere_w"));
		GameRegistry.registerTileEntity(TileGCEllipticCylindrical.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_elliptic_cylindrical"));
		GameRegistry.registerTileEntity(TileGCEllipticCylindricalMu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_elliptic_cylindrical_mu"));
		GameRegistry.registerTileEntity(TileGCEllipticCylindricalNu.class, new ResourceLocation(GraphingCalculator3D.MODID + ":tile_gc_elliptic_cylindrical_nu"));
	}
}
