package mekanism.common.inventory.slot;

import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import mekanism.api.annotations.NonNull;
import mekanism.api.fluid.IExtendedFluidTank;
import mekanism.api.inventory.AutomationType;
import mekanism.api.inventory.IMekanismInventory;
import mekanism.common.capabilities.Capabilities;
import mekanism.common.capabilities.MergedTank;
import mekanism.common.capabilities.MergedTank.CurrentType;
import mekanism.common.inventory.slot.chemical.GasInventorySlot;
import mekanism.common.inventory.slot.chemical.InfusionInventorySlot;
import mekanism.common.inventory.slot.chemical.PigmentInventorySlot;
import mekanism.common.inventory.slot.chemical.SlurryInventorySlot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidUtil;

public class HybridInventorySlot extends BasicInventorySlot implements IFluidHandlerSlot {

    private static boolean hasCapability(@Nonnull ItemStack stack) {
        return FluidUtil.getFluidHandler(stack).isPresent() || stack.getCapability(Capabilities.GAS_HANDLER_CAPABILITY).isPresent() ||
               stack.getCapability(Capabilities.INFUSION_HANDLER_CAPABILITY).isPresent() || stack.getCapability(Capabilities.PIGMENT_HANDLER_CAPABILITY).isPresent() ||
               stack.getCapability(Capabilities.SLURRY_HANDLER_CAPABILITY).isPresent();
    }

    public static HybridInventorySlot inputOrDrain(MergedTank mergedTank, @Nullable IMekanismInventory inventory, int x, int y) {
        Objects.requireNonNull(mergedTank, "Merged tank cannot be null");
        Predicate<@NonNull ItemStack> fluidInsertPredicate = FluidInventorySlot.getInputPredicate(mergedTank.getFluidTank());
        Predicate<@NonNull ItemStack> gasInsertPredicate = GasInventorySlot.getDrainInsertPredicate(mergedTank.getGasTank(), GasInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> infusionInsertPredicate = InfusionInventorySlot.getDrainInsertPredicate(mergedTank.getInfusionTank(), InfusionInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> pigmentInsertPredicate = PigmentInventorySlot.getDrainInsertPredicate(mergedTank.getPigmentTank(), PigmentInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> slurryInsertPredicate = SlurryInventorySlot.getDrainInsertPredicate(mergedTank.getSlurryTank(), SlurryInventorySlot::getCapability);
        BiPredicate<@NonNull ItemStack, @NonNull AutomationType> insertPredicate = (stack, automationType) -> {
            CurrentType currentType = mergedTank.getCurrentType();
            if (currentType == CurrentType.FLUID) {
                return fluidInsertPredicate.test(stack);
            } else if (currentType == CurrentType.GAS) {
                return gasInsertPredicate.test(stack);
            } else if (currentType == CurrentType.INFUSION) {
                return infusionInsertPredicate.test(stack);
            } else if (currentType == CurrentType.PIGMENT) {
                return pigmentInsertPredicate.test(stack);
            } else if (currentType == CurrentType.SLURRY) {
                return slurryInsertPredicate.test(stack);
            }//Else the tank is empty, check if any insert predicate is valid
            return fluidInsertPredicate.test(stack) || gasInsertPredicate.test(stack) || infusionInsertPredicate.test(stack) || pigmentInsertPredicate.test(stack) ||
                   slurryInsertPredicate.test(stack);
        };
        //Extract predicate, always allow the player to manually extract or if the insert predicate no longer matches allow for it to be extracted
        return new HybridInventorySlot(mergedTank, (stack, automationType) -> automationType == AutomationType.MANUAL || !insertPredicate.test(stack, automationType),
              insertPredicate, HybridInventorySlot::hasCapability, inventory, x, y);
    }

    public static HybridInventorySlot outputOrFill(MergedTank mergedTank, @Nullable IMekanismInventory inventory, int x, int y) {
        Objects.requireNonNull(mergedTank, "Merged tank cannot be null");
        Predicate<@NonNull ItemStack> gasExtractPredicate = GasInventorySlot.getFillExtractPredicate(mergedTank.getGasTank(), GasInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> infusionExtractPredicate = InfusionInventorySlot.getFillExtractPredicate(mergedTank.getInfusionTank(), InfusionInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> pigmentExtractPredicate = PigmentInventorySlot.getFillExtractPredicate(mergedTank.getPigmentTank(), PigmentInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> slurryExtractPredicate = SlurryInventorySlot.getFillExtractPredicate(mergedTank.getSlurryTank(), SlurryInventorySlot::getCapability);
        Predicate<@NonNull ItemStack> gasInsertPredicate = stack -> GasInventorySlot.fillInsertCheck(mergedTank.getGasTank(), GasInventorySlot.getCapability(stack));
        Predicate<@NonNull ItemStack> infusionInsertPredicate = stack -> InfusionInventorySlot.fillInsertCheck(mergedTank.getInfusionTank(), InfusionInventorySlot.getCapability(stack));
        Predicate<@NonNull ItemStack> pigmentInsertPredicate = stack -> PigmentInventorySlot.fillInsertCheck(mergedTank.getPigmentTank(), PigmentInventorySlot.getCapability(stack));
        Predicate<@NonNull ItemStack> slurryInsertPredicate = stack -> SlurryInventorySlot.fillInsertCheck(mergedTank.getSlurryTank(), SlurryInventorySlot.getCapability(stack));
        return new HybridInventorySlot(mergedTank, (stack, automationType) -> {
            if (automationType == AutomationType.MANUAL) {
                //Always allow the player to manually extract
                return true;
            }
            CurrentType currentType = mergedTank.getCurrentType();
            if (currentType == CurrentType.FLUID) {
                //Always allow extracting from a "fluid output" slot
                return true;
            } else if (currentType == CurrentType.GAS) {
                return gasExtractPredicate.test(stack);
            } else if (currentType == CurrentType.INFUSION) {
                return infusionExtractPredicate.test(stack);
            } else if (currentType == CurrentType.PIGMENT) {
                return pigmentExtractPredicate.test(stack);
            } else if (currentType == CurrentType.SLURRY) {
                return slurryExtractPredicate.test(stack);
            }//Else the tank is empty, check all our extraction predicates
            return gasExtractPredicate.test(stack) && infusionExtractPredicate.test(stack) && pigmentExtractPredicate.test(stack) && slurryExtractPredicate.test(stack);
        }, (stack, automationType) -> {
            CurrentType currentType = mergedTank.getCurrentType();
            if (currentType == CurrentType.FLUID) {
                //Only allow inserting internally for "fluid output" slots
                return automationType == AutomationType.INTERNAL;
            } else if (currentType == CurrentType.GAS) {
                return gasInsertPredicate.test(stack);
            } else if (currentType == CurrentType.INFUSION) {
                return infusionInsertPredicate.test(stack);
            } else if (currentType == CurrentType.PIGMENT) {
                return pigmentInsertPredicate.test(stack);
            } else if (currentType == CurrentType.SLURRY) {
                return slurryInsertPredicate.test(stack);
            }//Else the tank is empty, if the item is a fluid handler and it is an internal check allow it
            if (automationType == AutomationType.INTERNAL && FluidUtil.getFluidHandler(stack).isPresent()) {
                return true;
            }
            //otherwise only allow it if one of the chemical insert predicates matches
            return gasInsertPredicate.test(stack) || infusionInsertPredicate.test(stack) || pigmentInsertPredicate.test(stack) || slurryInsertPredicate.test(stack);
        }, HybridInventorySlot::hasCapability, inventory, x, y);
    }

    private final MergedTank mergedTank;

    // used by IFluidHandlerSlot
    private boolean isDraining;
    private boolean isFilling;

    private HybridInventorySlot(MergedTank mergedTank, BiPredicate<@NonNull ItemStack, @NonNull AutomationType> canExtract,
          BiPredicate<@NonNull ItemStack, @NonNull AutomationType> canInsert, Predicate<@NonNull ItemStack> validator, @Nullable IMekanismInventory inventory,
          int x, int y) {
        super(canExtract, canInsert, validator, inventory, x, y);
        this.mergedTank = mergedTank;
    }

    /**
     * Drains tank into slot (tries all types)
     */
    public void drainChemicalTanks() {
        drainChemicalTank(CurrentType.GAS);
        drainChemicalTank(CurrentType.INFUSION);
        drainChemicalTank(CurrentType.PIGMENT);
        drainChemicalTank(CurrentType.SLURRY);
    }

    /**
     * Drains tank into slot
     */
    public void drainChemicalTank(CurrentType type) {
        if (type == CurrentType.GAS) {
            ChemicalInventorySlot.drainChemicalTank(this, mergedTank.getGasTank(), GasInventorySlot.getCapability(current));
        } else if (type == CurrentType.INFUSION) {
            ChemicalInventorySlot.drainChemicalTank(this, mergedTank.getInfusionTank(), InfusionInventorySlot.getCapability(current));
        } else if (type == CurrentType.PIGMENT) {
            ChemicalInventorySlot.drainChemicalTank(this, mergedTank.getPigmentTank(), PigmentInventorySlot.getCapability(current));
        } else if (type == CurrentType.SLURRY) {
            ChemicalInventorySlot.drainChemicalTank(this, mergedTank.getSlurryTank(), SlurryInventorySlot.getCapability(current));
        }
    }

    /**
     * Fills tank from slot (tries all types)
     */
    public void fillChemicalTanks() {
        fillChemicalTank(CurrentType.GAS);
        fillChemicalTank(CurrentType.INFUSION);
        fillChemicalTank(CurrentType.PIGMENT);
        fillChemicalTank(CurrentType.SLURRY);
    }

    /**
     * Fills tank from slot
     */
    public void fillChemicalTank(CurrentType type) {
        if (type == CurrentType.GAS) {
            ChemicalInventorySlot.fillChemicalTank(this, mergedTank.getGasTank(), GasInventorySlot.getCapability(current));
        } else if (type == CurrentType.INFUSION) {
            ChemicalInventorySlot.fillChemicalTank(this, mergedTank.getInfusionTank(), InfusionInventorySlot.getCapability(current));
        } else if (type == CurrentType.PIGMENT) {
            ChemicalInventorySlot.fillChemicalTank(this, mergedTank.getPigmentTank(), PigmentInventorySlot.getCapability(current));
        } else if (type == CurrentType.SLURRY) {
            ChemicalInventorySlot.fillChemicalTank(this, mergedTank.getSlurryTank(), SlurryInventorySlot.getCapability(current));
        }
    }

    @Override
    public IExtendedFluidTank getFluidTank() {
        return mergedTank.getFluidTank();
    }

    @Override
    public boolean isDraining() {
        return isDraining;
    }

    @Override
    public boolean isFilling() {
        return isFilling;
    }

    @Override
    public void setDraining(boolean draining) {
        isDraining = draining;
    }

    @Override
    public void setFilling(boolean filling) {
        isFilling = filling;
    }
}
