package org.guardian.entries;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.guardian.ActionType;

public class ItemEntry extends DataEntry {

    private int typeId, data, enchId, enchPower, amount;

    public ItemEntry(ActionType action, String playerName, Location loc, String worldName, long date, String pluginName) {
        super(action, playerName, loc, worldName, date, pluginName);
    }

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
