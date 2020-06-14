package open.gfl.chipcalc.util;

import open.gfl.chipcalc.puzzle.Chip;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.httpclient.cookie.CookieSpec;

public class Stat implements Serializable {
    public final int brk;
    public final int dmg;
    public final int hit;
    public final int rld;

    public Stat() {
        this.dmg = 0;
        this.brk = 0;
        this.hit = 0;
        this.rld = 0;
    }

    public Stat(int i) {
        this.dmg = i;
        this.brk = i;
        this.hit = i;
        this.rld = i;
    }

    public Stat(int i, int i2, int i3, int i4) {
        this.dmg = i;
        this.brk = i2;
        this.hit = i3;
        this.rld = i4;
    }

    public Stat(int[] iArr) {
        this.dmg = iArr[0];
        this.brk = iArr[1];
        this.hit = iArr[2];
        this.rld = iArr[3];
    }

    public int[] toArray() {
        return new int[]{this.dmg, this.brk, this.hit, this.rld};
    }

    public int sum() {
        return this.dmg + this.brk + this.hit + this.rld;
    }

    public static Stat sumStat(List<Chip> list) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (Chip stat : list) {
            Stat stat2 = stat.getStat();
            i += stat2.dmg;
            i2 += stat2.brk;
            i3 += stat2.hit;
            i4 += stat2.rld;
        }
        return new Stat(i, i2, i3, i4);
    }

    public static Stat sumPt(List<Chip> list) {
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        for (Chip chip : list) {
            Stat stat = chip.pt;
            i += stat.dmg;
            i2 += stat.brk;
            i3 += stat.hit;
            i4 += stat.rld;
        }
        return new Stat(i, i2, i3, i4);
    }

    public boolean allZero() {
        return this.dmg == 0 && this.brk == 0 && this.hit == 0 && this.rld == 0;
    }

    public boolean allGeq(Stat stat) {
        return stat.dmg <= this.dmg && stat.brk <= this.brk && stat.hit <= this.hit && stat.rld <= this.rld;
    }

    public Stat limit(Stat stat) {
        return new Stat(Math.min(this.dmg, stat.dmg), Math.min(this.brk, stat.brk), Math.min(this.hit, stat.hit), Math.min(this.rld, stat.rld));
    }

    public Stat add(Stat stat) {
        return new Stat(this.dmg + stat.dmg, this.brk + stat.brk, this.hit + stat.hit, this.rld + stat.rld);
    }

    public String toString() {
        return this.dmg + CookieSpec.PATH_DELIM + this.brk + CookieSpec.PATH_DELIM + this.hit + CookieSpec.PATH_DELIM + this.rld;
    }

    public int hashCode() {
        return ((((((371 + this.dmg) * 53) + this.brk) * 53) + this.hit) * 53) + this.rld;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != Stat.class) {
            return false;
        }
        Stat stat = (Stat) obj;
        if (this.dmg == stat.dmg && this.brk == stat.brk && this.hit == stat.hit && this.rld == stat.rld) {
            return true;
        }
        return false;
    }
}
