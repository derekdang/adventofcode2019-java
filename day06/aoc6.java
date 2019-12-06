import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://adventofcode.com/2019/day/6
 */
public class aoc6 {
    public static void main(String[] args) {
        String fileName = args[0];
        Map<String, Orbiter> orbitMap = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            for (String line; (line = br.readLine()) != null;) {
                String[] split = line.split("[)]");
                String object = split[0];
                String orbiter = split[1];

                Orbiter orbiter1;
                Orbiter orbiter2;
                if (orbitMap.isEmpty()) {
                    orbiter1 = new Orbiter(object, null, new HashSet<>());
                    orbiter2 = new Orbiter(orbiter, orbiter1, new HashSet<>());
                } else {
                    orbiter1 = orbitMap.getOrDefault(object, new Orbiter(object, null, new HashSet<>()));
                    orbiter2 = orbitMap.getOrDefault(orbiter, new Orbiter(orbiter, orbiter1, new HashSet<>()));
                    if (orbiter2.parent == null) orbiter2.parent = orbiter1;
                }

                orbitMap.putIfAbsent(object, orbiter1);
                orbitMap.putIfAbsent(orbiter, orbiter2);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        for (Orbiter orb : orbitMap.values()) {
            if (orb.parent == null) continue;
            Orbiter tmp = orb.parent.parent;
            while(tmp != null) {
                orb.indirects.add(tmp);
                tmp = tmp.parent;
            }
        }

        int directOrbits = 0;
        int indirectOrbits = 0;
        for (Orbiter orb : orbitMap.values()) {
            if (orb.parent != null) directOrbits++;
            indirectOrbits += orb.indirects.size();
        }
        // Answer for part 1 is 333679
        System.out.printf("Total # of direct & indirect orbits: %d\n", directOrbits+indirectOrbits);

        // Answer for part 2 is 370
        findJumps(orbitMap);
    }

    private static void findJumps(Map<String, Orbiter> orbitMap) {
        Orbiter me = orbitMap.get("YOU");
        Orbiter santa = orbitMap.get("SAN");
        Set<Orbiter> intersection = me.indirects;
        intersection.retainAll(santa.indirects);

        int minJumps = Integer.MAX_VALUE;
        for(Orbiter orb : intersection) {
            int myStepsToAncestor = 0;
            int santaStepsToAncestor = 0;

            Orbiter r1 = me.parent;
            Orbiter r2 = santa.parent;
            while(r1 != orb && r1 != null) {
                r1 = r1.parent;
                myStepsToAncestor++;
            }

            while(r2 != orb && r2 != null) {
                r2 = r2.parent;
                santaStepsToAncestor++;
            }

            minJumps = Math.min(myStepsToAncestor+santaStepsToAncestor, minJumps);
        }
        System.out.printf("Minimum orbital transfers required to get from YOU to SAN: %d", minJumps);
    }
}
