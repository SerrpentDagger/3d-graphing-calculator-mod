package graphingcalculator3d.common.gameplay.blocks;

import java.util.ArrayList;

import graphingcalculator3d.common.GraphingCalculator3D;
import net.minecraft.block.Block;
import net.minecraftforge.fml.common.registry.GameRegistry.ObjectHolder;

@ObjectHolder(GraphingCalculator3D.MODID)
public class GCBlocks
{
	public static ArrayList<Block> gcBlockList = new ArrayList<Block>();
	
	///////////////////////////////////////////////////////////
	
	public static final BlockGC block_gc_cartesian = null;
	public static final BlockGC block_gc_spherical = null;
	public static final BlockGC block_gc_spherical_theta = null;
	public static final BlockGC block_gc_spherical_phi = null;
	public static final BlockGC block_gc_cylindrical = null;
	public static final BlockGC block_gc_cylindrical_h = null;
	public static final BlockGC block_gc_cylindrical_theta = null;
	public static final BlockGC block_gc_parabolic_cylindrical = null;
	public static final BlockGC block_gc_parabolic_cylindrical_sigma = null;
	public static final BlockGC block_gc_parabolic_cylindrical_tau = null;
	public static final BlockGC block_gc_parabolic = null;
	public static final BlockGC block_gc_parabolic_tau = null;
	public static final BlockGC block_gc_parabolic_phi = null;
	public static final BlockGC block_gc_bipolar_cylindrical = null;
	public static final BlockGC block_gc_bipolar_cylindrical_sigma = null;
	public static final BlockGC block_gc_bipolar_cylindrical_tau = null;
	public static final BlockGC block_gc_oblate_spheroidal = null;
	public static final BlockGC block_gc_oblate_spheroidal_nu = null;
	public static final BlockGC block_gc_oblate_spheroidal_phi = null;
	public static final BlockGC block_gc_prolate_spheroidal = null;
	public static final BlockGC block_gc_prolate_spheroidal_nu = null;
	public static final BlockGC block_gc_prolate_spheroidal_phi = null;
	public static final BlockGC block_gc_toroidal = null;
	public static final BlockGC block_gc_toroidal_phi = null;
	public static final BlockGC block_gc_toroidal_tau = null;
	public static final BlockGC block_gc_conical = null;
	public static final BlockGC block_gc_conical_mu = null;
	public static final BlockGC block_gc_conical_nu = null;
	public static final BlockGC block_gc_6_sphere = null;
	public static final BlockGC block_gc_6_sphere_v = null;
	public static final BlockGC block_gc_6_sphere_w = null;
	
	public static final Block block_mesh_flat = null;
	public static final Block block_mesh_grid = null;
}
