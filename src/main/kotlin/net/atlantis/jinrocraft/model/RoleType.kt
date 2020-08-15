package net.atlantis.jinrocraft.model

import kotlin.random.Random

enum class RoleType(val key: String, val id:Int, val jpName: String, val groupType: GroupType) {
    CITIZEN("Citizen",0, "市民", GroupType.CITIZENS),
    SEER("Seer", 1,"占い師", GroupType.CITIZENS),
    MEDIUM("Medium", 2,"霊能者", GroupType.CITIZENS),
    HUNTER("Hunter", 3,"狩人", GroupType.CITIZENS),
    WEREWOLF("Werewolf", 4,"人狼", GroupType.WEREWOLVES),
    MADMAN("Madman", 5,"狂人", GroupType.WEREWOLVES),
    FOX("Fox", 6,"妖狐", GroupType.FOXES);

    companion object {
        fun findByKey(key: String?): RoleType? {
            return values().firstOrNull { it.key == key }
        }

        fun findByID(id:Int): RoleType? {
            return values().firstOrNull{ it.id == id }
        }
    }
}
//Modded by Bun133

fun getRandomRole():RoleType{
    //TODO
    val r= Random
    return RoleType.findByID(r.nextInt(RoleType.values().size)) ?: RoleType.CITIZEN
}