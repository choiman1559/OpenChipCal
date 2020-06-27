package open.gfl.chipcalc.activity;

import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.network.GFLInjector;
import open.gfl.chipcalc.network.GFLReader;
import open.gfl.chipcalc.puzzle.Chip;
import com.github.megatronking.netbare.NetBare;
import com.github.megatronking.netbare.NetBareConfig;
import com.github.megatronking.netbare.http.HttpInjectInterceptor;
import com.github.megatronking.netbare.http.HttpVirtualGatewayFactory;
import com.github.megatronking.netbare.ip.IpAddress;
import com.github.megatronking.netbare.ssl.JKS;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\bH\u0002J\"\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u000bH\u0016J\u0012\u0010\u0011\u001a\u00020\u000b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u000bH\u0014J\u0016\u0010\u0015\u001a\u00020\u000b2\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002J\b\u0010\u0019\u001a\u00020\u000bH\u0002J\u0010\u0010\u001a\u001a\u00020\u000b2\u0006\u0010\t\u001a\u00020\bH\u0003J\u0006\u0010\u001b\u001a\u00020\u000bJ\b\u0010\u001c\u001a\u00020\u000bH\u0002J\b\u0010\u001d\u001a\u00020\u000bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lopen/gfl/chipcalc/activity/VpnActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "config", "Lcom/github/megatronking/netbare/NetBareConfig;", "reader", "Lopen/gfl/chipcalc/network/GFLReader;", "getUID", "", "data", "onActivityResult", "", "requestCode", "", "resultCode", "Landroid/content/Intent;", "onBackPressed", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "passChipsAndFinish", "chips", "Ljava/util/ArrayList;", "Lopen/gfl/chipcalc/puzzle/Chip;", "prepare", "saveData", "sendNotification", "start", "stop", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
public final class VpnActivity extends AppCompatActivity {
    private static final String NAME = "GFLChipCalc";
    private static final int REQUEST_START_VPN = 1;
    private HashMap _$_findViewCache;
    private NetBareConfig config;
    private final GFLReader reader = new GFLReader(this);

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
        View view = (View) this._$_findViewCache.get(i);
        if (view != null) {
            return view;
        }
        View findViewById = findViewById(i);
        this._$_findViewCache.put(i, findViewById);
        return findViewById;
    }

    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView((int) R.layout.activity_vpn);
        ((Button) findViewById(R.id.backButton)).setOnClickListener(new VpnActivity$onCreate$1(this));
        prepare();
        start();
    }

    protected void onResume() {
        super.onResume();
        if (this.reader.isDataRead()) {
            String data = this.reader.getData();
            ArrayList<Chip> parseAndFilter = Chip.parseAndFilter(data);
            if (parseAndFilter == null) {
                Toast.makeText(this, getString(R.string.toast_import_fail), Toast.LENGTH_SHORT).show();
                this.reader.reset();
                return;
            }
            stop();
            new AlertDialog.Builder(this).setTitle(getString(R.string.msg_save_title)).setMessage(getString(R.string.msg_save)).setCancelable(false).setPositiveButton(R.string.yes, new VpnActivity$onResume$1(this, data, parseAndFilter)).setNegativeButton(R.string.no, new VpnActivity$onResume$2(this, parseAndFilter)).show();
        }
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i2 == -1 && i == 1) {
            start();
        }
    }

    public void onBackPressed() {
        stop();
        super.onBackPressed();
    }

    private String getUID(String str) {
        try {
            String string = new JSONObject(str).getJSONObject("user_info").getString("user_id");
            return string;
        } catch (Exception unused) {
            return "Unknown";
        }
    }

    public final void sendNotification() {
        Context context = this;
        Intent intent = new Intent(context, VpnActivity.class);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        NotificationCompat.Builder contentIntent = new NotificationCompat.Builder(context, Global.CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher).setContentTitle(getString(R.string.notification_success_title)).setContentText(getString(R.string.notification_success)).setPriority(1).setDefaults(-1).setAutoCancel(true).setContentIntent(PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT));
        NotificationManagerCompat from = NotificationManagerCompat.from(context);
        from.notify(0, contentIntent.build());
    }

    public final void saveData(java.lang.String r5) throws IOException {
        Calendar instance = Calendar.getInstance();
        Date time = instance.getTime();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd_HHmm", Locale.getDefault());
        String str2 = getUID(r5) + "_" + simpleDateFormat.format(time) + ".json";
        if (Intrinsics.areEqual("mounted", Environment.getExternalStorageState())) {
            File file = new File(getExternalFilesDir(null), str2);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file));
            outputStreamWriter.write(r5);
            outputStreamWriter.close();
            Toast.makeText(this, file.getCanonicalPath(), Toast.LENGTH_LONG).show();
        }
    }

    public final void passChipsAndFinish(ArrayList<Chip> arrayList) {
        Intent intent = new Intent(this, BoardSettingActivity.class);
        intent.putExtra("chips", arrayList);
        startActivity(intent);
        finish();
    }

    private void prepare() {
        NetBare.get().attachApplication(getApplication(), false);
        char[] charArray = NAME.toCharArray();
        NetBareConfig build = new NetBareConfig.Builder().dumpUid(false).setMtu(4096).setAddress(new IpAddress("10.1.10.1", 32)).addRoute(new IpAddress("0.0.0.0", 0)).setSession(NAME).setVirtualGatewayFactory(new HttpVirtualGatewayFactory(new JKS(getApplication(), NAME, charArray, NAME, NAME, NAME, NAME, NAME), CollectionsKt.listOf(HttpInjectInterceptor.createFactory(new GFLInjector(this.reader))))).build();
        this.config = build;
    }

    private void start() {
        Intent prepare = NetBare.get().prepare();
        if (prepare != null) {
            startActivityForResult(prepare, 1);
            return;
        }
        NetBare netBare = NetBare.get();
        NetBareConfig netBareConfig = this.config;
        if (netBareConfig == null) {
            Intrinsics.throwUninitializedPropertyAccessException("config");
        }
        netBare.start(netBareConfig);
        Toast.makeText(this, getString(R.string.toast_vpn_instruction), Toast.LENGTH_LONG).show();
        Intent intent = new Intent("android.intent.action.MAIN");
        intent.addCategory("android.intent.category.HOME");
        startActivity(intent);
    }

    private void stop() {
        NetBare.get().stop();
    }
}
