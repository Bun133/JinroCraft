package com.bun133.jinrocraft.command

import com.bun133.jinrocraft.util.*
import net.atlantis.jinrocraft.command.BaseCommand
import net.atlantis.jinrocraft.command.CommandArgs
import net.md_5.bungee.api.ChatColor
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Bukkit
import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.Sound
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.inject

class VoteStartCommand :BaseCommand(){
    companion object{
        private const val Timer_Ticks:Long=600L
    }

    private val plugin: JavaPlugin by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if(player.isOp){
            announceTitle(ComponentBuilder("投票が開始されました").bold(true))
//            announceSound(Sound.BLOCK_ANVIL_DESTROY)
            announceNote(Instrument.PIANO, Note(12))
            VoteService().reset()
            setTimer(plugin, Timer_Ticks, Runnable {
                VoteStopCommand().onCommandByPlayer(player, command, label, args)
            })
//            Bukkit.getServer().broadcastMessage("投票が開始されました")
        }
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        announceTitle(ComponentBuilder("投票が開始されました").bold(true))
        announceNote(Instrument.PIANO, Note(12))
        VoteService().reset()
        setTimer(plugin, Timer_Ticks, Runnable {
            VoteStopCommand().onCommandByOther(sender, command, label, args)
        })
        return true
    }

}
