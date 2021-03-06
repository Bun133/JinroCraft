package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StatusCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        val roleType = RoleService().getRole(player) ?: return false
        player.sendMessage("あなたは ${roleType.jpName} です")
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        sender.sendMessage("You must be a player!")
        return false
    }
}