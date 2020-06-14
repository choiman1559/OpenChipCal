package open.gfl.chipcalc.puzzle;

import open.gfl.chipcalc.util.Rational;
import open.gfl.chipcalc.util.Stat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.bouncycastle.asn1.eac.CertificateBody;

public class Chip implements Serializable {
    /* access modifiers changed from: private */
    public static final Map<String, Boolean[][]> CHIP_MATRIX_MAP = new HashMap<String, Boolean[][]>() {
        {
            Boolean valueOf = Boolean.valueOf(Chip.O);
            put(Chip.TYPE_1, new Boolean[][]{new Boolean[]{valueOf}});
            put(Chip.TYPE_2, new Boolean[][]{new Boolean[]{valueOf}, new Boolean[]{valueOf}});
            put("3I", new Boolean[][]{new Boolean[]{valueOf}, new Boolean[]{valueOf}, new Boolean[]{valueOf}});
            put("3L", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}});
            put("4I", new Boolean[][]{new Boolean[]{valueOf, valueOf, valueOf, valueOf}});
            put("4L", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}});
            put("4Lm", new Boolean[][]{new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, valueOf, valueOf}});
            put("4O", new Boolean[][]{new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, valueOf}});
            put("4T", new Boolean[][]{new Boolean[]{false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}});
            put("4Z", new Boolean[][]{new Boolean[]{valueOf, valueOf, false}, new Boolean[]{false, valueOf, valueOf}});
            put("4Zm", new Boolean[][]{new Boolean[]{false, valueOf, valueOf}, new Boolean[]{valueOf, valueOf, false}});
            put("5C", new Boolean[][]{new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{valueOf, false, valueOf}});
            put("5I", new Boolean[][]{new Boolean[]{valueOf, valueOf, valueOf, valueOf, valueOf}});
            put("5L", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, false}, new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}});
            put("5Lm", new Boolean[][]{new Boolean[]{false, valueOf}, new Boolean[]{false, valueOf}, new Boolean[]{false, valueOf}, new Boolean[]{valueOf, valueOf}});
            put("5P", new Boolean[][]{new Boolean[]{false, valueOf}, new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, valueOf}});
            put("5Pm", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, valueOf}});
            put("5V", new Boolean[][]{new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, valueOf, valueOf}});
            put("5Z", new Boolean[][]{new Boolean[]{false, false, valueOf}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{valueOf, false, false}});
            put("5Zm", new Boolean[][]{new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{false, false, valueOf}});
            put("5F", new Boolean[][]{new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{false, valueOf, false}});
            put("5Fm", new Boolean[][]{new Boolean[]{false, false, valueOf}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{false, valueOf, false}});
            put("5N", new Boolean[][]{new Boolean[]{false, valueOf}, new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, false}, new Boolean[]{valueOf, false}});
            put("5Nm", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}, new Boolean[]{false, valueOf}, new Boolean[]{false, valueOf}});
            put("5T", new Boolean[][]{new Boolean[]{false, valueOf, false}, new Boolean[]{false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}});
            put("5W", new Boolean[][]{new Boolean[]{false, valueOf, valueOf}, new Boolean[]{valueOf, valueOf, false}, new Boolean[]{valueOf, false, false}});
            put("5X", new Boolean[][]{new Boolean[]{false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{false, valueOf, false}});
            put("5Y", new Boolean[][]{new Boolean[]{false, valueOf}, new Boolean[]{valueOf, valueOf}, new Boolean[]{false, valueOf}, new Boolean[]{false, valueOf}});
            put("5Ym", new Boolean[][]{new Boolean[]{valueOf, false}, new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, false}, new Boolean[]{valueOf, false}});
            put("6A", new Boolean[][]{new Boolean[]{valueOf, false, false}, new Boolean[]{valueOf, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}});
            put("6C", new Boolean[][]{new Boolean[]{valueOf, false, false, valueOf}, new Boolean[]{valueOf, valueOf, valueOf, valueOf}});
            put("6D", new Boolean[][]{new Boolean[]{false, valueOf, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf, valueOf}});
            put("6I", new Boolean[][]{new Boolean[]{valueOf, valueOf, valueOf, valueOf, valueOf, valueOf}});
            put("6O", new Boolean[][]{new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, valueOf}, new Boolean[]{valueOf, valueOf}});
            put("6R", new Boolean[][]{new Boolean[]{false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{valueOf, valueOf, false}});
            put("6T", new Boolean[][]{new Boolean[]{false, false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf, valueOf}, new Boolean[]{false, false, valueOf, false}});
            put("6Y", new Boolean[][]{new Boolean[]{false, valueOf, false}, new Boolean[]{valueOf, valueOf, valueOf}, new Boolean[]{valueOf, false, valueOf}});
            put("6Z", new Boolean[][]{new Boolean[]{valueOf, valueOf, valueOf, false}, new Boolean[]{false, valueOf, valueOf, valueOf}});
            put("6Zm", new Boolean[][]{new Boolean[]{false, valueOf, valueOf, valueOf}, new Boolean[]{valueOf, valueOf, valueOf, false}});
        }
    };

    private static final Map<String, Integer> CHIP_ROTATION_MAP = new HashMap<String, Integer>() {
        {
            String[][] strArr = Chip.NAMES_N;
            for (String[] strArr2 : strArr) {
                for (Object obj : strArr2) {
                    PuzzleMatrix puzzleMatrix = new PuzzleMatrix((Object[][]) Chip.CHIP_MATRIX_MAP.get(obj));
                    for (int i3 = Chip.PT; i3 <= 4; i3 += Chip.PT) {
                        PuzzleMatrix puzzleMatrix2 = new PuzzleMatrix((Object[][]) Chip.CHIP_MATRIX_MAP.get(obj));
                        puzzleMatrix2.rotate(i3);
                        if (puzzleMatrix.equals(puzzleMatrix2)) {
                            put((String) obj, Integer.valueOf(i3));
                            break;
                        }
                    }
                }
            }
        }
    };
    public static final int COLOR_BLUE = 1;
    public static final int COLOR_NA = -1;
    public static final int COLOR_ORANGE = 0;
    public static final int LEVEL_MAX = 20;
    private static final Map<Integer, String> MAP_GRID = new HashMap<Integer, String>() {
        {
            int i = 39;
            for (String access$100 : Chip.TYPES) {
                for (String put : Chip.getNames(access$100)) {
                    put(Integer.valueOf(i), put);
                    i--;
                }
            }
        }
    };
    private static final String[] NAMES_1;
    private static final String[] NAMES_2;
    private static final String[] NAMES_3;
    private static final String[] NAMES_4;
    private static final String[] NAMES_5A;
    private static final String[] NAMES_5B;
    private static final String[] NAMES_6;
    public static final String[][] NAMES_N;
    public static final String NAME_DEFAULT;
    private static final boolean O = true;
    private static final int PT = 1;
    public static final int PT_MAX = 5;
    public static final Rational[] RATES;
    public static final Rational RATE_BRK = new Rational(CertificateBody.profileType, 10);
    public static final Rational RATE_DMG = new Rational(44, 10);
    public static final Rational RATE_HIT = new Rational(71, 10);
    public static final Rational RATE_RLD;
    public static final int SIZE_MAX = 6;
    public static final int STAR_MAX = 5;
    public static final int STAR_MIN = 2;
    private static final int STAT = 0;
    public static final String TYPE_1 = "1";
    public static final String TYPE_2 = "2";
    public static final String TYPE_3 = "3";
    public static final String TYPE_4 = "4";
    public static final String TYPE_5A = "5A";
    public static final String TYPE_5B = "5B";
    public static final String TYPE_6 = "6";
    public static final String[] TYPES = {TYPE_6, TYPE_5B, TYPE_5A, TYPE_4, TYPE_3, TYPE_2, TYPE_1};
    private static final boolean X = false;
    public final int color;
    public final String hoc;
    public final String id;
    public final int initLevel;
    public final int initRotation;
    public int level;
    public final String name;
    public final Stat pt;
    public int rotation;
    public final int star;

    static {
        Rational rational = new Rational(57, 10);
        RATE_RLD = rational;
        RATES = new Rational[]{RATE_DMG, RATE_BRK, RATE_HIT, rational};
        String[] strArr = {"6R", "6C", "6I", "6T", "6Y", "6Zm", "6Z", "6D", "6A", "6O"};
        NAMES_6 = strArr;
        String[] strArr2 = {"5Fm", "5F", "5T", "5X", "5Y", "5Ym", "5N", "5Nm", "5W"};
        NAMES_5B = strArr2;
        String[] strArr3 = {"5Lm", "5L", "5V", "5Zm", "5Z", "5C", "5I", "5P", "5Pm"};
        NAMES_5A = strArr3;
        String[] strArr4 = {"4T", "4Z", "4Zm", "4L", "4Lm", "4O", "4I"};
        NAMES_4 = strArr4;
        String[] strArr5 = {"3L", "3I"};
        NAMES_3 = strArr5;
        String[] strArr6 = {TYPE_2};
        NAMES_2 = strArr6;
        String[] strArr7 = {TYPE_1};
        NAMES_1 = strArr7;
        NAMES_N = new String[][]{strArr, strArr2, strArr3, strArr4, strArr5, strArr6, strArr7};
        NAME_DEFAULT = strArr7[0];
    }

    public Chip(Chip chip) {
        this.id = chip.id;
        this.name = chip.name;
        this.pt = chip.pt;
        this.initRotation = chip.initRotation;
        this.initLevel = chip.initLevel;
        this.star = chip.star;
        this.color = chip.color;
        this.level = chip.level;
        this.rotation = chip.rotation;
        this.hoc = chip.hoc;
    }

    public Chip(String str, String str2, int i, int i2, Stat stat, int i3, int i4, String str3) {
        this.id = str;
        this.name = str2;
        this.pt = stat;
        this.initRotation = i4;
        this.initLevel = i3;
        this.star = i;
        this.color = i2;
        this.level = i3;
        this.rotation = i4;
        this.hoc = str3;
    }

    public Stat getStat() {
        return new Stat(getStat(RATE_DMG, this, this.pt.dmg), getStat(RATE_BRK, this, this.pt.brk), getStat(RATE_HIT, this, this.pt.hit), getStat(RATE_RLD, this, this.pt.rld));
    }

    private static int getStat(Rational rational, Chip chip, int i) {
        return getStat(rational, getType(chip.name), chip.star, chip.level, i);
    }

    private static int getStat(Rational rational, String str, int i, int i2, int i3) {
        return getLevelMult(i2).mult(new Rational(i3).mult(rational).mult(getTypeMult(str, i)).getIntCeil()).getIntCeil();
    }

    static int getMaxEffStat(Rational rational, int i) {
        return getLevelMult(20).mult(new Rational(i).mult(rational).getIntCeil()).getIntCeil();
    }

    private static Rational getLevelMult(int i) {
        return i < 10 ? new Rational(i).mult(8, 100).add(1) : new Rational(i).mult(7, 100).add(11, 10);
    }

    private static Rational getTypeMult(String str, int i) {
        int i2 = getSize(str) < 5 ? 16 : 20;
        int i3 = 0;
        int i4 = 4;
        int i5 = (getSize(str) < 3 || TYPE_5A.equals(str)) ? 4 : 0;
        if (getSize(str) >= 4 && !TYPE_5A.equals(str)) {
            i4 = 0;
        }
        int i6 = (i2 * i) - i5;
        if (3 < i) {
            i3 = i4;
        }
        return new Rational(i6 - i3, 100);
    }

    private static String getType(String str) {
        if (str.matches("5[C|I|L|V|P|Z]m?")) {
            return TYPE_5A;
        }
        if (getSize(str) == 5) {
            return TYPE_5B;
        }
        return str.substring(0, 1);
    }

    public int getSize() {
        return getSize(this.name);
    }

    private static int getSize(String str) {
        return Integer.parseInt(str.substring(0, 1));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0033, code lost:
        r3 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:?, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003c, code lost:
        throw r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.ArrayList<open.gfl.chipcalc.puzzle.Chip> readChip_file(android.content.Context r2, android.net.Uri r3) {
        return null; //temporary
        //TODO : restore
     }

    /* access modifiers changed from: private */
    public static String[] getNames(String str) {
        return NAMES_N[Arrays.asList(TYPES).indexOf(str)];
    }

    public static ArrayList<Chip> parseAndFilter(String str) {
        try {
            ArrayList<Chip> arrayList = new ArrayList<>();
            Iterator<Chip> it = parse(str).iterator();
            while (it.hasNext()) {
                Chip next = it.next();
                if (next.star == 5) {
                    arrayList.add(next);
                }
            }
            return arrayList;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0079 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:7:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.ArrayList<open.gfl.chipcalc.puzzle.Chip> parse(java.lang.String r21) throws org.json.JSONException {
        return null; //temporary
        //TODO : restore
       }

    static PuzzleMatrix initMatrix(String str, int i) {
        PuzzleMatrix puzzleMatrix = new PuzzleMatrix((Object[][]) CHIP_MATRIX_MAP.get(str));
        puzzleMatrix.rotate(i);
        return puzzleMatrix;
    }

    public void setMaxLevel() {
        this.level = 20;
    }

    public void resetLevel() {
        this.level = this.initLevel;
    }

    public void setRotation(int i) {
        this.rotation = i % CHIP_ROTATION_MAP.get(this.name).intValue();
    }

    public void resetRotation() {
        this.rotation = this.initRotation;
    }

    int getRotation() {
        return this.rotation;
    }

    public boolean rotated() {
        if (this.rotation != this.initRotation) {
            return O;
        }
        return false;
    }

    int getNumTicket() {
        if (this.rotation != this.initRotation) {
            return this.star * 10;
        }
        return 0;
    }

    int getCumulXP() {
        int i = 0;
        for (int i2 = this.initLevel + 1; i2 <= 20; i2++) {
            i += getXP(this.star, i2);
        }
        return i;
    }

    private static int getXP(int i, int i2) {
        int i3 = 0x0096;
        int i4 = ((i2 - 1) * 75) + 0x0096 + (6 <= i2 ? (i2 - 5) * 75 : 0) + (17 <= i2 ? 0x0096 : 0);
        if (20 != i2) {
            i3 = 0;
        }
        return ((i4 + i3) * Math.max(0, i - 2)) / 3;
    }

    public PuzzleMatrix<Boolean> getMatrix() {
        return generateMatrix(this.name, this.rotation);
    }

    private static PuzzleMatrix<Boolean> generateMatrix(String str, int i) {
        PuzzleMatrix<Boolean> puzzleMatrix = new PuzzleMatrix((Object[][]) CHIP_MATRIX_MAP.get(str));
        puzzleMatrix.rotate(i);
        return puzzleMatrix;
    }
}
