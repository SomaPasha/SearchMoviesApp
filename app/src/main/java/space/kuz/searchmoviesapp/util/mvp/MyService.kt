package space.kuz.searchmoviesapp.util.mvp

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import android.widget.Toast
import space.kuz.searchmoviesapp.iu.main.EVENT
import space.kuz.searchmoviesapp.iu.main.MainActivity
import java.io.*
import java.lang.StringBuilder
import java.security.Provider
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import android.R

import android.os.Environment
import androidx.core.content.PackageManagerCompat
import java.lang.Exception
import androidx.core.content.PackageManagerCompat.LOG_TAG
import androidx.core.content.PackageManagerCompat.LOG_TAG
import android.system.Os.socket











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
        writeFile("redic.txt","OnStartCommand ${intent?.extras?.getString(EVENT)} ${time} \n" ,MODE_APPEND)
        readFile("redic.txt")
        //Log.d(TAG,  readFile("redic.txt").toString() )
        return super.onStartCommand(intent, flags, startId)
    }
    fun writeFile(FileName:String, text:String, mode: Int) {
        try {

            // отрываем поток для записи
            val bw = BufferedWriter(
                OutputStreamWriter(
                    openFileOutput(FileName,mode)
                )
            )
            // пишем данные
            bw.write(text)
            // закрываем поток
            bw.close()
        //    Log.d("@@@", "Файл записан")

        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
    fun readFile(FILENAME:String) {
        try {
            // открываем поток для чтения
            val br = BufferedReader(
                InputStreamReader(
                    openFileInput(FILENAME)
                )
            )
            var str: String? = ""
            // читаем содержимое
            while (br.readLine().also { str = it } != null) {
                Log.d("@@@@", str!!)
            }
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "OnUnBind")
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        writeFile("redic.txt","nulll" , MODE_PRIVATE)
        super.onDestroy()
        Log.d(TAG, "OnDestroy")
    }
    class MyBinder : Binder(){

    }
}