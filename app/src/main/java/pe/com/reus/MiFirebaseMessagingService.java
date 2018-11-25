package pe.com.reus;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MiFirebaseMessagingService extends FirebaseMessagingService {

    public static final String TAG="NOTICIAS";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        String from = remoteMessage.getFrom();
        Log.d(TAG, "Mensaje recibido de:" + from);
        if (remoteMessage.getNotification() != null){
            Log.d(TAG,"Notificacion" + remoteMessage.getNotification().getBody());
        }
    }
}
