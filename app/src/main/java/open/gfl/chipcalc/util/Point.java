package open.gfl.chipcalc.util;

import java.io.Serializable;

public class Point implements Serializable {
    public final int x;
    public final int y;

    public Point(int i, int i2) {
        this.x = i;
        this.y = i2;
    }
}
