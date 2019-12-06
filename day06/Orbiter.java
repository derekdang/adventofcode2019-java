import java.util.Set;

public class Orbiter {
    String name;
    Orbiter parent;
    Set<Orbiter> indirects;

    public Orbiter(String name, Orbiter parent, Set<Orbiter> indirects) {
        this.name = name;
        this.parent = parent;
        this.indirects = indirects;
    }
}
