package com.onestopinteractive.promocion;

import com.android.volley.VolleyError;

/**
 * Created by Stephen Drew on 6/20/2015.
 */
public interface SyncListener {
    public void syncStarted(int count);
    public void syncProgress(int count);
    public void syncCompleted();
    public void syncEndedWithError(VolleyError error);
}
