package com.alfresco.support.alfrescodb.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import com.alfresco.support.alfrescodb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class WebController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${reportFile}")
    private String reportFile;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    SqlMapperController sqlMapper;

    @Value("${spring.datasource.platform}")
    private String dbType;

    @RequestMapping("/")
    public String index(String name, Model model) {
        return "index";
    }

    @RequestMapping("/report")
    public void report(Model model) {

        model.addAttribute("reportFile", reportFile);

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(reportFile));

            // Database Size
            List < RelationInfo > listRelationInfos = sqlMapper.findTablesInfo();
            out.write("Database Tables Information");

            if (dbType.equalsIgnoreCase("mysql") || dbType.equalsIgnoreCase("postgres")) {
                out.write("\nTable Name, Total Size, Row Estimate, Table Size, Index Size");

                for (int i = 0; i < listRelationInfos.size(); i++) {
                    out.write(listRelationInfos.get(i).printDbInfo());
                }

                String dbSize = sqlMapper.findDbSize();
                out.write("\n\nDatabase Size");
                out.write("\nSize");
                out.write(dbSize);
            } else if (dbType.equalsIgnoreCase("oracle")){
                List<OracleRelationInfo> OracleListRelationInfos = sqlMapper.findTablesInfo();

                out.write("\nTables Size");
                out.write("\nTable Name, Size MB");
                for (int i = 0; i < OracleListRelationInfos.size(); i++) {
                    out.write(OracleListRelationInfos.get(i).printTableInfo());
                }

                List<OracleRelationInfo> OracleListIndexesInfos = sqlMapper.findTablesInfo();

                out.write("\n\nIndexes Size");
                out.write("\nTable Name, Index Name, Index Size MB");
                for (int i = 0; i < OracleListIndexesInfos.size(); i++) {
                    out.write(OracleListIndexesInfos.get(i).printIndexInfo());
                }
            } else if (dbType.equalsIgnoreCase("microsoft")) {
                List<MSSqlRelationInfo> MSSqlListRelationInfos = sqlMapper.findTablesInfo();

                out.write("\nTables Size");
                out.write("\nTable Name, Rows Count, Total Space KB, Used Space KB, Unused Space KB");
                for (int i = 0; i < listRelationInfos.size(); i++) {
                    out.write(MSSqlListRelationInfos.get(i).printTableInfo());
                }

                List<MSSqlRelationInfo> MSSqlListIndexesInfos = sqlMapper.findIndexesInfo();

                out.write("\n\nIndexes Size");
                out.write("\nTable Name, Index Name, Index Size KB");
                for (int i = 0; i < MSSqlListIndexesInfos.size(); i++) {
                    out.write(MSSqlListIndexesInfos.get(i).printIndexInfo());
                }
            }

            // Users and groups
            List < UsersAndGroups > listUsersAndGroups = sqlMapper.findUsersPerGroup();
            out.write("\n\nGroups");
            out.write("\nTenant, Group Name, No. of Users");
            if (listUsersAndGroups != null) {
                for (int i = 0; i < listUsersAndGroups.size(); i++) {
                    out.write(listUsersAndGroups.get(i).printGroups());
                }
            }

            // Content by Mimetype
            List < Content > listContent = sqlMapper.getContentSizeByMimeType();
            out.write("\n\nContent by Mimetype");
            out.write("\nMimetype, No. of Documents, Size");
            if (listContent != null) {
                for (int i = 0; i < listContent.size(); i++) {
                    out.write(listContent.get(i).printContentByMimemtype());
                }
            }

            // No of documents
            Float contentSize = sqlMapper.getContentSize();
            Integer numDocuments = sqlMapper.getDocsNumber();
            out.write("\n\nContent Size");
            out.write("\nNo. of Documents, Size");
            out.write("\n" + numDocuments + "," + contentSize);

            // Groups capabilities
            List < UsersAndGroups > listCapabilities = sqlMapper.findCapabilitiesPerGroup();
            out.write("\n\nGroups");
            out.write("\nTenant, Group Name, No. of Users");
            if (listCapabilities != null) {
                for (int i = 0; i < listCapabilities.size(); i++) {
                    out.write(listCapabilities.get(i).printCapabilities());
                }
            }

            // Workflows
            List < Workflows > listOpenWorkflows = sqlMapper.findOpenProcesses();
            out.write("\n\nOpen Workflows");
            out.write("\nTenant, Process Definition, No Occurrences");
            if (listOpenWorkflows != null) {
                for (int i = 0; i < listOpenWorkflows.size(); i++) {
                    out.write(listOpenWorkflows.get(i).printProcesses());
                }
            }

            List < Workflows > listClosedWorkflows = sqlMapper.findClosedProcesses();
            out.write("\n\nClosed Workflows");
            out.write("\nPTenant, rocess Definition, No Occurrences");
            if (listClosedWorkflows != null) {
                for (int i = 0; i < listClosedWorkflows.size(); i++) {
                    out.write(listClosedWorkflows.get(i).printProcesses());
                }
            }

            List < Workflows > listOpenTasks = sqlMapper.findOpenTasks();
            out.write("\n\nOpen Tasks");
            out.write("\nTenant, Process Definition, Task Name, No Occurrences");
            if (listOpenTasks != null) {
                for (int i = 0; i < listOpenWorkflows.size(); i++) {
                    out.write(listOpenWorkflows.get(i).printTasks());
                }
            }

            List < Workflows > listClosedTasks = sqlMapper.findClosedTasks();
            out.write("\n\nClosed Tasks");
            out.write("\nTenant, Process Definition, Task Name, No Occurrences");
            if (listClosedTasks != null) {
                for (int i = 0; i < listClosedTasks.size(); i++) {
                    out.write(listClosedTasks.get(i).printTasks());
                }
            }

            List < Workflows > listOpenSingleTasks = sqlMapper.findOpenSingleTasks();
            out.write("\n\nOpen Single Tasks");
            out.write("\nTenant, Process Definition, Task Name, No Occurrences");
            if (listOpenSingleTasks != null) {
                for (int i = 0; i < listOpenSingleTasks.size(); i++) {
                    out.write(listOpenSingleTasks.get(i).printSingleTasks());
                }
            }

            List < Workflows > listClosedSingleTasks = sqlMapper.findClosedSingleTasks();
            out.write("\n\nClosed Single Tasks");
            out.write("\nTenant, Process Definition, Task Name, No Occurrences");
            if (listClosedSingleTasks != null) {
                for (int i = 0; i < listOpenSingleTasks.size(); i++) {
                    out.write(listClosedSingleTasks.get(i).printSingleTasks());
                }
            }

            // Process Deployments
            List < Workflows > listDeployments = sqlMapper.getProcessDeployments();
            out.write("\n\nProcess Deployments");
            out.write("\nTenant, Process Definition, Version, Deployment Time");
            if (listUsersAndGroups != null) {
                for (int i = 0; i < listDeployments.size(); i++) {
                    out.write(listDeployments.get(i).printDeployments());
                }
            }
            out.close();
        } catch (IOException e) {
            System.out.println("Exception ");

        }
    }

    @RequestMapping("/workflows")
    public void workflows(Model model) {

        // Count open processes
        List < Workflows > listOpenWorkflows = sqlMapper.findOpenProcesses();
        model.addAttribute("listOpenWorkflows", listOpenWorkflows);

        // Count closed processes
        List < Workflows > listClosedWorkflows = sqlMapper.findClosedProcesses();
        model.addAttribute("listClosedWorkflows", listClosedWorkflows);

        // Count open tasks
        List < Workflows > listOpenTasks = sqlMapper.findOpenTasks();
        model.addAttribute("listOpenTasks", listOpenTasks);

        // Count closed tasks
        List < Workflows > listClosedTasks = sqlMapper.findClosedTasks();
        model.addAttribute("listClosedTasks", listClosedTasks);

        // Count open single tasks
        List < Workflows > listOpenSingleTasks = sqlMapper.findOpenSingleTasks();
        model.addAttribute("listOpenSingleTasks", listOpenSingleTasks);

        // Count closed tasks
        List < Workflows > listClosedSingleTasks = sqlMapper.findClosedSingleTasks();
        model.addAttribute("listClosedSingleTasks", listClosedSingleTasks);

        // Process Deployments
        List < Workflows > listProcessDeployments = sqlMapper.getProcessDeployments();
        model.addAttribute("listProcessDeployments", listProcessDeployments);
    }

    @RequestMapping("/dbSize")
    public void dbSize(Model model) {
        if (dbType.equalsIgnoreCase("mysql") || dbType.equalsIgnoreCase("postgres")) {
            List < RelationInfo > listRelationInfos = sqlMapper.findTablesInfo();
            model.addAttribute("listRelationInfos", listRelationInfos);

            String dbSize = sqlMapper.findDbSize();
            model.addAttribute("dbSize", dbSize);
        } else if (dbType.equalsIgnoreCase("oracle")){
            List<OracleRelationInfo> OracleListRelationInfos = sqlMapper.findTablesInfo();
            model.addAttribute("OracleListRelationInfos", OracleListRelationInfos);

            List<OracleRelationInfo> OracleListIndexesInfos = sqlMapper.findIndexesInfo();
            model.addAttribute("OracleListIndexesInfos", OracleListIndexesInfos);
        } else if (dbType.equalsIgnoreCase("microsoft")) {
            List<MSSqlRelationInfo> MSSqlListRelationInfos = sqlMapper.findTablesInfo();
            model.addAttribute("MSSqlListRelationInfos", MSSqlListRelationInfos);

            List<MSSqlRelationInfo> MSSqlListIndexesInfos = sqlMapper.findIndexesInfo();
            model.addAttribute("MSSqlListIndexesInfos", MSSqlListIndexesInfos);
        }
    }

    @RequestMapping("/usersAndGroups")
    public void authorities(Model model) {

        //Count users per group
        List < UsersAndGroups > listUsersAndGroups = sqlMapper.findUsersPerGroup();
        model.addAttribute("listUsersAndGroups", listUsersAndGroups);

        //Groups Capabilities
        List < UsersAndGroups > listGroupsCapabilities = sqlMapper.findCapabilitiesPerGroup();
        model.addAttribute("listGroupsCapabilities", listGroupsCapabilities);
    }

    @RequestMapping("/content")
    public void content(Model model) {

        //Content Size by Mime Type
        List < Content > listContentSizeByMimeType = sqlMapper.getContentSizeByMimeType();
        model.addAttribute("listContentSizeByMimeType", listContentSizeByMimeType);

        //Content Total Content Size
        Float contentSize = sqlMapper.getContentSize();
        model.addAttribute("contentSize", contentSize);

        //Content Total Docs Number
        Integer docsNumber = sqlMapper.getDocsNumber();
        model.addAttribute("docsNumber", docsNumber);
    }

    @RequestMapping("/deployments")
    public void deployments(Model model) {

        //Process Deployments
        List < Workflows > listProcessDeployments = sqlMapper.getProcessDeployments();
        model.addAttribute("listProcessDeployments", listProcessDeployments);
    }

    @RequestMapping("/endPoints")
    public void endPoints(Model model) {

        //Alfresco End Points
        List < AlfrescoEndPoint > listAlfrescoEndPoints = sqlMapper.getAlfrescoEndPoints();
        model.addAttribute("listAlfrescoEndPoints", listAlfrescoEndPoints);

        //Data Sources
        List < DataSource > listDataSources = sqlMapper.getDataSources();
        model.addAttribute("listDataSources", listDataSources);

        //Rest End Points
        List < RestEndPoint > listRestEndPoints = sqlMapper.getRestEndPoints();
        model.addAttribute("listRestEndPoints", listRestEndPoints);
    }
}