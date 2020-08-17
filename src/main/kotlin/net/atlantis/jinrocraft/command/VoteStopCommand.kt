package net.atlantis.jinrocraft.command

import net.atlantis.jinrocraft.util.VoteService
import net.atlantis.jinrocraft.util.announce
import net.atlantis.jinrocraft.util.announceNote
import net.atlantis.jinrocraft.util.announceTitle
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VoteStopCommand : BaseCommand() {
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if (player.isOp) {
            announceTitle(ComponentBuilder("投票が終了されました").bold(true))
            announceNote(Instrument.PIANO, Note(12))
            announce("今回の投票結果は......")
            for (p in VoteService().stopVote().getVoteResult(1)!!) {
                announce(p.first.name + "さんが" + p.second + "票でした")
            }
        }
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        announceTitle(ComponentBuilder("投票が終了されました").bold(true))
        announceNote(Instrument.PIANO, Note(12))
        announce("今回の投票結果は......")
        for (p in VoteService().stopVote().getVoteResult(1)!!) {
            announce(p.first.name + "さんが" + p.second + "票でした")
        }
        return true
    }
}