package autocompletion;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

public class MyAutoCompleteTextView
	extends AutoCompleteTextView {

	public MyAutoCompleteTextView(Context context) {
		this(context,null);
	}

	public MyAutoCompleteTextView(Context context, AttributeSet attrs) {
		this(context, attrs, android.R.attr.autoCompleteTextViewStyle);
	}

	public MyAutoCompleteTextView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	public boolean enoughToFilter() {		
		return true;
	}

}
