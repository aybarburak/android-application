package org.muferobotics.olympics.model;

import org.muferobotics.olympics.R;

public class Schedule {
    String time;
    String title;
    Integer description;
    private int aThumbnail;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(Integer description) {
        this.description = description;
    }

    public Integer getDescription() {
        switch (description)
        {
            case 0: description = R.string.activity_maps_uzumcu; break;
            case 1: description = R.string.activity_maps_sport; break;
            case 2: description = R.string.nul; break;
        }
        return description;
    }

    public void setThumbnail(Integer aThumb) {
        this.aThumbnail = aThumb;
    }

    public Integer getThumbnail() {
        switch (aThumbnail)
        {
            case 0: aThumbnail = R.drawable.istanbul_b; break;
            case 1: aThumbnail = R.drawable.fire_b; break;
            case 2: aThumbnail = R.drawable.ball_b; break;
            case 3: aThumbnail = R.drawable.color_b; break;
            case 4: aThumbnail = R.drawable.sumo_b; break;
            case 5: aThumbnail = R.drawable.flag_b; break;
            case 6: aThumbnail = R.drawable.juri_b; break;
            case 7: aThumbnail = R.drawable.free_b; break;
            case 8: aThumbnail = R.drawable.conference; break;
            case 9: aThumbnail = R.drawable.time; break;
            case 10: aThumbnail = R.drawable.prize; break;
            case 11: aThumbnail = R.drawable.ribbon; break;
        }
        return aThumbnail;
    }
}
