package com.touchpoint.scenario;

import com.touchpoint.logging.Log;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;

public class ScenarioEnvironment {

    private Object currentPage;

    private final ThreadLocal<ScopedVariables> variables = new ThreadLocal<>();

    public Object getVar(String name) {
        return getVariables().get(name);
    }

    public void setVar(String name, Object object) {
        getVariables().put(name, object);
        String log = String.format("Value of [%s] is saved in [%s] variable", object, name);
        Log.info(log);
    }

    private ScopedVariables getVariables() {
        if (variables.get() == null) {
            variables.set(new ScopedVariables());
        }
        return variables.get();
    }

    public void setCurrentPage(Object page) {
        currentPage = page;
        Log.info("Current page is " + page.getClass().getName());
    }

    public Object getCurrentPage() {
        return currentPage;
    }

    public WebElement getElement(String elementName) throws IllegalAccessException, NoSuchFieldException {
        Class<?> clazz = currentPage.getClass();
        Field field =  clazz.getDeclaredField(elementName);
        field.setAccessible(true);
        return (WebElement) field.get(currentPage);
    }
}
