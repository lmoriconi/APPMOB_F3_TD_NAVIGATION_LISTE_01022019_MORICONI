package ludus.lenny.lessports;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private final String EXTRA_NAME = "name";
    private final String EXTRA_DESC = "description";
    private final String EXTRA_RESULT = "result";

    private TextView tv_result = null;
    private String result = null;
    private RecyclerView recycler_view;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layout_manager;

    private Resources res;
    private String[] sports;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();                                               //On récupère les ressources de l'application, puis...
        sports = res.getStringArray(R.array.t_name);                        //... On récupère le tableau de noms de sports

        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        recycler_view.setHasFixedSize(true);                                //Taille fixe des éléments de la liste = optimisation du recyclerView

        layout_manager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layout_manager);

        adapter = new SportAdapter(sports);                                 //Remplir l'adaptateur
        recycler_view.setAdapter(adapter);

        recycler_view.addOnItemTouchListener(new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                launchActivity(sports, position);
            }
        }));
    }

    //Configuration de l'Intent pour lancer une nouvelle activité
    private void launchActivity(String[] sports, int position) {
        Intent myIntent = new Intent(this, SecondActivity.class);          //On crée un nouvel Intent, qui fera le pont entre l'activité actuelle et l'activité cible en transmettant certaines données
        String[] desc = res.getStringArray(R.array.t_desc);

        myIntent = myIntent.putExtra(EXTRA_NAME, sports[position]);                     //On ajoute à l'Intent le sport cliqué, qu'il transmettra à l'activité cible afin de déterminer le contenu à afficher
        myIntent = myIntent.putExtra(EXTRA_DESC, desc[position]);                       //Idem avec la description

        startActivityForResult(myIntent, 1);                                 //On lance la nouvelle activité en lui transmettant notre Intent, ainsi qu'un code de requête
    }

    //Lorsqu'on revient à l'activité principale, on récupère un résultat transmis par la seconde activité, en l'occurence on souhaite récupérer le nom du dernier sport consulté
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //S'il s'agit de la requête 1
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {                         //Si l'activité a bien retourné un résultat
                result = data.getStringExtra(EXTRA_RESULT);                 //On récupère la valeur du résultat
                tv_result = (TextView) findViewById(R.id.tv_result);        //On récupère la référence du TextView où le résultat sera affiché
                tv_result.setText("Dernier sport consulté : " + result);    //On affiche le résultat de la requête, on l'occurence le nom du sport consulté
                Log.v("LENNY", "Result: OK");
            }
            if (resultCode == Activity.RESULT_CANCELED) {                   //Si l'acitivité n'a retourné aucun résultat
                result = "null";
                Log.v("LENNY", "Result: KO");
            }
        }
    }
}
