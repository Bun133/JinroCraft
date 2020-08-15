package com.bun133.jinrocraft.util

import net.md_5.bungee.api.ChatMessageType
import net.md_5.bungee.api.chat.ComponentBuilder
import org.bukkit.*
import org.bukkit.entity.Player

fun announce(s:String){
    Bukkit.getServer().broadcastMessage(s)
}

fun getAllPlayers():List<Player>{
    return Bukkit.getServer().onlinePlayers.toList();
}

fun showTitleToPlayer(builder: ComponentBuilder,p:Player){
    p.spigot().sendMessage(ChatMessageType.ACTION_BAR,builder.currentComponent)
}

fun announceTitle(builder: ComponentBuilder){
    Bukkit.getServer().onlinePlayers.stream().forEach {
        showTitleToPlayer(builder,it)
    }
}

fun playSound(p: Player,s: Sound){
    p.playSound(p.location,s,0.5f,0.0f)
}

fun announceSound(s:Sound){
    Bukkit.getServer().onlinePlayers.stream().forEach {
        playSound(it,s)
    }
}

fun playNote(p:Player,i: Instrument,n: Note){
    p.playNote(p.location,i,n)
}

fun announceNote(i:Instrument,n:Note){
    Bukkit.getServer().onlinePlayers.stream().forEach {
        playNote(it,i, n)
    }
}