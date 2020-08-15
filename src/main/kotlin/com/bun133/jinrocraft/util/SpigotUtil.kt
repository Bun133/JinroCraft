package com.bun133.jinrocraft.util

import org.bukkit.Bukkit
import org.bukkit.entity.Player

fun announce(s:String){
    Bukkit.getServer().onlinePlayers.forEach { it.sendMessage(s) }
}

fun getAllPlayers():List<Player>{
    return Bukkit.getServer().onlinePlayers.toList();
}