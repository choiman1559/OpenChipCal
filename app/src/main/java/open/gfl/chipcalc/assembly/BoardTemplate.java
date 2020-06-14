package open.gfl.chipcalc.assembly;

import androidx.annotation.NonNull;

import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.util.Point;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class BoardTemplate {
    final boolean isSymmetric;
    private final List<Point> locations;
    final Map<String, Integer> nameCountMap = new HashMap();
    private final List<String> names;
    private final List<Integer> rotations;

    List<Point> getLocations() {
        return new ArrayList(this.locations);
    }

    int getRotation(int i) {
        return this.rotations.get(i).intValue();
    }

    private BoardTemplate(String str, int i, List<String> list, List<Integer> list2, List<Point> list3, @NonNull boolean z) {
        this.names = list;
        for (String next : list) {
            if (!this.nameCountMap.containsKey(next)) {
                this.nameCountMap.put(next, 0);
            }
            Map<String, Integer> map = this.nameCountMap;
            map.put(next, Integer.valueOf(map.get(next).intValue() + 1));
        }
        this.rotations = list2;
        this.locations = list3;
        this.isSymmetric = z;
    }

    public static Set<BoardTemplate> loadTemplates(String str, int i) {
        HashSet hashSet = new HashSet();
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(BoardTemplate.class.getClassLoader().getResourceAsStream("res/raw/preset_" + str.toLowerCase().replace("-", "") + "_" + i + ".dat")));
            while (bufferedReader.ready()) {
                try {
                    String[] split = bufferedReader.readLine().split(";");
                    boolean equals = Chip.TYPE_1.equals(split[3]);
                    List asList = Arrays.asList(split[0].split(","));
                    ArrayList arrayList = new ArrayList(asList.size());
                    for (String valueOf : split[1].split(",")) {
                        arrayList.add(Integer.valueOf(valueOf));
                    }
                    ArrayList arrayList2 = new ArrayList(asList.size());
                    for (String split2 : split[2].split(",")) {
                        String[] split3 = split2.split("\\.");
                        arrayList2.add(new Point(Integer.parseInt(split3[0]), Integer.parseInt(split3[1])));
                    }
                    hashSet.add(new BoardTemplate(str, i, asList, arrayList, arrayList2, equals));
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    bufferedReader.close();
                    throw th3;
                }
            }
            bufferedReader.close();
        } catch (IOException unused) {
        } catch (Throwable th4) {
            new Throwable().addSuppressed(th4);
        }
        return hashSet;
    }
}
