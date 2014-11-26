package com.mokalab.butler.serialization;

import android.os.Bundle;
import android.os.Parcel;

import com.mokalab.butler.util.Log;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

//import java.io.ByteArrayInputStream;
//import java.io.InputStream;
//import android.util.Base64;

public class SerialBundle 
{
	//it's a singleton, to be thread safe and avoid multiple writing on the same file
	private static SerialBundle _instance=null;
	
	protected SerialBundle(){
		
	}
	
	public static SerialBundle getInstance(){
		if(_instance==null){
			_instance=new SerialBundle();
		}
		return _instance;
	}
	
	/**
	 * serializes a bundle into a file
	 * @param bundle
	 * @param filePath
	 * @return
	 */
	public boolean serializeBundle(final Bundle bundle,final String filePath) 
	{	
		boolean returnBool=false;
		final Parcel parcel = Parcel.obtain();
		try {
			parcel.writeBundle(bundle);
			//final ByteArrayOutputStream bos = new ByteArrayOutputStream();
			FileOutputStream bos = new FileOutputStream(filePath);
			final GZIPOutputStream zos = new GZIPOutputStream(new BufferedOutputStream(bos));
			zos.write(parcel.marshall());
			zos.close();
			bos.close();
			//base64 = Base64.encodeToString(bos.toByteArray(), 0);
			returnBool = true;
		}
		catch(FileNotFoundException e){
			Log.error("serializeBundle, FileNotFoundException", e);
			//base64 = null;
		}
		catch(IOException e) {
			Log.error("serializeBundle, IOException",e);
			//base64 = null;
		} finally {
			parcel.recycle();
		}
		//return base64;
		return returnBool;
	}

	//-----------------------------------------------------------------------------
	/**
	 * deserializes a Bundle from a file
	 * @param filePath the complete path where to store the file
	 * @return
	 */
	public Bundle deserializeBundle(final String filePath) 
	{
		//if file doesn't exists return null
		File file = new File(filePath);
		if(!file.exists()) {
			return null;
		}

		Bundle bundle = null;
		final Parcel parcel = Parcel.obtain();
		try {
			final ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
			final byte[] buffer = new byte[1024];
			final GZIPInputStream zis = new GZIPInputStream(new FileInputStream(filePath));//new ByteArrayInputStream(Base64.decode(base64, 0)));
			int len = 0;
			while ((len = zis.read(buffer)) != -1) {
				byteBuffer.write(buffer, 0, len);
			}
			zis.close();
			parcel.unmarshall(byteBuffer.toByteArray(), 0, byteBuffer.size());
			parcel.setDataPosition(0);
			bundle = parcel.readBundle();
			byteBuffer.close();
		} 
		catch(FileNotFoundException e){
			Log.error("deserializeBundle, FileNotFoundException",e);
			bundle = null;
		}catch (IOException e) {
			e.printStackTrace();
			Log.error("deserializeBundle, IOException",e);
			bundle = null;
		}  finally {
			parcel.recycle();
		}
		return bundle;
	}
}