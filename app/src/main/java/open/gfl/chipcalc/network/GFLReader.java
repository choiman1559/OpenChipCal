package open.gfl.chipcalc.network;

import open.gfl.chipcalc.activity.VpnActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

public class GFLReader {
    private static final String SIGNKEY = "sign";
    private static final String TAG = "GFL_GFLReader";
    private String data;
    private boolean dataRead = false;
    private String key;
    private boolean keyReceived = false;
    private final VpnActivity vpnActivity;

    public GFLReader(VpnActivity vpnActivity2) {
        this.vpnActivity = vpnActivity2;
    }

    public void process_sign(String pm) {
        try {
            key = parseSign(decode(pm, AuthCode.SIGN_KEY));
            keyReceived = true;
        } catch (Exception ex) { }
    }

    private String parseSign(String str) throws JSONException {
        return new JSONObject(str).getString(SIGNKEY);
    }

    synchronized void process_index(String str) {
        if (!str.contains("{")) {
            while (!this.keyReceived) {
                try {
                    wait();
                } catch (Exception unused) {
                }
            }
            this.data = decode(str, this.key);
            this.dataRead = true;
            this.vpnActivity.sendNotification();
        }
    }

    public void reset() {
        this.keyReceived = false;
        this.dataRead = false;
    }

    public boolean isDataRead() {
        return this.dataRead;
    }

    public String getData() {
        return this.data;
    }

    private static String decode(String str, String str2) {
        if (str != null) {
            try {
                if (!str.isEmpty()) {
                    if (str.length() <= 1) {
                        return str;
                    }
                    if (str.startsWith("#")) {
                        return decode_gzip(AuthCode.decodeWithGzip(str.substring(1), str2));
                    }
                    return AuthCode.decode(str, str2);
                }
            } catch (Exception unused) {
            }
        }
        return "";
    }

    public static String decode_gzip(byte[] array) throws IOException {
        GZIPInputStream gzipIS = new GZIPInputStream(new ByteArrayInputStream(array));
        ByteArrayOutputStream byteOS = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int cl;
        while ((cl = gzipIS.read(buffer)) != -1) {
            byteOS.write(buffer, 0, cl);
        }
        return byteOS.toString();
    }
}
