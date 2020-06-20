package open.gfl.chipcalc.activity;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.adapter.ChipAdapter;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.Chip;
import open.gfl.chipcalc.view.BoardView;
import open.gfl.chipcalc.view.ChipGridView;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0002J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u001e\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bX\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\t\u001a\u0012\u0012\u0004\u0012\u00020\n0\u0006j\b\u0012\u0004\u0012\u00020\n`\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lopen/gfl/chipcalc/activity/BoardActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "adapter", "Lopen/gfl/chipcalc/adapter/ChipAdapter;", "chipRots", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "chips", "Lopen/gfl/chipcalc/puzzle/Chip;", "max", "", "fabClick", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BoardActivity.kt */
public final class BoardActivity extends AppCompatActivity {
    private static final int WIDTH_UNIT = 350;
    private HashMap _$_findViewCache;
    private ChipAdapter adapter;
    private final ArrayList<Integer> chipRots = new ArrayList<>();
    private final ArrayList<Chip> chips = new ArrayList<>();
    private boolean max;

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

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_board);
        Serializable serializableExtra = getIntent().getSerializableExtra("board");
        if (serializableExtra != null) {
            Board board = (Board) serializableExtra;
            CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.ctLayout);
            ChipGridView chipGridView = (ChipGridView) findViewById(R.id.chipGridView);
            FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
            Intrinsics.checkExpressionValueIsNotNull(collapsingToolbarLayout, "ctLayout");
            collapsingToolbarLayout.setTitle(Global.fPercStr(board.statPerc));
            floatingActionButton.bringToFront();
            floatingActionButton.setOnClickListener(new BoardActivity$onCreate$1(this));
            ((BoardView) findViewById(R.id.boardView)).setBoard(board);
            Resources resources = getResources();
            Intrinsics.checkExpressionValueIsNotNull(resources, "resources");
            int i = resources.getDisplayMetrics().widthPixels;
            Intrinsics.checkExpressionValueIsNotNull(chipGridView, "chipGridView");
            chipGridView.setColumnWidth(i / (i / WIDTH_UNIT));
            ArrayList<Chip> arrayList = board.chips;
            Intrinsics.checkExpressionValueIsNotNull(arrayList, "board.chips");
            for (Chip chip : arrayList) {
                this.chips.add(new Chip(chip));
            }
            for (Chip chip2 : this.chips) {
                this.chipRots.add(Integer.valueOf(chip2.rotation));
                chip2.resetLevel();
                chip2.resetRotation();
            }
            ChipAdapter chipAdapter = new ChipAdapter(this, this.chips);
            this.adapter = chipAdapter;
            if (chipAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            }
            chipGridView.setAdapter(chipAdapter);
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type open.gfl.chipcalc.puzzle.Board");
    }


    public final void fabClick() {
        this.max = !this.max;
        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
        if (this.max) {
            Intrinsics.checkExpressionValueIsNotNull(floatingActionButton, "fab");
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.chip_level)));
            int size = this.chips.size();
            for (int i = 0; i < size; i++) {
                Chip chip = this.chips.get(i);
                Intrinsics.checkExpressionValueIsNotNull(chip, "chips[i]");
                Chip chip2 = chip;
                chip2.setMaxLevel();
                Integer num = this.chipRots.get(i);
                Intrinsics.checkExpressionValueIsNotNull(num, "chipRots[i]");
                chip2.setRotation(num.intValue());
            }
        } else {
            Intrinsics.checkExpressionValueIsNotNull(floatingActionButton, "fab");
            floatingActionButton.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(this, R.color.colorAccent)));
            for (Chip chip3 : this.chips) {
                chip3.resetLevel();
                chip3.resetRotation();
            }
        }
        ChipAdapter chipAdapter = this.adapter;
        if (chipAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        }
        chipAdapter.notifyDataSetChanged();
    }
}
