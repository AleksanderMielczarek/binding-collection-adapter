package me.tatarka.bindingcollectionadapter.sample;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.tatarka.bindingcollectionadapter.sample.databinding.AutocompletetextviewViewBinding;

/**
 * Created by Aleksander Mielczarek on 06.04.2016.
 */
public class FragmentAutoCompleteTextView extends Fragment {
    private static final String TAG = "BindingtAutoCompleteTextView";
    private ViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        viewModel = new ViewModel(false);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final AutocompletetextviewViewBinding binding = AutocompletetextviewViewBinding.inflate(inflater, container, false);
        binding.list.setThreshold(1);
        binding.setViewModel(viewModel);
        binding.setListeners(new Listeners(viewModel));
        binding.executePendingBindings();
        return binding.getRoot();
    }
}
