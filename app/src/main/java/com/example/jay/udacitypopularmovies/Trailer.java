
package com.example.jay.udacitypopularmovies;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Trailer {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("resultTrailers")
    @Expose
    private List<ResultTrailer> resultTrailers = new ArrayList<ResultTrailer>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public Trailer() {
    }

    /**
     * 
     * @param id
     * @param resultTrailers
     */
    public Trailer(Integer id, List<ResultTrailer> resultTrailers) {
        this.id = id;
        this.resultTrailers = resultTrailers;
    }

    /**
     * 
     * @return
     *     The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    public Trailer withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The resultTrailers
     */
    public List<ResultTrailer> getResultTrailers() {
        return resultTrailers;
    }

    /**
     * 
     * @param resultTrailers
     *     The resultTrailers
     */
    public void setResultTrailers(List<ResultTrailer> resultTrailers) {
        this.resultTrailers = resultTrailers;
    }

    public Trailer withResults(List<ResultTrailer> resultTrailers) {
        this.resultTrailers = resultTrailers;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
