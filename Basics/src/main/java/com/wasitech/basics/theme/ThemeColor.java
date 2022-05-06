package com.wasitech.basics.theme;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;

import androidx.core.app.ActivityCompat;

import com.wasitech.basics.R;
import com.wasitech.basics.app.BaseApp;

import static com.wasitech.basics.theme.Theme.CUSTOM_THEME;
import static com.wasitech.basics.theme.Theme.DARK_THEME;
import static com.wasitech.basics.theme.Theme.LIGHT_THEME;
import static com.wasitech.basics.theme.Theme.getCur;

public class ThemeColor {

    public static ColorStateList getDefaultColorList() {
        return ColorStateList.valueOf(getDefaultColors());
    }

    public static ColorStateList getDefaultOppositeColorList() {
        return ColorStateList.valueOf(getDefaultOppositeColors());
    }

    public static int getDefaultColors() {
        switch (getCur()) {
            default:
            case LIGHT_THEME: {
                return Color.BLACK;
            }
            case DARK_THEME: {
                return Color.WHITE;
            }
            case CUSTOM_THEME: {
                return Color.parseColor("#123231");
            }
        }
    }

    public static int getDefaultOppositeColors() {
        switch (getCur()) {
            default:
            case LIGHT_THEME: {
                return Color.WHITE;
            }
            case DARK_THEME: {
                return (Color.BLACK);
            }
            case CUSTOM_THEME: {
                return (Color.parseColor("#f2f231"));
            }
        }
    }

    public static int getActivityTints(Context context) {
        switch (getCur()) {
            default:
            case LIGHT_THEME: {
                return (ActivityCompat.getColor(context, R.color.bg_tint_light));
            }
            case DARK_THEME: {
                return (ActivityCompat.getColor(context, R.color.bg_tint_dark));
            }
            case CUSTOM_THEME: {
                return (Color.parseColor("#123231"));
            }
        }
    }

    public static int getActivityTopBottomColors() {
        switch (getCur()) {
            default:
            case LIGHT_THEME: {
                return (Color.parseColor("#BD000000"));
            }
            case DARK_THEME: {
                return (Color.parseColor("#F0000000"));
            }
            case CUSTOM_THEME: {
                return (Color.parseColor("#FD000000"));
            }
        }
    }

    public static class Text {
        private static final String TEXT_COLOR_DEFAULT = "txt_default";
        private static final String TEXT_COLOR_TINT = "txt_tint";
        private static final String TEXT_COLOR_HINT = "txt_hint";

        public static int getDefaultColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.BLACK;
                }
                case DARK_THEME: {
                    return Color.WHITE;
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(TEXT_COLOR_DEFAULT, "#FFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getTintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#B0000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#BEFFFFFF");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(TEXT_COLOR_TINT, "#BEFFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getHintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#DD000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#DDFFFFFF");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(TEXT_COLOR_HINT, "#DDFFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static ColorStateList getHintColorList() {
            return ColorStateList.valueOf(getHintColors());
        }

        public static ColorStateList getTintColorList() {
            return ColorStateList.valueOf(getTintColors());
        }

        public static ColorStateList getDefaultColorList() {
            return ColorStateList.valueOf(getDefaultColors());
        }

        public static void setDefaultColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(TEXT_COLOR_DEFAULT, s).apply();
        }

        public static void setTintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(TEXT_COLOR_TINT, s).apply();
        }

        public static void setHintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(TEXT_COLOR_HINT, s).apply();
        }
    }

    public static class Switch {
        private static final String SWT_TINT_COLOR_TRACK = "swt_track";
        private static final String SWT_TINT_COLOR_THUMB = "swt_thumb";
        private static final String SWT_DIM_COLOR_TRACK = "swt_d_track";
        private static final String SWT_DIM_COLOR_THUMB = "swt_d_thumb";

        public static int getTrackTintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#66000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#66FDFDFD");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(SWT_TINT_COLOR_TRACK, "#66FFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getThumbTintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#1C1C1C");
                }
                case DARK_THEME: {
                    return Color.parseColor("#E6FDFDFD");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(SWT_TINT_COLOR_THUMB, "#66FFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getTrackTintDimColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#66000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#66FDFDFD");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(SWT_DIM_COLOR_TRACK, "#66FFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getThumbTintDimColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#59565A");
                }
                case DARK_THEME: {
                    return Color.parseColor("#A9A7AA");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(SWT_DIM_COLOR_THUMB, "#66FFFFFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static ColorStateList getTrackTintColorList() {
            return ColorStateList.valueOf(getTrackTintColors());
        }

        public static ColorStateList getThumbTintColorList() {
            return ColorStateList.valueOf(getThumbTintColors());
        }


        public static ColorStateList getTrackTintDimColorList() {
            return ColorStateList.valueOf(getTrackTintDimColors());
        }

        public static ColorStateList getThumbTintDimColorList() {
            return ColorStateList.valueOf(getThumbTintDimColors());
        }

        public static void setTrackTintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(SWT_TINT_COLOR_TRACK, s).apply();
        }

        public static void setThumbTintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(SWT_TINT_COLOR_THUMB, s).apply();
        }

        public static void setTrackTintDimColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(SWT_DIM_COLOR_TRACK, s).apply();
        }

        public static void setThumbTintDimColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(SWT_DIM_COLOR_THUMB, s).apply();
        }

    }

    public static class Image {
        private static final String IMAGE_COLOR_DEFAULT = "img_default";
        private static final String IMAGE_COLOR_TINT = "img_tint";
        private static final String IMAGE_COLOR_HINT = "img_hint";

        public static int getDefaultColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.BLACK;
                }
                case DARK_THEME: {
                    return Color.WHITE;
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(IMAGE_COLOR_DEFAULT, "#FFDEFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getTintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#B0000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#BEFFFFFF");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(IMAGE_COLOR_TINT, "#BEFFDEFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static int getHintColors() {
            switch (getCur()) {
                default:
                case LIGHT_THEME: {
                    return Color.parseColor("#DD000000");
                }
                case DARK_THEME: {
                    return Color.parseColor("#DDFFFFFF");
                }
                case CUSTOM_THEME: {
                    String val = BaseApp.getPref().getString(IMAGE_COLOR_HINT, "#DDFFDEFF");
                    return Color.parseColor(val);
                }
            }
        }

        public static ColorStateList getHintColorList() {
            return ColorStateList.valueOf(getHintColors());
        }

        public static ColorStateList getTintColorList() {
            return ColorStateList.valueOf(getTintColors());
        }

        public static ColorStateList getDefaultColorList() {
            return ColorStateList.valueOf(getDefaultColors());
        }

        public static void setDefaultColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(IMAGE_COLOR_DEFAULT, s).apply();
        }

        public static void setTintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(IMAGE_COLOR_TINT, s).apply();
        }

        public static void setHintColors(String s) {
            if (s == null || s.isEmpty() || s.length() < 6)
                return;
            BaseApp.getPref().edit().putString(IMAGE_COLOR_HINT, s).apply();
        }

    }


}
