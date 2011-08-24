/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.taskcompleter.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import org.hibernate.annotations.IndexColumn;

/**
 *
 * @author dan
 */
@Entity
public class Problem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="PROBLEM_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable=false)
    private String title;
    
    @ElementCollection
    @IndexColumn(name="PROCEDURE_NUM")
    @JoinTable(name="PROBLEM_PROCEDURES",
            joinColumns = @JoinColumn(name="PROBLEM_ID"))
    private List<String> procedures;
    
    @Column(nullable=false)
    private String contactName;
    
    @Column(nullable=false)
    private String documentationFile;
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public List<String> getProcedures() {
        return procedures;
    }
    
    public void setProcedures(List<String> procedures) {
        this.procedures = procedures;
    }
    
    public String getContactName() {
        return contactName;
    }
    
    public void setContactName(String contactName) {
        this.contactName = contactName;
    }
    
    public String getDocumentationFile() {
        return documentationFile;
    }
    
    public void setDocumentationFile(String documentationFile) {
        this.documentationFile = documentationFile;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        try {
            Problem other = (Problem)object;
            if(other.id != null && id != null) {
                return other.id.equals(id);
            } else {
                return other.title.equals(title) &&
                        other.procedures.equals(procedures) &&
                        other.contactName.equals(contactName) &&
                        other.documentationFile.contains(documentationFile);
            }
        } catch(ClassCastException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "com.artechnologiesllc.taskcompleter.domain.Problem[ id=" + id + " ]";
    }
    
}
