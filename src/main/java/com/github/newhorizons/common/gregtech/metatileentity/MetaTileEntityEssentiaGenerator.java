package com.github.newhorizons.common.gregtech.metatileentity;

import codechicken.lib.raytracer.CuboidRayTraceResult;
import codechicken.lib.render.CCRenderState;
import codechicken.lib.render.pipeline.IVertexOperation;
import codechicken.lib.vec.Matrix4;
import com.github.newhorizons.client.GTMTextures;
import com.github.newhorizons.common.gregtech.EssentiaLogic;
import com.github.newhorizons.common.gregtech.GTMMetaBlocks;
import com.github.newhorizons.common.gregtech.predicate.EssentiaCellPredicate;
import com.github.newhorizons.common.gregtech.predicate.TileEntityPredicate;
import com.github.newhorizons.common.gregtech.tileentity.EssentiaHatch;
import com.github.newhorizons.proxy.CommonProxy;
import com.google.common.collect.Lists;
import gregtech.api.capability.GregtechDataCodes;
import gregtech.api.capability.IEnergyContainer;
import gregtech.api.capability.IMultipleTankHandler;
import gregtech.api.capability.IWorkable;
import gregtech.api.capability.impl.EnergyContainerList;
import gregtech.api.capability.impl.FluidTankList;
import gregtech.api.metatileentity.IDataInfoProvider;
import gregtech.api.metatileentity.MetaTileEntity;
import gregtech.api.metatileentity.interfaces.IGregTechTileEntity;
import gregtech.api.metatileentity.multiblock.IMultiblockPart;
import gregtech.api.metatileentity.multiblock.MultiblockAbility;
import gregtech.api.metatileentity.multiblock.MultiblockWithDisplayBase;
import gregtech.api.pattern.BlockPattern;
import gregtech.api.pattern.FactoryBlockPattern;
import gregtech.api.pattern.PatternMatchContext;
import gregtech.api.util.GTUtility;
import gregtech.client.renderer.ICubeRenderer;
import gregtech.client.renderer.texture.Textures;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.translation.I18n;
import thaumcraft.api.blocks.BlocksTC;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.github.newhorizons.common.gregtech.metablock.GTMMetaCasing.MetalCasingType.MAGIC_CASING;

public class MetaTileEntityEssentiaGenerator extends MultiblockWithDisplayBase implements IDataInfoProvider, IWorkable {

    private int tier = -1;
    public final List<EssentiaHatch> mEssentiaHatch = new LinkedList<>();
    public IMultipleTankHandler inputFluidInventory;
    public IEnergyContainer energyContainer;
    private final EssentiaLogic logic;
    private final int[] stable = new int[] {0, 1, 2, 5, 10};

    public MetaTileEntityEssentiaGenerator(ResourceLocation metaTileEntityId) {
        super(metaTileEntityId);
        this.logic = new EssentiaLogic(this);
    }

    @Override
    public int getProgress() {
        return logic.getProgress();
    }

    @Override
    public int getMaxProgress() {
        return logic.getMaxProgress();
    }

    @Override
    public boolean isWorkingEnabled() {
        return logic.isWorkingEnabled();
    }

    @Override
    public void setWorkingEnabled(boolean b) {
        logic.setWorkingEnabled(b);
    }

    @Override
    public ICubeRenderer getBaseTexture(IMultiblockPart iMultiblockPart) {
        return GTMTextures.MAGIC_CASING;
    }

    protected void initializeAbilities() {
        this.inputFluidInventory = new FluidTankList(true, getAbilities(MultiblockAbility.IMPORT_FLUIDS));
        this.energyContainer = new EnergyContainerList(getAbilities(MultiblockAbility.OUTPUT_ENERGY));
    }

    public void renderMetaTileEntity(CCRenderState renderState, Matrix4 translation, IVertexOperation[] pipeline) {
        super.renderMetaTileEntity(renderState, translation, pipeline);
        getFrontOverlay().renderOrientedState(renderState, translation, pipeline, getFrontFacing(), this.logic.isActive(), this.logic.isWorkingEnabled());
    }

    private void resetTileAbilities() {
        this.inputFluidInventory = new FluidTankList(true);
        this.energyContainer = new EnergyContainerList(Lists.newArrayList());
    }

    public boolean updateEssentiaHatchState() {
        for (EssentiaHatch hatch : mEssentiaHatch) {
            hatch.mState = logic.getUpgrade();
        }
        return true;
    }

    @Override
    public boolean onRightClick(EntityPlayer aPlayer, EnumHand hand, EnumFacing facing, CuboidRayTraceResult hitResult) {
        if (!getWorld().isRemote) {
            ItemStack tCurrentItem = aPlayer.inventory.getCurrentItem();
            if (tCurrentItem != null
                    && tCurrentItem.getItem().equals(CommonProxy.Upgrades)) {
                int tMeta = tCurrentItem.getItemDamage();
                if ((logic.getUpgrade() & (1 << tMeta)) == 0 && tMeta != 0) {
                    tCurrentItem.setCount(tCurrentItem.getCount() - 1);
                    logic.setUpgrade(logic.getUpgrade() | (1 << tMeta));
                    aPlayer.sendMessage(new TextComponentString(
                            tCurrentItem.getDisplayName()
                                    + I18n.translateToLocal("largeessentiagenerator.chat")));
                }
                updateEssentiaHatchState();
                return true;
            }
        }
        super.onRightClick(aPlayer, hand, facing, hitResult);
        return true;
    }

    @Override
    protected void formStructure(PatternMatchContext context) {
        super.formStructure(context);
        initializeAbilities();
        Object type = context.get("ESS_CELL");
        if (type instanceof Integer) {
            this.tier = (int) type;
        } else {
            this.tier = 0;
        }
        logic.setMaxProgress(20);
        logic.setStable(stable[tier] * 25);
    }

    @Override
    public void invalidateStructure() {
        super.invalidateStructure();
        resetTileAbilities();
        this.logic.invalidate();
    }

    @Nonnull
    @Override
    public List<ITextComponent> getDataInfo() {
        return new LinkedList<>();
    }

    @Nonnull
    @Override
    protected BlockPattern createStructurePattern() {
        return FactoryBlockPattern.start()
                .aisle("T##TXT##T", "T###C###T", "A#######A")
                .aisle("##TCCCT##", "###CEC###", "#########")
                .aisle("#TCCCCCT#", "##CEEEC##", "#########")
                .aisle("TCCCCCCCT", "#CEEEEEC#", "#########")
                .aisle("XCCCCCCCX", "CEEEEEEEC", "####S####")
                .aisle("TCCCCCCCT", "#CEEEEEC#", "#########")
                .aisle("#TCCCCCT#", "##CEEEC##", "#########")
                .aisle("##TCCCT##", "###CEC###", "#########")
                .aisle("T##TXT##T", "T###C###T", "A#######A")
                .where('S', selfPredicate())
                .where('T', blocks(BlocksTC.stoneArcaneBrick))
                .where('A', blocks(BlocksTC.amberBrick))
                .where('C', states(getCasing()))
                .where('E', EssentiaCellPredicate.ESSENTIA_CELLS)
                .where('X',
                        abilities(MultiblockAbility.IMPORT_FLUIDS, MultiblockAbility.MAINTENANCE_HATCH, MultiblockAbility.OUTPUT_ENERGY)
                        .or(TileEntityPredicate.get(EssentiaHatch.class, this))
                        .or(states(getCasing())))
                .where('#', any())
                .build();
    }

    public IBlockState getCasing() {
        return GTMMetaBlocks.GTM_BLOCK_CASING.getState(MAGIC_CASING);
    }

    @Override
    public MetaTileEntity createMetaTileEntity(IGregTechTileEntity iGregTechTileEntity) {
        return new MetaTileEntityEssentiaGenerator(metaTileEntityId);
    }

    public void addEssentiaHatch(TileEntity te) {
        this.mEssentiaHatch.add((EssentiaHatch) te);
    }

    public int getTier() {
        return this.tier;
    }

    @Override
    protected void updateFormedValid() {
        if (!getWorld().isRemote) {
            this.logic.updateLogic();
            if (this.logic.wasActiveAndNeedsUpdate()) {
                this.logic.setWasActiveAndNeedsUpdate(false);
                this.logic.setActive(false);
            }
        }
    }

    @Override
    public boolean isActive() {
        return super.isActive() && this.logic.isActive();
    }

    @Override
    public void receiveCustomData(int dataId, PacketBuffer buf) {
        super.receiveCustomData(dataId, buf);
        if (dataId == GregtechDataCodes.IS_WORKING) {
            this.logic.setActive(buf.readBoolean());
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKABLE_ACTIVE) {
            this.logic.setActive(buf.readBoolean());
            scheduleRenderUpdate();
        } else if (dataId == GregtechDataCodes.WORKING_ENABLED) {
            this.logic.setWorkingEnabled(buf.readBoolean());
            scheduleRenderUpdate();
        }
    }

    @Override
    public NBTTagCompound writeToNBT(@Nonnull NBTTagCompound data) {
        super.writeToNBT(data);
        return this.logic.writeToNBT(data);
    }

    @Override
    public void readFromNBT(NBTTagCompound data) {
        super.readFromNBT(data);
        this.logic.readFromNBT(data);
    }

    @Override
    public void writeInitialSyncData(PacketBuffer buf) {
        super.writeInitialSyncData(buf);
        this.logic.writeInitialSyncData(buf);
    }

    @Override
    public void receiveInitialSyncData(PacketBuffer buf) {
        super.receiveInitialSyncData(buf);
        this.logic.receiveInitialSyncData(buf);
    }

    @Override
    protected boolean shouldShowVoidingModeButton() {
        return false;
    }

}
