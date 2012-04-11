package org.guardian.entries;

import java.util.List;
import java.util.Map;
import org.bukkit.Location;
import org.bukkit.block.BlockState;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.guardian.ActionType;
import org.guardian.util.BukkitUtils;

public class ItemEntry extends DataEntry {

    private int typeId, data, amount;
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
        stack.addEnchantments(enchantments);
        return stack;
    }

    public String getMessage() {
        final StringBuilder msg = new StringBuilder();
        if (date > 0) {
            msg.append(dateFormat.format(date));
            msg.append(" ");
        }
        if (playerName != null) {
            msg.append(playerName);
            msg.append(" ");
        }
        if (action == ActionType.INVENTORY_TAKE) {
            msg.append("took " + amount + "x " + BukkitUtils.materialName(typeId, (byte) data));
            msg.append(" ");
        } else {
            msg.append("put in " + amount + "x " + BukkitUtils.materialName(typeId, (byte) data));
            msg.append(" ");
        }
        if (loc != null) {
            msg.append(" at ");
            msg.append(loc.getBlockX());
            msg.append(":");
            msg.append(loc.getBlockY());
            msg.append(":");
            msg.append(loc.getBlockZ());
        }
        return msg.toString();
    }

    public List<BlockState> getRollbackBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public List<BlockState> getRebuildBlockStates() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
