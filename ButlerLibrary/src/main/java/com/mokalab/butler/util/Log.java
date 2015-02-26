package com.mokalab.butler.util;
import android.content.Context;
import android.os.Build;
import android.widget.Toast;

import org.json.JSONException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

/**
 * @author Antonio Tari
 */
public final class Log{
    public static final String NULL_VALUE="__NULL__";
    public static final String EMPTY_VALUE="__EMPTY__";

    final static int WARN = 1;
    final static int INFO = 2;
    final static int DEBUG = 3;
    final static int VERB = 4;

    static int LOG_LEVEL;
    public static boolean DEBUG_MODE=true;

    public static final String tagPrefix="moka";
    public static final String tagDebug = tagPrefix +  "";
    public static final String tagHi =  tagPrefix +  ".Log.hipriority";
    public static final String tagHttp =  tagPrefix +  ".Log.http";
    public static final String tagError =  tagPrefix +  ".Log.error";

    public static final String SEPARATOR_DEFAULT=" *** ";

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    static
    {
        if ("google_sdk".equals(Build.PRODUCT) || "sdk".equals(Build.PRODUCT))
        {
            LOG_LEVEL = VERB;
        }
        else
        {
            LOG_LEVEL = INFO;
        }

    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void debug(Object... messages){
        d(tagDebug,messages);
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void hi(Object... messages){
        d(tagHi,messages);
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void http(Object... messages){
        d(tagHttp,messages);
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void error(Object... messages){
        e(tagError,messages);
    }

//	//---------------------------------------------------------------------------------------
//	//-----------------------------------
//	/**
//	 * this one also sends an error report to the server
//	 * @param context
//	 * @param messages
//	 */
//	public static void error(Context context,Object... messages)
//	{
//		e(tagError,messages);
//		ATErrorLog.getInstance().sendErrorLog(context, ATUtil.getInstance(context).getAppName(), buildString(messages));
//	}

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void c(Class tag,Object messages){
        w(tag.getCanonicalName(),messages);
    }

    /**
     *
     * @param e
     * @return
     */
    public static String stackTraceToString(Throwable e){
        if(e==null){
            return null;
        }

        String resultStr=null;
        try{
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            resultStr=sw.toString(); // stack trace as a string
            pw.flush();
            pw.close();
            sw.flush();
            sw.close();
        }catch(IOException ioe){}
        return resultStr;
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static String checkString(Object str){
        try{
            if(str==null)
            {
                return (NULL_VALUE);
            }
            else if(str instanceof Throwable){
                //String thStr = ((Throwable)str).getLocalizedMessage();
                return checkString(stackTraceToString((Throwable) str));
            }
            else if(str instanceof Exception)
            {
                StringBuilder sb=new StringBuilder("EXCEPTION ON CLASS: ");
                sb.append(str.getClass().getSimpleName());
                sb.append(", Exception ");
                if(str instanceof JSONException){
                    sb.append("JSONException");
                }
                else if(str instanceof IOException){
                    sb.append("IOException");
                }
                else if(str instanceof NullPointerException){
                    sb.append("NullPointerException");
                }
                else if(str instanceof FileNotFoundException){
                    sb.append("FileNotFoundException");
                }

                sb.append(" : ");
                sb.append(checkString(((Exception)str).getLocalizedMessage()));
                sb.append(SEPARATOR_DEFAULT);
                sb.append(stackTraceToString((Exception) str));

                return checkString(sb.toString());
//				return checkString("EXCEPTION ON CLASS: "+str.getClass().getSimpleName()+", exception:")+
//						checkString(((Exception)str).getLocalizedMessage());
            }
            else if(
                    ((str instanceof String) && (
                            ( (String)str).isEmpty() || ((String)str).equalsIgnoreCase(" ") )
                    )
                            ||
                            (( str.toString().isEmpty() || str.toString().equalsIgnoreCase(" ")))
                    )
            {
                return EMPTY_VALUE;
            }
            else
            {
                return str.toString();
            }
        } catch (Throwable d) {
            return "Cannot log: "+android.util.Log.getStackTraceString(d);
        }
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static String buildString(Object[] sa){
        return buildString(sa,SEPARATOR_DEFAULT);
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static String buildString(Object[] sa,String separator){
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<sa.length;i++)
        {
            sb.append(checkString(sa[i]));
            if(i<(sa.length-1))
                sb.append(separator);

            //Object theObj=sa[i];
            //if(theObj==null)
            //	theObj=ATUtil.NULL_VALUE;

            //sb.append((theObj instanceof Exception)?((Exception)theObj).getLocalizedMessage():theObj.toString()
            //		+ ((i==(sa.length-1))?"":"\n"));
        }
        String stackTrace = calcStackLine(Arrays.asList(Log.class.getName()));
        return stackTrace + "\t" + sb.toString();
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    static boolean showTag(int debugLevel, String tag){
		/*if(tag.equalsIgnoreCase(tags.TAG_DB))
		{
			return APP_DEBUG&&SHOW_TAG_DB;
		}

		if(tag.equalsIgnoreCase(tags.TAG_GET))
		{
			return APP_DEBUG&&SHOW_TAG_GET;
		}*/

        return debugLevel >= android.util.Log.INFO || isShowDebugMessages();
    }


    /**
     *Error
     */
    public static void e(String tag, Object... string){
        if(showTag(android.util.Log.ERROR, tag)){
            android.util.Log.e(tag, buildString(string));
        }
    }

    /**
     * Warn
     */
    public static void w(String tag, Object... string){
        if(showTag(android.util.Log.WARN, tag))
            android.util.Log.w(tag, buildString(string));
    }

    /**
     * Info
     */
    public static void i(String tag, Object... string){
        if(showTag(android.util.Log.INFO, tag)){
            if(LOG_LEVEL >= INFO){
                android.util.Log.i(tag, buildString(string));
            }
        }
    }

    /**
     * Debug
     */
    public static void d(String tag, Object... string){
        if(showTag(android.util.Log.DEBUG, tag)){
            //if(LOG_LEVEL >= DEBUG)
            {
                android.util.Log.d(tag, buildString(string));
            }
        }
    }

    /**
     * Verbose
     */
    public static void v(String tag, Object... string){
        if(showTag(android.util.Log.WARN, tag)){
            //if(LOG_LEVEL >= VERB)
            {
                android.util.Log.v(tag, buildString(string));
            }
        }
    }


    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void Toast(Context c,Object... message){
        if(isShowDebugMessages())
            Toast.makeText(c,buildString(message),Toast.LENGTH_LONG ).show();
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    public static void Toast(Context c,int stringId){
        if(isShowDebugMessages())
            Toast.makeText(c,c.getResources().getString(stringId),Toast.LENGTH_LONG ).show();
    }

    //---------------------------------------------------------------------------------------
    //-----------------------------------
    static boolean isShowDebugMessages(){
//		return !ATUtil.isReleaseSigned
//				//.getInstance().getIsReleaseSigned())
//				&& ATUtil.isDebug;
//				//.getInstance().getIsDebug();
        return DEBUG_MODE;
    }

    /**
     * @param ignoreClasses List of class names to ignore.
     */
    private static String calcStackLine(List<String> ignoreClasses) {

        StackTraceElement line = null;
        // Find the first line below an ignored class.
        for (StackTraceElement stack : new Throwable().getStackTrace()) {
            if (ignoreClasses.contains(stack.getClassName())) {
                line = null;
            } else if (line == null) {
                line = stack;
            }
        }
        if (line == null) {
            return "";
        }
        String desc = line.getClassName();
        int period = desc.lastIndexOf(".");
        if (period != -1) {
            desc = desc.substring(period+1);
        }
        desc += "."+line.getMethodName();
        int lineNumber = line.getLineNumber();
        if (lineNumber >= 0) {
            // Note: getLineNumber returns a negative number if the line number is unknown.
            desc += "("+lineNumber+")";
        }
        return desc;
    }
}