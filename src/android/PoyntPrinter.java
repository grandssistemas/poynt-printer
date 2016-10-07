package br.com.grands.cordova.plugin;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.os.IBinder;
import co.poynt.os.services.v1.IPoyntReceiptPrintingService;
import co.poynt.os.services.v1.IPoyntReceiptPrintingServiceListener;
import android.util.Log;
import co.poynt.os.model.PrintedReceipt;

/**
 * This class echoes a string called from JavaScript.
 */
public class PoyntPrinter extends CordovaPlugin {

    private static final String TAG = "PoyntPrinter";
    private static final String PRINT = "print";
    private CallbackContext callbackContext;
    private JSONArray executeArgs;
    private IPoyntReceiptPrintingService printingService;
    private IPoyntReceiptPrintingServiceListener printingServiceListener;
    private Intent serviceIntent = null;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        this.callbackContext = callbackContext;
        this.executeArgs = args;
        JSONObject arg_object = args.getJSONObject(0);
        if (action.equals(PRINT)) {
            try {
                // String printId = arg_object.getString("printId");
                // String photo = arg_object.getString("image");
                // byte[] bytes = Base64.decode(photo, Base64.DEFAULT);
                // Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                // printingService.printBitmap(printId, bitmap, printingServiceListener);
                PrintedReceipt te = new PrintedReceipt();
                te.setBusinessName("Grands Sistemas");
                printingService.printReceipt("text", te, printingServiceListener);
                this.callbackContext.success();
            } catch (Exception e) {
                this.callbackContext.error(e.getMessage());
            }
        }
        return true;
    }

    @Override
    public void initialize(CordovaInterface cordova, CordovaWebView webView) {
        super.initialize(cordova, webView);
        Activity context = cordova.getActivity().getApplicationContext();
        serviceIntent = new Intent(context, IPoyntReceiptPrintingService.class);
        context.startService(serviceIntent);
        context.bindService(serviceIntent, printServiceConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection printServiceConnection = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            printingService = IPoyntReceiptPrintingService.Stub.asInterface(iBinder);
        }
        public void onServiceDisconnected(ComponentName componentName) {
            printingService = null;
        }
    };
}
