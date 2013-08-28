/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package android.accounts;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;

public class AccountExtra implements Parcelable {
    public String key;
    public String value;

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof AccountExtra)) return false;
        final AccountExtra other = (AccountExtra)o;
        return key.equals(other.key) && value.equals(other.value);
    }

    public int hashCode() {
        int result = 17;
        result = 31 * result + key.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

    public AccountExtra(String key, String value) {
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException("the key must not be empty: " + key);
        }
        if (TextUtils.isEmpty(value)) {
            throw new IllegalArgumentException("the value must not be empty: " + value);
        }
        this.key = key;
        this.value = value;
    }

    public AccountExtra(Parcel in) {
        this.key = in.readString();
        this.value = in.readString();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(key);
        dest.writeString(value == null ? "" : value);
    }

    public static final Creator<AccountExtra> CREATOR = new Creator<AccountExtra>() {
        public AccountExtra createFromParcel(Parcel source) {
            return new AccountExtra(source);
        }

        public AccountExtra[] newArray(int size) {
            return new AccountExtra[size];
        }
    };

    public String toString() {
        return "AccountExtra {key=" + key + ", value=" + value + "}";
    }
}
