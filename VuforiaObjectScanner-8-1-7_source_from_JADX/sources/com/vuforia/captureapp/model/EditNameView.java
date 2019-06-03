package com.vuforia.captureapp.model;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.vuforia.captureapp.BuildConfig;
import com.vuforia.captureapp.C0026R;
import java.util.List;

public class EditNameView extends EditText {
    private int MAX_NAME_LENGTH = 64;
    List<String> mCaptureNameList;
    private Dialog mContainingDialog;
    private Context mContext;
    private EditNameView mEditNameView;
    private ImageView mEditTextImage;
    private TextView mErrorTextView;
    private Animation mFadeOutAnim;
    private boolean mShiftPressed = false;

    /* renamed from: com.vuforia.captureapp.model.EditNameView$1 */
    class C00501 implements OnKeyListener {
        C00501() {
        }

        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if ((i < 7 || i > 16 || EditNameView.this.mShiftPressed) && ((i < 29 || i > 54) && !(i == 69 && EditNameView.this.mShiftPressed))) {
                if (!(i == 66 || i == 4)) {
                    if (i != 0) {
                        if (i == 67) {
                            if (keyEvent.getAction() == 1) {
                                i = EditNameView.this.mEditNameView.getSelectionStart();
                                if (i > 0) {
                                    keyEvent = EditNameView.this.mEditNameView.getText().toString();
                                    String str = BuildConfig.FLAVOR;
                                    if (keyEvent.length() > i) {
                                        str = keyEvent.substring(i);
                                    }
                                    StringBuilder stringBuilder = new StringBuilder();
                                    i--;
                                    stringBuilder.append(keyEvent.substring(0, i));
                                    stringBuilder.append(str);
                                    EditNameView.this.mEditNameView.setText(stringBuilder.toString());
                                    EditNameView.this.mEditNameView.setSelection(i);
                                }
                            }
                            EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_image);
                            EditNameView.this.mErrorTextView.setText(BuildConfig.FLAVOR);
                            return true;
                        } else if (i == 59) {
                            if (keyEvent.getAction() == null) {
                                EditNameView.this.mShiftPressed = true;
                            } else if (keyEvent.getAction() == 1) {
                                EditNameView.this.mShiftPressed = false;
                            }
                            return false;
                        } else {
                            EditNameView.this.mErrorTextView.setText(EditNameView.this.getResources().getString(C0026R.string.edit_text_error_character));
                            EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_error_image);
                            return true;
                        }
                    }
                }
                if (i == 4 && keyEvent.getAction() == 1) {
                    EditNameView.this.mContainingDialog.dismiss();
                }
                if (i == 66) {
                    view = EditNameView.this.mEditNameView.getText().toString();
                    for (i = 0; i < view.length(); i++) {
                        keyEvent = view.charAt(i);
                        if ((keyEvent < 97 || keyEvent > 122) && ((keyEvent < 65 || keyEvent > 90) && ((keyEvent < 48 || keyEvent > 57) && keyEvent != 95))) {
                            EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_error_image);
                            EditNameView.this.mErrorTextView.setText(EditNameView.this.getResources().getString(C0026R.string.edit_text_error_character));
                            return true;
                        }
                    }
                    if (view == BuildConfig.FLAVOR || EditNameView.this.mCaptureNameList.contains(view) != null) {
                        EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_error_image);
                        EditNameView.this.mErrorTextView.setText(EditNameView.this.getResources().getString(C0026R.string.edit_text_error_duplicate_name));
                        return true;
                    }
                }
                return false;
            } else if (EditNameView.this.mEditNameView.getText().toString().length() >= EditNameView.this.MAX_NAME_LENGTH) {
                EditNameView.this.mErrorTextView.setText(EditNameView.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_error_image);
                return true;
            } else {
                EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_image);
                EditNameView.this.mErrorTextView.setText(BuildConfig.FLAVOR);
                return false;
            }
        }
    }

    public EditNameView(Context context) {
        super(context);
        this.mContext = context;
        this.mEditNameView = this;
    }

    public EditNameView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mContext = context;
        this.mEditNameView = this;
    }

    public void init(Dialog dialog, TextView textView, ImageView imageView, List<String> list) {
        this.mContainingDialog = dialog;
        this.mErrorTextView = textView;
        this.mEditTextImage = imageView;
        this.mCaptureNameList = list;
        this.mFadeOutAnim = AnimationUtils.loadAnimation(this.mContext, C0026R.anim.error_text_animation);
        this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_image);
        this.mContainingDialog.setCanceledOnTouchOutside(null);
        setOnKeyListener(new C00501());
    }

    public InputConnection onCreateInputConnection(EditorInfo editorInfo) {
        editorInfo.actionLabel = null;
        editorInfo.inputType = 1;
        editorInfo.imeOptions = 268435462;
        editorInfo.actionLabel = "OK";
        editorInfo = new BaseInputConnection(this, false) {
            public boolean setComposingText(CharSequence charSequence, int i) {
                if (charSequence.length() + EditNameView.this.mEditNameView.getText().length() > EditNameView.this.MAX_NAME_LENGTH) {
                    charSequence = charSequence.subSequence(0, EditNameView.this.MAX_NAME_LENGTH - EditNameView.this.mEditNameView.getText().length());
                    EditNameView.this.mErrorTextView.setText(EditNameView.this.getResources().getString(C0026R.string.edit_text_error_max_length));
                    EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_error_image);
                }
                if (EditNameView.this.mEditNameView != 0) {
                    i = EditNameView.this.mEditNameView.getSelectionStart();
                    String obj = EditNameView.this.mEditNameView.getText().toString();
                    String substring = obj.substring(i);
                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(obj.substring(0, i));
                    stringBuilder.append(charSequence.toString());
                    stringBuilder.append(substring);
                    EditNameView.this.mEditNameView.setText(stringBuilder.toString());
                    EditNameView.this.mEditNameView.setSelection(i + charSequence.toString().length());
                    EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_image);
                    EditNameView.this.mErrorTextView.setText(BuildConfig.FLAVOR);
                }
                return true;
            }

            public boolean finishComposingText() {
                return super.finishComposingText();
            }

            public boolean commitText(CharSequence charSequence, int i) {
                return super.commitText(charSequence, i);
            }

            public boolean performPrivateCommand(String str, Bundle bundle) {
                return super.performPrivateCommand(str, bundle);
            }

            public boolean deleteSurroundingText(int i, int i2) {
                int selectionStart = EditNameView.this.mEditNameView.getSelectionStart();
                if (selectionStart > 0) {
                    String obj = EditNameView.this.mEditNameView.getText().toString();
                    String str = BuildConfig.FLAVOR;
                    if (obj.length() > selectionStart) {
                        str = obj.substring(selectionStart);
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    selectionStart--;
                    stringBuilder.append(obj.substring(0, selectionStart));
                    stringBuilder.append(str);
                    EditNameView.this.mEditNameView.setText(stringBuilder.toString());
                    EditNameView.this.mEditNameView.setSelection(selectionStart);
                }
                EditNameView.this.mEditTextImage.setImageResource(C0026R.drawable.edit_text_image);
                EditNameView.this.mErrorTextView.setText(BuildConfig.FLAVOR);
                return super.deleteSurroundingText(i, i2);
            }
        };
        this.mEditNameView.setSelection(this.mEditNameView.length());
        return editorInfo;
    }

    public void onSelectionChanged(int i, int i2) {
        super.onSelectionChanged(i, i2);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return super.onKeyUp(i, keyEvent);
    }
}
