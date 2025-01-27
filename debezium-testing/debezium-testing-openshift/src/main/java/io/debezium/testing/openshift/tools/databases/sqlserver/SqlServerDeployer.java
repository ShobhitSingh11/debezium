/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.testing.openshift.tools.databases.sqlserver;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.debezium.testing.openshift.tools.databases.DatabaseDeployer;
import io.fabric8.kubernetes.api.model.Service;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.openshift.client.OpenShiftClient;

/**
 * @author Jakub Cechacek
 */
public class SqlServerDeployer extends DatabaseDeployer<SqlServerController> {

    public static class Deployer extends DatabaseBuilder<SqlServerDeployer.Deployer, SqlServerDeployer> {
        @Override
        public SqlServerDeployer build() {
            return new SqlServerDeployer(
                    project,
                    deployment,
                    services,
                    ocpClient);
        }
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(SqlServerDeployer.class);

    private SqlServerDeployer(
                              String project,
                              Deployment deployment,
                              List<Service> services,
                              OpenShiftClient ocp) {
        super("sqlserver", project, deployment, services, ocp);
    }

    @Override
    public SqlServerController getController(Deployment deployment, List<Service> services, String dbType, OpenShiftClient ocp) {
        return new SqlServerController(deployment, services, dbType, ocp);
    }
}
