package dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;

import com.dingmouren.commonlib.R;

/**
 * Created by dingmouren on 2018/8/2.
 */

public class NetStateChangedDialog extends Dialog {
    public NetStateChangedDialog(@NonNull Context context) {
        super(context);
        View rootView = LayoutInflater.from(context).inflate(R.layout.dialog_network_state_tip,null);
        super.setContentView(rootView);

    }

    
}
