package io.grpc.netty.shaded.io.netty.handler.codec.http.multipart;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
 */
/* loaded from: classes7.dex */
final class FileUploadUtil {
    private FileUploadUtil() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int hashCode(FileUpload fileUpload) {
        return fileUpload.getName().hashCode();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static boolean equals(FileUpload fileUpload, FileUpload fileUpload2) {
        return fileUpload.getName().equalsIgnoreCase(fileUpload2.getName());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static int compareTo(FileUpload fileUpload, FileUpload fileUpload2) {
        return fileUpload.getName().compareToIgnoreCase(fileUpload2.getName());
    }
}
