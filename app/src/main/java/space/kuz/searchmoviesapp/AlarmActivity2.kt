package space.kuz.searchmoviesapp

import android.media.Ringtone
import android.media.RingtoneManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class AlarmActivity2 : AppCompatActivity() {
    var ringtoneManager: Ringtone?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_2)
        var notification  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtoneManager = RingtoneManager.getRingtone(this , notification)
        if(ringtoneManager == null){
            notification  = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
            ringtoneManager = RingtoneManager.getRingtone(this, notification)
        }
        ringtoneManager!!.play()
    }
    override fun onDestroy() {
        ringtoneManager!!.stop()
        super.onDestroy()
    }
}