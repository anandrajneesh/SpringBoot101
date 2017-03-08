package org.extras.config;

import org.mvel2.MVEL;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateCompiler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Anand_Rajneesh on 3/3/2017.
 */
@Component
@ConfigurationProperties
public class Configuration {

    private String tarrifRules;
    private String excludeRules;
    private String notAcceptedDoc;
    private String acceptedDoc;
    private String outputDir;
    private List<String> dateParameters;

    private Serializable compiledTarrifRules;
    private Serializable compiledExcludeRules;

    @PostConstruct
    public void initTemplates(){
        CompiledTemplate template = TemplateCompiler.compileTemplate(new File(tarrifRules));
        compiledTarrifRules = MVEL.compileExpression(template.getTemplate());

        template = TemplateCompiler.compileTemplate(new File(excludeRules));
        compiledExcludeRules = MVEL.compileExpression(template.getTemplate());

    }

    public String getTarrifRules() {
        return tarrifRules;
    }

    public void setTarrifRules(String tarrifRules) {
        this.tarrifRules = tarrifRules;
    }

    public String getExcludeRules() {
        return excludeRules;
    }

    public void setExcludeRules(String excludeRules) {
        this.excludeRules = excludeRules;
    }

    public String getNotAcceptedDoc() {
        return notAcceptedDoc;
    }

    public void setNotAcceptedDoc(String notAcceptedDoc) {
        this.notAcceptedDoc = notAcceptedDoc;
    }

    public String getAcceptedDoc() {
        return acceptedDoc;
    }

    public void setAcceptedDoc(String acceptedDoc) {
        this.acceptedDoc = acceptedDoc;
    }

    public String getOutputDir() {
        return outputDir;
    }

    public void setOutputDir(String outputDir) {
        this.outputDir = outputDir;
    }

    public List<String> getDateParameters() {
        return dateParameters;
    }

    public void setDateParameters(List<String> dateParameters) {
        this.dateParameters = dateParameters;
    }

    public Serializable getCompiledExcludeRules() {
        return compiledExcludeRules;
    }

    public void setCompiledExcludeRules(Serializable compiledExcludeRules) {
        this.compiledExcludeRules = compiledExcludeRules;
    }

    public Serializable getCompiledTarrifRules() {
        return compiledTarrifRules;
    }

    public void setCompiledTarrifRules(Serializable compiledTarrifRules) {
        this.compiledTarrifRules = compiledTarrifRules;
    }
}
