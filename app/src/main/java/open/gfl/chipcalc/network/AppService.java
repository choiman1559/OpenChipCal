package open.gfl.chipcalc.network;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import androidx.core.app.NotificationCompat;
import open.gfl.chipcalc.Global;
import open.gfl.chipcalc.R;
import open.gfl.chipcalc.activity.VpnActivity;
import com.github.megatronking.netbare.NetBareService;

public class AppService extends NetBareService {
    protected int notificationId() {
        return 1;
    }

    protected Notification createNotification() {
        Intent intent = new Intent(this, VpnActivity.class);
        intent.addCategory("android.intent.category.LAUNCHER");
        intent.setAction("android.intent.action.MAIN");
        return new NotificationCompat.Builder(this, Global.CHANNEL_ID).setSmallIcon(R.drawable.ic_launcher).setPriority(-2).setOngoing(true).setContentIntent(PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)).build();
    }
}
