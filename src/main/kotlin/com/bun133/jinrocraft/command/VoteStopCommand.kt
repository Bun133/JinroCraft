package com.bun133.jinrocraft.command

import com.bun133.jinrocraft.util.VoteService
import com.bun133.jinrocraft.util.announce
import com.bun133.jinrocraft.util.announceNote
import com.bun133.jinrocraft.util.announceTitle
import net.atlantis.jinrocraft.command.BaseCommand
import net.atlantis.jinrocraft.command.CommandArgs
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.Instrument
import org.bukkit.Note
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class VoteStopCommand : BaseCommand(){
    override fun onCommandByPlayer(player: Player, command: Command, label: String, args: CommandArgs): Boolean {
        if(player.isOp){
            announceTitle(ComponentBuilder("投票が終了されました").bold(true))
            announceNote(Instrument.PIANO, Note(12))
            announce("今回の投票結果は......")
//            for(p in VoteService().stopVote().getVoteResult(1)!!){
//                announce(p.first.name+"さんが"+p.second+"票でした")
//            }
            val p=VoteService().stopVote().getVoteResult()
            if (p != null) {
                announce(p.first.name+"さんが"+p.second+"票でした")
            }else announce("Null")
        }
        return true
    }

    override fun onCommandByOther(sender: CommandSender, command: Command, label: String, args: CommandArgs): Boolean {
        announceTitle(ComponentBuilder("投票が終了されました").bold(true))
        announceNote(Instrument.PIANO, Note(12))
        return true
    }
}