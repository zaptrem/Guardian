package org.guardian.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.guardian.Guardian;
import org.guardian.PlayerSession;
import org.guardian.util.BukkitUtils;

/**
 * Abstract class representing a command. When run by the command manager (
 * {@link Guardian}), it pre-processes all the data into more useful forms.
 * Extending classes should adjust required fields in their constructor
 *
 */
public abstract class BaseCommand {

    protected final Guardian plugin = Guardian.getInstance();
    protected CommandSender sender;
    protected List<String> args = new ArrayList<String>();
    protected PlayerSession session;
    protected String usedCommand;
    protected Player player;
    // Commands below can be set by each individual command
    public String name;
    public int minArgs = -1;
    public int maxArgs = -1;
    public boolean allowConsole = true;
    public String usage;

    /**
     * Method called by the command manager in {@link Guardian} to run the
     * command. Arguments are processed into a list for easier manipulating.
     * Argument lengths, permissions and sender types are all handled.
     *
     * @param csender
     *            {@link CommandSender} to send data to
     * @param preArgs arguments to be processed
     * @param cmd command being executed
     * @return true on success, false if there is an error in the checks or if
     * the extending command returns false
     */
    public boolean run(CommandSender csender, String[] preArgs, String cmd) {
        sender = csender;
        session = plugin.getSessionManager().getSession(sender);
        usedCommand = cmd;
        // Sort out arguments
        args.clear();
        args.addAll(Arrays.asList(preArgs));
        // Remove commands from arguments
        for (int i = 0; i < name.split(" ").length && i < args.size(); i++) {
            args.remove(0);
        }
        // Check arg lengths
        if (minArgs > -1 && args.size() < minArgs || maxArgs > -1 && args.size() > maxArgs) {
            BukkitUtils.sendMessage(sender, ChatColor.RED + "Wrong arguments supplied!");
            sendUsage();
            return true;
        }
        // Check if sender should be a player
        if (!allowConsole && !(sender instanceof Player)) {
            return false;
        }
        if (sender instanceof Player) {
            player = (Player) sender;
        }
        // Check permission
        if (!permission()) {
            BukkitUtils.sendMessage(sender, ChatColor.RED + "You do not have permission to do that!");
            return false;
        }
        return execute();

    }

    /**
     * Runs the extending command. Should only be run by the BaseCommand after
     * all pre-processing is done
     *
     * @return true on success, false otherwise
     */
    public abstract boolean execute();

    /**
     * Performs the extending command's permission check.
     *
     * @return true if the user has permission, false if not
     */
    public abstract boolean permission();

    /**
     * Sends advanced help to the sender
     */
    public abstract void moreHelp();

    /**
     * Displays the help information for this command
     */
    public void sendUsage() {
        BukkitUtils.sendMessage(sender, ChatColor.RED + "/" + usedCommand + " " + name + " " + usage);
    }

    public static void showPage(CommandSender sender, int page) {
        Guardian plugin = Guardian.getInstance();
        PlayerSession session = plugin.getSessionManager().getSession(sender);
        if (session.getEntryCache() != null && session.getEntryCache().size() > 0) {
            final int startpos = (page - 1) * plugin.getConf().linesPerPage;
            if (page > 0 && startpos <= session.getEntryCache().size() - 1) {
                final int stoppos = startpos + plugin.getConf().linesPerPage >= session.getEntryCache().size() ? session.getEntryCache().size() - 1 : startpos + plugin.getConf().linesPerPage - 1;
                final int numberOfPages = (int) Math.ceil(session.getEntryCache().size() / (double) plugin.getConf().linesPerPage);
                if (numberOfPages != 1) {
                    sender.sendMessage(ChatColor.DARK_AQUA + "Page " + page + "/" + numberOfPages);
                }
                for (int i = startpos; i <= stoppos; i++) {
                    sender.sendMessage(ChatColor.GOLD + session.getEntryCache().get(i).getMessage());
                }
            } else {
                sender.sendMessage(ChatColor.RED + "There isn't a page '" + page + "'");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "No blocks in lookup cache");
        }
    }
}
