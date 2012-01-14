package org.guardian.entries;

import java.util.List;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class ItemEntry extends DataEntry {

    private int typeId, data, enchId, enchPower, amount;

    public int getTypeId() {
        return typeId;
    }

    public int getData() {
        return data;
    }

    public int getAmount() {
        return amount;

    }

    public Enchantment getEnchantment() {
        return Enchantment.getById(enchId);
    }

    public int getEnchantmentPower() {
        return enchPower;
    }

    public ItemStack getItemStack() {
        ItemStack stack = new ItemStack(typeId, amount, (short) data);
        stack.addEnchantment(getEnchantment(), enchPower);
        return stack;
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BlockState> getRollbackBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<BlockState> getRebuildBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
