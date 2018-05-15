package com.alfresco.support.alfrescodb.dao;

import com.alfresco.support.alfrescodb.model.UsersAndGroups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UsersAndGroupsMapper {
    @Select("select GROUPS.name groupName, count(USER_GROUP.user_id) occurrences, TENANT.name tenantName \n " +
            "from USER_GROUP, GROUPS, TENANT \n" +
            "where GROUPS.id = USER_GROUP.group_id \n" +
            "and TENANT.id = GROUPS.tenant_id \n" +
            "group by GROUPS.name, TENANT.name")
    List<UsersAndGroups> findUsersPerGroup();

    @Select("select GROUPS.name groupName, GROUP_CAPABILITY.name capability, TENANT.name tenantName \n" +
            "from GROUP_CAPABILITY, GROUPS, TENANT \n" +
            "where GROUPS.id = GROUP_CAPABILITY.group_id \n" +
            "and TENANT.id = GROUPS.tenant_id \n" +
            "order by GROUPS.name, TENANT.name" )
    List<UsersAndGroups> findCapabilitiesPerGroup();

    @Select("select count(*) as occurrences, TENANT.name \n" +
            "from USERS, TENANT \n" +
            "where TENANT.id = USERS.tenant_id \n" +
            "group by USERS.tenant_id")
    List<UsersAndGroups> findUsers();

    @Select("select count(*) as occurrences, TENANT.name " +
            "from GROUPS, TENANT " +
            "where TENANT.id = GROUPS.tenant_id " +
            "group by TENANT.name")
            List<UsersAndGroups> findGroups();
}