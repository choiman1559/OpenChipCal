package open.gfl.chipcalc.activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.puzzle.Chip;
import java.util.ArrayList;
import java.util.HashMap;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0002J\b\u0010\u0005\u001a\u00020\u0004H\u0002J\b\u0010\u0006\u001a\u00020\u0004H\u0002J\b\u0010\u0007\u001a\u00020\u0004H\u0002J\"\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0014J\u0012\u0010\u000e\u001a\u00020\u00042\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0016¨\u0006\u0019"}, d2 = {"Lopen/gfl/chipcalc/activity/ImportActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "fButtonClick", "", "initAd", "initNotificationChannel", "lButtonClick", "onActivityResult", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateOptionsMenu", "", "menu", "Landroid/view/Menu;", "onOptionsItemSelected", "item", "Landroid/view/MenuItem;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})

public final class ImportActivity extends AppCompatActivity {
    private static final int REQUEST_READ = 1;
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
        setContentView((int) R.layout.activity_import);
        initNotificationChannel();
        new Thread(ImportActivity$onCreate$1.INSTANCE).start();
        setSupportActionBar(findViewById(R.id.toolbar));
        findViewById(R.id.fileButton).setOnClickListener(new ImportActivity$onCreate$2(this));
        findViewById(R.id.loginButton).setOnClickListener(new ImportActivity$onCreate$3(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem menuItem) {
        Intrinsics.checkParameterIsNotNull(menuItem, "item");
        if (menuItem.getItemId() != R.id.aboutMenuItem) {
            return super.onOptionsItemSelected(menuItem);
        }
        startActivity(new Intent(this, AboutActivity.class));
        return true;
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 1 && i2 == -1) {
            Log.d("uri",intent != null ? intent.getData().toString() : "null");
            ArrayList<Chip> readChip_file = Chip.readChip_file(getApplicationContext(), intent != null ? intent.getData() : null);
            if (readChip_file == null) {
                Toast.makeText(this, getString(R.string.toast_import_fail), Toast.LENGTH_SHORT).show();
            } else if (readChip_file.isEmpty()) {
                Toast.makeText(this, getString(R.string.toast_import_none), Toast.LENGTH_SHORT).show();
            } else {
                Intent intent2 = new Intent(this, BoardSettingActivity.class);
                intent2.putExtra("chips", readChip_file);
                startActivity(intent2);
            }
        }
    }

    public final void fButtonClick() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("application/*");
        startActivityForResult(intent, 1);
    }

    public final void lButtonClick() {
        startActivity(new Intent(this, VpnActivity.class));
    }

    private void initNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            String string = getString(R.string.channel_name);
            String string2 = getString(R.string.channel_description);
            NotificationChannel notificationChannel = new NotificationChannel(Global.CHANNEL_ID, string, NotificationManager.IMPORTANCE_LOW);
            notificationChannel.setDescription(string2);
            Object systemService = getSystemService(Context.NOTIFICATION_SERVICE);
            if (systemService != null) {
                ((NotificationManager) systemService).createNotificationChannel(notificationChannel);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type android.app.NotificationManager");
        }
    }
}
