package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import open.gfl.chipcalc.R;
import open.gfl.chipcalc.assembly.Assembler;
import open.gfl.chipcalc.util.Stat;

import java.util.Arrays;
import java.util.HashMap;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\nH\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0011H\u0002J\u0012\u0010\u0013\u001a\u00020\u00112\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0011H\u0002J\b\u0010\u0017\u001a\u00020\u0011H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lopen/gfl/chipcalc/activity/CalculateActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "assembler", "Lopen/gfl/chipcalc/assembly/Assembler;", "name", "", "star", "", "startTime", "", "stat", "Lopen/gfl/chipcalc/util/Stat;", "time", "getTime", "m", "onBackPressed", "", "onBackPressedPositive", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "startResultActivity", "updateTime", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CalculateActivity.kt */
public final class CalculateActivity extends AppCompatActivity {
    private static final String TAG = "GFL_CalculateActivity";
    private static final int TIME_LIMIT = 30;
    private HashMap _$_findViewCache;
    /* access modifiers changed from: private */
    public Assembler assembler;
    private String name;
    private int star = 5;
    private long startTime;
    private Stat stat;
    /* access modifiers changed from: private */
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
        super.onCreate(r15);
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

    /* access modifiers changed from: private */
    public final void updateTime() {
        this.time = System.currentTimeMillis() - this.startTime;
        TextView textView = (TextView) findViewById(R.id.timerTextView);
        Intrinsics.checkExpressionValueIsNotNull(textView, "timerTextView");
        textView.setText(getTime(Math.max(0, ((long) 30000) - this.time)));
    }

    private final String getTime(long j) {
        long j2 = j / ((long) 1000);
        long j3 = (long) 3600;
        long j4 = j2 / j3;
        long j5 = (long) 60;
        long j6 = (j2 % j3) / j5;
        long j7 = j2 % j5;
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(j4));
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
