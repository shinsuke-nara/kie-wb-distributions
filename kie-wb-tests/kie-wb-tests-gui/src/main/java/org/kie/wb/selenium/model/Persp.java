/*
 * Copyright 2017 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kie.wb.selenium.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.kie.wb.selenium.model.persps.*;

import static org.kie.wb.selenium.model.KieWbDistribution.*;

public class Persp<T extends AbstractPerspective> {

    public static final Persp<HomePerspective> HOME
            = new Persp<>("N/A",
                          "Home",
                          HomePerspective.class);
    public static final Persp<AdminPagePerspective> ADMIN
            = new Persp<>("N/A",
                          "Admin",
                          AdminPagePerspective.class);
    public static final Persp<ArtifactRepositoryPerspective> ARTIFACTS
            = new Persp<>("N/A",
                          "Artifacts",
                          ArtifactRepositoryPerspective.class);

    public static final Persp<ProjectLibraryPerspective> PROJECTS
            = new Persp<>("Design",
                          "Projects",
                          ProjectLibraryPerspective.class,
                          KIE_DROOLS_WB,
                          KIE_WB);
    public static final Persp<BusinessDashboardsPerspective> DASHBOARDS
            = new Persp<>("Design",
                          "Dashboards",
                          BusinessDashboardsPerspective.class);

    public static final Persp<ProvisioningManagementPerspective> DEPLOYMENTS
            = new Persp<>("DevOps",
                          "Deployments",
                          ProvisioningManagementPerspective.class);
    public static final Persp<ExecutionServersPerspective> EXECUTION_SERVERS
            = new Persp<>("DevOps",
                          "Execution Servers",
                          ExecutionServersPerspective.class);

    public static final Persp<ProcessDefinitionsPerspective> PROCESS_DEFINITIONS
            = new Persp<>("Manage",
                          "Process Definitions",
                          ProcessDefinitionsPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<ProcessInstancesPerspective> PROCESS_INSTANCES
            = new Persp<>("Manage",
                          "Process Instances",
                          ProcessInstancesPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<TaskAdministrationPerspective> TASK_ADMINISTRATION
            = new Persp<>("Manage",
                          "Tasks Administration",
                          TaskAdministrationPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<JobsPerspective> JOBS
            = new Persp<>("Manage",
                          "Jobs",
                          JobsPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<ExecutionErrorsPerspective> EXECUTION_ERRORS
            = new Persp<>("Manage",
                          "Execution Errors",
                          ExecutionErrorsPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);

    public static final Persp<TasksPerspective> TASKS
            = new Persp<>("Track",
                          "Task Lists",
                          TasksPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<ProcessAndTaskDashboardPerspective> PROCESS_AND_TASK_DASHBOARD
            = new Persp<>("Track",
                          "Process & Task Reports",
                          ProcessAndTaskDashboardPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);
    public static final Persp<AppsPerspective> BUSINESS_DASHBOARDS
            = new Persp<>("Track",
                          "Business Dashboards",
                          AppsPerspective.class,
                          KIE_WB,
                          KIE_WB_MONITORING);

    private static final List<Persp<? extends AbstractPerspective>> ALL_PERSPECTIVES = Collections.unmodifiableList(Arrays.asList(
            ADMIN,
            HOME,
            PROJECTS,
            DASHBOARDS,
            DEPLOYMENTS,
            EXECUTION_SERVERS,
            PROCESS_DEFINITIONS,
            PROCESS_INSTANCES,
            TASK_ADMINISTRATION,
            JOBS,
            EXECUTION_ERRORS,
            TASKS,
            PROCESS_AND_TASK_DASHBOARD,
            BUSINESS_DASHBOARDS
    ));
    private final String parentMenu;
    private final String menuItem;
    private final Class<T> perspPageObjectClass;
    private final List<KieWbDistribution> kieWbDistributions;

    private Persp(String parentMenu,
                  String menuItem,
                  Class<T> perspPageObjectClass) {
        this(parentMenu,
             menuItem,
             perspPageObjectClass,
             KIE_DROOLS_WB,
             KIE_WB,
             KIE_WB_MONITORING);
    }

    /**
     * @param parentMenu Name of the top level menu in which the menu item to
     * access the perspective is present
     * @param menuItem Name of the item in the menu to navigate to the
     * perspective
     * @param perspPageObjectClass Selenium Page Object class representing given
     * perspective
     * @param distributions Distributions where perspective is present
     */
    private Persp(String parentMenu,
                  String menuItem,
                  Class<T> perspPageObjectClass,
                  KieWbDistribution... distributions) {
        this.parentMenu = parentMenu;
        this.menuItem = menuItem;
        this.perspPageObjectClass = perspPageObjectClass;
        this.kieWbDistributions = Arrays.asList(distributions);
    }

    public static List<Persp<? extends AbstractPerspective>> getAllPerspectives(final KieWbDistribution distribution) {
        return ALL_PERSPECTIVES.stream().filter(perspective -> perspective.getKieWbDistributions().contains(distribution)).collect(Collectors.toList());
    }

    public String getMenu() {
        return parentMenu;
    }

    public String getName() {
        return menuItem;
    }

    public Class<T> getPerspectivePageObjectClass() {
        return perspPageObjectClass;
    }

    public List<KieWbDistribution> getKieWbDistributions() {
        return kieWbDistributions;
    }

    /**
     * @return String used to identify parametrized testcases.
     */
    @Override
    public String toString() {
        return getName().replace(' ',
                                 '_');
    }
}
