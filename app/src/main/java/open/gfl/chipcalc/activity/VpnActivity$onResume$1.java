package open.gfl.chipcalc.activity;

import android.content.DialogInterface;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 16})
/* compiled from: VpnActivity.kt */
final class VpnActivity$onResume$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ ArrayList $chips;
    final /* synthetic */ String $data;
    final /* synthetic */ VpnActivity this$0;

    VpnActivity$onResume$1(VpnActivity vpnActivity, String str, ArrayList arrayList) {
        this.this$0 = vpnActivity;
        this.$data = str;
        this.$chips = arrayList;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        VpnActivity vpnActivity = this.this$0;
        String str = this.$data;
        Intrinsics.checkExpressionValueIsNotNull(str, "data");
        vpnActivity.saveData(str);
        this.this$0.passChipsAndFinish(this.$chips);
    }
}