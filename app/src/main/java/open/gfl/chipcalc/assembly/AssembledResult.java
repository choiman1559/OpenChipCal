package open.gfl.chipcalc.assembly;

import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.util.Point;
import java.io.Serializable;
import java.util.List;

public class AssembledResult implements Serializable {
    public final List<Chip> chips;
    public final List<Point> locations;
    final double percent;

    AssembledResult(List<Chip> list, List<Point> list2, double d) {
        this.chips = list;
        this.locations = list2;
        this.percent = d;
    }
}
