package com.unity3d.player;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import com.vuforia.PIXEL_FORMAT;

/* renamed from: com.unity3d.player.k */
public final class C0048k extends Dialog implements TextWatcher, OnClickListener {
    /* renamed from: c */
    private static int f182c = 1627389952;
    /* renamed from: d */
    private static int f183d = -1;
    /* renamed from: e */
    private static int f184e = 134217728;
    /* renamed from: f */
    private static int f185f = 67108864;
    /* renamed from: a */
    private Context f186a = null;
    /* renamed from: b */
    private UnityPlayer f187b = null;

    /* renamed from: com.unity3d.player.k$1 */
    class C00451 implements OnFocusChangeListener {
        /* renamed from: a */
        final /* synthetic */ C0048k f179a;

        C00451(C0048k c0048k) {
            this.f179a = c0048k;
        }

        public final void onFocusChange(View view, boolean z) {
            if (z) {
                this.f179a.getWindow().setSoftInputMode(5);
            }
        }
    }

    /* renamed from: com.unity3d.player.k$3 */
    class C00473 implements OnEditorActionListener {
        /* renamed from: a */
        final /* synthetic */ C0048k f181a;

        C00473(C0048k c0048k) {
            this.f181a = c0048k;
        }

        public final boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
            if (i == 6) {
                this.f181a.m90a(this.f181a.m86a(), false);
            }
            return false;
        }
    }

    public C0048k(Context context, UnityPlayer unityPlayer, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2, boolean z4) {
        super(context);
        this.f186a = context;
        this.f187b = unityPlayer;
        m96a(z4);
        getWindow().requestFeature(1);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        setContentView(createSoftInputView());
        getWindow().setLayout(-1, -2);
        getWindow().clearFlags(2);
        if (C0044j.f175a) {
            getWindow().clearFlags(f184e);
            getWindow().clearFlags(f185f);
        }
        EditText editText = (EditText) findViewById(1057292289);
        Button button = (Button) findViewById(1057292290);
        m88a(editText, str, i, z, z2, z3, str2, i2);
        button.setOnClickListener(this);
        editText.setOnFocusChangeListener(new C00451(this));
        editText.requestFocus();
    }

    /* renamed from: a */
    private static int m85a(int i, boolean z, boolean z2, boolean z3) {
        int i2 = 0;
        int i3 = (z ? 32768 : 524288) | (z2 ? 131072 : 0);
        if (z3) {
            i2 = PIXEL_FORMAT.NV12;
        }
        i3 |= i2;
        if (i >= 0) {
            if (i <= 10) {
                int[] iArr = new int[]{1, 16385, 12290, 17, 2, 3, 8289, 33, 1, 16417, 17};
                return (iArr[i] & 2) != 0 ? iArr[i] : iArr[i] | i3;
            }
        }
        return i3;
    }

    /* renamed from: a */
    private String m86a() {
        EditText editText = (EditText) findViewById(1057292289);
        return editText == null ? null : editText.getText().toString().trim();
    }

    /* renamed from: a */
    private void m88a(EditText editText, String str, int i, boolean z, boolean z2, boolean z3, String str2, int i2) {
        editText.setImeOptions(6);
        editText.setText(str);
        editText.setHint(str2);
        editText.setHintTextColor(f182c);
        editText.setInputType(C0048k.m85a(i, z, z2, z3));
        editText.setImeOptions(33554432);
        if (i2 > 0) {
            editText.setFilters(new InputFilter[]{new LengthFilter(i2)});
        }
        editText.addTextChangedListener(this);
        editText.setSelection(editText.getText().length());
        editText.setClickable(true);
    }

    /* renamed from: a */
    private void m90a(String str, boolean z) {
        ((EditText) findViewById(1057292289)).setSelection(0, 0);
        this.f187b.reportSoftInputStr(str, 1, z);
    }

    /* renamed from: a */
    public final void m93a(int i) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            if (i > 0) {
                editText.setFilters(new InputFilter[]{new LengthFilter(i)});
                return;
            }
            editText.setFilters(new InputFilter[0]);
        }
    }

    /* renamed from: a */
    public final void m94a(int i, int i2) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            i2 += i;
            if (editText.getText().length() >= i2) {
                editText.setSelection(i, i2);
            }
        }
    }

    /* renamed from: a */
    public final void m95a(String str) {
        EditText editText = (EditText) findViewById(1057292289);
        if (editText != null) {
            editText.setText(str);
            editText.setSelection(str.length());
        }
    }

    /* renamed from: a */
    public final void m96a(boolean z) {
        int i;
        Window window = getWindow();
        LayoutParams attributes = window.getAttributes();
        if (z) {
            attributes.gravity = 8;
            i = 20000;
        } else {
            attributes.gravity = 80;
            i = 0;
        }
        attributes.x = i;
        attributes.y = i;
        window.setAttributes(attributes);
    }

    public final void afterTextChanged(Editable editable) {
        this.f187b.reportSoftInputStr(editable.toString(), 0, false);
    }

    public final void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    protected final View createSoftInputView() {
        View relativeLayout = new RelativeLayout(this.f186a);
        relativeLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        relativeLayout.setBackgroundColor(f183d);
        View c00462 = new EditText(this, this.f186a) {
            /* renamed from: a */
            final /* synthetic */ C0048k f180a;

            public final boolean onKeyPreIme(int i, KeyEvent keyEvent) {
                if (i != 4) {
                    return i == 84 ? true : super.onKeyPreIme(i, keyEvent);
                } else {
                    this.f180a.m90a(this.f180a.m86a(), true);
                    return true;
                }
            }

            protected final void onSelectionChanged(int i, int i2) {
                this.f180a.f187b.reportSoftInputSelection(i, i2 - i);
            }

            public final void onWindowFocusChanged(boolean z) {
                super.onWindowFocusChanged(z);
                if (z) {
                    ((InputMethodManager) this.f180a.f186a.getSystemService("input_method")).showSoftInput(this, 0);
                }
            }
        };
        ViewGroup.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.addRule(15);
        layoutParams.addRule(0, 1057292290);
        c00462.setLayoutParams(layoutParams);
        c00462.setId(1057292289);
        relativeLayout.addView(c00462);
        c00462 = new Button(this.f186a);
        c00462.setText(this.f186a.getResources().getIdentifier("ok", "string", "android"));
        ViewGroup.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(15);
        layoutParams2.addRule(11);
        c00462.setLayoutParams(layoutParams2);
        c00462.setId(1057292290);
        c00462.setBackgroundColor(0);
        relativeLayout.addView(c00462);
        ((EditText) relativeLayout.findViewById(1057292289)).setOnEditorActionListener(new C00473(this));
        relativeLayout.setPadding(16, 16, 16, 16);
        return relativeLayout;
    }

    public final void onBackPressed() {
        m90a(m86a(), true);
    }

    public final void onClick(View view) {
        m90a(m86a(), false);
    }

    public final void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }
}
