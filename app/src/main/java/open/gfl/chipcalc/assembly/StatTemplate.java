package open.gfl.chipcalc.assembly;

import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.util.Stat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import open.gfl.chipcalc.CipherSuite;

public class StatTemplate {
    public static final Map<String, ArrayList<StatTemplate>> PRESET = new HashMap<String, ArrayList<StatTemplate>>() {
        {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new StatTemplate(Chip.TYPE_5A, Board.NAME_BGM71, 5, new Stat(), new Stat(5)));
            arrayList.add(new StatTemplate(Chip.TYPE_5B, new Stat(CipherSuite.TLS_RSA_WITH_AES_256_GCM_SHA384, 328, CipherSuite.TLS_DH_anon_WITH_CAMELLIA_128_CBC_SHA256, 45), new Stat(13, 10, 10, 3), new Stat(1, 0, 0, 0), new Stat(3, 3, 3, 3)));
            arrayList.add(new StatTemplate(Chip.TYPE_5B, new Stat(CipherSuite.TLS_DHE_DSS_WITH_CAMELLIA_128_CBC_SHA256, 328, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA, 45), new Stat(16, 10, 7, 3), new Stat(1, 0, 1, 0), new Stat(4, 3, 1, 3)));
            put(Board.NAME_BGM71, arrayList);
            ArrayList arrayList2 = new ArrayList();
            arrayList2.add(new StatTemplate(Chip.TYPE_5A, Board.NAME_AGS30, 5, new Stat(), new Stat(3, 5, 5, 5)));
            put(Board.NAME_AGS30, arrayList2);
            ArrayList arrayList3 = new ArrayList();
            arrayList3.add(new StatTemplate(Chip.TYPE_5A, Board.NAME_2B14, 5, new Stat(), new Stat(5)));
            arrayList3.add(new StatTemplate(Chip.TYPE_5A, new Stat(227, 33, 90, 90), new Stat(20, 1, 5, 6), new Stat(2, 0, 0, 0), new Stat(5, 1, 5, 3)));
            arrayList3.add(new StatTemplate(Chip.TYPE_5A, new Stat(227, 58, 80, 90), new Stat(20, 2, 4, 6), new Stat(2, 0, 0, 0), new Stat(5, 2, 1, 3)));
            arrayList3.add(new StatTemplate(Chip.TYPE_5B, new Stat(220, 58, 90, 90), new Stat(19, 2, 5, 6), new Stat(2, 0, 0, 0), new Stat(4, 2, 3, 3)));
            put(Board.NAME_2B14, arrayList3);
            ArrayList arrayList4 = new ArrayList();
            arrayList4.add(new StatTemplate(Chip.TYPE_4, Board.NAME_M2, 5, new Stat(), new Stat(5, 2, 5, 5)));
            put(Board.NAME_M2, arrayList4);
            ArrayList arrayList5 = new ArrayList();
            arrayList5.add(new StatTemplate(Chip.TYPE_5A, Board.NAME_AT4, 5, new Stat(), new Stat(5)));
            arrayList5.add(new StatTemplate(Chip.TYPE_5A, new Stat(CipherSuite.TLS_DH_anon_WITH_AES_256_GCM_SHA384, 261, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, 65), new Stat(14, 8, 9, 5), new Stat(1, 0, 0, 0), new Stat(4, 3, 2, 4)));
            arrayList5.add(new StatTemplate(Chip.TYPE_6, new Stat(CipherSuite.TLS_DH_anon_WITH_AES_128_GCM_SHA256, 261, CipherSuite.TLS_PSK_WITH_AES_128_CBC_SHA256, 65), new Stat(14, 8, 9, 5), new Stat(1, 0, 1, 0), new Stat(3, 3, 2, 4)));
            put(Board.NAME_AT4, arrayList5);
            ArrayList arrayList6 = new ArrayList();
            arrayList6.add(new StatTemplate(Chip.TYPE_5B, Board.NAME_QLZ04, 5, new Stat(), new Stat(3, 4, 3, 4)));
            put(Board.NAME_QLZ04, arrayList6);
        }
    };
    public final boolean isMaxStat;
    public final Stat pt;
    public final Stat ptMax;
    public final Stat ptMin;
    public final Stat stat;
    public final String typeMin;

    private StatTemplate(String str, String str2, int i, Stat stat2, Stat stat3) {
        this.stat = Board.getMaxStat(str2, i);
        this.pt = Board.getMaxPt(str2, i);
        this.ptMin = stat2;
        this.ptMax = stat3;
        this.typeMin = str;
        this.isMaxStat = true;
    }

    private StatTemplate(String str, Stat stat2, Stat stat3, Stat stat4, Stat stat5) {
        this.stat = stat2;
        this.pt = stat3;
        this.ptMin = stat4;
        this.ptMax = stat5;
        this.typeMin = str;
        this.isMaxStat = false;
    }

    public String toString(String str) {
        return this.isMaxStat ? str : this.pt.toString();
    }
}
