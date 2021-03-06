package net.atlantis.jinrocraft.listener

import net.atlantis.jinrocraft.model.RoleService
import net.atlantis.jinrocraft.model.RoleType
import net.atlantis.jinrocraft.model.npc.Grave
import net.atlantis.jinrocraft.model.role.Medium
import net.atlantis.jinrocraft.model.role.Seer
import net.atlantis.jinrocraft.model.role.Werewolf
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.event.player.PlayerInteractAtEntityEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.plugin.java.JavaPlugin
import org.koin.core.KoinComponent
import org.koin.core.inject

class PlayerListener : Listener, KoinComponent {
    private val plugin: JavaPlugin by inject()

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        RoleService().initRole(player)
    }

    @EventHandler
    fun onDeath(event: PlayerDeathEvent) {
        val player = event.entity
        event.deathMessage = "${ChatColor.RED}${player.name}が襲撃されました"
        Grave().create(player.location, player)
    }

    @EventHandler
    fun onRespawn(event: PlayerRespawnEvent) {
        val player = event.player
        player.gameMode = GameMode.SPECTATOR
    }

    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {
        val player = event.player
        if (player.gameMode == GameMode.SPECTATOR) {
            event.isCancelled = true
        }
    }

    @EventHandler
    fun onClick(event: PlayerInteractAtEntityEvent) {
        val player = event.player
        val clickedEntity = event.rightClicked
        val roleType = RoleService().getRole(player)
        when (roleType) {
            RoleType.SEER -> {
                Seer().onClickedEntity(player, clickedEntity)
            }
            RoleType.MEDIUM -> {
                Medium().onClickedEntity(player, clickedEntity)
            }
        }
    }

    @EventHandler
    fun onAttack(event: EntityDamageByEntityEvent) {
        val attacker = event.damager
        if (attacker is Player) {
            val defender = event.entity
            val roleType = RoleService().getRole(attacker)
            when (roleType) {
                RoleType.WEREWOLF -> {
                    Werewolf().onAttackedEntity(attacker, defender, event)
                }
            }
        }
    }
}