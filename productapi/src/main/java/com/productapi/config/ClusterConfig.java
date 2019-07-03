package com.productapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractClusterConfiguration;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.core.cql.keyspace.KeyspaceOption;

import java.util.Arrays;
import java.util.List;

@Configuration
public class ClusterConfig extends AbstractClusterConfiguration {

    private final String keyspace;
    private final String hosts;

    ClusterConfig(
            @Value("${spring.data.cassandra.keyspace-name}") String keyspace,
            @Value("${spring.data.cassandra.contact-points}") String hosts) {
        this.keyspace = keyspace;
        this.hosts = hosts;
    }

    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        final CreateKeyspaceSpecification specification =
                CreateKeyspaceSpecification.createKeyspace(keyspace)
                        .ifNotExists()
                        .with(KeyspaceOption.DURABLE_WRITES, true)
                        .withSimpleReplication();
        return Arrays.asList(specification);
    }

    @Override
    protected String getContactPoints() {
        return hosts;
    }

    @Override
    protected boolean getMetricsEnabled() { return false; }

}
