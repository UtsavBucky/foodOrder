package com.utsavbucky.onebanc.customlisteners;

import org.json.JSONArray;

public interface JsonArrayListener {
    public void onGettingResponse(JSONArray response, int requestCode);
}
