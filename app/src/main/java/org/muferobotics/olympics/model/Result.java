package org.muferobotics.olympics.model;

import com.google.gson.annotations.SerializedName;

import org.muferobotics.olympics.R;
import org.parceler.Parcel;

@Parcel
public class Result {

    @SerializedName("time")
    Integer time;
    @SerializedName("points")
    Integer points;
    @SerializedName("disqualified")
    Integer disqualified;
    @SerializedName("name")
    String name;
    @SerializedName("round")
    Integer round;
    @SerializedName("winner")
    String winner;
    @SerializedName("defeated")
    String defeated;

    Integer categoryName;

    public Result() {
    }

    public Integer getRound() {
        return round;
    }

    public void setRound(Integer round) {
        this.round = round;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getDefeated() {
        return defeated;
    }

    public void setDefeated(String defeated) {
        this.defeated = defeated;
    }

    public String getName() {
        return name;
    }

    public void setName(String robotName) {
        this.name = robotName;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public Integer getDisqualified() {
        return disqualified;
    }

    public void setDisqualified(Integer disqualified) {
        this.disqualified = disqualified;
    }
}