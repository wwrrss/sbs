package io.sirio.sbs.fragments;


import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.adapters.CursosAdapter;
import io.sirio.sbs.models.CursoPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosFragment extends Fragment implements CursosAdapter.OnItemClickListener{

    public static final String TAG = "Cursos";
    private ArrayList<CursoPost> dataset = new ArrayList<>();
    RecyclerView recyclerView;
    CursosAdapter cursosAdapter;
    private ProgressDialog progressDialog = null;


    String URL = "http://sbsconsulting.com.ec/?json=get_tag_posts&tag_slug=curso";

    public CursosFragment() {
        // Required empty public constructor
    }




    @Override
    public void onItemClick(View view, int position) {
        Log.e("click",position+"");
       String contentPage =  dataset.get(position).getContent();
        try {
            Bundle bundle = new Bundle();
            bundle.putString("contentCurso",contentPage);
            Fragment fragment = new CursosViewFragment();
            fragment.setArguments(bundle);
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,fragment).commit();
        }catch (Exception ex){
            ex.printStackTrace();
        }



    }

    private class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            // TODO Auto-generated method stub
            InputStream inputStream = null;
            String result = null;
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(params[0]);

            try {

                HttpResponse response = client.execute(httpGet);
                inputStream = response.getEntity().getContent();

                // convert inputstream to string
                if (inputStream != null) {
                    result = convertInputStreamToString(inputStream);
                    Log.i("App", "Data received:" + result);

                } else
                    result = "Failed to fetch data";

                return result;

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(String dataFetched) {
            //parse the JSON data and then display
            parseJSON(dataFetched);
            progressDialog.cancel();
            cursosAdapter.setCursoList(dataset);
            recyclerView.setAdapter(cursosAdapter);
        }


        private String convertInputStreamToString(InputStream inputStream) throws IOException {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            String result = "";
            while ((line = bufferedReader.readLine()) != null)
                result += line;

            inputStream.close();
            return result;

        }

        private void parseJSON(String data) {

            try {

                JSONObject jsonResponse = new JSONObject(data);
                JSONArray jsonMainNode = jsonResponse.optJSONArray("posts");
                int postCount = Integer.parseInt(jsonResponse.getString("count"));

                int jsonArrLength = jsonMainNode.length();

                for (int i = 0; i < jsonArrLength; i++) {
                    CursoPost curso = new CursoPost();
                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    curso.setTitle(jsonChildNode.getString("title"));
                    curso.setId(jsonChildNode.getInt("id"));
                    curso.setContent(jsonChildNode.getString("content"));
                    curso.setExcerpt(jsonChildNode.getString("excerpt"));
                    curso.setUrl(jsonChildNode.getString("url"));
                    dataset.add(curso);
                }

            } catch (Exception e) {
                Log.i("App", "Error parsing data" + e.getMessage());

            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState){

        final View view = inflater.inflate(R.layout.fragment_cursos, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        cursosAdapter = new CursosAdapter(getActivity());
        cursosAdapter.SetOnItemClickListener(this);
        recyclerView.setAdapter(cursosAdapter);
        progressDialog = ProgressDialog.show(getActivity(), "Espere por favor", "estamos atendiendo su solicitud");

        new FetchDataTask().execute(URL);

        return view;

    }


}
