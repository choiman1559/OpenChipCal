package open.gfl.chipcalc.activity;

import android.widget.RatingBar;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "ratingBar", "Landroid/widget/RatingBar;", "kotlin.jvm.PlatformType", "rating", "", "<anonymous parameter 2>", "", "onRatingChanged"}, k = 3, mv = {1, 1, 16})
/* compiled from: BoardSettingActivity.kt */
final class BoardSettingActivity$starRatingBarChange$1 implements RatingBar.OnRatingBarChangeListener {
    final /* synthetic */ BoardSettingActivity this$0;

    BoardSettingActivity$starRatingBarChange$1(BoardSettingActivity boardSettingActivity) {
        this.this$0 = boardSettingActivity;
    }

    public final void onRatingChanged(RatingBar ratingBar, float f, boolean z) {
        Intrinsics.checkExpressionValueIsNotNull(ratingBar, "ratingBar");
        ratingBar.setRating(Math.max(f, 1.0f));
        this.this$0.updateTemplate();
    }
}
