package io.github.suzp1984.jbigandroid.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import javax.inject.Inject;

import io.github.suzp1984.jbigandroid.JbigApplication;
import io.github.suzp1984.jbigandroid.R;
import io.github.suzp1984.jbigandroid.adapter.ImageAdapter;
import io.github.suzp1984.jbigandroid.controller.JbigController;
import io.github.suzp1984.jbigandroid.controller.MainController;

public class DecoderFragment extends Fragment implements
        JbigController.JbigDecoderUi {

    RecyclerView mRecyclerView;

    @Inject
    MainController mMainController;

    private ImageAdapter mImageAdapter;

    private JbigController.JbigUiCallback mJbigUiCallback;

    public DecoderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        ((JbigApplication) getActivity().getApplication()).getApplicationComponent().inject(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_decoder, container, false);

        mRecyclerView = view.findViewById(R.id.recycler);
        mImageAdapter = new ImageAdapter();

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(false);
        mRecyclerView.setAdapter(mImageAdapter);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            // Restore last state for checked position.
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        getController().attachUi(this);
    }

    @Override
    public void onPause() {
        getController().detachUi(this);

        super.onPause();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    // JbigDecoderUi
    @Override
    public void showJbig(byte[] jbig) {

    }

    @Override
    public void showBitmaps(Bitmap[] bitmaps) {
        Log.d("DecoderFragment", "show bitmaps");
        mImageAdapter.addBitmap(bitmaps[0]);
        mImageAdapter.notifyDataSetChanged();
    }

    @Override
    public void setCallback(JbigController.JbigUiCallback callback) {
        mJbigUiCallback = callback;
    }

    @Override
    public boolean isModal() {
        return false;
    }

    private JbigController getController() {

        return mMainController.getJbigController();
    }
}
