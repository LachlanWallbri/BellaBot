package org.bouncycastle.pqc.crypto.qteslarnd1;

/* loaded from: classes9.dex */
final class Parameter {
    public static final int BARRETT_DIVISION_I = 32;
    public static final int BARRETT_DIVISION_III_P = 34;
    public static final int BARRETT_DIVISION_III_SIZE = 32;
    public static final int BARRETT_DIVISION_III_SPEED = 32;
    public static final int BARRETT_DIVISION_I_P = 29;
    public static final int BARRETT_MULTIPLICATION_I = 1021;
    public static final int BARRETT_MULTIPLICATION_III_P = 15;
    public static final int BARRETT_MULTIPLICATION_III_SIZE = 1021;
    public static final int BARRETT_MULTIPLICATION_III_SPEED = 511;
    public static final int BARRETT_MULTIPLICATION_I_P = 1;
    public static final int B_BIT_I = 20;
    public static final int B_BIT_III_P = 23;
    public static final int B_BIT_III_SIZE = 20;
    public static final int B_BIT_III_SPEED = 21;
    public static final int B_BIT_I_P = 21;
    public static final int B_I = 1048575;
    public static final int B_III_P = 8388607;
    public static final int B_III_SIZE = 1048575;
    public static final int B_III_SPEED = 2097151;
    public static final int B_I_P = 2097151;
    public static final int D_I = 21;
    public static final int D_III_P = 24;
    public static final int D_III_SIZE = 21;
    public static final int D_III_SPEED = 22;
    public static final int D_I_P = 22;
    public static final int GENERATOR_A_I = 19;
    public static final int GENERATOR_A_III_P = 180;
    public static final int GENERATOR_A_III_SIZE = 38;
    public static final int GENERATOR_A_III_SPEED = 38;
    public static final int GENERATOR_A_I_P = 108;
    public static final int H_I = 30;
    public static final int H_III_P = 40;
    public static final int H_III_SIZE = 48;
    public static final int H_III_SPEED = 48;
    public static final int H_I_P = 25;
    public static final int INVERSE_NUMBER_THEORETIC_TRANSFORM_I = 113307;
    public static final int INVERSE_NUMBER_THEORETIC_TRANSFORM_III_P = 851423148;
    public static final int INVERSE_NUMBER_THEORETIC_TRANSFORM_III_SIZE = 1217638;
    public static final int INVERSE_NUMBER_THEORETIC_TRANSFORM_III_SPEED = 237839;
    public static final int INVERSE_NUMBER_THEORETIC_TRANSFORM_I_P = 472064468;
    public static final int KEY_GENERATOR_BOUND_E_I = 1586;
    public static final int KEY_GENERATOR_BOUND_E_III_P = 901;
    public static final int KEY_GENERATOR_BOUND_E_III_SIZE = 910;
    public static final int KEY_GENERATOR_BOUND_E_III_SPEED = 1147;
    public static final int KEY_GENERATOR_BOUND_E_I_P = 554;
    public static final int KEY_GENERATOR_BOUND_S_I = 1586;
    public static final int KEY_GENERATOR_BOUND_S_III_P = 901;
    public static final int KEY_GENERATOR_BOUND_S_III_SIZE = 910;
    public static final int KEY_GENERATOR_BOUND_S_III_SPEED = 1233;
    public static final int KEY_GENERATOR_BOUND_S_I_P = 554;
    public static final int K_I = 1;
    public static final int K_III_P = 5;
    public static final int K_III_SIZE = 1;
    public static final int K_III_SPEED = 1;
    public static final int K_I_P = 4;
    public static final int N_I = 512;
    public static final int N_III_P = 2048;
    public static final int N_III_SIZE = 1024;
    public static final int N_III_SPEED = 1024;
    public static final int N_I_P = 1024;
    public static final int N_LOGARITHM_I = 9;
    public static final int N_LOGARITHM_III_P = 11;
    public static final int N_LOGARITHM_III_SIZE = 10;
    public static final int N_LOGARITHM_III_SPEED = 10;
    public static final int N_LOGARITHM_I_P = 10;
    public static final int Q_I = 4205569;
    public static final int Q_III_P = 1129725953;
    public static final int Q_III_SIZE = 4206593;
    public static final int Q_III_SPEED = 8404993;
    public static final long Q_INVERSE_I = 3098553343L;
    public static final long Q_INVERSE_III_P = 861290495;
    public static final long Q_INVERSE_III_SIZE = 4148178943L;
    public static final long Q_INVERSE_III_SPEED = 4034936831L;
    public static final long Q_INVERSE_I_P = 3421990911L;
    public static final int Q_I_P = 485978113;
    public static final int Q_LOGARITHM_I = 23;
    public static final int Q_LOGARITHM_III_P = 31;
    public static final int Q_LOGARITHM_III_SIZE = 23;
    public static final int Q_LOGARITHM_III_SPEED = 24;
    public static final int Q_LOGARITHM_I_P = 29;
    public static final int REJECTION_I = 1586;
    public static final int REJECTION_III_P = 901;
    public static final int REJECTION_III_SIZE = 910;
    public static final int REJECTION_III_SPEED = 1147;
    public static final int REJECTION_I_P = 554;
    public static final int R_I = 1081347;
    public static final int R_III_SIZE = 35843;
    public static final int R_III_SPEED = 15873;
    public static final double SIGMA_E_I = 22.93d;
    public static final double SIGMA_E_III_P = 8.5d;
    public static final double SIGMA_E_III_SIZE = 7.64d;
    public static final double SIGMA_E_III_SPEED = 10.2d;
    public static final double SIGMA_E_I_P = 8.5d;
    public static final double SIGMA_I = 22.93d;
    public static final double SIGMA_III_P = 8.5d;
    public static final double SIGMA_III_SIZE = 7.64d;
    public static final double SIGMA_III_SPEED = 10.2d;
    public static final double SIGMA_I_P = 8.5d;
    public static final int S_BIT_I = 10;
    public static final int S_BIT_III_P = 8;
    public static final int S_BIT_III_SIZE = 8;
    public static final int S_BIT_III_SPEED = 9;
    public static final int S_BIT_I_P = 8;
    public static final int U_I = 1586;
    public static final int U_III_P = 901;
    public static final int U_III_SIZE = 910;
    public static final int U_III_SPEED = 1233;
    public static final int U_I_P = 554;
    public static final double XI_I = 27.0d;
    public static final double XI_III_P = 10.0d;
    public static final double XI_III_SIZE = 9.0d;
    public static final double XI_III_SPEED = 12.0d;
    public static final double XI_I_P = 10.0d;

    Parameter() {
    }
}
