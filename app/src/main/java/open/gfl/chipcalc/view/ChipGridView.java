package open.gfl.chipcalc.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

public class ChipGridView extends GridView {
    public ChipGridView(Context context) {
        super(context);
    }

    public ChipGridView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public ChipGridView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void onMeasure(int i, int i2) {
        super.onMeasure(i, View.MeasureSpec.makeMeasureSpec(536870911, Integer.MIN_VALUE));
    }
}
