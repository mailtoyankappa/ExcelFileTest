package com.bezkoder.spring.files.excel.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "hospital")
public class Hospital {

  @Id
  @Column(name = "code")
  private String code;

  @Column(name = "source")
  private String source;

  @Column(name = "codeListCode")
  private String codeListCode;

  @Column(name = "displayValue")
  private String displayValue;

  @Column(name = "longDescription")
  private String longDescription;

  @Column(name = "fromDate")
  private Date fromDate;

  @Column(name = "toDate")
  private Date toDate;

  @Column(name = "sortingPriority")
  private int sortingPriority;


  public Hospital() {

  }

  public Hospital(String code, String source, String codeListCode, String displayValue, String longDescription, Date fromDate, Date toDate, int sortingPriority) {
    this.code = code;
    this.source = source;
    this.codeListCode = codeListCode;
    this.displayValue = displayValue;
    this.longDescription = longDescription;
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.sortingPriority = sortingPriority;
  }

  @Override
  public String toString() {
    return "Hospital{" +
            "code=" + code +
            ", source='" + source + '\'' +
            ", codeListCode='" + codeListCode + '\'' +
            ", displayValue=" + displayValue +
            ", longDescription='" + longDescription + '\'' +
            ", fromDate='" + fromDate + '\'' +
            ", toDate=" + toDate +
            ", sortingPriority=" + sortingPriority +
            '}';
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  public String getCodeListCode() {
    return codeListCode;
  }

  public void setCodeListCode(String codeListCode) {
    this.codeListCode = codeListCode;
  }

  public String getDisplayValue() {
    return displayValue;
  }

  public void setDisplayValue(String displayValue) {
    this.displayValue = displayValue;
  }

  public String getLongDescription() {
    return longDescription;
  }

  public void setLongDescription(String longDescription) {
    this.longDescription = longDescription;
  }

  public Date getFromDate() {
    return fromDate;
  }

  public void setFromDate(Date fromDate) {
    this.fromDate = fromDate;
  }

  public Date getToDate() {
    return toDate;
  }

  public void setToDate(Date toDate) {
    this.toDate = toDate;
  }

  public int getSortingPriority() {
    return sortingPriority;
  }

  public void setSortingPriority(int sortingPriority) {
    this.sortingPriority = sortingPriority;
  }
}
