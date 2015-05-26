package io.sirio.sbs;

import android.app.ProgressDialog;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;

import io.sirio.sbs.adapters.CursosAdapter;
import io.sirio.sbs.models.Curso;


public class CursosActivity extends ActionBarActivity {

    private ArrayList<Curso> dataset;
    RecyclerView recyclerView;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursos);

        Toolbar toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String URL= "https://script.googleusercontent.com/macros/echo?user_content_key=iIGW6tDpoMMsAsRLO82xJG8WIVhY4VtM5gJgiA3QO6zStQOhGr8N4EbfKEDH59r0gTfTtum2cUrxmSuOwabPti5EzVkbj1G7m5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnA21ty4-NFgqoKc_ZhjFAX1ah6w8hoKrwU71K7lgPAwVmCE74_lR62bbtcQNKfQPMhdIug5zkO6q&lib=MuVcg4D5zOmfGpbFbxsUiP8v3COA06hUq";

        id = R.layout.row_cursos;

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_cursos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        RequestQueue queue= Volley.newRequestQueue(this);

        final ProgressDialog progressDialog = ProgressDialog.show(this, "Espere por favor", "estamos atendiendo su solicitud");

        JsonArrayRequest request = new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
               // Log.e("miREspuesta", response.toString());
                dataset = new ArrayList<>();
                //parser(response);En vez de hacer el parse utilizar GSON que ya te hace eso automaticamente
                Gson gson = new Gson();
                Type listType = new TypeToken<ArrayList<Curso>>(){}.getType();
                dataset = gson.fromJson(response.toString(),listType);
                progressDialog.cancel();
                recyclerView.setAdapter(new CursosAdapter(dataset, id));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.cancel();
            }
        });


        queue.add(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_cursos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if(id == R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }

        return super.onOptionsItemSelected(item);
    }




}
