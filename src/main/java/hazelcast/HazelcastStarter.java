package hazelcast;

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

/**
 * Created by manishk on 10/13/16.
 */
public class HazelcastStarter {

    public static void main(String[] args) {
        Config config = new Config();

        // Create Join Config with TCP.
        config.getNetworkConfig().setJoin(new JoinConfig()
                .setMulticastConfig(
                        new MulticastConfig().setEnabled(false))
                .setTcpIpConfig(
                        new TcpIpConfig().setEnabled(true)
                                .addMember("10.84.195.214").addMember("192.168.156.1")));
        config.setManagementCenterConfig(
                new ManagementCenterConfig()
                        .setEnabled(true)
                        .setUrl("http://10.44.73.198:8080/mancenter"));

        // If we remove below line then MAP start showing on Management Center.
        config.getMapConfigs().put("111", new MapConfig().setName("ABC").setStatisticsEnabled(false).setBackupCount(0).setAsyncBackupCount(0));
        config.getMapConfigs().put("222", new MapConfig().setName("XYZ").setStatisticsEnabled(false).setBackupCount(0).setAsyncBackupCount(0));

        HazelcastInstance hazelcastInstance = Hazelcast.newHazelcastInstance(config);
        hazelcastInstance.getMap("ABC").put("name", "manish");
        hazelcastInstance.getMap("XYZ").put("name", "manish");
    }
}
