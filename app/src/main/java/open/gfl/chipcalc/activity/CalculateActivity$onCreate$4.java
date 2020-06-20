package open.gfl.chipcalc.activity;

import android.os.Handler;
import android.widget.TextView;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.Global;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016¨\u0006\u0004"}, d2 = {"bunnyspa/gfl/chipcalc/activity/CalculateActivity$onCreate$4", "Ljava/lang/Runnable;", "run", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CalculateActivity.kt */
public final class CalculateActivity$onCreate$4 implements Runnable {
    final Handler $handler;
    final TextView $percentTextView;
    final CalculateActivity this$0;

    CalculateActivity$onCreate$4(CalculateActivity calculateActivity, TextView textView, Handler handler) {
        this.this$0 = calculateActivity;
        this.$percentTextView = textView;
        this.$handler = handler;
    }

    public void run() {
        this.this$0.updateTime();
        TextView textView = this.$percentTextView;
        Intrinsics.checkExpressionValueIsNotNull(textView, "percentTextView");
        textView.setText(this.this$0.getString(R.string.calculate_highest_found) + " : " + Global.fPercStr(CalculateActivity.access$getAssembler$p(this.this$0).getHighestPercent()));
        if (!CalculateActivity.access$getAssembler$p(this.this$0).done() && ((long) 30000) < this.this$0.time) {
            CalculateActivity.access$getAssembler$p(this.this$0).stop();
        }
        if (CalculateActivity.access$getAssembler$p(this.this$0).done()) {
            this.this$0.startResultActivity();
        } else {
            this.$handler.postDelayed(this, 100);
        }
    }
}


