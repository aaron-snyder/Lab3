package com.example.movieposter;

import static android.widget.Toast.*;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements PostersListener {

    private Button buttonAddToWatchlist;

    /**
     * Initializes the activity, sets up UI elements, enables edge-to-edge display,
     * and populates the list of movie posters with sample data.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously
     * being shut down then this Bundle contains the most recent data; otherwise, it is null.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        RecyclerView postersRecyclerView = findViewById(R.id.postersRecyclerView);
        buttonAddToWatchlist = findViewById(R.id.buttonAddToWatchList);

        // Prepare data
        List<Poster> posterList = new ArrayList<>();
        Poster minecraftMovie = new Poster();
        Poster menInBlack = new Poster();
        Poster beetlejuice = new Poster();
        Poster beetlejuiceTwo = new Poster();
        Poster cocaineBear = new Poster();
        Poster barbie = new Poster();
        Poster oppenheimer = new Poster();
        Poster theCrazies = new Poster();
        Poster worldWarZ = new Poster();
        Poster jujutsuKaisen = new Poster();

        // Set the movies poster images
        minecraftMovie.image = R.drawable.minecraft_poster;
        menInBlack.image = R.drawable.men_in_black_poster;
        beetlejuice.image = R.drawable.beetlejuice_poster;
        beetlejuiceTwo.image = R.drawable.beetlejuice_2_poster;
        cocaineBear.image = R.drawable.cocaine_bear_poster;
        barbie.image = R.drawable.barbie_poster;
        oppenheimer.image = R.drawable.oppenheimer_poster;
        theCrazies.image = R.drawable.the_crazies_poster;
        worldWarZ.image = R.drawable.world_war_z_poster;
        jujutsuKaisen.image = R.drawable.jjk_poster;

        // Set the movies names
        minecraftMovie.name = "The Minecraft Movie";
        menInBlack.name = "Men In Black";
        beetlejuice.name = "Beetlejuice";
        beetlejuiceTwo.name = "Beetlejuice 2";
        cocaineBear.name = "Cocaine Bear";
        barbie.name = "The Barbie Movie";
        oppenheimer.name = "Oppenheimer";
        theCrazies.name = "The Crazies";
        worldWarZ.name = "World War Z";
        jujutsuKaisen.name = "Jujutsu Kaisen 0";

        // Set the movies authors
        minecraftMovie.createdBy = "Jared Hess";
        menInBlack.createdBy = "Lowell Cunningham";
        beetlejuice.createdBy = "Michael McDowell";
        beetlejuiceTwo.createdBy = "Tim Burton";
        cocaineBear.createdBy = "Jimmy Warden";
        barbie.createdBy = "Greta Gerwig";
        oppenheimer.createdBy = "Christopher Nolan";
        theCrazies.createdBy = "Scott Kosar";
        worldWarZ.createdBy = "Max Brooks";
        jujutsuKaisen.createdBy = "Gege Akutami";

        // Set the movies ratings
        minecraftMovie.rating = 2f;
        menInBlack.rating = 4f;
        beetlejuice.rating = 4f;
        beetlejuiceTwo.rating = 3f;
        cocaineBear.rating = 5f;
        barbie.rating = 4f;
        oppenheimer.rating = 5f;
        theCrazies.rating = 3f;
        worldWarZ.rating = 4f;
        jujutsuKaisen.rating = 5f;

        // Set the movies descriptions
        minecraftMovie.story = "A funny movie about the popular video game Minecraft.";
        menInBlack.story = "An action-comedy about a government agency that monitors alien life on Earth.";
        beetlejuice.story = "A comedy movie about a demon that is trying to swindle himself back to life using a newly dead couple.";
        beetlejuiceTwo.story = "A sequel to Beetlejuice, where the demon is now being hunted by his crazy ex-wife to finish his sacrifice.";
        cocaineBear.story = "A comedy-horror based on a true story about a bear that got into cocaine accidentally dropped into a forest by drug smugglers.";
        barbie.story = "A meta-comedy that follows Barbie as she leaves the perfect Barbie world to explore the not-so-perfect real world.";
        oppenheimer.story = "A historical frama the displays the life of J. Robert Oppenheimer, and his role in developing the atomic bomb.";
        theCrazies.story = "A horror movie about a small town thrown into chaos when a mysterious toxin contaminates the water supply.";
        worldWarZ.story = "An action-horror film that follows a former UN investigator racing against time to find a solution to a global zombie pandemic threatening to wipe out humanity.";
        jujutsuKaisen.story = "A dark fantasy anime film that follows Yuta Okkotsu, a young man haunted by a powerful cursed spirit.";

        // Add all movies to the posterList
        posterList.add(minecraftMovie);
        posterList.add(menInBlack);
        posterList.add(beetlejuice);
        posterList.add(beetlejuiceTwo);
        posterList.add(cocaineBear);
        posterList.add(barbie);
        posterList.add(oppenheimer);
        posterList.add(theCrazies);
        posterList.add(worldWarZ);
        posterList.add(jujutsuKaisen);

        final PosterAdapter posterAdapter = new PosterAdapter(posterList, this);
        postersRecyclerView.setAdapter(posterAdapter);

        buttonAddToWatchlist.setOnClickListener(new View.OnClickListener() {
            /**
             * Handles the click event for adding selected posters to the watchlist.
             * Displays a Toast message with the names of the selected posters.
             *
             * @param v the view that was clicked.
             */
            @Override
            public void onClick(View v) {
                List<Poster> selectPosters = posterAdapter.getSelectedPoster();

                StringBuilder posterNames = new StringBuilder();
                for (int i = 0; i < selectPosters.size(); i++) {
                    if (i==0) {
                        posterNames.append(selectPosters.get(i).name);
                    } else {
                        posterNames.append("\n").append(selectPosters.get(i).name);
                    }
                }
                Toast.makeText(MainActivity.this, posterNames.toString(), Toast.LENGTH_SHORT).show();
            }

        });

    }

    /**
     * Updates the visibility of the "Add to Watchlist" button based on whether a poster is selected.
     *
     * @param isSelected true if at least one poster is selected, false otherwise.
     */
    @Override
    public void onPosterAction(Boolean isSelected) {
        if (isSelected) {
            buttonAddToWatchlist.setVisibility(View.VISIBLE);
        } else {
            buttonAddToWatchlist.setVisibility(View.GONE);
        }
    }
}