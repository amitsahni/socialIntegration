package fbconnect.callback;

/**
 * Created by amit on 4/2/17.
 */

public abstract class FBCallback<RESULT, ERROR> implements OnFBCallback<RESULT, ERROR> {

    @Override
    public void onSuccess(RESULT result) {

    }

    @Override
    public void onCancel() {

    }

    @Override
    public void onError(ERROR error) {

    }
}
