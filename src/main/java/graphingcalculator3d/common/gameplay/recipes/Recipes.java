package graphingcalculator3d.common.gameplay.recipes;

import graphingcalculator3d.common.GraphingCalculator3D;
import graphingcalculator3d.common.gameplay.blocks.GCBlocks;
import graphingcalculator3d.common.gameplay.items.GCItems;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class Recipes
{
	public static void loadRecipes()
	{
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_cartesian"), null,
				new ItemStack(GCBlocks.block_gc_cartesian), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Blocks.STONE
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_spherical"), null,
				new ItemStack(GCBlocks.block_gc_spherical), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.SNOWBALL
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_cylindrical"), null,
				new ItemStack(GCBlocks.block_gc_cylindrical), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.BUCKET
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_cylindrical"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_cylindrical), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.BOWL
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_bipolar_cylindrical"), null,
				new ItemStack(GCBlocks.block_gc_bipolar_cylindrical), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.GOLD_NUGGET
				});
		
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_oblate_spheroidal"), null,
				new ItemStack(GCBlocks.block_gc_oblate_spheroidal), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.SLIME_BALL
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_toroidal"), null,
				new ItemStack(GCBlocks.block_gc_toroidal), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.CLAY_BALL
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_conical"), null,
				new ItemStack(GCBlocks.block_gc_conical), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.CARROT
				});

		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_6_sphere"), null,
				new ItemStack(GCBlocks.block_gc_6_sphere), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.ENDER_PEARL
				});

		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_prolate_spheroidal"), null,
				new ItemStack(GCBlocks.block_gc_prolate_spheroidal), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.MAGMA_CREAM
				});
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic"), null,
				new ItemStack(GCBlocks.block_gc_parabolic), new Object[]
				{
						"IGI",
						"IVI",
						"ORO",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Blocks.REDSTONE_LAMP,
						'O', Blocks.OBSIDIAN,
						'V', Items.CAULDRON
				});
		
		/////////////
		
		GameRegistry.addShapedRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_memory_card"), null,
				new ItemStack(GCItems.item_memory_card), new Object[]
				{
						"GII",
						"INI",
						"RNR",
						'I', Items.IRON_INGOT,
						'G', Blocks.GLASS,
						'R', Items.REDSTONE,
						'N', Items.GOLD_NUGGET
				});
		
		///////////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_spherical_phi"), null,
				new ItemStack(GCBlocks.block_gc_spherical_phi),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_spherical))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_spherical_theta"), null,
				new ItemStack(GCBlocks.block_gc_spherical_theta),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_spherical_phi))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_spherical_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_spherical),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_spherical_theta))
						}
				);
		
		////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_cylindrical_h"), null,
				new ItemStack(GCBlocks.block_gc_cylindrical_h),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_cylindrical))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_cylindrical_theta"), null,
				new ItemStack(GCBlocks.block_gc_cylindrical_theta),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_cylindrical_h))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_cylindrical_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_cylindrical),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_cylindrical_theta))
						}
				);
		
		/////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_cylindrical_sigma"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_cylindrical_sigma),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic_cylindrical))
						}
				);

		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_cylindrical_tau"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_cylindrical_tau),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic_cylindrical_sigma))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_cylindrical_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_cylindrical),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic_cylindrical_tau))
						}
				);

		/////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_tau"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_tau),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic))
						}
				);

		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_varphi"), null,
				new ItemStack(GCBlocks.block_gc_parabolic_phi),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic_tau))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_parabolic_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_parabolic),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_parabolic_phi))
						}
				);
		
		//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_bipolar_cylindrical_sigma"), null,
				new ItemStack(GCBlocks.block_gc_bipolar_cylindrical_sigma),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_bipolar_cylindrical))
						}
				);

		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_bipolar_cylindrical_tau"), null,
				new ItemStack(GCBlocks.block_gc_bipolar_cylindrical_tau),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_bipolar_cylindrical_sigma))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_bipolar_cylindrical_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_bipolar_cylindrical),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_bipolar_cylindrical_tau))
						}
				);
		
		//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_memory_card_reset"), null,
				new ItemStack(GCItems.item_memory_card),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCItems.item_memory_card))
						}
				);
		
		//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_oblate_spheroidal_nu"), null,
				new ItemStack(GCBlocks.block_gc_oblate_spheroidal_nu),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_oblate_spheroidal))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_oblate_spheroidal_varphi"), null,
				new ItemStack(GCBlocks.block_gc_oblate_spheroidal_phi),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_oblate_spheroidal_nu))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_oblate_spheroidal_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_oblate_spheroidal),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_oblate_spheroidal_phi))
						}
				);
		
//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_prolate_spheroidal_nu"), null,
				new ItemStack(GCBlocks.block_gc_prolate_spheroidal_nu),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_prolate_spheroidal))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_prolate_spheroidal_varphi"), null,
				new ItemStack(GCBlocks.block_gc_prolate_spheroidal_phi),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_prolate_spheroidal_nu))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_prolate_spheroidal_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_prolate_spheroidal),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_prolate_spheroidal_phi))
						}
				);
		
		//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_toroidal_phi"), null,
				new ItemStack(GCBlocks.block_gc_toroidal_phi),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_toroidal))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_toroidal_tau"), null,
				new ItemStack(GCBlocks.block_gc_toroidal_tau),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_toroidal_phi))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_toroidal_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_toroidal),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_toroidal_tau))
						}
				);
		
		//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_conical_mu"), null,
				new ItemStack(GCBlocks.block_gc_conical_mu),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_conical))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_conical_nu"), null,
				new ItemStack(GCBlocks.block_gc_conical_nu),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_conical_mu))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_conical_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_conical),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_conical_nu))
						}
				);
		
//////////
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_6_sphere_v"), null,
				new ItemStack(GCBlocks.block_gc_6_sphere_v),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_6_sphere))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_6_sphere_w"), null,
				new ItemStack(GCBlocks.block_gc_6_sphere_w),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_6_sphere_v))
						}
				);
		
		GameRegistry.addShapelessRecipe(new ResourceLocation(GraphingCalculator3D.MODID + ":recipe_gc_6_sphere_shapeless"), null,
				new ItemStack(GCBlocks.block_gc_6_sphere),
				new Ingredient[]
						{
								Ingredient.fromStacks(new ItemStack(GCBlocks.block_gc_6_sphere_w))
						}
				);
	}
}
