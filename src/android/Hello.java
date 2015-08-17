package com.example.plugin;
import android.content.Context;
//import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.view.Gravity;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

 public static void TriggerScan(Context context)
    {
		//android.widget.Toast toast = android.widget.Toast.makeText(webView.getContext(), message, 0);
		//toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
		//toast.setDuration(android.widget.Toast.LENGTH_LONG);
        List<String> mMounts = new ArrayList<String>(10);
        File mountFile = new File("/proc/mounts");
        if(mountFile.exists()) {

            try {

                Scanner scanner = new Scanner(mountFile);
                while (scanner.hasNext()) {
                    String line = scanner.nextLine();
                    //if (line.startsWith("/dev/block/vold/")) {
                    if (line.startsWith("/dev/")) {
                        String[] lineElements = line.split(" ");
                        String element = lineElements[1];

                        // don't add the default mount path
                        // it's already in the list.
                        if (!element.equals("/mnt/sdcard"))
                            mMounts.add(element);
                    }
                }
                //Toast.makeText(context, mMounts.toString(), Toast.LENGTH_LONG).show();
				android.widget.Toast toast = android.widget.Toast.makeText(context, mMounts.toString(), 0);
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
				toast.setDuration(android.widget.Toast.LENGTH_LONG);
				//toast.show();
            }
            catch (Exception e)
            {
                e.printStackTrace();
                
				//Toast.makeText(context, "Proc:"+e.toString(),android.widget.Toast.LENGTH_SHORT).show();
				android.widget.Toast toast = android.widget.Toast.makeText(context, "Proc:"+e.toString(), 0);
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
				toast.setDuration(android.widget.Toast.LENGTH_LONG);
				//toast.show();
            }
        }
        for (int j = 0; j < mMounts.size(); j++)
        {
            String mount = mMounts.get(j);
            mount+="/";

            File TriggerFile = new File(mount, "HTA02100.000");
            //if (TriggerFile.canWrite())
            {
                try {
                    if (TriggerFile.exists()) {
                        TriggerFile.delete();
                    }
                    TriggerFile.createNewFile();
                    TriggerFile.delete();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Toast.makeText(context, e.toString(), android.widget.Toast.LENGTH_SHORT).show();
					android.widget.Toast toast = android.widget.Toast.makeText(context, e.toString(), 0);
					toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
					toast.setDuration(android.widget.Toast.LENGTH_LONG);
					//toast.show();
					
                }
                //Toast.makeText(context, mount.toString(),android.widget.Toast.LENGTH_SHORT).show();
				android.widget.Toast toast = android.widget.Toast.makeText(context, mount.toString(), 0);
				toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
				toast.setDuration(android.widget.Toast.LENGTH_LONG);
				//toast.show();
            }
        }
        //File ADDR3file = new File("/mnt/media_rw/usbotg/", "HTA02100.000");
    }


    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("scan")) {

            String name = data.getString(0);
            String message = "Hello, " + name;
			TriggerScan(webView.getContext());
            callbackContext.success(message);

            return true;

        } else {
            
            return false;

        }
    }
}
/*
package nl.xservices.plugins;

import android.content.Context;
//import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import android.view.Gravity;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;


public class Toast extends CordovaPlugin {

  private static final String ACTION_SHOW_EVENT = "show";

  private android.widget.Toast mostRecentToast;

  // note that webView.isPaused() is not Xwalk compatible, so tracking it poor-man style
  private boolean isPaused;
   
  @Override
  public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
    if (ACTION_SHOW_EVENT.equals(action)) {

      if (this.isPaused) {
        return true;
      }

      final String message = args.getString(0);
      final String duration = args.getString(1);
      final String position = args.getString(2);

      cordova.getActivity().runOnUiThread(new Runnable() {
        public void run() {
          android.widget.Toast toast = android.widget.Toast.makeText(webView.getContext(), message, 0);

          if ("top".equals(position)) {
            toast.setGravity(Gravity.TOP|Gravity.CENTER_HORIZONTAL, 0, 20);
          } else  if ("bottom".equals(position)) {
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 20);
          } else if ("center".equals(position)) {
            toast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL, 0, 0);
          } else {
            callbackContext.error("invalid position. valid options are 'top', 'center' and 'bottom'");
            return;
          }

          if ("short".equals(duration)) {
            toast.setDuration(android.widget.Toast.LENGTH_SHORT);
          } else if ("long".equals(duration)) {
            toast.setDuration(android.widget.Toast.LENGTH_LONG);
          } else {
            callbackContext.error("invalid duration. valid options are 'short' and 'long'");
            return;
          }

          toast.show();
		  TriggerScan(webView.getContext());
          mostRecentToast = toast;
          callbackContext.success();
        }
      });

      return true;
    } else {
      callbackContext.error("toast." + action + " is not a supported function. Did you mean '" + ACTION_SHOW_EVENT + "'?");
      return false;
    }
  }

  @Override
  public void onPause(boolean multitasking) {
    if (mostRecentToast != null) {
      mostRecentToast.cancel();
    }
    this.isPaused = true;
  }

  @Override
  public void onResume(boolean multitasking) {
    this.isPaused = false;
  }
}*/