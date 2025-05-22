package com.slamtec.slamware.internal;

import com.slamtec.slamware.AbstractSlamwarePlatform;
import com.slamtec.slamware.diagnosis.IMessageLogFetcher;
import com.slamtec.slamware.exceptions.ConnectionFailException;
import com.slamtec.slamware.exceptions.ConnectionLostException;
import com.slamtec.slamware.exceptions.ConnectionTimeOutException;
import com.slamtec.slamware.exceptions.CxxStdException;
import com.slamtec.slamware.exceptions.InvalidArgumentException;
import com.slamtec.slamware.exceptions.OperationFailException;
import com.slamtec.slamware.exceptions.ParseInvalidException;
import com.slamtec.slamware.exceptions.RequestFailException;
import com.slamtec.slamware.exceptions.UnauthorizedRequestException;
import com.slamtec.slamware.exceptions.UnsupportedCommandException;
import com.slamtec.slamware.robot.CompositeMap;
import com.slamtec.slamware.robot.GridMap;
import com.slamtec.slamware.robot.Location;
import com.slamtec.slamware.robot.Map;
import com.slamtec.slamware.robot.MapKind;
import com.slamtec.slamware.robot.MapLayer;
import com.slamtec.slamware.robot.MapType;
import com.slamtec.slamware.robot.OperationAuditLog;
import com.slamtec.slamware.sdp.CompositeMapHelper;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/* JADX WARN: Classes with same name are omitted:
  classes6.dex
  classes7.dex
 */
/* loaded from: classes2.dex */
public class SlamwareLogUtils {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
    private static final String FILE_ZIP_NAME = dateFormat.format(new Date()) + "-slamware_log.zip";

    public static synchronized String saveSlamwareLog(AbstractSlamwarePlatform abstractSlamwarePlatform, String str) {
        synchronized (SlamwareLogUtils.class) {
            File file = new File(str);
            if (!file.exists()) {
                file.mkdirs();
            }
            String str2 = str + "in_mem_log.stms";
            String str3 = str + "robot_maps.stcm";
            String str4 = str + "audit_log.log";
            String str5 = str + "sys_collect.log";
            if (saveHiddenMap(abstractSlamwarePlatform, str + "hidden_slam_map.stcm") && fetchInMemLog(abstractSlamwarePlatform, str2) && saveRobotMap(abstractSlamwarePlatform, str3) && getAuditLog(abstractSlamwarePlatform, str4) && fetchSystemLogs(str5)) {
                return compressFileList(Arrays.asList(file.listFiles()), str + FILE_ZIP_NAME);
            }
            List asList = Arrays.asList(file.listFiles());
            if (asList != null) {
                deleteAllLogFiles(asList);
            }
            return null;
        }
    }

    private static String compressFileList(List<File> list, String str) {
        if (list.isEmpty()) {
            return null;
        }
        try {
            File file = new File(str);
            if (file.exists()) {
                file.delete();
            }
            ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(str)));
            for (File file2 : list) {
                if (file2.getName().contains("audit_log.log") || file2.getName().contains("hidden_slam_map.stcm") || file2.getName().contains("in_mem_log.stms") || file2.getName().contains("robot_maps.stcm") || file2.getName().contains("sys_collect.log")) {
                    FileInputStream fileInputStream = new FileInputStream(file2);
                    zipOutputStream.putNextEntry(new ZipEntry(file2.getName()));
                    byte[] bArr = new byte[4096];
                    while (true) {
                        int read = fileInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        zipOutputStream.write(bArr, 0, read);
                    }
                    zipOutputStream.flush();
                    fileInputStream.close();
                }
            }
            zipOutputStream.close();
            deleteAllLogFiles(list);
            return str;
        } catch (FileNotFoundException unused) {
            deleteAllLogFiles(list);
            return null;
        } catch (IOException e) {
            deleteAllLogFiles(list);
            e.printStackTrace();
            return null;
        }
    }

    private static void deleteAllLogFiles(List<File> list) {
        if (list != null) {
            for (int i = 0; i < list.size(); i++) {
                File file = list.get(i);
                if (file.getName().contains("audit_log.log") || file.getName().contains("hidden_slam_map.stcm") || file.getName().contains("in_mem_log.stms") || file.getName().contains("robot_maps.stcm") || file.getName().contains("sys_collect.log")) {
                    list.get(i).delete();
                }
            }
        }
    }

    private static boolean getAuditLog(AbstractSlamwarePlatform abstractSlamwarePlatform, String str) {
        if (abstractSlamwarePlatform == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        File file = new File(str);
        if (file.exists()) {
            file.delete();
            new File(str);
        }
        try {
            List<OperationAuditLog> operationAuditLogs = abstractSlamwarePlatform.getOperationAuditLogs();
            if (operationAuditLogs == null) {
                return false;
            }
            for (int i = 0; i < operationAuditLogs.size(); i++) {
                OperationAuditLog operationAuditLog = operationAuditLogs.get(i);
                String str2 = "Peer IP:  " + operationAuditLog.getPeerIp() + "\n";
                String str3 = "Source:  " + operationAuditLog.getSource() + "\n";
                String str4 = "Level:  " + operationAuditLog.getOperationAuditLevel().name() + "\n";
                String str5 = "Timestamp:  " + operationAuditLog.getTimestampstr() + "\n";
                String str6 = "Content:  " + operationAuditLog.getContent() + "\n\n\n";
                sb.append(str2);
                sb.append(str3);
                sb.append(str4);
                sb.append(str5);
                sb.append(str6);
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(sb.toString().getBytes());
            fileOutputStream.close();
            return true;
        } catch (ConnectionFailException e) {
            e.printStackTrace();
            return false;
        } catch (ConnectionTimeOutException e2) {
            e2.printStackTrace();
            return false;
        } catch (ParseInvalidException e3) {
            e3.printStackTrace();
            return false;
        } catch (RequestFailException e4) {
            e4.printStackTrace();
            return false;
        } catch (UnauthorizedRequestException e5) {
            e5.printStackTrace();
            return false;
        } catch (UnsupportedCommandException e6) {
            e6.printStackTrace();
            return false;
        } catch (FileNotFoundException e7) {
            e7.printStackTrace();
            return false;
        } catch (IOException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    private static boolean fetchInMemLog(AbstractSlamwarePlatform abstractSlamwarePlatform, String str) {
        int updateSnapshot;
        if (abstractSlamwarePlatform == null) {
            return false;
        }
        try {
            IMessageLogFetcher createMessageLogFetcher = SlamwareInternalApi.createMessageLogFetcher(abstractSlamwarePlatform);
            if (createMessageLogFetcher == null || (updateSnapshot = createMessageLogFetcher.updateSnapshot()) < 0) {
                return false;
            }
            long snapshotSize = createMessageLogFetcher.getSnapshotSize(updateSnapshot);
            if (snapshotSize <= 0) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            long j = 0;
            while (j < snapshotSize) {
                byte[] readSnapshot = createMessageLogFetcher.readSnapshot(updateSnapshot, j, 4194304L);
                if (readSnapshot == null) {
                    return false;
                }
                fileOutputStream.write(readSnapshot);
                j += readSnapshot.length;
            }
            fileOutputStream.close();
            createMessageLogFetcher.releaseSnapshot(updateSnapshot);
            return true;
        } catch (ConnectionFailException e) {
            e.printStackTrace();
            return false;
        } catch (ConnectionLostException e2) {
            e2.printStackTrace();
            return false;
        } catch (ConnectionTimeOutException e3) {
            e3.printStackTrace();
            return false;
        } catch (CxxStdException e4) {
            e4.printStackTrace();
            return false;
        } catch (InvalidArgumentException e5) {
            e5.printStackTrace();
            return false;
        } catch (ParseInvalidException e6) {
            e6.printStackTrace();
            return false;
        } catch (RequestFailException e7) {
            e7.printStackTrace();
            return false;
        } catch (UnauthorizedRequestException e8) {
            e8.printStackTrace();
            return false;
        } catch (UnsupportedCommandException e9) {
            e9.printStackTrace();
            return false;
        } catch (FileNotFoundException e10) {
            e10.printStackTrace();
            return false;
        } catch (IOException e11) {
            e11.printStackTrace();
            return false;
        }
    }

    private static boolean saveRobotMap(AbstractSlamwarePlatform abstractSlamwarePlatform, String str) {
        if (abstractSlamwarePlatform == null) {
            return false;
        }
        try {
            new CompositeMapHelper().saveFile(str, abstractSlamwarePlatform.getCompositeMap());
            return true;
        } catch (ConnectionFailException e) {
            e.printStackTrace();
            return false;
        } catch (ConnectionTimeOutException e2) {
            e2.printStackTrace();
            return false;
        } catch (InvalidArgumentException e3) {
            e3.printStackTrace();
            return false;
        } catch (OperationFailException e4) {
            e4.printStackTrace();
            return false;
        } catch (ParseInvalidException e5) {
            e5.printStackTrace();
            return false;
        } catch (RequestFailException e6) {
            e6.printStackTrace();
            return false;
        } catch (UnauthorizedRequestException e7) {
            e7.printStackTrace();
            return false;
        } catch (UnsupportedCommandException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    private static boolean saveHiddenMap(AbstractSlamwarePlatform abstractSlamwarePlatform, String str) {
        if (abstractSlamwarePlatform == null) {
            return false;
        }
        try {
            CompositeMapHelper compositeMapHelper = new CompositeMapHelper();
            Map map = abstractSlamwarePlatform.getMap(MapType.BITMAP_8BIT, MapKind.SLAM_MAP, abstractSlamwarePlatform.getKnownArea(MapType.BITMAP_8BIT, MapKind.SLAM_MAP));
            GridMap gridMap = new GridMap();
            gridMap.setUsage("explore");
            gridMap.setDimension(map.getDimension());
            gridMap.setOrigin(new Location(map.getOrigin().getX(), map.getOrigin().getY(), 0.0f));
            gridMap.setResolution(map.getResolution());
            gridMap.setMapData(map.getData());
            CompositeMap compositeMap = new CompositeMap();
            ArrayList<MapLayer> arrayList = new ArrayList<>();
            arrayList.add(gridMap);
            compositeMap.setMaps(arrayList);
            compositeMap.setMetaData(gridMap.getMetaData());
            compositeMapHelper.saveFile(str, compositeMap);
            return true;
        } catch (ConnectionFailException e) {
            e.printStackTrace();
            return false;
        } catch (ConnectionTimeOutException e2) {
            e2.printStackTrace();
            return false;
        } catch (InvalidArgumentException e3) {
            e3.printStackTrace();
            return false;
        } catch (OperationFailException e4) {
            e4.printStackTrace();
            return false;
        } catch (ParseInvalidException e5) {
            e5.printStackTrace();
            return false;
        } catch (RequestFailException e6) {
            e6.printStackTrace();
            return false;
        } catch (UnauthorizedRequestException e7) {
            e7.printStackTrace();
            return false;
        } catch (UnsupportedCommandException e8) {
            e8.printStackTrace();
            return false;
        }
    }

    private static boolean fetchSystemLogs(String str) {
        byte[] resBody;
        try {
            SysLogsFetchArg sysLogsFetchArg = new SysLogsFetchArg();
            sysLogsFetchArg.setDeviceHost("192.168.11.1");
            sysLogsFetchArg.setTimeoutMS(300000L);
            sysLogsFetchArg.getReqParams().put("cmdName", "collect_system_log");
            SysLogsFetchResult fetchSysLog = SlamwareInternalApi.fetchSysLog(sysLogsFetchArg);
            if (fetchSysLog == null || (resBody = fetchSysLog.getResBody()) == null) {
                return false;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(str);
            fileOutputStream.write(resBody);
            fileOutputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
