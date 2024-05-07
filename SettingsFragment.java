package com.example.settingactivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;

import androidx.annotation.Nullable;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener {

    private SharedPreferences sharedPreferences;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.root_preferences);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        sharedPreferences.registerOnSharedPreferenceChangeListener(this);
        loadPreferences();




        Preference generalPreference = findPreference("general");

        // Set an onClickListener for the "General" preference
        generalPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Replace the container with the fragment you want to navigate to
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new GeneralSettingsFragment()) // Replace with your desired fragment
                        .addToBackStack(null) // Optional: Add to back stack for navigation
                        .commit();
                return true;
            }
        });


        Preference notificationsPreference = findPreference("notifications");

        // Set an onClickListener for the "General" preference
        notificationsPreference.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
            @Override
            public boolean onPreferenceClick(Preference preference) {
                // Replace the container with the fragment you want to navigate to
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.container, new NotificationsFragment()) // Replace with your desired fragment
                        .addToBackStack(null) // Optional: Add to back stack for navigation
                        .commit();
                return true;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        // Update SharedPreferences when preferences change
        if (key.equals("language_preference") || key.equals("hours_format_preference")) {
            String value = sharedPreferences.getString(key, "");
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    private void loadPreferences() {
        // Load saved preferences
        String language = sharedPreferences.getString("language_preference", "");
        String hoursFormat = sharedPreferences.getString("hours_format_preference", "");
        // Use the preferences as needed
    }
}
