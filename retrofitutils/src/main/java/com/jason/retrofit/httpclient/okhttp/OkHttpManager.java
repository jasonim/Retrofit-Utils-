package com.jason.retrofit.httpclient.okhttp;

import com.jason.retrofit.httpclient.https.HttpsUtils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by hujd on 16-6-23.
 */
public class OkHttpManager {
//    private static final String CRT_TEST = "-----BEGIN CERTIFICATE-----\n" +
//            "MIIC0DCCAbigAwIBAgIEV24u4DANBgkqhkiG9w0BAQUFADAqMRQwEgYDVQQLEwt3c2Rhc2hpLmNv\n" +
//            "bTESMBAGA1UEAxMJbG9jYWxob3N0MB4XDTE2MDYyNTA3MTIzMloXDTE3MDYyNTA3MTIzMlowKjEU\n" +
//            "MBIGA1UECxMLd3NkYXNoaS5jb20xEjAQBgNVBAMTCWxvY2FsaG9zdDCCASIwDQYJKoZIhvcNAQEB\n" +
//            "BQADggEPADCCAQoCggEBANNRABiyRdPmL//0F++6/XsxCo6J90U4HFDJ8JFN+/my9O0gRVtKFtNj\n" +
//            "GEfXPAvZsIyOwrG/3nwdfGZJAGDfekKchQxYIzhzF68jtYooVOFv3IghW57SHuhUl+ZSwO4bc+m3\n" +
//            "TrGceqegEOMyZbZWOwCmlzNwfFa+m4gGAnVjPReThSvjUwstOut+Nx4PEjIqmGoA2CC8yysTkaTm\n" +
//            "YDHZoVlXI88LNMFijWZWSJkWp88n4PqBQHE8iMJJhLoc969rBOhqOCRGMo1w/upWi26cvoqphoNx\n" +
//            "Jc+lDSsY9t62wW2tBqwAg+Mc2SHrwmVRUNnvX7C/i/glhfklO9KgFYRd7MMCAwEAATANBgkqhkiG\n" +
//            "9w0BAQUFAAOCAQEAk9StlQqPjcWhrveDtEkDAnd38UuUuYCM51coAHh/Y0/KK7o+8GSN7CcVcTal\n" +
//            "RUIq4/MX65zK1cistiwnH2Uy8li5UCWeijdbD3Fo1K9vgrdB9fKGvCIYRDOpsbq5r4XE7cJF1ZO0\n" +
//            "fqj7wWF7x/EdJc2Nz0oISTHSLrkqBWYhXWZBjmUjXKWs8uBEdq0xi8jFXPRAtn3tOOldzKKEf9Kx\n" +
//            "sWazUO9rVk/wMJz+tDp7OmYQaKTw+6J+EGyCCknA5sgUMwVG/HVoJ8Nk6A3Vxky4+2kMtpewI/ht\n" +
//            "8qGYtGEa/cdHppyfc5lW0sQ85e6Ixw0vrLiwXl0pEuzZkcHq0OGhlw==\n" +
//            "-----END CERTIFICATE-----\n";
    private final OkHttpClient.Builder httpBuilder;

    private static class SingletonHolder{
        private static final OkHttpManager INSTANCE = new OkHttpManager();
    }

    private OkHttpManager() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request newRequest = chain.request().newBuilder().addHeader("Content-Type", "application/json").build();
                return chain.proceed(newRequest);
            }
        };


        // init OkHttpClient
        httpBuilder = new OkHttpClient().newBuilder();
        httpBuilder.addInterceptor(interceptor);

    }

    private void  setHttpBuilderSSLParams() {
        //设置自签名证书相关参数
//        InputStream[] certificates = {new Buffer().writeUtf8(CER_TEST).inputStream()};
        HttpsUtils.SSLParams sslParams = HttpsUtils.getSslSocketFactory(null, null, null);
        httpBuilder.hostnameVerifier(new HttpsUtils.UnSafeHostnameVerifier())
                .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);
    }

    /**
     * 获取OkHttpClient
     * @param isSupportSSL 是否支持自签名证书
     * @return
     */
    public static OkHttpClient getOkHttpClient(boolean isSupportSSL) {
        if(isSupportSSL) {
            SingletonHolder.INSTANCE.setHttpBuilderSSLParams();
        }
        return SingletonHolder.INSTANCE.httpBuilder.build();
    }

}
