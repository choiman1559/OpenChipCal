package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import open.gfl.chipcalc.R;
import open.gfl.chipcalc.assembly.Assembler;
import open.gfl.chipcalc.assembly.StatTemplate;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.util.Stat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0019"}, d2 = {"Lbunnyspa/gfl/chipcalc/activity/CalculateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "assembler", "Lbunnyspa/gfl/chipcalc/assembly/Assembler;", "name", "", "star", "", "startTime", "", "stat", "Lbunnyspa/gfl/chipcalc/util/Stat;", "time", "getTime", "m", "onBackPressed", "", "onBackPressedPositive", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "startResultActivity", "updateTime", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CalculateActivity.kt */
public final class CalculateActivity extends AppCompatActivity {
    public static final Companion Companion = new Companion();
    private static final String TAG = "GFL_CalculateActivity";
    private static final int TIME_LIMIT = 30;
    private HashMap _$_findViewCache;
    private Assembler assembler;
    private String name;
    private int star = 5;
    private long startTime;
    private Stat stat;
    long time;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0007"}, d2 = {"Lbunnyspa/gfl/chipcalc/activity/CalculateActivity$Companion;", "", "()V", "TAG", "", "TIME_LIMIT", "", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: CalculateActivity.kt */
    public static final class Companion {
        private Companion() {
        }
    }

    public void _$_clearFindViewByIdCache() {
        HashMap hashMap = this._$_findViewCache;
        if (hashMap != null) {
            hashMap.clear();
        }
    }

    public View _$_findCachedViewById(int i) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }
        View view = (View) this._$_findViewCache.get(Integer.valueOf(i));
        if (view != null) {
            return view;
        }
        view = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), view);
        return view;
    }

    public static /* synthetic */ Assembler access$getAssembler$p(CalculateActivity calculateActivity) {
        Assembler assembler = calculateActivity.assembler;
        if (calculateActivity == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        return assembler;
    }

    protected void onCreate(Bundle r5) {
        super.onCreate(r5);
        setContentView(R.layout.activity_calculate);
        Serializable bundle = getIntent().getSerializableExtra("chips");
        if (bundle != null) {
            String str;
            Stat stat;
            Map map;
            String str2;
            Integer num;
            Collection arrayList;
            int i;
            Object obj;
            Object obj2;
            Collection collection;
            Chip chip = null;
            String str3;
            Collection arrayList2;
            String str4;
            List<Chip> toList;
            int i2;
            Stat stat2;
            TextView textView;
            Assembler assembler;
            Handler handler;
            ArrayList arrayList3 = (ArrayList) bundle;
            String str5 = "name";
            String stringExtra = getIntent().getStringExtra(str5);
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"name\")");
            this.name = stringExtra;
            this.star = getIntent().getIntExtra("star", 5);
            int intExtra = getIntent().getIntExtra("templateIndex", -1);
            boolean booleanExtra = getIntent().getBooleanExtra("maxLevel", true);
            boolean booleanExtra2 = getIntent().getBooleanExtra("rotation", true);
            boolean booleanExtra3 = getIntent().getBooleanExtra("symmetry", false);
            if (this.star >= 5) {
                if (intExtra >= 0) {
                    Map map2 = StatTemplate.PRESET;
                    str = this.name;
                    if (str == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str5);
                    }
                    ArrayList arrayList4 = (ArrayList) map2.get(str);
                    StatTemplate statTemplate = arrayList4 != null ? (StatTemplate) arrayList4.get(intExtra) : null;
                    if (statTemplate == null) {
                        Intrinsics.throwNpe();
                    }
                    Intrinsics.checkExpressionValueIsNotNull(statTemplate, "StatTemplate.PRESET[name]?.get(templateIndex)!!");
                    stat = statTemplate.stat;
                    Intrinsics.checkExpressionValueIsNotNull(stat, "sTemplate.stat");
                    this.stat = stat;
                    map = Board.MAP_COLOR;
                    str2 = this.name;
                    if (str2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str5);
                    }
                    num = (Integer) map.get(str2);
                    arrayList = new ArrayList();
                    for (Object obj3 : arrayList3) {
                        i = ((Chip) obj3).color;
                        if (num == null) {
                            if (i == num.intValue()) {
                                obj2 = 1;
                                if (obj2 == null) {
                                    arrayList.add(obj3);
                                }
                            }
                        }
                        obj2 = null;
                        if (obj2 == null) {
                            arrayList.add(obj3);
                        }
                    }
                    collection = new ArrayList();
                    for (Object next : (List) arrayList) {
                        chip = (Chip) next;
                        str3 = chip.hoc;
                        Intrinsics.checkExpressionValueIsNotNull(str3, "c.hoc");
                        if ((((CharSequence) str3).length() != 0 ? 1 : null) == null) {
                            str = chip.hoc;
                            str3 = this.name;
                            if (str3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(str5);
                            }
                            if (Intrinsics.areEqual(str, str3)) {
                                collection.add(next);
                            }
                        }
                    }
                    arrayList2 = new ArrayList();
                    for (Object next2 : (List) collection) {
                        chip = (Chip) next2;
                        str3 = this.name;
                        if (str3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException(str5);
                        }
                        str4 = "M2";
                        if (!Intrinsics.areEqual(str3, str4) || chip.getSize() <= 4) {
                            str3 = this.name;
                            if (str3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException(str5);
                            }
                            if (!Intrinsics.areEqual(str3, str4) || chip.getSize() <= 3) {
                                arrayList2.add(next2);
                            }
                        }
                    }
                    toList = CollectionsKt.toList(arrayList2);
                    if (booleanExtra) {
                        for (Chip maxLevel : toList) {
                            maxLevel.setMaxLevel();
                        }
                    }
                    str4 = this.name;
                    if (str4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str5);
                    }
                    i2 = this.star;
                    stat2 = this.stat;
                    if (stat2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("stat");
                    }
                    this.assembler = new Assembler(str4, i2, toList, stat2, booleanExtra2, booleanExtra3);
                    textView = findViewById(R.id.percentTextView);
                    (findViewById(R.id.cancelCalcButton)).setOnClickListener(new CalculateActivity$onCreate$2(this));
                    assembler = this.assembler;
                    if (assembler == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("assembler");
                    }
                    assembler.start();
                    this.startTime = System.currentTimeMillis();
                    handler = new Handler();
                    handler.post(new CalculateActivity$onCreate$4(CalculateActivity.this, textView, handler));
                    return;
                }
            }
            stringExtra = this.name;
            if (stringExtra == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str5);
            }
            stat = Board.getMaxStat(stringExtra, this.star);
            Intrinsics.checkExpressionValueIsNotNull(stat, "Board.getMaxStat(name, star)");
            this.stat = stat;
            map = Board.MAP_COLOR;
            str2 = this.name;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str5);
            }
            num = (Integer) map.get(str2);
            arrayList = new ArrayList();
            for (Object obj32 : arrayList3) {
                i = ((Chip) obj32).color;
                if (num == null) {
                    if (i == num.intValue()) {
                        obj2 = 1;
                        if (obj2 == null) {
                            arrayList.add(obj32);
                        }
                    }
                }
                obj2 = null;
                if (obj2 == null) {
                    arrayList.add(obj32);
                }
            }
            collection = new ArrayList();
            for (Object next22 : (List) arrayList) {
                chip = (Chip) next22;
                str3 = chip.hoc;
                Intrinsics.checkExpressionValueIsNotNull(str3, "c.hoc");
                if (((CharSequence) str3).length() != 0) {
                }
                if ((((CharSequence) str3).length() != 0 ? 1 : null) == null) {
                    str = chip.hoc;
                    str3 = this.name;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException(str5);
                    }
                    if (Intrinsics.areEqual(str, str3)) {
                        collection.add(next22);
                    }
                }
            }
            arrayList2 = new ArrayList();
            for (Object next222 : (List) collection) {
                chip = (Chip) next222;
                str3 = this.name;
                if (str3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(str5);
                }
                arrayList2.add(next222);
            }
            toList = CollectionsKt.toList(arrayList2);
            if (booleanExtra) {
                while (((ArrayList) bundle).iterator().hasNext()) {
                    chip.setMaxLevel();
                }
            }
            str4 = this.name;
            if (str4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException(str5);
            }
            i2 = this.star;
            stat2 = this.stat;
            if (stat2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stat");
            }
            this.assembler = new Assembler(str4, i2, toList, stat2, booleanExtra2, booleanExtra3);
            textView = findViewById(R.id.percentTextView);
            (findViewById(R.id.cancelCalcButton)).setOnClickListener(new CalculateActivity$onCreate$2(this));
            assembler = this.assembler;
            if (assembler == null) {
                Intrinsics.throwUninitializedPropertyAccessException("assembler");
            }
            assembler.start();
            this.startTime = System.currentTimeMillis();
            handler = new Handler();
            handler.post(new CalculateActivity$onCreate$4(this, textView, handler));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<open.gfl.chipcalc.puzzle.Chip> /* = java.util.ArrayList<open.gfl.chipcalc.puzzle.Chip> */");
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.msg_back_title)).setMessage(getString(R.string.msg_back)).setPositiveButton(R.string.yes, new CalculateActivity$onBackPressed$1(this)).setNegativeButton(R.string.no, CalculateActivity$onBackPressed$2.INSTANCE).show();
    }

    final void onBackPressedPositive() {
        super.onBackPressed();
        Assembler assembler = this.assembler;
        if (assembler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        assembler.stop();
        finish();
    }

    final void startResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        Assembler assembler = this.assembler;
        if (assembler == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        intent.putExtra("results", assembler.getResults());
        Stat stat = this.stat;
        if (stat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stat");
        }
        intent.putExtra("maxStat", stat);
        String str = this.name;
        String str2 = "name";
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException(str2);
        }
        intent.putExtra(str2, str);
        intent.putExtra("star", this.star);
        startActivity(intent);
        finish();
    }

    final void updateTime() {
        this.time = System.currentTimeMillis() - this.startTime;
        TextView textView = findViewById(R.id.timerTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "timerTextView");
        textView.setText(getTime(Math.max(0, ((long) 30000) - this.time)));
    }

    String getTime(long j) {
        j /= 1000;
        long j2 = 3600;
        long j3 = j / j2;
        long j4 = 60;
        j2 = (j % j2) / j4;
        j %= j4;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(j3);
        String str = ":";
        stringBuilder.append(str);
        Object[] copyOf = Arrays.copyOf(new Object[]{j2}, 1);
        String str2 = "%02d";
        String format = String.format(str2, copyOf);
        String str3 = "java.lang.String.format(format, *args)";
        Intrinsics.checkExpressionValueIsNotNull(format, str3);
        stringBuilder.append(format);
        stringBuilder.append(str);
        j = Long.parseLong(String.format(str2, Arrays.copyOf(new Object[]{Long.valueOf(j)}, 1)));
        Intrinsics.checkExpressionValueIsNotNull(j, str3);
        stringBuilder.append(j);
        return stringBuilder.toString();
    }
}

