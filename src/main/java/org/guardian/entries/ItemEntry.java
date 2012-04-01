package org.guardian.entries;

import java.sql.Blob;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.guardian.ActionType;

public class ItemEntry extends DataEntry {

    private int typeId, data, enchId, enchPower, amount;
    private Map<Enchantment, Integer> enchantments;

    public ItemEntry(ActionType action, String playerName, Location loc, long date, ItemStack item, String pluginName) {
        super(action, playerName, loc, loc.getWorld().getName(), date, pluginName);
        typeId = item.getTypeId();
        data = item.getDurability();
        amount = item.getAmount();
        enchantments = item.getEnchantments();
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

    public Map<Enchantment, Integer> getEnchantments() {
        return enchantments;
    }
    
    public ItemStack getItemStack() {
        ItemStack stack = new ItemStack(typeId, amount, (short) data);
        //stack.addEnchantment(getEnchantment(), enchPower);
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
