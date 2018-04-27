package org.thoughtcrime.securesms.contactshare.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class Phone implements Selectable, Parcelable {

  private final String number;
  private final Type   type;
  private final String label;

  private boolean selected;

  public Phone(@NonNull String number, @NonNull Type type, @Nullable String label) {
    this.number   = number;
    this.type     = type;
    this.label    = label;
    this.selected = true;
  }

  private Phone(Parcel in) {
    this(in.readString(), Type.valueOf(in.readString()), in.readString());
  }

  public @NonNull String getNumber() {
    return number;
  }

  public @NonNull Type getType() {
    return type;
  }

  public @Nullable String getLabel() {
    return label;
  }

  @Override
  public void setSelected(boolean selected) {
    this.selected = selected;
  }

  @Override
  public boolean isSelected() {
    return selected;
  }

  @Override
  public int describeContents() {
    return 0;
  }

  @Override
  public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(number);
    dest.writeString(type.name());
    dest.writeString(label);
  }

  public static final Creator<Phone> CREATOR = new Creator<Phone>() {
    @Override
    public Phone createFromParcel(Parcel in) {
      return new Phone(in);
    }

    @Override
    public Phone[] newArray(int size) {
      return new Phone[size];
    }
  };

  public enum Type {
    HOME, MOBILE, WORK, CUSTOM
  }
}
