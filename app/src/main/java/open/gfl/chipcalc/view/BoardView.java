package open.gfl.chipcalc.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.core.view.ViewCompat;
import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.PuzzleMatrix;

public class BoardView extends AppCompatImageView {
    private Board board;
    private final Paint paint = new Paint();

    public BoardView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void setBoard(Board board2) {
        this.board = board2;
        invalidate();
    }

    protected void onDraw(Canvas canvas) {
        canvas.drawColor(ViewCompat.MEASURED_STATE_MASK);
        Board board2 = this.board;
        if (board2 != null) {
            PuzzleMatrix<Integer> matrix = board2.getMatrix();
            float width = (float) getWidth();
            float height = (float) getHeight();
            int numCol = matrix.getNumCol();
            int numRow = matrix.getNumRow();
            for (int i = 0; i < numRow; i++) {
                for (int i2 = 0; i2 < numCol; i2++) {
                    int intValue = matrix.get(i, i2).intValue();
                    if (intValue >= 0) {
                        this.paint.setColor(Global.getIndexColor(intValue));
                        float f = (float) numCol;
                        float f2 = (float) numRow;
                        canvas.drawRect(((((float) i2) * width) / f) + 1.0f, ((((float) i) * height) / f2) + 1.0f, ((((float) (i2 + 1)) * width) / f) - 1.0f, ((((float) (i + 1)) * height) / f2) - 1.0f, this.paint);
                    }
                }
            }
        }
    }
}
