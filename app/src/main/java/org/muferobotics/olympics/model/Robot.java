package org.muferobotics.olympics.model;

import com.google.gson.annotations.SerializedName;

import org.muferobotics.olympics.R;

public class Robot {
    @SerializedName("name")
    String robotName;

    @SerializedName("category_id")
    Integer categoryID;

    Integer categoryName;

    private int aThumbnail;

    public Robot(){

    }

    public String getName() {
        return robotName;
    }

    public void setName(String robotName) {
        this.robotName = robotName;
    }

    public Integer getCategory() {
        switch (categoryID)
        {
            case 0: categoryName = R.string.robot_istanbul; break;
            case 1: categoryName = R.string.robot_fire; break;
            case 2: categoryName = R.string.robot_tenis; break;
            case 3: categoryName = R.string.robot_color; break;
            case 4: categoryName = R.string.robot_sumo; break;
            case 5: categoryName = R.string.robot_flag; break;
            case 6: categoryName = R.string.robot_judges; break;
            case 7: categoryName = R.string.robot_free; break;
        }
        return categoryName;
    }

    public void setCategory(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public Integer getThumbnail() {
        switch (this.categoryID)
        {
            case 0: aThumbnail = R.drawable.istanbul_b; break;
            case 1: aThumbnail = R.drawable.fire_b; break;
            case 2: aThumbnail = R.drawable.ball_b; break;
            case 3: aThumbnail = R.drawable.color_b; break;
            case 4: aThumbnail = R.drawable.sumo_b; break;
            case 5: aThumbnail = R.drawable.flag_b; break;
            case 6: aThumbnail = R.drawable.juri_b; break;
            case 7: aThumbnail = R.drawable.free_b; break;
        }
        return aThumbnail;
    }
}


