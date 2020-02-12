package kr.Tcrush.TheBestTimer.Tool

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import androidx.multidex.MultiDexApplication
import java.io.PrintWriter
import java.io.StringWriter
import java.io.Writer

class BaseApplication : MultiDexApplication() {
    companion object{
        var DEBUG = false
    }


    var uncaughtExceptionHandler: Thread.UncaughtExceptionHandler? = null

    private var instance: Context? = null

    fun getInstance(): Context? {
        return instance
    }

    override fun onCreate() {
        uncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler()
        Thread.setDefaultUncaughtExceptionHandler(UncaughtExceptionHandlerApplication(uncaughtExceptionHandler))
        super.onCreate()
        if (instance == null) {
            instance = applicationContext
        }
        DEBUG = isDebuggable(this)
    }

    private fun isDebuggable(context: Context): Boolean {
        var debuggable = false
        val pm = context.packageManager
        try {
            val appInfo = pm.getApplicationInfo(context.packageName, 0)
            debuggable = 0 != appInfo.flags and ApplicationInfo.FLAG_DEBUGGABLE
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        return debuggable
    }

    private fun getStackTrace(th: Throwable): String? {
        val result: Writer = StringWriter()
        val printWriter = PrintWriter(result)
        var cause: Throwable? = th
        while (cause != null) {
            cause.printStackTrace(printWriter)
            cause = cause.cause
        }
        val stacktraceAsString = result.toString()
        printWriter.close()
        return stacktraceAsString
    }

    internal class UncaughtExceptionHandlerApplication(var uncaughtExceptionHandler: Thread.UncaughtExceptionHandler?) : Thread.UncaughtExceptionHandler {
        override fun uncaughtException(thread: Thread, ex: Throwable) {
            uncaughtExceptionHandler?.uncaughtException(thread, ex)
        }
    }
}