// Copyright 2016 Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.proyecto26.inappbrowser;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.browser.customtabs.CustomTabsSession;

import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.facebook.react.bridge.Promise;

import java.lang.ref.WeakReference;

public class BottomBarManager extends BroadcastReceiver {
    private static final String TAG = "check";
    private static WeakReference<MediaPlayer> sMediaPlayerWeakRef;

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent myIntent = new Intent(context, ChromeTabsManagerActivity.class);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(myIntent);
        CustomTabsSession session = SessionHelper.getCurrentSession();
        if (session == null) return;
    }

    public static RemoteViews createRemoteViews(Context context, boolean showPlayIcon) {
        @SuppressLint("RemoteViewLayout") RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.remote_view);
        return remoteViews;
    }

    public static int[] getClickableIDs() {
        return new int[]{R.id.btn_finish};
    }

    public static PendingIntent getOnClickPendingIntent(Context context) {
        Intent broadcastIntent = new Intent(context, BottomBarManager.class);
        broadcastIntent.putExtra(CustomTabsIntent.EXTRA_REMOTEVIEWS_CLICKED_ID, getClickableIDs()[0]);
        return PendingIntent.getBroadcast(context, 0, broadcastIntent, 0 | PendingIntent.FLAG_IMMUTABLE);
    }

}
