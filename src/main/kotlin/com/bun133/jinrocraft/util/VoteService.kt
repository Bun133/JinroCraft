package com.bun133.jinrocraft.util

import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringListMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import net.atlantis.jinrocraft.model.RoleService
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.util.*

class VoteService : KoinComponent {
    companion object{
        private const val CONFIG_VOTE_KEY = "vote"
    }

    private val plugin: JavaPlugin by inject()

    fun reset() {
        val config: FileConfiguration = plugin.config
        config.set(CONFIG_VOTE_KEY, null)
    }

    fun resetPlayer(p:Player){
        setVote(p,"")
    }

    fun setVote(from:Player,to:Player){
        setVote(from,to.name)
    }

    fun setVote(from:Player,to:String){
        plugin.config.set(getConfigID(from),to)
        from.setStringMetadata(plugin,MetadataKey.VOTE.key,to)
        plugin.saveConfig()
    }

    private fun getVoteString(from:Player): String? {
        if (from.getStringMetadata(MetadataKey.VOTE.key)!=null){
            return from.getStringMetadata(MetadataKey.VOTE.key)
        }
        return plugin.config.getString(getConfigID(from))
    }

    fun getVotePlayer(from:Player): Player?{
        val name=getVoteString(from)
        for(player in Bukkit.getOnlinePlayers()){
            if(player.name == name) return player
        }
        return null
    }

    private fun getConfigID(p:Player):String = "$CONFIG_VOTE_KEY.${p.uniqueId}"
}