package com.project.springstocks.controller;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DbController {

    DBInfo dbinfo;
    public DbController(DBInfo dbinfo){
        this.dbinfo = dbinfo;
    }

    @RequestMapping("/dbinfo")
    public DBInfo getInfo(){
        return this.dbinfo;
    }
}

@Component
class DBInfo {
    private String url;

    public DBInfo(DataSource dataSource) throws SQLException{
        this.url = dataSource.getConnection().getMetaData().getURL();
    }

    public String getUrl() {
        return url;
    }
}
