package com.mokalab.butler.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;


public class ATImage
{
	//-----------------------------------------------------------------------------
	public static Drawable DrawableFromAsset(Context c,String imageName)
	{
		try{
			return Drawable.createFromStream(c.getAssets().open(imageName), null);
		}catch(Exception d)
		{return null;}
	}

	//-----------------------------------------------------------------------------
	public static Bitmap BitmapFromAsset(Context c,String imageName)
	{
		try{
			return drawableToBitmap(Drawable.createFromStream(c.getAssets().open(imageName), null));
		}catch(Exception d)
		{return null;}
	}

	//-----------------------------------------------------------------------------
	public static Bitmap drawableToBitmap (Drawable drawable)
	{
		if (drawable instanceof BitmapDrawable) {
			return ((BitmapDrawable)drawable).getBitmap();
		}

		int width = drawable.getIntrinsicWidth();
		width = width > 0 ? width : 1;
		int height = drawable.getIntrinsicHeight();
		height = height > 0 ? height : 1;

		Bitmap bitmap = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap); 
		drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
		drawable.draw(canvas);

		return bitmap;
	}

	//-----------------------------------------------------------------------------
	public static Bitmap ResizeBitmap(Bitmap bm, int newHeight, int newWidth) 
	{
		int width = bm.getWidth();
		int height = bm.getHeight();
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// CREATE A MATRIX FOR THE MANIPULATION
		Matrix matrix = new Matrix();
		// RESIZE THE BIT MAP
		matrix.postScale(scaleWidth, scaleHeight);

		// "RECREATE" THE NEW BITMAP
		Bitmap resizedBitmap = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
		return resizedBitmap;
	}

	//-----------------------------------------------------------------------------
	public static Bitmap getBitmapFromURL(String src)
	{
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			Log.error("getBitmapFromURL",e);
		}catch (Exception e) {
			e.printStackTrace();
			Log.error("getBitmapFromURL",e);
		}
		return null;
	}

	//-----------------------------------------------------------------------------
	public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,int reqWidth, int reqHeight) 
	{

		// First decode with inJustDecodeBounds=true to check dimensions
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeResource(res, resId, options);

		// Calculate inSampleSize
		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

		// Decode bitmap with inSampleSize set
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeResource(res, resId, options);
	}


	//-----------------------------------------------------------------------------
	public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight)
	{
		// Raw height and width of image
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) 
		{
			// Calculate ratios of height and width to requested height and width
			final int heightRatio = Math.round((float) height / (float) reqHeight);
			final int widthRatio = Math.round((float) width / (float) reqWidth);

			// Choose the smallest ratio as inSampleSize value, this will guarantee
			// a final image with both dimensions larger than or equal to the
			// requested height and width.
			inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
		}

		return inSampleSize;
	}

	//-----------------------------------------------------------------------------
	/**
	 * 
	 * @param sourceB			the source bitmap @Bitmap
	 * @param size				the size to reduce the bitmap to
	 * @param isTheMaxSize		true: the passed size the max size, false: the passed size the min size
	 * @return					the scaled bitmap
	 */
	public static Bitmap scaleBitmap(Bitmap sourceB,final int size,final boolean isTheMaxSize)
	{
		int w=0,h=0;

		float reducedRatio= (float)sourceB.getWidth() /  (float)size;
		w=size;
		h= Math.round((float) sourceB.getHeight()/reducedRatio);

		if(isTheMaxSize)
		{		
			if(h>size)
			{
				reducedRatio= (float)sourceB.getHeight() /  (float)size;
				h=size;
				w=Math.round((float)sourceB.getWidth()/reducedRatio);
			}
		}
		else
		{
			if(h<size)
			{
				reducedRatio= (float)sourceB.getHeight() / (float)size;
				h=size;
				w=Math.round((float)sourceB.getWidth()/reducedRatio);
			}
		}
		try{
			return Bitmap.createScaledBitmap(sourceB, w, h, true);
		}catch(Exception d){
			return sourceB;
		}
	}
	
	/**
	 * if the bitmap is too big it reduces it to the specified size
	 * @param sourceB		the source bitmap @Bitmap
	 * @param size			the size of the image in kbyte we want to obtain (1px=1kb)
	 * @return
	 */
	public static Bitmap reduceBitmap(Bitmap sourceB,final int maxSize)
	{
		long actualSize=(long)sourceB.getWidth()*sourceB.getHeight();
		Log.debug("actual size "+actualSize);
		if(actualSize<=maxSize)
			return sourceB;	
		int reducedSideSize=(int) Math.round(Math.sqrt((double)(actualSize-maxSize)));
		Log.debug("reducedSideSize "+reducedSideSize);
		
		return scaleBitmap(sourceB,reducedSideSize,true);
	}

	//-----------------------------------------------------------------------------
	public static Bitmap transparentBitmap(int w,int h)
	{
		Bitmap bp= Bitmap.createBitmap(w, h, Config.ARGB_8888);
		bp.eraseColor(android.graphics.Color.TRANSPARENT);
		return bp;
	}

	//-----------------------------------------------------------------------------
	public static Bitmap createColorBitmap(int w,int h, int color)
	{
		Bitmap bitmap = Bitmap.createBitmap(w, h, Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		Paint paint = new Paint();
		paint.setColor(color); 
		paint.setStyle(Paint.Style.FILL);       
		canvas.drawPaint(paint);
		return bitmap;
	}
	
	//-----------------------------------------------------------------------------
	public static Bitmap toGrayscale(Bitmap bmpOriginal) {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
}
