package chbachman.armour.items;

import baubles.api.BaubleType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;
import chbachman.armour.reference.Reference;
import chbachman.armour.util.NBTHelper;
import cofh.api.modhelpers.ThermalExpansionHelper;
import cofh.core.item.ItemBase;
import cofh.lib.util.helpers.ItemHelper;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class ItemRegister {
    
    public static ArmorMaterial materialModular;
    
    public static Item helmetModular;
    public static Item chestplateModular;
    public static Item leggingsModular;
    public static Item bootsModular;
    public static Item itemRing;
    public static Item itemPendant;
    public static Item itemBelt;
    
    public static ItemStack stackHelmetModular;
    public static ItemStack stackChestplateModular;
    public static ItemStack stackLeggingsModular;
    public static ItemStack stackBootsModular;
    public static ItemStack stackItemRing;
    public static ItemStack stackItemPendant;
    public static ItemStack stackItemBelt;
    
    public static ItemBase material;
    
    public static ItemStack temperedElectrum;
    public static ItemStack heatedElectrum;
    
    public static final String[] TEXTURE_MODULAR = { Reference.ARMOUR_LOATION + "Modular_1.png", Reference.ARMOUR_LOATION + "Modular_2.png" };
    
    public static void preInit() {
        
        material = (ItemBase) new ItemBase("modulararmour").setUnlocalizedName("material").setCreativeTab(CreativeTabs.tabMaterials);
        
        materialModular = EnumHelper.addArmorMaterial("", 25, new int[] { 3, 7, 5, 3 }, 10);
        helmetModular = new ItemModularArmour(materialModular, 0).setArmorTextures(TEXTURE_MODULAR).setUnlocalizedName("chbachman.armour.helmetModular").setTextureName(Reference.ITEM_LOCATION + "ModularHelmet");
        chestplateModular = new ItemModularArmour(materialModular, 1).setArmorTextures(TEXTURE_MODULAR).setUnlocalizedName("chbachman.armour.chestplateModular").setTextureName(Reference.ITEM_LOCATION + "ModularChestplate");
        leggingsModular = new ItemModularArmour(materialModular, 2).setArmorTextures(TEXTURE_MODULAR).setUnlocalizedName("chbachman.armour.leggingsModular").setTextureName(Reference.ITEM_LOCATION + "ModularLegs");
        bootsModular = new ItemModularArmour(materialModular, 3).setArmorTextures(TEXTURE_MODULAR).setUnlocalizedName("chbachman.armour.bootsModular").setTextureName(Reference.ITEM_LOCATION + "ModularBoots");
        itemRing = new ItemBauble().setBaubleType(BaubleType.RING).setUnlocalizedName("chbachman.armour.itemRing").setTextureName(Reference.ITEM_LOCATION + "ItemRing");
        itemPendant = new ItemBauble().setBaubleType(BaubleType.AMULET).setUnlocalizedName("chbachman.armour.itemPendant").setTextureName(Reference.ITEM_LOCATION + "ItemPendant");
        itemBelt = new ItemBauble().setBaubleType(BaubleType.BELT).setUnlocalizedName("chbachman.armour.itemBelt").setTextureName(Reference.ITEM_LOCATION + "ItemBelt");
        
        GameRegistry.registerItem(helmetModular, "helmetModular");
        GameRegistry.registerItem(chestplateModular, "chestplateModular");
        GameRegistry.registerItem(leggingsModular, "leggingsModular");
        GameRegistry.registerItem(bootsModular, "bootsModular");
        GameRegistry.registerItem(itemRing, "itemRing");
        GameRegistry.registerItem(itemPendant, "itemPendant");
        GameRegistry.registerItem(itemBelt, "itemBelt");
        
    }
    
    public static void init() {
        
        heatedElectrum = material.addOreDictItem(1, "heatedElectrum", 1);
        temperedElectrum = material.addOreDictItem(0, "temperedElectrum", 1);
        
        stackHelmetModular = NBTHelper.createDefaultStackTag(new ItemStack(helmetModular));
        stackChestplateModular = NBTHelper.createDefaultStackTag(new ItemStack(chestplateModular));
        stackLeggingsModular = NBTHelper.createDefaultStackTag(new ItemStack(leggingsModular));
        stackBootsModular = NBTHelper.createDefaultStackTag(new ItemStack(bootsModular));
        stackItemRing = NBTHelper.createDefaultStackTag(new ItemStack(itemRing));
        stackItemBelt = NBTHelper.createDefaultStackTag(new ItemStack(itemBelt));
        stackItemPendant = NBTHelper.createDefaultStackTag(new ItemStack(itemPendant));
        
        if (Loader.isModLoaded("ThermalFoundation")) {
            ThermalExpansionHelper.addTransposerFill(4000, GameRegistry.findItemStack("ThermalFoundation", "ingotElectrum", 1), heatedElectrum, new FluidStack(FluidRegistry.getFluid("pyrotheum"), 1000), false);
            ThermalExpansionHelper.addTransposerFill(4000, heatedElectrum, temperedElectrum, new FluidStack(FluidRegistry.getFluid("cryotheum"), 1000), false);
        }
    }
    
    public static void postInit() {
        if (!Loader.isModLoaded("ThermalExpansion")) {
            if (ItemHelper.oreNameExists("ingotElectrum")) {
                for (ItemStack stack : OreDictionary.getOres("ingotElectrum")) {
                    GameRegistry.addSmelting(stack, heatedElectrum, 0.0F);
                }
            } else {
                for (ItemStack stack : OreDictionary.getOres("ingotGold")) {
                    GameRegistry.addSmelting(stack, heatedElectrum, 0.0F);
                }
            }
            
            GameRegistry.addRecipe(new ShapelessOreRecipe(temperedElectrum, Items.water_bucket, "heatedElectrum"));
        }
        
        GameRegistry.addRecipe(new ShapedOreRecipe(stackHelmetModular, new Object[] { "III", "I I", 'I', temperedElectrum }));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackChestplateModular, new Object[] { "I I", "III", "III", 'I', temperedElectrum }));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackLeggingsModular, new Object[] { "III", "I I", "I I", 'I', temperedElectrum }));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackBootsModular, new Object[] { "I I", "I I", 'I', temperedElectrum }));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackItemRing, new Object[] {" I ", "I I", " I ", 'I', temperedElectrum}));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackItemBelt, new Object[] {"III", "I I", "III", 'I', temperedElectrum}));
        GameRegistry.addRecipe(new ShapedOreRecipe(stackItemPendant, new Object[] {" ss", "IIs", "II ", 'I', temperedElectrum, 's', Items.string}));
    }
    
}
