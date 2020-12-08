package com.gytech.Utils;

import org.apache.commons.lang.StringUtils;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.Enumeration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Logger;


public final class UUID {

    private static final Logger LOG = Logger.getLogger(UUID.class.getName());
    private static final AtomicInteger COUNTER = new AtomicInteger(0);
    private static final int JVM;
    private static final int IP;

    static {
        JVM = (int) (System.currentTimeMillis() >>> 8);
        byte[] bytes = new byte[4];
        new SecureRandom().nextBytes(bytes);
        byte[] addr = getInetAddress();
        if (addr != null) {
            bytes[0] = addr[2];
            bytes[1] = addr[3];
        }
        IP = toInt(bytes);
    }

    private static byte[] getInetAddress() {
        try {
            Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
            while (interfaces.hasMoreElements()) {
                NetworkInterface ni = interfaces.nextElement();
                if (ni.isLoopback() || ni.isVirtual() || ni.isPointToPoint() || !ni.isUp()) {
                    continue;
                }
                String name = ni.getDisplayName().toLowerCase();
                if (name.contains("vmnet") || name.contains("tap")) {
                    continue;
                }
                Enumeration<InetAddress> addresses = ni.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    byte[] addr = addresses.nextElement().getAddress();
                    if (addr.length == 4) {
                        return addr;
                    }
                }
            }
        } catch (Exception e) {
            LOG.warning("Error to get ip address");
        }
        return null;
    }

    private static int toInt(byte[] bytes) {
        int result = 0;
        for (int i = 0; i < 4; i++) {
            result = result << 8 | (bytes[i] & 0xff);
        }
        return result;
    }

    private static short getCount() {
        return (short) COUNTER.incrementAndGet();
    }

    private static String format(int intValue) {
        return StringUtils.leftPad(Integer.toHexString(intValue), 8, '0');
    }

    private static String format(short shortValue) {
        return StringUtils.leftPad(Integer.toHexString(shortValue), 4, '0');
    }

    public static String hex32() {
        long now = System.currentTimeMillis();
        short hiTime = (short) (now >>> 32);
        int loTime = (int) now;
        return format(hiTime) + format(loTime) + format(IP) + format(JVM) + format(getCount());
    }

    public static byte[] bytes() {
        long now = System.currentTimeMillis();
        short hiTime = (short) (now >>> 32);
        int loTime = (int) now;
        ByteBuffer buf = ByteBuffer.allocate(16);
        buf.putShort(hiTime);
        buf.putInt(loTime);
        buf.putInt(IP);
        buf.putInt(JVM);
        buf.putShort(getCount());
        return buf.array();
    }
}
