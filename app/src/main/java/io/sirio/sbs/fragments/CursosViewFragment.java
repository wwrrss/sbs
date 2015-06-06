package io.sirio.sbs.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import io.sirio.sbs.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosViewFragment extends Fragment {

    TextView tTituloCurso;

    public CursosViewFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_cursos_view, container, false);


        String content = getArguments().getString("contentCurso");

        WebView mWebView = (WebView) view.findViewById(R.id.webView);
        WebSettings settings = mWebView.getSettings();

        settings.setDefaultTextEncodingName("utf-8");
        String header = "<?xml version=\"1.0\" encoding=\"utf-8\" ?> ";
        mWebView.loadDataWithBaseURL(null, content, "text/html", "utf-8", null);
        return view;



    }


}
