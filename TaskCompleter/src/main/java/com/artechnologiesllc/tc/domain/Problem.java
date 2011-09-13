/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artechnologiesllc.tc.domain;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

/**
 * <p>Represents the alternate procedure if there is a problem with
 * a <code>Task</code>. This contains all the information necessary
 * for the operator to handle the problem, and send a case to the
 * correct person/group.</p>
 * 
 * <p>This class is an entity mapped to the <code>Problem</code> and
 * <code>ProblemProcedure</code> table.</p>
 * 
 * @author Dan Buhrman
 * @see Task
 */
@Entity
@Table(name="Problem")
@NamedQueries({
    @NamedQuery(name="com.artechnologiesllc.tc.problemById",
        query="from Problem as problem where problem.id = ?")
})
public class Problem implements Serializable {
    
    /**
     * The unique ID for this <code>Problem</code>.
     */
    private Integer id;
    /**
     * The title of the <code>Problem</code>.
     */
    private String title;
    /**
     * The person/group to contact.
     */
    private String contact;
    /**
     * The priority for the case.
     */
    private byte priority;
    /**
     * The path of the file containing documentation.
     */
    private String doc;
    /**
     * Procedures to follow.
     */
    private List<String> procedures;

    /**
     * Gets the unique ID for this <code>Problem</code>.
     * @return Unique ID.
     */
    @Id
    @Column(name="problem_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }
    /**
     * <p>Registers the unique ID for this problem.</p>
     * 
     * <p>This value should only be registered when the 
     * instance is persisted</p>
     * @param id Unique ID.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Gets the title of this <code>Problem</code>.
     * @return Title.
     */
    @Column(name="title", nullable=false, length=45)
    @NotNull
    public String getTitle() {
        return title;
    }
    /**
     * Registers the title of this <code>Problem</code>.
     * @param title Title.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * Gets the person/group to contact.
     * @return Contact person/group.
     */
    @Column(name="contact", nullable=false, length=25)
    @NotNull
    public String getContact() {
        return contact;
    }
    /**
     * Registers the person/group to contact
     * with this <code>Problem</code>.
     * @param contact Contact person/group.
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * Gets the priority of the case.
     * @return Priority of the case.
     */
    @Column(name="priority", nullable=false)
    @Range(min=1, max=5)
    public byte getPriority() {
        return priority;
    }
    /**
     * Registers the priority of the case to be
     * created for this <code>Problem</code>.
     * @param priority Priority of the case.
     */
    public void setPriority(byte priority) {
        this.priority = priority;
    }

    /**
     * Gets the path to the file containing the
     * documentation for this <code>Problem</code>.
     * @return Path to documentation file.
     */
    @Column(name="documentation")
    public String getDoc() {
        return doc;
    }
    /**
     * Registers the path to the documentation file.
     * @param doc Path to documentation file.
     */
    public void setDoc(String doc) {
        this.doc = doc;
    }
    
    /**
     * Gets the procedures to follow for this
     * <code>Problem</code>.
     * @return Procedures to follow.
     */
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="ProblemProcedures",
            joinColumns={@JoinColumn(name="problem_id", nullable=false)})
    @Column(name="procedure_value", nullable=false)
    @OrderColumn(name="procedure_num", nullable=false)
    @NotNull
    public List<String> getProcedures() {
        return procedures;
    }
    /**
     * Registers the list of procedures to follow
     * for this <code>Problem</code>.
     * @param procedures Procedures to follow.
     */
    public void setProcedures(List<String> procedures) {
        this.procedures = procedures;
    }
    
    @Override
    public boolean equals(Object obj) {
        try {
            Problem other = (Problem)obj;
            if(other.getId() != null && getId() != null)
                return other.getId().equals(getId());
            else
                return  other.getTitle().equals(getTitle()) &&
                        other.getContact().equals(getContact()) &&
                        other.getPriority() == getPriority() &&
                        other.getDoc().equals(getDoc()) &&
                        other.getProcedures().equals(getProcedures());
        } catch(ClassCastException e) {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() == null) ? 0 : getId();
        return hash;
    }
    
    @Override
    public String toString() {
        return ("Problem [" + getId() + "] " + getTitle());
    }
    
}
