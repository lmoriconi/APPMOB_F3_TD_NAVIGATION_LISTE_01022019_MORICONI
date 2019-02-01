package ludus.lenny.lessports;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class SportAdapter extends RecyclerView.Adapter<SportAdapter.ViewHolder> {
    private String[] mDataSet;                  //Déclarer tableau de données

    //ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
        //Déclarer les objets de la vue qui seront chargés (par le tableau de données)
        public TextView mTextView;
        public ImageView mImageView;

        //Constructeur du holder : le viewHolder a une référence vers tous les objets de la liste
        public ViewHolder(View v){
            super(v);
            mTextView = (TextView) v.findViewById(R.id.title);
            mImageView = (ImageView) v.findViewById(R.id.sport_img);
        }
    }

    //Constructeur de l'adaptateur
    public SportAdapter(String[] sports){
        mDataSet = sports;
    }

    //Chargement de layout et initialisation du viewHolder
    @Override
    public SportAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.elements, parent, false);
        ViewHolder vh = new ViewHolder(v);

        return vh;
    }

    //Lien entre ViewHolder et données
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position){
        holder.mTextView.setText(mDataSet[position]);
        //Selon son nom on affiche l'image correspondante dans l'ImageView
        switch (mDataSet[position]){
            case "Football" : holder.mImageView.setBackgroundResource(R.drawable.football); break;
            case "Tennis" : holder.mImageView.setBackgroundResource(R.drawable.tennis); break;
            case "Basketball" : holder.mImageView.setBackgroundResource(R.drawable.basketball); break;
            case "Handball" : holder.mImageView.setBackgroundResource(R.drawable.handball); break;
            case "Boxe" : holder.mImageView.setBackgroundResource(R.drawable.boxe); break;
            case "Ski" : holder.mImageView.setBackgroundResource(R.drawable.ski); break;
        }
    }

    //Nombre d'éléments de la liste
    @Override
    public int getItemCount(){
        return mDataSet.length;
    }
}
