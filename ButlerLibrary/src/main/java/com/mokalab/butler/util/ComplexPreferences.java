package com.mokalab.butler.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

public class ComplexPreferences //extends br.com.kots.mob.complex.preferences.ComplexPreferences
{
	private final static String KEY_BUNDLE = "com.antoniotari.android.util.keys.keybundle";
	private final static String KEY_SERIALIZABLE = "com.antoniotari.android.util.keys.keyserializable";
	private final static String KEY_JSONOBJECT = "com.antoniotari.android.util.keys.keyjsonobject";
	private final static String KEY_JSONARRAY = "com.antoniotari.android.util.keys.keyjsonarray";

	protected static ComplexPreferences complexPreferences;
	//protected Context context;
	protected WeakReference<SharedPreferences> preferencesW;
	protected WeakReference<SharedPreferences.Editor> editorW;
	protected static Gson GSON = new Gson();
	Type typeOfObject = new TypeToken<Object>() {}.getType();

	//-----------------------------------------------------------------------------
	protected ComplexPreferences()
	{
	}

	//-----------------------------------------------------------------------------
	protected ComplexPreferences(Context context, String namePreferences, int mode) 
	{
		//this.context = context;
		if (namePreferences == null || namePreferences.equals("")) 
			namePreferences = "complex_preferences";
		preferencesW = new WeakReference<SharedPreferences>(context.getSharedPreferences(namePreferences, mode));
		editorW = new WeakReference<SharedPreferences.Editor>(context.getSharedPreferences(namePreferences, mode).edit());
	}

	//-----------------------------------------------------------------------------
	public static ComplexPreferences getComplexPreferences(Context context,String namePreferences, int mode) 
	{
		if (complexPreferences == null) 
			complexPreferences = new ComplexPreferences(context,namePreferences, mode);
		return complexPreferences;
	}

	//-----------------------------------------------------------------------------
	public void putObject(Context context,String key, Object object) 
	{
		if(object == null){
			throw new IllegalArgumentException("complexPreferences, putObject, object is null");
		}

		if(key.equals("") || key == null){
			throw new IllegalArgumentException("complexPreferences, putObject, key is empty or null");
		}

		if (object instanceof Bundle){
			putBundle(context,key,(Bundle)object);
		}
		else if(object instanceof Serializable){
			putSerializable(context,key,(Serializable)object);
		}
		else if(object instanceof JSONObject || object instanceof JSONArray){
			putJson(context,key,object);
		}
		else
		{
			SharedPreferences.Editor editor=editorW.get();
			if(editor!=null){
				editor.putString(key, GSON.toJson(object));
			}
		}
	}

	//-----------------------------------------------------------------------------
	public void commit() 
	{
		SharedPreferences.Editor editor=editorW.get();
		if(editor!=null){
			editor.commit();
		}
	}

	//-----------------------------------------------------------------------------
	public <T> T getObject(String key, Class<T> a) 
	{
		SharedPreferences preferences=preferencesW.get();
		if(preferences!=null)
		{
			String gson = preferences.getString(key, null);
			if (gson == null) 
			{
				return null;
			} 
			else
			{
				try{
					return GSON.fromJson(gson, a);
				} catch (Exception e) {
					throw new IllegalArgumentException("Object storaged with key " + key + " is instanceof other class");				
				}
			}
		}
		return null;
	}

	//-----------------------------------------------------------------------------
	/**
	 * save a {@link}JSONObject into SharedPreferences
	 * @param key		ComplexPreferences key
	 * @param object	object to save
	 */
	public static void putJson(Context context,String key, Object object)
	{
		if(object==null){
			return;
		}
		
//		SharedPreferences.Editor editor=editorW.get();
//		if(editor==null){
//			return;
//		}
		
		//if the stored value is the same as the value we want to put, do nothing
		try{
			JSONObject jo=(JSONObject)getJson(context,key);	
			if(jo!=null){
				String oldValue=MD5.md5(jo.toString());
				String newValue=MD5.md5(((JSONObject)object).toString());
				if(oldValue.equalsIgnoreCase(newValue)){
					return;
				}
			}
			//if(jo!=null && (jo.toString().equalsIgnoreCase(((JSONObject)object).toString())))
			//	return;
		}catch(Exception d){
		}
		
		try{
			FileUtil.getInstance().writeStringFile(context, key, object.toString());
		}catch(FileNotFoundException e){
			Log.error("ComplexPreferences.putJson FileNotFoundException:",e);
		}catch(IOException e){
			Log.error("ComplexPreferences.putJson IOException:",e);
		}
//		Set<String> objToPut=new HashSet<String>();
//		if(object instanceof JSONObject)
//		{
//			objToPut.add(KEY_JSONOBJECT);
//			objToPut.add(((JSONObject)object).toString());
//		}
//		else if(object instanceof JSONArray)
//		{
//			objToPut.add(KEY_JSONARRAY);
//			objToPut.add(((JSONArray)object).toString());
//		}
//		else
//			return;
//		
//		editor.putStringSet(key,objToPut);
	}

	//-----------------------------------------------------------------------------
	public static Object getJson(Context context,String key)
	{
		String jsonStr=FileUtil.getInstance().readStringFile(context, key);
		if(jsonStr==null){
			return null;
		}
		
		try{
			if(jsonStr.startsWith("[")){
				return new JSONArray(jsonStr);
			}
			else{
				return new JSONObject(jsonStr);
			}
		}catch(JSONException e){
			Log.error("ComplexPreferences.getJson JSONException:",e);
		}
		
		return null;
		
//		SharedPreferences preferences=preferencesW.get();
//		if(preferences==null){
//			return null;
//		}
//		
//		try{
//			Set<String> set= preferences.getStringSet(key, null);
//			if(set.contains(KEY_JSONOBJECT))
//			{
//				for (String s : set) 
//				{
//					if(!s.equalsIgnoreCase(KEY_JSONOBJECT))
//						return new JSONObject(s);
//				}
//			}
//			else if(set.contains(KEY_JSONARRAY))
//			{
//				for (String s : set) 
//				{
//					if(!s.equalsIgnoreCase(KEY_JSONARRAY))
//						return new JSONArray(s);
//				}
//			}
//		}catch(JSONException e){}
//		catch(Exception e){}
//		return null;
//		//throw new IllegalArgumentException("object is not a json or is null");
	}

	//-----------------------------------------------------------------------------
	public Object getJson(Context context,String key,Object defaultValue)
	{
		Object obj=getJson(context,key);
		if(obj==null){
			return defaultValue;
		}
		return obj;
		
//		SharedPreferences preferences=preferencesW.get();
//		if(preferences==null){
//			return defaultValue;
//		}
//		
//		try{
//			Set<String> set= preferences.getStringSet(key, null);
//			if(set.contains(KEY_JSONOBJECT))
//			{
//				for (String s : set) 
//				{
//					if(!s.equalsIgnoreCase(KEY_JSONOBJECT))
//						return new JSONObject(s);
//				}
//			}
//			else if(set.contains(KEY_JSONARRAY))
//			{
//				for (String s : set) 
//				{
//					if(!s.equalsIgnoreCase(KEY_JSONARRAY))
//						return new JSONArray(s);
//				}
//			}
//		}catch(JSONException e){}
//		catch(Exception e){}
//		return defaultValue;
//		//throw new IllegalArgumentException("object is not a json or is null");
	}

	//-----------------------------------------------------------------------------
	public static void putSerializable(Context context,String key, Serializable object)
	{
		if(context==null || key==null || object==null){
			return;
		}
		FileUtil.getInstance().storeSerializable(context, key, object);
//		Bundle bu=new Bundle();
//		bu.putSerializable(KEY_SERIALIZABLE,(Serializable)object);
//		putBundle(key, bu);
	}

	//-----------------------------------------------------------------------------
	public static Object getSerializable(Context context,String key)
	{
		if(context==null || key==null){
			return null;
		}
		return FileUtil.getInstance().readSerializable(context, key);
//		Bundle bu=getBundle(key);
//		return bu.getSerializable(KEY_SERIALIZABLE);
	}

	//-----------------------------------------------------------------------------
	public static void putBundle(Context context,String key, Bundle object) 
	{
		if(object == null){
			throw new IllegalArgumentException("object is null");
		}
		
//		SharedPreferences.Editor editor=editorW.get();
//		if(editor==null){
//			return;
//		}

		if(key.equals("") || key == null){
			throw new IllegalArgumentException("key is empty or null");
		}
		
//		try{
//			//FileUtil.getInstance().writeStringFile(context, key, SerialBundle.serializeBundle(object));
		FileUtil.getInstance().storeBundle(context, key, object);
			//SerialBundle.serializeBundle(object,FileUtil.extStorageDir(context)+key);
//		}catch(FileNotFoundException e){
//			Log.error("ComplexPreferences.putBundle FileNotFoundException:",e);
//		}catch(IOException e){
//			Log.error("ComplexPreferences.putBundle IOException:",e);
//		}
		
//		//TODO check if the object is already present
//		Set<String> objToPut=new HashSet<String>();
//		if (object instanceof Bundle)
//		{
//			objToPut.add(KEY_BUNDLE);
//			objToPut.add(SerialBundle.serializeBundle((Bundle)object));
//		}
//		editor.putStringSet(key,objToPut);
	}
	
	
	//-----------------------------------------------------------------------------
	public static Bundle getBundle(Context context,String key)
	{
		Bundle returnBundle=null;
//		String str=FileUtil.getInstance().readStringFile(context, key);
//		if(str==null){
//			Log.error("getBundle,str is null");
//			returnBundle = new Bundle();
//		}
//		else{
			returnBundle = FileUtil.getInstance().readBundle(context, key);//SerialBundle.deserializeBundle(FileUtil.extStorageDir(context)+key);
			if(returnBundle==null){
				Log.error("getBundle,returnBundle is null");
				returnBundle = new Bundle();
			}
//		}
		return returnBundle;
//		SharedPreferences preferences=preferencesW.get();
//		if(preferences==null){
//			return new Bundle();
//		}
//		
//		Set<String> set= preferences.getStringSet(key, null);
//		if(set==null)return new Bundle();
//		if(set.contains(KEY_BUNDLE))
//		{
//			for (String s : set) 
//			{
//				if(!s.equalsIgnoreCase(KEY_BUNDLE))
//					return SerialBundle.deserializeBundle(s);
//			}
//		}
//		throw new IllegalArgumentException("object is not a bundle or is null");
	}

	//-----------------------------------------------------------------------------
	
}
