package open.gfl.chipcalc.puzzle;

import open.gfl.chipcalc.util.Point;
import open.gfl.chipcalc.util.Rational;
import open.gfl.chipcalc.util.Stat;
import open.gfl.chipcalc.util.StrIntMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.commons.httpclient.HttpStatus;
import org.bouncycastle.asn1.eac.EACTags;
import open.gfl.chipcalc.CipherSuite;

public class Board implements Serializable {
    public static int EMPTY = -1;
    public static final String NAME_2B14 = "2B14";
    public static final String NAME_AGS30 = "AGS-30";
    public static final String NAME_AT4 = "AT4";
    public static final String NAME_BGM71 = "BGM-71";
    public static final String NAME_M2 = "M2";
    public static final String NAME_QLZ04 = "QLZ-04";
    public static String[] NAMES = {NAME_BGM71, NAME_AGS30, NAME_2B14, NAME_M2, NAME_AT4, NAME_QLZ04};
    public static int UNUSED = -2;
    private final List<Point> chipLocs;
    public final ArrayList<Chip> chips;
    private final Stat maxStat;
    public final String name;
    public final int star;
    private final Stat stat;
    public final double statPerc;
    public final int ticket;
    public final int xp;

    public static final Map<String, Integer> MAP_COLOR = new HashMap<String, Integer>() {
        {
            put(Board.NAME_BGM71, 1);
            put(Board.NAME_AGS30, 0);
            put(Board.NAME_2B14, 0);
            put(Board.NAME_M2, 1);
            put(Board.NAME_AT4, 1);
            put(Board.NAME_QLZ04, 0);
        }
    };
    private static final Map<String, Integer[][]> MAP_MATRIX = new HashMap<String, Integer[][]>() {
        {
            put(Board.NAME_BGM71, new Integer[][]{new Integer[]{6, 6, 6, 6, 6, 6, 6, 6}, new Integer[]{6, 4, 4, 4, 3, 3, 3, 6}, new Integer[]{6, 4, 1, 1, 1, 1, 2, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 2, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 2, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 5, 6}, new Integer[]{6, 3, 3, 3, 5, 5, 5, 6}, new Integer[]{6, 6, 6, 6, 6, 6, 6, 6}});
            put(Board.NAME_AGS30, new Integer[][]{new Integer[]{6, 6, 5, 5, 6, 6, 6, 6}, new Integer[]{6, 3, 3, 2, 2, 6, 6, 6}, new Integer[]{4, 3, 1, 1, 1, 1, 6, 6}, new Integer[]{4, 2, 1, 1, 1, 1, 2, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 2, 4}, new Integer[]{6, 6, 1, 1, 1, 1, 3, 4}, new Integer[]{6, 6, 6, 2, 2, 3, 3, 6}, new Integer[]{6, 6, 6, 6, 5, 5, 6, 6}});
            put(Board.NAME_2B14, new Integer[][]{new Integer[]{6, 6, 6, 6, 6, 6, 6, 6}, new Integer[]{6, 6, 5, 6, 6, 5, 6, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 3, 6}, new Integer[]{4, 2, 1, 1, 1, 1, 3, 4}, new Integer[]{4, 2, 1, 1, 1, 1, 3, 4}, new Integer[]{6, 2, 1, 1, 1, 1, 3, 6}, new Integer[]{6, 6, 5, 6, 6, 5, 6, 6}, new Integer[]{6, 6, 6, 6, 6, 6, 6, 6}});
            put(Board.NAME_M2, new Integer[][]{new Integer[]{5, 3, 3, 6, 6, 6, 6, 5}, new Integer[]{6, 3, 1, 1, 6, 6, 2, 4}, new Integer[]{6, 6, 1, 1, 6, 2, 2, 4}, new Integer[]{6, 6, 1, 1, 1, 1, 2, 6}, new Integer[]{6, 2, 1, 1, 1, 1, 6, 6}, new Integer[]{4, 2, 2, 6, 1, 1, 6, 6}, new Integer[]{4, 2, 6, 6, 1, 1, 3, 6}, new Integer[]{5, 6, 6, 6, 6, 3, 3, 5}});
            put(Board.NAME_AT4, new Integer[][]{new Integer[]{6, 6, 6, 1, 1, 6, 6, 6}, new Integer[]{6, 6, 1, 1, 1, 1, 6, 6}, new Integer[]{6, 1, 1, 1, 1, 1, 1, 6}, new Integer[]{2, 1, 1, 6, 6, 1, 1, 3}, new Integer[]{2, 2, 2, 6, 6, 3, 3, 3}, new Integer[]{6, 2, 2, 4, 4, 3, 3, 6}, new Integer[]{6, 6, 5, 4, 4, 5, 6, 6}, new Integer[]{6, 6, 6, 5, 5, 6, 6, 6}});
            put(Board.NAME_QLZ04, new Integer[][]{new Integer[]{6, 6, 6, 6, 6, 6, 6, 6}, new Integer[]{5, 3, 6, 6, 6, 6, 3, 5}, new Integer[]{5, 3, 3, 6, 6, 3, 3, 5}, new Integer[]{4, 1, 1, 1, 1, 1, 1, 4}, new Integer[]{4, 1, 1, 1, 1, 1, 1, 4}, new Integer[]{6, 1, 1, 2, 2, 1, 1, 6}, new Integer[]{6, 6, 2, 2, 2, 2, 6, 6}, new Integer[]{6, 6, 6, 2, 2, 6, 6, 6}});
        }
    };
    private static final Map<String, Stat[]> MAP_MAX = new HashMap<String, Stat[]>() {
        {
            put(Board.NAME_BGM71, new Stat[]{new Stat(95, CipherSuite.TLS_DH_DSS_WITH_AES_256_GCM_SHA384, 96, 23), new Stat(114, 198, 115, 28), new Stat(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 231, CipherSuite.TLS_DH_RSA_WITH_CAMELLIA_256_CBC_SHA, 32), new Stat(CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 280, CipherSuite.TLS_DHE_DSS_WITH_AES_128_GCM_SHA256, 39), new Stat(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 329, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 46)});
            put(Board.NAME_AGS30, new Stat[]{new Stat(53, 65, 60, 117), new Stat(64, 78, 72, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), new Stat(75, 91, 84, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384), new Stat(90, 111, 102, 198), new Stat(106, 130, 120, 233)});
            put(Board.NAME_2B14, new Stat[]{new Stat(114, 29, 45, 54), new Stat(CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_256_CBC_SHA, 35, 54, 64), new Stat(CipherSuite.TLS_DHE_RSA_WITH_AES_256_GCM_SHA384, 41, 63, 75), new Stat(CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA256, 49, 77, 91), new Stat(227, 58, 90, 107)});
            put(Board.NAME_M2, new Stat[]{new Stat(103, 30, 49, 74), new Stat(124, 36, 59, 89), new Stat(CipherSuite.TLS_DHE_PSK_WITH_AES_256_CBC_SHA, 42, 68, 104), new Stat(CipherSuite.TLS_PSK_WITH_NULL_SHA256, 51, 83, EACTags.NON_INTERINDUSTRY_DATA_OBJECT_NESTING_TEMPLATE), new Stat(HttpStatus.SC_PARTIAL_CONTENT, 60, 97, CipherSuite.TLS_RSA_PSK_WITH_AES_128_CBC_SHA)});
            put(Board.NAME_AT4, new Stat[]{new Stat(85, 131, 95, 45), new Stat(102, CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 114, 54), new Stat(118, CipherSuite.TLS_RSA_PSK_WITH_AES_256_CBC_SHA384, CipherSuite.TLS_DH_DSS_WITH_CAMELLIA_256_CBC_SHA, 63), new Stat(CipherSuite.TLS_DHE_PSK_WITH_AES_128_CBC_SHA, 222, CipherSuite.TLS_DH_RSA_WITH_AES_256_GCM_SHA384, 76), new Stat(CipherSuite.TLS_PSK_WITH_AES_256_GCM_SHA384, 261, CipherSuite.TLS_DHE_RSA_WITH_CAMELLIA_128_CBC_SHA256, 90)});
            put(Board.NAME_QLZ04, new Stat[]{new Stat(61, 72, 66, 117), new Stat(73, 86, 79, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA), new Stat(85, 100, 93, CipherSuite.TLS_DHE_DSS_WITH_AES_256_GCM_SHA384), new Stat(103, 122, 112, 198), new Stat(122, CipherSuite.TLS_DHE_PSK_WITH_3DES_EDE_CBC_SHA, CipherSuite.TLS_RSA_WITH_CAMELLIA_256_CBC_SHA, 233)});
        }
    };
    private static final StrIntMap<Integer> MAP_ROTATIONSTEP = new StrIntMap<Integer>() {
        {
            for (String str : Board.NAMES) {
                for (int i = 1; i <= 5; i++) {
                    PuzzleMatrix access$000 = Board.initMatrix(str, i);
                    int i2 = 1;
                    while (true) {
                        if (i2 > 4) {
                            break;
                        }
                        PuzzleMatrix access$0002 = Board.initMatrix(str, i);
                        access$0002.rotateContent(i2, Integer.valueOf(Board.UNUSED));
                        if (access$000.equals(access$0002)) {
                            put(str, i, Integer.valueOf(i2));
                            break;
                        }
                        i2++;
                    }
                }
            }
        }
    };

    public Board(String str, int i, Stat stat2, List<Chip> list, List<Point> list2) {
        this.name = str;
        this.star = i;
        this.chips = new ArrayList<>(list);
        this.chipLocs = list2;
        int i2 = 0;
        int i3 = 0;
        for (Chip next : list) {
            i2 += next.getCumulXP();
            i3 += next.getNumTicket();
        }
        this.xp = i2;
        this.ticket = i3;
        this.maxStat = stat2;
        Stat stat3 = getStat();
        this.stat = stat3;
        this.statPerc = getStatPerc(stat3, stat2);
    }

    public static Stat getMaxPt(String str, int i) {
        return getMaxPt(str, i, getMaxStat(str, i));
    }

    public static Stat getMaxPt(String str, int i, Stat stat2) {
        int[] array = stat2.toArray();
        int[] iArr = new int[4];
        for (Integer intValue : get56ChipCount(str, i)) {
            int intValue2 = intValue.intValue();
            for (int i2 = 0; i2 < 4; i2++) {
                int i3 = 0;
                for (int i4 : getPtDistribution(Chip.RATES[i2], intValue2, array[i2])) {
                    i3 += i4;
                }
                if (iArr[i2] < i3) {
                    iArr[i2] = i3;
                }
            }
        }
        Stat stat3 = new Stat(iArr);
        int cellCount = getCellCount(str, i) - stat3.sum();
        if (cellCount > 0) {
            stat3.add(new Stat(cellCount));
        }
        return stat3;
    }

    private static int[] getPtDistribution(Rational rational, int i, int i2) {
        int i3;
        int maxEffStat = Chip.getMaxEffStat(rational, 1);
        int[] iArr = new int[i];
        int i4 = 0;
        while (calcStat(rational, iArr) < i2) {
            iArr[i4] = iArr[i4] + 1;
            int i5 = iArr[i4];
            if (i5 > 0) {
                int i6 = i5 - 1;
                i3 = (i6 * maxEffStat) - Chip.getMaxEffStat(rational, i6);
            } else {
                i3 = 0;
            }
            int maxEffStat2 = (i5 * maxEffStat) - Chip.getMaxEffStat(rational, i5);
            int i7 = i5 + 1;
            if (maxEffStat2 * 2 < i3 + ((i7 * maxEffStat) - Chip.getMaxEffStat(rational, i7))) {
                i4 = (i4 + 1) % i;
            }
        }
        return iArr;
    }

    private static int calcStat(Rational rational, int[] iArr) {
        int i = 0;
        for (int maxEffStat : iArr) {
            i += Chip.getMaxEffStat(rational, maxEffStat);
        }
        return i;
    }

    private static List<Integer> get56ChipCount(String str, int i) {
        int cellCount = getCellCount(str, i);
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < cellCount / 6; i2++) {
            int i3 = cellCount - (i2 * 6);
            if (i3 % 5 == 0) {
                arrayList.add(Integer.valueOf((i3 / 5) + i2));
            }
        }
        return arrayList;
    }

    private static int getCellCount(String str, int i) {
        return initMatrix(str, i).getNumNotContaining(Integer.valueOf(UNUSED));
    }

    public static Stat getMaxStat(String str, int i) {
        return MAP_MAX.get(str)[limit(i - 1, 0, MAP_MAX.get(str).length)];
    }

    private static int limit(int i, int i2, int i3) {
        return Math.min(Math.max(i, i2), i3);
    }


    public static PuzzleMatrix<Integer> initMatrix(String str, int i) {
        PuzzleMatrix<Integer> puzzleMatrix = new PuzzleMatrix((Object[][]) MAP_MATRIX.get(str));
        for (int i2 = 0; i2 < puzzleMatrix.getNumRow(); i2++) {
            for (int i3 = 0; i3 < puzzleMatrix.getNumCol(); i3++) {
                puzzleMatrix.set(i2, i3, Integer.valueOf(puzzleMatrix.get(i2, i3).intValue() <= i ? EMPTY : UNUSED));
            }
        }
        return puzzleMatrix;
    }

    public PuzzleMatrix<Integer> getMatrix() {
        return toPlacement(this.name, this.star, this.chips, this.chipLocs);
    }

    private static PuzzleMatrix<Integer> toPlacement(String str, int i, List<Chip> list, List<Point> list2) {
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (Chip next : list) {
            arrayList.add(next.name);
            arrayList2.add(Integer.valueOf(next.getRotation()));
        }
        return toPlacement(str, i, arrayList, arrayList2, list2);
    }

    private static PuzzleMatrix<Integer> toPlacement(String str, int i, List<String> list, List<Integer> list2, List<Point> list3) {
        PuzzleMatrix<Integer> initMatrix = initMatrix(str, i);
        for (int i2 = 0; i2 < list.size(); i2++) {
            PuzzleMatrix<Boolean> initMatrix2 = Chip.initMatrix(list.get(i2), list2.get(i2).intValue());
            Set<Point> coords = initMatrix2.getCoords(true);
            Point pivot = initMatrix2.getPivot(true);
            Point point = list3.get(i2);
            for (Point next : coords) {
                Point point2 = new Point((next.x + point.x) - pivot.x, (next.y + point.y) - pivot.y);
                initMatrix.set(point2.x, point2.y, Integer.valueOf(i2));
            }
        }
        return initMatrix;
    }

    private Stat getStat() {
        return Stat.sumStat(this.chips);
    }

    public static double getStatPerc(Stat stat2, Stat stat3) {
        if (stat3.allZero() || stat2.allGeq(stat3)) {
            return 1.0d;
        }
        int[] array = stat2.limit(stat3).toArray();
        int[] array2 = stat3.toArray();
        double d = 0.0d;
        double d2 = 0.0d;
        for (int i = 0; i < 4; i++) {
            d2 += new Rational(array[i]).div(Chip.RATES[i]).getDouble();
            d += new Rational(array2[i]).div(Chip.RATES[i]).getDouble();
        }
        if (d == 0.0d) {
            return 1.0d;
        }
        return d2 / d;
    }
}
