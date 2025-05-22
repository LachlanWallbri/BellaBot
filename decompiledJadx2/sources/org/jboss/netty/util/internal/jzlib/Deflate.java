package org.jboss.netty.util.internal.jzlib;

import com.google.common.base.Ascii;
import kotlin.UShort;
import org.jboss.netty.util.internal.jzlib.JZlib;

/* JADX INFO: Access modifiers changed from: package-private */
/* loaded from: classes7.dex */
public final class Deflate {
    private static final int BUSY_STATE = 113;
    private static final int BlockDone = 1;
    private static final int Buf_size = 16;
    private static final int DYN_TREES = 2;
    private static final int END_BLOCK = 256;
    private static final int FAST = 1;
    private static final int FINISH_STATE = 666;
    private static final int FinishDone = 3;
    private static final int FinishStarted = 2;
    private static final int INIT_STATE = 42;
    private static final int MAX_MATCH = 258;
    private static final int MIN_LOOKAHEAD = 262;
    private static final int MIN_MATCH = 3;
    private static final int NeedMore = 0;
    private static final int REPZ_11_138 = 18;
    private static final int REPZ_3_10 = 17;
    private static final int REP_3_6 = 16;
    private static final int SLOW = 2;
    private static final int STATIC_TREES = 1;
    private static final int STORED = 0;
    private static final int STORED_BLOCK = 0;
    private static final int Z_ASCII = 1;
    private static final int Z_BINARY = 0;
    private static final int Z_UNKNOWN = 2;
    private static final Config[] config_table = new Config[10];
    private static final String[] z_errmsg;
    short bi_buf;
    int bi_valid;
    int block_start;
    int d_buf;
    byte data_type;
    int good_match;
    private int gzipUncompressedBytes;
    int hash_bits;
    int hash_mask;
    int hash_shift;
    int hash_size;
    short[] head;
    int heap_len;
    int heap_max;
    int ins_h;
    int l_buf;
    int last_eob_len;
    int last_flush;
    int last_lit;
    int level;
    int lit_bufsize;
    int lookahead;
    int match_available;
    int match_length;
    int match_start;
    int matches;
    int max_chain_length;
    int max_lazy_match;
    int nice_match;
    int opt_len;
    int pending;
    byte[] pending_buf;
    int pending_buf_size;
    int pending_out;
    short[] prev;
    int prev_length;
    int prev_match;
    int static_len;
    int status;
    int strategy;
    ZStream strm;
    int strstart;
    int w_bits;
    int w_mask;
    int w_size;
    byte[] window;
    int window_size;
    JZlib.WrapperType wrapperType;
    private boolean wroteTrailer;
    Tree l_desc = new Tree();
    Tree d_desc = new Tree();
    Tree bl_desc = new Tree();
    short[] bl_count = new short[16];
    int[] heap = new int[573];
    byte[] depth = new byte[573];
    short[] dyn_ltree = new short[1146];
    short[] dyn_dtree = new short[122];
    short[] bl_tree = new short[78];

    /* JADX INFO: Access modifiers changed from: private */
    /* loaded from: classes7.dex */
    public static final class Config {
        final int func;
        final int good_length;
        final int max_chain;
        final int max_lazy;
        final int nice_length;

        Config(int i, int i2, int i3, int i4, int i5) {
            this.good_length = i;
            this.max_lazy = i2;
            this.nice_length = i3;
            this.max_chain = i4;
            this.func = i5;
        }
    }

    static {
        config_table[0] = new Config(0, 0, 0, 0, 0);
        config_table[1] = new Config(4, 4, 8, 4, 1);
        config_table[2] = new Config(4, 5, 16, 8, 1);
        config_table[3] = new Config(4, 6, 32, 32, 1);
        config_table[4] = new Config(4, 4, 16, 16, 2);
        config_table[5] = new Config(8, 16, 32, 32, 2);
        config_table[6] = new Config(8, 16, 128, 128, 2);
        config_table[7] = new Config(8, 32, 128, 256, 2);
        config_table[8] = new Config(32, 128, 258, 1024, 2);
        config_table[9] = new Config(32, 258, 258, 4096, 2);
        z_errmsg = new String[]{"need dictionary", "stream end", "", "file error", "stream error", "data error", "insufficient memory", "buffer error", "incompatible version", ""};
    }

    private void lm_init() {
        this.window_size = this.w_size * 2;
        this.max_lazy_match = config_table[this.level].max_lazy;
        this.good_match = config_table[this.level].good_length;
        this.nice_match = config_table[this.level].nice_length;
        this.max_chain_length = config_table[this.level].max_chain;
        this.strstart = 0;
        this.block_start = 0;
        this.lookahead = 0;
        this.prev_length = 2;
        this.match_length = 2;
        this.match_available = 0;
        this.ins_h = 0;
    }

    private void tr_init() {
        Tree tree = this.l_desc;
        tree.dyn_tree = this.dyn_ltree;
        tree.stat_desc = StaticTree.static_l_desc;
        Tree tree2 = this.d_desc;
        tree2.dyn_tree = this.dyn_dtree;
        tree2.stat_desc = StaticTree.static_d_desc;
        Tree tree3 = this.bl_desc;
        tree3.dyn_tree = this.bl_tree;
        tree3.stat_desc = StaticTree.static_bl_desc;
        this.bi_buf = (short) 0;
        this.bi_valid = 0;
        this.last_eob_len = 8;
        init_block();
    }

    private void init_block() {
        for (int i = 0; i < 286; i++) {
            this.dyn_ltree[i * 2] = 0;
        }
        for (int i2 = 0; i2 < 30; i2++) {
            this.dyn_dtree[i2 * 2] = 0;
        }
        for (int i3 = 0; i3 < 19; i3++) {
            this.bl_tree[i3 * 2] = 0;
        }
        this.dyn_ltree[512] = 1;
        this.static_len = 0;
        this.opt_len = 0;
        this.matches = 0;
        this.last_lit = 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void pqdownheap(short[] sArr, int i) {
        int i2 = this.heap[i];
        int i3 = i << 1;
        while (true) {
            int i4 = this.heap_len;
            if (i3 > i4) {
                break;
            }
            if (i3 < i4) {
                int[] iArr = this.heap;
                int i5 = i3 + 1;
                if (smaller(sArr, iArr[i5], iArr[i3], this.depth)) {
                    i3 = i5;
                }
            }
            if (smaller(sArr, i2, this.heap[i3], this.depth)) {
                break;
            }
            int[] iArr2 = this.heap;
            iArr2[i] = iArr2[i3];
            int i6 = i3;
            i3 <<= 1;
            i = i6;
        }
        this.heap[i] = i2;
    }

    private static boolean smaller(short[] sArr, int i, int i2, byte[] bArr) {
        short s = sArr[i * 2];
        short s2 = sArr[i2 * 2];
        return s < s2 || (s == s2 && bArr[i] <= bArr[i2]);
    }

    private void scan_tree(short[] sArr, int i) {
        int i2;
        int i3;
        short s = sArr[1];
        if (s == 0) {
            i3 = 138;
            i2 = 3;
        } else {
            i2 = 4;
            i3 = 7;
        }
        sArr[((i + 1) * 2) + 1] = -1;
        int i4 = i2;
        int i5 = 0;
        short s2 = -1;
        int i6 = i3;
        short s3 = s;
        int i7 = 0;
        while (i7 <= i) {
            i7++;
            short s4 = sArr[(i7 * 2) + 1];
            i5++;
            if (i5 >= i6 || s3 != s4) {
                if (i5 < i4) {
                    short[] sArr2 = this.bl_tree;
                    int i8 = s3 * 2;
                    sArr2[i8] = (short) (sArr2[i8] + i5);
                } else if (s3 != 0) {
                    if (s3 != s2) {
                        short[] sArr3 = this.bl_tree;
                        int i9 = s3 * 2;
                        sArr3[i9] = (short) (sArr3[i9] + 1);
                    }
                    short[] sArr4 = this.bl_tree;
                    sArr4[32] = (short) (sArr4[32] + 1);
                } else if (i5 <= 10) {
                    short[] sArr5 = this.bl_tree;
                    sArr5[34] = (short) (sArr5[34] + 1);
                } else {
                    short[] sArr6 = this.bl_tree;
                    sArr6[36] = (short) (sArr6[36] + 1);
                }
                if (s4 == 0) {
                    i6 = 138;
                    i4 = 3;
                } else if (s3 == s4) {
                    i4 = 3;
                    s2 = s3;
                    i6 = 6;
                    i5 = 0;
                } else {
                    i4 = 4;
                    i6 = 7;
                }
                s2 = s3;
                i5 = 0;
            }
            s3 = s4;
        }
    }

    private int build_bl_tree() {
        scan_tree(this.dyn_ltree, this.l_desc.max_code);
        scan_tree(this.dyn_dtree, this.d_desc.max_code);
        this.bl_desc.build_tree(this);
        int i = 18;
        while (i >= 3 && this.bl_tree[(Tree.bl_order[i] * 2) + 1] == 0) {
            i--;
        }
        this.opt_len += ((i + 1) * 3) + 5 + 5 + 4;
        return i;
    }

    private void send_all_trees(int i, int i2, int i3) {
        send_bits(i - 257, 5);
        int i4 = i2 - 1;
        send_bits(i4, 5);
        send_bits(i3 - 4, 4);
        for (int i5 = 0; i5 < i3; i5++) {
            send_bits(this.bl_tree[(Tree.bl_order[i5] * 2) + 1], 3);
        }
        send_tree(this.dyn_ltree, i - 1);
        send_tree(this.dyn_dtree, i4);
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x006c  */
    /* JADX WARN: Removed duplicated region for block: B:20:0x0071  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private void send_tree(short[] sArr, int i) {
        int i2;
        int i3;
        short s = sArr[1];
        if (s == 0) {
            i3 = 138;
            i2 = 3;
        } else {
            i2 = 4;
            i3 = 7;
        }
        int i4 = i3;
        int i5 = i2;
        int i6 = 0;
        int i7 = 0;
        short s2 = -1;
        short s3 = s;
        while (i6 <= i) {
            i6++;
            short s4 = sArr[(i6 * 2) + 1];
            i7++;
            if (i7 >= i4 || s3 != s4) {
                if (i7 >= i5) {
                    if (s3 != 0) {
                        if (s3 != s2) {
                            send_code(s3, this.bl_tree);
                            i7--;
                        }
                        send_code(16, this.bl_tree);
                        send_bits(i7 - 3, 2);
                    } else if (i7 <= 10) {
                        send_code(17, this.bl_tree);
                        send_bits(i7 - 3, 3);
                    } else {
                        send_code(18, this.bl_tree);
                        send_bits(i7 - 11, 7);
                    }
                    if (s4 != 0) {
                        i4 = 138;
                        i5 = 3;
                    } else if (s3 == s4) {
                        i5 = 3;
                        s2 = s3;
                        i4 = 6;
                        i7 = 0;
                    } else {
                        i5 = 4;
                        i4 = 7;
                    }
                    s2 = s3;
                    i7 = 0;
                }
                do {
                    send_code(s3, this.bl_tree);
                    i7--;
                } while (i7 != 0);
                if (s4 != 0) {
                }
                s2 = s3;
                i7 = 0;
            }
            s3 = s4;
        }
    }

    private void put_byte(byte[] bArr, int i, int i2) {
        System.arraycopy(bArr, i, this.pending_buf, this.pending, i2);
        this.pending += i2;
    }

    private void put_byte(byte b) {
        byte[] bArr = this.pending_buf;
        int i = this.pending;
        this.pending = i + 1;
        bArr[i] = b;
    }

    private void put_short(int i) {
        put_byte((byte) i);
        put_byte((byte) (i >>> 8));
    }

    private void putShortMSB(int i) {
        put_byte((byte) (i >> 8));
        put_byte((byte) i);
    }

    private void send_code(int i, short[] sArr) {
        int i2 = i * 2;
        send_bits(sArr[i2] & UShort.MAX_VALUE, sArr[i2 + 1] & UShort.MAX_VALUE);
    }

    private void send_bits(int i, int i2) {
        int i3 = this.bi_valid;
        if (i3 > 16 - i2) {
            this.bi_buf = (short) (((i << i3) & 65535) | this.bi_buf);
            put_short(this.bi_buf);
            int i4 = this.bi_valid;
            this.bi_buf = (short) (i >>> (16 - i4));
            this.bi_valid = i4 + (i2 - 16);
            return;
        }
        this.bi_buf = (short) (((i << i3) & 65535) | this.bi_buf);
        this.bi_valid = i3 + i2;
    }

    private void _tr_align() {
        send_bits(2, 3);
        send_code(256, StaticTree.static_ltree);
        bi_flush();
        if (((this.last_eob_len + 1) + 10) - this.bi_valid < 9) {
            send_bits(2, 3);
            send_code(256, StaticTree.static_ltree);
            bi_flush();
        }
        this.last_eob_len = 7;
    }

    private boolean _tr_tally(int i, int i2) {
        byte[] bArr = this.pending_buf;
        int i3 = this.d_buf;
        int i4 = this.last_lit;
        bArr[(i4 * 2) + i3] = (byte) (i >>> 8);
        bArr[i3 + (i4 * 2) + 1] = (byte) i;
        bArr[this.l_buf + i4] = (byte) i2;
        this.last_lit = i4 + 1;
        if (i == 0) {
            short[] sArr = this.dyn_ltree;
            int i5 = i2 * 2;
            sArr[i5] = (short) (sArr[i5] + 1);
        } else {
            this.matches++;
            short[] sArr2 = this.dyn_ltree;
            int i6 = (Tree._length_code[i2] + 256 + 1) * 2;
            sArr2[i6] = (short) (sArr2[i6] + 1);
            short[] sArr3 = this.dyn_dtree;
            int d_code = Tree.d_code(i - 1) * 2;
            sArr3[d_code] = (short) (sArr3[d_code] + 1);
        }
        int i7 = this.last_lit;
        if ((i7 & 8191) == 0 && this.level > 2) {
            int i8 = this.strstart - this.block_start;
            int i9 = i7 * 8;
            for (int i10 = 0; i10 < 30; i10++) {
                i9 = (int) (i9 + (this.dyn_dtree[i10 * 2] * (Tree.extra_dbits[i10] + 5)));
            }
            int i11 = i9 >>> 3;
            if (this.matches < this.last_lit / 2 && i11 < i8 / 2) {
                return true;
            }
        }
        return this.last_lit == this.lit_bufsize - 1;
    }

    private void compress_block(short[] sArr, short[] sArr2) {
        if (this.last_lit != 0) {
            int i = 0;
            do {
                byte[] bArr = this.pending_buf;
                int i2 = this.d_buf;
                int i3 = i * 2;
                int i4 = (bArr[i2 + i3 + 1] & 255) | ((bArr[i2 + i3] << 8) & 65280);
                int i5 = bArr[this.l_buf + i] & 255;
                i++;
                if (i4 == 0) {
                    send_code(i5, sArr);
                } else {
                    byte b = Tree._length_code[i5];
                    send_code(b + 256 + 1, sArr);
                    int i6 = Tree.extra_lbits[b];
                    if (i6 != 0) {
                        send_bits(i5 - Tree.base_length[b], i6);
                    }
                    int i7 = i4 - 1;
                    int d_code = Tree.d_code(i7);
                    send_code(d_code, sArr2);
                    int i8 = Tree.extra_dbits[d_code];
                    if (i8 != 0) {
                        send_bits(i7 - Tree.base_dist[d_code], i8);
                    }
                }
            } while (i < this.last_lit);
        }
        send_code(256, sArr);
        this.last_eob_len = sArr[513];
    }

    private void set_data_type() {
        int i = 0;
        int i2 = 0;
        while (i < 7) {
            i2 += this.dyn_ltree[i * 2];
            i++;
        }
        int i3 = 0;
        while (i < 128) {
            i3 += this.dyn_ltree[i * 2];
            i++;
        }
        while (i < 256) {
            i2 += this.dyn_ltree[i * 2];
            i++;
        }
        this.data_type = (byte) (i2 <= (i3 >>> 2) ? 1 : 0);
    }

    private void bi_flush() {
        int i = this.bi_valid;
        if (i == 16) {
            put_short(this.bi_buf);
            this.bi_buf = (short) 0;
            this.bi_valid = 0;
        } else if (i >= 8) {
            put_byte((byte) this.bi_buf);
            this.bi_buf = (short) (this.bi_buf >>> 8);
            this.bi_valid -= 8;
        }
    }

    private void bi_windup() {
        int i = this.bi_valid;
        if (i > 8) {
            put_short(this.bi_buf);
        } else if (i > 0) {
            put_byte((byte) this.bi_buf);
        }
        this.bi_buf = (short) 0;
        this.bi_valid = 0;
    }

    private void copy_block(int i, int i2, boolean z) {
        bi_windup();
        this.last_eob_len = 8;
        if (z) {
            put_short((short) i2);
            put_short((short) (~i2));
        }
        put_byte(this.window, i, i2);
    }

    private void flush_block_only(boolean z) {
        int i = this.block_start;
        if (i < 0) {
            i = -1;
        }
        _tr_flush_block(i, this.strstart - this.block_start, z);
        this.block_start = this.strstart;
        this.strm.flush_pending();
    }

    private int deflate_stored(int i) {
        int i2 = this.pending_buf_size;
        int i3 = 65535 > i2 + (-5) ? i2 - 5 : 65535;
        while (true) {
            if (this.lookahead <= 1) {
                fill_window();
                if (this.lookahead == 0 && i == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    flush_block_only(i == 4);
                    return this.strm.avail_out == 0 ? i == 4 ? 2 : 0 : i == 4 ? 3 : 1;
                }
            }
            this.strstart += this.lookahead;
            this.lookahead = 0;
            int i4 = this.block_start + i3;
            int i5 = this.strstart;
            if (i5 == 0 || i5 >= i4) {
                this.lookahead = this.strstart - i4;
                this.strstart = i4;
                flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
            }
            if (this.strstart - this.block_start >= this.w_size - 262) {
                flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
            }
        }
    }

    private void _tr_stored_block(int i, int i2, boolean z) {
        send_bits((z ? 1 : 0) + 0, 3);
        copy_block(i, i2, true);
    }

    private void _tr_flush_block(int i, int i2, boolean z) {
        int i3;
        int i4;
        int i5;
        if (this.level > 0) {
            if (this.data_type == 2) {
                set_data_type();
            }
            this.l_desc.build_tree(this);
            this.d_desc.build_tree(this);
            i4 = build_bl_tree();
            i3 = ((this.opt_len + 3) + 7) >>> 3;
            i5 = ((this.static_len + 3) + 7) >>> 3;
            if (i5 <= i3) {
                i3 = i5;
            }
        } else {
            i3 = i2 + 5;
            i4 = 0;
            i5 = i3;
        }
        if (i2 + 4 <= i3 && i != -1) {
            _tr_stored_block(i, i2, z);
        } else if (i5 == i3) {
            send_bits((z ? 1 : 0) + 2, 3);
            compress_block(StaticTree.static_ltree, StaticTree.static_dtree);
        } else {
            send_bits((z ? 1 : 0) + 4, 3);
            send_all_trees(this.l_desc.max_code + 1, this.d_desc.max_code + 1, i4 + 1);
            compress_block(this.dyn_ltree, this.dyn_dtree);
        }
        init_block();
        if (z) {
            bi_windup();
        }
    }

    private void fill_window() {
        do {
            int i = this.window_size;
            int i2 = this.lookahead;
            int i3 = this.strstart;
            int i4 = (i - i2) - i3;
            if (i4 == 0 && i3 == 0 && i2 == 0) {
                i4 = this.w_size;
            } else if (i4 == -1) {
                i4--;
            } else {
                int i5 = this.strstart;
                int i6 = this.w_size;
                if (i5 >= (i6 + i6) - MIN_LOOKAHEAD) {
                    byte[] bArr = this.window;
                    System.arraycopy(bArr, i6, bArr, 0, i6);
                    int i7 = this.match_start;
                    int i8 = this.w_size;
                    this.match_start = i7 - i8;
                    this.strstart -= i8;
                    this.block_start -= i8;
                    int i9 = this.hash_size;
                    int i10 = i9;
                    do {
                        short[] sArr = this.head;
                        i9--;
                        int i11 = sArr[i9] & UShort.MAX_VALUE;
                        int i12 = this.w_size;
                        sArr[i9] = i11 >= i12 ? (short) (i11 - i12) : (short) 0;
                        i10--;
                    } while (i10 != 0);
                    int i13 = this.w_size;
                    int i14 = i13;
                    do {
                        short[] sArr2 = this.prev;
                        i13--;
                        int i15 = sArr2[i13] & UShort.MAX_VALUE;
                        int i16 = this.w_size;
                        sArr2[i13] = i15 >= i16 ? (short) (i15 - i16) : (short) 0;
                        i14--;
                    } while (i14 != 0);
                    i4 += this.w_size;
                }
            }
            if (this.strm.avail_in == 0) {
                return;
            }
            this.lookahead += this.strm.read_buf(this.window, this.strstart + this.lookahead, i4);
            if (this.lookahead >= 3) {
                byte[] bArr2 = this.window;
                int i17 = this.strstart;
                this.ins_h = bArr2[i17] & 255;
                this.ins_h = ((bArr2[i17 + 1] & 255) ^ (this.ins_h << this.hash_shift)) & this.hash_mask;
            }
            if (this.lookahead >= MIN_LOOKAHEAD) {
                return;
            }
        } while (this.strm.avail_in != 0);
    }

    private int deflate_fast(int i) {
        boolean _tr_tally;
        int i2;
        int i3;
        int i4;
        int i5 = 0;
        while (true) {
            if (this.lookahead < MIN_LOOKAHEAD) {
                fill_window();
                if (this.lookahead < MIN_LOOKAHEAD && i == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    flush_block_only(i == 4);
                    return this.strm.avail_out == 0 ? i == 4 ? 2 : 0 : i == 4 ? 3 : 1;
                }
            }
            if (this.lookahead >= 3) {
                int i6 = this.ins_h << this.hash_shift;
                byte[] bArr = this.window;
                int i7 = this.strstart;
                this.ins_h = (i6 ^ (bArr[(i7 + 3) - 1] & 255)) & this.hash_mask;
                short[] sArr = this.head;
                int i8 = this.ins_h;
                int i9 = sArr[i8] & UShort.MAX_VALUE;
                this.prev[this.w_mask & i7] = sArr[i8];
                sArr[i8] = (short) i7;
                i5 = i9;
            }
            if (i5 != 0 && ((this.strstart - i5) & 65535) <= this.w_size - MIN_LOOKAHEAD && this.strategy != 2) {
                this.match_length = longest_match(i5);
            }
            int i10 = this.match_length;
            if (i10 >= 3) {
                _tr_tally = _tr_tally(this.strstart - this.match_start, i10 - 3);
                int i11 = this.lookahead;
                int i12 = this.match_length;
                this.lookahead = i11 - i12;
                if (i12 <= this.max_lazy_match && this.lookahead >= 3) {
                    this.match_length = i12 - 1;
                    do {
                        this.strstart++;
                        int i13 = this.ins_h << this.hash_shift;
                        byte[] bArr2 = this.window;
                        i2 = this.strstart;
                        this.ins_h = (i13 ^ (bArr2[(i2 + 3) - 1] & 255)) & this.hash_mask;
                        short[] sArr2 = this.head;
                        int i14 = this.ins_h;
                        i3 = sArr2[i14] & UShort.MAX_VALUE;
                        this.prev[this.w_mask & i2] = sArr2[i14];
                        sArr2[i14] = (short) i2;
                        i4 = this.match_length - 1;
                        this.match_length = i4;
                    } while (i4 != 0);
                    this.strstart = i2 + 1;
                    i5 = i3;
                } else {
                    this.strstart += this.match_length;
                    this.match_length = 0;
                    byte[] bArr3 = this.window;
                    int i15 = this.strstart;
                    this.ins_h = bArr3[i15] & 255;
                    this.ins_h = ((bArr3[i15 + 1] & 255) ^ (this.ins_h << this.hash_shift)) & this.hash_mask;
                }
            } else {
                _tr_tally = _tr_tally(0, this.window[this.strstart] & 255);
                this.lookahead--;
                this.strstart++;
            }
            if (_tr_tally) {
                flush_block_only(false);
                if (this.strm.avail_out == 0) {
                    return 0;
                }
            }
        }
    }

    private int deflate_slow(int i) {
        int i2;
        int i3 = 0;
        while (true) {
            if (this.lookahead < MIN_LOOKAHEAD) {
                fill_window();
                if (this.lookahead < MIN_LOOKAHEAD && i == 0) {
                    return 0;
                }
                if (this.lookahead == 0) {
                    if (this.match_available != 0) {
                        _tr_tally(0, this.window[this.strstart - 1] & 255);
                        this.match_available = 0;
                    }
                    flush_block_only(i == 4);
                    return this.strm.avail_out == 0 ? i == 4 ? 2 : 0 : i == 4 ? 3 : 1;
                }
            }
            if (this.lookahead >= 3) {
                int i4 = this.ins_h << this.hash_shift;
                byte[] bArr = this.window;
                int i5 = this.strstart;
                this.ins_h = (i4 ^ (bArr[(i5 + 3) - 1] & 255)) & this.hash_mask;
                short[] sArr = this.head;
                int i6 = this.ins_h;
                int i7 = sArr[i6] & UShort.MAX_VALUE;
                this.prev[this.w_mask & i5] = sArr[i6];
                sArr[i6] = (short) i5;
                i3 = i7;
            }
            this.prev_length = this.match_length;
            this.prev_match = this.match_start;
            this.match_length = 2;
            if (i3 != 0 && this.prev_length < this.max_lazy_match && ((this.strstart - i3) & 65535) <= this.w_size - MIN_LOOKAHEAD) {
                if (this.strategy != 2) {
                    this.match_length = longest_match(i3);
                }
                int i8 = this.match_length;
                if (i8 <= 5 && (this.strategy == 1 || (i8 == 3 && this.strstart - this.match_start > 4096))) {
                    this.match_length = 2;
                }
            }
            int i9 = this.prev_length;
            if (i9 >= 3 && this.match_length <= i9) {
                int i10 = this.strstart;
                int i11 = (this.lookahead + i10) - 3;
                boolean _tr_tally = _tr_tally((i10 - 1) - this.prev_match, i9 - 3);
                int i12 = this.lookahead;
                int i13 = this.prev_length;
                this.lookahead = i12 - (i13 - 1);
                this.prev_length = i13 - 2;
                do {
                    int i14 = this.strstart + 1;
                    this.strstart = i14;
                    if (i14 <= i11) {
                        int i15 = this.ins_h << this.hash_shift;
                        byte[] bArr2 = this.window;
                        int i16 = this.strstart;
                        this.ins_h = (i15 ^ (bArr2[(i16 + 3) - 1] & 255)) & this.hash_mask;
                        short[] sArr2 = this.head;
                        int i17 = this.ins_h;
                        int i18 = sArr2[i17] & UShort.MAX_VALUE;
                        this.prev[this.w_mask & i16] = sArr2[i17];
                        sArr2[i17] = (short) i16;
                        i3 = i18;
                    }
                    i2 = this.prev_length - 1;
                    this.prev_length = i2;
                } while (i2 != 0);
                this.match_available = 0;
                this.match_length = 2;
                this.strstart++;
                if (_tr_tally) {
                    flush_block_only(false);
                    if (this.strm.avail_out == 0) {
                        return 0;
                    }
                } else {
                    continue;
                }
            } else if (this.match_available != 0) {
                if (_tr_tally(0, this.window[this.strstart - 1] & 255)) {
                    flush_block_only(false);
                }
                this.strstart++;
                this.lookahead--;
                if (this.strm.avail_out == 0) {
                    return 0;
                }
            } else {
                this.match_available = 1;
                this.strstart++;
                this.lookahead--;
            }
        }
    }

    private int longest_match(int i) {
        int i2;
        int i3 = this.max_chain_length;
        int i4 = this.strstart;
        int i5 = this.prev_length;
        int i6 = this.w_size;
        int i7 = i4 > i6 + (-262) ? i4 - (i6 - 262) : 0;
        int i8 = this.nice_match;
        int i9 = this.w_mask;
        int i10 = this.strstart + 258;
        byte[] bArr = this.window;
        int i11 = i4 + i5;
        byte b = bArr[i11 - 1];
        byte b2 = bArr[i11];
        if (this.prev_length >= this.good_match) {
            i3 >>= 2;
        }
        int i12 = this.lookahead;
        if (i8 > i12) {
            i8 = i12;
        }
        do {
            byte[] bArr2 = this.window;
            int i13 = i + i5;
            if (bArr2[i13] == b2 && bArr2[i13 - 1] == b && bArr2[i] == bArr2[i4]) {
                int i14 = i + 1;
                if (bArr2[i14] == bArr2[i4 + 1]) {
                    int i15 = i4 + 2;
                    int i16 = i14 + 1;
                    do {
                        byte[] bArr3 = this.window;
                        i15++;
                        int i17 = i16 + 1;
                        if (bArr3[i15] != bArr3[i17]) {
                            break;
                        }
                        i15++;
                        int i18 = i17 + 1;
                        if (bArr3[i15] != bArr3[i18]) {
                            break;
                        }
                        i15++;
                        int i19 = i18 + 1;
                        if (bArr3[i15] != bArr3[i19]) {
                            break;
                        }
                        i15++;
                        int i20 = i19 + 1;
                        if (bArr3[i15] != bArr3[i20]) {
                            break;
                        }
                        i15++;
                        int i21 = i20 + 1;
                        if (bArr3[i15] != bArr3[i21]) {
                            break;
                        }
                        i15++;
                        int i22 = i21 + 1;
                        if (bArr3[i15] != bArr3[i22]) {
                            break;
                        }
                        i15++;
                        int i23 = i22 + 1;
                        if (bArr3[i15] != bArr3[i23]) {
                            break;
                        }
                        i15++;
                        i16 = i23 + 1;
                        if (bArr3[i15] != bArr3[i16]) {
                            break;
                        }
                    } while (i15 < i10);
                    i2 = 258 - (i10 - i15);
                    int i24 = i10 - 258;
                    if (i2 > i5) {
                        this.match_start = i;
                        if (i2 >= i8) {
                            break;
                        }
                        byte[] bArr4 = this.window;
                        int i25 = i24 + i2;
                        b = bArr4[i25 - 1];
                        b2 = bArr4[i25];
                        i5 = i2;
                    }
                    i4 = i24;
                }
            }
            i = this.prev[i & i9] & UShort.MAX_VALUE;
            if (i <= i7) {
                break;
            }
            i3--;
        } while (i3 != 0);
        i2 = i5;
        int i26 = this.lookahead;
        return i2 <= i26 ? i2 : i26;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deflateInit(ZStream zStream, int i, int i2, int i3, JZlib.WrapperType wrapperType) {
        return deflateInit2(zStream, i, 8, i2, i3, 0, wrapperType);
    }

    private int deflateInit2(ZStream zStream, int i, int i2, int i3, int i4, int i5, JZlib.WrapperType wrapperType) {
        if (wrapperType == JZlib.WrapperType.ZLIB_OR_NONE) {
            throw new IllegalArgumentException("ZLIB_OR_NONE allowed only for inflate");
        }
        zStream.msg = null;
        if (i == -1) {
            i = 6;
        }
        if (i3 < 0) {
            throw new IllegalArgumentException("windowBits: " + i3);
        }
        if (i4 < 1 || i4 > 9 || i2 != 8 || i3 < 9 || i3 > 15 || i < 0 || i > 9 || i5 < 0 || i5 > 2) {
            return -2;
        }
        zStream.dstate = this;
        this.wrapperType = wrapperType;
        this.w_bits = i3;
        this.w_size = 1 << this.w_bits;
        int i6 = this.w_size;
        this.w_mask = i6 - 1;
        this.hash_bits = i4 + 7;
        int i7 = this.hash_bits;
        this.hash_size = 1 << i7;
        int i8 = this.hash_size;
        this.hash_mask = i8 - 1;
        this.hash_shift = ((i7 + 3) - 1) / 3;
        this.window = new byte[i6 * 2];
        this.prev = new short[i6];
        this.head = new short[i8];
        this.lit_bufsize = 1 << (i4 + 6);
        int i9 = this.lit_bufsize;
        this.pending_buf = new byte[i9 * 4];
        this.pending_buf_size = i9 * 4;
        this.d_buf = i9 / 2;
        this.l_buf = i9 * 3;
        this.level = i;
        this.strategy = i5;
        return deflateReset(zStream);
    }

    private int deflateReset(ZStream zStream) {
        zStream.total_out = 0L;
        zStream.total_in = 0L;
        zStream.msg = null;
        this.pending = 0;
        this.pending_out = 0;
        this.wroteTrailer = false;
        this.status = this.wrapperType == JZlib.WrapperType.NONE ? 113 : 42;
        zStream.adler = Adler32.adler32(0L, null, 0, 0);
        zStream.crc32 = 0;
        this.gzipUncompressedBytes = 0;
        this.last_flush = 0;
        tr_init();
        lm_init();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deflateEnd() {
        int i = this.status;
        if (i != 42 && i != 113 && i != FINISH_STATE) {
            return -2;
        }
        this.pending_buf = null;
        this.head = null;
        this.prev = null;
        this.window = null;
        return this.status == 113 ? -3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deflateParams(ZStream zStream, int i, int i2) {
        if (i == -1) {
            i = 6;
        }
        if (i < 0 || i > 9 || i2 < 0 || i2 > 2) {
            return -2;
        }
        int deflate = (config_table[this.level].func == config_table[i].func || zStream.total_in == 0) ? 0 : zStream.deflate(1);
        if (this.level != i) {
            this.level = i;
            this.max_lazy_match = config_table[this.level].max_lazy;
            this.good_match = config_table[this.level].good_length;
            this.nice_match = config_table[this.level].nice_length;
            this.max_chain_length = config_table[this.level].max_chain;
        }
        this.strategy = i2;
        return deflate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deflateSetDictionary(ZStream zStream, byte[] bArr, int i) {
        int i2;
        int i3;
        if (bArr == null || this.status != 42) {
            return -2;
        }
        zStream.adler = Adler32.adler32(zStream.adler, bArr, 0, i);
        if (i < 3) {
            return 0;
        }
        int i4 = this.w_size;
        if (i > i4 - 262) {
            i2 = i4 - 262;
            i3 = i - i2;
        } else {
            i2 = i;
            i3 = 0;
        }
        System.arraycopy(bArr, i3, this.window, 0, i2);
        this.strstart = i2;
        this.block_start = i2;
        byte[] bArr2 = this.window;
        this.ins_h = bArr2[0] & 255;
        this.ins_h = ((bArr2[1] & 255) ^ (this.ins_h << this.hash_shift)) & this.hash_mask;
        for (int i5 = 0; i5 <= i2 - 3; i5++) {
            this.ins_h = ((this.ins_h << this.hash_shift) ^ (this.window[(i5 + 3) - 1] & 255)) & this.hash_mask;
            short[] sArr = this.prev;
            int i6 = this.w_mask & i5;
            short[] sArr2 = this.head;
            int i7 = this.ins_h;
            sArr[i6] = sArr2[i7];
            sArr2[i7] = (short) i5;
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int deflate(ZStream zStream, int i) {
        int deflate_stored;
        if (i <= 4 && i >= 0) {
            if (zStream.next_out == null || ((zStream.next_in == null && zStream.avail_in != 0) || (this.status == FINISH_STATE && i != 4))) {
                zStream.msg = z_errmsg[4];
            } else {
                if (zStream.avail_out == 0) {
                    zStream.msg = z_errmsg[7];
                    return -5;
                }
                this.strm = zStream;
                int i2 = this.last_flush;
                this.last_flush = i;
                if (this.status == 42) {
                    int i3 = C87441.$SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[this.wrapperType.ordinal()];
                    if (i3 == 1) {
                        int i4 = (((this.w_bits - 8) << 4) + 8) << 8;
                        int i5 = ((this.level - 1) & 255) >> 1;
                        if (i5 > 3) {
                            i5 = 3;
                        }
                        int i6 = i4 | (i5 << 6);
                        if (this.strstart != 0) {
                            i6 |= 32;
                        }
                        putShortMSB(i6 + (31 - (i6 % 31)));
                        if (this.strstart != 0) {
                            putShortMSB((int) (zStream.adler >>> 16));
                            putShortMSB((int) (zStream.adler & 65535));
                        }
                        zStream.adler = Adler32.adler32(0L, null, 0, 0);
                    } else if (i3 == 2) {
                        put_byte(Ascii.f1926US);
                        put_byte((byte) -117);
                        put_byte((byte) 8);
                        put_byte((byte) 0);
                        put_byte((byte) 0);
                        put_byte((byte) 0);
                        put_byte((byte) 0);
                        put_byte((byte) 0);
                        int i7 = config_table[this.level].func;
                        if (i7 == 1) {
                            put_byte((byte) 4);
                        } else if (i7 == 2) {
                            put_byte((byte) 2);
                        } else {
                            put_byte((byte) 0);
                        }
                        put_byte((byte) -1);
                        zStream.crc32 = 0;
                    }
                    this.status = 113;
                }
                if (this.pending != 0) {
                    zStream.flush_pending();
                    if (zStream.avail_out == 0) {
                        this.last_flush = -1;
                        return 0;
                    }
                } else if (zStream.avail_in == 0 && i <= i2 && i != 4) {
                    zStream.msg = z_errmsg[7];
                    return -5;
                }
                if (this.status == FINISH_STATE && zStream.avail_in != 0) {
                    zStream.msg = z_errmsg[7];
                    return -5;
                }
                int i8 = zStream.next_in_index;
                try {
                    if (zStream.avail_in != 0 || this.lookahead != 0 || (i != 0 && this.status != FINISH_STATE)) {
                        int i9 = config_table[this.level].func;
                        if (i9 == 0) {
                            deflate_stored = deflate_stored(i);
                        } else if (i9 == 1) {
                            deflate_stored = deflate_fast(i);
                        } else {
                            deflate_stored = i9 != 2 ? -1 : deflate_slow(i);
                        }
                        if (deflate_stored == 2 || deflate_stored == 3) {
                            this.status = FINISH_STATE;
                        }
                        if (deflate_stored != 0 && deflate_stored != 2) {
                            if (deflate_stored == 1) {
                                if (i == 1) {
                                    _tr_align();
                                } else {
                                    _tr_stored_block(0, 0, false);
                                    if (i == 3) {
                                        for (int i10 = 0; i10 < this.hash_size; i10++) {
                                            this.head[i10] = 0;
                                        }
                                    }
                                }
                                zStream.flush_pending();
                                if (zStream.avail_out == 0) {
                                    this.last_flush = -1;
                                    return 0;
                                }
                            }
                        }
                        if (zStream.avail_out == 0) {
                            this.last_flush = -1;
                        }
                        return 0;
                    }
                    if (i != 4) {
                        return 0;
                    }
                    if (this.wrapperType == JZlib.WrapperType.NONE || this.wroteTrailer) {
                        return 1;
                    }
                    int i11 = C87441.$SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[this.wrapperType.ordinal()];
                    if (i11 == 1) {
                        putShortMSB((int) (zStream.adler >>> 16));
                        putShortMSB((int) (zStream.adler & 65535));
                    } else if (i11 == 2) {
                        put_byte((byte) (zStream.crc32 & 255));
                        put_byte((byte) ((zStream.crc32 >>> 8) & 255));
                        put_byte((byte) ((zStream.crc32 >>> 16) & 255));
                        put_byte((byte) ((zStream.crc32 >>> 24) & 255));
                        put_byte((byte) (this.gzipUncompressedBytes & 255));
                        put_byte((byte) ((this.gzipUncompressedBytes >>> 8) & 255));
                        put_byte((byte) ((this.gzipUncompressedBytes >>> 16) & 255));
                        put_byte((byte) ((this.gzipUncompressedBytes >>> 24) & 255));
                    }
                    zStream.flush_pending();
                    this.wroteTrailer = true;
                    return this.pending != 0 ? 0 : 1;
                } finally {
                    this.gzipUncompressedBytes += zStream.next_in_index - i8;
                }
            }
        }
        return -2;
    }

    /* renamed from: org.jboss.netty.util.internal.jzlib.Deflate$1 */
    /* loaded from: classes7.dex */
    static /* synthetic */ class C87441 {
        static final /* synthetic */ int[] $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType = new int[JZlib.WrapperType.values().length];

        static {
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.ZLIB.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$jboss$netty$util$internal$jzlib$JZlib$WrapperType[JZlib.WrapperType.GZIP.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }
}
