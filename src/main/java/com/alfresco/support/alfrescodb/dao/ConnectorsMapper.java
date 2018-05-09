package com.alfresco.support.alfrescodb.dao;

import com.alfresco.support.alfrescodb.model.AlfrescoEndPoint;
import com.alfresco.support.alfrescodb.model.DataSource;
import com.alfresco.support.alfrescodb.model.RestEndPoint;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ConnectorsMapper {
    // Alfresco End Points
    @Select("SELECT ALFRESCO_ENDPOINT.name, TENANT.name tenant, alf_repo_url alfRepoUrl, alf_share_url alfShareUrl, version " +
            "from ALFRESCO_ENDPOINT, TENANT " +
            "where ALFRESCO_ENDPOINT.tenant_id = TENANT.id")
    List<AlfrescoEndPoint> getAlfrescoEndPoints();

    // Data Source
    @Select("SELECT DATA_SOURCE.name, TENANT.name tenant, datasource_config dataSourceConfig " +
            "from DATA_SOURCE, TENANT " +
            "where DATA_SOURCE.tenant_id = TENANT.id")
    List<DataSource> getDataSources();

    // Rest End Points
    @Select("SELECT ENDPOINT_CONFIGURATION.name, TENANT.name tenant, host, port, pathuri pathUri " +
            "from ENDPOINT_CONFIGURATION, TENANT " +
            "where ENDPOINT_CONFIGURATION.tenant_id = TENANT.id")
    List<RestEndPoint> getRestEndPoints();
}