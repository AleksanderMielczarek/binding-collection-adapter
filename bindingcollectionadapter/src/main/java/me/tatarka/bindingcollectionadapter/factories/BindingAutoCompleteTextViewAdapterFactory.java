package me.tatarka.bindingcollectionadapter.factories;

import android.widget.AutoCompleteTextView;

import me.tatarka.bindingcollectionadapter.BindingAutoCompleteTextViewAdapter;
import me.tatarka.bindingcollectionadapter.ItemViewArg;

/**
 * Created by Aleksander Mielczarek on 06.04.2016.
 */
public interface BindingAutoCompleteTextViewAdapterFactory {

    <T> BindingAutoCompleteTextViewAdapter<T> create(AutoCompleteTextView autoCompleteTextView, ItemViewArg<T> arg);

    BindingAutoCompleteTextViewAdapterFactory DEFAULT = new BindingAutoCompleteTextViewAdapterFactory() {
        @Override
        public <T> BindingAutoCompleteTextViewAdapter<T> create(AutoCompleteTextView autoCompleteTextView, ItemViewArg<T> arg) {
            return new BindingAutoCompleteTextViewAdapter<>(arg);
        }
    };
}
