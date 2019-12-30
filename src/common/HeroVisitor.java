package common;

import heroes.Knight;
import heroes.Pyromancer;
import heroes.Rogue;
import heroes.Wizard;

public interface HeroVisitor {
    void visit(Knight knight);
    void visit(Pyromancer pyromancer);
    void visit(Wizard wizard);
    void visit(Rogue rogue);
}
