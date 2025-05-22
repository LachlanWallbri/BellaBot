package retrofit2;

/* JADX WARN: Classes with same name are omitted:
  classes8.dex
  classes9.dex
 */
/* loaded from: classes2.dex */
public interface Callback<T> {
    void onFailure(Call<T> call, Throwable th);

    void onResponse(Call<T> call, Response<T> response);
}
