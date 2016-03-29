package com.softserve.webtester.service;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softserve.webtester.model.Variable;

@Service
public class RequestExecuteSupportService {

    private static final Logger LOGGER = Logger.getLogger(RequestExecuteSupportService.class);

    @Autowired
    private VelocityEngine velocityEngine;
    
    public String getExecutedQueryValue(Connection dbCon, String sqlQuery) throws SQLException {
        String result = null;
        try {
            if (sqlQuery.trim().toLowerCase().startsWith("select")) {
                Statement statement = dbCon.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);
                while(results.next()) {
                    result = results.getString(1);
                }
                return result;
            } else {
                return result;
            }
        } catch (SQLException e) {
            LOGGER.error("Could not execute query: " + sqlQuery, e);
            throw e;
        }
    }
    
    public String getEvaluatedString (String source, List<Variable> varibleList, String logString){
        VelocityContext context = new VelocityContext();
        for (Variable var : varibleList) {
            context.put(var.getName(), var.getValue());
        }
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, logString, source);
        return writer.toString();
    }
}
