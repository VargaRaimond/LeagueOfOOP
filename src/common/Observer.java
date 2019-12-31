package common;

import angels.Angel;
import heroes.Player;

public interface Observer {
    void update(Player hero);
    void update(Angel angel);
    void update(Player hero1, Player hero2);
    void update(Angel angel, Player hero);
}
