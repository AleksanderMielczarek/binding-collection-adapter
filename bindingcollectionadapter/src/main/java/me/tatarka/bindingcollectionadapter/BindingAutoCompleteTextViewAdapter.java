package me.tatarka.bindingcollectionadapter;

import android.support.annotation.NonNull;
import android.widget.Adapter;
import android.widget.Filter;
import android.widget.Filterable;

/**
 * Created by Aleksander Mielczarek on 06.04.2016.
 */
public class BindingAutoCompleteTextViewAdapter<T> extends BindingListViewAdapter<T> implements Filterable {

    private final Filter filter;

    /**
     * Constructs a new instance with the given {@link ItemViewArg}.
     *
     * @param arg
     */
    public BindingAutoCompleteTextViewAdapter(@NonNull ItemViewArg<T> arg) {
        super(arg);
        filter = new StubFilter(this);
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    private static final class StubFilter extends Filter {

        private final FilterResults filterResults;

        public StubFilter(Adapter adapter) {
            filterResults = new FilterResults();
            filterResults.count = adapter.getCount();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }
}
