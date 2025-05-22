package org.bouncycastle.crypto.generators;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.aliyun.alink.dm.api.DMErrorCode;
import java.io.PrintStream;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Vector;
import org.apache.commons.compress.archivers.tar.TarConstants;
import org.apache.http.HttpStatus;
import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator;
import org.bouncycastle.crypto.KeyGenerationParameters;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.params.NaccacheSternKeyGenerationParameters;
import org.bouncycastle.crypto.params.NaccacheSternKeyParameters;
import org.bouncycastle.crypto.params.NaccacheSternPrivateKeyParameters;
import org.bouncycastle.math.Primes;
import org.bouncycastle.util.BigIntegers;

/* loaded from: classes9.dex */
public class NaccacheSternKeyPairGenerator implements AsymmetricCipherKeyPairGenerator {
    private NaccacheSternKeyGenerationParameters param;
    private static int[] smallPrimes = {3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199, Primes.SMALL_FACTOR_LIMIT, 223, 227, 229, 233, 239, 241, 251, 257, TarConstants.VERSION_OFFSET, 269, 271, 277, 281, 283, 293, 307, 311, 313, TypedValues.Attributes.TYPE_EASING, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397, 401, 409, HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, TypedValues.Cycle.TYPE_WAVE_SHAPE, 431, 433, 439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, DMErrorCode.ERROR_CMP_SEND_ERROR_CONNECT_NOT_CONNECTED, 523, 541, 547, 557};
    private static final BigInteger ONE = BigInteger.valueOf(1);

    private static Vector findFirstPrimes(int i) {
        Vector vector = new Vector(i);
        for (int i2 = 0; i2 != i; i2++) {
            vector.addElement(BigInteger.valueOf(smallPrimes[i2]));
        }
        return vector;
    }

    private static BigInteger generatePrime(int i, int i2, SecureRandom secureRandom) {
        BigInteger createRandomPrime;
        do {
            createRandomPrime = BigIntegers.createRandomPrime(i, i2, secureRandom);
        } while (createRandomPrime.bitLength() != i);
        return createRandomPrime;
    }

    private static int getInt(SecureRandom secureRandom, int i) {
        int nextInt;
        int i2;
        if (((-i) & i) == i) {
            return (int) ((i * (secureRandom.nextInt() & Integer.MAX_VALUE)) >> 31);
        }
        do {
            nextInt = secureRandom.nextInt() & Integer.MAX_VALUE;
            i2 = nextInt % i;
        } while ((nextInt - i2) + (i - 1) < 0);
        return i2;
    }

    private static Vector permuteList(Vector vector, SecureRandom secureRandom) {
        Vector vector2 = new Vector();
        Vector vector3 = new Vector();
        for (int i = 0; i < vector.size(); i++) {
            vector3.addElement(vector.elementAt(i));
        }
        vector2.addElement(vector3.elementAt(0));
        while (true) {
            vector3.removeElementAt(0);
            if (vector3.size() == 0) {
                return vector2;
            }
            vector2.insertElementAt(vector3.elementAt(0), getInt(secureRandom, vector2.size() + 1));
        }
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public AsymmetricCipherKeyPair generateKeyPair() {
        long j;
        BigInteger generatePrime;
        BigInteger add;
        BigInteger generatePrime2;
        BigInteger bigInteger;
        BigInteger add2;
        BigInteger bigInteger2;
        BigInteger bigInteger3;
        BigInteger bigInteger4;
        BigInteger bigInteger5;
        BigInteger bigInteger6;
        boolean z;
        BigInteger bigInteger7;
        PrintStream printStream;
        StringBuilder sb;
        String str;
        BigInteger createRandomPrime;
        int i;
        SecureRandom secureRandom;
        int strength = this.param.getStrength();
        SecureRandom random = this.param.getRandom();
        int certainty = this.param.getCertainty();
        boolean isDebug = this.param.isDebug();
        if (isDebug) {
            System.out.println("Fetching first " + this.param.getCntSmallPrimes() + " primes.");
        }
        Vector permuteList = permuteList(findFirstPrimes(this.param.getCntSmallPrimes()), random);
        BigInteger bigInteger8 = ONE;
        BigInteger bigInteger9 = bigInteger8;
        for (int i2 = 0; i2 < permuteList.size() / 2; i2++) {
            bigInteger9 = bigInteger9.multiply((BigInteger) permuteList.elementAt(i2));
        }
        for (int size = permuteList.size() / 2; size < permuteList.size(); size++) {
            bigInteger8 = bigInteger8.multiply((BigInteger) permuteList.elementAt(size));
        }
        BigInteger multiply = bigInteger9.multiply(bigInteger8);
        int bitLength = (((strength - multiply.bitLength()) - 48) / 2) + 1;
        BigInteger generatePrime3 = generatePrime(bitLength, certainty, random);
        BigInteger generatePrime4 = generatePrime(bitLength, certainty, random);
        if (isDebug) {
            System.out.println("generating p and q");
        }
        BigInteger shiftLeft = generatePrime3.multiply(bigInteger9).shiftLeft(1);
        BigInteger shiftLeft2 = generatePrime4.multiply(bigInteger8).shiftLeft(1);
        long j2 = 0;
        while (true) {
            j = j2 + 1;
            generatePrime = generatePrime(24, certainty, random);
            add = generatePrime.multiply(shiftLeft).add(ONE);
            if (add.isProbablePrime(certainty)) {
                while (true) {
                    do {
                        generatePrime2 = generatePrime(24, certainty, random);
                    } while (generatePrime.equals(generatePrime2));
                    bigInteger = shiftLeft2;
                    add2 = generatePrime2.multiply(shiftLeft2).add(ONE);
                    if (add2.isProbablePrime(certainty)) {
                        break;
                    }
                    shiftLeft2 = bigInteger;
                }
                bigInteger2 = shiftLeft;
                if (!multiply.gcd(generatePrime.multiply(generatePrime2)).equals(ONE)) {
                    continue;
                } else {
                    if (add.multiply(add2).bitLength() >= strength) {
                        break;
                    }
                    if (isDebug) {
                        System.out.println("key size too small. Should be " + strength + " but is actually " + add.multiply(add2).bitLength());
                    }
                }
            } else {
                bigInteger = shiftLeft2;
                bigInteger2 = shiftLeft;
            }
            j2 = j;
            shiftLeft2 = bigInteger;
            shiftLeft = bigInteger2;
        }
        if (isDebug) {
            bigInteger3 = generatePrime4;
            System.out.println("needed " + j + " tries to generate p and q.");
        } else {
            bigInteger3 = generatePrime4;
        }
        BigInteger multiply2 = add.multiply(add2);
        BigInteger multiply3 = add.subtract(ONE).multiply(add2.subtract(ONE));
        if (isDebug) {
            System.out.println("generating g");
        }
        long j3 = 0;
        while (true) {
            Vector vector = new Vector();
            bigInteger4 = add2;
            bigInteger5 = add;
            int i3 = 0;
            while (i3 != permuteList.size()) {
                BigInteger divide = multiply3.divide((BigInteger) permuteList.elementAt(i3));
                while (true) {
                    j3++;
                    createRandomPrime = BigIntegers.createRandomPrime(strength, certainty, random);
                    i = strength;
                    secureRandom = random;
                    if (createRandomPrime.modPow(divide, multiply2).equals(ONE)) {
                        strength = i;
                        random = secureRandom;
                    }
                }
                vector.addElement(createRandomPrime);
                i3++;
                strength = i;
                random = secureRandom;
            }
            int i4 = strength;
            SecureRandom secureRandom2 = random;
            bigInteger6 = ONE;
            for (int i5 = 0; i5 < permuteList.size(); i5++) {
                bigInteger6 = bigInteger6.multiply(((BigInteger) vector.elementAt(i5)).modPow(multiply.divide((BigInteger) permuteList.elementAt(i5)), multiply2)).mod(multiply2);
            }
            int i6 = 0;
            while (true) {
                if (i6 >= permuteList.size()) {
                    z = false;
                    break;
                }
                if (bigInteger6.modPow(multiply3.divide((BigInteger) permuteList.elementAt(i6)), multiply2).equals(ONE)) {
                    if (isDebug) {
                        System.out.println("g has order phi(n)/" + permuteList.elementAt(i6) + "\n g: " + bigInteger6);
                    }
                    z = true;
                } else {
                    i6++;
                }
            }
            if (!z) {
                if (!bigInteger6.modPow(multiply3.divide(BigInteger.valueOf(4L)), multiply2).equals(ONE)) {
                    if (!bigInteger6.modPow(multiply3.divide(generatePrime), multiply2).equals(ONE)) {
                        if (!bigInteger6.modPow(multiply3.divide(generatePrime2), multiply2).equals(ONE)) {
                            if (!bigInteger6.modPow(multiply3.divide(generatePrime3), multiply2).equals(ONE)) {
                                bigInteger7 = bigInteger3;
                                if (!bigInteger6.modPow(multiply3.divide(bigInteger7), multiply2).equals(ONE)) {
                                    break;
                                }
                                if (isDebug) {
                                    System.out.println("g has order phi(n)/b\n g: " + bigInteger6);
                                }
                                bigInteger3 = bigInteger7;
                                add = bigInteger5;
                                add2 = bigInteger4;
                                strength = i4;
                                random = secureRandom2;
                            } else if (isDebug) {
                                printStream = System.out;
                                sb = new StringBuilder();
                                str = "g has order phi(n)/a\n g: ";
                                sb.append(str);
                                sb.append(bigInteger6);
                                printStream.println(sb.toString());
                            }
                        } else if (isDebug) {
                            printStream = System.out;
                            sb = new StringBuilder();
                            str = "g has order phi(n)/q'\n g: ";
                            sb.append(str);
                            sb.append(bigInteger6);
                            printStream.println(sb.toString());
                        }
                    } else if (isDebug) {
                        printStream = System.out;
                        sb = new StringBuilder();
                        str = "g has order phi(n)/p'\n g: ";
                        sb.append(str);
                        sb.append(bigInteger6);
                        printStream.println(sb.toString());
                    }
                } else if (isDebug) {
                    printStream = System.out;
                    sb = new StringBuilder();
                    str = "g has order phi(n)/4\n g:";
                    sb.append(str);
                    sb.append(bigInteger6);
                    printStream.println(sb.toString());
                }
            }
            bigInteger7 = bigInteger3;
            bigInteger3 = bigInteger7;
            add = bigInteger5;
            add2 = bigInteger4;
            strength = i4;
            random = secureRandom2;
        }
        if (isDebug) {
            System.out.println("needed " + j3 + " tries to generate g");
            System.out.println();
            System.out.println("found new NaccacheStern cipher variables:");
            System.out.println("smallPrimes: " + permuteList);
            System.out.println("sigma:...... " + multiply + " (" + multiply.bitLength() + " bits)");
            PrintStream printStream2 = System.out;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("a:.......... ");
            sb2.append(generatePrime3);
            printStream2.println(sb2.toString());
            System.out.println("b:.......... " + bigInteger7);
            System.out.println("p':......... " + generatePrime);
            System.out.println("q':......... " + generatePrime2);
            System.out.println("p:.......... " + bigInteger5);
            System.out.println("q:.......... " + bigInteger4);
            System.out.println("n:.......... " + multiply2);
            System.out.println("phi(n):..... " + multiply3);
            System.out.println("g:.......... " + bigInteger6);
            System.out.println();
        }
        return new AsymmetricCipherKeyPair((AsymmetricKeyParameter) new NaccacheSternKeyParameters(false, bigInteger6, multiply2, multiply.bitLength()), (AsymmetricKeyParameter) new NaccacheSternPrivateKeyParameters(bigInteger6, multiply2, multiply.bitLength(), permuteList, multiply3));
    }

    @Override // org.bouncycastle.crypto.AsymmetricCipherKeyPairGenerator
    public void init(KeyGenerationParameters keyGenerationParameters) {
        this.param = (NaccacheSternKeyGenerationParameters) keyGenerationParameters;
    }
}
