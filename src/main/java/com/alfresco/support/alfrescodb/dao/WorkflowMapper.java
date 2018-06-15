package com.alfresco.support.alfrescodb.dao;

import com.alfresco.support.alfrescodb.model.Workflows;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface WorkflowMapper {
    @Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, count(*) occurrences " +
            "from ACT_HI_PROCINST, ACT_RE_PROCDEF " +
            "where END_TIME_ is null " +
            "and ACT_HI_PROCINST.PROC_DEF_ID_= ACT_RE_PROCDEF.ID_ " +
            "group by ACT_RE_PROCDEF.NAME_, ACT_HI_PROCINST.TENANT_ID_")
    List<Workflows> openProcesses();

    @Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, count(*) occurrences " +
            "from ACT_HI_PROCINST, ACT_RE_PROCDEF " +
            "where END_TIME_ is not null " +
            "and ACT_HI_PROCINST.PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_ " +
            "group by ACT_RE_PROCDEF.NAME_, ACT_HI_PROCINST.TENANT_ID_")
    List<Workflows> closedProcesses();

    @Select("select ACT_HI_TASKINST.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, ACT_HI_TASKINST.NAME_ taskName, count(*) occurrences  " +
            "from ACT_HI_TASKINST, ACT_RE_PROCDEF " +
            "where ACT_HI_TASKINST.END_TIME_ is null " +
            "and ACT_HI_TASKINST.PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_ " +
            "group by ACT_RE_PROCDEF.NAME_, ACT_HI_TASKINST.NAME_, ACT_HI_TASKINST.TENANT_ID_ ")
    List<Workflows> openTasks();

    @Select("select ACT_HI_TASKINST.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, ACT_HI_TASKINST.NAME_ taskName, count(*) occurrences  " +
            "from ACT_HI_TASKINST, ACT_RE_PROCDEF " +
            "where ACT_HI_TASKINST.END_TIME_ is not null " +
            "and ACT_HI_TASKINST.PROC_DEF_ID_ = ACT_RE_PROCDEF.ID_ " +
            "group by ACT_RE_PROCDEF.NAME_, ACT_HI_TASKINST.NAME_, ACT_HI_TASKINST.TENANT_ID_ ")
    List<Workflows> closedTasks();

    @Select("select ACT_HI_TASKINST.TENANT_ID_ tenantName, ACT_HI_TASKINST.NAME_ taskName, count(*) occurrences " +
            "from ACT_HI_TASKINST " +
            "where ACT_HI_TASKINST.END_TIME_ is null " +
            "and  ACT_HI_TASKINST.PROC_DEF_ID_ is null " +
            "group by NAME_, ACT_HI_TASKINST.TENANT_ID_")
    List<Workflows> openSingleTasks();

    @Select("select ACT_HI_TASKINST.TENANT_ID_ tenantName, ACT_HI_TASKINST.NAME_ taskName, count(*) occurrences " +
            "from ACT_HI_TASKINST " +
            "where ACT_HI_TASKINST.END_TIME_ is not null " +
            "and  ACT_HI_TASKINST.PROC_DEF_ID_ is null " +
            "group by NAME_, ACT_HI_TASKINST.TENANT_ID_")
    List<Workflows> closedSingleTasks();

    @Select("select ACT_RE_PROCDEF.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, " +
            "ACT_RE_PROCDEF.VERSION_ version, ACT_RE_DEPLOYMENT.DEPLOY_TIME_ deployTime " +
            "from ACT_RE_PROCDEF, ACT_RE_DEPLOYMENT " +
            "where ACT_RE_DEPLOYMENT.ID_ = ACT_RE_PROCDEF.DEPLOYMENT_ID_ " +
            "order by ACT_RE_PROCDEF.VERSION_ asc")
    List<Workflows> processDeployments();
	
	
	
	
	
	
	
	
	
	
	//Cody Addition
	@Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, ACT_RE_PROCDEF.NAME_ procDefName, ACT_HI_PROCINST.START_TIME_ startTime " +
        "from ACT_HI_PROCINST, ACT_RE_PROCDEF " +
        "where END_TIME_ is null " +
        "and ACT_HI_PROCINST.PROC_DEF_ID_= ACT_RE_PROCDEF.ID_ " +
		"order by ACT_HI_PROCINST.START_TIME_ asc")
    List<Workflows> longRunningProcesses();
	
	
	
	@Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, ACT_HI_PROCINST.DELETE_REASON_ deleteReason, count(*) occurrences " +
		"from ACT_HI_PROCINST " +
		"where ACT_HI_PROCINST.DELETE_REASON_ is not null " +
		"group by ACT_HI_PROCINST.DELETE_REASON_, ACT_HI_PROCINST.TENANT_ID_")
	List<Workflows> cancelledProcesses();
	
	
	
	@Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, count(*) occurrences " +
		"from ACT_HI_PROCINST " +
		"where ACT_HI_PROCINST.END_TIME_ is not null and ACT_HI_PROCINST.DELETE_REASON_ is null " +
		"group by ACT_HI_PROCINST.TENANT_ID_")
	List<Workflows> completedProcesses();
	
	
	
	@Select("select ACT_HI_PROCINST.TENANT_ID_ tenantName, count(*) occurrences " +
		"from ACT_HI_PROCINST " +
		"where ACT_HI_PROCINST.END_TIME_ is null " +
		"group by ACT_HI_PROCINST.TENANT_ID_")
	List<Workflows> openProcessesCount();
	//End Cody Addition
}