package kr.Tcrush.TheBestTimer.Tool

import android.util.Log

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class Dlog {
    companion object{
        val TAG : String = "SecondProject"
        fun buildLogMsg (message : String) : String?{
            val ste : StackTraceElement = Thread.currentThread().stackTrace[4]
            val sb : StringBuilder = StringBuilder()
            sb.append("[")
                .append(ste.methodName)
                .append("()")
                .append("]")
                .append(" :: ")
                .append(message)
                .append(" (")
                .append(ste.fileName)
                .append(":")
                .append(ste.lineNumber)
                .append(")")
            return sb.toString()
        }
        /** Log Level Error  */
        fun e(message: String) {
            if (BaseApplication.DEBUG) Log.e(Dlog.TAG, Dlog.buildLogMsg(message))
        }

        /** Log Level Warning  */
        fun w(message: String) {
            if (BaseApplication.DEBUG) Log.w(Dlog.TAG, Dlog.buildLogMsg(message))
        }

        /** Log Level Information  */
        fun i(message: String) {
            if (BaseApplication.DEBUG) Log.i(Dlog.TAG, Dlog.buildLogMsg(message))
        }

        /** Log Level Debug  */
        fun d(message: String) {
            if (BaseApplication.DEBUG) Log.d(Dlog.TAG, Dlog.buildLogMsg(message))
        }

        /** Log Level Verbose  */
        fun v(message: String) {
            if (BaseApplication.DEBUG) Log.v(Dlog.TAG, Dlog.buildLogMsg(message))
        }
    }







}