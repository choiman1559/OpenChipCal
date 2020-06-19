package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
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
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lopen/gfl/chipcalc/activity/CalculateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "assembler", "Lopen/gfl/chipcalc/assembly/Assembler;", "name", "", "star", "", "startTime", "", "stat", "Lopen/gfl/chipcalc/util/Stat;", "time", "getTime", "m", "onBackPressed", "", "onBackPressedPositive", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "startResultActivity", "updateTime", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class CalculateActivity extends AppCompatActivity {
    private static final String TAG = "GFL_CalculateActivity";
    private static final int TIME_LIMIT = 30;
    private HashMap _$_findViewCache;
    public Assembler assembler;
    private String name;
    private int star = 5;
    private long startTime;
    private Stat stat;
    public long time;

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
        View findViewById = findViewById(i);
        this._$_findViewCache.put(Integer.valueOf(i), findViewById);
        return findViewById;
    }

    public static final /* synthetic */ Assembler access$getAssembler$p(CalculateActivity calculateActivity) {
        Assembler assembler2 = calculateActivity.assembler;
        if (assembler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        return assembler2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:100:0x013d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x012a  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x017a  */
    /* JADX WARNING: Removed duplicated region for block: B:96:0x00f4 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void onCreate(android.os.Bundle r15) {
        //TODO : restore
        boolean z;
        boolean z2;
        super.onCreate(r15);
        setContentView((int) R.layout.activity_calculate);
        Serializable serializableExtra = getIntent().getSerializableExtra("chips");
        if (serializableExtra != null) {
            ArrayList arrayList = (ArrayList) serializableExtra;
            String stringExtra = getIntent().getStringExtra("name");
            Intrinsics.checkExpressionValueIsNotNull(stringExtra, "intent.getStringExtra(\"name\")");
            this.name = stringExtra;
            this.star = getIntent().getIntExtra("star", 5);
            int intExtra = getIntent().getIntExtra("templateIndex", -1);
            boolean booleanExtra = getIntent().getBooleanExtra("maxLevel", true);
            boolean booleanExtra2 = getIntent().getBooleanExtra("rotation", true);
            boolean booleanExtra3 = getIntent().getBooleanExtra("symmetry", false);
            if (this.star < 5 || intExtra < 0) {
                String str = this.name;
                if (str == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("name");
                }
                Stat maxStat = Board.getMaxStat(str, this.star);
                Intrinsics.checkExpressionValueIsNotNull(maxStat, "Board.getMaxStat(name, star)");
                this.stat = maxStat;
            } else {
                Map<String, ArrayList<StatTemplate>> map = StatTemplate.PRESET;
                String str2 = this.name;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("name");
                }
                ArrayList arrayList2 = map.get(str2);
                StatTemplate statTemplate = arrayList2 != null ? (StatTemplate) arrayList2.get(intExtra) : null;
                if (statTemplate == null) {
                    Intrinsics.throwNpe();
                }
                Intrinsics.checkExpressionValueIsNotNull(statTemplate, "StatTemplate.PRESET[name]?.get(templateIndex)!!");
                Stat stat2 = statTemplate.stat;
                Intrinsics.checkExpressionValueIsNotNull(stat2, "sTemplate.stat");
                this.stat = stat2;
            }
            Map<String, Integer> map2 = Board.MAP_COLOR;
            String str3 = this.name;
            if (str3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("name");
            }
            Integer num = map2.get(str3);
            Collection arrayList3 = new ArrayList();
            for (Object next : arrayList) {
                if (num != null && ((Chip) next).color == num.intValue()) {
                    arrayList3.add(next);
                }
            }
            Collection arrayList4 = new ArrayList();
            for (Object next2 : (List) arrayList3) {
                Chip chip = (Chip) next2;
                String str4 = chip.hoc;
                Intrinsics.checkExpressionValueIsNotNull(str4, "c.hoc");
                if (!(str4.length() == 0)) {
                    String str5 = chip.hoc;
                    String str6 = this.name;
                    if (str6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("name");
                    }
                    if (!Intrinsics.areEqual((Object) str5, (Object) str6)) {
                        z2 = false;
                        if (!z2) {
                            arrayList4.add(next2);
                        }
                    }
                }
                z2 = true;
                if (!z2) {
                }
            }
            Collection arrayList5 = new ArrayList();
            for (Object next3 : (List) arrayList4) {
                Chip chip2 = (Chip) next3;
                String str7 = this.name;
                if (str7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("name");
                }
                if (!(!Intrinsics.areEqual((Object) str7, (Object) Board.NAME_M2)) || chip2.getSize() <= 4) {
                    String str8 = this.name;
                    if (str8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("name");
                    }
                    if (!Intrinsics.areEqual((Object) str8, (Object) Board.NAME_M2) || chip2.getSize() <= 3) {
                        z = false;
                        if (!z) {
                            arrayList5.add(next3);
                        }
                    }
                }
                z = true;
                if (!z) {
                }
            }
            List<Chip> list = CollectionsKt.toList((List) arrayList5);
            if (booleanExtra) {
                for (Chip maxLevel : list) {
                    maxLevel.setMaxLevel();
                }
            }
            String str9 = this.name;
            if (str9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("name");
            }
            int i = this.star;
            Stat stat3 = this.stat;
            if (stat3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("stat");
            }
            this.assembler = new Assembler(str9, i, list, stat3, booleanExtra2, booleanExtra3);
            TextView textView = (TextView) findViewById(R.id.percentTextView);
            ((Button) findViewById(R.id.cancelCalcButton)).setOnClickListener(new CalculateActivity$onCreate$2(this));
            Assembler assembler2 = this.assembler;
            if (assembler2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("assembler");
            }
            assembler2.start();
            this.startTime = System.currentTimeMillis();
            Handler handler = new Handler();
            handler.post(new CalculateActivity$onCreate$4(this, textView, handler));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<bunnyspa.gfl.chipcalc.puzzle.Chip> /* = java.util.ArrayList<bunnyspa.gfl.chipcalc.puzzle.Chip> */");
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.msg_back_title)).setMessage(getString(R.string.msg_back)).setPositiveButton(R.string.yes, new CalculateActivity$onBackPressed$1(this)).setNegativeButton(R.string.no, CalculateActivity$onBackPressed$2.INSTANCE).show();
    }

    /* access modifiers changed from: private */
    public final void onBackPressedPositive() {
        super.onBackPressed();
        Assembler assembler2 = this.assembler;
        if (assembler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        assembler2.stop();
        finish();
    }

    /* access modifiers changed from: private */
    public final void startResultActivity() {
        Intent intent = new Intent(this, ResultActivity.class);
        Assembler assembler2 = this.assembler;
        if (assembler2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("assembler");
        }
        intent.putExtra("results", assembler2.getResults());
        Stat stat2 = this.stat;
        if (stat2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("stat");
        }
        intent.putExtra("maxStat", stat2);
        String str = this.name;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("name");
        }
        intent.putExtra("name", str);
        intent.putExtra("star", this.star);
        startActivity(intent);
        finish();
    }

    public final void updateTime() {
        this.time = System.currentTimeMillis() - this.startTime;
        TextView textView = findViewById(R.id.timerTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "timerTextView");
        textView.setText(getTime(Math.max(0, ((long) 30000) - this.time)));
    }

    private final String getTime(long j) {
        long j2 = j / ((long) 1000);
        long j3 = 3600;
        long j4 = j2 / j3;
        long j5 = 60;
        long j6 = (j2 % j3) / j5;
        long j7 = j2 % j5;
        StringBuilder sb = new StringBuilder();
        sb.append(j4);
        sb.append(":");
        String format = String.format("%02d", Arrays.copyOf(new Object[]{Long.valueOf(j6)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(format, *args)");
        sb.append(format);
        sb.append(":");
        String format2 = String.format("%02d", Arrays.copyOf(new Object[]{Long.valueOf(j7)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format2, "java.lang.String.format(format, *args)");
        sb.append(format2);
        return sb.toString();
    }
}
