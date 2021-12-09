package space.kuz.searchmoviesapp.util.mvp

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast

class ExampleBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
      if (ConnectivityManager.CONNECTIVITY_ACTION.equals(intent?.action)){
        var  noConnectivity:Boolean =
            intent?.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)!!
          if(noConnectivity){
              Toast.makeText(context, "Disconneсt", Toast.LENGTH_LONG).show()
          } else {
              Toast.makeText(context, "Conneted", Toast.LENGTH_LONG).show()
          }
      }
    }
}