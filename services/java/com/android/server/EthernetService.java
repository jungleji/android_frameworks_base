package com.android.server;

import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.BroadcastReceiver;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.net.ethernet.IEthernetManager;
import android.net.ethernet.EthernetDataTracker;
import android.net.NetworkStateTracker;
import android.net.DhcpInfo;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;
import android.os.Process;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.Log;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.io.FileDescriptor;
import java.io.PrintWriter;

import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.android.internal.app.IBatteryStats;
import com.android.server.am.BatteryStatsService;

public class EthernetService extends IEthernetManager.Stub {

    private static final String TAG = "EthernetService";
    private static final boolean DEBUG = false;

    private static void LOG(String msg) {
        if ( DEBUG ) {
            Log.d(TAG, msg);
        }
    }

    private Context mContext;

    private final EthernetDataTracker mEthernetDataTracker;

    EthernetService(Context context) {

        LOG("EthernetService() : Entered.");

        mContext = context;
        mEthernetDataTracker = EthernetDataTracker.getInstance();
        LOG("EthernetService() : mEthernetDataTracker = " + mEthernetDataTracker);
    }

    public int getEthernetConnectState() {
        // enforceAccessPermission();
        LOG("getEthernetEnabledState() : Entered.");
        return mEthernetDataTracker.ethCurrentState;
    }


    public int getEthernetIfaceState() {
        // enforceAccessPermission();
        LOG("getEthernetIfaceState()");
        return mEthernetDataTracker.getEthernetIfaceState();
    }

    /*
      0: no carrier (RJ45 unplug)
      1: carrier exist (RJ45 plugin)
    */
    public int getEthernetCarrierState() {
        LOG("getEthernetCarrierState()");
        return mEthernetDataTracker.getEthernetCarrierState();
    }

    public boolean setEthernetEnabled(boolean enable) {
        // enforceChangePermission();

        LOG("setEthernetEnabled() : enable="+enable);
        if ( enable ) {
            mEthernetDataTracker.enableEthIface();
        } else {
            mEthernetDataTracker.disableEthIface();
        }

        return true;
    }

    public String getEthernetIfaceName() {
        return mEthernetDataTracker.getEthIfaceName();
    }

    public String getEthernetHwaddr(String iface) {
        return mEthernetDataTracker.getEthernetHwaddr(iface);
    }
}
