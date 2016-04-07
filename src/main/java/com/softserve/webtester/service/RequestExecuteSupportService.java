package com.softserve.webtester.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.softserve.webtester.model.Environment;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.softserve.webtester.model.Variable;

@Service
public class RequestExecuteSupportService {

    private static final Logger LOGGER = Logger.getLogger(RequestExecuteSupportService.class);

    @Autowired
    private VelocityEngine velocityEngine;

    @Autowired
    private EnvironmentService environmentService;
    
    /**
     * Get value from executed SQL query on given connection builded on Environment 
     * @param input connection and query
     * @return string value
     */
    public String getExecutedQueryValue(Environment environment, String sqlQuery) throws SQLException {
        String result = null;
        if (sqlQuery.trim().toLowerCase().startsWith("select")) {
            Connection dbCon = null;
            try {
                dbCon = environmentService.getConnection(environment);
                Statement statement = dbCon.createStatement();
                ResultSet results = statement.executeQuery(sqlQuery);
                while(results.next()) {
                    result = results.getString(1);
                }
            } catch (SQLException e) {
                LOGGER.error("Could not execute query: " + sqlQuery, e);
                throw e;
            } catch (Exception e) {

            } finally {
                dbCon.close();
            }
            return result;
        } else {
            return result;
        }
    }
    
    /**
     * Get evaluated a String containing Velocity Template Language using list of Variable, and returns the result as a String.
     * @param input instance should be formatted, data to build context and data for logging
     * @return formatted string object
     */
    public String getEvaluatedString (String source, List<Variable> varibleList, String logString){
        VelocityContext context = new VelocityContext();
        for (Variable var : varibleList) {
            context.put(var.getName(), var.getValue());
        }
        StringWriter writer = new StringWriter();
        velocityEngine.evaluate(context, writer, logString, source);
        return writer.toString();
    }
    
    /**
     * Formats input XML or JSON instance.
     * 
     * @param input instance should be formatted
     * @return formatted string object
     */
    public String format(String input) {
        if (StringUtils.isBlank(input)) {
            return null;
        }
        if (input.startsWith("<")) {
            return prettyFormatXML(input, 2);
        }
        if (input.startsWith("[") || input.startsWith("{")) {
            return prettyFormatJSON(input);
        }
        return input;
    }

    /**
     * Formats XML string
     * 
     * @param input XML string should be formatted
     * @param indent indent count
     * @return formatted string object
     */
    private String prettyFormatXML(String input, int indent) {
        try {
            Source xmlInput = new StreamSource(new StringReader(input));
            StringWriter stringWriter = new StringWriter();
            StreamResult xmlOutput = new StreamResult(stringWriter);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            transformerFactory.setAttribute("indent-number", indent);
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(xmlInput, xmlOutput);
            return xmlOutput.getWriter().toString();
        } catch (Exception e) {
            LOGGER.error("Unable to parse XML: " + input);
        }
        return input;
    }

    /**
     * Formats JSON string
     * 
     * @param input JSON string should be formatted
     * @return formatted string object
     */
    private String prettyFormatJSON(String input) {
        try {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JsonParser jp = new JsonParser();
            JsonElement je = jp.parse(input);
            return gson.toJson(je);
        } catch (JsonSyntaxException e) {
            LOGGER.error("Unable to parse JSON: " + input);
        }
        return input;
    }
}