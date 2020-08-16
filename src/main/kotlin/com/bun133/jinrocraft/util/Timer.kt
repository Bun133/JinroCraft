package com.bun133.jinrocraft.util

import net.atlantis.jinrocraft.ext.scheduleAsyncRunnable
import org.bukkit.plugin.java.JavaPlugin
import org.bukkit.scheduler.BukkitRunnable
import org.koin.core.KoinComponent
import org.koin.core.inject

class Timer(private val r:Runnable) : BukkitRunnable(), KoinComponent {
    private val plugin: JavaPlugin by inject()
    override fun run() {
        r.run()
    }
}

fun genTimer(r:Runnable):Timer{
    return Timer(r)
}

fun setTimer(p:JavaPlugin,ticks:Long,timer:Timer){
    p.scheduleAsyncRunnable(timer,ticks)
}

fun setTimer(p:JavaPlugin,ticks:Long,r:Runnable){
    setTimer(p, ticks, genTimer(r))
}