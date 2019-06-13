package com.vuforia.eyewear.Calibration.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public interface ICalibrationProfileService extends IInterface {

    public static abstract class Stub extends Binder implements ICalibrationProfileService {
        private static final String DESCRIPTOR = "com.vuforia.eyewear.Calibration.service.ICalibrationProfileService";
        static final int TRANSACTION_clearProfile = 12;
        static final int TRANSACTION_getActiveProfile = 4;
        static final int TRANSACTION_getCameraToEyePose = 8;
        static final int TRANSACTION_getEyeProjection = 9;
        static final int TRANSACTION_getMaxProfileCount = 1;
        static final int TRANSACTION_getProfileName = 6;
        static final int TRANSACTION_getUsedProfileCount = 2;
        static final int TRANSACTION_isProfileUsed = 3;
        static final int TRANSACTION_setActiveProfile = 5;
        static final int TRANSACTION_setCameraToEyePose = 10;
        static final int TRANSACTION_setEyeProjection = 11;
        static final int TRANSACTION_setProfileName = 7;

        private static class Proxy implements ICalibrationProfileService {
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            public IBinder asBinder() {
                return this.mRemote;
            }

            public int getMaxProfileCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getUsedProfileCount() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(2, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean isProfileUsed(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(3, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public int getActiveProfile() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    this.mRemote.transact(4, obtain, obtain2, 0);
                    obtain2.readException();
                    int readInt = obtain2.readInt();
                    return readInt;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setActiveProfile(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(5, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public String getProfileName(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    this.mRemote.transact(6, obtain, obtain2, 0);
                    obtain2.readException();
                    i = obtain2.readString();
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setProfileName(int i, String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeString(str);
                    boolean z = false;
                    this.mRemote.transact(7, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float[] getCameraToEyePose(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(8, obtain, obtain2, 0);
                    obtain2.readException();
                    i = obtain2.createFloatArray();
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public float[] getEyeProjection(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    this.mRemote.transact(Stub.TRANSACTION_getEyeProjection, obtain, obtain2, 0);
                    obtain2.readException();
                    i = obtain2.createFloatArray();
                    return i;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setCameraToEyePose(int i, int i2, float[] fArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeFloatArray(fArr);
                    fArr = null;
                    this.mRemote.transact(Stub.TRANSACTION_setCameraToEyePose, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        fArr = 1;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return fArr;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean setEyeProjection(int i, int i2, float[] fArr) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeFloatArray(fArr);
                    fArr = null;
                    this.mRemote.transact(Stub.TRANSACTION_setEyeProjection, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        fArr = 1;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return fArr;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            public boolean clearProfile(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    boolean z = false;
                    this.mRemote.transact(Stub.TRANSACTION_clearProfile, obtain, obtain2, 0);
                    obtain2.readException();
                    if (obtain2.readInt() != 0) {
                        z = true;
                    }
                    obtain2.recycle();
                    obtain.recycle();
                    return z;
                } catch (Throwable th) {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ICalibrationProfileService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof ICalibrationProfileService)) {
                return new Proxy(iBinder);
            }
            return (ICalibrationProfileService) queryLocalInterface;
        }

        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1598968902) {
                switch (i) {
                    case 1:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getMaxProfileCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 2:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getUsedProfileCount();
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 3:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = isProfileUsed(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 4:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getActiveProfile();
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 5:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = setActiveProfile(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 6:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getProfileName(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeString(i);
                        return true;
                    case 7:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = setProfileName(parcel.readInt(), parcel.readString());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case 8:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getCameraToEyePose(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeFloatArray(i);
                        return true;
                    case TRANSACTION_getEyeProjection /*9*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = getEyeProjection(parcel.readInt(), parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeFloatArray(i);
                        return true;
                    case TRANSACTION_setCameraToEyePose /*10*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = setCameraToEyePose(parcel.readInt(), parcel.readInt(), parcel.createFloatArray());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case TRANSACTION_setEyeProjection /*11*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = setEyeProjection(parcel.readInt(), parcel.readInt(), parcel.createFloatArray());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    case TRANSACTION_clearProfile /*12*/:
                        parcel.enforceInterface(DESCRIPTOR);
                        i = clearProfile(parcel.readInt());
                        parcel2.writeNoException();
                        parcel2.writeInt(i);
                        return true;
                    default:
                        return super.onTransact(i, parcel, parcel2, i2);
                }
            }
            parcel2.writeString(DESCRIPTOR);
            return true;
        }
    }

    boolean clearProfile(int i) throws RemoteException;

    int getActiveProfile() throws RemoteException;

    float[] getCameraToEyePose(int i, int i2) throws RemoteException;

    float[] getEyeProjection(int i, int i2) throws RemoteException;

    int getMaxProfileCount() throws RemoteException;

    String getProfileName(int i) throws RemoteException;

    int getUsedProfileCount() throws RemoteException;

    boolean isProfileUsed(int i) throws RemoteException;

    boolean setActiveProfile(int i) throws RemoteException;

    boolean setCameraToEyePose(int i, int i2, float[] fArr) throws RemoteException;

    boolean setEyeProjection(int i, int i2, float[] fArr) throws RemoteException;

    boolean setProfileName(int i, String str) throws RemoteException;
}
