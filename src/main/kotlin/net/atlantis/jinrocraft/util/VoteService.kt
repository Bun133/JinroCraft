package net.atlantis.jinrocraft.util

import net.atlantis.jinrocraft.ext.getStringMetadata
import net.atlantis.jinrocraft.ext.setStringMetadata
import net.atlantis.jinrocraft.metadata.MetadataKey
import org.bukkit.Bukkit
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class VoteService : KoinComponent {
    companion object {
        private const val CONFIG_VOTE_KEY = "vote"
    }

    private val plugin: JavaPlugin by inject()
    private var isOpen: Boolean = true;

    fun reset() {
        val config: FileConfiguration = plugin.config
        config.set(CONFIG_VOTE_KEY, null)
        isOpen = true;
    }

    fun setVote(from: Player, to: Player) {
        setVote(from, to.name)
    }

    fun setVote(from: Player, to: String) {
        if (!isOpen) {
            from.sendMessage("投票はすでに締め切られています！")
            return
        }
        plugin.config.set(getConfigID(from), to)
        from.setStringMetadata(plugin, MetadataKey.VOTE.key, to)
        plugin.saveConfig()
    }

    private fun getVoteString(from: Player): String? {
        if (from.getStringMetadata(MetadataKey.VOTE.key) != null) {
            return from.getStringMetadata(MetadataKey.VOTE.key)
        }
        return plugin.config.getString(getConfigID(from))
    }

    fun getVotePlayer(from: Player): Player? {
        val name = getVoteString(from)
        for (player in Bukkit.getOnlinePlayers()) {
            if (player.name == name) return player
        }
        return null
    }

    private fun getConfigID(p: Player): String = "$CONFIG_VOTE_KEY.${p.uniqueId}"

    fun stopVote(): VoteService {
        isOpen = false
        return this@VoteService
    }

    fun getVoteResult(): Pair<Player, Int>? {
        return getVoteResult(1)?.get(0)
    }

    fun getVoteResult(i: Int): List<Pair<Player, Int>>? {
        if (isOpen) return null
        val voteList = getAllPlayers().stream()
                .map { getVotePlayer(it) }
        val voteMap: MutableMap<Player, Int> = mutableMapOf()
        voteList.filter { it != null }
                .forEach {
                    if (voteMap[it!!] == null) voteMap[it] = 0
                    voteMap[it] = voteMap[it]!! + 1
                }
        val sortedList = voteMap.toSortedMap(compareBy { voteMap[it] }).toList()
        return sortedList.subList(0, i)
    }
}