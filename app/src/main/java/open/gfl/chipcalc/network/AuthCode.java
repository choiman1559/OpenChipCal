package open.gfl.chipcalc.network;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthCode {
    private static final String MD5 = "MD5";
    static final String SIGN_KEY = "yundoudou";
    private static final String UTF8 = "UTF-8";

    private enum authCodeMode {
        encode,
        decode
    }

    private static int toUnsignedInt(byte b) {
        return b & 255;
    }

    public static String decode(String str, String str2) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return authcode(str, str2, authCodeMode.decode, 3600);
    }

    public static byte[] decodeWithGzip(String str, String str2) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return decodeWithGzip(str, str2, 3600);
    }

    private static byte[] decodeWithGzip(String source, String key, int expiry) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (source != null && key != null) {
            key = MD5(key);
            String str = MD5(cutString(key, 16, 16));
            String str2 = MD5(cutString(key, 0, 16));
            String pass = str + MD5(str);

            byte[] buffer;
            try {
                buffer = Base64.getDecoder().decode(cutString(source, 0));
            } catch (IllegalArgumentException var15) {
                try {
                    buffer = Base64.getDecoder().decode(cutString(source + "=", 0));
                } catch (IllegalArgumentException var14) {
                    try {
                        buffer = Base64.getDecoder().decode(cutString(source + "==", 0));
                    } catch (IllegalArgumentException var13) {
                        return null;
                    }
                }
            }

            byte[] bytes = rc4(buffer, pass);
            String str5 = new String(bytes, "UTF-8");
            long num2 = Long.valueOf(cutString(str5, 0, 10));
            byte[] destinationArray = new byte[bytes.length - 26];
            System.arraycopy(bytes, 26, destinationArray, 0, bytes.length - 26);
            byte[] buffer4 = new byte[bytes.length - 26 + str2.length()];
            System.arraycopy(destinationArray, 0, buffer4, 0, destinationArray.length);
            System.arraycopy(str2.getBytes("UTF-8"), 0, buffer4, destinationArray.length, str2.length());
            return (num2 == 0L || num2 - UnixTimestamp() > 0L) && cutString(str5, 10, 16).equals(cutString(MD5(new String(buffer4, "UTF-8")), 0, 16)) ? null : destinationArray;
        } else {
            return null;
        }
    }

    private static String MD5(String str) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        byte[] digest = MessageDigest.getInstance(MD5).digest(str.getBytes(UTF8));
        String str2 = "";
        for (int i = 0; i < digest.length; i++) {
            str2 = str2 + String.format("%02x", new Object[]{Byte.valueOf(digest[i])});
        }
        return str2;
    }

    private static String cutString(String str, int i, int i2) {
        int i3 = 0;
        if (i >= 0) {
            if (i2 < 0) {
                i2 *= -1;
                int i4 = i - i2;
                if (i4 < 0) {
                    i2 = i;
                    i = 0;
                } else {
                    i = i4;
                }
            }
            if (i > str.length()) {
                return "";
            }
            i3 = i;
        } else if (i2 < 0 || (i2 = i2 + i) <= 0) {
            return "";
        }
        if (str.length() - i3 < i2) {
            i2 = str.length() - i3;
        }
        return substring(str, i3, i2);
    }

    private static String cutString(String str, int i) {
        return cutString(str, i, str.length());
    }

    private static byte[] getKey(byte[] bArr, int i) {
        byte[] bArr2 = new byte[i];
        for (int i2 = 0; i2 < i; i2++) {
            bArr2[i2] = (byte) i2;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i; i4++) {
            i3 = ((i3 + toUnsignedInt(bArr2[i4])) + toUnsignedInt(bArr[i4 % bArr.length])) % i;
            byte b = bArr2[i4];
            bArr2[i4] = bArr2[i3];
            bArr2[i3] = b;
        }
        return bArr2;
    }

    private static String authcode(String source, String key, AuthCode.authCodeMode operation, int expiry) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        if (source != null && key != null) {
            key = MD5(key);
            String str = MD5(cutString(key, 16, 16));
            String str2 = MD5(cutString(key, 0, 16));
            String pass = str + MD5(str);
            byte[] buffer;
            if (operation != AuthCode.authCodeMode.decode) {
                source = (expiry != 0 ? String.valueOf((long)expiry + UnixTimestamp()) : "0000000000") + cutString(MD5(source + str2), 0, 16) + source;
                buffer = rc4(source.getBytes("UTF-8"), pass);
                return Base64.getEncoder().encodeToString(buffer);
            } else {
                try {
                    buffer = Base64.getDecoder().decode(cutString(source, 0));
                } catch (IllegalArgumentException var14) {
                    try {
                        buffer = Base64.getDecoder().decode(cutString(source + "=", 0));
                    } catch (IllegalArgumentException var13) {
                        try {
                            buffer = Base64.getDecoder().decode(cutString(source + "==", 0));
                        } catch (IllegalArgumentException var12) {
                            return "";
                        }
                    }
                }

                byte[] bytes = rc4(buffer, pass);
                String str5 = new String(bytes, "UTF-8");
                long num2 = Long.valueOf(cutString(str5, 0, 10));
                return (num2 == 0L || num2 - UnixTimestamp() > 0L) && cutString(str5, 10, 16).equals(cutString(MD5(cutString(str5, 26) + str2), 0, 16)) ? cutString(str5, 26) : "";
            }
        } else {
            return "";
        }
    }

    private static byte[] rc4(byte[] bArr, String str) throws UnsupportedEncodingException {
        if (bArr == null || str == null) {
            return null;
        }
        byte[] bArr2 = new byte[bArr.length];
        byte[] key = getKey(str.getBytes(UTF8), 256);
        int i = 0;
        int i2 = 0;
        for (int i3 = 0; i3 < bArr.length; i3++) {
            i = (i + 1) % key.length;
            i2 = (i2 + toUnsignedInt(key[i])) % key.length;
            byte b = key[i];
            key[i] = key[i2];
            key[i2] = b;
            bArr2[i3] = (byte) (bArr[i3] ^ key[(toUnsignedInt(key[i]) + toUnsignedInt(key[i2])) % key.length]);
        }
        return bArr2;
    }

    private static long UnixTimestamp() {
        return System.currentTimeMillis() / 1000;
    }

    private static String substring(String str, int i, int i2) {
        return str.substring(i, Math.min(i2 + i, str.length()));
    }
}
