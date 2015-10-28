package com.sixgodit.xzz.jlatexmathexample;

import org.scilab.forge.jlatexmath.core.TeXFormula;

/**
 * Created by xuzhenzhou on 15/10/14.
 */
public class LaTeXInfo {
    private TeXFormula mTeXFormula;
    private int start;
    private int end;
    private String group;

    public LaTeXInfo( TeXFormula teXFormula, int start,int end, String group) {
        this.start = start;
        mTeXFormula = teXFormula;
        this.end = end;
        this.group = group;
    }

    public TeXFormula getTeXFormula() {
        return mTeXFormula;
    }

    public void setTeXFormula(TeXFormula teXFormula) {
        mTeXFormula = teXFormula;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
