package com.utsavbucky.onebanc.customlisteners;

import org.json.JSONObject;

public interface Authlistener{
    public void onAuthResponse(JSONObject response, int requestcode);
}
