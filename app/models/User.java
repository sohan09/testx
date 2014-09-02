/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Table;
import play.data.validation.Constraints.Required;

/**
 *
 * @author sohan
 */
@Entity
@Table(name = "p_user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Required
    @Column(length = 50)
    private String firstName;

    @Required
    @Column(length = 50)
    private String lastName;

    @Required
    @Column(length = 100)
    private String email;
	
    @Required
    @Column(length = 200)
    private String userId;

    @Required
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;

    @Required
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    public User() {
    }

    public User(String firstName, String lastName, String email, String userId, Date createdDate, Date lastModifiedDate) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
		this.userId = userId;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public User(String firstName, String lastName, String email, String userId) {
        this(firstName, lastName, email, userId, null, null);
    }

    public User(String firstName, String lastName) {

        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {

        return lastName;
    }

    public void setFirstName(String firstName) {

        this.firstName = firstName;
    }

    public void setLastName(String lastName) {

        this.lastName = lastName;
    }
	
    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }
	
    public String getUserId() {

        return userId;
    }

    public void setUserId(String userId) {

        this.userId = userId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof User)) {
            return false;
        }
        User other = (User) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "models.User[ id=" + id + " ]";
    }

}
