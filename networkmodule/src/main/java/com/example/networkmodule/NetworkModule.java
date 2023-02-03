package com.example.networkmodule;

import org.json.JSONObject;

public interface NetworkModule {
    JSONObject fetchCustomUI (String URL);
    byte[] fetchLogo (String URL);
}
