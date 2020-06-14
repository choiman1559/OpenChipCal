package open.gfl.chipcalc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.puzzle.PuzzleMatrix;

public class ChipTileView extends View {
    private Chip chip;
    private final Paint paint = new Paint();

    public ChipTileView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setChip(Chip chip2) {
        this.chip = chip2;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        int i;
        int i2;
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        if (this.chip != null) {
            this.paint.setColor(ContextCompat.getColor(getContext(), this.chip.color == 0 ? R.color.chip_orange : R.color.chip_blue));
            PuzzleMatrix<Boolean> matrix = this.chip.getMatrix();
            float width = (float) getWidth();
            float height = (float) getHeight();
            int numCol = matrix.getNumCol();
            int numRow = matrix.getNumRow();
            float f = (((float) (6 - numRow)) * height) / 12.0f;
            float f2 = (((float) (6 - numCol)) * width) / 12.0f;
            int i3 = 0;
            while (i3 < numRow) {
                int i4 = 0;
                while (i4 < numCol) {
                    if (matrix.get(i3, i4).booleanValue()) {
                        i2 = i4;
                        i = i3;
                        canvas.drawRect(((((float) i4) * width) / 6.0f) + f2 + 1.0f, ((((float) i3) * height) / 6.0f) + f + 1.0f, (((((float) (i4 + 1)) * width) / 6.0f) + f2) - 1.0f, (((((float) (i3 + 1)) * height) / 6.0f) + f) - 1.0f, this.paint);
                    } else {
                        i2 = i4;
                        i = i3;
                    }
                    i4 = i2 + 1;
                    i3 = i;
                }
                i3++;
            }
        }
    }
}
