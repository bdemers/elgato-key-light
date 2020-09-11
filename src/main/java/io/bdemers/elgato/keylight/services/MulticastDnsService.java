package io.bdemers.elgato.keylight.services;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.LifecycleProcessor;
import org.springframework.stereotype.Service;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Map;

/**
 * Sets up mDNS broadcasts for {@code _elg._tcp.local} to mimic an Elgato Key Light Air.
 */
@Service
public class MulticastDnsService implements LifecycleProcessor {

    private final JmDNS jmdns;

    private final int port;

    public MulticastDnsService(@Value("${server.port}") int port) {
        this.port = port;
        try {
            this.jmdns = JmDNS.create(InetAddress.getLocalHost());
            registerService();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to resolve address", e);
        }
    }

    @SneakyThrows
    private void registerService() {
        ServiceInfo serviceInfo = ServiceInfo.create("_elg._tcp.local", "Elgato Key Light Air 1337", port, 1, 1, Map.of(
                "mf", "Elgato",
                "dt", "200",
                "id", "id=3C:6A:9D:13:C1:BD",
                "md", "Elgato Key Light Air 20LAB9901",
                "pv", "1.0"
        ));
        jmdns.registerService(serviceInfo);
    }

    @Override
    public void start() {
        registerService();
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void stop() {
        jmdns.unregisterAllServices();
    }

    @Override
    public void onClose() {
        jmdns.unregisterAllServices();
    }

    @Override
    public boolean isRunning() {
        ServiceInfo[] infos = jmdns.list("_elg._tcp.local");
        return infos != null && infos.length > 0;
    }
}
