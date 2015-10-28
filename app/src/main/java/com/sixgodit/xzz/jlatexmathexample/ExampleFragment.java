package com.sixgodit.xzz.jlatexmathexample;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;


public class ExampleFragment extends Fragment implements OnClickListener {

    public static Fragment newInstance(String latex,
                                       int tag) {
        ExampleFragment fragment = new ExampleFragment();
        fragment.setTag(tag);
        fragment.setFormula(latex);
        return fragment;
    }

    private LaTexTextView mLaTexTextView;
    private String mLatex;
    private float mTextSize = 12;
    private int mTag;
    private EditText mSizeText;

    private void setFormula(String latex) {
        mLatex = latex;
    }

    private void setTag(int tag) {
        mTag = tag;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString("latex", mLatex);
        outState.putFloat("textsize", mTextSize);
        outState.putInt("tag", mTag);
        super.onSaveInstanceState(outState);
    }

    ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mLatex = savedInstanceState.getString("latex");
            mTextSize = savedInstanceState.getFloat("textsize");
            mTag = savedInstanceState.getInt("tag");
        }
    }

    ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LinearLayout layout = (LinearLayout) inflater.inflate(
                R.layout.fragment_example, container, false);
        mLaTexTextView = (LaTexTextView) layout.findViewById(R.id.logo);
        mSizeText = (EditText) layout.findViewById(R.id.size);
        layout.findViewById(R.id.set_textsize).setOnClickListener(this);

        return layout;
    }

    @Override
    public void onResume() {
        super.onResume();
//        setformula();
        mLaTexTextView.setLinketext(mLatex);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.set_textsize) {
            mTextSize = Integer.valueOf(mSizeText.getText().toString());
            mLaTexTextView.setTextSize(mTextSize);
            mLaTexTextView.setLinketext(mLatex);
        }
    }
}
