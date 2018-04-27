package utils;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * @Author: youxingyang
 * @date: 2018/4/26 9:40
 */
public final class IpAddressUtil {
    /**
     * ��ȡ����IP
     */
    public static final String INTRANET_IP = getIntranetIp();
    /**
     * ��ȡ����IP
     */
    public static final String INTERNET_IP = getInternetIp();

    private IpAddressUtil() {
    }

    /**
     * �������IP
     *
     * @return ����IP
     */
    private static String getIntranetIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * �������IP
     *
     * @return ����IP
     */
    private static String getInternetIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip;
            Enumeration<InetAddress> address;
            while (networks.hasMoreElements()) {
                address = networks.nextElement().getInetAddresses();
                while (address.hasMoreElements()) {
                    ip = address.nextElement();
                    if (ip != null && ip instanceof Inet4Address && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INTRANET_IP)) {
                        return ip.getHostAddress();
                    }
                }
            }
            // ���û������IP���ͷ�������IP
            return INTRANET_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println(getIntranetIp());
        System.out.println(getInternetIp());
    }
}
