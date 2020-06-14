package open.gfl.chipcalc.network;

import com.github.megatronking.netbare.NetBareUtils;
import com.github.megatronking.netbare.http.HttpBody;
import com.github.megatronking.netbare.http.HttpRequest;
import com.github.megatronking.netbare.http.HttpRequestHeaderPart;
import com.github.megatronking.netbare.http.HttpResponse;
import com.github.megatronking.netbare.http.HttpResponseHeaderPart;
import com.github.megatronking.netbare.injector.InjectorCallback;
import com.github.megatronking.netbare.injector.SimpleHttpInjector;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class GFLInjector extends SimpleHttpInjector {
    private static final String CHUNKED_TRANSFER = "chunked";
    private static final String CONTENT_ENCODING = "Content-Encoding";
    private static final String FILTER = "(.*(\\.girlfrontline\\.co\\.kr|\\.ppgame\\.com|\\.txwy\\.tw|\\.sunborngame\\.com).*\\/Index\\/(getDigitalSkyNbUid|getUidTianxiaQueue|getUidEnMicaQueue)|.*(\\.girlfrontline\\.co\\.kr|\\.ppgame\\.com|\\.txwy\\.tw|\\.sunborngame\\.com).*\\/Index\\/index)";
    private static final String FILTER_INDEX = ".*(\\.girlfrontline\\.co\\.kr|\\.ppgame\\.com|\\.txwy\\.tw|\\.sunborngame\\.com).*\\/Index\\/index";
    private static final String FILTER_SIGN = ".*(\\.girlfrontline\\.co\\.kr|\\.ppgame\\.com|\\.txwy\\.tw|\\.sunborngame\\.com).*\\/Index\\/(getDigitalSkyNbUid|getUidTianxiaQueue|getUidEnMicaQueue)";
    private static final String GZIP = "gzip";
    private static final String REGEX_HOST = "(\\.girlfrontline\\.co\\.kr|\\.ppgame\\.com|\\.txwy\\.tw|\\.sunborngame\\.com)";
    private static final String TRANSFER_ENCODING = "Transfer-Encoding";
    private ByteArrayOutputStream buffer;
    private boolean chunkedTransfer;
    private boolean gzipEncoding;
    private final GFLReader reader;
    private boolean reading = false;

    public boolean sniffRequest(HttpRequest httpRequest) {
        return true;
    }

    public boolean sniffResponse(HttpResponse httpResponse) {
        return true;
    }

    public GFLInjector(GFLReader gFLReader) {
        this.reader = gFLReader;
    }

    public void onRequestInject(HttpRequestHeaderPart httpRequestHeaderPart, InjectorCallback injectorCallback) throws IOException {
        injectorCallback.onFinished(httpRequestHeaderPart);
    }

    public void onRequestInject(HttpRequest httpRequest, HttpBody httpBody, InjectorCallback injectorCallback) throws IOException {
        injectorCallback.onFinished(httpBody);
    }

    public void onResponseInject(HttpResponseHeaderPart httpResponseHeaderPart, InjectorCallback injectorCallback) throws IOException {
        try {
            if (httpResponseHeaderPart.uri().toString().matches(FILTER)) {
                this.buffer = new ByteArrayOutputStream();
                boolean z = true;
                this.reading = true;
                this.chunkedTransfer = httpResponseHeaderPart.headers().containsKey("Transfer-Encoding") && httpResponseHeaderPart.header("Transfer-Encoding").contains(CHUNKED_TRANSFER);
                if (!httpResponseHeaderPart.headers().containsKey("Content-Encoding") || !httpResponseHeaderPart.header("Content-Encoding").contains(GZIP)) {
                    z = false;
                }
                this.gzipEncoding = z;
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            injectorCallback.onFinished(httpResponseHeaderPart);
            throw th;
        }
        injectorCallback.onFinished(httpResponseHeaderPart);
    }

    public void onResponseInject(HttpResponse httpResponse, HttpBody httpBody, InjectorCallback injectorCallback) throws IOException {
        try {
            if (httpResponse.url().matches(FILTER)) {
                byte[] array = httpBody.toBuffer().array();
                boolean z = true;
                if (this.chunkedTransfer) {
                    this.buffer.write(array);
                    if (!new String(array, StandardCharsets.UTF_8).endsWith(NetBareUtils.PART_END)) {
                        z = false;
                    }
                } else {
                    this.buffer.write(array);
                }
                if (z) {
                    this.buffer.close();
                    this.reading = false;
                    String read = read();
                    if (httpResponse.url().matches(FILTER_SIGN)) {
                        this.reader.process_sign(read);
                    } else if (httpResponse.url().matches(FILTER_INDEX)) {
                        this.reader.process_index(read);
                    }
                }
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            injectorCallback.onFinished(httpBody);
            throw th;
        }
        injectorCallback.onFinished(httpBody);
    }

    private String read() {
        byte[] bArr;
        try {
            if (this.chunkedTransfer) {
                bArr = unchunkData(this.buffer.toByteArray());
            } else {
                bArr = this.buffer.toByteArray();
            }
            if (this.gzipEncoding) {
                return GFLReader.decode_gzip(bArr);
            }
            return new String(bArr, StandardCharsets.UTF_8);
        } catch (IOException unused) {
            return "";
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0021, code lost:
        r1 = r4.toByteArray();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0031, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0036, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r0.addSuppressed(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x003a, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x003d, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0042, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:?, code lost:
        r0.addSuppressed(r7);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0046, code lost:
        throw r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0049, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0052, code lost:
        throw r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static byte[] unchunkData(byte[] r7) {
        //TODO : restore
        /*
            r0 = 0
            byte[] r1 = new byte[r0]
            r2 = 10240(0x2800, float:1.4349E-41)
            byte[] r2 = new byte[r2]
            java.io.ByteArrayInputStream r3 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0053 }
            r3.<init>(r7)     // Catch:{ IOException -> 0x0053 }
            org.apache.commons.httpclient.ChunkedInputStream r7 = new org.apache.commons.httpclient.ChunkedInputStream     // Catch:{ all -> 0x0047 }
            r7.<init>(r3)     // Catch:{ all -> 0x0047 }
            java.io.ByteArrayOutputStream r4 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x003b }
            r4.<init>()     // Catch:{ all -> 0x003b }
        L_0x0016:
            int r5 = r7.read(r2)     // Catch:{ all -> 0x002f }
            r6 = -1
            if (r5 == r6) goto L_0x0021
            r4.write(r2, r0, r5)     // Catch:{ all -> 0x002f }
            goto L_0x0016
        L_0x0021:
            byte[] r1 = r4.toByteArray()     // Catch:{ all -> 0x002f }
            r4.close()     // Catch:{ all -> 0x003b }
            r7.close()     // Catch:{ all -> 0x0047 }
            r3.close()     // Catch:{ IOException -> 0x0053 }
            goto L_0x0053
        L_0x002f:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0031 }
        L_0x0031:
            r2 = move-exception
            r4.close()     // Catch:{ all -> 0x0036 }
            goto L_0x003a
        L_0x0036:
            r4 = move-exception
            r0.addSuppressed(r4)     // Catch:{ all -> 0x003b }
        L_0x003a:
            throw r2     // Catch:{ all -> 0x003b }
        L_0x003b:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x003d }
        L_0x003d:
            r2 = move-exception
            r7.close()     // Catch:{ all -> 0x0042 }
            goto L_0x0046
        L_0x0042:
            r7 = move-exception
            r0.addSuppressed(r7)     // Catch:{ all -> 0x0047 }
        L_0x0046:
            throw r2     // Catch:{ all -> 0x0047 }
        L_0x0047:
            r7 = move-exception
            throw r7     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r0 = move-exception
            r3.close()     // Catch:{ all -> 0x004e }
            goto L_0x0052
        L_0x004e:
            r2 = move-exception
            r7.addSuppressed(r2)     // Catch:{ IOException -> 0x0053 }
        L_0x0052:
            throw r0     // Catch:{ IOException -> 0x0053 }
        L_0x0053:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: open.gfl.chipcalc.network.GFLInjector.unchunkData(byte[]):byte[]");
    }
}
