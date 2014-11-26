package com.mokalab.butler.util;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo.State;
import android.os.Build;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.util.Patterns;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.regex.Pattern;


public class PhoneInfoUtil 
{
	//save keys
	private static final String TAG= "com.antoniotari.atutil";
	private static final String KEY_USEREMAIL=TAG+".save.useremail";
	private static final String KEY_EMAILARRAY=TAG+".save.emailArray";
	private static final String KEY_UNIQUEID=TAG+".save.uniqueId";
	
	//variables
	private static  String _userEmail=null;
	private static  TreeSet<String> _emailArray=null;
	private static  String _uniqueId=null;
	
	private final static String DEFAULT_USEREMAIL = "default@email.ca";

	private static Map<String,String> _phoneInfo=new HashMap<String,String>();
	
	public static enum WhatConnected
	{
		WIFI,
		CELLULAR,
		NONE
	}

	//---------------------------------------------------------------------------------
	public static WhatConnected getWhatConnected(Context context)
	{
		ConnectivityManager conMan = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

		//mobile
		State mobile;
		if(conMan!=null && conMan.getNetworkInfo(0)!=null && conMan.getNetworkInfo(0).getState()!=null)
			mobile = conMan.getNetworkInfo(0).getState();
		else 
			mobile= State.DISCONNECTED;

		State wifi;
		if(conMan!=null && conMan.getNetworkInfo(0)!=null && conMan.getNetworkInfo(1).getState()!=null)
			wifi = conMan.getNetworkInfo(1).getState();
		else 
			wifi= State.DISCONNECTED;
		
		//wifi
		//State wifi = conMan.getNetworkInfo(1).getState();

		if (mobile == State.CONNECTED || mobile == State.CONNECTING)
		{
		    return WhatConnected.CELLULAR;
		}
		else if (wifi == State.CONNECTED || wifi == State.CONNECTING)
		{
			 return WhatConnected.WIFI;
		}
		else
		{
			 return WhatConnected.NONE;
		}
	}
	
	//---------------------------------------------------------------------------------
	/**
	 * get the user phone number
	 * important! set permissions:
	 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * <uses-permission android:name="android.permission.GET_ACCOUNTS" />
	 * @param context
	 * @return
	 */
	public static String getMyPhoneNumber(Context context)
	{
		TelephonyManager mTelephonyMgr;
		mTelephonyMgr = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE); 
		return mTelephonyMgr.getLine1Number();
	}

	//---------------------------------------------------------------------------------
	public static  String getMy10DigitPhoneNumber(Context context)
	{
		String s = getMyPhoneNumber(context);
		return s.substring(2);
	}
	
	//-------------------------------------------------------------------
	//---------------------------------------------------------------------------------
	/**
	 * get the user email
	 * important! set permissions:
	 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * <uses-permission android:name="android.permission.GET_ACCOUNTS" />
     */ 
	public static String userEmail(Context context)
	{
		if(_userEmail!=null)
			return _userEmail;
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String emailS = preferences.getString(KEY_USEREMAIL,null);
		if(emailS!=null)
		{
			_userEmail=emailS;
			return emailS;
		}	
		
		try
		{
			Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
			Account[] accounts = AccountManager.get(context).getAccounts();
			for (Account account : accounts) 
			{
				if (emailPattern.matcher(account.name).matches()) 
				{
					if(account.type.equalsIgnoreCase("com.google"))
					{
						_userEmail=account.name;
						
						SharedPreferences.Editor editor = preferences.edit();
						editor.putString(KEY_USEREMAIL,account.name);
						editor.commit();
						
						return account.name;
					}
				}
			}

			try{
				return emailArray(context).first();// .getString(0);
			}catch(Exception d){
				return DEFAULT_USEREMAIL;
			}
		}
		catch(Exception e){}
		return DEFAULT_USEREMAIL;
	}
	
	//-------------------------------------------------------------------
	public static TreeSet<String> emailArray(Context context)
	{
		if(_emailArray!=null)
			return _emailArray;
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		Set<String> emailSet = preferences.getStringSet(KEY_EMAILARRAY, null);// .getString(KEY_EMAILARRAY,null);
		
		TreeSet<String> emailS;
		if(emailSet==null)
		{
			emailS= emailArray(context,GetUserEmails(context));
		}
		else
			emailS=new TreeSet<String>(emailSet);
		
		_emailArray=emailS;
		return emailS;		
	}

	//-------------------------------------------------------------------
	public static TreeSet<String> emailArray(Context context,Set<String> newValue)
	{
		if(newValue==null)return null;

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putStringSet(KEY_EMAILARRAY,newValue);
		editor.commit();
		return new TreeSet<String>(newValue);
	}

	//-------------------------------------------------------------------
	/**
	 * get the user emails
	 * important! set permissions:
	 * <uses-permission android:name="android.permission.READ_PHONE_STATE" />
     * <uses-permission android:name="android.permission.GET_ACCOUNTS" />
     */ 
	public static Set<String> GetUserEmails(Context context)
	{
		Set<String> emailArray2=new TreeSet<String>();
		try
		{
			Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
			Account[] accounts = AccountManager.get(context).getAccounts();
			for (Account account : accounts) 
			{
				if (emailPattern.matcher(account.name).matches()) 
				{
					//resultS=account.name+", "+resultS;
					//Log.d(tags.TAG_DEBUG,account.name+" ---- "+account.type+" ---- "+account.toString());
					emailArray2.add(account.name);
				}
			}
			//return emailArray2;
		}
		catch(Exception e){}
		
		return emailArray2;
	}

	//-------------------------------------------------------------------
	//-------------------------------------------------------------------
	public static String deviceId(Context context)
	{
		//final TelephonyManager tm = (TelephonyManager) MyApp.getInstance().getSystemService(Context.TELEPHONY_SERVICE);

		//final String tmDevice, tmSerial, androidId;
		//String tmDevice = "" + tm.getDeviceId();
		//tmSerial = "" + tm.getSimSerialNumber();
		String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		//UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		//String deviceId = deviceUuid.toString();
		return androidId;
	}

	//-------------------------------------------------------------------
	public static String deviceType(Context context)
	{
		//final TelephonyManager tm = (TelephonyManager) MyApp.getInstance().getSystemService(Context.TELEPHONY_SERVICE);

		//final String tmDevice, tmSerial, androidId;
		//String tmDevice = "" + tm.getDeviceId();
		//tmSerial = "" + tm.getSimSerialNumber();
		String androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		//UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		//String deviceId = deviceUuid.toString();
		return androidId;
	}
	
	//-------------------------------------------------------------------
	public static String deviceManufacture()//Context context)
	{
		//TelephonyManager manager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String device = android.os.Build.MANUFACTURER;
		if (device == null || device.equals("unknown")) {
			device = "Simulator";
		}
		return device;
	}

	//-------------------------------------------------------------------
	public static String deviceSystemVersion()//Context context)
	{
		return android.os.Build.VERSION.RELEASE;
	}

	//-------------------------------------------------------------------
	public static String deviceModel()//Context context)
	{
		return android.os.Build.MODEL;
	}

	//-------------------------------------------------------------------
	public static String getUniqueId(Context context)
	{
		final TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);

		final String tmDevice, tmSerial, androidId;
		tmDevice = "" + tm.getDeviceId();
		tmSerial = "" + tm.getSimSerialNumber();
		androidId = "" + android.provider.Settings.Secure.getString(context.getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);

		UUID deviceUuid = new UUID(androidId.hashCode(), ((long)tmDevice.hashCode() << 32) | tmSerial.hashCode());
		return deviceUuid.toString();
	}

	//-------------------------------------------------------------------
	public static String uniqueId(Context context)
	{
		if(_uniqueId!=null)
			return _uniqueId;
		
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		String uniqId = preferences.getString(KEY_UNIQUEID,null);
		if(uniqId==null)
		{
			uniqId=getUniqueId(context);
			uniqueId(context,uniqId);
		}
		else
			_uniqueId=uniqId;
		
		return uniqId;
	}

	//-------------------------------------------------------------------
	private static void uniqueId(Context context,String newValue)
	{
		if(newValue==null)return;
		
		_uniqueId=newValue;

		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
		SharedPreferences.Editor editor = preferences.edit();
		editor.putString(KEY_UNIQUEID,newValue.toString());
		editor.commit();
	}

	//-------------------------------------------------------------------
	public static String getPhoneInfo(Context context)
	{
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		putPhoneInfo("deviceId",tm.getDeviceId());
		putPhoneInfo("simSerialNumber",tm.getSimSerialNumber());
		putPhoneInfo("androidId",deviceId(context));
		putPhoneInfo("DeviceSoftwareVersion",tm.getDeviceSoftwareVersion());
		//putPhoneInfo("GroupIdLevel1",tm.getGroupIdLevel1());
		putPhoneInfo("phoneNumber",tm.getLine1Number());
		//putPhoneInfo("MmsUAProfUrl",tm.getMmsUAProfUrl());
		//putPhoneInfo("MmsUserAgent",tm.getMmsUserAgent());
		putPhoneInfo("NetworkCountryIso",tm.getNetworkCountryIso());
		putPhoneInfo("NetworkOperator",tm.getNetworkOperator());
		putPhoneInfo("NetworkOperatorName",tm.getNetworkOperatorName());
		putPhoneInfo("SimCountryIso",tm.getSimCountryIso());
		putPhoneInfo("SimOperator",tm.getSimOperator());
		putPhoneInfo("SimOperatorName",tm.getSimOperatorName());
		putPhoneInfo("SubscriberId",tm.getSubscriberId());
		putPhoneInfo("VoiceMailAlphaTag",tm.getVoiceMailAlphaTag());
		putPhoneInfo("VoiceMailNumber",tm.getVoiceMailNumber());
		putPhoneInfo("isNetworkRoaming",tm.isNetworkRoaming()?"true":"false");

		return _phoneInfo.toString();
	}

	//-------------------------------------------------------------------
	private static void putPhoneInfo(String key,String value)
	{
		if(value==null)
			return;
		if(value.equalsIgnoreCase(""))
			return;

		if (_phoneInfo==null)
			_phoneInfo=new HashMap<String,String>();

		try{
			_phoneInfo.put(key, value);
		}
		catch(Exception d){}
	}

	//-------------------------------------------------------------------
	public static String userNumber(Context context)
	{
		if(_phoneInfo==null || _phoneInfo.get("phoneNumber")==null)
			return getUserNumber(context);

		return _phoneInfo.get("phoneNumber");
	}

	//-------------------------------------------------------------------
	private static String getUserNumber(Context context)
	{
		final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return tm.getLine1Number();
	}
	
	//-------------------------------------------------------------------
    //-------------------------------------------------------------------
    public static boolean isAndroidEmulator(Context context)
    {
        //String model = Build.MODEL;
        //Log.d(tags.TAG_DEBUG, "model=" + model);
        String product = Build.PRODUCT;
        //Log.d(tags.TAG_DEBUG, "product=" + product);
        boolean isEmulator = false;
        if (product != null)
        {
            isEmulator = product.equals("sdk") || product.contains("_sdk") || product.contains("sdk_");
        }
        //Log.d(tags.TAG_DEBUG, "isEmulator=" + isEmulator);
        return isEmulator;
    }
	
	//-------------------------------------------------------------------
	
	/**usage
	 * 	public void onCreate Bundle b ) 
	 * 	{
   			super.onCreate(savedInstanceState);
   			if ( signedWithDebugKey(this,this.getClass()) ) 
   			{ }
		}
	 */
	
	//public static boolean signedWithDebugKey(Context context, Class<?> cls) 
	//{
	 //   boolean result = false;
	//    try 
	//    {
	//        ComponentName comp = new ComponentName(context, cls);
	//        PackageInfo pinfo = context.getPackageManager().getPackageInfo(comp.getPackageName(),PackageManager.GET_SIGNATURES);
	//        Signature sigs[] = pinfo.signatures;
	//        //for ( int i = 0; i < sigs.length;i++)
	//        //Log.d(tags.TAG_DEBUG,Global.ConvertRawToString(R.raw.debug_key)+"\n"+sigs[0].toCharsString());
	//        if (Global.ConvertRawToString(R.raw.debug_key).equals(sigs[0].toCharsString())) 
	//        {
	//            result = true;
	//            //Log.d(tags.TAG_DEBUG,"package has been signed with the debug key");
	//        } 
	//        //else 
	//        //    Log.d(tags.TAG_DEBUG,"package signed with a key other than the debug key");
	//
	//    } catch (android.content.pm.PackageManager.NameNotFoundException e) 
	//    {
	//        return false;
	//    }
	//    return result;
	//} 
	
	//-------------------------------------------------------------------
}
