package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.util.announce
import net.atlantis.jinrocraft.util.getAllPlayers
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.getRandomRole
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StartCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (player.isOp) {
            if (args[0] == "vote") return VoteStartCommand().onCommandByPlayer(player, command, label, args)
            announce("ゲームが開始されました")
            setUpGame()
        } else {
            player.sendMessage("このコマンドはオペレーターのみが使用できます")
        }
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        announce("ゲームが開始されました")
        setUpGame()
        return true
    }

    private fun setUpGame() {
        //心なしの最適化
        val rs = RoleService();
        getAllPlayers()
                .filter { it.gameMode == GameMode.SURVIVAL }
                .forEach {
                    rs.initRole(it)
                    rs.setRole(it, getRandomRole())
                }
    }
}
