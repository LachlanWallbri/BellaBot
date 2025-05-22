package com.pudu.library.loracall;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SlipTool.kt */
@Metadata(m3959bv = {1, 0, 3}, m3960d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J \u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n0\tj\b\u0012\u0004\u0012\u00020\n`\u000bJ\f\u0010\f\u001a\u00020\n*\u00020\u0006H\u0002J\f\u0010\r\u001a\u00020\u000e*\u00020\u0004H\u0002¨\u0006\u000f"}, m3961d2 = {"Lcom/pudu/library/loracall/SlipTool;", "", "()V", "msgToSlip", "", "slipMsg", "Lcom/pudu/library/loracall/SlipMsgBean;", "slipToMsg", "byteArray", "Ljava/util/ArrayList;", "", "Lkotlin/collections/ArrayList;", "getChecksum", "getNumberSum", "", "library_loracall_release"}, m3962k = 1, m3963mv = {1, 1, 16})
/* loaded from: classes4.dex */
public final class SlipTool {
    public static final SlipTool INSTANCE = new SlipTool();

    private SlipTool() {
    }

    public final byte[] msgToSlip(SlipMsgBean slipMsg) {
        Intrinsics.checkParameterIsNotNull(slipMsg, "slipMsg");
        ArrayList arrayList = new ArrayList();
        arrayList.add(Byte.valueOf(slipMsg.getFrameNumber()));
        arrayList.add(Byte.valueOf(slipMsg.getSrc()));
        arrayList.add(Byte.valueOf(slipMsg.getDest()));
        arrayList.add(Byte.valueOf(slipMsg.getType()));
        arrayList.add(Byte.valueOf(slipMsg.getId()));
        arrayList.add(Byte.valueOf((byte) slipMsg.getData().length));
        for (byte b : slipMsg.getData()) {
            arrayList.add(Byte.valueOf(b));
        }
        arrayList.add(Byte.valueOf(getChecksum(slipMsg)));
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add(Byte.valueOf(SlipConfig.END));
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            Object obj = arrayList.get(i);
            Intrinsics.checkExpressionValueIsNotNull(obj, "list[i]");
            byte byteValue = ((Number) obj).byteValue();
            if (byteValue == -64) {
                arrayList2.add(Byte.valueOf(SlipConfig.ESC));
                arrayList2.add(Byte.valueOf(SlipConfig.ESC_END));
            } else if (byteValue == -37) {
                arrayList2.add(Byte.valueOf(SlipConfig.ESC));
                arrayList2.add(Byte.valueOf(SlipConfig.ESC_ESC));
            } else {
                arrayList2.add(Byte.valueOf(byteValue));
            }
        }
        arrayList2.add(Byte.valueOf(SlipConfig.END));
        final byte[] byteArray = KotlinUtilsKt.toByteArray(arrayList2);
        KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$msgToSlip$2
            /* JADX INFO: Access modifiers changed from: package-private */
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // kotlin.jvm.functions.Function0
            public final String invoke() {
                return "发送原始数据:" + KotlinUtilsKt.toHexString(byteArray);
            }
        }, 1, null);
        return byteArray;
    }

    public final SlipMsgBean slipToMsg(final ArrayList<Byte> byteArray) {
        Intrinsics.checkParameterIsNotNull(byteArray, "byteArray");
        try {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$1
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "接收原数据: " + KotlinUtilsKt.toHexString(byteArray);
                }
            }, 1, null);
            if (byteArray.isEmpty()) {
                return null;
            }
            final ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < byteArray.size()) {
                Byte b = byteArray.get(i);
                Intrinsics.checkExpressionValueIsNotNull(b, "byteArray[index]");
                byte byteValue = b.byteValue();
                if (byteValue == -37 && i < byteArray.size() - 1) {
                    int i2 = i + 1;
                    Byte b2 = byteArray.get(i2);
                    if (b2 != null && b2.byteValue() == -36) {
                        arrayList.add(Byte.valueOf(SlipConfig.END));
                        i += 2;
                    }
                    Byte b3 = byteArray.get(i2);
                    if (b3 != null && b3.byteValue() == -35) {
                        arrayList.add(Byte.valueOf(SlipConfig.ESC));
                        i += 2;
                    }
                    arrayList.add(Byte.valueOf(byteValue));
                    i = i2;
                } else {
                    arrayList.add(Byte.valueOf(byteValue));
                    i++;
                }
            }
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$2
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "替换后数据: " + KotlinUtilsKt.toHexString(arrayList);
                }
            }, 1, null);
            final SlipMsgBean slipMsgBean = new SlipMsgBean();
            Object obj = arrayList.get(0);
            Intrinsics.checkExpressionValueIsNotNull(obj, "list[0]");
            slipMsgBean.setFrameNumber(((Number) obj).byteValue());
            Object obj2 = arrayList.get(1);
            Intrinsics.checkExpressionValueIsNotNull(obj2, "list[1]");
            slipMsgBean.setSrc(((Number) obj2).byteValue());
            Object obj3 = arrayList.get(2);
            Intrinsics.checkExpressionValueIsNotNull(obj3, "list[2]");
            slipMsgBean.setDest(((Number) obj3).byteValue());
            Object obj4 = arrayList.get(3);
            Intrinsics.checkExpressionValueIsNotNull(obj4, "list[3]");
            slipMsgBean.setType(((Number) obj4).byteValue());
            Object obj5 = arrayList.get(4);
            Intrinsics.checkExpressionValueIsNotNull(obj5, "list[4]");
            slipMsgBean.setId(((Number) obj5).byteValue());
            Object obj6 = arrayList.get(5);
            Intrinsics.checkExpressionValueIsNotNull(obj6, "list[5]");
            final byte byteValue2 = ((Number) obj6).byteValue();
            List subList = arrayList.subList(6, byteValue2 + 6);
            Intrinsics.checkExpressionValueIsNotNull(subList, "list.subList(6, 6 + payloadLength)");
            slipMsgBean.setData(KotlinUtilsKt.toByteArray(subList));
            final byte byteValue3 = ((Number) CollectionsKt.last((List) arrayList)).byteValue();
            final byte checksum = getChecksum(slipMsgBean);
            if (byteValue3 != checksum) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$3
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "数据校验失败--oldChecksum:" + KotlinUtilsKt.toHexString(byteValue3) + " newChecksum:" + KotlinUtilsKt.toHexString(checksum);
                    }
                }, 1, null);
                return null;
            }
            if (!Intrinsics.areEqual(KotlinUtilsKt.toHexString(slipMsgBean.getDest()), "01")) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$4
                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "不是接收数据";
                    }
                }, 1, null);
                return null;
            }
            if (slipMsgBean.getType() != 16 && slipMsgBean.getType() != 32) {
                KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$6
                    /* JADX INFO: Access modifiers changed from: package-private */
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(0);
                    }

                    @Override // kotlin.jvm.functions.Function0
                    public final String invoke() {
                        return "收到消息-" + SlipMsgBean.this + "\npayloadLength:" + ((int) byteValue2);
                    }
                }, 1, null);
                return slipMsgBean;
            }
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$5
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "收到应答-" + SlipMsgBean.this + "\npayloadLength:" + ((int) byteValue2);
                }
            }, 1, null);
            return slipMsgBean;
        } catch (Exception e) {
            KotlinUtilsKt.log$default(this, null, new Function0<String>() { // from class: com.pudu.library.loracall.SlipTool$slipToMsg$7
                /* JADX INFO: Access modifiers changed from: package-private */
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(0);
                }

                @Override // kotlin.jvm.functions.Function0
                public final String invoke() {
                    return "slipToMsg: 解析错误" + KotlinUtilsKt.stackTraceToString(e);
                }
            }, 1, null);
            return null;
        }
    }

    private final byte getChecksum(SlipMsgBean slipMsgBean) {
        return (byte) (((((((slipMsgBean.getFrameNumber() + slipMsgBean.getSrc()) + slipMsgBean.getDest()) + slipMsgBean.getType()) + slipMsgBean.getId()) + slipMsgBean.getData().length) + getNumberSum(slipMsgBean.getData())) ^ 255);
    }

    private final int getNumberSum(byte[] bArr) {
        int i = 0;
        for (byte b : bArr) {
            i += b;
        }
        return i;
    }
}
