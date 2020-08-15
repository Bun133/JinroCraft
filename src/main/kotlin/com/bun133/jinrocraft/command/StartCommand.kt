package com.bun133.jinrocraft.command

import com.bun133.jinrocraft.util.announce
import com.bun133.jinrocraft.util.getAllPlayers
import net.atlantis.jinrocraft.command.BaseCommand
import net.atlantis.jinrocraft.command.CommandArgs
import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.getRandomRole
import org.bukkit.Bukkit
import org.bukkit.GameMode
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class StartCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (player.isOp){
            announce("ゲームが開始されました")
            setUpGame()
        }else{
            player.sendMessage("このコマンドはオペレーターのみが使用できます")
            return true
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
        var rs=RoleService();
        getAllPlayers()
                .filter { it.gameMode==GameMode.SURVIVAL }
                .forEach {
                    rs.initRole(it)
                    rs.setRole(it, getRandomRole())
                }
    }
}
