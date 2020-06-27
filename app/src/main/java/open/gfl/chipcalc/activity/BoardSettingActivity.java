package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Switch;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.assembly.StatTemplate;
import open.gfl.chipcalc.puzzle.Board;
import open.gfl.chipcalc.puzzle.Chip;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0002\b\r\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\b\u0010\u0011\u001a\u00020\u0010H\u0016J\u0012\u0010\u0012\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0010H\u0002J\b\u0010\u0016\u001a\u00020\u0010H\u0002J\b\u0010\u0017\u001a\u00020\u0010H\u0002R\u001e\u0010\u0003\u001a\u0012\u0012\u0004\u0012\u00020\u00050\u0004j\b\u0012\u0004\u0012\u00020\u0005`\u0006X.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0004\n\u0002\u0010\tR\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0004\n\u0002\u0010\u000e¨\u0006\u0018"}, d2 = {"Lopen/gfl/chipcalc/activity/BoardSettingActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "chips", "Ljava/util/ArrayList;", "Lopen/gfl/chipcalc/puzzle/Chip;", "Lkotlin/collections/ArrayList;", "nameSpinnerOnSelectedListener", "open/gfl/chipcalc/activity/BoardSettingActivity$nameSpinnerOnSelectedListener$1", "Lopen/gfl/chipcalc/activity/BoardSettingActivity$nameSpinnerOnSelectedListener$1;", "starRatingBarChange", "Landroid/widget/RatingBar$OnRatingBarChangeListener;", "templateSpinnerOnSelectedListener", "open/gfl/chipcalc/activity/BoardSettingActivity$templateSpinnerOnSelectedListener$1", "Lopen/gfl/chipcalc/activity/BoardSettingActivity$templateSpinnerOnSelectedListener$1;", "calculateButtonClick", "", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "reimportButtonClick", "updateStatText", "updateTemplate", "app_release"}, k = 1, mv = {1, 1, 16})
public final class BoardSettingActivity extends AppCompatActivity {
    private ArrayList<Chip> chips;
    private final BoardSettingActivity$nameSpinnerOnSelectedListener$1 nameSpinnerOnSelectedListener = new BoardSettingActivity$nameSpinnerOnSelectedListener$1(this);
    private final RatingBar.OnRatingBarChangeListener starRatingBarChange = new BoardSettingActivity$starRatingBarChange$1(this);
    private final BoardSettingActivity$templateSpinnerOnSelectedListener$1 templateSpinnerOnSelectedListener = new BoardSettingActivity$templateSpinnerOnSelectedListener$1(this);

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_boardsetting);
        Serializable serializableExtra = getIntent().getSerializableExtra("chips");
        if (serializableExtra != null) {
            this.chips = (ArrayList) serializableExtra;
            Spinner spinner = (Spinner) findViewById(R.id.nameSpinner);
            RatingBar ratingBar = (RatingBar) findViewById(R.id.starRatingBar);
            Spinner spinner2 = (Spinner) findViewById(R.id.templateSpinner);
            Switch switchR = (Switch) findViewById(R.id.rotationSwitch);
            switchR.setVisibility(View.INVISIBLE);
            spinner.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, Board.NAMES));
            spinner.setOnItemSelectedListener(this.nameSpinnerOnSelectedListener);
            spinner2.setOnItemSelectedListener(this.templateSpinnerOnSelectedListener);
            ratingBar.setOnRatingBarChangeListener(this.starRatingBarChange);
            ((Button) findViewById(R.id.reimportButton)).setOnClickListener(new BoardSettingActivity$onCreate$1(this));
            ((Button) findViewById(R.id.calculateButton)).setOnClickListener(new BoardSettingActivity$onCreate$2(this));
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.ArrayList<open.gfl.chipcalc.puzzle.Chip> /* = java.util.ArrayList<open.gfl.chipcalc.puzzle.Chip> */");
    }

    public void onBackPressed() {
        new AlertDialog.Builder(this).setTitle(getString(R.string.msg_back_title)).setMessage(getString(R.string.msg_back)).setPositiveButton(R.string.yes, new BoardSettingActivity$onBackPressed$1(this)).setNegativeButton(R.string.no, BoardSettingActivity$onBackPressed$2.INSTANCE).show();
    }


    public final void reimportButtonClick() {
        onBackPressed();
    }


    public final void calculateButtonClick() {
        Spinner spinner = (Spinner) findViewById(R.id.nameSpinner);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.starRatingBar);
        Switch switchR = (Switch) findViewById(R.id.maxSwitch);
        Switch switchR2 = (Switch) findViewById(R.id.rotationSwitch);
        Spinner spinner2 = (Spinner) findViewById(R.id.templateSpinner);
        String[] strArr = Board.NAMES;
        String str = strArr[spinner.getSelectedItemPosition()];
        int rating = (int) ratingBar.getRating();
        boolean isChecked = switchR.isChecked();
        boolean isChecked2 = switchR2.isChecked();
        SpinnerAdapter adapter = spinner2.getAdapter();
        int selectedItemPosition = adapter.getCount() > 1 ? spinner2.getSelectedItemPosition() : -1;
        Intent intent = new Intent(this, CalculateActivity.class);
        ArrayList<Chip> arrayList = this.chips;
        if (arrayList == null) {
            Intrinsics.throwUninitializedPropertyAccessException("chips");
        }
        intent.putExtra("chips", arrayList);
        intent.putExtra("name", str);
        intent.putExtra("star", rating);
        intent.putExtra("templateIndex", selectedItemPosition);
        intent.putExtra("maxLevel", isChecked);
        intent.putExtra("rotation", isChecked2);
        intent.putExtra("symmetry", false);
        startActivity(intent);
    }


    public final void updateTemplate() {
        List list;
        Spinner spinner = (Spinner) findViewById(R.id.nameSpinner);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.starRatingBar);
        Spinner spinner2 = (Spinner) findViewById(R.id.templateSpinner);
        String[] strArr = Board.NAMES;
        String str = strArr[spinner.getSelectedItemPosition()];
        int rating = (int) ratingBar.getRating();
        String string = getString(R.string.setting_maxstat);
        if (rating == 5) {
            ArrayList arrayList = StatTemplate.PRESET.get(str);
            Integer valueOf = arrayList != null ? Integer.valueOf(arrayList.size()) : null;
            if (valueOf == null) {
                Intrinsics.throwNpe();
            }
            if (valueOf.intValue() > 1) {
                Iterable<StatTemplate> iterable = arrayList;
                Collection arrayList2 = new ArrayList(collectionSizeOrDefault(iterable, 10));
                for (StatTemplate statTemplate : iterable) {
                    arrayList2.add(statTemplate.toString(string));
                }
                list = CollectionsKt.toList((List) arrayList2);
            } else {
                list = CollectionsKt.listOf(string);
            }
        } else {
            list = CollectionsKt.listOf(string);
        }
        spinner2.setAdapter(new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, list));
        updateStatText();
    }

    public static <T> int collectionSizeOrDefault(Iterable<? extends T> iterable, int i) {
        return iterable instanceof Collection ? ((Collection) iterable).size() : i;
    }

    public final void updateStatText() {
        StatTemplate statTemplate;
        Spinner spinner = (Spinner) findViewById(R.id.templateSpinner);
        TextView textView = (TextView) findViewById(R.id.statTextView);
        Spinner spinner2 = (Spinner) findViewById(R.id.nameSpinner);
        RatingBar ratingBar = (RatingBar) findViewById(R.id.starRatingBar);
        String[] strArr = Board.NAMES;
        String str = strArr[spinner2.getSelectedItemPosition()];
        int rating = (int) ratingBar.getRating();
        SpinnerAdapter adapter = spinner.getAdapter();
        int selectedItemPosition = adapter.getCount() > 1 ? spinner.getSelectedItemPosition() : -1;
        if (selectedItemPosition < 0) {
            textView.setText(Board.getMaxStat(str, rating).toString());
            return;
        }
        ArrayList arrayList = StatTemplate.PRESET.get(str);
        textView.setText(String.valueOf((arrayList == null || (statTemplate = (StatTemplate) arrayList.get(selectedItemPosition)) == null) ? null : statTemplate.stat));
    }
}
