package com.alfresco.support.alfrescodb.dao;

import com.alfresco.support.alfrescodb.model.Content;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ContentMapper {
    // Content size by mimetype
    @Select("SELECT mime_type mimeType, count(*) occurrences, (sum(content_size) / 1024 / 1024) diskSpace " +
            "from RELATED_CONTENT " +
            "group by mime_type")
    List<Content> getContentSizeByMimeType();

    // Content size
    @Select("SELECT (sum(content_size) / 1024 / 1024) diskSpace " +
            "from RELATED_CONTENT")
    Float getContentSize();

    // Total Docs
    @Select("SELECT count(*) docsNumber from RELATED_CONTENT")
    Integer getDocsNumber();
}