package creativestudioaq.daily;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by honggyu on 2016-02-09.
 */


public class RestartService extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals("ACTION.Restart.OnService")){

            Intent i = new Intent(context,OnService.class);

            context.startService(i);
        }
    }
}
