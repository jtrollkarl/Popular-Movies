
package com.example.jay.udacitypopularmovies;

import android.net.Uri;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.provider.ContentUri;
import com.raizlabs.android.dbflow.annotation.provider.TableEndpoint;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.Model;

import javax.annotation.Generated;

@TableEndpoint(name = Movie.ENDPOINT, contentProvider = ContentProvider.class)
@Table(database = PopularMoviesDatabase.class, cachingEnabled = true)
@Generated("org.jsonschema2pojo")
public class Movie extends BaseModel {

    public static final String ENDPOINT = "Movie";
    @ContentUri(path = ENDPOINT, type = ContentUri.ContentType.VND_MULTIPLE + ENDPOINT)
    public static final Uri CONTENT_URI = Uri.parse(ContentProvider.BASE_CONTENT_URI + ContentProvider.AUTHORITY
            + "/" + ENDPOINT);
    public static final String NAME = "Movie";

    @PrimaryKey(autoincrement = true)
    @SerializedName("id")
    @Expose
    private long id;

    @Column
    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @Column
    @SerializedName("adult")
    @Expose
    private boolean adult;

    @Column
    @SerializedName("overview")
    @Expose
    private String overview;

    @Column
    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("genre_ids")
    @Expose
    private transient List<Long> genreIds = new ArrayList<Long>();

    @Column
    @SerializedName("original_title")
    @Expose
    private String originalTitle;

    @Column
    @SerializedName("original_language")
    @Expose
    private String originalLanguage;

    @Column
    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("backdrop_path")
    @Expose
    private transient String backdropPath;

    @Column
    @SerializedName("popularity")
    @Expose
    private double popularity;

    @Column
    @SerializedName("vote_count")
    @Expose
    private long voteCount;

    @Column
    @SerializedName("video")
    @Expose
    private boolean video;

    @Column
    @SerializedName("vote_average")
    @Expose
    private double voteAverage;

    /**
     * 
     * @return
     *     The posterPath
     */
    public String getPosterPath() {
        return posterPath;
    }

    /**
     * 
     * @param posterPath
     *     The poster_path
     */
    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    /**
     * 
     * @return
     *     The adult
     */
    public boolean isAdult() {
        return adult;
    }

    /**
     * 
     * @param adult
     *     The adult
     */
    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    /**
     * 
     * @return
     *     The overview
     */
    public String getOverview() {
        return overview;
    }

    /**
     * 
     * @param overview
     *     The overview
     */
    public void setOverview(String overview) {
        this.overview = overview;
    }

    /**
     * 
     * @return
     *     The releaseDate
     */
    public String getReleaseDate() {
        return releaseDate;
    }

    /**
     * 
     * @param releaseDate
     *     The release_date
     */
    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    /**
     * 
     * @return
     *     The genreIds
     */
    public List<Long> getGenreIds() {
        return genreIds;
    }

    /**
     * 
     * @param genreIds
     *     The genre_ids
     */
    public void setGenreIds(List<Long> genreIds) {
        this.genreIds = genreIds;
    }

    /**
     * 
     * @return
     *     The id
     */
    public long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The originalTitle
     */
    public String getOriginalTitle() {
        return originalTitle;
    }

    /**
     * 
     * @param originalTitle
     *     The original_title
     */
    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    /**
     * 
     * @return
     *     The originalLanguage
     */
    public String getOriginalLanguage() {
        return originalLanguage;
    }

    /**
     * 
     * @param originalLanguage
     *     The original_language
     */
    public void setOriginalLanguage(String originalLanguage) {
        this.originalLanguage = originalLanguage;
    }

    /**
     * 
     * @return
     *     The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The backdropPath
     */
    public String getBackdropPath() {
        return backdropPath;
    }

    /**
     * 
     * @param backdropPath
     *     The backdrop_path
     */
    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    /**
     * 
     * @return
     *     The popularity
     */
    public double getPopularity() {
        return popularity;
    }

    /**
     * 
     * @param popularity
     *     The popularity
     */
    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    /**
     * 
     * @return
     *     The voteCount
     */
    public long getVoteCount() {
        return voteCount;
    }

    /**
     * 
     * @param voteCount
     *     The vote_count
     */
    public void setVoteCount(long voteCount) {
        this.voteCount = voteCount;
    }

    /**
     * 
     * @return
     *     The video
     */
    public boolean isVideo() {
        return video;
    }

    /**
     * 
     * @param video
     *     The video
     */
    public void setVideo(boolean video) {
        this.video = video;
    }

    /**
     * 
     * @return
     *     The voteAverage
     */
    public double getVoteAverage() {
        return voteAverage;
    }

    /**
     * 
     * @param voteAverage
     *     The vote_average
     */
    public void setVoteAverage(double voteAverage) {
        this.voteAverage = voteAverage;
    }

}
