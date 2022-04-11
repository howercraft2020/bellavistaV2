package cl.clsoft.bave.apis;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.NoSuchAlgorithmException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static OkHttpClient okHttpClient2 = UnsafeOkHttpClient.getUnsafeOkHttpClient();


    CertificateFactory cf = CertificateFactory.getInstance("X.509");
    String caInput = "-----BEGIN CERTIFICATE-----\n" +
            "MIIDOjCCAiICCQDze3Y21CepcDANBgkqhkiG9w0BAQUFADBfMQswCQYDVQQGEwJY\n" +
            "WDEVMBMGA1UEBwwMRGVmYXVsdCBDaXR5MQwwCgYDVQQKDANTMlQxFTATBgNVBAsM\n" +
            "DEVxdWlwb19TSUdMRTEUMBIGA1UEAwwLMTAuMTMuMzAuMzEwHhcNMjIwMzI0MTQw\n" +
            "NDQ2WhcNMjMwMzI0MTQwNDQ2WjBfMQswCQYDVQQGEwJYWDEVMBMGA1UEBwwMRGVm\n" +
            "YXVsdCBDaXR5MQwwCgYDVQQKDANTMlQxFTATBgNVBAsMDEVxdWlwb19TSUdMRTEU\n" +
            "MBIGA1UEAwwLMTAuMTMuMzAuMzEwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK\n" +
            "AoIBAQChqJpmSuWHKfOt9gZL4BG3etCm6owdRbHvs/+hACK5ruYPTlfwc2zOWJdV\n" +
            "9sMOefjKVd85aa3GQnXxDlNRnhrnVoeJvpsfFdTfRofBsmQjDAWMlXhgtg5xEMc4\n" +
            "O+ljov1Uwvccd7IFtrIlmPsqAKVEIes8MPbtE1ThCGEa7uKI2iurqsvP7pAyjgs7\n" +
            "cHm6WDnaCqSzH7yXalW8XSYVCNEFE7oyL+eY7BPKwTUshWo4sLBOJ6JBdbXvc0wE\n" +
            "76+OPioWoGodkHMYezfYvy72rUjh1qGfZ2xQwfXxSgh8o9rqtbp4zbsRJ+ORYxU8\n" +
            "6KqwADT14UVV1om1Ge6Sm9K8ChFNAgMBAAEwDQYJKoZIhvcNAQEFBQADggEBAIQ+\n" +
            "ILasOl7xKBLZgtMJqo0R37emH9OMZuhDHgU3cg93lkVou3gz5YcALEmWAx7d/SIm\n" +
            "P+bbz39F9ftVSTOlALPqoY6YXpj4ysbm1409LISq5+7/4AH0MJB9RZNg2qrnRD9b\n" +
            "51z6L6o/99DjOc2HeOYC/T8dWNsEZ7Khb3j6HyOyqGCfm0j1Sv6cowRdBJW/RRPO\n" +
            "zEN8NOxvfzqBPLoWlT0lcpbSOJsRIdmib2VjfDT20EDmxq4d/uelPV3fpkbe7whE\n" +
            "XX5n3zBsFOPtaTOcsJrALEMUhVeYfCCaHC93vwsGqpOTA1/LcFWNRtxaVpWOfM9b\n" +
            "ofHF1AR0QGdXStAOsPs=\n" +
            "-----END CERTIFICATE-----";

    Certificate ca;
    KeyStore keyStore;
    String pfxPassword = "Famae2022";



    private static final OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .sslSocketFactory(getSSLSocketFactory())
            .hostnameVerifier(new HostnameVerifier() {

                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            }).build();




    private static Retrofit retrofit = null;

    public RetrofitClient() throws NoSuchAlgorithmException, CertificateException {
    }

    public static Retrofit getClient(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }
    public static Retrofit getClientResponse(String baseUrl) {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl)
                    .client(okHttpClient2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
    }

    private static SSLSocketFactory getSSLSocketFactory() {

        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            return sslSocketFactory;
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            return null;
        }

    }




}

