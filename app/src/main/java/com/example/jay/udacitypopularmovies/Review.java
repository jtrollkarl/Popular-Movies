
package com.example.jay.udacitypopularmovies;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Generated("org.jsonschema2pojo")
public class Review {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("resultReviewses")
    @Expose
    private List<ResultReviews> resultReviewses = new ArrayList<ResultReviews>();
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Review() {
    }

    /**
     * 
     * @param id
     * @param resultReviewses
     * @param totalResults
     * @param page
     * @param totalPages
     */
    public Review(Integer id, Integer page, List<ResultReviews> resultReviewses, Integer totalPages, Integer totalResults) {
        this.id = id;
        this.page = page;
        this.resultReviewses = resultReviewses;
        this.totalPages = totalPages;
        this.totalResults = totalResults;
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

    public Review withId(Integer id) {
        this.id = id;
        return this;
    }

    /**
     * 
     * @return
     *     The page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * 
     * @param page
     *     The page
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    public Review withPage(Integer page) {
        this.page = page;
        return this;
    }

    /**
     * 
     * @return
     *     The resultReviewses
     */
    public List<ResultReviews> getResult() {
        return resultReviewses;
    }

    /**
     * 
     * @param resultReviewses
     *     The resultReviewses
     */
    public void setResultReviewses(List<ResultReviews> resultReviewses) {
        this.resultReviewses = resultReviewses;
    }

    public Review withResults(List<ResultReviews> resultReviewses) {
        this.resultReviewses = resultReviewses;
        return this;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Integer getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Review withTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * 
     * @return
     *     The totalResults
     */
    public Integer getTotalResults() {
        return totalResults;
    }

    /**
     * 
     * @param totalResults
     *     The total_results
     */
    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Review withTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
        return this;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}
