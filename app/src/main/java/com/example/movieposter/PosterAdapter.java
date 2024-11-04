package com.example.movieposter;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;
public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private List<Poster> posterList;
    private PostersListener postersListener;

    /**
     * Creates a new instance of PosterAdapter with the provided list of posters and listener.
     *
     * @param posterList      The list of Poster objects to be displayed in the RecyclerView.
     * @param postersListener Listener to handle actions on selected posters.
     */
    public PosterAdapter(List<Poster> posterList, PostersListener postersListener) {
        this.posterList = posterList;
        this.postersListener = postersListener;
    }

    /**
     * Inflates the layout for each item in the RecyclerView.
     *
     * @param parent   The parent ViewGroup that holds the items.
     * @param viewType The view type of the new View.
     * @return A new instance of PosterViewHolder with the item layout.
     */
    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PosterViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_container_poster, parent, false));
    }

    /**
     * Binds the data of a Poster object to the ViewHolder.
     *
     * @param holder   The PosterViewHolder which holds the views to display.
     * @param position The position of the item in the adapter.
     */
    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {
        holder.bindPosters(posterList.get(position));
    }

    /**
     * Returns the total number of items in the adapter.
     *
     * @return The size of the poster list.
     */
    @Override
    public int getItemCount() {
        return posterList.size();
    }

    /**
     * Retrieves the list of posters that are selected by the user.
     *
     * @return A list of selected Poster objects.
     */
    public List<Poster> getSelectedPoster() {
        List<Poster> selectedPosters = new ArrayList<>();
        for (Poster poster : this.posterList) {
            if (poster.isSelected) {
                selectedPosters.add(poster);
            }
        }
        return selectedPosters;
    }

    /**
     * PosterViewHolder is a nested class that holds the views for each poster item in the list.
     * It binds the poster data to the views and manages selection behavior.
     */
    class PosterViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout layoutPosters;
        View viewBackground;
        RoundedImageView imagePoster;
        TextView textName, textCreatedBy, textStory;
        RatingBar ratingBar;
        ImageView imageSelected;

        /**
         * Constructor for PosterViewHolder, initializing the views in the item layout.
         *
         * @param itemView The root view of the item layout.
         */
        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);
            layoutPosters = itemView.findViewById(R.id.layoutPosters);
            viewBackground = itemView.findViewById(R.id.viewBackground);
            imagePoster = itemView.findViewById(R.id.imagePosters);
            textName = itemView.findViewById(R.id.textName);
            textCreatedBy = itemView.findViewById(R.id.textCreateBy);
            textStory = itemView.findViewById(R.id.textStory);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            imageSelected = itemView.findViewById(R.id.imageSelected);
        }

        /**
         * Binds the Poster data to the views in the item layout and sets up click listener
         * to handle selection and deselection of posters.
         *
         * @param poster The Poster object containing the data to bind to the views.
         */
        void bindPosters(final Poster poster) {
            imagePoster.setImageResource(poster.image);
            textName.setText(poster.name);
            textCreatedBy.setText(poster.createdBy);
            textStory.setText(poster.story);
            ratingBar.setRating(poster.rating);

            // Set background and selection indicator based on whether the poster is selected
            if (poster.isSelected) {
                viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                imageSelected.setVisibility(View.VISIBLE);
            } else {
                viewBackground.setBackgroundResource(R.drawable.poster_background);
                imageSelected.setVisibility(View.GONE);
            }

            // Handle click event for selecting/deselecting a poster
            layoutPosters.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (poster.isSelected) {
                        viewBackground.setBackgroundResource(R.drawable.poster_background);
                        imageSelected.setVisibility(View.GONE);
                        poster.isSelected = false;
                        if (getSelectedPoster().isEmpty()) {
                            postersListener.onPosterAction(false);
                        }
                    } else {
                        viewBackground.setBackgroundResource(R.drawable.poster_selected_background);
                        imageSelected.setVisibility(View.VISIBLE);
                        poster.isSelected = true;
                        postersListener.onPosterAction(true);
                    }
                }
            });
        }
    }
}
