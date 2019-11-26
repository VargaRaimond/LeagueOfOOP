package heroes.abilities;

import heroes.PlayerType;

import java.util.HashMap;
import java.util.Map;

public final class AbilityFactory {

    private static AbilityFactory instance;

    private final Map<AbilityType, Ability> allAbilities;

    private AbilityFactory() {
        allAbilities = new HashMap<>();
        initiateAbilities();
    }

    // Constants for every ability, a player type means the race modifier for that player
    private static class DeflectDmg {
        public static final float KNIGHT = 0.4f;
        public static final float PYRO = 0.3f;
        public static final float ROGUE = 0.2f;
        public static final float WIZARD = 0f;
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

    private static class ExecuteDmg {
        public static final float WIZARD = -0.2f;
        public static final float KNIGHT = 0f;
        public static final float PYRO = 0.1f;
        public static final float ROGUE = 0.15f;
        public static final float BASE = 200;
        public static final float LVL_SCALE = 30;
        public static final float BASE_INST_KILL = 0.2f;
    }

    private static class SlamDmg {
        public static final float WIZARD = 0.05f;
        public static final float KNIGHT = 0.2f;
        public static final float PYRO = -0.1f;
        public static final float ROGUE = -0.2f;
        public static final float BASE = 100;
        public static final float LVL_SCALE = 40;
        public static final int STUN_DURATION = 1;
    }

    private static class BackstabDmg {
        public static final float WIZARD = 0.25f;
        public static final float KNIGHT = -0.1f;
        public static final float PYRO = 0.25f;
        public static final float ROGUE = 0.2f;
        public static final float BASE = 200;
        public static final float LVL_SCALE = 20;
        public static final int ROUNDS_TO_CRIT = 3;
    }

    private static class ParalysisDmg {
        public static final float WIZARD = 0.25f;
        public static final float KNIGHT = -0.2f;
        public static final float PYRO = 0.2f;
        public static final float ROGUE = -0.1f;
        public static final float DOT_DMG = 40;
        public static final int DOT_DURATION = 3;
        public static final float DOT_SCALE = 10;
    }

    public static AbilityFactory getInstance() {
        if (instance == null) {
            instance = new AbilityFactory();
        }
        return instance;
    }


    private void initiateAbilities() {
        Map<PlayerType, Float> specs;

        // Create Wizard spells
        specs = raceModif(DeflectDmg.ROGUE, DeflectDmg.KNIGHT, DeflectDmg.PYRO, DeflectDmg.WIZARD);
        Deflect deflect = new Deflect(DeflectDmg.BASE, DeflectDmg.LVL_SCALE, specs);
        specs = raceModif(DrainDmg.ROGUE, DrainDmg.KNIGHT, DrainDmg.PYRO, DrainDmg.WIZARD);
        Drain drain = new Drain(DrainDmg.BASE, DrainDmg.LVL_SCALE, specs);

        // Create Pyromancer spells
        specs = raceModif(FireblastDmg.ROGUE, FireblastDmg.KNIGHT, FireblastDmg.PYRO,
                FireblastDmg.WIZARD);
        Fireblast fireblast = new Fireblast(FireblastDmg.BASE, FireblastDmg.LVL_SCALE, specs);
        specs = raceModif(IgniteDmg.ROGUE, IgniteDmg.KNIGHT, IgniteDmg.PYRO, IgniteDmg.WIZARD);
        Ignite ignite = new Ignite(IgniteDmg.BASE, IgniteDmg.LVL_SCALE, specs, IgniteDmg.DOT_DMG,
                IgniteDmg.DOT_SCALE, IgniteDmg.DOT_DURATION);

        // Create Knight spells
        specs = raceModif(ExecuteDmg.ROGUE, ExecuteDmg.KNIGHT, ExecuteDmg.PYRO, ExecuteDmg.WIZARD);
        Execute execute = new Execute(ExecuteDmg.BASE, ExecuteDmg.LVL_SCALE, specs,
                ExecuteDmg.BASE_INST_KILL);
        specs = raceModif(SlamDmg.ROGUE, SlamDmg.KNIGHT, SlamDmg.PYRO, SlamDmg.WIZARD);
        Slam slam = new Slam(SlamDmg.BASE, SlamDmg.LVL_SCALE, specs, SlamDmg.STUN_DURATION);

        // Create Rogue spells
        specs = raceModif(BackstabDmg.ROGUE, BackstabDmg.KNIGHT, BackstabDmg.PYRO,
                BackstabDmg.WIZARD);
        Backstab backStab = new Backstab(BackstabDmg.BASE, BackstabDmg.LVL_SCALE, specs,
                BackstabDmg.ROUNDS_TO_CRIT);
        specs = raceModif(ParalysisDmg.ROGUE, ParalysisDmg.KNIGHT, ParalysisDmg.PYRO,
                ParalysisDmg.WIZARD);
        Paralysis paralysis = new Paralysis(specs, ParalysisDmg.DOT_DMG, ParalysisDmg.DOT_SCALE,
                ParalysisDmg.DOT_DURATION);

        allAbilities.put(deflect.type, deflect);
        allAbilities.put(drain.type, drain);
        allAbilities.put(fireblast.type, fireblast);
        allAbilities.put(ignite.type, ignite);
        allAbilities.put(execute.type, execute);
        allAbilities.put(slam.type, slam);
        allAbilities.put(backStab.type, backStab);
        allAbilities.put(paralysis.type, paralysis);
    }

    private Map<PlayerType, Float> raceModif(final float rogue, final float knight,
                                                 final float pyro, final float wizard) {
        Map<PlayerType, Float> modifiers = new HashMap<>();
        modifiers.put(PlayerType.Rogue, rogue);
        modifiers.put(PlayerType.Knight, knight);
        modifiers.put(PlayerType.Pyromancer, pyro);
        modifiers.put(PlayerType.Wizard, wizard);
        return modifiers;
    }

    public Ability getAbility(final AbilityType type) {
        return allAbilities.get(type);
    }
}
