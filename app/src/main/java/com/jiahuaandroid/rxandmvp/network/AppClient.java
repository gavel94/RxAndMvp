package com.jiahuaandroid.rxandmvp.network;

import android.util.Log;

import com.jiahuaandroid.rxandmvp.BuildConfig;
import com.jiahuaandroid.rxandmvp.network.modify.GsonConverterFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by jhhuang on 2016/5/19.
 * QQ:781913268
 * Description：AppClient
 */
public class AppClient
{
    /**
     * 自己处理过的gson转换器。详情：com.jiahuaandroid.rxandmvp.network.modify.GsonResponseBodyConverter
     */
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static Converter.Factory scalarsconverterfactory = ScalarsConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();
    public static String baseUrl = "http://192.168.1.240:9080/";
    private Retrofit retrofit;

//    private static class SingletonHolder {
//        private static final AppClient INSTANCE = new AppClient();
//    }
//
//    public static AppClient getInstance() {
//        return SingletonHolder.INSTANCE;
//    }

    public AppClient()
    {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS);

        if (BuildConfig.DEBUG)
        {
            builder.addInterceptor(new LoggerInterceptor("http", true));
        }

        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(scalarsconverterfactory)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .baseUrl(baseUrl)
                .build();

    }

    public AppClient(String url)
    {

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new HeaderInterceptor())
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS);

        if (BuildConfig.DEBUG)
        {
            builder.addInterceptor(new LoggerInterceptor("http", true));
        }

        OkHttpClient okHttpClient = builder.build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(rxJavaCallAdapterFactory)
                .baseUrl(url)
                .build();

    }


    public <T> T create(Class<T> clazz)
    {
        return retrofit.create(clazz);
    }

    /**
     * 全局头信息
     */
    class HeaderInterceptor implements Interceptor
    {

        @Override
        public Response intercept(Chain chain) throws IOException
        {
            Request request = chain.request();
            String path = request.url().encodedPath();
            Log.d("AppClient", path + ">>>path");
            String query = request.url().query();
            if (BuildConfig.DEBUG)
            {
                Log.d("AppClient", query + ">>>query");
            }
            //这里设置成你的全局header
            Map<String, String> headers = new HashMap<>();
            Request interRequest = chain.request().newBuilder()
                    .headers(Headers.of(headers))
                    .build();
            return chain.proceed(interRequest);
        }
    }
}


