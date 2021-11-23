package space.kuz.searchmoviesapp.util.mvp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import space.kuz.searchmoviesapp.iu.main.EVENT
import space.kuz.searchmoviesapp.iu.main.MainActivity
import java.security.Provider
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class MyService: Service() {
    private val   TAG = "@@@"
    private  val binder = MyBinder()

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "OnCreate")
    }
    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "OnBind")
      //  return  null
        return binder
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
     var currentDate = Date()
       val  timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
        var time = timeFormat.format(currentDate)
        Log.d(TAG, "OnStartCommand ${intent?.extras?.getString(EVENT)} ${time} " )
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "OnUnBind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "OnDestroy")
    }
    class MyBinder : Binder(){

    }
}