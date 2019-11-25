package heroes.abilities;

import heroes.Player;

public final class Execute extends Ability {

    float executePercent;

    Execute(final float baseDmg, final float lvlScale, final float rogueDmg, final float knightDmg, final float pyroDmg, final float wizardDmg, final float baseInstKill) {
        super(baseDmg, lvlScale, rogueDmg, knightDmg, pyroDmg, wizardDmg);
        type = AbilityType.Execute;
        executePercent = baseInstKill;
    }

    public int computeBaseDamage(Player player, int level, float landModifier) {
        if(player.getCurrentHp() <= player.getMaxHp() * (executePercent + 0.01f * level)) {
            return player.getCurrentHp();
        }
        float dmg = baseDamage + level * dmgScalePerLevel;
        dmg += dmg * landModifier;
        return Math.round(dmg);
    }

    public boolean checkIntantKill(Player player, int level) {
        return player.getCurrentHp() <= player.getMaxHp() * (executePercent + 0.01f * level);
    }

    public int addRaceModif(Player player, int dmg) {
        return Math.round(dmg + dmg * modifierByRace.get(player.type));
    }
}
