package com.mokalab.butler.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

//import com.antoniotari.global.Log;
//import com.antoniotari.global.tags;
//import com.quickblox.content.helper.DataHolder;

public class ATImageStore 
{
	private static ATImageStore instance=null;

	private ATImageStore()
	{

	}

	public static ATImageStore getInstance()
	{
		if(instance==null)
			instance=new ATImageStore();
		return instance;
	}

	//-----------------------------------------------------------------------------
	//-----------------
	public synchronized void StoreImage(Context context,Bitmap image) 
	{
		File pictureFile = getOutputMediaFile(context);
		saveFile(pictureFile,image);
	}

	//-----------------------------------------------------------------------------
	//-----------------
	public synchronized void StoreImage(Context context,Bitmap image,String name) 
	{
		File pictureFile = getOutputMediaFile(context,name);
		saveFile(pictureFile,image);
	}

	//-----------------------------------------------------------------------------
	//-----------------
	/**
	 * 
	 * @param context
	 * @param image
	 * @param name
	 * @param maxSize		the max size in pixel of the image
	 */
	public void StoreImage(Context context,Bitmap image,String name,int maxSize) 
	{
		Bitmap img=ATImage.reduceBitmap(image, maxSize);
		Log.debug("size before:"+image.getWidth()+" "+image.getHeight(),"size after:"+img.getWidth()+" "+img.getHeight());
		StoreImage(context, img,name);
	}

	//-----------------------------------------------------------------------------
	//-----------------
	public static Bitmap LoadImage(Context context,String name)
	{
		try{
            String storageDir=StorageDir(context);
            if(storageDir==null) {
                return null;
            }

            String filePath=storageDir+name+".jpg";

            if(!(new File(filePath).isFile())){
                return null;
            }

			Bitmap selectedImage =  BitmapFactory.decodeFile(filePath);
			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			selectedImage.compress(Bitmap.CompressFormat.JPEG, 90, stream);
            stream.close();
			return selectedImage;
		}
		catch(Exception d){
			return null;
		}
	}

	//-----------------------------------------------------------------------------
	//-----------------
	private static void saveFile(File pictureFile,Bitmap image)
	{
		if (pictureFile == null || image==null) 
		{
			//Log.d(tags.TAG_DEBUG,"Error creating media file, check storage permissions: ");// e.getMessage());
			return;
		} 
		try {
			FileOutputStream fos = new FileOutputStream(pictureFile);
			image.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.close();
		} catch (FileNotFoundException e) {
			//Log.d(tags.TAG_DEBUG, "File not found: " + e.getMessage());
		} catch (IOException e) {
			//Log.d(tags.TAG_DEBUG, "Error accessing file: " + e.getMessage());
		} catch (Exception e){}
	}


	//-----------------------------------------------------------------------------
	//----------------- Create a File for saving an image or video 
	private static File getOutputMediaFile(Context context)
	{
		// Create a media file name
		String timeStamp = new SimpleDateFormat("ddMMyyyy_HHmm",Locale.getDefault()).format(new Date());
		String mImageName="MI_"+ timeStamp;
		return getOutputMediaFile(context,mImageName);
	} 

	//-----------------------------------------------------------------------------
	//----------------- Create a File for saving an image or video 
	public static File getOutputMediaFile(Context context,String mImageName)
	{
		String dir=StorageDir(context);
		if(dir==null)return null;
		// Create a media file name
		File mediaFile;
		mediaFile = new File(dir + mImageName+".jpg");  
		return mediaFile;
	} 

	//-----------------------------------------------------------------------------
	//-----------------
	private static String StorageDir(Context context)
	{
        return FileUtil.storageDir(context);
//		// To be safe, you should check that the SDCard is mounted
//		// using Environment.getExternalStorageState() before doing this. 
//		File mediaStorageDir = new File(Environment.getExternalStorageDirectory()
//				+ "/Android/data/"
//				//+ getApplicationContext().getPackageName()
//				+ context.getPackageName()
//				+ "/Files"); 
//
//		// This location works best if you want the created images to be shared
//		// between applications and persist after your app has been uninstalled.
//
//		// Create the storage directory if it does not exist
//		if (! mediaStorageDir.exists()){
//			if (! mediaStorageDir.mkdirs()){
//				return null;
//			}
//		} 
//
//		return mediaStorageDir.getPath() + File.separator;
	}
}