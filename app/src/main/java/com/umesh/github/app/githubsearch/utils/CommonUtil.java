package com.umesh.github.app.githubsearch.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtil {
	
	public static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	public static final String USERNAME_PATTERN = "[a-zA-Z][a-zA-Z ]*";

	public static boolean validateEmail(String email) {
		Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = emailPattern.matcher(email);
		return matcher.matches();
	}

	public static boolean validateUserName(String name) {
		Pattern namePattern = Pattern.compile(USERNAME_PATTERN);
		Matcher matcher = namePattern.matcher(name);
		return matcher.matches();
	}

	public static String getServerNameFromURL(String url) {
		try {
			int from = url.indexOf("//") + 2;
			int to = url.indexOf("/", from);
			if (to < 0) {
				return url.substring(from);
			}
			return url.substring(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}

	}

	public static String getFormatedDouble(double value) {
		return new DecimalFormat("0.00").format(value);
	}

	public static String getFormatedDoubleInWholeNumber(double value) {
		return new DecimalFormat("0").format(value);
	}

	public static String getNodeNameFromURL(String url) {
		try {
			int firstIndex = url.indexOf("//");
			int from = url.indexOf("/", firstIndex + 2);
			if (url.charAt(from - 1) == '/') {
				return "";
			}
			int to = url.length();
			return url.substring(from, to);
		} catch (Exception e) {
			e.printStackTrace();
			return url;
		}
	}
	
	public static String getPath(Activity activity, Uri uri) {
		if (null != uri) {
			String[] projection = { MediaStore.Images.Media.DATA };
			Cursor cursor = activity.getContentResolver().query(uri, projection, null, null, null);
			if (null != cursor && cursor.moveToFirst()) {
				int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
				return cursor.getString(columnIndex);
			}
		}
		return null;
	}

    public static String getCSVFromList(List<String> stringList){
        String temp="";
        for(String s:stringList)
            temp+=s+",";
        if(temp.length()>0)
        return temp.substring(0,temp.length()-1);
        return "";
    }

	public static List<String> getListFromCSV(String csv) {

		List<String> stringList = new ArrayList<>();
		if(TextUtils.isEmpty(csv))
			return stringList;

		String[] strings = csv.split(",");
		for (String s : strings) {
			stringList.add(s.trim());
		}
		return stringList;
	}

	public static boolean isEmpty(String string) {
		return TextUtils.isEmpty(string) || string.contentEquals("null");
	}

	public static boolean isLocationEnabled(Context context) {
		int locationMode = 0;
		String locationProviders;

		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
			try {
				locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
			} catch (Settings.SettingNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			return locationMode != Settings.Secure.LOCATION_MODE_OFF;
		}else{
			locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
			return !TextUtils.isEmpty(locationProviders);
		}
	}

	public static boolean isValidMobileNumber(String mobileNumber) {

		if(isEmpty(mobileNumber) || !TextUtils.isDigitsOnly(mobileNumber) || mobileNumber.length() < 10)
			return false;

		return true;
	}

	public static void setListViewHeightBasedOnChildren(ListView listView) {
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			// pre-condition
			return;
		}

		int totalHeight = listView.getPaddingTop() + listView.getPaddingBottom();

		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			if (listItem instanceof ViewGroup) {
				listItem.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
			}

			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}

		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}

}