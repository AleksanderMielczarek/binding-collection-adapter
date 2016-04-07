package me.tatarka.bindingcollectionadapter.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.AppCompatAutoCompleteTextView;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Aleksander Mielczarek on 06.04.2016.
 */
public class BindingAutoCompleteTextView extends AppCompatAutoCompleteTextView {

    public BindingAutoCompleteTextView(Context context) {
        super(context);
    }

    public BindingAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BindingAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean enoughToFilter() {
        return true;
    }

    @Override
    protected CharSequence convertSelectionToString(Object selectedItem) {
        return "";
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN && shouldShowDropDown()) {
            showDropDown();
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if (focused && shouldShowDropDown()) {
            performFiltering("", 0);
        }
    }

    private boolean shouldShowDropDown() {
        return !isPopupShowing() && getAdapter() != null;
    }

}
