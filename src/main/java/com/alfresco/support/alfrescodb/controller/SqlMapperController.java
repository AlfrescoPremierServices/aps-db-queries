package com.alfresco.support.alfrescodb.controller;

import com.alfresco.support.alfrescodb.dao.*;
import com.alfresco.support.alfrescodb.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import java.util.List;

@ComponentScan
@Component
public class SqlMapperController {

    @Value("${spring.datasource.platform}")
    private String dbType;

    @Autowired
    private UsersAndGroupsMapper usersAndGroupsMapper;

    @Autowired
    private WorkflowMapper workflowMapper;


    @Autowired
    private ContentMapper contentMapper;

    @Autowired
    private DbSizeMapper dbSizeMapper;

    @Autowired
    private  ConnectorsMapper connectorsMapper;

    // Database Sizing
    public List findTablesInfo(){
        if (dbType.equalsIgnoreCase("postgres")){
            return dbSizeMapper.findTablesInfoPostgres();
        } else if (dbType.equalsIgnoreCase("mysql")){
            return dbSizeMapper.findTablesInfoMysql();
        } else if (dbType.equalsIgnoreCase("microsoft")){
            return dbSizeMapper.findTablesInfoMSSql();
        } else if (dbType.equalsIgnoreCase("oracle")){
            return dbSizeMapper.findTablesInfoOracle();
        }

        return null;
    }

    public String findDbSize(){
        if (dbType.equals("postgres")){
            return dbSizeMapper.findDbSizePostgres();
        } else if (dbType.equalsIgnoreCase("mysql")){
            return dbSizeMapper.findDbSizeMysql();
        }

        return null;
    }

    public List findIndexesInfo(){
        if (dbType.equals("oracle")){
            return dbSizeMapper.findIndexesInfoOracle();
        } else if (dbType.equalsIgnoreCase("microsoft")){
            return dbSizeMapper.findIndexesInfoMSSql();
        }

        return null;
    }

    // Users and Groups
    public List findUsers(){
        return usersAndGroupsMapper.findUsers();
    }

    public List findGroups(){
        return usersAndGroupsMapper.findGroups();
    }

    public List findUsersPerGroup(){
        return usersAndGroupsMapper.findUsersPerGroup();
    }

    public List findCapabilitiesPerGroup(){
        return usersAndGroupsMapper.findCapabilitiesPerGroup();
    }

    // Workflows
    public List findOpenProcesses(){
        return workflowMapper.openProcesses();
    }

    public List findClosedProcesses(){
        return workflowMapper.closedProcesses();
    }

    public List findOpenTasks(){
        return workflowMapper.openTasks();
    }

    public List findClosedTasks(){
        return workflowMapper.closedTasks();
    }

    public List findOpenSingleTasks(){
        return workflowMapper.openSingleTasks();
    }

    public List findClosedSingleTasks(){
        return workflowMapper.closedSingleTasks();
    }

    public List <Workflows> getProcessDeployments(){
        return workflowMapper.processDeployments();
    }
	
	
	
	
	
	//Cody Addition
	public List findLongRunningWorkflows(){
        return workflowMapper.longRunningProcesses();
	}

	public List findCancelledWorkflows(){
        return workflowMapper.cancelledProcesses();
	}

	public List findCompletedWorkflows(){
        return workflowMapper.completedProcesses();
	}	
	
	public List findOpenWorkflowsCount(){
        return workflowMapper.openProcessesCount();
	}	
	//End Cody Addition	
		
		
		
		

    // Content Sizing
    public List <Content> getContentSizeByMimeType(){
        return contentMapper.getContentSizeByMimeType();
    }

    public Float getContentSize(){
        return contentMapper.getContentSize();
    }

    public Integer getDocsNumber(){
        return contentMapper.getDocsNumber();
    }

    // Connectors
    public List <AlfrescoEndPoint> getAlfrescoEndPoints(){
        return connectorsMapper.getAlfrescoEndPoints();
    }

    public List <DataSource> getDataSources(){
        return connectorsMapper.getDataSources();
    }

    public List <RestEndPoint> getRestEndPoints(){
        return connectorsMapper.getRestEndPoints();
    }
}