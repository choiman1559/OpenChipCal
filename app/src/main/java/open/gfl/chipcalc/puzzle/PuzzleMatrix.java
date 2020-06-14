package open.gfl.chipcalc.puzzle;

import open.gfl.chipcalc.util.Point;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import org.apache.commons.httpclient.HttpStatus;

public class PuzzleMatrix<E> implements Serializable {
    private List<E> list;
    private int nCol;
    private int nRow;

    PuzzleMatrix(E[][] eArr) {
        int length = eArr.length;
        this.nRow = length;
        this.nCol = length > 0 ? eArr[0].length : 0;
        this.list = new ArrayList(this.nCol * this.nRow);
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                this.list.add(eArr[i][i2]);
            }
        }
    }

    private boolean isValid(int i, int i2) {
        return i >= 0 && i < this.nRow && i2 >= 0 && i2 < this.nCol;
    }

    public E get(int i, int i2) {
        if (isValid(i, i2)) {
            return this.list.get((i * this.nCol) + i2);
        }
        return null;
    }

    void set(int i, int i2, E e) {
        if (isValid(i, i2)) {
            this.list.set((i * this.nCol) + i2, e);
        }
    }

    public int getNumCol() {
        return this.nCol;
    }

    public int getNumRow() {
        return this.nRow;
    }

    int getNumNotContaining(E e) {
        int i = 0;
        for (E equals : this.list) {
            if (!equals.equals(e)) {
                i++;
            }
        }
        return i;
    }

    Point getPivot(E e) {
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                if (get(i, i2) == e) {
                    return new Point(i, i2);
                }
            }
        }
        return null;
    }

    Set<Point> getCoords(E e) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                if (get(i, i2) == e) {
                    hashSet.add(new Point(i, i2));
                }
            }
        }
        return hashSet;
    }

    private Set<Point> getCoordsExcept(E e) {
        HashSet hashSet = new HashSet();
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                if (get(i, i2) != e) {
                    hashSet.add(new Point(i, i2));
                }
            }
        }
        return hashSet;
    }

    private void rotate() {
        ArrayList arrayList = new ArrayList(this.nCol * this.nRow);
        int i = this.nCol;
        int i2 = this.nRow;
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                arrayList.add(this.list.get((((i2 - i4) - 1) * i) + i3));
            }
        }
        this.nRow = i;
        this.nCol = i2;
        this.list = arrayList;
    }

    void rotate(int i) {
        int i2 = i % 4;
        for (int i3 = 0; i3 < i2; i3++) {
            rotate();
        }
    }

    void rotateContent(int i, E e) {
        if (i == 2) {
            int i2 = this.nRow;
            int i3 = this.nCol;
            int i4 = 0;
            int i5 = 0;
            for (Point next : getCoordsExcept(e)) {
                if (i2 > next.x) {
                    i2 = next.x;
                }
                if (i4 < next.x) {
                    i4 = next.x;
                }
                if (i3 > next.y) {
                    i3 = next.y;
                }
                if (i5 < next.y) {
                    i5 = next.y;
                }
            }
            ArrayList arrayList = new ArrayList(getCoordsExcept(e));
            while (!arrayList.isEmpty()) {
                Point point = (Point) arrayList.get(0);
                Point point2 = new Point((i2 + i4) - point.x, (i3 + i5) - point.y);
                if (point.equals(point2)) {
                    arrayList.remove(point);
                } else {
                    Object obj = get(point.x, point.y);
                    set(point.x, point.y, get(point2.x, point2.y));
                    set(point2.x, point2.y, (E) obj);
                    arrayList.remove(point);
                    arrayList.remove(point2);
                }
            }
            return;
        }
        rotate(i);
    }

    public boolean isSymmetric(E e) {
        int i = this.nRow;
        int i2 = this.nCol;
        int i3 = 0;
        int i4 = 0;
        for (Point next : getCoordsExcept(e)) {
            if (i > next.x) {
                i = next.x;
            }
            if (i3 < next.x) {
                i3 = next.x;
            }
            if (i2 > next.y) {
                i2 = next.y;
            }
            if (i4 < next.y) {
                i4 = next.y;
            }
        }
        HashMap hashMap = new HashMap();
        boolean z = true;
        boolean z2 = true;
        for (int i5 = i; i5 <= (i3 + i) / 2; i5++) {
            int i6 = i2;
            while (true) {
                if (i6 > i4) {
                    break;
                }
                Object obj = get(i5, i6);
                Object obj2 = get((i3 - i5) + i, i6);
                if ((!hashMap.containsKey(obj) || Objects.equals(hashMap.get(obj), obj2)) && (!hashMap.containsKey(obj2) || Objects.equals(hashMap.get(obj2), obj))) {
                    hashMap.put(obj, obj2);
                    hashMap.put(obj2, obj);
                    i6++;
                }
            }
            z2 = false;
            if (!z2) {
                break;
            }
        }
        if (z2) {
            return true;
        }
        hashMap.clear();
        boolean z3 = true;
        for (int i7 = i; i7 <= i3; i7++) {
            int i8 = i2;
            while (true) {
                if (i8 > (i4 + i2) / 2) {
                    break;
                }
                Object obj3 = get(i7, i8);
                Object obj4 = get(i7, (i4 - i8) + i2);
                if (hashMap.containsKey(obj3) && !Objects.equals(hashMap.get(obj3), obj4)) {
                    z3 = false;
                    break;
                }
                hashMap.put(obj3, obj4);
                i8++;
            }
            if (!z3) {
                break;
            }
        }
        if (z3) {
            return true;
        }
        hashMap.clear();
        for (int i9 = i; i9 <= i3; i9++) {
            int i10 = i2;
            while (true) {
                if (i10 > i4) {
                    break;
                }
                Object obj5 = get(i9, i10);
                Object obj6 = get((i3 - i9) + i, (i4 - i10) + i2);
                if (hashMap.containsKey(obj5) && !Objects.equals(hashMap.get(obj5), obj6)) {
                    z = false;
                    break;
                }
                hashMap.put(obj5, obj6);
                i10++;
            }
            if (!z) {
                break;
            }
        }
        return z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.nRow; i++) {
            for (int i2 = 0; i2 < this.nCol; i2++) {
                String obj = get(i, i2).toString();
                char c = 65535;
                int hashCode = obj.hashCode();
                if (hashCode != 1444) {
                    if (hashCode == 1445 && obj.equals("-2")) {
                        c = 1;
                    }
                } else if (obj.equals("-1")) {
                    c = 0;
                }
                if (c == 0) {
                    sb.append("  ");
                } else if (c != 1) {
                    sb.append(obj);
                    sb.append(" ");
                } else {
                    sb.append("X ");
                }
            }
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        PuzzleMatrix puzzleMatrix = (PuzzleMatrix) obj;
        if (this.nCol == puzzleMatrix.nCol && this.nRow == puzzleMatrix.nRow && this.list.equals(puzzleMatrix.list)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((HttpStatus.SC_UNSUPPORTED_MEDIA_TYPE + this.nCol) * 83) + this.nRow) * 83) + Objects.hashCode(this.list);
    }
}
