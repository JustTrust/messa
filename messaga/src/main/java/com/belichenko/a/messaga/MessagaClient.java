package com.belichenko.a.messaga;

import android.app.Application;
import android.content.Context;

import com.belichenko.a.messaga.data.models.MessageEvent;
import com.belichenko.a.messaga.listeners.OnMessageListener;
import com.belichenko.a.messaga.listeners.OnSuccessErrorListener;

import org.greenrobot.eventbus.EventBus;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import timber.log.Timber;

/**
 * Created by Belichenko Anton on 13.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MessagaClient implements BackgroundManager.Listener {

    private static MessagaClient sInstance;

    private boolean mConnected;
    private boolean mRegister;
    private Context mContext;
    private BackgroundManager mBackgroundManager;
    private WebSocket mWebSocket;
    private OnSuccessErrorListener mConnectionListener;
    private OnSuccessErrorListener mAuthenticationListener;
    private OnMessageListener mOnMessageListener;

    public static MessagaClient getInstance() {
        if (sInstance == null) {
            sInstance = new MessagaClient();
        }
        return sInstance;
    }

    private MessagaClient() {}

    public boolean isRegistered() {
        return mRegister;
    }

    public boolean isConnected() {
        return mConnected;
    }

    public Context getContext() {
        return mContext;
    }

    public EventBus getEventBus(){
        return EventBus.getDefault();
    }

    public void register(Context context) {
        if (context == null) {
            mRegister = false;
            Timber.d("register: context = null");
        } else if (!(context instanceof Application)) {
            mRegister = false;
            Timber.d("register: context != Application ");
        } else {
            if (BuildConfig.DEBUG) {
                Timber.plant(new Timber.DebugTree() {
                    @Override
                    protected String createStackElementTag(StackTraceElement element) {
                        return super.createStackElementTag(element)
                                + "::Line:" + element.getLineNumber() + ""
                                + "::" + element.getMethodName() + "()";
                    }
                });
            }
            mContext = context;
            mRegister = true;
            mBackgroundManager = BackgroundManager.getInstance((Application) mContext);
            mBackgroundManager.registerListener(this);
            Timber.d("register successful");
        }
    }

    public void connect() {
        if (isRegistered()) {
            if (isConnected()) {
                Timber.d("connect: already connected");
                if (mConnectionListener != null) {
                    mConnectionListener.onError("connect: already connected");
                }
            } else {
                openSocket();
            }
        } else {
            mConnected = false;
            Timber.d("connect: client not registered yet");
            if (mConnectionListener != null) {
                mConnectionListener.onError("connect: client not registered yet");
            }
        }
    }

    public void registerConnectionListener(OnSuccessErrorListener connectionListener) {
        mConnectionListener = connectionListener;
    }

    public void registerAuthenticationListener(OnSuccessErrorListener authenticationListener) {
        mAuthenticationListener = authenticationListener;
    }

    public void registerOnMessageListener(OnMessageListener onMessgeListener) {
        mOnMessageListener = onMessgeListener;
    }

    public void openSocket() {

        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(1000, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://echo.websocket.org")
                .build();
        client.newWebSocket(request, new SocketListener());

        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(String text) {
        if (isRegistered() && isConnected()) {
            mWebSocket.send(text);
        }
    }

    @Override
    public void onBecameForeground() {
        if (mWebSocket != null && !isConnected()) {
            connect();
        }
    }

    @Override
    public void onBecameBackground() {
        if (mWebSocket != null && isConnected()) {
            mWebSocket.close(0, "App became background");
        }
    }

    class SocketListener extends WebSocketListener {
        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            MessagaClient.this.mConnected = true;
            MessagaClient.this.mWebSocket = webSocket;
            if (mConnectionListener != null) {
                mConnectionListener.onSuccess();
            }
            EventBus.getDefault().post(new MessageEvent("open", response.message()));
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            EventBus.getDefault().post(new MessageEvent("msg", text));
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            MessagaClient.this.mConnected = false;
            EventBus.getDefault().post(new MessageEvent("close", reason));
        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            MessagaClient.this.mConnected = false;
            MessagaClient.this.mWebSocket = null;
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            if (mConnectionListener != null) {
                mConnectionListener.onError(t.getMessage());
            }
            MessagaClient.this.mConnected = false;
            MessagaClient.this.mWebSocket = null;
        }
    }

}
