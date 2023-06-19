package com.example.googleonetaplogin

import android.content.Context

class CommonUtils {

    fun calculateLayout(context: Context, list: ArrayList<String>) {
        /*
        float tmpTextWidth;
        float totalWidth = 0;

        for (int i = 0; i < tmpList.size(); i++) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = layoutInflater.inflate(R.layout.item_genre_list, null);
            TextView tvGenre = view.findViewById(R.id.tv_genre);

            tmpTextWidth = tvGenre.getPaint().measureText(tmpList.get(i).genre);
            totalWidth += tmpTextWidth;
        }

        return totalWidth;
        public int convertPxToDp(Context context, float pixels) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        return (int) (pixels / ((float) displayMetrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT));
        }
        */
    }

}