package open.gfl.chipcalc.assembly;

import org.jetbrains.annotations.NotNull;

import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.puzzle.ChipComparator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CCIterator implements Iterator<List<Chip>> {
    private final Map<String, List<Chip>> candidateMap;
    private final List<int[]> combs = new ArrayList();
    private final List<String> names = new ArrayList();

    CCIterator(Collection<Chip> collection, BoardTemplate boardTemplate) {
        ArrayList<String> arrayList = new ArrayList<>(boardTemplate.nameCountMap.keySet());
        Collections.sort(arrayList, ChipComparator.getInstance());
        for (String str : arrayList) {
            int intValue = boardTemplate.nameCountMap.get(str).intValue();
            this.names.add(str);
            this.combs.add(nCrInit(intValue));
        }
        Set<String> keySet = boardTemplate.nameCountMap.keySet();
        ArrayList arrayList2 = new ArrayList();
        for (Chip next : collection) {
            if (keySet.contains(next.name)) {
                arrayList2.add(next);
            }
        }
        this.candidateMap = genMap(boardTemplate, arrayList2);
    }

    public boolean hasNext() {
        if (this.combs.isEmpty()) {
            return true;
        }
        List<int[]> list = this.combs;
        return list.get(list.size() - 1) != null;
    }

    public List<Chip> next() {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (int i2 = 0; i2 < this.combs.size(); i2++) {
            int[] iArr = this.combs.get(i2);
            List list = this.candidateMap.get(this.names.get(i2));
            for (int i3 : iArr) {
                arrayList.add(list.get(i3));
            }
        }
        while (true) {
            if (i < this.combs.size()) {
                int[] nextComb = nextComb(i);
                if (nextComb != null) {
                    this.combs.set(i, nextComb);
                    break;
                }
                List<int[]> list2 = this.combs;
                list2.set(i, i < list2.size() + -1 ? nCrInit(this.combs.get(i).length) : null);
                i++;
            } else {
                break;
            }
        }
        return arrayList;
    }

    private int[] nextComb(int i) {
        return nCrNext(this.combs.get(i), getSize(this.names.get(i)));
    }

    private int getSize(String str) {
        if (!this.candidateMap.containsKey(str)) {
            return 0;
        }
        return this.candidateMap.get(str).size();
    }

    private static int[] nCrInit(int i) {
        int[] iArr = new int[i];
        for (int i2 = 0; i2 < i; i2++) {
            iArr[i2] = i2;
        }
        return iArr;
    }

    private static int[] nCrNext(int[] iArr, int i) {
        int i2 = i - 1;
        int length = iArr.length;
        while (true) {
            length--;
            if (-1 >= length) {
                return null;
            }
            int i3 = iArr[length] + 1;
            if (i3 > i2) {
                i2--;
            } else {
                int[] iArr2 = new int[iArr.length];
                System.arraycopy(iArr, 0, iArr2, 0, length);
                for (int i4 = length; i4 < iArr.length; i4++) {
                    iArr2[i4] = (i3 + i4) - length;
                }
                return iArr2;
            }
        }
    }

    static Map<String, List<Chip>> genMap(BoardTemplate boardTemplate, List<Chip> list) {
        HashMap hashMap = new HashMap();
        for (Chip next : list) {
            String str = next.name;
            if (boardTemplate.nameCountMap.keySet().contains(str)) {
                if (!hashMap.containsKey(str)) {
                    hashMap.put(str, new ArrayList());
                }
                ((List) hashMap.get(str)).add(next);
            }
        }
        return hashMap;
    }

    static boolean hasEnoughChips(Map<String, List<Chip>> map, @NotNull BoardTemplate boardTemplate) {
        Map<String, Integer> map2 = boardTemplate.nameCountMap;
        for (String next : map2.keySet()) {
            if (getSize(map, next) < map2.get(next)) {
                return false;
            }
        }
        return true;
    }

    private static int getSize(Map<String, List<Chip>> map, String str) {
        if (!map.containsKey(str)) {
            return 0;
        }
        return map.get(str).size();
    }
}
