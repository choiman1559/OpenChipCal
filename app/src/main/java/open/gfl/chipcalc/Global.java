package open.gfl.chipcalc;

import open.gfl.chipcalc.assembly.BoardTemplate;
import open.gfl.chipcalc.puzzle.Board;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Global {
    public static final String CHANNEL_ID = "open.gfl.chipcalc.notification_channel";
    private static final int[] COLORS_DEFAULT = {-1697461, -12798901, -7911, -12360744, -687567, -7266636, -12128016, -1035546, -4393460, -344386, -16744320, -1655041, -6659292, -1336, -8388608, -5570621, -8355840, -10063, -16777099, -8355712};
    public static final Map<String, Map<Integer, Set<BoardTemplate>>> TEMPLATES = new HashMap<String, Map<Integer, Set<BoardTemplate>>>() {
        {
            for (String str : Board.NAMES) {
                HashMap hashMap = new HashMap();
                put(str, hashMap);
                for (int i = 1; i <= 5; i++) {
                    hashMap.put(Integer.valueOf(i), BoardTemplate.loadTemplates(str, i));
                }
            }
        }
    };

    public static int getIndexColor(int i) {
        int[] iArr = COLORS_DEFAULT;
        return iArr[i % iArr.length];
    }

    public static String fPercStr(double d) {
        return String.format("%.2f", new Object[]{Double.valueOf(d * 100.0d)}) + "%";
    }
}
