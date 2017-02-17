package com.belichenko.a.messaga;

import com.belichenko.a.messaga.data.models.MessageEvent;

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
 * Created by Belichenko Anton on 17.02.17.
 * mailto: a.belichenko@gmail.com
 */

public class MsgWebSocket extends WebSocketListener {

    private static MsgWebSocket sInstance;

    public static MsgWebSocket getInstance() {
        if (sInstance == null) {
            sInstance = new MsgWebSocket();
        }
        return sInstance;
    }

    private MsgWebSocket() {
        connect();
    }

    private boolean mConnected;
    private WebSocket mWebSocket;

    public boolean isConnected() {
        return mConnected;
    }

    private void connect() {
        OkHttpClient client = new OkHttpClient.Builder()
                .readTimeout(0, TimeUnit.MILLISECONDS)
                .build();

        Request request = new Request.Builder()
                .url("ws://echo.websocket.org")
                .build();
        client.newWebSocket(request, this);

        // Trigger shutdown of the dispatcher's executor so this process can exit cleanly.
        client.dispatcher().executorService().shutdown();
    }

    public void sendMessage(String text) {
        if (mWebSocket != null && isConnected()) {
            mWebSocket.send(text);
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        mConnected = true;
        mWebSocket = webSocket;
        EventBus.getDefault().post(new MessageEvent("open", response.message()));
        Timber.d("onOpen: webSocket = [%s], response = [%s]", webSocket, response.message());
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        EventBus.getDefault().post(new MessageEvent("msg", text));
        Timber.d("onMessage: webSocket = [%s], text = [%s]", webSocket, text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        Timber.d("onMessage: webSocket = [%s], bytes = [%s]", webSocket, bytes);
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        mConnected = false;
        EventBus.getDefault().post(new MessageEvent("close", reason));
        Timber.d("onClosing: webSocket = [%s], code = [%s], reason = [%s]", webSocket, code, reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        mConnected = false;
        mWebSocket = null;
        Timber.d("onClosed: webSocket = [%s], code = [%s], reason = [%s]", webSocket, code, reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        mConnected = false;
        mWebSocket = null;
        Timber.d("onFailure: webSocket = [%s], t = [%s], response = [%s]", webSocket, t, response);
        connect();
    }

    void goForeground() {
        Timber.d("goForeground: ");
        connect();
    }

    void goBackground() {
        Timber.d("goBackground:");
        mWebSocket.close(0, "App go in Background");
    }

}
