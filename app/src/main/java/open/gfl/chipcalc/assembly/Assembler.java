package open.gfl.chipcalc.assembly;

import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.util.Stat;

//import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
//TODO : add gradle

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Assembler {
    private static final int CORE = Runtime.getRuntime().availableProcessors();
    private static final int RESULT_LIMIT = 10;
    private static final String TAG = "GFL_Assembler";
    private final Set<BoardTemplate> bTemplates;
    private final List<Chip> chips;
    private double maxResultPercent = -1.0d;
    private final Stat maxStat;
    private final Map<Stat, Double> percentCache = new HashMap();
    private int progress;
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue();
    private double resultPercentThreshold = -1.0d;
    private final Map<Double, List<AssembledResult>> results = new HashMap();
    private final boolean rotation;
    private volatile boolean running;
    private final ThreadPoolExecutor threadPoolExecutor;
    public final int totalCount;


    public Assembler(String str, int i, List<Chip> list, Stat stat, boolean z, boolean z2) {
        int i2 = CORE;
        this.threadPoolExecutor = new ThreadPoolExecutor(i2, i2 * 2, 100, TimeUnit.MILLISECONDS, this.queue);
        int i3 = CORE;
        this.progress = CORE;
        this.running = true;
        this.chips = list;
        this.maxStat = stat;
        this.rotation = z;
        if (z2) {
            this.bTemplates = new HashSet();
            this.bTemplates.addAll((Set) ((Map) Global.TEMPLATES.get(str)).get(Integer.valueOf(i)));
        } else {
            this.bTemplates = (Set) ((Map) Global.TEMPLATES.get(str)).get(Integer.valueOf(i));
        }
        for (BoardTemplate boardTemplate2 : this.bTemplates) {
            if (filter(boardTemplate2) != false) {
                i3++;
            }
        }
        this.totalCount = i3;
    }

    public void start() {
        for (BoardTemplate boardTemplate : this.bTemplates) {
            if (filter(boardTemplate)) {
                this.threadPoolExecutor.execute(new qcEKqdrOurNV5CFQO_UO1CXtORw(this, boardTemplate));
            }
        }
    }

    public /* synthetic */ void lambda$start$0$Assembler(BoardTemplate boardTemplate) {
        assemble(boardTemplate);
    }

    public void stop() {
        this.running = false;
        this.threadPoolExecutor.shutdown();
    }

    private boolean filter(BoardTemplate boardTemplate) {
        return CCIterator.hasEnoughChips(CCIterator.genMap(boardTemplate, this.chips), boardTemplate);
    }

    private void assemble(BoardTemplate boardTemplate) {
        Double d;
        CCIterator cCIterator = new CCIterator(this.chips, boardTemplate);
        while (this.running && cCIterator.hasNext()) {
            if (this.running) {
                List next = cCIterator.next();
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < next.size(); i++) {
                    Chip chip = new Chip((Chip) next.get(i));
                    chip.setRotation(boardTemplate.getRotation(i));
                    arrayList.add(chip);
                }
                Stat sumStat = Stat.sumStat(arrayList);
                synchronized (this.percentCache) {
                    if (this.percentCache.containsKey(sumStat)) {
                        d = this.percentCache.get(sumStat);
                    } else {
                        Double valueOf = Double.valueOf(Board.getStatPerc(sumStat, this.maxStat));
                        this.percentCache.put(sumStat, valueOf);
                        d = valueOf;
                    }
                    if (this.running && this.resultPercentThreshold <= d.doubleValue()) {
                        addToList(new AssembledResult(arrayList, boardTemplate.getLocations(), d.doubleValue()));
                    }
                }
            }
        }
        this.progress++;
    }

    private synchronized void addToList(AssembledResult assembledResult) {
        double d = assembledResult.percent;
        if (this.resultPercentThreshold <= d) {
            if (!this.results.containsKey(Double.valueOf(d))) {
                this.results.put(Double.valueOf(d), new ArrayList());
            }
            this.results.get(Double.valueOf(d)).add(assembledResult);
            if (this.maxResultPercent < d) {
                this.maxResultPercent = d;
            }
            if (10 < this.results.get(Double.valueOf(this.maxResultPercent)).size()) {
                this.resultPercentThreshold = d;
                this.results.clear();
                this.results.put(Double.valueOf(d), this.results.get(Double.valueOf(d)));
            }
        }
    }

    public double getHighestPercent() {
        /*if (this.results.isEmpty()) {
            return FirebaseRemoteConfig.DEFAULT_VALUE_FOR_DOUBLE;
        }*/
        return this.maxResultPercent;
    }

    public boolean done() {
        return this.progress == this.totalCount || this.threadPoolExecutor.isTerminated();
    }

    public ArrayList<AssembledResult> getResults() {
        if (this.results.keySet().isEmpty()) {
            return new ArrayList<>();
        }
        ArrayList arrayList = new ArrayList(this.results.keySet());
        Collections.sort(arrayList);
        Collections.reverse(arrayList);
        ArrayList<AssembledResult> arrayList2 = new ArrayList<>();
        int i = 0;
        for (int i2 = 0; i2 < arrayList.size() && i < 10; i2++) {
            List list = this.results.get(Double.valueOf(((Double) arrayList.get(i2)).doubleValue()));
            arrayList2.addAll(list);
            i += list.size();
        }
        return arrayList2;
    }
}
