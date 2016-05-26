/**
 * Created by Fahar on 5/18/2016.
 */
package com.example.jamshi.messaging;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
//import android.content.IntentReceiver;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.telephony.gsm.SmsMessage;

public class SMSApplication extends BroadcastReceiver {

static final String ACTION = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        NotificationManager mNotificationManager = (NotificationManager) arg0.getSystemService(Context.NOTIFICATION_SERVICE);
        if (arg1.getAction().equals(ACTION)) {

            StringBuilder sb = new StringBuilder();
            String from = new String();
            String body = new String();

            Bundle bundle = arg1.getExtras();
            if (bundle != null) {
                Object[] pdus = (Object[]) bundle.get("pdus");
                for (Object pdu : pdus){
                    SmsMessage messages = SmsMessage.createFromPdu((byte[]) pdu);
                    sb.append(messages.getDisplayOriginatingAddress());
                    from = messages.getDisplayOriginatingAddress();
                    sb.append(messages.getDisplayMessageBody());
                    body= messages.getDisplayMessageBody();
                }
            }

            int icon = R.drawable.icon;
            CharSequence tickerText = from + ": " + body;
            long when = System.currentTimeMillis();

            Notification notification = new Notification(icon, tickerText, when);
            CharSequence contentTitle = "New SMS Message";
            CharSequence contentText = sb.toString();
            Intent notificationIntent = new Intent();
            PendingIntent contentIntent = PendingIntent.getActivity(arg0, 0, notificationIntent, 0);

            notification.setLatestEventInfo(arg0, contentTitle, contentText, contentIntent);
            notification.vibrate = new long[] { 100, 250, 100, 500};
            notification.flags |= Notification.FLAG_AUTO_CANCEL;

            mNotificationManager.notify(1, notification);
        }

    }
}



