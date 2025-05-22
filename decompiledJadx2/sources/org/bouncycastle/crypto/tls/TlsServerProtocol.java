package org.bouncycastle.crypto.tls;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.SecureRandom;
import java.util.Vector;
import org.bouncycastle.crypto.params.AsymmetricKeyParameter;
import org.bouncycastle.crypto.tls.TlsProtocol;
import org.bouncycastle.crypto.util.PublicKeyFactory;
import org.bouncycastle.util.Arrays;

/* loaded from: classes9.dex */
public class TlsServerProtocol extends TlsProtocol {
    protected CertificateRequest certificateRequest;
    protected short clientCertificateType;
    protected TlsKeyExchange keyExchange;
    protected TlsHandshakeHash prepareFinishHash;
    protected TlsCredentials serverCredentials;
    protected TlsServer tlsServer;
    TlsServerContextImpl tlsServerContext;

    public TlsServerProtocol(InputStream inputStream, OutputStream outputStream, SecureRandom secureRandom) {
        super(inputStream, outputStream, secureRandom);
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }

    public TlsServerProtocol(SecureRandom secureRandom) {
        super(secureRandom);
        this.tlsServer = null;
        this.tlsServerContext = null;
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.clientCertificateType = (short) -1;
        this.prepareFinishHash = null;
    }

    public void accept(TlsServer tlsServer) throws IOException {
        if (tlsServer == null) {
            throw new IllegalArgumentException("'tlsServer' cannot be null");
        }
        if (this.tlsServer != null) {
            throw new IllegalStateException("'accept' can only be called once");
        }
        this.tlsServer = tlsServer;
        this.securityParameters = new SecurityParameters();
        this.securityParameters.entity = 0;
        this.tlsServerContext = new TlsServerContextImpl(this.secureRandom, this.securityParameters);
        this.securityParameters.serverRandom = createRandomBlock(tlsServer.shouldUseGMTUnixTime(), this.tlsServerContext.getNonceRandomGenerator());
        this.tlsServer.init(this.tlsServerContext);
        this.recordStream.init(this.tlsServerContext);
        tlsServer.notifyCloseHandle(this);
        this.recordStream.setRestrictReadVersion(false);
        blockForHandshake();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    public void cleanupHandshake() {
        super.cleanupHandshake();
        this.keyExchange = null;
        this.serverCredentials = null;
        this.certificateRequest = null;
        this.prepareFinishHash = null;
    }

    protected boolean expectCertificateVerifyMessage() {
        short s = this.clientCertificateType;
        return s >= 0 && TlsUtils.hasSigningCapability(s);
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    protected TlsContext getContext() {
        return this.tlsServerContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    AbstractTlsContext getContextAdmin() {
        return this.tlsServerContext;
    }

    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    protected TlsPeer getPeer() {
        return this.tlsServer;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0020, code lost:
    
        if (r3 == 9) goto L15;
     */
    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void handleAlertWarningMessage(short s) throws IOException {
        super.handleAlertWarningMessage(s);
        if (s != 41) {
            return;
        }
        if (TlsUtils.isSSL(getContext()) && this.certificateRequest != null) {
            short s2 = this.connection_state;
            if (s2 == 8) {
                this.tlsServer.processClientSupplementalData(null);
            }
            notifyClientCertificate(Certificate.EMPTY_CHAIN);
            this.connection_state = (short) 10;
            return;
        }
        throw new TlsFatalAlert((short) 10);
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:12:0x0022. Please report as an issue. */
    @Override // org.bouncycastle.crypto.tls.TlsProtocol
    protected void handleHandshakeMessage(short s, ByteArrayInputStream byteArrayInputStream) throws IOException {
        CertificateStatus certificateStatus;
        Certificate certificate = null;
        if (s == 1) {
            short s2 = this.connection_state;
            if (s2 != 0) {
                if (s2 != 16) {
                    throw new TlsFatalAlert((short) 10);
                }
                refuseRenegotiation();
                return;
            }
            receiveClientHelloMessage(byteArrayInputStream);
            this.connection_state = (short) 1;
            sendServerHelloMessage();
            this.connection_state = (short) 2;
            this.recordStream.notifyHelloComplete();
            Vector serverSupplementalData = this.tlsServer.getServerSupplementalData();
            if (serverSupplementalData != null) {
                sendSupplementalDataMessage(serverSupplementalData);
            }
            this.connection_state = (short) 3;
            this.keyExchange = this.tlsServer.getKeyExchange();
            this.keyExchange.init(getContext());
            this.serverCredentials = this.tlsServer.getCredentials();
            TlsCredentials tlsCredentials = this.serverCredentials;
            if (tlsCredentials == null) {
                this.keyExchange.skipServerCredentials();
            } else {
                this.keyExchange.processServerCredentials(tlsCredentials);
                certificate = this.serverCredentials.getCertificate();
                sendCertificateMessage(certificate);
            }
            this.connection_state = (short) 4;
            if (certificate == null || certificate.isEmpty()) {
                this.allowCertificateStatus = false;
            }
            if (this.allowCertificateStatus && (certificateStatus = this.tlsServer.getCertificateStatus()) != null) {
                sendCertificateStatusMessage(certificateStatus);
            }
            this.connection_state = (short) 5;
            byte[] generateServerKeyExchange = this.keyExchange.generateServerKeyExchange();
            if (generateServerKeyExchange != null) {
                sendServerKeyExchangeMessage(generateServerKeyExchange);
            }
            this.connection_state = (short) 6;
            if (this.serverCredentials != null) {
                this.certificateRequest = this.tlsServer.getCertificateRequest();
                if (this.certificateRequest != null) {
                    if (TlsUtils.isTLSv12(getContext()) != (this.certificateRequest.getSupportedSignatureAlgorithms() != null)) {
                        throw new TlsFatalAlert((short) 80);
                    }
                    this.keyExchange.validateCertificateRequest(this.certificateRequest);
                    sendCertificateRequestMessage(this.certificateRequest);
                    TlsUtils.trackHashAlgorithms(this.recordStream.getHandshakeHash(), this.certificateRequest.getSupportedSignatureAlgorithms());
                }
            }
            this.connection_state = (short) 7;
            sendServerHelloDoneMessage();
            this.connection_state = (short) 8;
            this.recordStream.getHandshakeHash().sealHashAlgorithms();
            return;
        }
        if (s == 11) {
            short s3 = this.connection_state;
            if (s3 == 8) {
                this.tlsServer.processClientSupplementalData(null);
            } else if (s3 != 9) {
                throw new TlsFatalAlert((short) 10);
            }
            if (this.certificateRequest == null) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveCertificateMessage(byteArrayInputStream);
            this.connection_state = (short) 10;
            return;
        }
        if (s == 20) {
            short s4 = this.connection_state;
            if (s4 != 11) {
                if (s4 != 12) {
                    throw new TlsFatalAlert((short) 10);
                }
            } else if (expectCertificateVerifyMessage()) {
                throw new TlsFatalAlert((short) 10);
            }
            processFinishedMessage(byteArrayInputStream);
            this.connection_state = (short) 13;
            if (this.expectSessionTicket) {
                sendNewSessionTicketMessage(this.tlsServer.getNewSessionTicket());
            }
            this.connection_state = (short) 14;
            sendChangeCipherSpecMessage();
            sendFinishedMessage();
            this.connection_state = (short) 15;
            completeHandshake();
            return;
        }
        if (s == 23) {
            if (this.connection_state != 8) {
                throw new TlsFatalAlert((short) 10);
            }
            this.tlsServer.processClientSupplementalData(readSupplementalDataMessage(byteArrayInputStream));
            this.connection_state = (short) 9;
            return;
        }
        if (s == 15) {
            if (this.connection_state != 11) {
                throw new TlsFatalAlert((short) 10);
            }
            if (!expectCertificateVerifyMessage()) {
                throw new TlsFatalAlert((short) 10);
            }
            receiveCertificateVerifyMessage(byteArrayInputStream);
            this.connection_state = (short) 12;
            return;
        }
        if (s != 16) {
            throw new TlsFatalAlert((short) 10);
        }
        switch (this.connection_state) {
            case 8:
                this.tlsServer.processClientSupplementalData(null);
            case 9:
                if (this.certificateRequest == null) {
                    this.keyExchange.skipClientCredentials();
                } else {
                    if (TlsUtils.isTLSv12(getContext())) {
                        throw new TlsFatalAlert((short) 10);
                    }
                    if (!TlsUtils.isSSL(getContext())) {
                        notifyClientCertificate(Certificate.EMPTY_CHAIN);
                    } else if (this.peerCertificate == null) {
                        throw new TlsFatalAlert((short) 10);
                    }
                }
            case 10:
                receiveClientKeyExchangeMessage(byteArrayInputStream);
                this.connection_state = (short) 11;
                return;
            default:
                throw new TlsFatalAlert((short) 10);
        }
    }

    protected void notifyClientCertificate(Certificate certificate) throws IOException {
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        if (this.peerCertificate != null) {
            throw new TlsFatalAlert((short) 10);
        }
        this.peerCertificate = certificate;
        if (certificate.isEmpty()) {
            this.keyExchange.skipClientCredentials();
        } else {
            this.clientCertificateType = TlsUtils.getClientCertificateType(certificate, this.serverCredentials.getCertificate());
            this.keyExchange.processClientCertificate(certificate);
        }
        this.tlsServer.notifyClientCertificate(certificate);
    }

    protected void receiveCertificateMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        Certificate parse = Certificate.parse(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        notifyClientCertificate(parse);
    }

    protected void receiveCertificateVerifyMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        byte[] sessionHash;
        if (this.certificateRequest == null) {
            throw new IllegalStateException();
        }
        DigitallySigned parse = DigitallySigned.parse(getContext(), byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        try {
            SignatureAndHashAlgorithm algorithm = parse.getAlgorithm();
            if (TlsUtils.isTLSv12(getContext())) {
                TlsUtils.verifySupportedSignatureAlgorithm(this.certificateRequest.getSupportedSignatureAlgorithms(), algorithm);
                sessionHash = this.prepareFinishHash.getFinalHash(algorithm.getHash());
            } else {
                sessionHash = this.securityParameters.getSessionHash();
            }
            AsymmetricKeyParameter createKey = PublicKeyFactory.createKey(this.peerCertificate.getCertificateAt(0).getSubjectPublicKeyInfo());
            TlsSigner createTlsSigner = TlsUtils.createTlsSigner(this.clientCertificateType);
            createTlsSigner.init(getContext());
            if (createTlsSigner.verifyRawSignature(algorithm, parse.getSignature(), createKey, sessionHash)) {
            } else {
                throw new TlsFatalAlert((short) 51);
            }
        } catch (TlsFatalAlert e) {
            throw e;
        } catch (Exception e2) {
            throw new TlsFatalAlert((short) 51, e2);
        }
    }

    protected void receiveClientHelloMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        ProtocolVersion readVersion = TlsUtils.readVersion(byteArrayInputStream);
        this.recordStream.setWriteVersion(readVersion);
        if (readVersion.isDTLS()) {
            throw new TlsFatalAlert((short) 47);
        }
        byte[] readFully = TlsUtils.readFully(32, byteArrayInputStream);
        if (TlsUtils.readOpaque8(byteArrayInputStream).length > 32) {
            throw new TlsFatalAlert((short) 47);
        }
        int readUint16 = TlsUtils.readUint16(byteArrayInputStream);
        if (readUint16 < 2 || (readUint16 & 1) != 0) {
            throw new TlsFatalAlert((short) 50);
        }
        this.offeredCipherSuites = TlsUtils.readUint16Array(readUint16 / 2, byteArrayInputStream);
        short readUint8 = TlsUtils.readUint8(byteArrayInputStream);
        if (readUint8 < 1) {
            throw new TlsFatalAlert((short) 47);
        }
        this.offeredCompressionMethods = TlsUtils.readUint8Array(readUint8, byteArrayInputStream);
        this.clientExtensions = readExtensions(byteArrayInputStream);
        this.securityParameters.extendedMasterSecret = TlsExtensionsUtils.hasExtendedMasterSecretExtension(this.clientExtensions);
        if (!this.securityParameters.isExtendedMasterSecret() && this.tlsServer.requiresExtendedMasterSecret()) {
            throw new TlsFatalAlert((short) 40);
        }
        getContextAdmin().setClientVersion(readVersion);
        this.tlsServer.notifyClientVersion(readVersion);
        this.tlsServer.notifyFallback(Arrays.contains(this.offeredCipherSuites, CipherSuite.TLS_FALLBACK_SCSV));
        this.securityParameters.clientRandom = readFully;
        this.tlsServer.notifyOfferedCipherSuites(this.offeredCipherSuites);
        this.tlsServer.notifyOfferedCompressionMethods(this.offeredCompressionMethods);
        if (Arrays.contains(this.offeredCipherSuites, 255)) {
            this.secure_renegotiation = true;
        }
        byte[] extensionData = TlsUtils.getExtensionData(this.clientExtensions, EXT_RenegotiationInfo);
        if (extensionData != null) {
            this.secure_renegotiation = true;
            if (!Arrays.constantTimeAreEqual(extensionData, createRenegotiationInfo(TlsUtils.EMPTY_BYTES))) {
                throw new TlsFatalAlert((short) 40);
            }
        }
        this.tlsServer.notifySecureRenegotiation(this.secure_renegotiation);
        if (this.clientExtensions != null) {
            TlsExtensionsUtils.getPaddingExtension(this.clientExtensions);
            this.tlsServer.processClientExtensions(this.clientExtensions);
        }
    }

    protected void receiveClientKeyExchangeMessage(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.keyExchange.processClientKeyExchange(byteArrayInputStream);
        assertEmpty(byteArrayInputStream);
        if (TlsUtils.isSSL(getContext())) {
            establishMasterSecret(getContext(), this.keyExchange);
        }
        this.prepareFinishHash = this.recordStream.prepareToFinish();
        this.securityParameters.sessionHash = getCurrentPRFHash(getContext(), this.prepareFinishHash, null);
        if (!TlsUtils.isSSL(getContext())) {
            establishMasterSecret(getContext(), this.keyExchange);
        }
        this.recordStream.setPendingConnectionState(getPeer().getCompression(), getPeer().getCipher());
    }

    protected void sendCertificateRequestMessage(CertificateRequest certificateRequest) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 13);
        certificateRequest.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendCertificateStatusMessage(CertificateStatus certificateStatus) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 22);
        certificateStatus.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendNewSessionTicketMessage(NewSessionTicket newSessionTicket) throws IOException {
        if (newSessionTicket == null) {
            throw new TlsFatalAlert((short) 80);
        }
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 4);
        newSessionTicket.encode(handshakeMessage);
        handshakeMessage.writeToRecordStream();
    }

    protected void sendServerHelloDoneMessage() throws IOException {
        byte[] bArr = new byte[4];
        TlsUtils.writeUint8((short) 14, bArr, 0);
        TlsUtils.writeUint24(0, bArr, 1);
        writeHandshakeMessage(bArr, 0, bArr.length);
    }

    protected void sendServerHelloMessage() throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage(this, (short) 2);
        ProtocolVersion serverVersion = this.tlsServer.getServerVersion();
        if (!serverVersion.isEqualOrEarlierVersionOf(getContext().getClientVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.recordStream.setReadVersion(serverVersion);
        this.recordStream.setWriteVersion(serverVersion);
        this.recordStream.setRestrictReadVersion(true);
        getContextAdmin().setServerVersion(serverVersion);
        TlsUtils.writeVersion(serverVersion, handshakeMessage);
        handshakeMessage.write(this.securityParameters.serverRandom);
        TlsUtils.writeOpaque8(TlsUtils.EMPTY_BYTES, handshakeMessage);
        int selectedCipherSuite = this.tlsServer.getSelectedCipherSuite();
        if (!Arrays.contains(this.offeredCipherSuites, selectedCipherSuite) || selectedCipherSuite == 0 || CipherSuite.isSCSV(selectedCipherSuite) || !TlsUtils.isValidCipherSuiteForVersion(selectedCipherSuite, getContext().getServerVersion())) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.cipherSuite = selectedCipherSuite;
        short selectedCompressionMethod = this.tlsServer.getSelectedCompressionMethod();
        if (!Arrays.contains(this.offeredCompressionMethods, selectedCompressionMethod)) {
            throw new TlsFatalAlert((short) 80);
        }
        this.securityParameters.compressionAlgorithm = selectedCompressionMethod;
        TlsUtils.writeUint16(selectedCipherSuite, handshakeMessage);
        TlsUtils.writeUint8(selectedCompressionMethod, (OutputStream) handshakeMessage);
        this.serverExtensions = TlsExtensionsUtils.ensureExtensionsInitialised(this.tlsServer.getServerExtensions());
        boolean z = false;
        if (this.secure_renegotiation) {
            if (TlsUtils.getExtensionData(this.serverExtensions, EXT_RenegotiationInfo) == null) {
                this.serverExtensions.put(EXT_RenegotiationInfo, createRenegotiationInfo(TlsUtils.EMPTY_BYTES));
            }
        }
        if (TlsUtils.isSSL(this.tlsServerContext)) {
            this.securityParameters.extendedMasterSecret = false;
        } else if (this.securityParameters.isExtendedMasterSecret()) {
            TlsExtensionsUtils.addExtendedMasterSecretExtension(this.serverExtensions);
        }
        if (!this.serverExtensions.isEmpty()) {
            this.securityParameters.encryptThenMAC = TlsExtensionsUtils.hasEncryptThenMACExtension(this.serverExtensions);
            this.securityParameters.maxFragmentLength = processMaxFragmentLengthExtension(this.clientExtensions, this.serverExtensions, (short) 80);
            this.securityParameters.truncatedHMac = TlsExtensionsUtils.hasTruncatedHMacExtension(this.serverExtensions);
            this.allowCertificateStatus = !this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsExtensionsUtils.EXT_status_request, (short) 80);
            if (!this.resumedSession && TlsUtils.hasExpectedEmptyExtensionData(this.serverExtensions, TlsProtocol.EXT_SessionTicket, (short) 80)) {
                z = true;
            }
            this.expectSessionTicket = z;
            writeExtensions(handshakeMessage, this.serverExtensions);
        }
        this.securityParameters.prfAlgorithm = getPRFAlgorithm(getContext(), this.securityParameters.getCipherSuite());
        this.securityParameters.verifyDataLength = 12;
        applyMaxFragmentLengthExtension();
        handshakeMessage.writeToRecordStream();
    }

    protected void sendServerKeyExchangeMessage(byte[] bArr) throws IOException {
        TlsProtocol.HandshakeMessage handshakeMessage = new TlsProtocol.HandshakeMessage((short) 12, bArr.length);
        handshakeMessage.write(bArr);
        handshakeMessage.writeToRecordStream();
    }
}
