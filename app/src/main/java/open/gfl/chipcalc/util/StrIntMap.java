package open.gfl.chipcalc.util;

import java.util.HashMap;
import java.util.Map;

public class StrIntMap<V> {
    private final Map<String, Map<Integer, V>> data = new HashMap();

    protected void put(String str, int i, V v) {
        init(str);
        this.data.get(str).put(Integer.valueOf(i), v);
    }

    public V get(String str, int i) {
        if (containsKey(str, i)) {
            return this.data.get(str).get(Integer.valueOf(i));
        }
        return null;
    }

    private boolean containsKey(String str, int i) {
        return this.data.containsKey(str) && this.data.get(str).containsKey(Integer.valueOf(i));
    }

    public int size(String str) {
        return this.data.get(str).size();
    }

    private void init(String str) {
        if (!this.data.containsKey(str)) {
            this.data.put(str, new HashMap());
        }
    }
}
