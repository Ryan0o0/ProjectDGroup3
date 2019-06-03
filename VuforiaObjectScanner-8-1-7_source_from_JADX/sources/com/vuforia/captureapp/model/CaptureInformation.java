package com.vuforia.captureapp.model;

import android.annotation.SuppressLint;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class CaptureInformation implements Serializable {
    private static final long serialVersionUID = 4729003554512915982L;
    private final SimpleDateFormat _dateFormat;
    private int[] _facets = new int[49];
    private float _fileSize;
    private String _imagePath;
    private boolean _isSelected;
    private String _itemName;
    private Date _lastModified;
    private int _nbPoints;

    @SuppressLint({"SimpleDateFormat"})
    public CaptureInformation(String str, String str2, Date date, Properties properties) {
        this._imagePath = str;
        this._itemName = str2;
        this._lastModified = date;
        this._dateFormat = new SimpleDateFormat("MMM d, yyyy HH:mm");
        str = null;
        this._isSelected = false;
        this._nbPoints = Integer.parseInt(properties.getProperty("nbPoints", "124"));
        this._fileSize = Float.parseFloat(properties.getProperty("mFileSize", "0"));
        while (str < this._facets.length) {
            str2 = this._facets;
            date = new StringBuilder();
            date.append("facet");
            date.append(str);
            str2[str] = Integer.parseInt(properties.getProperty(date.toString(), "0"));
            str++;
        }
    }

    public String getImagePath() {
        return this._imagePath;
    }

    public String getItemName() {
        return this._itemName;
    }

    public long getLastModified() {
        return this._lastModified.getTime();
    }

    public String getFormatedLastModified() {
        return this._dateFormat.format(this._lastModified);
    }

    public void toggleSelected() {
        this._isSelected ^= 1;
    }

    public boolean isSelected() {
        return this._isSelected;
    }

    public void selected(boolean z) {
        this._isSelected = false;
    }

    public int getNbPoints() {
        return this._nbPoints;
    }

    public float getFileSize() {
        return this._fileSize;
    }

    public int[] getFacets() {
        return this._facets;
    }

    public void updateName(String str, String str2) {
        this._itemName = str;
        this._imagePath = str2;
    }

    public void update(Date date, int i, float f, int[] iArr) {
        this._lastModified = date;
        if (i > 0) {
            this._nbPoints = i;
        }
        if (f > null) {
            this._fileSize = f;
        }
        f = 0.0f;
        for (int i2 : iArr) {
            if (i2 > 0) {
                f++;
            }
        }
        if (f > null) {
            for (date = null; date < iArr.length; date++) {
                this._facets[date] = iArr[date];
            }
        }
    }
}
