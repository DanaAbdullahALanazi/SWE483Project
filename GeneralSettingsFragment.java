package com.example.settingactivity;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

public class GeneralSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resource file specific to "General" settings
        setPreferencesFromResource(R.xml.general_preferences, rootKey);
    }
}
