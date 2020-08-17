package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.util.VoteService
import net.atlantis.jinrocraft.command.BaseCommand
import net.atlantis.jinrocraft.command.CommandArgs
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VoteCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (args[0] == null) return false;
        args[0]?.let {
            return if (checkUser(args[0])) {
                VoteService().setVote(player, it)
                player.sendMessage("You voted to " + ChatColor.YELLOW.toString() + args[0])
                true;
            } else false;
        }
        return true;
    }

    private fun checkUser(s: String?): Boolean {
        if (s == null) return false;
        return Bukkit.getServer().onlinePlayers.map { it.name }.contains(s)
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You have to be player!")
        return true;
    }
}
