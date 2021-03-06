package tutosandroidfrance.com.toolbaranddrawer.tuts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import tutosandroidfrance.com.toolbaranddrawer.R;


public class SecondFragment extends Fragment {
	// Store instance variables
	private String title;
	private int page;

	// newInstance constructor for creating fragment with arguments
	public static SecondFragment newInstance() {
		SecondFragment fragmentFirst = new SecondFragment();
		Bundle args = new Bundle();
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("someInt", 0);
		title = getArguments().getString("someTitle");
	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_second, container, false);
		return view;
	}
}