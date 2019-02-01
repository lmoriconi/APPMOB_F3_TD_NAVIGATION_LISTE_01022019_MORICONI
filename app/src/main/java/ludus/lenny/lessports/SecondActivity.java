package ludus.lenny.lessports;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private final String EXTRA_NAME = "name";               //Nom du sport cliqué
    private final String EXTRA_DESC = "description";        //Description du sport cliqué
    private final String EXTRA_RESULT = "result";           //Résultat renvoyé à l'activité principale

    private ImageView sport_img = null;
    private Button btn_return = null;
    private TextView tv_desc = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        btn_return = (Button) findViewById(R.id.btn_previous);
        tv_desc = (TextView) findViewById(R.id.description);
        sport_img = (ImageView) findViewById(R.id.sport_img);

        Intent myIntent = getIntent();                                      //On récupère l'Intent de l'activité principale

        final String str_name = myIntent.getStringExtra(EXTRA_NAME);        //Grâce à l'Intent on récupère le nom du sport cliqué
        final String str_desc = myIntent.getStringExtra(EXTRA_DESC);        //Idem avec la description

        switch (str_name){
            case "Football" : sport_img.setBackgroundResource(R.drawable.football2); break;
            case "Tennis" : sport_img.setBackgroundResource(R.drawable.tennis2); break;
            case "Basketball" : sport_img.setBackgroundResource(R.drawable.basketball2); break;
            case "Handball" : sport_img.setBackgroundResource(R.drawable.handball2); break;
            case "Boxe" : sport_img.setBackgroundResource(R.drawable.boxe2); break;
            case "Ski" : sport_img.setBackgroundResource(R.drawable.ski2); break;
        }

        tv_desc.setText(str_desc);                                      //On affiche la description du sport dans l'activité

        //Lorsqu'on clique sur le bouton "Retour"
        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                launchActivity(str_name);       //On lance l'activité principale en donnant en paramètre le nom du dernier sport affiché
            }
        });

    }

    private void launchActivity(String result) {
        Intent returnIntent = new Intent(this, MainActivity.class);     //On crée un Intent de retour vers l'activité principale
        returnIntent.putExtra(EXTRA_RESULT, result);                                 //On récupère le nom du sport affiché comme valeur de retour
        setResult(Activity.RESULT_OK, returnIntent);                                 //On confirme avoir récupéré un résultat
        finish();                                                                    //On termine l'activité
    }
}
