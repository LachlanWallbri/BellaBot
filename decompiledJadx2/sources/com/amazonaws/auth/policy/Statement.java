package com.amazonaws.auth.policy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/* loaded from: classes.dex */
public class Statement {
    private Effect effect;
    private List<Resource> resources;
    private List<Principal> principals = new ArrayList();
    private List<Action> actions = new ArrayList();
    private List<Condition> conditions = new ArrayList();

    /* renamed from: id */
    private String f1138id = null;

    /* loaded from: classes.dex */
    public enum Effect {
        Allow,
        Deny
    }

    public Statement(Effect effect) {
        this.effect = effect;
    }

    public String getId() {
        return this.f1138id;
    }

    public void setId(String str) {
        this.f1138id = str;
    }

    public Statement withId(String str) {
        setId(str);
        return this;
    }

    public Effect getEffect() {
        return this.effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public List<Action> getActions() {
        return this.actions;
    }

    public void setActions(Collection<Action> collection) {
        this.actions = new ArrayList(collection);
    }

    public Statement withActions(Action... actionArr) {
        setActions(Arrays.asList(actionArr));
        return this;
    }

    public List<Resource> getResources() {
        return this.resources;
    }

    public void setResources(Collection<Resource> collection) {
        this.resources = new ArrayList(collection);
    }

    public Statement withResources(Resource... resourceArr) {
        setResources(Arrays.asList(resourceArr));
        return this;
    }

    public List<Condition> getConditions() {
        return this.conditions;
    }

    public void setConditions(List<Condition> list) {
        this.conditions = list;
    }

    public Statement withConditions(Condition... conditionArr) {
        setConditions(Arrays.asList(conditionArr));
        return this;
    }

    public List<Principal> getPrincipals() {
        return this.principals;
    }

    public void setPrincipals(Collection<Principal> collection) {
        this.principals = new ArrayList(collection);
    }

    public void setPrincipals(Principal... principalArr) {
        setPrincipals(new ArrayList(Arrays.asList(principalArr)));
    }

    public Statement withPrincipals(Principal... principalArr) {
        setPrincipals(principalArr);
        return this;
    }
}
