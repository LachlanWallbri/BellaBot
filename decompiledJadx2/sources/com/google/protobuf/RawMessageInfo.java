package com.google.protobuf;

/* JADX WARN: Classes with same name are omitted:
  classes2.dex
  classes4.dex
 */
/* loaded from: classes.dex */
final class RawMessageInfo implements MessageInfo {
    private final MessageLite defaultInstance;
    private final int flags;
    private final String info;
    private final Object[] objects;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RawMessageInfo(MessageLite messageLite, String str, Object[] objArr) {
        this.defaultInstance = messageLite;
        this.info = str;
        this.objects = objArr;
        char charAt = str.charAt(0);
        if (charAt < 55296) {
            this.flags = charAt;
            return;
        }
        int i = charAt & 8191;
        int i2 = 13;
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            char charAt2 = str.charAt(i3);
            if (charAt2 < 55296) {
                this.flags = i | (charAt2 << i2);
                return;
            } else {
                i |= (charAt2 & 8191) << i2;
                i2 += 13;
                i3 = i4;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public String getStringInfo() {
        return this.info;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Object[] getObjects() {
        return this.objects;
    }

    @Override // com.google.protobuf.MessageInfo
    public MessageLite getDefaultInstance() {
        return this.defaultInstance;
    }

    @Override // com.google.protobuf.MessageInfo
    public ProtoSyntax getSyntax() {
        return (this.flags & 1) == 1 ? ProtoSyntax.PROTO2 : ProtoSyntax.PROTO3;
    }

    @Override // com.google.protobuf.MessageInfo
    public boolean isMessageSetWireFormat() {
        return (this.flags & 2) == 2;
    }
}
