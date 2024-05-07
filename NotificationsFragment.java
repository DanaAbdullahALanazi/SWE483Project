package com.example.settingactivity;

import android.os.Bundle;

import androidx.preference.CheckBoxPreference;
import androidx.preference.MultiSelectListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import android.content.SharedPreferences;

import android.text.TextUtils; // To check for empty selected prayers

import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;

import androidx.preference.SeekBarPreference;

import java.util.Set;

public class NotificationsFragment extends PreferenceFragmentCompat {
    private SharedPreferences sharedPreferences; // SharedPreferences instance
    private static final String KEY_SELECTED_PRAYERS = "selected_prayers";

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resource file specific to "General" settings
        setPreferencesFromResource(R.xml.notifications_prefs, rootKey);

        sharedPreferences = getPreferenceManager().getSharedPreferences();



        EditTextPreference minutesBeforeAthan = findPreference("minutes before athan");
        ListPreference reminderType = findPreference("reminder_type");
        SeekBarPreference volumePrayer = findPreference("volume_prayer");
        ListPreference reminderTypePrayer = findPreference("reminder_type_prayer");
        CheckBoxPreference keepRingingSilentMode = findPreference("keep_ringing_in_silent_mode");

        // Save preferences only if prayers are selected
        String selectedPrayers = sharedPreferences.getString(KEY_SELECTED_PRAYERS, "");
        if (!TextUtils.isEmpty(selectedPrayers)) {
            if (minutesBeforeAthan != null) {
                minutesBeforeAthan.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String minutes = (String) newValue;
                        sharedPreferences.edit().putString("minutes_before_athan_" + selectedPrayers, minutes).apply(); // Save minutes with prayer prefix
                        return true;
                    }
                });
            }

            if (reminderType != null) {
                reminderType.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String type = (String) newValue;
                        sharedPreferences.edit().putString("reminder_type_" + selectedPrayers, type).apply(); // Save reminder type with prayer prefix
                        return true;
                    }
                });
            }

            if (volumePrayer != null) {
                volumePrayer.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        int volume = (int) newValue;
                        sharedPreferences.edit().putInt("volume_prayer_" + selectedPrayers, volume).apply(); // Save volume with prayer prefix
                        return true;
                    }
                });
            }
            if (reminderTypePrayer != null) {
                volumePrayer.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        String type = (String) newValue;
                        sharedPreferences.edit().putString("reminder_type_prayer_" + selectedPrayers, type).apply(); // Save volume with prayer prefix
                        return true;
                    }
                });
            }



            if (keepRingingSilentMode != null) {
                keepRingingSilentMode.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                    @Override
                    public boolean onPreferenceChange(Preference preference, Object newValue) {
                        boolean keepRinging = (boolean) newValue;
                        sharedPreferences.edit().putBoolean("keep_ringing_in_silent_mode_" + selectedPrayers, keepRinging).apply(); // Save keep ringing setting with prayer prefix
                        return true;
                    }
                });
            }
        }


    }
}
