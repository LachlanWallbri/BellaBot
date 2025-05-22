package com.amazonaws.handlers;

import com.amazonaws.AmazonClientException;
import com.amazonaws.util.ClassLoaderHelper;
import com.amazonaws.util.StringUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes.dex */
public class HandlerChainFactory {
    public List<RequestHandler2> newRequestHandlerChain(String str) {
        return createRequestHandlerChain(str, RequestHandler.class);
    }

    public List<RequestHandler2> newRequestHandler2Chain(String str) {
        return createRequestHandlerChain(str, RequestHandler2.class);
    }

    private List<RequestHandler2> createRequestHandlerChain(String str, Class<?> cls) {
        BufferedReader bufferedReader;
        ArrayList arrayList = new ArrayList();
        try {
            try {
                InputStream resourceAsStream = getClass().getResourceAsStream(str);
                if (resourceAsStream == null) {
                    return arrayList;
                }
                bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream, StringUtils.UTF8));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            String trim = readLine.trim();
                            if (!"".equals(trim)) {
                                Object newInstance = ClassLoaderHelper.loadClass(trim, cls, getClass()).newInstance();
                                if (cls.isInstance(newInstance)) {
                                    if (cls == RequestHandler2.class) {
                                        arrayList.add((RequestHandler2) newInstance);
                                    } else if (cls == RequestHandler.class) {
                                        arrayList.add(RequestHandler2.adapt((RequestHandler) newInstance));
                                    } else {
                                        throw new IllegalStateException();
                                    }
                                } else {
                                    throw new AmazonClientException("Unable to instantiate request handler chain for client.  Listed request handler ('" + trim + "') does not implement the " + cls + " API.");
                                }
                            }
                        } else {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused) {
                            }
                            return arrayList;
                        }
                    } catch (Exception e) {
                        e = e;
                        throw new AmazonClientException("Unable to instantiate request handler chain for client: " + e.getMessage(), e);
                    } catch (Throwable th) {
                        th = th;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (IOException unused2) {
                            }
                        }
                        throw th;
                    }
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (Throwable th2) {
            th = th2;
            bufferedReader = null;
        }
    }
}
