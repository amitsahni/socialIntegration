package fbconnect.callback;

import com.facebook.FacebookException;

/**
 * A fbconnect.callback class for the Facebook SDK.
 */
public interface OnFBCallback<RESULT, ERROR> {

    /**
     * Called when the dialog completes without error.
     * <p/>
     * Note: This will be called instead of {@link #onCancel()} if any of the following conditions
     * are true.
     * <ul>
     * <li>
     * {@link com.facebook.share.widget.MessageDialog} is used.
     * </li>
     * <li>
     * The logged in Facebook user has not authorized the app that has initiated the dialog.
     * </li>
     * </ul>
     *
     * @param result Result from the dialog
     */
    public void onSuccess(RESULT result);

    /**
     * Called when the dialog is canceled.
     * <p/>
     * Note: {@link #onSuccess(RESULT)} will be called instead if any of the following conditions
     * are true.
     * <ul>
     * <li>
     * {@link com.facebook.share.widget.MessageDialog} is used.
     * </li>
     * <li>
     * The logged in Facebook user has not authorized the app that has initiated the dialog.
     * </li>
     * </ul>
     */
    public void onCancel();

    /**
     * Called when the dialog finishes with an error.
     *
     * @param error The error that occurred
     */
    public void onError(ERROR error);
}
