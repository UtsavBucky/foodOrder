package com.utsavbucky.onebanc.customlisteners;

import org.json.JSONObject;

public interface JsonObjectListener {
    public void onGettingResponse(JSONObject response, int requestCode);
}
