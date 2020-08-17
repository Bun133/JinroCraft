package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.util.VoteService
import net.atlantis.jinrocraft.util.announceNote
import net.atlantis.jinrocraft.util.announceTitle
import net.atlantis.jinrocraft.util.setTimer
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.inject

class VoteStartCommand : BaseCommand() {
    companion object {
        private const val Timer_Ticks: Long = 600L
    }

    private val plugin: JavaPlugin by inject()

    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (player.isOp) {
            announceTitle(ComponentBuilder("投票が開始されました").bold(true))
            announceNote(Instrument.PIANO, Note(12))
            VoteService().reset()
            setTimer(plugin, Timer_Ticks, Runnable {
                VoteStopCommand().onCommandByPlayer(player, command, label, args)
            })
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
