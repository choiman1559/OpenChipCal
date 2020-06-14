package open.gfl.chipcalc.puzzle;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static open.gfl.chipcalc.puzzle.Chip.NAMES_N;

public class ChipComparator implements Comparator<String> {
    private static ChipComparator instance;
    private final Map<String, Integer> indices = new HashMap();

    public static ChipComparator getInstance() {
        if (instance == null) {
            instance = new ChipComparator();
        }
        return instance;
    }

    public ChipComparator() {
        int i = 1;
        for (String[] names : NAMES_N) {
            for (String name : names) {
                indices.put(name, i);
                i++;
            }
        }
    }

    public int compare(String str, String str2) {
        if (!str.equals(str2) && this.indices.containsKey(str) && this.indices.containsKey(str2)) {
            return this.indices.get(str).intValue() - this.indices.get(str2).intValue();
        }
        return 0;
    }
}
