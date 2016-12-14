package org.muferobotics.olympics.ui.fragment;

import android.support.v4.app.Fragment;

import org.muferobotics.olympics.core.App;
import org.muferobotics.olympics.rest.MUFEClient;

public class BaseFragment extends Fragment {
    App getApp() {
        return (App) this.getActivity().getApplication();
    }

    MUFEClient getClient() {
        return getApp().getMufeClient();
    }
}
