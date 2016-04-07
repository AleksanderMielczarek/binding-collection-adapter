package me.tatarka.bindingcollectionadapter.sample;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import me.tatarka.bindingcollectionadapter.BindingListViewAdapter;
import me.tatarka.bindingcollectionadapter.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter.ItemView;
import me.tatarka.bindingcollectionadapter.ItemViewSelector;

/**
 * Created by evan on 5/31/15.
 */
public class ViewModel {
    private final boolean checkable;
    public final ObservableList<ItemViewModel> items = new ObservableArrayList<>();

    private List<ItemViewModel> removedItems = new ArrayList<>();

    public ViewModel(boolean checkable) {
        this.checkable = checkable;
        for (int i = 0; i < 3; i++) {
            items.add(new ItemViewModel(i, checkable));
        }
    }

    /**
     * ItemView of a single type
     */
    public final ItemView singleItemView = ItemView.of(BR.item, R.layout.item);

    /**
     * ItemView of drop down type
     */
    public final ItemView dropDownItemView = ItemView.of(BR.item, R.layout.item_dropdown);

    /**
     * ItemView of multiple types based on the data.
     */
    public final ItemViewSelector<ItemViewModel> multipleItemViews = new ItemViewSelector<ItemViewModel>() {
        @Override
        public void select(ItemView itemView, int position, ItemViewModel item) {
            itemView.setBindingVariable(BR.item)
                    .setLayoutRes(position == 0 ? R.layout.item_header : R.layout.item);
        }

        // Only needed if you are using in a ListView
        @Override
        public int viewTypeCount() {
            return 2;
        }
    };

    /**
     * Define stable item ids
     */
    public final BindingListViewAdapter.ItemIds<ItemViewModel> itemIds = new BindingListViewAdapter.ItemIds<ItemViewModel>() {
        @Override
        public long getItemId(int position, ItemViewModel item) {
            return position;
        }
    };

    /**
     * Define page titles for a ViewPager
     */
    public final BindingViewPagerAdapter.PageTitles<ItemViewModel> pageTitles = new BindingViewPagerAdapter.PageTitles<ItemViewModel>() {
        @Override
        public CharSequence getPageTitle(int position, ItemViewModel item) {
            return "Item " + (item.getIndex() + 1);
        }
    };

    public TextWatcher getFilterDropdown() {
        return new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                for (ItemViewModel itemViewModel : items) {
                    if (!String.valueOf(itemViewModel.getIndex()).contains(s)) {
                        removedItems.add(itemViewModel);
                    }
                }
                for (ItemViewModel itemViewModel : removedItems) {
                    items.remove(itemViewModel);
                }
                for (ItemViewModel itemViewModel : removedItems) {
                    if (String.valueOf(itemViewModel.getIndex()).contains(s) && !items.contains(itemViewModel)) {
                        items.add(itemViewModel);
                    }
                }
                for (ItemViewModel itemViewModel : items) {
                    removedItems.remove(itemViewModel);
                }
                Collections.sort(items, new Comparator<ItemViewModel>() {
                    @Override
                    public int compare(ItemViewModel lhs, ItemViewModel rhs) {
                        return Integer.compare(lhs.getIndex(), rhs.getIndex());
                    }
                });
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
    }

    public void addItem() {
        items.add(new ItemViewModel(items.size(), checkable));
    }

    public void removeItem() {
        if (items.size() > 1) {
            items.remove(items.size() - 1);
        }
    }
}
