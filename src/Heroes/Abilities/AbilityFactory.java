package Heroes.Abilities;

import java.util.HashMap;
import java.util.Map;

public class AbilityFactory {

    private static AbilityFactory instance;

    private final Map<AbilityType, Ability> allAbilities;

    private AbilityFactory() {
        allAbilities = new HashMap<>();
        initiateAbilities();
    }

    private static class DeflectDmg {
        public static final float KNIGHT = 0.4f;
        public static final float PYRO = 0.3f;
        public static final float ROGUE = 0.2f;
        public static final float BASE = 0.35f;
        public static final float LVL_SCALE = 0.2f;
    }

    private static class DrainDmg {
        public static final float WIZARD = 0.05f;
        public static final float KNIGHT = 0.2f;
        public static final float PYRO = -0.1f;
        public static final float ROGUE = -0.2f;
        public static final float BASE = 0.2f;
        public static final float LVL_SCALE = 0.5f;
    }

    private static class FireblastDmg {
        public static final float WIZARD = 0.05f;
        public static final float KNIGHT = 0.2f;
        public static final float PYRO = -0.1f;
        public static final float ROGUE = -0.2f;
        public static final float BASE = 350;
        public static final float LVL_SCALE = 50;
    }

    private static class IgniteDmg {
        public static final float WIZARD = 0.05f;
        public static final float KNIGHT = 0.2f;
        public static final float PYRO = -0.1f;
        public static final float ROGUE = -0.2f;
        public static final float BASE = 150;
        public static final float LVL_SCALE = 20;
        public static final float DOT_DMG = 50;
        public static final int DOT_DURATION = 2;
        public static final float DOT_SCALE = 30;
    }

    public static AbilityFactory getInstance() {
        if(instance == null) {
            instance = new AbilityFactory();
        }
        return instance;
    }


    private void initiateAbilities() {
        Deflect deflect = new Deflect(DeflectDmg.BASE, DeflectDmg.LVL_SCALE, DeflectDmg.ROGUE, DeflectDmg.KNIGHT, DeflectDmg.PYRO);
        Drain drain = new Drain(DrainDmg.BASE, DrainDmg.LVL_SCALE, DrainDmg.ROGUE, DrainDmg.KNIGHT, DrainDmg.PYRO, DrainDmg.WIZARD);
        Fireblast fireblast = new Fireblast(FireblastDmg.BASE, FireblastDmg.LVL_SCALE, FireblastDmg.ROGUE, FireblastDmg.KNIGHT, FireblastDmg.PYRO, FireblastDmg.WIZARD);
        Ignite ignite = new Ignite(IgniteDmg.BASE, IgniteDmg.LVL_SCALE, IgniteDmg.ROGUE, IgniteDmg.KNIGHT, IgniteDmg.PYRO, IgniteDmg.WIZARD, IgniteDmg.DOT_DMG, IgniteDmg.DOT_SCALE, IgniteDmg.DOT_DURATION);

        allAbilities.put(deflect.type, deflect);
        allAbilities.put(drain.type, drain);
        allAbilities.put(fireblast.type, fireblast);
        allAbilities.put(ignite.type, ignite);

    }

    public Ability getAbility(AbilityType type) {
        return allAbilities.get(type);
    }
}
