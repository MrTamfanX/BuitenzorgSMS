package com.buitenzorgsms;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.*;
import android.app.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.webkit.*;
import android.animation.*;
import android.view.animation.*;
import java.util.*;
import java.util.regex.*;
import java.text.*;
import org.json.*;
import java.util.ArrayList;
import java.util.HashMap;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.EditText;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import java.io.InputStream;
import android.content.Intent;
import android.net.Uri;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AdapterView;
import android.view.View;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import android.content.ClipData;
import android.content.ClipboardManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.DialogFragment;


public class MainActivity extends  AppCompatActivity  { 
	
	
	private String save = "";
	private double length = 0;
	private String value = "";
	private double number = 0;
	
	private ArrayList<HashMap<String, Object>> listmapsms = new ArrayList<>();
	
	private LinearLayout linear1;
	private LinearLayout linear2;
	private TextView textview2;
	private LinearLayout linear3;
	private ListView listview1;
	private TextView textview1;
	private ImageView imageview1;
	private ImageView imageview2;
	private EditText edittext1;
	private ImageView imageview3;
	
	private Calendar calendar = Calendar.getInstance();
	private AlertDialog.Builder dialog;
	private Intent intent = new Intent();
	@Override
	protected void onCreate(Bundle _savedInstanceState) {
		super.onCreate(_savedInstanceState);
		setContentView(R.layout.main);
		initialize(_savedInstanceState);
		initializeLogic();
	}
	
	private void initialize(Bundle _savedInstanceState) {
		
		linear1 = (LinearLayout) findViewById(R.id.linear1);
		linear2 = (LinearLayout) findViewById(R.id.linear2);
		textview2 = (TextView) findViewById(R.id.textview2);
		linear3 = (LinearLayout) findViewById(R.id.linear3);
		listview1 = (ListView) findViewById(R.id.listview1);
		textview1 = (TextView) findViewById(R.id.textview1);
		imageview1 = (ImageView) findViewById(R.id.imageview1);
		imageview2 = (ImageView) findViewById(R.id.imageview2);
		edittext1 = (EditText) findViewById(R.id.edittext1);
		imageview3 = (ImageView) findViewById(R.id.imageview3);
		dialog = new AlertDialog.Builder(this);
		
		listview1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> _param1, View _param2, int _param3, long _param4) {
				final int _position = _param3;
				dialog.setTitle(listmapsms.get((int)_position).get("address").toString());
				dialog.setIcon(R.drawable.ic_akasbuitenzorgsyn_5);
				dialog.setMessage("Pesan: ".concat(listmapsms.get((int)_position).get("body").toString().concat("\n\nNomer: ".concat(listmapsms.get((int)_position).get("date").toString()))));
				dialog.setPositiveButton("Salin", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						((ClipboardManager) getSystemService(getApplicationContext().CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("clipboard", listmapsms.get((int)_position).get("body").toString()));
						SketchwareUtil.showMessage(getApplicationContext(), "Tersalin");
					}
				});
				dialog.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface _dialog, int _which) {
						
					}
				});
				dialog.create().show();
			}
		});
		
		textview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Fitur ini belum di aktifkan masih beta");
			}
		});
		
		imageview1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				SketchwareUtil.showMessage(getApplicationContext(), "Fitur ini belum di aktifkan masih beta");
			}
		});
		
		imageview2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View _view) {
				_Popup();
			}
		});
		
		edittext1.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				final String _charSeq = _param1.toString();
				listmapsms = new Gson().fromJson(save, new TypeToken<ArrayList<HashMap<String, Object>>>(){}.getType());
				if (_charSeq.length() > 0) {
					length = listmapsms.size();
					number = length - 1;
					for(int _repeat23 = 0; _repeat23 < (int)(length); _repeat23++) {
						value = listmapsms.get((int)number).get("address").toString();
						if (value.toLowerCase().contains(_charSeq.toLowerCase())) {
							
						}
						else {
							listmapsms.remove((int)(number));
						}
						number--;
					}
				}
				listview1.setAdapter(new Listview1Adapter(listmapsms));
				((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
			}
			
			@Override
			public void beforeTextChanged(CharSequence _param1, int _param2, int _param3, int _param4) {
				
			}
			
			@Override
			public void afterTextChanged(Editable _param1) {
				
			}
		});
	}
	
	private void initializeLogic() {
		listmapsms = getsms.main(getApplicationContext());
		listview1.setAdapter(new Listview1Adapter(listmapsms));
		((BaseAdapter)listview1.getAdapter()).notifyDataSetChanged();
		_NavStatusBarColor("#101D24", "#101D24");
		_SetCornerRadius(linear3, 16, 22, "#101D24");
		listview1.setOverScrollMode(View.OVER_SCROLL_NEVER);listview1.setVerticalScrollBarEnabled(false);
		listview1.setDivider(null);listview1.setDividerHeight(0); listview1.setSelector(android.R.color.transparent);
		
		_rippleRoundStroke(imageview1, "#101D24", "#EEEEEE", 20, 0, "#101D24");
		_rippleRoundStroke(imageview2, "#101D24", "#EEEEEE", 20, 0, "#101D24");
		_rippleRoundStroke(imageview3, "#101D24", "#EEEEEE", 360, 0, "#101D24");
		_rippleRoundStroke(textview1, "#101D24", "#EEEEEE", 20, 0, "#101D24");
		save = new Gson().toJson(listmapsms);
	}
	
	@Override
	protected void onActivityResult(int _requestCode, int _resultCode, Intent _data) {
		
		super.onActivityResult(_requestCode, _resultCode, _data);
		
		switch (_requestCode) {
			
			default:
			break;
		}
	}
	
	public void _addCardView (final View _layoutView, final double _margins, final double _cornerRadius, final double _cardElevation, final double _cardMaxElevation, final boolean _preventCornerOverlap, final String _backgroundColor) {
		androidx.cardview.widget.CardView cv = new androidx.cardview.widget.CardView(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
		int m = (int)_margins;
		lp.setMargins(m,m,m,m);
		cv.setLayoutParams(lp);
		int c = Color.parseColor(_backgroundColor);
		cv.setCardBackgroundColor(c);
		cv.setRadius((float)_cornerRadius);
		cv.setCardElevation((float)_cardElevation);
		cv.setMaxCardElevation((float)_cardMaxElevation);
		cv.setPreventCornerOverlap(_preventCornerOverlap);
		if(_layoutView.getParent() instanceof LinearLayout){
			ViewGroup vg = ((ViewGroup)_layoutView.getParent());
			vg.removeView(_layoutView);
			vg.removeAllViews();
			vg.addView(cv);
			cv.addView(_layoutView);
		}else{
			
		}
	}
	
	
	public void _NavStatusBarColor (final String _color1, final String _color2) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
			Window w = this.getWindow();	w.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);	w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
			w.setStatusBarColor(Color.parseColor("#" + _color1.replace("#", "")));	w.setNavigationBarColor(Color.parseColor("#" + _color2.replace("#", "")));
		}
	}
	
	
	public void _SetCornerRadius (final View _view, final double _radius, final double _shadow, final String _color) {
		android.graphics.drawable.GradientDrawable ab = new android.graphics.drawable.GradientDrawable();
		
		ab.setColor(Color.parseColor(_color));
		ab.setCornerRadius((float) _radius);
		_view.setElevation((float) _shadow);
		_view.setBackground(ab);
		
		//Add More block in OnCreateActivity :
	}
	
	
	public void _rippleRoundStroke (final View _view, final String _focus, final String _pressed, final double _round, final double _stroke, final String _strokeclr) {
		android.graphics.drawable.GradientDrawable GG = new android.graphics.drawable.GradientDrawable();
		GG.setColor(Color.parseColor(_focus));
		GG.setCornerRadius((float)_round);
		GG.setStroke((int) _stroke,
		Color.parseColor("#" + _strokeclr.replace("#", "")));
		android.graphics.drawable.RippleDrawable RE = new android.graphics.drawable.RippleDrawable(new android.content.res.ColorStateList(new int[][]{new int[]{}}, new int[]{ Color.parseColor("#FF757575")}), GG, null);
		_view.setBackground(RE);
	}
	
	
	public void _Popup () {
		View popupView = getLayoutInflater().inflate(R.layout.popupkas, null);
		final PopupWindow popup = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
		TextView tx1 = popupView.findViewById(R.id.tx1);
		
		TextView tx2 = popupView.findViewById(R.id.tx2);
		
		TextView tx3 = popupView.findViewById(R.id.tx3);
		
		
		LinearLayout ly = popupView.findViewById(R.id.ly);
		
		LinearLayout lin1 = popupView.findViewById(R.id.lin1);
		
		LinearLayout lin2 = popupView.findViewById(R.id.lin2);
		
		LinearLayout lin3 = popupView.findViewById(R.id.lin3);
		
		tx1.setTextColor(0xFF2962FF);
		tx2.setTextColor(0xFF2962FF);
		tx3.setTextColor(0xFF2962FF);
		tx1.setText("YOUTUBE");
		tx2.setText("FACEBOOK");
		tx3.setText("INSTAGRAM");
		lin1.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://youtube.com/channel/UCtEg6TEEUKOkKSc8GP6DbiA"));
				startActivity(intent);
				popup.dismiss();
			} });
		
		lin2.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://www.facebook.com/profile.php?id=100038754743614"));
				startActivity(intent);
				popup.dismiss();
			} });
		
		lin3.setOnClickListener(new OnClickListener() { public void onClick(View view) {
				intent.setAction(Intent.ACTION_VIEW);
				intent.setData(Uri.parse("https://www.instagram.com/Prakasa_Jr644"));
				startActivity(intent);
				popup.dismiss();
			} });
		
		popup.setAnimationStyle(android.R.style.Animation_Dialog);
		
		popup.showAsDropDown(imageview2, 0,0);
		
		android.graphics.drawable.GradientDrawable ln = new android.graphics.drawable.GradientDrawable ();
		ln.setColor (Color.parseColor("#101D24"));
		
		ln.setCornerRadius (10);
		
		ly.setBackground (ln);
		
		ly.setElevation(14);
	}
	
	
	public class Listview1Adapter extends BaseAdapter {
		ArrayList<HashMap<String, Object>> _data;
		public Listview1Adapter(ArrayList<HashMap<String, Object>> _arr) {
			_data = _arr;
		}
		
		@Override
		public int getCount() {
			return _data.size();
		}
		
		@Override
		public HashMap<String, Object> getItem(int _index) {
			return _data.get(_index);
		}
		
		@Override
		public long getItemId(int _index) {
			return _index;
		}
		@Override
		public View getView(final int _position, View _v, ViewGroup _container) {
			LayoutInflater _inflater = (LayoutInflater)getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View _view = _v;
			if (_view == null) {
				_view = _inflater.inflate(R.layout.akas, null);
			}
			
			final LinearLayout linear2 = (LinearLayout) _view.findViewById(R.id.linear2);
			final LinearLayout linear3 = (LinearLayout) _view.findViewById(R.id.linear3);
			final LinearLayout linear4 = (LinearLayout) _view.findViewById(R.id.linear4);
			final ImageView imageview1 = (ImageView) _view.findViewById(R.id.imageview1);
			final LinearLayout linear5 = (LinearLayout) _view.findViewById(R.id.linear5);
			final TextView textview2 = (TextView) _view.findViewById(R.id.textview2);
			final TextView textview1 = (TextView) _view.findViewById(R.id.textview1);
			final TextView textview3 = (TextView) _view.findViewById(R.id.textview3);
			
			if (listmapsms.get((int)_position).containsKey("address")) {
				textview1.setText(listmapsms.get((int)_position).get("address").toString());
				textview1.setVisibility(View.VISIBLE);
				linear2.setVisibility(View.VISIBLE);
			}
			else {
				textview1.setVisibility(View.GONE);
				linear2.setVisibility(View.GONE);
			}
			if (listmapsms.get((int)_position).containsKey("body")) {
				textview2.setText(listmapsms.get((int)_position).get("body").toString());
				textview2.setVisibility(View.VISIBLE);
			}
			else {
				textview2.setVisibility(View.GONE);
			}
			if (listmapsms.get((int)_position).containsKey("date")) {
				textview3.setText(listmapsms.get((int)_position).get("date").toString());
				textview3.setVisibility(View.VISIBLE);
			}
			else {
				textview3.setVisibility(View.GONE);
			}
			_addCardView(imageview1, 0, 20, 8, 8, true, "#101D24");
			textview1.setTextSize((int)17);
			textview2.setTextSize((int)13);
			textview3.setTextSize((int)11);
			
			return _view;
		}
	}
	
	@Deprecated
	public void showMessage(String _s) {
		Toast.makeText(getApplicationContext(), _s, Toast.LENGTH_SHORT).show();
	}
	
	@Deprecated
	public int getLocationX(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[0];
	}
	
	@Deprecated
	public int getLocationY(View _v) {
		int _location[] = new int[2];
		_v.getLocationInWindow(_location);
		return _location[1];
	}
	
	@Deprecated
	public int getRandom(int _min, int _max) {
		Random random = new Random();
		return random.nextInt(_max - _min + 1) + _min;
	}
	
	@Deprecated
	public ArrayList<Double> getCheckedItemPositionsToArray(ListView _list) {
		ArrayList<Double> _result = new ArrayList<Double>();
		SparseBooleanArray _arr = _list.getCheckedItemPositions();
		for (int _iIdx = 0; _iIdx < _arr.size(); _iIdx++) {
			if (_arr.valueAt(_iIdx))
			_result.add((double)_arr.keyAt(_iIdx));
		}
		return _result;
	}
	
	@Deprecated
	public float getDip(int _input){
		return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, _input, getResources().getDisplayMetrics());
	}
	
	@Deprecated
	public int getDisplayWidthPixels(){
		return getResources().getDisplayMetrics().widthPixels;
	}
	
	@Deprecated
	public int getDisplayHeightPixels(){
		return getResources().getDisplayMetrics().heightPixels;
	}
	
}