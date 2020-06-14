package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.adapter.BoardAdapter;
import open.gfl.chipcalc.assembly.AssembledResult;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.util.Stat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0005¢\u0006\u0002\u0010\u0002J \u0010\u0003\u001a\u00020\u00042\u0016\u0010\u0005\u001a\u0012\u0012\u0004\u0012\u00020\u00070\u0006j\b\u0012\u0004\u0012\u00020\u0007`\bH\u0002J\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0002J\u0012\u0010\f\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014¨\u0006\u0010"}, d2 = {"Lopen/gfl/chipcalc/activity/ResultActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "listItemClick", "Landroid/widget/AdapterView$OnItemClickListener;", "boards", "Ljava/util/ArrayList;", "Lopen/gfl/chipcalc/puzzle/Board;", "Lkotlin/collections/ArrayList;", "onBackPressed", "", "onBackPressedPositive", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ResultActivity.kt */
public final class ResultActivity extends AppCompatActivity {
    private static final String TAG = "GFL_ResultActivity";
    private HashMap _$_findViewCache;

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
        setContentView((int) R.layout.activity_result);
        Serializable serializableExtra = getIntent().getSerializableExtra("results");
        if (serializableExtra != null) {
            ArrayList arrayList = (ArrayList) serializableExtra;
            Serializable serializableExtra2 = getIntent().getSerializableExtra("maxStat");
            if (serializableExtra2 != null) {
                Stat stat = (Stat) serializableExtra2;
                String stringExtra = getIntent().getStringExtra("name");
                int intExtra = getIntent().getIntExtra("star", 5);
                ArrayList arrayList2 = new ArrayList();
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    AssembledResult assembledResult = (AssembledResult) it.next();
                    arrayList2.add(new Board(stringExtra, intExtra, stat, assembledResult.chips, assembledResult.locations));
                }
                ListView listView = (ListView) findViewById(R.id.resultListView);
                Intrinsics.checkExpressionValueIsNotNull(listView, "resultListView");
                listView.setAdapter(new BoardAdapter(this, arrayList2));
                listView.invalidate();
                listView.setOnItemClickListener(listItemClick(arrayList2));
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type open.gfl.chipcalc.util.Stat");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<open.gfl.chipcalc.assembly.AssembledResult> /* = java.util.ArrayList<open.gfl.chipcalc.assembly.AssembledResult> */");
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.msg_back_title)).setMessage(getString(R.string.msg_back)).setPositiveButton(R.string.yes, new ResultActivity$onBackPressed$1(this)).setNegativeButton(R.string.no, ResultActivity$onBackPressed$2.INSTANCE).show();
    }

    /* access modifiers changed from: private */
    public final void onBackPressedPositive() {
        super.onBackPressed();
        finish();
    }

    private final AdapterView.OnItemClickListener listItemClick(ArrayList<Board> arrayList) {
        return new ResultActivity$listItemClick$1(this, arrayList);
    }
}
