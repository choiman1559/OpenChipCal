package open.gfl.chipcalc.network;

import com.github.megatronking.netbare.NetBareUtils;
import com.github.megatronking.netbare.http.HttpBody;
import com.github.megatronking.netbare.http.HttpRequest;
import com.github.megatronking.netbare.http.HttpRequestHeaderPart;
import com.github.megatronking.netbare.http.HttpResponse;
import com.github.megatronking.netbare.http.HttpResponseHeaderPart;
import com.github.megatronking.netbare.injector.InjectorCallback;
import com.github.megatronking.netbare.injector.SimpleHttpInjector;

import org.apache.commons.httpclient.ChunkedInputStream;

import java.io.ByteArrayInputStream;
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

    private static byte[] unchunkData(byte[] r7) throws IOException {
        ByteArrayInputStream bais = new ByteArrayInputStream(r7);
        ChunkedInputStream cis = new ChunkedInputStream(bais);

        byte[] resBytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        byte[] buffer = new byte[1024];
        int read = -1;
        try {
            while ( (read = cis.read(buffer)) != -1 ) {
                bos.write(buffer, 0, read);
            }
            resBytes = bos.toByteArray();
            bos.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return resBytes;
    }
}
