package io.sirio.sbs.fragments;


import android.app.Activity;
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
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.ArrayList;

import io.sirio.sbs.R;
import io.sirio.sbs.VolleyApplication;
import io.sirio.sbs.adapters.CursosAdapter;
import io.sirio.sbs.models.Curso;
import io.sirio.sbs.models.CursoPost;

/**
 * A simple {@link Fragment} subclass.
 */
public class CursosFragment extends Fragment {

    FragmentActivity mActivity;
    private ArrayList<CursoPost> dataset = new ArrayList<>();
    RecyclerView recyclerView;
    CursosAdapter cursosAdapter;
    private ProgressDialog progressDialog = null;


    String URL = "http://sbsconsulting.com.ec/?json=get_tag_posts&tag_slug=curso";

    public CursosFragment() {
        // Required empty public constructor
    }

    private void sendJsonRequest() {
        final ProgressDialog progressDialog = ProgressDialog.show(getActivity(), "Espere por favor", "estamos atendiendo su solicitud");

        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.e("miREspuesta", response.toString());
              /*  Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Curso>>() {
                }.getType();
                dataset = gson.fromJson(response.toString(), listType);*/
             //   parseJSON(response.toString());
                cursosAdapter.setCursoList(dataset);
                progressDialog.cancel();
                recyclerView.setAdapter(cursosAdapter);

/*                    cursosAdapter.SetOnItemClickListener(new CursosAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Bundle bundle = new Bundle();
//                          TextView fechaText = (TextView) view.findViewById(R.id.fecha_celula);
//                          bundle.putString("fecha",fechaText.getText().toString());
                            Fragment cursosFragment = new CursosFragment();
                            // cursosFragment.setArguments(bundle);
                            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, cursosFragment).commit();

                        }
                    } );*/


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.cancel();
                Log.e("error", error.getMessage());
                Toast.makeText(getActivity(), "Unable to fetch data: " + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        });


        VolleyApplication.getInstance().getRequestQueue().add(request);

    }



    public void parser(JSONArray response) {


        for (int i = 0; i < response.length(); i++) {

            CursoPost curso = new CursoPost();
            try {
                Log.e("POST", response.toString());
                JSONObject jsonObject = (JSONObject) response.get(i);
                Log.e("POST", jsonObject.toString());
                curso.setTitle(jsonObject.getString("title"));

                dataset.add(curso);
            } catch (JSONException e) {
                e.printStackTrace();
            }
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
                CursoPost curso = new CursoPost();
                for (int i = 0; i < jsonArrLength; i++) {

                    JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                    curso.setTitle(jsonChildNode.getString("title"));
                    Log.e("POST", curso.getTitle());
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
        recyclerView.setAdapter(cursosAdapter);
        progressDialog = ProgressDialog.show(getActivity(), "Espere por favor", "estamos atendiendo su solicitud");

        new FetchDataTask().execute(URL);

        return view;

    }


}
